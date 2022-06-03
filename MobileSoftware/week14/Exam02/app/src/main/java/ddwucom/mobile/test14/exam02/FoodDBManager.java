package ddwucom.mobile.test14.exam02;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

public class FoodDBManager {

    FoodDBHelper foodDBHelper = null;
    Cursor cursor = null;

    public FoodDBManager(Context context) {
        foodDBHelper = new FoodDBHelper(context);    //foodDBHelper 생성자로 생성
    }

//    DB의 모든 food를 반환
    public ArrayList<Food> getAllFood() {
        ArrayList<Food> foodList = new ArrayList<Food>();
        SQLiteDatabase db = foodDBHelper.getReadableDatabase();

        Cursor cursor = db.rawQuery("SELECT * FROM " + FoodDBHelper.TABLE_NAME, null);

        while(cursor.moveToNext()) {
            long id = cursor.getInt(cursor.getColumnIndexOrThrow(FoodDBHelper.COL_ID));
            String food = cursor.getString(cursor.getColumnIndexOrThrow(FoodDBHelper.COL_FOOD));
            String nation = cursor.getString(cursor.getColumnIndexOrThrow(FoodDBHelper.COL_NATION));
            foodList.add ( new Food (id, food, nation) );
        }

        cursor.close();
        foodDBHelper.close();
        return foodList;
    }

//    DB 에 새로운 food 추가
    public boolean addNewFood(Food newFood) {
        SQLiteDatabase db = foodDBHelper.getWritableDatabase();
        ContentValues value = new ContentValues();
        value.put(FoodDBHelper.COL_FOOD, newFood.getFood());
        value.put(FoodDBHelper.COL_NATION, newFood.getNation());
        //새로운 객체를 하나 만들어서 나라 이름과 음식 이름을 넣어서 전달

//      insert 메소드를 사용할 경우 데이터 삽입이 정상적으로 이루어질 경우 1 이상, 이상이 있을 경우 0 반환 확인 가능
        long count = db.insert(FoodDBHelper.TABLE_NAME, null, value);

        if(count > 0) return true;    //정상적으로 수행 시 1이상의 값을 반환함
        else return false;
    }

//    _id 를 기준으로 food 의 이름과 nation 변경
    public boolean modifyFood(Food food) {   //바뀐 내용을 가진 food를 매개변수로 받아옴
        //바꿀 때는 아이디와 바뀔 값들 모두를 알고 있어야함
        //그래서 food객체 형태로 받아야함
        //FoodDBHelper foodDBHelper = new FoodDBHelper(this);
        SQLiteDatabase sqLiteDatabase = foodDBHelper.getWritableDatabase();

        ContentValues row = new ContentValues();   //바꿀 내용을 저장하는 ContentValues
        row.put(FoodDBHelper.COL_FOOD, food.getFood());
        row.put(FoodDBHelper.COL_NATION, food.getNation());
        //바꿀 데이터를 삽입함(editText의 값을 삽입)

        String whereClause = FoodDBHelper.COL_ID + "=?";
        String[] whereArgs = new String[] { String.valueOf(food.get_id()) };
        //where절의 조건을 선언함

        int result = sqLiteDatabase.update(foodDBHelper.TABLE_NAME, row, whereClause, whereArgs);

        foodDBHelper.close();
        if(result > 0) return true;
        else return false;
    }

//    _id 를 기준으로 DB에서 food 삭제
    public boolean removeFood(long id) {
        //DB 삭제 작업 수행
        //helper객체를 생성해서 writerable객체를 얻어옴
        //FoodDBHelper foodDBHelper = new FoodDBHelper(this);
        SQLiteDatabase sqLiteDatabase = foodDBHelper.getWritableDatabase();

        String whereClause = FoodDBHelper.COL_ID + "=?";
        String[] whereArgs = new String[] { String.valueOf(id) };
        //여기서는 바로 id 값을 받아서 사용함
        //get_id는 숫자를 반환하는 것인데, 문자 타입으로 값을 받고 있기 때문에 오류 -> 문자열로 변경!
        //이렇게 되면 =?와 결합하게 됨

        int result = sqLiteDatabase.delete(FoodDBHelper.TABLE_NAME, whereClause, whereArgs);
        //잘 삭제됐으면 1 이상의 값, 아니라면 0을 반환함 -> if 문 판단 가능

        foodDBHelper.close();

        if(result > 0) return true;
        else return false;
        //삭제가 잘 되면 true, 아니면 false
    }

//    나라 이름으로 DB 검색
    public ArrayList<Food> getFoodsByNation(String nation) {

        return null;
    }

//    음식 이름으로 DB 검색
    public ArrayList<Food> getFoodByName(String foodName) {
        return null;
    }

//    id 로 DB 검색
    public Food getFoodById(long id) {

        return  null;
    }

//    close 수행
    public void close() {
        if (foodDBHelper != null) foodDBHelper.close();
        if (cursor != null) cursor.close();
    };
}
