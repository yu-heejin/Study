package ddwucom.moblie.week12.activitytest;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    final static int SUB_ACTIVITY_CODE = 100;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void onClick(View v) {
        DataClass dataClass = new DataClass();
        switch (v.getId()) {
            case R.id.button:
                Intent intent = new Intent(this, SubActivity.class);
                intent.putExtra("subject", "mobile software");  //값 넘겨주기 추가(변수명, 값)
                intent.putExtra("time", 3);
                intent.putExtra("dataClass", dataClass);
//                startActivity(intent);
                startActivityForResult(intent, SUB_ACTIVITY_CODE);   //결과를 위해 액티비티 실행
                //서브에서 결과를 만들어서 메인으로 보내줌
                //액티비티 정보를 사용해서 액티비티를 띄울거임
                //SUB_ACTIVITY_CODE : 구분코드, 결과 식별(?)


                //등록 안 하면 앱이 끝나버림
                //android.content.ActivityNotFoundException : 등록을 하지 않아서 발생하는 오류
                //AndroidManifest에서 등록 필요
                //Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.google.com"));   구글 웹 브라우저 실행
//                Intent intent = new Intent(Intent.ACTION_DIAL, Uri.parse("tel: 010-1111-1111"));   //번호 키패드 실행
//                startActivity(intent);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {   //sub_activity_code, result_ok, intent
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
            case SUB_ACTIVITY_CODE:
                if(resultCode == RESULT_OK) {
                    String resultData = data.getStringExtra("result_data");
                    Toast.makeText(this, "Result: " + resultData, Toast.LENGTH_SHORT).show();
                }
        }
    }
}