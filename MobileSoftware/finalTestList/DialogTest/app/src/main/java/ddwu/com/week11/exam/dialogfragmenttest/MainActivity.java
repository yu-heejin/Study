package ddwu.com.week11.exam.dialogfragmenttest;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;

import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void onClick(View v) {
//        구현한 DialogFragment 생성
        DialogFragment dialogFragment = new TestDialogFragment();

        switch(v.getId()) {
            case R.id.button:
//                다이얼로그 표시, 프래그먼트를 사용하므로 FragmentManager 를 전달
                dialogFragment.show(getSupportFragmentManager(), "Test Dialog Fragment");
                break;
        }
    }

}