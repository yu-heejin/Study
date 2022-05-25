package ddwucom.moblie.week13.sqlitetest;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import androidx.annotation.Nullable;

public class FoodDBHelper extends SQLiteOpenHelper {
    final static String TAG = "FoodDBHelper";
    final static String DB_NAME = "food.db";

    //table, column 선언
    public final static String TABLE_NAME = "food_table";
    public final static String COL_ID = "_id";
    public final static String COL_FOOD = "food";
    public final static String COL_NATION = "nation";
    //table, column 이름은 많이 사용하기 때문에 일일이 사용하지 않고 상수로 선언함

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String sql = "CREATE TABLE " + TABLE_NAME + "(" + COL_ID + " integer primary key autoincrement," +
                COL_FOOD + " TEXT, " + COL_NATION + " TEXT)";
        Log.d(TAG, sql);
        sqLiteDatabase.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(sqLiteDatabase);
    }

    public FoodDBHelper(@Nullable Context context) {    //생성자 정의 필수 -> DB table을 생성함
        //super(context, name, factory, version);    //꼭 매개변수로 안 받아도 됨, null로 채워서 보내기 가능
        super(context, DB_NAME, null, 1);    //factory는 현재 사용하지 않으므로 null
        //버전을 바꾸면 onUpgrade 실행 후 onCreate 실행됨
    }
}
