package com.example.recyclerviewtest;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class MyRecyclerAdapter extends RecyclerView.Adapter<MyRecyclerAdapter.ViewHolder> {

    private Context context;
    private int layout;
    private List<String> list;

    public MyRecyclerAdapter(Context context, int layout, List<String> dataList) {
        this.context = context;
        this.layout = layout;
        this.list = dataList;
    }

    /* 뷰 생성을 위한 inflation 수행*/
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
//        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
//        View view = inflater.inflate(R.layout.item_layout, parent, false);
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_layout, parent, false);
        return new ViewHolder(view);
    }

    /*각 뷰에 데이터 추가 작업 수행*/
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.getTextView().setText(list.get(position));
    }

    /*getCount() 와 동일*/
    @Override
    public int getItemCount() {
        return list.size();
    }


    /* ViewHolder 클래스 - 생성자와 어댑터 뷰의 각 뷰가 갖고 있는 내부 뷰에 대한 getter를 추가*/
    public static class ViewHolder extends RecyclerView.ViewHolder {
//        레이아웃에 배치한 view 항목에 따라 추가
        private final TextView textView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            textView = (TextView) itemView.findViewById(R.id.textView);

//            해당 view에 이벤트 처리가 필요할 경우 이벤트 리스너 연결 추가
//            textView.setOnLongClickListener(new View.OnLongClickListener() {
//                @Override
//                public boolean onLongClick(View view) {
//                    Toast.makeText(ViewHolder.this.itemView.getContext(), ((TextView)view).getText() , Toast.LENGTH_SHORT).show();
//                    return true;
//                }
//            });
        }

        public TextView getTextView() {
            return textView;
        }
    }

}
