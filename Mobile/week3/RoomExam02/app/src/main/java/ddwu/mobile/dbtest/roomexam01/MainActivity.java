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

import io.reactivex.Flowable;
import io.reactivex.Single;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class MainActivity extends AppCompatActivity {

    final static String TAG = "MainActivity";

    EditText etFood;
    EditText etNation;
    ListView listView;

    ArrayAdapter<Phone> adapter;

    FoodDB db;
    FoodDao dao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etFood = findViewById(R.id.etFood);
        etNation = findViewById(R.id.etNation);
        listView = findViewById(R.id.listView);
        adapter = new ArrayAdapter<Food>(this, android.R.layout.simple_list_item_1, new ArrayList<Food>());

        //db = Room.databaseBuilder(getApplicationContext(), FoodDB.class, "food_db.db").build();
        db = FoodDB.getDatabase(this);
        dao = db.foodDao();
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
//                    }
//                }).start();
//                long rowId = dao.insertFood(new Food(food, nation));
//                Log.i(TAG, "Insert: " + rowId);
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        Single<Long> insertResult = dao.insertFood(new Food(food, nation));
                        insertResult.subscribeOn(Schedulers.io())
                                .observeOn(AndroidSchedulers.mainThread())
                                .subscribe(result -> Log.d(TAG, "Insert success!: " + result),   //결과로 전달 받는 매개변수는 result
                                        throwable -> Log.d(TAG, "err"));
                    }
                });
                break;
            case R.id.btnUpdate:
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        dao.updateFood(new Food(food, nation));
                        Log.i(TAG, "Update: ");
                    }
                }).start();

                break;
            case R.id.btnDelete:
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        dao.deleteFood(new Food(food, nation));
                        Log.i(TAG, "Delete: ");
                    }
                }).start();

                break;
            case R.id.btnShow:
//                new Thread(new Runnable() {
//                    @Override
//                    public void run() {
//                        List<Food> foods = dao.getAllFoods();
//                        for(Food food : foods) {
//                            Log.i(TAG, "Food is " + food.food);
//                        }
//                    }
//                }).start();

                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        Flowable<List<Food>> resultFoods = dao.getAllFoods();

                        resultFoods.subscribeOn(Schedulers.io())
                                .observeOn(AndroidSchedulers.mainThread())
                                .subscribe(foods -> {
                                    for(Food aFood : foods) {
                                        Log.d(TAG, aFood.toString());
                                    }
                                },
                                        throwable -> Log.d(TAG, "error", throwable));
                    }
                }).start();
                break;
        }
    }
}