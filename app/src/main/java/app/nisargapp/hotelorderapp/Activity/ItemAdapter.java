package app.nisargapp.hotelorderapp.Activity;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AlphaAnimation;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;


import com.squareup.picasso.Picasso;

import java.util.List;

import app.nisargapp.hotelorderapp.API;
import app.nisargapp.hotelorderapp.Model.tblItem;
import app.nisargapp.hotelorderapp.R;

public class ItemAdapter extends  RecyclerView.Adapter<ItemAdapter.ViewHolder> {


    public List<tblItem> list;
    Context context;
    public String name;

    public ItemAdapter(Context context, List<tblItem> list) {
        this.context = context;
        this.list = list;
    }



    public class ViewHolder extends RecyclerView.ViewHolder {

        public TextView tvItem;
        public ImageView img;
        public CardView cv;

        public ViewHolder(View view) {
            super(view);
            tvItem = view.findViewById(R.id.tvItem);
            img = view.findViewById(R.id.img);
            cv = view.findViewById(R.id.cv);
        }
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(context)
                .inflate(R.layout.item_row, parent, false);

        return new ViewHolder(itemView);
    }


    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        // setFadeAnimation(holder.itemView);

        final tblItem task = list.get(position);
        try {

            holder.tvItem.setText(task.ITEM_NAME);

            Picasso.with(context)
                    .load(API.IMAGE_URL+task.ITEM_IMAGE).fit()
                    .centerCrop()
                    .into(holder.img);

            holder.cv.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    context.startActivity(new Intent(context,Detail_.class)
                    .putExtra("image",API.IMAGE_URL+task.ITEM_IMAGE)
                                    .putExtra("name",task.ITEM_NAME)
                                    .putExtra("price",task.ITEM_PRICE)
                                    .putExtra("details",task.ITEM_DESCRIPTION)
                                    .putExtra("size",task.ITEM_SIZE)
                                    .putExtra("itemid",task.ITEM_ID)
                    );
                }
            });
        }catch (Exception ex){

        }


    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public void setFadeAnimation(View view) {
        AlphaAnimation anim = new AlphaAnimation(0.0f, 1.0f);
        anim.setDuration(500);
        view.startAnimation(anim);
    }

    public String showFullName(String name){

        String str="";
        switch (name){
            case "Sun":
                str="Sunday";
                break;
            case "Mon":
                str="Monday";
                break;
            case "Wed":
                str="Wednesday";
                break;
            case "Thu":
                str="Thursday";
                break;
            case "Fri":
                str="Friday";
                break;
            case "Sat":
                str="Saturday";
                break;
            case "Tue":
                str="Tuesday";
                break;


        }
        return str;
    }
}
