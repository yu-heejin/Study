package ddwucom.moblie.week04.layouttest;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.android.material.textfield.TextInputEditText;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
     //   setContentView(R.layout.activity_main);   -> 주석처리로 xml사용안됨
        //내부에서 자바객체를 만들어줘야함 (설계도 + 객체 + 화면에 보여줌) 

        LinearLayout linear = new LinearLayout(this);   //레이아웃을 직접 생성, this: mainactivity객체를 넣어둠
        linear.setOrientation(LinearLayout.VERTICAL);   //방향설정
        linear.setBackgroundColor(Color.LTGRAY);   //배경색 설정
        //LinearLayout객체 생성

        TextView text = new TextView(this);
        text.setText("TextView");
        text.setGravity(Gravity.CENTER);
        text.setTextColor(Color.RED);
        text.setTextSize(20);
        //textView 객체 생성

        linear.addView(text);   //Linear안에 text를 넣어줌
        setContentView(linear);  //메모리 상에 존재하는 객체를 넣어서 내용물을 화면에 출력
        //이미 자바객체로 생성되어 있기 때문에 만들어줄 필요 없음(화면에 보여줌)

        //setContentView는 이름은 같지만 기능이 다른 overriding
    }

    public void onClick(View v) {
        //버튼 클릭 시 레이아웃 세로 -> 가로
        LinearLayout layout = findViewById(R.id.layout);
       // layout.setOrientation(LinearLayout.HORIZONTAL);
        switch (layout.getOrientation()) {
            case LinearLayout.HORIZONTAL:
                layout.setOrientation(LinearLayout.VERTICAL);
                break;

            case LinearLayout.VERTICAL:
                layout.setOrientation(LinearLayout.HORIZONTAL);

        }
    }
}