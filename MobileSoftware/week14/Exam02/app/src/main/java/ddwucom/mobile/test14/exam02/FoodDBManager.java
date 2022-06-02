package ddwucom.mobile.test14.exam02;

import android.content.Context;
import android.database.Cursor;

import java.util.ArrayList;

public class FoodDBManager {

    FoodDBHelper foodDBHelper = null;
    Cursor cursor = null;

    public FoodDBManager(Context context) {
        foodDBHelper = new FoodDBHelper(context);
    }

//    DB의 모든 food를 반환
    public ArrayList<Food> getAllFood() {

        return null;
    }

//    DB 에 새로운 food 추가
    public boolean addNewFood(Food newFood) {

        return true;
    }

//    _id 를 기준으로 food 의 이름과 nation 변경
    public boolean modifyFood(Food food) {

        return true;
    }

//    _id 를 기준으로 DB에서 food 삭제
    public boolean removeFood(long id) {

        return true;
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
