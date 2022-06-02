package ddwucom.mobile.test14.exam02;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

public class UpdateActivity extends AppCompatActivity {
    Food food;     //get_id를 통해 아이디를 알아낼 수 있음

    EditText etFood;
    EditText etNation;

    FoodDBManager foodDBManager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update);

        food = (Food) getIntent().getSerializableExtra("food");
        //반환되는 형태가 serializable 인터페이스이기 때문에 타입 캐스팅을 해줘야함
        etFood = findViewById(R.id.et_food_name);
        etNation = findViewById(R.id.et_nation);

        etFood.setText(food.getFood());
        etNation.setText(food.getNation());
        //전달받은 객체의 음식값과 국가명을 받아옴

        foodDBManager = new FoodDBManager(this);
    }

    public void onClick(View v) {
        switch(v.getId()) {
            case R.id.btn_update:
                //DB를 직접 접근하는 코드 사용
                //값이 바뀌지 않아도 그냥 데이터베이스에 update함(즉 값이 같은지 다른지 식별하지 않음)
                String foodName = etFood.getText().toString();
                String nation = etNation.getText().toString();
                //새로운 내용을 받아옴
                //전달받은 객체를 바로 쓸 수 있도록(?) 선언
                food.setFood(foodName);   //food.setFood(etFood.getText().toString());
                food.setNation(nation);   //food.setFood(etNation.getText().toString());
                boolean result = foodDBManager.modifyFood(food);
                //수정한 객체를 통째로 전달한다.
                if(result) {
                    //코드 잘 됨
                    Intent resultIntent = new Intent();   //절대 startActivity 하면 안됨(이미 Main이 실행상태임)
                    //resultIntent.putExtra("foodName", foodName);   //resultIntent.putExtra("foodName", food.getFood());
                    resultIntent.putExtra("food", food);   //바뀐 food 객체를 담아놓을 수도 있음(바뀐 정보를 알아낼 수 있음)
                    setResult(RESULT_OK, resultIntent);
                } else {
                    //잘 안됨
                    setResult(RESULT_CANCELED);
                }

//                FoodDBHelper foodDBHelper = new FoodDBHelper(this);
//                SQLiteDatabase sqLiteDatabase = foodDBHelper.getWritableDatabase();
//
//
//                ContentValues row = new ContentValues();   //바꿀 내용을 저장하는 ContentValues
//                row.put(FoodDBHelper.COL_FOOD, foodName);
//                row.put(FoodDBHelper.COL_NATION, nation);
//                //바꿀 데이터를 삽입함(editText의 값을 삽입)
//
//                String whereClause = FoodDBHelper.COL_ID + "=?";
//                String[] whereArgs = new String[] { String.valueOf(food.get_id()) };
//                //where절의 조건을 선언함
//
//                int result = sqLiteDatabase.update(foodDBHelper.TABLE_NAME, row, whereClause, whereArgs);
//
//                if(result > 0) {
//                    //업데이트 잘 된 경우
//                    //메인으로 돌아갈 때 결과가 잘 설정됐음을 알려줌(setResult)
//                    Intent resultIntent = new Intent();   //절대 startActivity 하면 안됨(이미 Main이 실행상태임)
//                    resultIntent.putExtra("foodName", foodName);
//                    setResult(RESULT_OK, resultIntent);
//                } else {
//                    //업데이트 잘 안 된 경우
//                    //실패한 경우를 리턴함
//                    setResult(RESULT_CANCELED);
//                }
                break;

            case R.id.btn_cancel:
                setResult(RESULT_CANCELED);
                break;
        }
        finish();
    }
}
