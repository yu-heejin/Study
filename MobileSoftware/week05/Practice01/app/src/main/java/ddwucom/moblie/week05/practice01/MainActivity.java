package ddwucom.moblie.week05.practice01;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.media.AudioAttributes;
import android.media.SoundPool;
import android.os.Bundle;
import android.os.Vibrator;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    SoundPool soundPool;
    int sound;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        AudioAttributes audioAttributes = new AudioAttributes.Builder()
                .setUsage(AudioAttributes.USAGE_NOTIFICATION)
                .setContentType(AudioAttributes.CONTENT_TYPE_MUSIC)
                .build();

        soundPool = new SoundPool.Builder()
                .setMaxStreams(5)
                .setAudioAttributes(audioAttributes)
                .build();

        sound = soundPool.load(this, R.raw.dingdong, 1);
    }

    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnToast:
                Toast.makeText(this, "유희진", Toast.LENGTH_LONG).show();
                break;

            case R.id.btnSound:
                soundPool.play(sound, 1, 1, 0, 0, 1);
                break;

            case R.id.btnVibration:
                Vibrator vibrator = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
                vibrator.vibrate(500);
                vibrator.vibrate(new long[] {100, 50, 200, 50}, 0);

                vibrator.cancel();
        }
    }
}