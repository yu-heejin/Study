package ddwu.com.mbile.example.notitest;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.os.Build;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        createNotificationChannel();   // 알람을 사용하기 전에 해당 메소드 실행 (액티비티의 onCreate() 등)
    }

    public void onClick (View v) {
        switch (v.getId()) {
            case R.id.btnNoti:
                // 알림창에 작업 버튼 추가
                int notificationId = 100;
//                Intent snoozeIntent = new Intent(this, NotiActivity.class);
//                snoozeIntent.setAction(getString(R.string.ACTION_SNOOZE));
//                snoozeIntent.putExtra(notificationId, 0);
//                PendingIntent snoozePendingIntent =
//                        PendingIntent.getBroadcast(this, 0, snoozeIntent, 0);


                // 알람 실행 시 알림을 탭했을 때 실행할 동작 지정
                Intent intent = new Intent(this, NotiActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, intent, 0);

                // 알림을 생성하는 빌더 지정
                NotificationCompat.Builder builder = new NotificationCompat.Builder(this, getString(R.string.CHANNEL_ID))
                        .setSmallIcon(R.mipmap.ic_launcher)
                        .setContentTitle("알람입니당")
                        .setContentText("배가 너무 아파서 시험공부가 하기 싫어요")
                        .setPriority(NotificationCompat.PRIORITY_DEFAULT)     // 알람 표시의 우선순위
                        .addAction(R.drawable.ic_launcher_background, "snooze", pendingIntent)    // action 버튼 추가
                        .setAutoCancel(true);

                // NotificationManager를 사용하여 생성한 알림을 실행
                NotificationManagerCompat notificationManager = NotificationManagerCompat.from(this);
                notificationManager.notify(notificationId, builder.build());

                break;
        }
    }


    private void createNotificationChannel() {   // NotificationChannel을 생성하여 NotificationManager의 createNotificationChannel()에 전달
        // Create the NotificationChannel, but only on API 26+ because
        // the NotificationChannel class is new and not in the support library
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            CharSequence name = getString(R.string.channel_name);       // strings.xml 에 채널명 기록
            String description = getString(R.string.channel_description);       // strings.xml에 채널 설명 기록
            int importance = NotificationManager.IMPORTANCE_DEFAULT;    // 알림의 우선순위 지정
            NotificationChannel channel = new NotificationChannel(getString(R.string.CHANNEL_ID), name, importance);    // CHANNEL_ID 지정
            channel.setDescription(description);
            // Register the channel with the system; you can't change the importance
            // or other notification behaviors after this
            NotificationManager notificationManager = getSystemService(NotificationManager.class);  // 채널 생성
            notificationManager.createNotificationChannel(channel);
        }
    }

}
