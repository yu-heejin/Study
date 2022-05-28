package ddwucom.mobile.test13.exam01;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    TextView tvDisplay;
    FoodDBHelper myDbHelper;
    ArrayList<Food> foodArrayList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tvDisplay = findViewById(R.id.tvDisplay);

        myDbHelper = new FoodDBHelper(this);
        foodArrayList = new ArrayList<Food>();
    }


    public void onClick(View v) {
        switch(v.getId()) {
            case R.id.btnSelect:
                SQLiteDatabase db = myDbHelper.getReadableDatabase();   //helper가 준비되었기 때문에 DB 얻어옴
                String[] columns = new String[] {FoodDBHelper.COL_ID, FoodDBHelper.COL_FOOD, FoodDBHelper.COL_NATION};
                String selection = null;   //조건 없음
                String[] selectArgs = null;

                Cursor cursor = db.query(FoodDBHelper.TABLE_NAME, columns, selection, selectArgs, null, null, null, null);
                //db의 모든 레코드를 반환


                while (cursor.moveToNext()) {
                    //long id = cursor.getLong(0);
                    long _id = cursor.getLong(cursor.getColumnIndex(FoodDBHelper.COL_ID));
                    String foodName = cursor.getString(cursor.getColumnIndex(FoodDBHelper.COL_FOOD));
                    String nation = cursor.getString(cursor.getColumnIndex(FoodDBHelper.COL_NATION));
                    //String foodName = cursor.getString(1);   //두번째 컬럼
                    //String nation = cursor.getString(2);

                    //food 객체 생성
                    Food addFood = new Food(_id, foodName, nation);
                    foodArrayList.add(addFood);
                }
                cursor.close();
                myDbHelper.close();

                String result = "";
                for (Food newFood : foodArrayList) {
                    result += newFood.toString() + "\n";
                    System.out.println(result);
                }

                tvDisplay.setText(result);
                break;
            case R.id.btnAdd:
                db = myDbHelper.getWritableDatabase();
                ContentValues row = new ContentValues();
                row.put(FoodDBHelper.COL_FOOD, "갈비탕");
                row.put(FoodDBHelper.COL_NATION, "한국");
                db.insert(FoodDBHelper.TABLE_NAME, null,row);
            case R.id.btnUpdate:

                break;
            case R.id.btnRemove:

                break;
        }

        myDbHelper.close();
    }

}
