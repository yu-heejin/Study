package mobile.example.sensorbasictest;


import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

import java.util.List;

public class MainActivity extends AppCompatActivity {

	private TextView tvText;
	private SensorManager sensorManager;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		SensorManager sensorManager = (SensorManager)getSystemService(Context.SENSOR_SERVICE);
		
		tvText = findViewById(R.id.tvText);
		
	}
	
	public void onClick(View v) {
		switch(v.getId()) {
		case R.id.btnSensor:
			List<Sensor> sensorList = sensorManager.getSensorList(Sensor.TYPE_ALL);
			String result = "";
			
			// 6 페이지 강의자료 내용 마저 입력하기
			
			tvText.setText(result);
			
			break;
		}
	}
	
	
}
