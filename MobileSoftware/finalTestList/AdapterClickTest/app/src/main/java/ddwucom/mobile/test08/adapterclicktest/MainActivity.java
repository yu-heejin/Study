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

import java.lang.reflect.Array;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    SubjectManager subjectManager;
    ArrayList<String> subjectList;
    ArrayAdapter<String> adapter;
    ListView listView;
    EditText editText;
    String val;
    int position;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        subjectManager = new SubjectManager();
        subjectList = subjectManager.getSubjectList();
        editText = findViewById(R.id.etItem);

        adapter = new ArrayAdapter<String>(
                this, android.R.layout.simple_list_item_1, subjectList
        );

        listView = findViewById(R.id.listView);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(onItemClickListener);   //항목 클릭 시 해당 항목을 표시
        listView.setOnItemLongClickListener(onItemLongClickListener);
    }

    AdapterView.OnItemClickListener onItemClickListener = new AdapterView.OnItemClickListener() {
        @Override
        //항목 클릭 시 해당 항목을 EditText에 표시
        public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
            //String val = editText.getText().toString();   -> 현재 editText는 빈 값을 가짐
            editText.setText(subjectManager.getSubject(i));
            position = i;
        }
    };

    //롱클릭시 해당 항목 삭제
    AdapterView.OnItemLongClickListener onItemLongClickListener = new AdapterView.OnItemLongClickListener() {
        @Override
        public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {
            subjectManager.removeData(i);
            position = i;
            adapter.notifyDataSetChanged();
            return false;
        }
    };

    public void onClick(View view) {
        //문자열 입력 후 추가 버튼을 눌러 항목 추가
        val = editText.getText().toString();
        switch(view.getId()) {
            case R.id.btnInsert:
                subjectManager.addData(val);
                break;
                
            //항목을 수정한 후 수정 버튼을 누를 경우 리스트 뷰 항목 변경    
            case R.id.btnUpdate:
                if(!val.equals(subjectManager.getSubject(position))) {
                    val = editText.getText().toString();
                    subjectManager.editData(position, val);
                } else {
                    Toast.makeText(MainActivity.this, "수정 사항이 없습니다.", Toast.LENGTH_SHORT).show();
                }
                break;
        }

        adapter.notifyDataSetChanged();
    }


}
