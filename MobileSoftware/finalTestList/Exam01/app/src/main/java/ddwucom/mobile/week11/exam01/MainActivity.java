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

        listView = findViewById(R.id.listView);
        foodManager = new FoodManager();
        foodList = foodManager.getFoodList();


        // Food 객체의 toString() 메소드가 호출되어 하나의 문자열로 처리됨
        adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, foodList);

        listView.setAdapter(adapter);

        // listView 롱클릭 이벤트 추가
        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {
                final int position = i;
                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                builder.setTitle("음식 삭제")
                        .setMessage(foodList.get(i).getFood() + " 을(를) 삭제하시겠습니까?")
                        .setPositiveButton("삭제", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                foodManager.removeData(position);
                                adapter.notifyDataSetChanged();
                            }
                        })
                        .setNegativeButton("취소", null)
                        .setCancelable(true)
                        .show();
                return true;
            }
        });
    }


    public void onClick(View v) {
        //Toast.makeText(this, "기능 구현 후 토스트는 삭제", Toast.LENGTH_SHORT).show();
        switch (v.getId()) {
            case R.id.button:
                final ConstraintLayout addLayout = (ConstraintLayout) View.inflate(MainActivity.this, R.layout.add_food, null);
                final EditText food = addLayout.findViewById(R.id.food);
                final EditText nation = addLayout.findViewById(R.id.nation);

                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                //builder 하나만 선언해놓고 두 이벤트 리스너에서 동시에 쓰면 오류남!
                //이벤트별 따로 설정하는 것이 좋다.
                builder.setTitle("음식 추가")
                        .setView(addLayout)
                        .setPositiveButton("추가", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
//                                EditText food = addLayout.findViewById(R.id.food);
//                                EditText nation = addLayout.findViewById(R.id.nation);
                                //커스텀 대화창 안에 있는 값을 선언해야하기 때문에 addLayout.~ 형태로 선언해야함
                                String f = food.getText().toString();
                                String n = nation.getText().toString();

                                foodManager.addData(new Food(f, n));
                                adapter.notifyDataSetChanged();
                            }
                        })
                        .setNegativeButton("취소", null)
                        .show();
                break;
        }
    }


}
