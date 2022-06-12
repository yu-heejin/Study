package ddwucom.mobile.week13.myapplication3;

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

        myDataArrayList = new ArrayList<MyData>();

        myDataArrayList.add(new MyData(1, "하월곡동", "서울시 성북구", "좋음"));
        myDataArrayList.add(new MyData(2, "은행2동", "성남시 중원구", "맑음"));

        myAdapter = new MyAdapter(this, R.layout.custom_view, myDataArrayList);

        listView = (ListView) findViewById(R.id.list);

        listView.setAdapter(myAdapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                String msg = myDataArrayList.get(i).getDetail() + " " + myDataArrayList.get(i).getTitle();
                Toast.makeText(MainActivity.this, msg + "의 날씨는 " + myDataArrayList.get(i).getState(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}