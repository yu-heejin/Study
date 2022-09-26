package ddwu.mobile.dbtest.roomexam01;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface FoodDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)   //테이블에 동일한 ID의 행이 있을 경우 대체함
    //단일 매개변수일 경우 long, 컬렉션일 경우 long[] or List<Long> return
    long insertFood(Food food);

    @Update  //매개변수 Entity의 동일 ID 행을 찾아 수정 수행
    //반환타입을 설정할 경우 수정한 행 개수 반환 가능
    void updateFood(Food food);

    @Delete   //매개변수 Entity의 동일 ID 행을 찾아 삭제
    // 반환타입을 설정할 경우 삭제한 행의 개수를 반환
    void deleteFood(Food food);

    @Query("SELECT * FROM food_table WHERE id = :id")
    //복잡한 삽입, 삭제, 수정 작업을 수행할 때 쿼리 사용
    // :{name} : 메소드의 매개변수 사용 쿼리 내부에 작성 시 :매개변수 형식 사용
    Food getAllFoods(int id);

    @Query("SELECT * FROM food_table")
    List<Food> getAllFoods();

}
