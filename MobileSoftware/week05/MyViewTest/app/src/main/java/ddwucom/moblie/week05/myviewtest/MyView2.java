package ddwucom.moblie.week05.myviewtest;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import androidx.annotation.Nullable;

public class MyView2 extends View {  //xml에 추가하고 싶다면 모든 생성자를 다 만들어야함
    int color;

    public int getColor() {
        return color;
    }

    public void setColor(int color) {
        this.color = color;
    }

    public MyView2(Context context) {
        super(context);
        setColor(Color.BLUE);
    }

    public MyView2(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        setColor(Color.BLUE);
    }

    public MyView2(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        setColor(Color.BLUE);
    }

    public MyView2(Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        setColor(Color.BLUE);
    }

    protected void onDraw(Canvas canvas) {
        //  super.onDraw(canvas);
        canvas.drawColor(Color.LTGRAY);
        Paint pnt = new Paint();
        //pnt.setColor(Color.RED);
        pnt.setColor(color);
        canvas.drawCircle(100, 100, 80, pnt);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {

        return super.onTouchEvent(event);
    }
}
