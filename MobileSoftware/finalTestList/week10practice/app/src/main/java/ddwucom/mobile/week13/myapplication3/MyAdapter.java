package ddwucom.mobile.week13.myapplication3;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class MyAdapter extends BaseAdapter {
    private Context context;
    private int layout;
    private ArrayList<MyData> myDataArrayList;
    private LayoutInflater layoutInflater;

    public MyAdapter(Context context, int layout, ArrayList<MyData> myDataArrayList) {
        this.context = context;
        this.layout = layout;
        this.myDataArrayList = myDataArrayList;
        layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return myDataArrayList.size();
    }

    @Override
    public Object getItem(int i) {
        return myDataArrayList.get(i);
    }

    @Override
    public long getItemId(int i) {
        return myDataArrayList.get(i).get_id();
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        final int pos = i;

        if(view == null) {
            view = layoutInflater.inflate(layout, viewGroup, false);
        }

        TextView t1 = view.findViewById(R.id.id);
        TextView t2 = view.findViewById(R.id.title);
        TextView t3 = view.findViewById(R.id.detail);
        TextView t4 = view.findViewById(R.id.state);

        t1.setText(String.valueOf(myDataArrayList.get(pos).get_id()));
        t2.setText(myDataArrayList.get(pos).getTitle());
        t3.setText(myDataArrayList.get(pos).getDetail());
        t4.setText(myDataArrayList.get(pos).getState());
        return view;
    }
}
