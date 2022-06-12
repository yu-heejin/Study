package ddwu.com.week10.exam.customadaptertest;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private ArrayList<MyData> myDataList;
    private MyAdapter myAdapter;
    private ListView listView;

    private MyDataManager myDataManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        myDataList = new ArrayList<MyData>();
        myDataList.add ( new MyData(1, "홍길동", "012345") );
        myDataList.add ( new MyData(2, "전우치", "123456") );
        myDataList.add ( new MyData(3, "일지매", "234567") );

//        DataManager 를 활용할 경우 위의 코드를 대체
//        myDataManager = new MyDataManager();
//        myDataList = myDataManager.getMyDataList();

        myAdapter = new MyAdapter(this, R.layout.custom_adapter_view, myDataList);

        listView = findViewById(R.id.listView);
        listView.setAdapter(myAdapter);


        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {
                Toast.makeText(MainActivity.this, myDataList.get(i).getPhone() + " 삭제", Toast.LENGTH_SHORT).show();
                myDataList.remove(i);
//                myDataManager.removeData(i);      // dataManager 를 사용할 경우 위의 코드 대체
                myAdapter.notifyDataSetChanged();
                return true;
            }
        });

    }
}