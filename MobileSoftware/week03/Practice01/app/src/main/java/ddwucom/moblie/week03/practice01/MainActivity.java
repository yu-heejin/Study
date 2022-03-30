package ddwucom.moblie.week03.practice01;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void onClick(View v) {
        EditText etName = findViewById(R.id.etName);
        String name = etName.getText().toString();
        EditText etPhone = findViewById(R.id.etPhone);
        String phone = etPhone.getText().toString();

        switch(v.getId()) {
            case R.id.btnHello:
                Toast.makeText(this, "안녕하세요, 저는 " + name + "입니다.\n" +
                        "전화번호는 " + phone + "입니다.", Toast.LENGTH_SHORT).show();
                break;

            case R.id.btnExit:
                finish();
        }

    }
}