package mobile.database.dbtest02;

import android.content.DialogInterface;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.CursorAdapter;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

public class AllContactsActivity extends AppCompatActivity {
	
	ListView lvContacts = null;
	ContactDBHelper helper;
	Cursor cursor;
	//SimpleCursorAdapter adapter;
	MyCursorAdapter adapter;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_all_contacts);
		lvContacts = (ListView)findViewById(R.id.lvContacts);

		helper = new ContactDBHelper(this);

//		  SimpleCursorAdapter 객체 생성
//        adapter = new SimpleCursorAdapter(this, android.R.layout.simple_list_item_1, null,
//				new String[] {"phone"}, new int[] {android.R.id.text1}, CursorAdapter.FLAG_REGISTER_CONTENT_OBSERVER);
		//simple_list_item_1 을 사용할 때 textView id는 text1로 고정되어있음!

		//myCursorAdapter
		adapter = new MyCursorAdapter(this, R.layout.listview_layout, null);
		lvContacts.setAdapter(adapter);

//		adapter = new SimpleCursorAdapter(this, android.R.layout.simple_list_item_2, null,
//				new String[] {"name", "phone"}, new int[] {android.R.id.text1, android.R.id.text2}, CursorAdapter.FLAG_REGISTER_CONTENT_OBSERVER);
//		lvContacts.setAdapter(adapter);

//		리스트 뷰 클릭 처리
//        lvContacts.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
//            @Override
//            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int position, long id) {
//				//이제 position 값 말고 id 값을 직접 사용할 수 있다
//
//           return true;
//            }
//        });

		//리스트 뷰 롱클릭 처리
		lvContacts.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
			@Override
			public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
				//DB에 바로 삭제
				//다이얼로그를 띄워야함 -> 아래 삭제 코드는 다이얼로그에서 확인을 눌렀을 때 동작해야함
				//확인해야 삭제, 아니면 삭제할 필요가 없음
				//해당 코드에서 한가지 정보를 기록해서 유지할 수 있도록 해야하는데 그것이 바로 id값임

				final long dbId = id;    //상수로 만들어서 값을 유지시키고 해당 값을 다이얼로그에서 사용

				AlertDialog.Builder builder = new AlertDialog.Builder(AllContactsActivity.this);
				builder.setTitle("delete")
						.setPositiveButton("삭제", new DialogInterface.OnClickListener() {
							@Override
							public void onClick(DialogInterface dialog, int which) {
								String whereClause = "_id=?";
								//String[] whereArgs = new String[] {String.valueOf(id)};   //id값 typeCasting
								//바깥의 id값은 쓸 수 없음(실제로 오류남)
								//지역변수이기 때문에 다이얼로그가 뜨면서 메소드가 계속 진행되면서 사라짐
								//final로 만들어서 보관해야 다이얼로그에서 사용할 수 있음

								String[] whereArgs = new String[] {String.valueOf(dbId)};
								db.delete(ContactDBHelper.TABLE_NAME, whereClause, whereArgs);
							}
						});

//				String whereClause = "_id=?";
//				//String[] whereArgs = new String[] {String.valueOf(id)};   //id값 typeCasting
//				String[] whereArgs = new String[] {String.valueOf(dbId)};

				//id 값을 위 같이 사용할 땐 상관없지만, 다이얼로그로 확인버튼을 누를 때는 문제가 됨
				//다이얼로그 확인 버튼은 나중에 누르기 때문에 지역변수 id값은 사라짐 -> 유지 필요

//				db.delete(ContactDBHelper.TABLE_NAME, whereClause, whereArgs);
				return true;
			}
		});


	}

	@Override
	protected void onResume() {
		super.onResume();
//        DB에서 데이터를 읽어와 Adapter에 설정
        SQLiteDatabase db = helper.getReadableDatabase();
        cursor = db.rawQuery("select * from " + ContactDBHelper.TABLE_NAME, null);

        adapter.changeCursor(cursor);
        helper.close();
	}

	@Override
	protected void onDestroy() {
		super.onDestroy();
//        cursor 사용 종료
		if (cursor != null) cursor.close();
	}

}




