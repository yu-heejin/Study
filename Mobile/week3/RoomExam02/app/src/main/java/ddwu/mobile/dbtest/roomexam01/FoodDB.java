package ddwu.mobile.dbtest.roomexam01;

import androidx.room.Database;
import androidx.room.RoomDatabase;

@Database(entities = {Food.class}, version = 1)
// 데이터베이스 설정 및 데이터 접근 지점을 제공하는 추상 클래스
public abstract class FoodDB extends RoomDatabase {
    
}
