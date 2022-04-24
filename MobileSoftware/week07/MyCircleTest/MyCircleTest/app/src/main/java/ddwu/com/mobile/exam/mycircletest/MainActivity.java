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

        //onCreateContextMenu는 메뉴가 생성될 때마다 호출되기 때문에 사전에 체크 표시를 해두는 것이 좋다.
        //그래서 수업중에 배열에 체크 상태를 담아 놓는 것도 이때문인듯
        int color = myCircle.getColor();
        switch(color)
        {
            case Color.RED:
                menu.findItem(R.id.radio01).setChecked(true);
                break;
            case Color.BLUE:
                menu.findItem(R.id.radio03).setChecked(true);
                break;
            case Color.GREEN:
                menu.findItem(R.id.radio02).setChecked(true);
                break;
        }
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
        item.setChecked(true);
        myCircle.invalidate();
        return true;
    }

}
