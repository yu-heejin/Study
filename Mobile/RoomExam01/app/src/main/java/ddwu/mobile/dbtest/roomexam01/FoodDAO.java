package ddwu.mobile.dbtest.roomexam01;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

import io.reactivex.Flowable;
import io.reactivex.Single;

@Dao
public interface FoodDAO {
//    @Insert(onConflict = OnConflictStrategy.REPLACE)  //테이블에 동일한 ID의 행이 있을 경우 대체
//    long insertFood(Food food);   //추가한 행의 rowId 반환
//
//    @Update
//    void updateFood(Food food);
//
//    @Delete
//    void deleteFood(Food food);
//
//    @Query("SELECT * FROM food_table")
//    List<Food> getAllFoods();
//
//    @Query("SELECT * FROM food_table WHERE id = :id")
//    Food getFood(int id);

    //RxJava 타입 반환
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    Single<Long> insertFood(Food food);

    @Update
    Comparable updateFood(Food food);

    @Delete
    Comparable deleteFood(Food food);

    @Query("SELECT * FROM food_table")
    List<Food> getAllFoods();

    @Query("SELECT * FROM food_table WHERE id = :id")
    Flowable<List<Food>> getFood(int id);
}
