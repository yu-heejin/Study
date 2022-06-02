package ddwucom.mobile.test12.exam02;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

public class UpdateActivity extends AppCompatActivity {
    Food food;

    EditText etFood;
    EditText etNation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update);

        food = (Food) getIntent().getSerializableExtra("food");
        //반환되는 형태가 serializable 인터페이스이기 때문에 타입 캐스팅을 해줘야함
        etFood = findViewById(R.id.et_food_name3);
        etNation = findViewById(R.id.et_nation3);

        etFood.setText(food.getFood());
        etNation.setText(food.getNation());
        //전달받은 객체의 음식값과 국가명을 받아옴
    }

    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_update:
                

                break;

            case R.id.btn_cancel:

                break;
        }
    }
}
