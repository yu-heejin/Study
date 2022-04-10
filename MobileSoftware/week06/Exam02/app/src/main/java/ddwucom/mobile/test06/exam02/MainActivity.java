package ddwucom.mobile.test06.exam02;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    MyView myView;
    float x, y, bX, bY;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        myView = (MyView) findViewById(R.id.myView);

        //1, MyView 클래스에 onTouchEvent 메소드를 재정의하여 터치한 위치에 원 표시
//        myView.setOnTouchListener(myView);

        //2. MainActivity에 익명 내부 클래스의 임시 객체 구현
        myView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                bX = myView.getPosX();
                bY = myView.getPosY();
                x = event.getX();
                y = event.getY();

                myView.setPosX(x);
                myView.setPosY(y);

                myView.invalidate();
                return false;
            }
        });

        //3. 롱클릭할 경우 원의 색상이 바뀌는 기능을 익명 내부 클래스의 임시 객체 구현으로 작성
        myView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                System.out.println("before : " + bX + ", " + bY);
                System.out.println("after : " + x + ", " + y);
                if(x != bX || y != bY) {
                    if(myView.getPaintColor() == Color.CYAN) {
                        myView.setPaintColor(Color.RED);
                    } else {
                        myView.setPaintColor(Color.CYAN);
                    }
                }
                return false;
            }
        });
    }
}
