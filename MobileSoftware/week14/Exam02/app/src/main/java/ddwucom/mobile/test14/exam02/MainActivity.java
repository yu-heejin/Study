package ddwucom.mobile.test14.exam02;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    final static String TAG = "MainActivity";
    final int REQ_CODE = 100;
    final int UPDATE_CODE = 200;

    ListView listView;
    ArrayAdapter adapter;
    ArrayList<Food> foodList = null;
    FoodDBHelper dbHelper;
    FoodDBManager foodDBManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listView = findViewById(R.id.listView);
        foodList = new ArrayList<Food>();

        adapter = new ArrayAdapter<Food>(this, android.R.layout.simple_list_item_1, foodList);
        listView.setAdapter(adapter);
        foodDBManager = new FoodDBManager(this);
        
        //DB에 있는 값을 읽어온다
        dbHelper = new FoodDBHelper(this);
        SQLiteDatabase db = dbHelper.getReadableDatabase();

        Cursor cursor = db.rawQuery("SELECT * FROM " + FoodDBHelper.TABLE_NAME, null);

        while(cursor.moveToNext()) {
            long id = cursor.getInt(cursor.getColumnIndexOrThrow(FoodDBHelper.COL_ID));
            String food = cursor.getString(cursor.getColumnIndexOrThrow(FoodDBHelper.COL_FOOD));
            String nation = cursor.getString(cursor.getColumnIndexOrThrow(FoodDBHelper.COL_NATION));
            foodList.add ( new Food (id, food, nation) );
        }

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                //int i(position) 정보로 우리가 사용해야할 food 객체 정보를 찾아냄
                Food food = foodList.get(i);  //현재 클릭한 위치의 객체 정보
                //이 정보를 Update Activity를 띄울 intent에 넣어서 전달

                Intent intent = new Intent(MainActivity.this, UpdateActivity.class);
                intent.putExtra("food", food);
                //food 객체를 담을 수 있는 이유는 Food 객체에 Serializable 인터페이스를 구현해놨기 때문

                //startActivity(intent);  //인텐트 실행
                startActivityForResult(intent, UPDATE_CODE);
                //업데이트 액티비티가 해당 코드에 의해 실행됨 but 업데이트를 했을 경우와 안 했을 경우 두가지 경우가 있음
                //따라서 수정 했는지 안했는지에 대한 결과를 알아내야함
            }
        });

        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {
                //int i(교수님 화면 기준 int position)이 몇번째 항목인지 알려줌
                //해당 변수는 지역변수라 함수가 끝나면 사라지기 때문에 상수나 지역변수로 선언해야함
                final int pos = i;
                //향후 커서 어댑터를 사용하면 아이디 값이 자동으로 들어옴
                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);   //메인액티비티 컨텍스트 추가
                builder.setTitle(R.string.dialog_title)
                        .setMessage(R.string.dialog_message)
                        .setPositiveButton(R.string.dialog_ok, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                //확인 버튼 클릭 시 수행
                                //deleteRecord(pos);

                                boolean result = foodDBManager.removeFood(foodList.get(pos).get_id());

                                if (result) {
                                    Toast.makeText(MainActivity.this, "삭제 완료", Toast.LENGTH_SHORT).show();
                                    foodList.clear();
                                    foodList.addAll(foodDBManager.getAllFood());   //화면 읽어오기
                                    adapter.notifyDataSetChanged();   //화면 갱신 코드를 붙여넣음
                                } else {
                                    Toast.makeText(MainActivity.this, "삭제 실패", Toast.LENGTH_SHORT).show();
                                }
                            }
                        })
                        .setNegativeButton(R.string.dialog_cancel, null)    //버튼 누를 때 취소만 하면 되니까 null
                        .setCancelable(false)   //다른 부분을 클릭해도 다이얼로그가 반드시 수행되도록 함(즉 사라지지 않도록 함)
                        .show();
                //각각의 string을 string.xml에 정의 후 사용

                return true;
            }
        });

        cursor.close();
        dbHelper.close();
    }

    /*
        리스트뷰의 몇번째 항목을 클릭했는지 알아내서 그 항목을 지워야함
        아이디값이 매우 중요(식별!)
     */

