package mobile.example.network.toyasynctask;

import androidx.appcompat.app.AppCompatActivity;

import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    final static String TAG = "MainActivity";

    TextView textView;
    TextAsyncTask textAsyncTask;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textView = findViewById(R.id.textView);
    }


    public void onClick(View v) {
        switch(v.getId()) {
            case R.id.btnStart1:
//                AsyncTask 생성 후 실행
                textAsyncTask = new TextAsyncTask();
                textAsyncTask.execute("hi!", "hello!");     // 매개변수 전달, strings[] 에 전달됨
                Toast.makeText(this, "AsyncTask Start!", Toast.LENGTH_SHORT).show();

                break;
            case R.id.btnStop:
                if (textAsyncTask != null) textAsyncTask.cancel(true);     // AsyncTask 중지
                break;
            case R.id.btnStart2:
                Toast.makeText(this, "Click!!!", Toast.LENGTH_SHORT).show();
                break;
        }
    }


//    String 타입의 매개변수를 전달받음, Integer 타입으로 진행상태 정보 표시, Integer 타입의 결과 생성
    class TextAsyncTask extends AsyncTask<String, Integer, Integer> {

        final static String TAG = "TextAsyncTask";

        @Override
        protected void onPreExecute() {   //비동기처리 전 실행
            textView.setText(textView.getText() + "\nonPreExecute() is performed!" + "\n");
        }

      /*  필수 구현
        excute(...) 에 입력한 매개변수를 배열 형태로 전달  */
        @Override
        protected Integer doInBackground(String... strings) {

            Log.d(TAG, " Async start! " + strings[0] + " " + strings[1]);

//            AsyncTask에서 해야할 작업
            int sum = 0;
            for (int i=1; i <= 100; i++) {
                sum += i;
                Log.d(TAG, "value: " + i);
                publishProgress(sum, i);   //sum, i 동시에 전달
                try {
                    Thread.sleep(50);
                } catch (InterruptedException e) {   //cancel되는 순간 발생, 쓰레드 종료
                    e.printStackTrace();
                    break;
                }
            }

            return sum;
        }

//        진행 상태를 publishProgress() 로부터 전달받음
        @Override
        protected void onProgressUpdate(Integer... values) {
            Log.d(TAG, "sum: " + values[0]);
            textView.setText("value: " + values[1] + "\n");
        }

//        @Override
//        protected void onCancelled(Integer n) {
//            textView.setText(textView.getText() + "Stop button is clicked!! " + n);
//        }

//        AsyncTask 중지 시 호출
        @Override
        protected void onCancelled() {
            textView.setText(textView.getText() + "Interrupted!");
            Log.d(TAG, "Stop button is clicked!");
        }

//        doInBackground() 가 반환한 결과값을 전달 받음
        @Override
        protected void onPostExecute(Integer n) {  // 수행 결과
            textView.setText(textView.getText() + "sum: " + n + "\n");
        }
    }


}

