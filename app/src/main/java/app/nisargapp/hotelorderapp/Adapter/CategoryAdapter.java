package app.nisargapp.hotelorderapp.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.List;

import app.nisargapp.hotelorderapp.Activity.Item;
import app.nisargapp.hotelorderapp.Activity.Item_;
import app.nisargapp.hotelorderapp.Model.tblCategory;
import app.nisargapp.hotelorderapp.R;

public class CategoryAdapter extends BaseAdapter {

    List<tblCategory> list;
    Context context;

    public CategoryAdapter(List<tblCategory> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int i) {
        return list.get(i);
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {

        viewHolder holder;
        if(view==null){
            view= LayoutInflater.from(context).inflate(R.layout.category_list_row,viewGroup,false);
            holder=new viewHolder(view);
            view.setTag(holder);
        }else{
                holder= (viewHolder) view.getTag();
        }
        tblCategory category=list.get(i);
        holder.tv.setText(category.CATEGORY_NAME);
        holder.rl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                context.startActivity(new Intent(context, Item_.class));
            }
        });
        return view;
    }
    class viewHolder {
        TextView tv;
        RelativeLayout rl;
        public viewHolder(View v) {
            tv=v.findViewById(R.id.tvCategory);
            rl=v.findViewById(R.id.rl);
        }
    }
}
