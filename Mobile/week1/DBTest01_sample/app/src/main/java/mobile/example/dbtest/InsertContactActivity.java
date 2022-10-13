package mobile.example.dbtest;

import android.app.Activity;
import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class InsertContactActivity extends Activity {

	ContactDBHelper helper;
	EditText etName;
	EditText etPhone;
	EditText etCategory;
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_insert_contact);

//      DBHelper 생성
		helper = new ContactDBHelper(this);

		// 화면 요소를 가져옴
		etName = (EditText)findViewById(R.id.editText1);
		etPhone = (EditText)findViewById(R.id.editText2);
		etCategory = (EditText)findViewById(R.id.editText3);
	}
	
	
	public void onClick(View v) {
		//helper에서 db를 가져옴
		SQLiteDatabase db = helper.getWritableDatabase();

		ContentValues row = new ContentValues();
		row.put(ContactDBHelper.COL_NAME, etName.getText().toString());
		row.put(ContactDBHelper.COL_PHONE, etPhone.getText().toString());
		row.put(ContactDBHelper.COL_CAT, etCategory.getText().toString());

		//insert문은 long타입의 값을 반환
		//insert 수행 후 영향을 받은 row의 개수를 반환함! -> result값이 0보다 크면 정상적으로 반환이 됐음을 뜻함
		long result = db.insert(ContactDBHelper.TABLE_NAME, null, row);

		//SQL문 상에서는 ' '로 묶여야만 문자열로 취급됨
		//exec의 경우 void타입 -> 반환값이 없기 때문에 해당 기능이 제대로 수행이 됐는지 알 수 없음 (try-catch문 사용 필요)
		db.execSQL("insert into " + ContactDBHelper.TABLE_NAME + " values ( NULL, '"
		+ etName.getText().toString() + "', '" + etPhone.getText().toString() + "', '" + etCategory.getText().toString() + "');");
		//메소드 방식으로 사용하는 것이 더 편함

		helper.close();    //명시적으로 커밋을 위해 사용한다
		//db를 close하는 것이 아니라 helper를 close
	}
	

}
