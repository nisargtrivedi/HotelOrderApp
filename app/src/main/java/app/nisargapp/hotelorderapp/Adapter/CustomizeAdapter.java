package app.nisargapp.hotelorderapp.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.List;

import app.nisargapp.hotelorderapp.Activity.Item_;
import app.nisargapp.hotelorderapp.Model.tblCategory;
import app.nisargapp.hotelorderapp.Model.tblToppings;
import app.nisargapp.hotelorderapp.R;

public class CustomizeAdapter extends BaseAdapter {

    List<tblToppings> list;
    Context context;

    OnCustomeClick click;

    public void click(OnCustomeClick click){
        this.click=click;
    }
    public CustomizeAdapter(List<tblToppings> list, Context context) {
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
            view= LayoutInflater.from(context).inflate(R.layout.customize_row,viewGroup,false);
            holder=new viewHolder(view);
            view.setTag(holder);
        }else{
                holder= (viewHolder) view.getTag();
        }
        tblToppings category=list.get(i);
        holder.tvText.setText(category.Name);
        holder.tvPrice.setText("Rs."+category.Price);

        holder.tvText.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if(b){
                    category.isSelected=1;
                    click.OnAdd(category);
                }
            }
        });

        return view;
    }
    class viewHolder {
        TextView tvPrice;
        CheckBox tvText;
        RelativeLayout rl;
        public viewHolder(View v) {
            tvPrice=v.findViewById(R.id.tvPrice);
            tvText=v.findViewById(R.id.tvText);
        }
    }
}
