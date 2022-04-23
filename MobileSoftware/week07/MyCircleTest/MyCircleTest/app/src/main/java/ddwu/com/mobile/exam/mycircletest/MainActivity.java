package ddwu.com.mobile.exam.mycircletest;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Bundle;
import android.util.AttributeSet;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private MyCircle myCircle;
    int circleSize;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        /*Custom View 객체*/
        myCircle = findViewById(R.id.myCircle);
        registerForContextMenu(myCircle);
        circleSize = myCircle.getR();
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.memu_main, menu);
        return true;
    }

    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        menu.setHeaderTitle("Change Color");
        getMenuInflater().inflate(R.menu.menu_view, menu);
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        switch(item.getItemId()) {
            case R.id.bigger:
                circleSize += 10;
                break;

            case R.id.smaller:
                circleSize -= 10;
                break;
        }
        myCircle.setR(circleSize);
        myCircle.invalidate();
        return true;
    }

    public boolean onContextItemSelected(MenuItem item) {
        switch(item.getItemId()) {
            case R.id.radio01:
                myCircle.setColor(Color.RED);
                break;

            case R.id.radio02:
                myCircle.setColor(Color.GREEN);
                break;

            case R.id.radio03:
                myCircle.setColor(Color.BLUE);
                break;
        }

        myCircle.invalidate();
        return true;
    }

}
