package mobile.example.alarmtest;

import android.content.*;
import android.widget.*;

public class RepeatReceiver extends BroadcastReceiver {
	public void onReceive(Context context, Intent intent) {
		Toast.makeText(context, "Hi all!", Toast.LENGTH_SHORT).show();

		// notification 생성


	}
}
