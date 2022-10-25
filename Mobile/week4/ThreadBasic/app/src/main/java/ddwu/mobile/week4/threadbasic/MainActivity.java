package ddwu.mobile.week4.threadbasic;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    final static String TAG = "MainActivity";

    EditText etText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etText = findViewById(R.id.etText);
    }

    public void onClick(View v) {
        switch(v.getId()) {
            case R.id.btnStart:
                //TestThread t = new TestThread();
                TestThread t = new TestThread(handler);
                t.start();
                etText.setText("Thread start!");
                Toast.makeText(this, "Running!", Toast.LENGTH_SHORT).show();
                break;
        }
    }

    Handler handler = new Handler() {   //재정의
        @Override
        public void handleMessage(@NonNull Message msg) {
            //super.handleMessage(msg);
            int i = msg.arg1;   //i값 꺼냄
            etText.setText("i: " + i);
        }
    };

    class TestThread extends Thread {
        //Handler 보관 변수
        Handler handler2;
        public TestThread(Handler handler) {
            this.handler2 = handler;
        }

        @Override
        public void run() {
            for (int i=0; i < 100; i++) {
                //Log.d(TAG, "i: " + i );
                Message msg = Message.obtain();
                msg.arg1 = i;
                handler2.sendMessage(msg);   //핸들러에 메세지 전송
                //sendMessage가 호출되면서 메인에 있는 핸들메세지가 호출됨

                try {
                    Thread.sleep(50);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }


}

