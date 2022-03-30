package ddwucom.moblie.week05.soundtest;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.media.AudioAttributes;
import android.media.AudioManager;
import android.media.SoundPool;
import android.os.Bundle;
import android.os.Vibrator;
import android.view.View;

public class MainActivity extends AppCompatActivity {
    SoundPool soundPool;
    int sound;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

 //       soundPool = new SoundPool(1, AudioManager.STREAM_MUSIC, 0);
        //가능하면 사용하는 것을 추천하지 않음
//        AudioAttributes audioAttributes = new AudioAttributes.Builder()
//                .setUsage(AudioAttributes.USAGE_NOTIFICATION)
//                .setContentType(AudioAttributes.CONTENT_TYPE_MUSIC)
//                .build();
//
//        soundPool = new SoundPool.Builder()
//                .setMaxStreams(5)
//                .setAudioAttributes(audioAttributes)
//                .build();
//
//        sound = soundPool.load(this, R.raw.dingdong, 1);  //미리 적재
        //액티비티 생성시 soundpool을 불러와야 버튼 클릭 시 실행 가능
        //onCreate의 근본적인 목적 : 화면 구성
        //이 코드를 실행하느라고 화면 띄우는 것이 오래걸릴 수 있음
        //onCreate는 최대한 빨리 실행돼야함  ->  로딩하는 함수를 따로 만들어야한다
    }

    public void onClick(View v) {
//        AudioAttributes audioAttributes = new AudioAttributes.Builder()
//                .setUsage(AudioAttributes.USAGE_NOTIFICATION)
//                .setContentType(AudioAttributes.CONTENT_TYPE_MUSIC)
//                .build();
//
//        SoundPool soundPool = new SoundPool.Builder()
//                .setMaxStreams(5)
//                .setAudioAttributes(audioAttributes)
//                .build();

//        SoundPool soundPool = new SoundPool(1, AudioManager.STREAM_MUSIC, 0);
        //가능하면 사용하는 것을 추천하지 않음
//        int sound = soundPool.load(this, R.raw.dingdong, 1);
//        soundPool.play(sound, 1, 1, 0, 0, 1);
        Vibrator vibrator = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);

        //진동은 스마트폰의 모터를 사용
        //모터를 계속 사용하면 배터리낭비 -> 안드로이드 운영체제의 허락을 받아야함
        //manifest파일에서 추가해야함
        vibrator.vibrate(500);
        vibrator.vibrate(new long[] {100, 50, 200, 50}, 0);

        vibrator.cancel();

    }
}