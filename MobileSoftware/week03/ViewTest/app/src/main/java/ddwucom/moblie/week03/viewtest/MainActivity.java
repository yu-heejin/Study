package ddwucom.moblie.week03.viewtest;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //레이아웃 값을 가져옴, 화면 생성

//        TextView tx = findViewById(R.id.textView);  //화면에 있는 뷰 요소를 가져옴
//        tx.setText("cooling");  //그 값을 이 값으로 변경해라(set)

        /**
         * xml파일 상에서는 정적으로 지정해놓음
         * 화면에 변화를 주고 싶으면 자바 코드 내에서 실행
         */
    }

    public void onClick(View v) {
        //switch문으로도 변경 가능함
        EditText et = findViewById(R.id.edit);
        String tx = et.getText().toString();   //change variable err
        //문자열로 반환하는 toString으로 읽어옴
        if(v.getId() == R.id.button_ok) {  //getter
            Toast.makeText(this, tx, Toast.LENGTH_SHORT).show();
        } else if(v.getId() == R.id.button_not) {
            Toast.makeText(this, "취소", Toast.LENGTH_SHORT).show();
        }
    }
}