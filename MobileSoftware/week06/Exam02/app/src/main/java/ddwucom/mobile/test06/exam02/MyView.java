package ddwucom.mobile.test06.exam02;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import androidx.annotation.Nullable;

public class MyView extends View /*implements View.OnTouchListener*/ {

    float posX = 100;
    float posY = 100;
    float r = 100;
    int paintColor = Color.CYAN;

    public MyView(Context context) {
        super(context);
    }

    public MyView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public MyView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public void setPosX(float posX) {
        this.posX = posX;
    }

    public void setPosY(float posY) {
        this.posY = posY;
    }

    public void setPaintColor(int paintColor) {
        this.paintColor = paintColor;
    }

    public float getPosX() {
        return posX;
    }

    public float getPosY() {
        return posY;
    }

    public int getPaintColor() {
        return paintColor;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        canvas.drawColor(Color.LTGRAY);

        Paint paint = new Paint();
        paint.setColor(paintColor);

        canvas.drawCircle(posX, posY, r, paint);
    }

//    @Override
//    public boolean onTouch(View v, MotionEvent event) {
//        this.setPosX(event.getX());
//        this.setPosY(event.getY());
//
//        this.invalidate();
//        return false;
//    }
}
