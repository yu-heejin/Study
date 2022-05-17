package ddwucom.mobile.week11.exam01;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    ListView listView;
    ArrayAdapter adapter;
    ArrayList<Food> foodList;
    FoodManager foodManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        foodManager = new FoodManager();
        listView = findViewById(R.id.listView);
        foodList = foodManager.getFoodList();

        // DataManager 적용해 볼 것
//        foodList = new ArrayList<Food>();
//        foodList.add(new Food("김치찌개", "한국"));
//        foodList.add(new Food("된장찌개", "한국"));
//        foodList.add(new Food("훠궈", "중국"));
//        foodList.add(new Food("딤섬", "중국"));
//        foodList.add(new Food("초밥", "일본"));
//        foodList.add(new Food("오코노미야키", "일본"));

        // Food 객체의 toString() 메소드가 호출되어 하나의 문자열로 처리됨
        adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, foodList);

        listView.setAdapter(adapter);
        listView.setOnItemLongClickListener(itemLongClickListener);
        // listView 롱클릭 이벤트 추가
    }

    AdapterView.OnItemLongClickListener itemLongClickListener =
            new AdapterView.OnItemLongClickListener() {
                @Override
                public boolean onItemLongClick(AdapterView<?> parent, View view, final int pos, long id) {
                    AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                    builder.setTitle("음식 삭제")
                            .setMessage(foodManager.getFood(pos).getFood() + "을(를) 삭제하시겠습니까?")
                            .setIcon(R.mipmap.ic_launcher)
                            .setPositiveButton("삭제", new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialogInterface, int whichButton) {
                                    foodManager.removeData(pos);
                                    adapter.notifyDataSetChanged();
                                }
                            })
                            .setNegativeButton("취소", null)
                            .show();
                    return false;
                }
            };


    public void onClick(View v) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        final ConstraintLayout foodLayout = (ConstraintLayout)View.inflate(this, R.layout.add_food, null);
        switch (v.getId()) {
            case R.id.btn1:
                builder.setTitle("음식 추가")
                        .setView(foodLayout)
                        .setPositiveButton("추가", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int i) {
                                EditText foodName = foodLayout.findViewById(R.id.foodName);
                                EditText nationName = foodLayout.findViewById(R.id.nationName);
                                String fName = foodName.getText().toString();
                                String nName = nationName.getText().toString();
                                foodManager.addData(new Food(fName, nName));
                                adapter.notifyDataSetChanged();
                            }
                        })
                        .setNegativeButton("취소", null)
                        .show();

        }
       // Toast.makeText(this, "기능 구현 후 토스트는 삭제", Toast.LENGTH_SHORT).show();
    }


}
