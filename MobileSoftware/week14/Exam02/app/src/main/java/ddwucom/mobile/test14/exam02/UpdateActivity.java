package ddwucom.mobile.test14.exam02;

import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

public class UpdateActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update);
    }

    public void onClick(View v) {
        switch(v.getId()) {
            case R.id.btn_update:

                break;
            case R.id.btn_cancel:

                break;
        }
    }
}
