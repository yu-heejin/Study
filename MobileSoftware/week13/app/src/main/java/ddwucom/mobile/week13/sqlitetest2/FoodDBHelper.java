package ddwucom.mobile.week13.sqlitetest2;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import androidx.annotation.Nullable;

public class FoodDBHelper extends SQLiteOpenHelper {
    //처음 상속받으면 에러가 나는데, 메소드를 재정의하지 않았기 때문
    final static String TAG = "FoodDBHelper";
    final static String DB_NAME = "foods.db";
    public final static String TABLE_NAME = "food_table";
    public final static String COL_ID = "_id";
    public final static String COL_FOOD = "food";
    public final static String COL_NATION = "nation";

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {   //'테이블'을 생성함
        String sql = "CREATE TABLE " + TABLE_NAME + " (" + COL_ID + " integer primary key autoincrement, " +
                COL_FOOD + " TEXT, " + COL_NATION + " TEXT)";
        //띄어쓰기를 주의해야 한다. 문자열이 붙어서 다른 단어가 될 수 있기 때문
        //기본적으로 id가 필요한데, 아이디같은 경우에는 안드로이드에서 기본적으로 임의의 상수값 Long type value를 넣게 됨
        //PK를 따로 설정하는 것이 아니기 때문
        //id는 integer, PK, autoincrement 속성을 넣으면 아이디를 내가 일일이 부여할 필요가 없음
        //레코드가 들어가면 자동으로 id값을 줌
        //_id의 형태로 이름을 지어야한다. 안드로이드 다른 클래스에서는 기본키는 항상 _id라고 정했을 것이라 생각하기 때문
        //이렇게 안 정해도 되는데, 만약 다른 클래스에서 DB에 접근하려고 하면 _id가 기본키라고 생각하고 동작하기 때문에
        //다른 클래스와 연계하면서 프로그램을 돌리고 싶으면 무조건 _id로 해놓아야함
        //기본키 : 유일한 식별자 - 의미없는 값을 부여하는 것이 일반적(????)이다. 어떤 특정한 정보 값은 중복이거나 없을 수 있기 때문이다.
        Log.d(TAG, sql);
        sqLiteDatabase.execSQL(sql);   //insert, update, delete 시 사용함 execSQL -> 전달받아 테이블을 만듦

        //onCreate는 언제 실행이 되는가?
        //helper를 실행하고 getWriteable/ReadableDatabase를 수행해서 DB를 얻어오는데,
        //이것을 맨 처음 최초 1회 수행할 때만 onCreate가 실행됨
        //onCreate가 맨 처음에만 실행됨. 테이블 만드는 문장이 동작하면서 DB에 테이블이 생성됨
        //맨처음에만 실행되니까 getWriteable 등을 다른 곳에서 다시 실행하면 onCreate는 실행되지 않음(이미 테이블이 만들어졌기 때문)
        //매번 테이블을 만드는 것이 아니라 최초 1회 앱을 설치하고 나서 DB를 사용하는 getWriteable/ReadableDatabase를 호출하는
        //맨 처음 시점에만 테이블을 생성하게 됨
    }

    //중간에 테이블을 수정해야하는데, 수정한다고 해서 테이블 구조가 바뀌는 것은 아님. 이미 앱은 깔려있기 때문 + DB를 한번이라도 사용함 = 이미 테이블 만들어짐
    //내용을 아무리 바꿔봤자 onCreate는 실행이 안됨
    //따라서, 테이블 생성 코드를 수정하고 생성자에서 버전값을 바꾸면 그땐 다시 실행할 수 있음
    //i(oldVer)과 i1(newVer)값이 다르면 onUpgrade 메소드 실행
    //복잡하면 앱을 걍 삭제하고 다시 깔면 됨
    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);    //앞서 설치했던 테이블을 지워버림(만약 존재한다면)
        onCreate(sqLiteDatabase);   //테이블 다시 만듦
    }

    public FoodDBHelper(@Nullable Context context) {    //'파일'을 생성
        //생성자 부분
        //부모 생성자를 다시 호출하고 있는데, 이때 매개변수를 전달받을 수 있지만, 굳이 전달받지 않아도 됨
        //외부에서 context만 전달받도록 함
        //DB_NAME : 실제 DB가 저장되는 파일의 이름. 생성자가 실행되면 생성됨
        //여러 곳에서 DBHelper 객체를 만드는데, 그 때마다 생성자가 실행되어 DB파일이 만들어지면 안됨!!
        //한 번 만들어지면 더 이상 만들어지지 않음. 즉 최초 1회 앱 설치 이후 맨 처음 이용 시 파일이 만들어짐
        //이미 만들어진 파일이 있다면 더이상 만들어지지 않음
        super(context, DB_NAME, null, 1);
        //버전 1 -> 여기서 버전 값을 바꾸면 자동으로 onUpgrade가 호출되기 시작함
    }
}
