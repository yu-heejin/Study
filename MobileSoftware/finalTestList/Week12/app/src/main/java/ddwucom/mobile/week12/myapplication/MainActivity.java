package ddwucom.mobile.week12.myapplication;

import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    ArrayList<String> arr;
    final static int SUB_ACTIVITY_CODE = 100;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//        arr = new ArrayList<>();
//
//        arr.add("안녕하세요");
//        arr.add("데이터를 넘겨줄게요");
//
//        Intent intent = new Intent(this, SubActivity.class);
//        intent.putExtra("Array", arr);
//        startActivity(intent);

//        Intent intent = new Intent(this, SubActivity.class);
//        startActivityForResult(intent, SUB_ACTIVITY_CODE);

        //묵시적 인텐트의 사용
        //1. 지정한 웹사이트 실행
//        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.google.com"));
//        startActivity(intent);

        //2. 지정한 전화번호로 전화 기능 실행
        Intent intent = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:010-1111-1111"));
        startActivity(intent);
    }

//    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
//        super.onActivityResult(requestCode, resultCode, data);
//        switch (requestCode) {
//            case SUB_ACTIVITY_CODE:
//                if (resultCode == RESULT_OK) {
//                    String resultData = data.getStringExtra("result_data");
//                    Toast.makeText(this, "Result: " + resultData, Toast.LENGTH_SHORT).show();
//                }
//                break;
//        }
//    }
}