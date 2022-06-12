package ddwucom.mobile.week11.myapplication4;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    int seletedIndex;
    boolean[] selectedItems = {false, false, false, false};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.button:
                AlertDialog.Builder builder = new AlertDialog.Builder(this);
//                builder.setTitle("대화상자 제목")
//                        .setMessage("대화상자 메세지")
//                        .setIcon(R.drawable.ic_launcher_background)
//                        .setPositiveButton("확인버튼", new DialogInterface.OnClickListener() {
//                            @Override
//                            public void onClick(DialogInterface dialogInterface, int i) {
//                                Toast.makeText(MainActivity.this, "확인", Toast.LENGTH_SHORT).show();
//                            }
//                        })
//                        .setCancelable(false)
//                        //true: 뒤로가기 버튼을 누르면 닫힘. 여백 화면을 눌러도 닫힘
//                        //false: 뒤로가기 버튼 눌러도 안 닫힘. 무조건 취소버튼 눌러야함. 여백 화면 눌러도 안 닫힘
//                        .setNeutralButton("대기버튼", null)
//                        .setNegativeButton("취소버튼", null)
//                        .show();

                //setCancelOnTouchOutside 구현 방법
//                builder.setTitle("대화상자 제목")
//                        .setMessage("대화상자 메세지")
//                        .setIcon(R.drawable.ic_launcher_background)
//                        .setPositiveButton("확인버튼", new DialogInterface.OnClickListener() {
//                            @Override
//                            public void onClick(DialogInterface dialogInterface, int i) {
//                                Toast.makeText(MainActivity.this, "확인", Toast.LENGTH_SHORT).show();
//                            }
//                        })
//                        .setCancelable(false)
//                        //true: 뒤로가기 버튼을 누르면 닫힘. 여백 화면을 눌러도 닫힘
//                        //false: 뒤로가기 버튼 눌러도 안 닫힘. 무조건 취소버튼 눌러야함. 여백 화면 눌러도 안 닫힘
//                        .setNeutralButton("대기버튼", null)
//                        .setNegativeButton("취소버튼", null);
//
//                Dialog dlg = builder.create();
//                dlg.setCanceledOnTouchOutside(false);     //dialog 객체를 호출한 상태에서 사용할 수 있음!
//                //백버튼 눌렀을 땐 닫히고 여백을 누르면 안 닫힘
//                dlg.show();

                //목록을 가진 대화상자
//                builder.setTitle("중식")
//                        .setItems(R.array.foods, new DialogInterface.OnClickListener() {
//                            @Override
//                            public void onClick(DialogInterface dialogInterface, int i) {
//                                String[] foods = getResources().getStringArray(R.array.foods);
//                                Toast.makeText(MainActivity.this, "선택: " + foods[i], Toast.LENGTH_SHORT).show();
//                            }
//                        })
//                        .show();

                //라디오 버튼을 가진 대화상자
//                builder.setTitle("음식을 선택하세요.")
//                        .setIcon(R.mipmap.ic_launcher)
//                        .setSingleChoiceItems(R.array.foods, seletedIndex, new DialogInterface.OnClickListener() {
//                            @Override
//                            public void onClick(DialogInterface dialogInterface, int i) {
//                                seletedIndex = i;   //선택한 항목의 index를 저장하기 위한 변수
//                            }
//                        })
//                        //context 메뉴와 달리 초기화가 안돼서, 굳이 선택 인덱스 값을 저장하지 않아도 라디오가 선택됨
//                        //selectedIndex는 이전에 선택한 항목 index값이 저장된다.
//                        //그래서 만약 초기에 짜장면을 골랐다면, 다른 요소를 골라도 selectedIndex의 값은 절대 변하지않음
//                        //그래서 클릭 이벤트 리스너를 통해 선택 항목의 index를 저장하는 것이다!
//                        .setPositiveButton("확인", new DialogInterface.OnClickListener() {
//                            @Override
//                            public void onClick(DialogInterface dialogInterface, int i) {
//                                String[] foods = getResources().getStringArray(R.array.foods);
//                                Toast.makeText(MainActivity.this, "선택: " + foods[seletedIndex], Toast.LENGTH_SHORT).show();
//                            }
//                        })
//                        .show();

                //체크박스를 가진 대화상자
//                builder.setTitle("음식을 선택하세요.")
//                        .setIcon(R.mipmap.ic_launcher)
//                        .setMultiChoiceItems(R.array.foods, selectedItems, new DialogInterface.OnMultiChoiceClickListener() {
//                            @Override
//                            public void onClick(DialogInterface dialogInterface, int i, boolean b) {
//                                selectedItems[i] = b;   //해당 체크박스가 선택되었는지?
//                            }
//                        })
//                        .setPositiveButton("확인", new DialogInterface.OnClickListener() {
//                            @Override
//                            public void onClick(DialogInterface dialogInterface, int i) {
//                                String[] foods = getResources().getStringArray(R.array.foods);
//                                String result = "선택 : ";
//                                for(int k=0; k<selectedItems.length; k++) {
//                                    if(selectedItems[k]) {
//                                        result += foods[k] + " ";
//                                    }
//                                }
//                                Toast.makeText(MainActivity.this, result, Toast.LENGTH_SHORT).show();
//                            }
//                        })
//                        .show();

                //커스텀 대화상자
                final ConstraintLayout orderLayout = (ConstraintLayout) View.inflate(MainActivity.this, R.layout.order_layout, null);

                builder.setTitle("주문 정보 입력")
                        .setIcon(R.mipmap.ic_launcher)
                        .setView(orderLayout)
                        .setPositiveButton("확인", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                Toast.makeText(MainActivity.this, "안녕", Toast.LENGTH_SHORT).show();
                            }
                        })
                        .show();

                break;
        }
    }
}