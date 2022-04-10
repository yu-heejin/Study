package ddwucom.moblie.week06.practice01;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import org.w3c.dom.Text;

public class MainActivity extends AppCompatActivity {
    //Button button;    //멤버변수로 선언
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //1. 별도의 리스터 인터페이스 구현 클래스 작성 방법(2.1)
//        final Button button = findViewById(R.id.btnDisplay);
//        MyView myView = new MyView();
//        button.setOnClickListener(myView);

        //2. 익명 내부 클래스 구현으로 작성(2.4)
//        final Button button  = findViewById(R.id.btnDisplay);
//        button.setOnClickListener(onClickListener);

        //3. 익명 내부 클래스의 임시 객체 구현 방법(2.5)
//        final Button button = findViewById(R.id.btnDisplay);
//        button.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                EditText editText = findViewById(R.id.editText);
//                TextView textView = findViewById(R.id.tvDisplay);
//                String text = editText.getText().toString();
//
//                textView.setText(text);
//            }
//        });
    }

//    class MyView implements View.OnClickListener {
//
//        @Override
//        public void onClick(View view) {
//            EditText editText = findViewById(R.id.editText);
//            TextView textView = findViewById(R.id.tvDisplay);
//            String text = editText.getText().toString();
//
//            textView.setText(text);
//        }
//    }

//    View.OnClickListener onClickListener = new View.OnClickListener() {    //익명 내부 클래스
//
//        @Override
//        public void onClick(View view) {
//            EditText editText = findViewById(R.id.editText);
//            TextView textView = findViewById(R.id.tvDisplay);
//            String text = editText.getText().toString();
//
//            textView.setText(text);
//        }
//    };
}