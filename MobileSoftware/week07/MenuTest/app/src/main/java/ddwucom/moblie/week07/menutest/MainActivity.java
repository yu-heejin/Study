package ddwucom.moblie.week07.menutest;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);   //메뉴 인플레이터를 얻어옴(파일명 id, menu : 위에 매개변수랑 이름 똑같아야함)
        //설계도에 따라 메뉴 구성
        return true;   //더이상 메뉴 만들지 않음
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        //item.getItemId() == R.id.item01    if나 switch로 구분(항목 추가시 id)
        //case R.id.subItem01:
//        switch (item.getItemId()) {
//            case R.id.item01:
//                Toast.makeText(this, "첫번째 항목!", Toast.LENGTH_SHORT).show();
//        }

        return true;
    }

    public void onMenuClick(MenuItem item) {    //view 대신 메뉴아이템이 들어옴
        //onOptionsItemSelected보다 우선순위가 높기 때문에 두 메소드 중 이 메소드가 먼저 실행됨
        switch (item.getItemId()) {
            case R.id.item_1:
                Toast.makeText(this, "하위 항목!", Toast.LENGTH_SHORT).show();
        }
    }
}