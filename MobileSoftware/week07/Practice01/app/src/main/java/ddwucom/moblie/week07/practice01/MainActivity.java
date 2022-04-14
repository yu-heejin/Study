package ddwucom.moblie.week07.practice01;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    //메뉴 아이템 클릭 시 해당 음식 이름을 toast로 출력함
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch(item.getItemId()) {
            case R.id.jajang:
                Toast.makeText(this, "짜장면", Toast.LENGTH_SHORT).show();
                break;

            case R.id.jambong:
                Toast.makeText(this, "짬뽕", Toast.LENGTH_SHORT).show();
                break;

            case R.id.kimchi:
                Toast.makeText(this, "김치찌개", Toast.LENGTH_SHORT).show();
                break;

            case R.id.soon:
                Toast.makeText(this, "순두부찌개", Toast.LENGTH_SHORT).show();
                break;
        }
        return true;
    }
}