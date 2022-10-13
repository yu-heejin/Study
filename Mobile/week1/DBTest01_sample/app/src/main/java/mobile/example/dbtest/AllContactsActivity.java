package mobile.example.dbtest;

import java.util.ArrayList;

import android.app.Activity;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class AllContactsActivity extends Activity {
	
	private ListView lvContacts = null;

	private ArrayAdapter<ContactDto> adapter;
	private ContactDBHelper helper;
	private ArrayList<ContactDto> contactList;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_all_contacts);

		helper = new ContactDBHelper(this);
		contactList = new ArrayList<ContactDto>();

		lvContacts = (ListView)findViewById(R.id.lvContacts);
		adapter = new ArrayAdapter<ContactDto>(this, android.R.layout.simple_list_item_1, contactList);

		lvContacts.setAdapter(adapter);
	}



	@Override
	protected void onResume() {
		super.onResume();   // 화면이 감춰졌다가 호출될 때 다시 실행
		//즉 화면을 나갔다가 다시 들어오면 화면에 같은 데이터가 중복으로 들어갈 가능성 O

		contactList.clear();   //DB에 값을 넣기 전 기존 리스트 삭제 -> 데이터 중복 문제 해결
		//새로 데이터를 읽어와도 전혀 문제되지 않음
		
		SQLiteDatabase db = helper.getReadableDatabase();
		Cursor cursor = db.rawQuery("select * from " + ContactDBHelper.TABLE_NAME, null);

		while(cursor.moveToNext()) {
			ContactDto dto = new ContactDto();

			dto.setId(cursor.getInt(cursor.getColumnIndex("_id")));
			dto.setName(cursor.getString(cursor.getColumnIndex(ContactDBHelper.COL_NAME))); //cursor.getString(1)과 동일
			dto.setPhone(cursor.getString(cursor.getColumnIndex(ContactDBHelper.COL_PHONE)));
			dto.setCategory(cursor.getString(cursor.getColumnIndex(ContactDBHelper.COL_CAT)));

			contactList.add(dto);
		}

		adapter.notifyDataSetChanged();  //화면 값 갱신 요청, 새로운 DB값으로 변경

		cursor.close();
		helper.close();
	}

}




