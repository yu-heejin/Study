package ddwucom.moblie.week09.listviewtest;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.sql.Array;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    DataManager dataManager;
    ArrayAdapter<String> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //1. 배열 형태의 원본 데이터 준비(String)
        //ArrayList: Collection객체, 동적으로 사이즈 생성
//        ArrayList<String> subjectList = new ArrayList<String>();
//        subjectList.add("모바일소프트웨어");
//        subjectList.add("네트워크");
//        subjectList.add("시스템프로그래밍");
//        subjectList.add("웹서비스");
//        subjectList.add("시스템/네트워크보안");
        //데이터가 많으면 자동으로 스크롤 표시됨

        //데이터 관리를 하는 클래스를 따로 만들어 구현(Activity 역할에 충실)
        dataManager = new DataManager();
        ArrayList<String> subjectList = dataManager.getSubjectList();

        //2. 어뎁터 생성
        //ArrayAdapter<String> adapter;

        //3. 매개 변수에는 원본 데이터와 레이아웃 정보 필수
        //this : 액티비티 정보
        //미리 만들어진 레이아웃(뷰) 사용 : android.R.layout.simple_list_item_1 -> 칸마다 텍스트만 표시
        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, subjectList);

        //4. listView 아이디를 통해 객체 가져옴
        ListView listView = findViewById(R.id.listview);

        //5. 어뎁터와 리스트뷰 연결
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(itemCLickListener);    //이벤트 리스너 연결
    }
    
    AdapterView.OnItemClickListener itemCLickListener =    //이벤트 리스너 객체 생성, 아이템 클릭 시 동작함
            new AdapterView.OnItemClickListener() {
                public void onItemClick(AdapterView<?> adapterView, View view, int pos, long l) {
                    //Toast.makeText(MainActivity.this, dataManager.getSubject(pos), Toast.LENGTH_SHORT).show();
                    //해당 항목의 원본 데이터를 받아서 띄워줌

                    //클릭 시 데이터 삭제
                    dataManager.removeData(pos);
                    //화면을 다시 그려줌. 이 코드 없으면 화면에는 반영이 안되기 때문에 오류가 날 가능성 O (갱신 필수)
                    adapter.notifyDataSetChanged();
                }
            };
}

/*
어뎁터 데이터는 화면뿐만 아니라 원본데이터도 있기 때문에
삭제할 땐 화면 뿐만 아니라 원본 데이터도 같이 삭제해야함
 */