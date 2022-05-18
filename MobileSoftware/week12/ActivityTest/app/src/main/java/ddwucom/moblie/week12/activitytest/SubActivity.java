package ddwucom.moblie.week12.activitytest;

import android.content.Intent;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class SubActivity extends AppCompatActivity {
    TextView textView;
    //모든 액티비티는 무조건 onCreate를 가져야함

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sub);

        Intent intent = getIntent();   //new가 아니라 get, 운영체제에 있는 값을 받아옴
        String data = intent.getStringExtra("subject");
        int time = intent.getIntExtra("time", 2);
        DataClass dataClass = (DataClass) intent.getSerializableExtra("dataClass");
        textView = findViewById(R.id.textView);
        textView.setText(data);
    }

    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.sub_button_ok:
                Intent resultIntent = new Intent();   //intent 생성(비어있는 인텐트)
                //메인 액티비티는 가려져있음 -> 결과만 돌려줘야함. 메인액티비티 인텐트 생성 X
                resultIntent.putExtra("result_data", "SubActivity returns data");   //빈 인탠트에 결과만 돌려줌
                setResult(RESULT_OK, resultIntent);  //결과를 돌려줄 때 setResult : 결과 잘 만드는 상황 or 취소 상황 -> 상수값으로 구분!
                //빈 인텐트에 값 씌워서 돌려줌 -> 결과로써 메인으로 돌아감
                break;

            case R.id.sub_button_cancel:
                setResult(RESULT_CANCELED);
                break;
        }

        finish();   //액티비티가 사라지는 순간 결과가 메인으로 돌아감 : 어느 버튼을 눌러도 실행
    }
}
