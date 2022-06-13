package ddwucom.mobile.week12.myapplication;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class SubActivity extends AppCompatActivity {
    ArrayList<String> getArr;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sub);

//        Intent intent = getIntent();
//        getArr = (ArrayList<String>) intent.getSerializableExtra("Array");
//
//        System.out.println(getArr);

//        Intent resultIntent = new Intent();
//        resultIntent.putExtra("result_data", "배고파");
//        setResult(RESULT_OK, resultIntent);
    }
}
