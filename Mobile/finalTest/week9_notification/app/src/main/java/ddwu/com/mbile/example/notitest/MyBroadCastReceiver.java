package ddwu.com.mbile.example.notitest;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.Toast;

public class MyBroadCastReceiver extends BroadcastReceiver {
    private static final String TAG = "MyBroadCastReceiver";

    @Override
    public void onReceive(Context context, Intent intent) {    // context: receive 실행중인 콘텍스트, intent: 방송 수신 메시지를 담은 intent
        // 빠른 시간 안에 수행을 마쳐야한다.
        StringBuilder db = new StringBuilder();
        db.append("Action: " + intent.getAction() + "\n");
        db.append("URI: " + intent.toUri(intent.URI_INTENT_SCHEME).toString() + "\n");

        String log = db.toString();
        Log.d(TAG, log);

        Toast.makeText(context, log, Toast.LENGTH_SHORT).show();
    }
}
