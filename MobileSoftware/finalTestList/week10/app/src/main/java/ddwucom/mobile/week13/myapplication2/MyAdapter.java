package ddwucom.mobile.week13.myapplication2;

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
    private Context context;   //inflater 객체 생성 시 필요
    private int layout;
    private ArrayList<MyData> myDataArrayList;
    private LayoutInflater layoutInflater;   //inflater 객체
    ViewHolder viewHolder;

    public MyAdapter(Context context, int layout, ArrayList<MyData> myDataArrayList) {
        this.context = context;
        this.layout = layout;   //커스텀 레이아웃이 매개변수로 들어옴
        this.myDataArrayList = myDataArrayList;
        layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);   //인수로 받은 Context를 사용하여 inflater 객체 준비
    }

    @Override
    public int getCount() {
        return myDataArrayList.size();   //배열 요소의 개수 반환
        //Adapter가 관리할 Data의 개수 설정
    }

    @Override
    public Object getItem(int i) {   //어떤 객체를 리턴할지 알 수 없기 때문에 최상위 객체인 Object 타입 사용
        return myDataArrayList.get(i);   //해당 인덱스에 있는 값 반환
        //Adapter가 관리하는 데이터의 아이템의 위치값을 객체 형태로 얻음
    }

    @Override
    public long getItemId(int i) {
        return myDataArrayList.get(i).get_id();
        //어댑터가 관리하는 데이터의 아이템의 위치 id값을 얻음
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {   //만들어져서 반환될 화면 구현
        //ListView에 보일 Row 설정
        //원본 데이터의 개수만큼 반복해서 호출된다
        //리스트 뷰 순서대로 원본 데이터로 뷰를 만들어 반환
        final int position = i;
        if(view == null) {
            view = layoutInflater.inflate(layout, viewGroup, false);
            //전달 받은 view가 미생성일 경우 inflater를 사용하여 layout에 해당하는 view 객체 생성
            //이 함수가 매번 실행되는 것을 생각해보자. 그럼 맨 처음에 함수가 호출될땐 view에 아무런 객체가 들어오지 않을 것이다
            //따라서 초기에 뷰를 저장하기 위해 layout, 즉 커스텀 뷰를 inflate하는 것이다
            viewHolder = new ViewHolder();
            viewHolder.tvNo = (TextView) view.findViewById(R.id.id);
            viewHolder.tvName = (TextView) view.findViewById(R.id.name);
            viewHolder.tvPhone = (TextView) view.findViewById(R.id.phone);
            viewHolder.button = (Button) view.findViewById(R.id.btn);
            view.setTag(viewHolder);   //해당 뷰에 고유 이름표가 뷰에 붙는다
            //view에도 바코드처럼 특별한 id를 부여할 수 있음, 즉 뷰를 식별하기 위함
            //findByView를 반복해서 호출하는 것은 성능 감소의 원인이 될 수 있다.
            //view가 초기에 들어오면 내부 클래스에 findViewById를 정적 클래스에 삽입한 후, 호출하지 않고 반복해서 사용할 수 있도록 구성
        } else {
            viewHolder = (ViewHolder) view.getTag();
            //재활용 하고있는 view에 대해 설정한 태그값을 받아옴
            //뷰를 재활용하기 위해 태그를 설정하는데, 이때 태그값을 viewHolder 객체로 설정한다면,!!!!!!!!!!!!!!!
            //getTag로 값을 받아올 때 view의 태그값인 viewHolder가 Object 형태로 반환됨 -> 형변환하여 viewHolder를 받아온다!!!!!!!!!!!!!!
        }

//        TextView tvNo = view.findViewById(R.id.id);
//        TextView tvName = view.findViewById(R.id.name);
//        TextView tvPhone = view.findViewById(R.id.phone);
//        Button button = view.findViewById(R.id.btn);
//        button.setFocusable(false);
        //커스텀 뷰에 버튼이 있을 경우 항목 아이템의 클릭/롱클릭이 동작하지 않을 수 있음
        //실제로 해당 코드를 주석처리 했더니 항목을 클릭했을 땐 longClick이 먹히지 않음을 알 수 있다!
        //버튼이 포커스를 모두 가져가기 때문에 버튼 외 다른 요소의 클릭이 먹히지 않을 수 있기 때문

//        tvNo.setText(String.valueOf(myDataArrayList.get(position).get_id()));
//        tvName.setText(myDataArrayList.get(position).getName());
//        tvPhone.setText(myDataArrayList.get(position).getPhone());
//        button.setOnClickListener(new Button.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Toast.makeText(context, myDataArrayList.get(position).getPhone() + " 선택", Toast.LENGTH_SHORT).show();
//            }
//        });

        //viewHolder.tvNo.setText(String.valueOf(myDataArrayList.get(position).get_id()));
        viewHolder.tvNo.setText(Integer.valueOf((int) myDataArrayList.get(position).get_id()).toString());
        viewHolder.tvName.setText(myDataArrayList.get(position).getName());
        viewHolder.tvPhone.setText(myDataArrayList.get(position).getPhone());
        viewHolder.button.setFocusable(false);

        viewHolder.button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Toast.makeText(context,myDataArrayList.get(position).getPhone() + " 선택", Toast.LENGTH_SHORT).show();
            }
        });
        return view;
    }

    static class ViewHolder {   //어댑터의 내부 클래스로 구현
        //뷰를 보관하는 객체
        TextView tvNo;
        TextView tvName;
        TextView tvPhone;
        Button button;
    }
}
