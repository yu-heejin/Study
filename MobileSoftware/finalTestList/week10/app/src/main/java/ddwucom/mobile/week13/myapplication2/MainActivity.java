package ddwucom.mobile.week13.myapplication2;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private ArrayList<MyData> myDataArrayList;
    private MyAdapter myAdapter;
    private ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        /*
            custom view를 만들고 listview를 생성할 때 가로와 세로를 match_parent 안 해주면 높이가 이상하게 설정된다.
         */

        myDataArrayList = new ArrayList<MyData>();

        myDataArrayList.add(new MyData(1, "홍길동", "012345"));
        myDataArrayList.add(new MyData(2, "전우치", "123456"));
        myDataArrayList.add(new MyData(3, "일지매", "234567"));

        myAdapter = new MyAdapter(this, R.layout.custom_adapter_view, myDataArrayList);
        //parameter : 현재 context / custom list view / data List
        //이전 차시에서 simple_list_item_1을 사용하던 것을 custom_adapter_view로 바꿔준 것

        listView = (ListView) findViewById(R.id.customListView);

        listView.setAdapter(myAdapter);

        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {
                Toast.makeText(MainActivity.this, myDataArrayList.get(i).getName(), Toast.LENGTH_SHORT).show();
                return true;
            }
        });
    }
}