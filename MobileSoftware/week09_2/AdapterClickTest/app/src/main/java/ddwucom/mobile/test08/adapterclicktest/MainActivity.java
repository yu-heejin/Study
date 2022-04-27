package ddwucom.mobile.test08.adapterclicktest;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.CheckedTextView;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    SubjectManager subjectManager;
    ArrayList<String> subjectList;
    ArrayAdapter<String> adapter;
    ListView listView;
    EditText editText;
    String item;
    int pos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        subjectManager = new SubjectManager();
        subjectList = subjectManager.getSubjectList();  //해당 객체를 가리키는 참조변수임. 원본 배열 아님

        adapter = new ArrayAdapter<String>(
                this, android.R.layout.simple_list_item_1, subjectList
        );

        listView = findViewById(R.id.listView);
        listView.setAdapter(adapter);
        editText = findViewById(R.id.etItem);

        listView.setOnItemClickListener(itemClickListener);
    }

    public void onClick(View v) {
        item = editText.getText().toString();

        switch (v.getId()) {
            case R.id.btnInsert:
                //2. EditText에 문자열 입력 후 추가 버튼을 눌러 항목 추가
                subjectList.add(item);
                adapter.notifyDataSetChanged();
                break;

            case R.id.btnUpdate:
                //3. EditText의 항목을 수정함 (if 조건문 잘 세우기)
                subjectManager.changeData(pos, item);
                adapter.notifyDataSetChanged();
                break;
        }
    }

    AdapterView.OnItemClickListener itemClickListener =
            new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    //1. 항목 클릭 시 해당 항목을 표시함
                    editText.setText(subjectManager.getSubject(position));
                    pos = position;
                }
            };


}