//    private void deleteRecord(int pos) {   //해당 위치에 있는 항목 삭제
//        //DB 삭제 작업 수행
//        //helper객체를 생성해서 writerable객체를 얻어옴
//        FoodDBHelper foodDBHelper = new FoodDBHelper(this);
//        SQLiteDatabase sqLiteDatabase = foodDBHelper.getWritableDatabase();
//
//        String whereClause = FoodDBHelper.COL_ID + "=?";
//        //우리가 알고 있는건 해당 리스트 항목의 위치를 알고 있을 뿐 아이디를 아는 것은 아님!
//        //그 위치로 가서 food 객체를 꺼내고, 그 food 객체에서 id를 꺼내면 됨
//        //foodList에서 꺼내면 됨!
//        String[] whereArgs = new String[] { String.valueOf(foodList.get(pos).get_id()) };   //pos 위치에 있는 객체를 꺼내 아이디 값을 반환받는다
//        //get_id는 숫자를 반환하는 것인데, 문자 타입으로 값을 받고 있기 때문에 오류 -> 문자열로 변경!
//        //이렇게 되면 =?와 결합하게 됨
//
//        int result = sqLiteDatabase.delete(FoodDBHelper.TABLE_NAME, whereClause, whereArgs);
//        //잘 삭제됐으면 1 이상의 값, 아니라면 0을 반환함 -> if 문 판단 가능
//
//        if(result > 0) {
//            Toast.makeText(this, "삭제 완료", Toast.LENGTH_SHORT).show();
//            foodList.clear();
//            foodList.addAll(foodDBManager.getAllFood());   //화면 읽어오기
//            adapter.notifyDataSetChanged();   //화면 갱신 코드를 붙여넣음
//        } else {
//            Toast.makeText(this, "삭제 실패", Toast.LENGTH_SHORT).show();
//        }
//
//        //마무리는 항상 DB를 닫아줘야한다
//        foodDBHelper.close();
//
//        //화면 갱신이 필요하다 -> DB에서 다시 읽어오는 작업이 필요
//        //이 부분은 다른 화면을 갔다가 다시 돌아오는 것이 아니라 단지 롱클릭 시 삭제되는 것이기 때문에
//        //onResume()이 실행이 안됨. 따라서 별도로 갱신하는 코드를 붙여넣는 것!
//        //이 기능은 DBManager로 옮기면 더 좋음 -> 이 때 pos값이 아니라 id값을 넘겨줘야함
//   }

    @Override
    protected void onResume() {   //화면 갱신
        super.onResume();
        foodList.clear();
        foodList.addAll(foodDBManager.getAllFood());
        adapter.notifyDataSetChanged();
        //DB의 내용을 다시 읽어옴
        //업데이트 시 업데이트 된 내용으로 바뀌어서 화면에 자동으로 갱신됨
        //돌아오면서 onActivityResult도 실행되면서 토스트 문구가 뜨게 됨
    }


    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.button:
                Intent intent = new Intent(this, AddActivity.class);
                startActivityForResult(intent, REQ_CODE);
                break;
        }
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQ_CODE) {  // AddActivity 호출 후 결과 확인
            switch (resultCode) {
                case RESULT_OK:
                    String food = data.getStringExtra("food");
                    Toast.makeText(this, food + " 추가 완료", Toast.LENGTH_SHORT).show();
                    break;
                case RESULT_CANCELED:
                    Toast.makeText(this, "음식 추가 취소", Toast.LENGTH_SHORT).show();
                    break;
            }
        } else if (requestCode == UPDATE_CODE) {    // UpdateActivity 호출 후 결과 확인
            switch (resultCode) {
                case RESULT_OK:
                    Toast.makeText(this, "음식 수정 완료", Toast.LENGTH_SHORT).show();
                    break;
                case RESULT_CANCELED:
                    Toast.makeText(this, "음식 수정 취소", Toast.LENGTH_SHORT).show();
                    break;
            }
        }
    }
}
