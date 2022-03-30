package ddwucom.moblie.week03.practice02;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void onClick(View v) {
        EditText editText = findViewById(R.id.editText);
        String name = editText.getText().toString();
       // int num = Integer.parseInt(name);

        switch(v.getId()) {
            case R.id.btnOne:
                name += "1";
                editText.setText(name);  //그 값을 이 값으로 변경해라(set)
                break;
            case R.id.btnTwo:
                name += "2";
                editText.setText(name);
                break;
            case R.id.btnThree:
                name += "3";
                editText.setText(name);
                break;
            case R.id.btnClear:
                name = "";
                editText.setText(name);
        }

    }
}