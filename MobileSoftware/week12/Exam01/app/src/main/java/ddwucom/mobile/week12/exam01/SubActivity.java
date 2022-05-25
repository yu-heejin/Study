package ddwucom.mobile.week12.exam01;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

public class SubActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sub);

        Intent intent = getIntent();
        String text = intent.getStringExtra("value");
        EditText etText = findViewById(R.id.etSub);
        etText.setText(text);
    }

    public void onClick(View v) {
        EditText etText = findViewById(R.id.etSub);
        String resultValue = etText.getText().toString();
        switch (v.getId()) {
            case R.id.btnClose:
                Intent resultIntent = new Intent();
                resultIntent.putExtra("result_data", resultValue);
                setResult(RESULT_OK, resultIntent);
                break;
        }
        finish();
    }
}
