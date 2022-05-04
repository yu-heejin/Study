package ddwucom.moblie.week10.customadatrtst;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ListView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private ArrayList<MyData> myDataList;
    private MyAdapter myAdapter;
    private ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        myDataList = new ArrayList<MyData>();

        myDataList.add(new MyData(1, "홍길동", "012345"));
        myDataList.add(new MyData(2, "전우치", "123456"));
        myDataList.add(new MyData(3, "일지매", "234567"));

        myAdapter = new MyAdapter(this, R.layout.custom_adapter_view, myDataList);
        listView = (ListView) findViewById(R.id.customListView);
        listView.setAdapter(myAdapter);
    }
    /**
     * customView 만들 때 관계 화살표를 밑으로 길게 잡으면 커스텀 뷰 크기가 길어짐..
     * onItemClickListener : 뷰 전체를 눌렀을 때 이벤트 발생
     * onClick : 뷰 안에 요소를 눌렀을 때 이벤트 발생
     * 각 항목의 요소에도 이벤트 리스너를 연결할 수 있음
     */
}