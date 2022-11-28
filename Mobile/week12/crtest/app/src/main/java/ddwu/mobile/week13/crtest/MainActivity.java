package ddwu.mobile.week13.crtest;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.ContentResolver;
import android.content.ContentUris;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.CursorAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    final static String TAG = "MainActivity";

    final int PERMISSION_REQ_CODE = 100;    // Permission 요청 코드

    EditText editText;
    ListView listView;
    ImageView imageView;

    SimpleCursorAdapter adapter;
    ContentResolver contentResolver;
    Cursor mCursor;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editText = findViewById(R.id.editText);
        listView = findViewById(R.id.listView);
        imageView = findViewById(R.id.imageView);

        contentResolver = getContentResolver();

        initList();
    }


    public void onClick(View v) {
        switch(v.getId()) {
            case R.id.button:
                if (checkPermission()) {     // 사용자 퍼미션 체크하기
                    mCursor = queryImageByType(editText.getText().toString());
                    Log.d(TAG, "count: " + mCursor.getCount());
                    if (mCursor.getCount() == 0) {
                        Toast.makeText(getApplicationContext(), "No images", Toast.LENGTH_SHORT).show();
                    } else {
                        adapter.changeCursor(mCursor);
                    }
                }
                break;
        }
    }


    private Cursor queryImageByType(String imageType) {
        Uri uri = MediaStore.Images.Media.EXTERNAL_CONTENT_URI;    // 미디어 테이블 이름을 가져온다.

        String selection = MediaStore.Images.ImageColumns.MIME_TYPE + "=?";
        String[] selectArgs = new String[] { "image/" + imageType };

        if (TextUtils.isEmpty(imageType)) {
            selection = null;
            selectArgs = null;
        }

        return contentResolver.query(uri, null, selection , selectArgs, null);    // 만들어진 커서 반환

    }


    private void initList() {
        adapter = new SimpleCursorAdapter(this,
                android.R.layout.simple_list_item_2,
                null,       // 연결 Cursor
                new String[] { MediaStore.Images.ImageColumns.MIME_TYPE, MediaStore.Images.ImageColumns.DATA },
                new int[] { android.R.id.text1, android.R.id.text2 },
                CursorAdapter.FLAG_REGISTER_CONTENT_OBSERVER);

        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Uri uri = ContentUris.withAppendedId(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, id);
                Log.d(TAG, "URI: " + uri);
                imageView.setImageURI(uri);
            }
        });
    }


    /* 필요 permission 요청 */
    private boolean checkPermission() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (checkSelfPermission(Manifest.permission.READ_EXTERNAL_STORAGE)
                    != PackageManager.PERMISSION_GRANTED) {
                ActivityCompat.requestPermissions(this,
                        new String[] {Manifest.permission.READ_EXTERNAL_STORAGE},
                        PERMISSION_REQ_CODE);
                return false;
            }
        }
        return true;
    }


    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if(requestCode == PERMISSION_REQ_CODE) {
            if(grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                // 퍼미션을 획득하였을 경우 계속 수행
                queryImageByType(editText.getText().toString());
            } else {
                // 퍼미션 미획득 시 액티비티 종료
                Toast.makeText(this, "앱 실행을 위해 권한 허용이 필요함", Toast.LENGTH_SHORT).show();
                finish();
            }
        }
    }

}