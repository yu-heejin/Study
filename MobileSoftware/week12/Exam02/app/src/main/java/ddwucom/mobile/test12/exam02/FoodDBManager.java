package ddwucom.mobile.test12.exam02;

import android.database.sqlite.SQLiteDatabase;

public class FoodDBManager {

    
    //_id를 기준으로 DB에서 food 삭제
    public boolean removeFood(long id) {
        //DB 삭제 작업 수행
        //helper객체를 생성해서 writerable객체를 얻어옴
        //FoodDBHelper foodDBHelper = new FoodDBHelper(this);
        SQLiteDatabase sqLiteDatabase = foodDBHelper.getWritableDatabase();

        String whereClause = FoodDBHelper.COL_ID + "=?";
        //우리가 알고 있는건 해당 리스트 항목의 위치를 알고 있을 뿐 아이디를 아는 것은 아님!
        //그 위치로 가서 food 객체를 꺼내고, 그 food 객체에서 id를 꺼내면 됨
        //foodList에서 꺼내면 됨!
        String[] whereArgs = new String[] { String.valueOf(id) };   //pos 위치에 있는 객체를 꺼내 아이디 값을 반환받는다
        //get_id는 숫자를 반환하는 것인데, 문자 타입으로 값을 받고 있기 때문에 오류 -> 문자열로 변경!
        //이렇게 되면 =?와 결합하게 됨

        int result = sqLiteDatabase.delete(FoodDBHelper.TABLE_NAME, whereClause, whereArgs);
        //잘 삭제됐으면 1 이상의 값, 아니라면 0을 반환함 -> if 문 판단 가능

        foodDBHelper.close();

        if(result > 0) return true;
        else return false;
        //삭제가 잘 되면 true, 아니면 false
    }
}
