package mobile.example.alarmtest;

import java.util.Calendar;

import android.app.Activity;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.SystemClock;
import android.view.View;

public class MainActivity extends Activity {
	
	PendingIntent sender = null;
	AlarmManager alarmManager = null;
	
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_alarm_test);
		alarmManager = (AlarmManager)getSystemService(Context.ALARM_SERVICE);    // 알람 관리를 위해 시스템이 제공하는 서비스 클래스
	}

	public void mOnClick(View v) {
		
		Intent intent = null;

		switch (v.getId()) {
		case R.id.onetime:
			// 예약에 의해 호출될 BR 지정
			intent = new Intent(this, MyBroadcastReceiver.class);
			sender = PendingIntent.getBroadcast(this, 0, intent, 0);

			// 알람 시간. 10초후
			Calendar calendar = Calendar.getInstance();
			calendar.setTimeInMillis(System.currentTimeMillis());
			calendar.add(Calendar.SECOND, 3);

			// 알람 등록
			alarmManager.set(AlarmManager.RTC, calendar.getTimeInMillis(), sender);   // 일회성 알람 set
			// RTC : UTC 표준 시간을 기준으로 알람 시간을 설정한다.
			break;
		case R.id.repeat:
			intent = new Intent(this, RepeatReceiver.class);
			sender = PendingIntent.getBroadcast(this, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT);
//			60초당 한번 알람 등록 --> 최소 1분 정도로 반복을 설정하여야 함
			// ELAPSED_REALTIME : 안드로이드 시스템이 부팅한 이후 경과된 시간으로 알람 시간 설정 (WAKEUP : 절전 시 안드로이드를 꺠운다)
			alarmManager.setRepeating(AlarmManager.ELAPSED_REALTIME_WAKEUP,
					SystemClock.elapsedRealtime() + 3000, 10000 * 6, sender);    // 정확한 반복 알람
			// SystemClock.elapsedRealTime() : 부팅 이후 현재 경과된 시간을 알 수 있다.
//			정확도가 떨어지는 반복 알람 설정 시		
//			alarmManager.setInexactRepeating(AlarmManager.ELAPSED_REALTIME_WAKEUP, SystemClock.elapsedRealtime() + 3000,
//					AlarmManager.INTERVAL_FIFTEEN_MINUTES, sender);
            break;
		case R.id.stop:
			intent = new Intent(this, RepeatReceiver.class);
			sender = PendingIntent.getBroadcast(this, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT);
			if (sender != null) alarmManager.cancel(sender);    // 알람 취소
			break;
		}

	}



//	일정 시간 간격으로 작업을 반복하고자 할 때는 Handler 를 사용할 수도 있음
//    Handler handler = new Handler() {
//        @Override
//        public void handleMessage(Message msg) {
//            if (msg.arg1 != 5) {
//                Toast.makeText(AlarmTestActivity.this, "Alarm!!! " + msg.arg1, Toast.LENGTH_SHORT).show();
//                Message newMsg = handler.obtainMessage();
//                newMsg.arg1 = msg.arg1 + 1;
//                this.sendMessageDelayed(newMsg, 3000);
//            }
//        }
//    };

}

