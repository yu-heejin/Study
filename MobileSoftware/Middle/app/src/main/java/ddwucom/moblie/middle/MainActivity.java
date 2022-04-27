package ddwucom.moblie.middle;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.PopupMenu;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    int a = 0;
    int num = -1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button button = (Button) findViewById(R.id.hh);
        Button button1 = (Button) findViewById(R.id.ff);

        registerForContextMenu(button);
        registerForContextMenu(button1);
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    public boolean onPrepareOptionsMenu(Menu menu) {  //메뉴 버튼을 누를 때마다 계속 수행
    //    menu.getItem(a).setEnabled(false);
    //    a++;
        if(num == 0) {
            menu.findItem(R.id.tem1).setChecked(true);
        } else {

            menu.findItem(R.id.tem2).setChecked(true);
        }
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        switch(item.getItemId()) {
            case R.id.tem1:
                num = 0;
                break;

            case R.id.tem2:
                num = 1;
                break;
        }
        return true;
    }

//    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
//        //super.onCreateContextMenu(menu, v, menuInfo);    부모 메소드는 호출할 필요 없음

        //여러개의 뷰가 있는 경우 이렇게 구분시켜주는 것이 필요함
//        switch (v.getId()) {
//            case R.id.hh:
 //               getMenuInflater().inflate(R.menu.menu_main, menu);
  //              menu.getItem(a).setEnabled(false);
 //                      a++;
  //              break;

 //           case R.id.ff:
//                getMenuInflater().inflate(R.menu.menu_change, menu);
 //               menu.getItem(a).setEnabled(false);
 //                      a++;
  //              break;

 //       }
        //실제 xml 메뉴를 연결시켜야함(현재 menu가 비어있음)
        //getMenuInflater().inflate(R.menu.menu_main, menu);
        //롱탭 시 메뉴의 위치는 기본적으로 뷰의 가운데쯤 뜨게 됨(버전에 따라 다르다)
 //   }
}