package ddwucom.moblie.week11.dialogtest;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void onClick(View v) {   //protected로 하면 오류남;
        //final ConstraintLayout orderLayout = (ConstraintLayout) View.inflate(this, R.layout.order_layout, null);
        //상수로 불렀기 때문에 사라지지 않음, 사용자 정의 화면
        switch (v.getId()) {
            case R.id.hello:
                AlertDialog.Builder builder = new AlertDialog.Builder(this);
                builder.setTitle("대화상자 제목")
                        //.setMessage("대화상자 메시지")
                        .setItems(R.array.foods, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                //EditText txt = orderLayout.findViewById(R.id.etProduct);
                                //String product = txt.getText().toString();
                                //토스트로 메시지를 띄워줌
                                String[] foods = getResources().getStringArray(R.array.foods);   //리소스에 접근할 수 있는 코드를 불러움
                                Toast.makeText(MainActivity.this, "선택 " + foods[i], Toast.LENGTH_SHORT).show();
                            }
                        })   //목록을 불러오고, 목록을 선택했을 때 반응 설정
                        //리스너 동작중 : 실행 시 화면이 달
                        .setIcon(R.mipmap.ic_launcher)       //아이콘이기 때문에 mipmap
                        .setCancelable(false)     //false : 내부 취소버튼이 아니라 안드로이드 폰 자체의 back버튼을 눌러도 사라지지 않음!!!!
                        .setPositiveButton("확인버튼", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                Toast.makeText(MainActivity.this, "확인", Toast.LENGTH_SHORT).show();    //클릭 시 해당 코드가 실행되면서 대화상자가 닫힘
                            }
                        })
                        //.setCanceledOnTouchOutside(false)   안됨
                        .setNeutralButton("대기버튼", null)
                        .setNegativeButton("취소버튼", null);

                builder.show();
                break;
        }
    }
}