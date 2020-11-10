package app.nisargapp.hotelorderapp.Activity;

import android.content.DialogInterface;
import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.mobandme.ada.Entity;
import com.mobandme.ada.exceptions.AdaFrameworkException;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.ViewById;

import app.nisargapp.hotelorderapp.Adapter.CartAdapter;
import app.nisargapp.hotelorderapp.Adapter.OnCartItemManagement;
import app.nisargapp.hotelorderapp.MainActivity;
import app.nisargapp.hotelorderapp.Model.tblCart;
import app.nisargapp.hotelorderapp.R;

@EActivity(R.layout.cart)
public class Cart extends BaseActivity {

    @ViewById
    RecyclerView rvItems;
    @ViewById
    TextView tvTotal;
    @ViewById
    RelativeLayout rl;
    @ViewById
    Button btnAdd;
    @ViewById
    ImageView done;

    CartAdapter adapter;
    int t=0;
    @AfterViews
    public void init(){

        try {
            dataContext.tblCarts.fill();
            adapter=new CartAdapter(this,dataContext.tblCarts);
            rvItems.setLayoutManager(new LinearLayoutManager(this));
            rvItems.setAdapter(adapter);
            if(dataContext.tblCarts.size()>0) {
                for (int i = 0; i < dataContext.tblCarts.size(); i++) {
                    t = t + ((dataContext.tblCarts.get(i).QUANTITY * dataContext.tblCarts.get(i).PRICE)+dataContext.tblCarts.get(i).EXTRA_AMT);
                }
                tvTotal.setText("Rs." + t);
                adapter.ItemCLick(new OnCartItemManagement() {
                    @Override
                    public void OnAdd(tblCart cart) {

                        try {
                            dataContext.tblCarts.fill();
                            if(dataContext.tblCarts.size()>0){
                                for(int i=0;i<dataContext.tblCarts.size();i++){
                                    if(cart.NAME.equalsIgnoreCase(dataContext.tblCarts.get(i).NAME)) {
                                        tblCart cart1 = dataContext.tblCarts.get(i);
                                        cart1.QUANTITY = cart1.QUANTITY + 1;
                                        cart1.setStatus(Entity.STATUS_UPDATED);
                                        dataContext.tblCarts.save(cart1);
                                    }
                                }
                            }
                        } catch (AdaFrameworkException e) {
                            e.printStackTrace();
                        }
                        adapter.notifyDataSetChanged();
                        t=0;
                        for(int i=0;i<dataContext.tblCarts.size();i++){
                            t = t + ((dataContext.tblCarts.get(i).QUANTITY * dataContext.tblCarts.get(i).PRICE)+dataContext.tblCarts.get(i).EXTRA_AMT);
                        }
                        tvTotal.setText("Rs."+t);
                        showEmptyCart();
                    }

                    @Override
                    public void OnMinus(tblCart cart) {

                        if(cart.QUANTITY>1){
                            try {
                                dataContext.tblCarts.fill();
                                if(dataContext.tblCarts.size()>0){
                                    for(int i=0;i<dataContext.tblCarts.size();i++){
                                        if(cart.NAME.equalsIgnoreCase(dataContext.tblCarts.get(i).NAME)) {
                                            tblCart cart1 = dataContext.tblCarts.get(i);
                                            cart1.QUANTITY = cart1.QUANTITY - 1;
                                            cart1.setStatus(Entity.STATUS_UPDATED);
                                            dataContext.tblCarts.save(cart1);
                                        }
                                    }
                                }
                            } catch (AdaFrameworkException e) {
                                e.printStackTrace();
                            }
                        }else{
                            AlertDialog.Builder builder= new AlertDialog.Builder(Cart.this);
                            builder.setMessage("Are you want sure delete this item?") .setTitle("Delete");

                            //Setting message manually and performing action on button click

                            builder.setCancelable(false)
                                    .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                                        public void onClick(DialogInterface dialog, int id) {
                                            dialog.dismiss();
                                            try {
                                                dataContext.tblCarts.fill("name=?",new String[]{cart.NAME},null);
                                                if(dataContext.tblCarts.size()>0){
                                                    for(int i=0;i<dataContext.tblCarts.size();i++){
                                                        dataContext.tblCarts.remove(i).setStatus(Entity.STATUS_DELETED);
                                                    }
                                                    dataContext.tblCarts.save();
                                                    dataContext.tblCarts.fill();
                                                }
                                            } catch (AdaFrameworkException e) {
                                                e.printStackTrace();
                                            }
                                            adapter.notifyDataSetChanged();
                                            t=0;
                                            for(int i=0;i<dataContext.tblCarts.size();i++){
                                                t = t + ((dataContext.tblCarts.get(i).QUANTITY * dataContext.tblCarts.get(i).PRICE)+dataContext.tblCarts.get(i).EXTRA_AMT);
                                            }
                                            tvTotal.setText("Rs."+t);
                                            showEmptyCart();
                                        }
                                    });
                            //Creating dialog box
                            AlertDialog alert = builder.create();
                            //Setting the title manually
                            alert.setTitle("Delete Item");
                            alert.show();
                        }
                        adapter.notifyDataSetChanged();
                        t=0;
                        for(int i=0;i<dataContext.tblCarts.size();i++){
                            t = t + ((dataContext.tblCarts.get(i).QUANTITY * dataContext.tblCarts.get(i).PRICE)+dataContext.tblCarts.get(i).EXTRA_AMT);
                        }
                        tvTotal.setText("Rs."+t);
                        showEmptyCart();
                    }

                    @Override
                    public void OnDelete(tblCart cart) {
                        AlertDialog.Builder builder= new AlertDialog.Builder(Cart.this);
                        builder.setMessage("Are you want sure delete this item?") .setTitle("Delete");

                        //Setting message manually and performing action on button click

                        builder.setCancelable(false)
                                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog, int id) {
                                        dialog.dismiss();
                                        try {
                                            dataContext.tblCarts.fill("name=?",new String[]{cart.NAME},null);
                                            if(dataContext.tblCarts.size()>0){
                                                for(int i=0;i<dataContext.tblCarts.size();i++){
                                                    dataContext.tblCarts.remove(i).setStatus(Entity.STATUS_DELETED);
                                                }
                                                dataContext.tblCarts.save();
                                                dataContext.tblCarts.fill();
                                            }
                                        } catch (AdaFrameworkException e) {
                                            e.printStackTrace();
                                        }

                                        adapter.notifyDataSetChanged();
                                        t=0;
                                        for(int i=0;i<dataContext.tblCarts.size();i++){
                                            t = t + ((dataContext.tblCarts.get(i).QUANTITY * dataContext.tblCarts.get(i).PRICE)+dataContext.tblCarts.get(i).EXTRA_AMT);
                                        }
                                        tvTotal.setText("Rs."+t);
                                        showEmptyCart();
                                    }
                                });
                        //Creating dialog box
                        AlertDialog alert = builder.create();
                        //Setting the title manually
                        alert.setTitle("Delete Item");
                        alert.show();

                    }
                });
            }else
                showEmptyCart();

        } catch (AdaFrameworkException e) {
            e.printStackTrace();
        }
    }
    private void showEmptyCart(){
        if(adapter.list.size()==0){
            rl.setVisibility(View.VISIBLE);
            rvItems.setVisibility(View.GONE);
            done.setVisibility(View.GONE);
        }else{
            rl.setVisibility(View.GONE);rvItems.setVisibility(View.VISIBLE);done.setVisibility(View.VISIBLE);
        }
    }
    @Click
    public void btnAdd(){
        finish();
        startActivity(new Intent(this, MainActivity.class));
    }
    @Click
    public void back(){
        finish();
    }

    @Click
    public void  done(){
        startActivity(new Intent(this, Bill_.class));
    }
}
