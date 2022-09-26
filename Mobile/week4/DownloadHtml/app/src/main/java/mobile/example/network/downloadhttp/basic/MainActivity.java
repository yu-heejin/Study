package mobile.example.network.downloadhttp.basic;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.os.NetworkOnMainThreadException;
import android.os.StrictMode;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import javax.net.ssl.HttpsURLConnection;

public class MainActivity extends Activity {

	EditText etUrl;
	TextView tvResult;
	ImageView imageView;
// 실행이 완료되면 해당 http 페이지의 소스코드를 가져옴

	@Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        etUrl = findViewById(R.id.etUrl);
        tvResult = findViewById(R.id.tvResult);
        imageView = findViewById(R.id.imageView);

/*        네트워크 메인 스레드 제약 사항 적용을 해제하기 위해 사용
		실습 시 테스트용으로만 사용하며 추후 스레드 또는 AsyncTask 사용 방법으로 대체할 것		*/
//		StrictMode.ThreadPolicy pol
//				= new StrictMode.ThreadPolicy.Builder().permitNetwork().build();
//		StrictMode.setThreadPolicy(pol);

		//버튼이 눌린 상태에서 멈춤 -> 네트워크를 메인스레드에서 사용할 수 없음 (비동기처리 안됨)
	}

	public void onClick(View v) {

		String address = etUrl.getText().toString();
//		String address = getResources().getString(R.string.image_url);

//		네트워크 사용 가능 여부 확인
		if (!isOnline()) {
			Toast.makeText(this, "Network is not available!", Toast.LENGTH_SHORT).show();
			return;
		}

		switch (v.getId()) {
			case R.id.btnDownload:
				if (!address.equals("")) {
					//String result = downloadContents(address);
					//네트워크 호출 부분이 다른 쓰레드 안에 들어가야함
					//tvResult.setText(result);
				}
				break;
			case R.id.btnImgDownload:
//				이미지를 다운로드 한 후 readStreamToBitmap() 호출

				String imageAddress = getResources().getString(R.string.image_url);
				Bitmap bitmap = downloadContents(imageAddress);
				imageView.setImageBitmap(bitmap);
				break;
		}

	}


//	네트워크 사용 가능 여부 확인
	private boolean isOnline() {
		ConnectivityManager connMgr = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
		NetworkInfo networkInfo = connMgr.getActiveNetworkInfo();
		return (networkInfo != null && networkInfo.isConnected());
	}


//	문자열 형태의 웹 주소를 입력으로 서버 응답을 문자열로 만들어 반환
	//네트워크 수행
	private Bitmap downloadContents(String address){		// 이미지일 경우 Bitmap 반환
		HttpsURLConnection conn = null;
		InputStream stream = null;
		String result = null;
		int responseCode = 200;
		Bitmap bitmap = null;

		try {
			URL url = new URL(address);
			conn = (HttpsURLConnection)url.openConnection();

			conn.setReadTimeout(5000);   //타임아웃 5초
			conn.setConnectTimeout(5000);
			conn.setRequestMethod("GET");  //get
			conn.setDoInput(true);
			//conn.setDoOutput(true);   ->  post 방식의 경우 추가 필요

			conn.connect();

			responseCode = conn.getResponseCode();
			if (responseCode != HttpsURLConnection.HTTP_OK) {   //응답코드가 200일 경우
				throw new IOException("HTTP error code: " + responseCode);
			}

			stream = conn.getInputStream();   //받아온 데이터를 실제로 꺼냄
			//result = readStream(stream);	// 이미지일 경우 readStreamToBitmap 사용
			//가공하는 작업 필요
			bitmap = readStreamToBitmap(stream);

		} catch (MalformedURLException e) {
			Toast.makeText(this, "주소 오류", Toast.LENGTH_SHORT).show();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (NetworkOnMainThreadException e) {
			e.printStackTrace();
		} finally {
			if (stream != null) {
				try { stream.close(); }
				catch (IOException e) { e.printStackTrace(); }
			}
			if (conn != null) conn.disconnect();
		}

		//return result;
		return bitmap;
	}


	public String readStream(InputStream stream){   //뽑아낸 데이터를 가공함
		StringBuilder result = new StringBuilder();

		try {
			InputStreamReader inputStreamReader = new InputStreamReader(stream);
			BufferedReader bufferedReader = new BufferedReader(inputStreamReader);

			String readLine = bufferedReader.readLine();

			while (readLine != null) {
				result.append(readLine + "\n");
				readLine = bufferedReader.readLine();
			}

			bufferedReader.close();
		} catch (IOException e) {
			e.printStackTrace();
		}

		return result.toString();
	}


	private Bitmap readStreamToBitmap(InputStream stream) {
		return BitmapFactory.decodeStream(stream);
	}


}
