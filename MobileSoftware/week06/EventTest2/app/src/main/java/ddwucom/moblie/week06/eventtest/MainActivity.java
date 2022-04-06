package ddwucom.moblie.week06.eventtest;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

public class MainActivity extends AppCompatActivity /*implements View.OnLongClickListener*/ {
    //TextView textView;  -> 이렇게 선언하는 것이 맞음(지역변수는 메소드 끝나면 사라짐)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//        TextView textView = findViewById(R.id.textView);   //MyTouch와 연결해야함 -> 해당 클래스의 객체
//        MyTouch myTouch = new MyTouch();
//
//        textView.setOnTouchListener(myTouch);
        
        //onClickListner는 추상적이기 때문에 직접 객체 생성불가능

//        TextView textView = findViewById(R.id.textView);
//        MyLongTouch myLongTouch = new MyLongTouch();

//        TextView textView = findViewById(R.id.textView);
//        textView.setOnLongClickListener(this);   //바람직하지 않은 방법
        //activity의 역할은 View 관리만 할 뿐 동작까지 처리하면 안됨, 액티비티 매우 길어질 수 있음

//        TextView textView = findViewById(R.id.textView);
//        textView.setOnLongClickListener(longClickListner);   //클래스가 없지만 내부적으로 클래스로 처리됨
        //인터페이스에서 바로 객체를 생성할 수 있도록 도움

//        TextView textView = findViewById(R.id.textView);
//        textView.setOnLongClickListener(new View.OnLongClickListener() {
//            @Override
//            public boolean onLongClick(View view) {
//                Toast.makeText(MainActivity.this, "Long Touch!!!", Toast.LENGTH_SHORT).show();
//                //코드가 길어지면 분리시키는 것이 더 나음
//                //다른 곳에 사용되지 못하고 이 안에서만 사용가능, 여러군데에서 쓰려면 변수에 저장하는 방법이 더 나음
//                return true;   //이 메소드는 long click 발생 시 실행됨
//                //return false : long click 아직 처리할 것이 남았음을 알려줌
//            }
//        });

        TextView textView = findViewById(R.id.textView);
        textView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                textView.setText("long click!!!");
                return true;
            }
        });
    }
    
    class MyTouch implements View.OnTouchListener {

        @Override
        public boolean onTouch(View view, MotionEvent motionEvent) {
            motionEvent.getX();    //x좌표 얻어옴
            motionEvent.getY();    //y좌표 얻어옴
           // motionEvent.getAction() == MotionEvent.ACTION_DOWN;   -> if나 switch문 이용
            return false;
        }
    }

//    @Override
//    public boolean onLongClick(View view) {
//        Toast.makeText(MainActivity.this, "Long Touch!!!", Toast.LENGTH_SHORT).show();
//        return false;
//    }


//    class MyTouch implements View.OnTouchListener {  //해당 리스너를 상속받는 클래스
//        @Override
//        public boolean onTouch(View view, MotionEvent motionEvent) {
//            Toast.makeText(MainActivity.this, "Touch!!", Toast.LENGTH_SHORT).show();   //this : MyTouch
//            //this는 MainActivity가 돼야함
//            return false;
//        }
//    }

//    class MyLongTouch implements View.OnLongClickListener {
//
//        @Override
//        public boolean onLongClick(View view) {
//            Toast.makeText(MainActivity.this, "Long Touch!!!", Toast.LENGTH_SHORT).show();
//            return false;
//        }
//    }

//  View.OnLongClickListener longClickListner = new View.OnLongClickListener() {   //익명 내부 클래스, 객체 못 만드는 인터페이스의 객체를 만들 수 있음
//        //new 하는 시점에 내용물을 채워주기 때문에 가능하다
//      //객체 보관을 위해 변수에 저장
//      //() -> default 생성자 호출
//        @Override
//        public boolean onLongClick(View view) {
//            Toast.makeText(MainActivity.this, "Long Touch!!!", Toast.LENGTH_SHORT).show();
//            return false;
//        }
//    };
}