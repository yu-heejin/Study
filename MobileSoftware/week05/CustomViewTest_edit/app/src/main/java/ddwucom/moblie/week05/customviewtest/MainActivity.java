package ddwucom.moblie.week05.customviewtest;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Bundle;
import android.view.View;

import java.util.Random;

public class MainActivity extends AppCompatActivity {
    int count = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void onClick(View v) {
        MyView myView = findViewById(R.id.myview);
        Random random = new Random();

        int x = random.nextInt(500);   //범위 0~499
        int y = random.nextInt(800);   //범위 0~799
        int r = (random.nextInt(3) + 1) * 100;   //범위 100 ~ 300

        if(count == 0) {
            myView.setX(100);
            myView.setY(100);
            myView.setR(80);
            count = 1;
        } else {
            myView.setX(x);
            myView.setY(y);
            myView.setR(r);
        }

        myView.invalidate();
    }
}