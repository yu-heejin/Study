package ddwucom.moblie.week10.customadatrtst;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class MyAdapter extends BaseAdapter {
    private Context context;
    private int layout;
    private ArrayList<MyData> myDataList;    //하나의 그룹 데이터가 여러개
    private LayoutInflater layoutInflater;   //inflater 객체 생성 시 필요하지만 없어도 됨
    ViewHolder holder;

    public MyAdapter(Context context, int layout, ArrayList<MyData> myDataList) {   //생성자
        this.context = context;   //레이아웃 인플레이터 사용
        this.layout = layout;
        this.myDataList = myDataList;
        layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        //context가 필요하기 때문에 전달받음
    }

    @Override
    public int getCount() {
        return myDataList.size();
    }

    @Override
    public Object getItem(int i) {   //몇번째 항목에 대한 실제 데이터값
        return myDataList.get(i);   //object type : 어떤 값이든 전달가능(최상위 클래스)
    }

    @Override
    public long getItemId(int i) {   //내부적으로 id는 long으로 식별하도록 구성됨
        return myDataList.get(i).get_id();   //id 값을 알아냄
    }

    @Override
    public View getView(int pos, View convertView, ViewGroup viewGroup) {

        final int position = pos;

        if(convertView == null) {  //모든 뷰가 초기 상태 null 아님
            convertView = layoutInflater.inflate(layout, viewGroup, false);
            //xml 화면 인플레이션
            //맨 처음에는 화면이 빈 화면이 들어오기 때문에 없으면 만들어주는 작업 필요
            //화면에 보여지는 갯수만큼 getView() 수행
            //이후 스크롤하면 만들어졌던게 가려지는데, 이때 null 대신 가려진 값이 들어오게됨
            //뷰 갯수만큼 다 실행되는것이 아니라 일정 개수만 수행함
            holder = new ViewHolder();
            holder.textNo = (TextView) convertView.findViewById(R.id.tvNo);
            holder.textName = (TextView) convertView.findViewById(R.id.tvName);
            holder.textPhone = (TextView) convertView.findViewById(R.id.tvPhone);
            holder.btnCheck = (Button) convertView.findViewById(R.id.btnCheck);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

//        TextView tvNo = convertView.findViewById(R.id.tvNo);
//        TextView tvName = convertView.findViewById(R.id.tvName);
//        TextView tvPhone = convertView.findViewById(R.id.tvPhone);
//        Button btnCheck = convertView.findViewById(R.id.btnCheck);
//        btnCheck.setFocusable(false);  //이벤트 리스너 클릭시 버튼 동작 중지. onCLick에만 반응하도록
        //버튼은 모든 포커싱을 갖고감 버튼 목적이 클릭이기 때문
        //각각의 뷰 요소를 가져옴
        //코드 부하가 많이 발생하는 부분 -> 스크롤할 때마다 요소를 찾지 않음(가능한 많이 사용하지 않도록)
        //미리 만들어놓은거 찾아서 씀

        holder.textNo.setText(Integer.valueOf((int) myDataList.get(position).get_id()).toString());
        //string.valueof 없으면 오류남 > long값을 집어넣을 수 없기 때문
        holder.textName.setText(myDataList.get(position).getName());
        holder.textPhone.setText(myDataList.get(position).getPhone());
        //원본 데이터 연결시킴
        
        //버튼 이벤트 구현하기
        holder.btnCheck.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Toast.makeText(context,myDataList.get(position).getPhone() + " 선택", Toast.LENGTH_SHORT).show();
            }
        });

        return convertView;
    }

    static class ViewHolder {   //뷰 요소들을 갖고 있음(위에 findViewById대신 사용하게되는 내부 클래스)
        TextView textNo;
        TextView textName;
        TextView textPhone;
        Button btnCheck;


    }
    //추상클래스 상속 -> 반드시 모두 구현(Alt + enter)
}
