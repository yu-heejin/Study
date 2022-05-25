package ddwucom.mobile.week12.exam01;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {
    final static int SUB_ACTIVITY_CODE = 100;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void onClick(View v) {
        EditText etValue = findViewById(R.id.etMain);
        String text = etValue.getText().toString();
        switch (v.getId()) {
            case R.id.btnCall:
                Intent intent = new Intent(this, SubActivity.class);
                //단방향 : 값 전달
                //startActivity(intent);
                intent.putExtra("value", text);
                startActivityForResult(intent, SUB_ACTIVITY_CODE);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
            case SUB_ACTIVITY_CODE:
                if (resultCode == RESULT_OK) {
                    String resultData = data.getStringExtra("result_data");
                    EditText etValue = findViewById(R.id.etMain);

                    etValue.setText(resultData);
                }

        }
    }
}