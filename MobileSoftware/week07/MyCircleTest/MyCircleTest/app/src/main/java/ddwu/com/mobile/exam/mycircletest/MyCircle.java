package ddwu.com.mobile.exam.mycircletest;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.Nullable;

public class MyCircle extends View {

    private Paint myPaint;
    private int x;     //가로 위치
    private int y;     //세로 위치
    private int r = 100;    //원의 크기는 반지름에 의해 결정
    private int color = Color.RED;

    public MyCircle(Context context) {
        super(context);
        myPaint = new Paint();
    }

    public MyCircle(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        myPaint = new Paint();
    }

    public MyCircle(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        myPaint = new Paint();
    }

    public void setR(int r) {
        this.r = r;
    }

    public int getR() {
        return r;
    }

    public void setColor(int color) {
        this.color = color;
    }

    public int getColor() {
        return color;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        myPaint.setColor(color);

        x = getWidth() / 2;
        y = getHeight() / 2;
        //가운데 정렬

        canvas.drawCircle(x, y, r, myPaint);
    }
}
