package ddwucom.moblie.middle;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.PopupMenu;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    PopupMenu popup;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void onClick(View v) {
        popup = new PopupMenu(this, v);
        MenuInflater inflater = popup.getMenuInflater();
        Menu menu = popup.getMenu();
        inflater.inflate(R.menu.menu_main, menu);

        popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem menuItem) {
                switch(menuItem.getItemId()) {
                    case R.id.item1:
                        Toast.makeText(MainActivity.this, "안녕", Toast.LENGTH_SHORT).show();
                        break;
                }
                return false;
            }
        });
    }
}