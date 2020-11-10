package app.nisargapp.hotelorderapp.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AlphaAnimation;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.List;

import app.nisargapp.hotelorderapp.Model.tblCart;
import app.nisargapp.hotelorderapp.R;

public class CartAdapterTwo extends  RecyclerView.Adapter<CartAdapterTwo.ViewHolder> {


    public List<tblCart> list;
    Context context;
    public String name;
    OnCartItemManagement onCartItemManagement;

    public void ItemCLick(OnCartItemManagement onCartItemManagement){
        this.onCartItemManagement=onCartItemManagement;
    }

    public CartAdapterTwo(Context context, List<tblCart> list) {
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
            qty= view.findViewById(R.id.qty);
            total= view.findViewById(R.id.price);

        }
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(context)
                .inflate(R.layout.row_two, parent, false);

        return new ViewHolder(itemView);
    }


    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        // setFadeAnimation(holder.itemView);

        final tblCart task = list.get(position);
        try {

            holder.name.setText(task.NAME+"\n"+task.EXTRA_DETAILS);
            holder.qty.setText(task.QUANTITY+"");
            holder.total.setText("Rs. "+((task.QUANTITY*task.PRICE)));


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
