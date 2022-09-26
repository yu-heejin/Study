package ddwu.mobile.dbtest.roomexam01;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Single;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class MainActivity extends AppCompatActivity {

    final static String TAG = "MainActivity";

    EditText etFood;
    EditText etNation;
    ListView listView;

//    ArrayAdapter<Food> adapter;

    FoodDB db;
    FoodDAO dao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etFood = findViewById(R.id.etFood);
        etNation = findViewById(R.id.etNation);
        listView = findViewById(R.id.listView);
        adapter = new ArrayAdapter<Food>(this, android.R.layout.simple_list_item_1, new ArrayList<Food>());
        listView.setAdapter(adapter);

        db = Room.databaseBuilder(this, FoodDB.class, "food_db.db").build();   //DB 생성하기
        dao = db.foodDAO();
    }


    public void onClick(View v) {

        final String food = etFood.getText().toString();
        final String nation = etNation.getText().toString();

        switch (v.getId()) {
            case R.id.btnInsert:
//                new Thread(new Runnable() {
//                    @Override
//                    public void run() {
//                        long rowId = dao.insertFood(new Food(food, nation));
//                        Log.i(TAG, "Insert: " + rowId);
//                    }   //멀티쓰레드 사용하기, 화면에서 동작하지 않음
//                });    -> 비동기 처리로 인해 사용하지않음
//                long rowId = dao.insertFood(new Food(food, nation));
//                Log.i(TAG, "Insert: " + rowId);

                Single<Long> insertResult = dao.insertFood(new Food(food, nation));
                insertResult.subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(result -> Log.d(TAG, "Insert success!: " + result),
                                throwable -> Log.d(TAG, "err"));

                break;
            case R.id.btnUpdate:

                break;
            case R.id.btnDelete:

                break;
            case R.id.btnShow:
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        List<Food> foodList = dao.getAllFoods();
                        for(Food food2 : foodList) {
                            Log.i(TAG, "food is " + food2.food);
                        }
                    }
                });
                break;
        }
    }
}