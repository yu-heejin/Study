package ddwucom.moblie.week11.dialogpractice;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    //int selectedIndex;    //라디오 버튼의 경우 하나만 선택하기 때문에 인덱스값만 저장
    boolean[] selectedItems = {false, false, false, false};   //체크박스의 경우 여러개 선택이 가능하기 때문에 배열로 저장

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void onClick2(View v) {
        final ConstraintLayout orderLayout = (ConstraintLayout)View.inflate(this, R.layout.order_layout, null);
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        String[] foodsArr = getResources().getStringArray(R.array.foods);

        builder.setTitle("중식")
                //.setMessage("골라봥")    -> 이거 없애야 리스트 들어옴
//                .setItems(R.array.foods, new DialogInterface.OnClickListener() {
//                    @Override
//                    public void onClick(DialogInterface dialogInterface, int i) {
//                        Toast.makeText(MainActivity.this, "선택" + foodsArr[i], Toast.LENGTH_SHORT).show();
//                    }
//                })
//                .setSingleChoiceItems(R.array.foods, selectedIndex, new DialogInterface.OnClickListener() {    //라디오 버튼
//                    @Override
//                    public void onClick(DialogInterface dialogInterface, int i) {
//                        selectedIndex = i;
//                        System.out.println(selectedIndex);
//                    }
//                })
//                .setMultiChoiceItems(R.array.foods, selectedItems, new DialogInterface.OnMultiChoiceClickListener() {
//                    @Override
//                    public void onClick(DialogInterface dialogInterface, int i, boolean isChecked) {
//                        selectedItems[i] = isChecked;
//                    }
//                })
                .setView(orderLayout)  //화면을 만들 자바 객체를 삽입(xml -> 자바 객체)
                .setIcon(R.mipmap.ic_launcher)
                .setPositiveButton("확인버튼", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        EditText etProduct = orderLayout.findViewById(R.id.etProduct);
                        String product = etProduct.getText().toString();
//                        //Toast.makeText(MainActivity.this, "성공!", Toast.LENGTH_SHORT).show();
//                        String[] foods = getResources().getStringArray(R.array.foods);
//                        //Toast.makeText(MainActivity.this, "선택 : " + foods[selectedIndex], Toast.LENGTH_SHORT).show();      //라디오버튼
//                        String result = "선택 : ";
//                        for(int j=0; j<selectedItems.length; j++) {
//                            if(selectedItems[j]) {
//                                result += foods[j] + " ";
//                            }
//                        }
//                        Toast.makeText(MainActivity.this, result, Toast.LENGTH_SHORT).show();
                        Toast.makeText(MainActivity.this, product, Toast.LENGTH_SHORT).show();
                    }
                })
                .setCancelable(false)   //false : 오직 대화상자의 취소버튼을 눌러야만 종료 / true : 뒤로가기나 대화상자 외의 배경을 누르면 종료
                //.setCancelOnTouchOutside(true);   안 먹히는 것 같음
                .setNeutralButton("대기버튼", null)
                .setNegativeButton("취소버튼", null)


                .show();
    }
}