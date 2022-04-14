package ddwucom.moblie.week07.menutest;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    boolean[] checkedItem;    //boolean 배열 생성(check상태 기록)

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //등록하는 작업은 시작 시 미리 등록이 진행돼야함 -> onCreate에서 진행(Context에서는 등록하는 것이 필요하다)
        TextView textView = findViewById(R.id.textView);
        //Activity가 가진 메소드에 등록시킴
        registerForContextMenu(textView);

        checkedItem = new boolean[2];
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        //super.onCreateContextMenu(menu, v, menuInfo);    부모 메소드는 호출할 필요 없음

        //여러개의 뷰가 있는 경우 이렇게 구분시켜주는 것이 필요함
        switch (v.getId()) {
            case R.id.textView:
                getMenuInflater().inflate(R.menu.menu_main, menu);
                break;
                
        }
        //실제 xml 메뉴를 연결시켜야함(현재 menu가 비어있음)
        //getMenuInflater().inflate(R.menu.menu_main, menu);
        //롱탭 시 메뉴의 위치는 기본적으로 뷰의 가운데쯤 뜨게 됨(버전에 따라 다르다)
    }

    @Override
    public boolean onContextItemSelected(@NonNull MenuItem item) {   //항목 선택 시 실행되는 것 구현
        //return super.onContextItemSelected(item);   return 값은 여기서 처리가 끝나기 때문에 true로 설정(더 이상 처리할 필요 없음)
        switch(item.getItemId()) {
            case R.id.Item01:
                Toast.makeText(this, "Context!!", Toast.LENGTH_SHORT).show();
                break;
        }
        return true;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {    //옵션 메뉴 구성시 필요
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

    //onclick속성을 이용하여 변경
    public void onMenuClick(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.radio01:
                item.setChecked(true);
                break;

            case R.id.radio02:
                item.setChecked(true);   //현재 아이템이 선택되었음
                break;
        }
    }
    //라디오 버튼의 경우 하나만 선택할 수 있기 때문에 몇번째 버튼이 선택되었는지만 기억하면 됨
    //int 배열 변수를 사용하여 기록

    public void onCheckClick(MenuItem item) {
        switch(item.getItemId()) {
            case R.id.check01:
                //체크가 되어있으면 한번 더 눌렀을 때 체크를 풀어야함
                //체크가 안 되어있으면 한번 더 눌렀을 때 체크를 해줘야함 (라디오 버튼과 달리 여러 개의 항목을 선택할 수 있기 때문!)
                //즉 현재 상태를 우선 알아야함
                if(item.isChecked()) {
                    //item.setChecked(false);
                    checkedItem[0] = false;     //배열에 현재 상태를 기록해놓음(곧 해제할거니까 미리 기록해놓는 개념)
                    item.setChecked(false);   //체크가 되어있다면 체크를 해제해야함
                } else {
                    //item.setChecked(true);
                    checkedItem[0] = true;
                    item.setChecked(true);
                }
                //사실 이렇게 해줄 필요는 없다. 화면에서 알아서 해줌
                //중요한 것은 체크 상태를 확인하는 것
                //아이템이 체크가 되었는지 아닌지 기록을 하고 있음
                //만약 체크한 상태에서 어떤 동작을 하면 동작이 있어야함 -> 이를 위해 이것이 체크되어있는지 아닌지 어딘가에 기억해놨다가 사용해야할 필요가 있음
                //매번 isChecked() 하기 번거로우니 boolean 배열에 저장해놓고 시작함
                break;

            case R.id.check02:
                if(item.isChecked()) {
                    //item.setChecked(false);
                    checkedItem[1] = false;
                    item.setChecked(false);
                } else {
                    //item.setChecked(true);
                    checkedItem[1] = true;
                    item.setChecked(true);
                }
                break;
        }
    }
}