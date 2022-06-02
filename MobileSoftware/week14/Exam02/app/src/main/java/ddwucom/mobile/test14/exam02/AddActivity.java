package ddwucom.mobile.test14.exam02;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class AddActivity extends AppCompatActivity {

    EditText etFood;
    EditText etNation;

    FoodDBHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);

        etFood = findViewById(R.id.et_food_name);
        etNation = findViewById(R.id.et_nation);

        dbHelper = new FoodDBHelper(this);
    }


    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_add:
                SQLiteDatabase db = dbHelper.getWritableDatabase();
                ContentValues value = new ContentValues();
                value.put(FoodDBHelper.COL_FOOD, etFood.getText().toString());
                value.put(FoodDBHelper.COL_NATION, etNation.getText().toString());

//                insert 메소드를 사용할 경우 데이터 삽입이 정상적으로 이루어질 경우 1 이상, 이상이 있을 경우 0 반환 확인 가능
                long count = db.insert(FoodDBHelper.TABLE_NAME, null, value);

                if (count > 0) {    // 정상수행에 따른 처리
                    Intent resultIntent = new Intent();
                    resultIntent.putExtra("food", etFood.getText().toString() );
                    setResult(RESULT_OK, resultIntent);
                    dbHelper.close();
                    finish();
                } else {        // 이상에 따른 처리
                    Toast.makeText(this, "새로운 음식 추가 실패!", Toast.LENGTH_SHORT).show();
                    dbHelper.close();
                }

                break;
            case R.id.btn_cancel:   // 취소에 따른 처리
                setResult(RESULT_CANCELED);
                finish();
                break;
        }
    }
}
