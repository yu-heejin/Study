package ddwucom.moblie.week05.myviewtest;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Bundle;
import android.util.AttributeSet;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //    MyView2 myView2 = new MyView2(this);
        //    setContentView(myView2);

//        MyInnerView vw = new MyInnerView(this);
//        vw.color = Color.RED;
//        vw.invalidate();
//        setContentView(vw);
    }

    public void onClick(View v) {
        MyView2 myView2 = findViewById(R.id.myview);
        myView2.setColor(Color.GREEN);
        //그림이 다시 그려지면 onDraw호출 못하니까 invalidate호출
        myView2.invalidate();
    }

//
//    class MyInnerView extends View {  //View클래스를 상속받은 customView (클래스 안 내부 클래스)
//        public int color;
////        public MyView(Context context) {
////            super(context);
////        }
////
////        public MyView(Context context, @Nullable AttributeSet attrs) {
////            super(context, attrs);
////        }
////
////        public MyView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
////            super(context, attrs, defStyleAttr);
////        }
////
////        public MyView(Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
////            super(context, attrs, defStyleAttr, defStyleRes);
////        }
//
//        public MyInnerView(Context context) {
//            super(context);
//            color = Color.BLUE;
//        }  // -> 기본 생성자(필수), context가 기본으로 들어간 생성자만 생성가능
//
//        @Override
//        protected void onDraw(Canvas canvas) {
//            //  super.onDraw(canvas);
//            canvas.drawColor(Color.LTGRAY);
//            Paint pnt = new Paint();
//            pnt.setColor(color);
//            canvas.drawCircle(100, 100, 80, pnt);
//        }
//
//    }
}