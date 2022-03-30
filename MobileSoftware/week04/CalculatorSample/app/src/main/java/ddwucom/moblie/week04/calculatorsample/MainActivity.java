package ddwucom.moblie.week04.calculatorsample;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {
    int num1 = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void onClick(View v) {
        EditText editText = findViewById(R.id.etDisplay);
        String num = editText.getText().toString();

        switch(v.getId()) {
            case R.id.btn_1:
                num += "1";
                editText.setText(num);
                break;

            case R.id.btn_2:
                num += "2";
                editText.setText(num);
                break;

            case R.id.btn_plus:
                num1 += Integer.parseInt(num);
                editText.setText("");
                break;

            case R.id.btn_equal:
                num1 += Integer.parseInt(num);
                num = Integer.toString(num1);
                num1 = 0;
                editText.setText(num);
        }
    }
}