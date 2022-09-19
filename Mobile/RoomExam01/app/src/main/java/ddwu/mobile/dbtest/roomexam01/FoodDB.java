package ddwu.mobile.dbtest.roomexam01;

import android.content.Context;

import androidx.room.Database;
import androidx.room.RoomDatabase;

//@Database(entities = {Food.class}, version = 1)
//public abstract class FoodDB extends RoomDatabase {
//    public abstract FoodDAO foodDAO();
//}

//Singleton 패턴
@Database(entities = {Food.class}, version = 1)
public abstract class FoodDB extends RoomDatabase {
    public abstract FoodDAO foodDAO();
    private static volatile FoodDB INSTANCE;

    static FoodDB getDatabase(final Context context) {
        if(INSTANCE == null) {
            INSTANCE
        }
    }
}
