package app.nisargapp.hotelorderapp.Activity;

import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Handler;
import android.text.Html;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.widget.Toolbar;

import com.google.android.material.appbar.AppBarLayout;
import com.google.android.material.appbar.CollapsingToolbarLayout;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.mobandme.ada.Entity;
import com.mobandme.ada.exceptions.AdaFrameworkException;
import com.squareup.picasso.Picasso;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.ViewById;

import app.nisargapp.hotelorderapp.API;
import app.nisargapp.hotelorderapp.Model.tblCart;
import app.nisargapp.hotelorderapp.Model.tblItem;
import app.nisargapp.hotelorderapp.R;

@EActivity(R.layout.detail)
public class Detail extends BaseActivity{

    @ViewById
    CollapsingToolbarLayout toolbar_layout;
    @ViewById
    AppBarLayout app_bar;
    @ViewById
    Toolbar toolbar;
    @ViewById
    ImageView expandedImage;
    @ViewById
    TextView tvDescription;
    @ViewById
    TextView price;
    @ViewById
            TextView size;
    @ViewById
    Button btnAdd;
    @ViewById
    Button btnShowCart;

    @ViewById
    FloatingActionButton fab;

    String image,name,pricee="0",details,sizee,itemid="0";
    @AfterViews
    public void init(){
        setSupportActionBar(toolbar);



        image=getIntent().getStringExtra("image");
        name=getIntent().getStringExtra("name");
        pricee=getIntent().getStringExtra("price");
        details=getIntent().getStringExtra("details");
        sizee=getIntent().getStringExtra("size");
        itemid=getIntent().getStringExtra("itemid");

        toolbar_layout.setTitle(name);
        tvDescription.setText(Html.fromHtml(details));
        price.setText("Rs. "+pricee);
        size.setText(sizee);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Picasso.with(Detail.this)
                        .load(image).fit()
                        .centerCrop()
                        .into(expandedImage);

            }
        },1000);
        app_bar.addOnOffsetChangedListener(new AppBarLayout.OnOffsetChangedListener() {
            boolean isShow = false;
            int scrollRange = -1;

            @Override
            public void onOffsetChanged(AppBarLayout appBarLayout, int verticalOffset) {
                if (scrollRange == -1) {
                    scrollRange = appBarLayout.getTotalScrollRange();
                }
                if (scrollRange + verticalOffset == 0) {
                    isShow = true;
                    //showOption(R.id.action_info);
                } else if (isShow) {
                    isShow = false;
                    //hideOption(R.id.action_info);
                }
            }
        });
    }
    @Click
    public void btnAdd(){


        startActivityForResult(new Intent(this,Customization_.class),1000);
    }
    @Click
    public void fab(){
        try {
            dataContext.tblCarts.fill("name=?",new String[]{name},null);
        } catch (AdaFrameworkException e) {
            e.printStackTrace();
        }
        AlertDialog.Builder builder= new AlertDialog.Builder(this);
        builder.setMessage("20% OFFER AVAILABLE ON BILLING AMOUNT GREATER THAN Rs.1000 \n PROMO CODE = PZ1000") .setTitle("OFFER");

        //Setting message manually and performing action on button click

                builder.setCancelable(false)
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.dismiss();

                    }
                });
        //Creating dialog box
        AlertDialog alert = builder.create();
        //Setting the title manually
        alert.setTitle("OFFER");
        alert.show();

    }
    @Click
    public void btnShowCart(){
        startActivity(new Intent(this,Cart_.class));
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if(resultCode==RESULT_OK){
            if(requestCode==1000){
                String DATA=data.getStringExtra("data");
                String total=data.getStringExtra("total");

                    try {
                        dataContext.tblCarts.fill("name=?", new String[]{name}, null);
                        if (dataContext.tblCarts.size() > 0) {
                            for (int i = 0; i < dataContext.tblCarts.size(); i++) {
                                if (name.equalsIgnoreCase(dataContext.tblCarts.get(i).NAME)) {
                                    tblCart cart = dataContext.tblCarts.get(i);
                                    cart.QUANTITY = cart.QUANTITY + 1;
                                    cart.setStatus(Entity.STATUS_UPDATED);
                                    dataContext.tblCarts.save(cart);
                                }
                            }
                        } else {
                            tblCart cart = new tblCart();
                            cart.QUANTITY = 1;
                            cart.NAME = name;
                            cart.PRICE = Integer.parseInt(pricee);
                            cart.SIZE = sizee;
                            cart.IMAGE = image;
                            cart.ITEMID=itemid;
                            if(!DATA.isEmpty()) {
                                cart.EXTRA_DETAILS = DATA;
                                cart.EXTRA_AMT = Integer.parseInt(total);
                            }else{
                                cart.EXTRA_DETAILS = "";
                                cart.EXTRA_AMT = Integer.parseInt(total);
                            }
                            cart.setStatus(Entity.STATUS_NEW);
                            dataContext.tblCarts.save(cart);
                        }
                    } catch (AdaFrameworkException e) {
                        e.printStackTrace();
                    }

            }
        }
    }
}
