package ddwu.mobile.dbtest.roomexam01;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "food_table")
public class Food {
    @PrimaryKey(autoGenerate = true)
    public int id;

    public String food;

    public String nation;

    public Food(String food, String nation) {
        this.food = food;
        this.nation = nation;
    }
}
