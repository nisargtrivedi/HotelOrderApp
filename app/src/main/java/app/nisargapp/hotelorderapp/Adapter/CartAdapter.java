package app.nisargapp.hotelorderapp.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AlphaAnimation;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.List;

import app.nisargapp.hotelorderapp.API;
import app.nisargapp.hotelorderapp.Activity.Detail_;
import app.nisargapp.hotelorderapp.Model.tblCart;
import app.nisargapp.hotelorderapp.Model.tblItem;
import app.nisargapp.hotelorderapp.R;

public class CartAdapter extends  RecyclerView.Adapter<CartAdapter.ViewHolder> {


    public List<tblCart> list;
    Context context;
    public String name;
    OnCartItemManagement onCartItemManagement;

    public void ItemCLick(OnCartItemManagement onCartItemManagement){
        this.onCartItemManagement=onCartItemManagement;
    }

    public CartAdapter(Context context, List<tblCart> list) {
        this.context = context;
        this.list = list;
    }



    public class ViewHolder extends RecyclerView.ViewHolder {

        public TextView name,qty,total,plus,minus,topping;
        public ImageView img;
        public LinearLayout cv;
        public Button delete;

        public ViewHolder(View view) {
            super(view);
            name = view.findViewById(R.id.name);
            img = view.findViewById(R.id.img);
            cv = view.findViewById(R.id.cv);
            qty= view.findViewById(R.id.qty);
            total= view.findViewById(R.id.total);
            plus= view.findViewById(R.id.plus);
            minus= view.findViewById(R.id.minus);
            delete=view.findViewById(R.id.delete);
            topping=view.findViewById(R.id.topping);
        }
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(context)
                .inflate(R.layout.row, parent, false);

        return new ViewHolder(itemView);
    }


    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        // setFadeAnimation(holder.itemView);

        final tblCart task = list.get(position);
        try {

            holder.name.setText(task.NAME);
            holder.qty.setText(task.QUANTITY+"");
            holder.total.setText("Rs. "+((task.QUANTITY*task.PRICE)));
            holder.topping.setText(task.EXTRA_DETAILS);

            Picasso.with(context)
                    .load(task.IMAGE).fit()
                    .centerCrop()
                    .into(holder.img);

            holder.plus.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    onCartItemManagement.OnAdd(task);
                }
            });

            holder.minus.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    onCartItemManagement.OnMinus(task);
                }
            });
            holder.delete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    onCartItemManagement.OnDelete(task);
                }
            });
            holder.cv.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
//                    context.startActivity(new Intent(context,Detail_.class)
//                    .putExtra("image",API.IMAGE_URL+task.ITEM_IMAGE)
//                                    .putExtra("name",task.ITEM_NAME)
//                                    .putExtra("price",task.ITEM_PRICE)
//                                    .putExtra("details",task.ITEM_DESCRIPTION)
//                                    .putExtra("size",task.ITEM_SIZE)
//                    );
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
}
