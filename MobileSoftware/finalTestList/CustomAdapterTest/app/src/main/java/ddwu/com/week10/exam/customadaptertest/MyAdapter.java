package ddwu.com.week10.exam.customadaptertest;

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
    private ArrayList<MyData> myDataList;
    private LayoutInflater layoutInflater;


    public MyAdapter(Context context, int layout, ArrayList<MyData> myDataList) {
        this.context = context;
        this.layout = layout;
        this.myDataList = myDataList;
        this.layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return myDataList.size();
    }

    @Override
    public Object getItem(int i) {
        return myDataList.get(i);
    }

    @Override
    public long getItemId(int i) {
        return myDataList.get(i).get_id();
    }

    @Override
    public View getView(int pos, View convertView, ViewGroup viewGroup) {
        final int position = pos;
        ViewHolder viewHolder;

        if (convertView == null) {
            convertView = layoutInflater.inflate(layout, viewGroup, false);

            viewHolder = new ViewHolder();
            viewHolder.textNo = convertView.findViewById(R.id.tvNo);
            viewHolder.textName = convertView.findViewById(R.id.tvName);
            viewHolder.textPhone = convertView.findViewById(R.id.tvPhone);
            viewHolder.btnCheck = convertView.findViewById(R.id.btnCheck);
            viewHolder.btnCheck.setFocusable(false);

            viewHolder.btnCheck.setOnClickListener(new Button.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Toast.makeText(context, myDataList.get(position).getPhone(), Toast.LENGTH_SHORT).show();
                }
            });

            convertView.setTag(viewHolder);

        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        viewHolder.textNo.setText( String.valueOf( myDataList.get(position).get_id()) );
        viewHolder.textName.setText( myDataList.get(position).getName() );
        viewHolder.textPhone.setText( myDataList.get(position).getPhone() );

        return convertView;
    }

    static class ViewHolder {
        TextView textNo;
        TextView textName;
        TextView textPhone;
        Button btnCheck;
    }


}
