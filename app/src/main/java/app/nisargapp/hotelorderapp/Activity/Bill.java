package app.nisargapp.hotelorderapp.Activity;

import android.content.DialogInterface;
import android.content.Intent;
import android.util.Log;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.mobandme.ada.Entity;
import com.mobandme.ada.exceptions.AdaFrameworkException;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.ViewById;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import app.nisargapp.hotelorderapp.API;
import app.nisargapp.hotelorderapp.Adapter.CartAdapter;
import app.nisargapp.hotelorderapp.Adapter.CartAdapterTwo;
import app.nisargapp.hotelorderapp.MainActivity;
import app.nisargapp.hotelorderapp.Model.tblItem;
import app.nisargapp.hotelorderapp.R;
import app.nisargapp.hotelorderapp.Utility.AppController;

@EActivity(R.layout.bill)
public class Bill extends BaseActivity {

    @ViewById
    RecyclerView rvItems;

    @ViewById
    TextView Amount;

    @ViewById
    TextView discount;

    @ViewById
    TextView finalAmount;
    @ViewById
            TextView bill;
    @ViewById
            TextView tvDate;
    @ViewById
    Spinner spTable;


    CartAdapterTwo adapter;
    int t=0;
    @AfterViews
    public void init(){

        try {
            dataContext.tblCarts.fill();
        } catch (AdaFrameworkException e) {
            e.printStackTrace();
        }
        adapter=new CartAdapterTwo(this,dataContext.tblCarts);
        rvItems.setLayoutManager(new LinearLayoutManager(this));
        rvItems.setAdapter(adapter);
        for (int i = 0; i < dataContext.tblCarts.size(); i++) {
            t = t + ((dataContext.tblCarts.get(i).QUANTITY * dataContext.tblCarts.get(i).PRICE)+dataContext.tblCarts.get(i).EXTRA_AMT);
        }
        Amount.setText("Rs." + t);
        if(t>1000){
            discount.setText("Rs.20");
            finalAmount.setText("Rs."+(t-20));
        }else{
            discount.setText("Rs.0");
            finalAmount.setText("Rs."+(t-0));
        }
        bill.setText(new Date().getTime()+"");
        Date date = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
        String strDate= formatter.format(date);
        tvDate.setText(strDate);

        //ORDERS();

    }
    @Click
    public void btnOrder(){
        bindItem();
    }

    public JSONArray ORDER_ITEMS(String tableid){
        JSONArray array=new JSONArray();
        try {
            dataContext.tblCarts.fill();
            for(int i=0;i<dataContext.tblCarts.size();i++){
                JSONObject obj=new JSONObject();
                obj.put("table_id",tableid);
                obj.put("item_id",dataContext.tblCarts.get(i).ITEMID);
                obj.put("qty",dataContext.tblCarts.get(i).QUANTITY);
                obj.put("price",dataContext.tblCarts.get(i).PRICE);
                obj.put("extra_items",dataContext.tblCarts.get(i).EXTRA_DETAILS);
                array.put(obj);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return array;
    }

    public JSONObject ORDERS(){
        Date date = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        String strDate= formatter.format(date);

        JSONObject object=new JSONObject();
        try {
            object.put("o_date",strDate);
            object.put("o_amount",t+"");
            object.put("o_status","PENDING");
            object.put("token",appPreferences.getString("TOKEN"));
        } catch (JSONException e) {
            e.printStackTrace();
        }
        System.out.println("ORDERS----->"+object.toString());

        return object;
    }

    private void bindItem(){
        showDialog();
        StringRequest request=new StringRequest(Request.Method.POST,
                API.PLACE_ORDER,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        hidePDialog();
                        Log.i("RESPONSE",response.toString());
                        if(!response.isEmpty()){
                            try {
                                JSONObject object=new JSONObject(response);
                                if(object!=null){
                                    int code=object.optInt("code");
                                    if(code==200){
                                        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(Bill.this);
                                        alertDialogBuilder.setTitle("ORDER INFORMATION");
                                        alertDialogBuilder.setMessage("Your order is successfully placed.We will notify you when order will completed.");
                                        alertDialogBuilder.setPositiveButton("yes",
                                                new DialogInterface.OnClickListener() {
                                                    @Override
                                                    public void onClick(DialogInterface arg0, int arg1) {
                                                        arg0.dismiss();
                                                        try {
                                                            dataContext.tblCarts.fill();
                                                            for(int i=0;i<dataContext.tblCarts.size();i++)
                                                                dataContext.tblCarts.remove(i).setStatus(Entity.STATUS_DELETED);

                                                            dataContext.tblCarts.save();
                                                            finishAffinity();
                                                            startActivity(new Intent(Bill.this, MainActivity.class));
                                                        } catch (AdaFrameworkException e) {
                                                            e.printStackTrace();
                                                        }
                                                    }
                                                });

                                        AlertDialog alertDialog = alertDialogBuilder.create();
                                        alertDialog.show();
                                    }else{

                                    }
                                }
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }

                        }

                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                hidePDialog();
            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                HashMap<String,String> map=new HashMap<>();
                map.put("order",ORDERS()+"");
                map.put("details",ORDER_ITEMS(tableno(spTable.getSelectedItem().toString()))+"");
                return map;
            }
        };
        AppController.getInstance().addToRequestQueue(request);
    }
    private String tableno(String t){
        String ans="";
        switch (t){
            case "R101":ans="1";break;
            case "R102":ans="2";break;
            case "R103":ans="3";break;
            case "R104":ans="4";break;
            case "R105":ans="5";break;
            case "R106":ans="6";break;
            case "R107":ans="7";break;
            case "R108":ans="8";break;
            case "R109":ans="9";break;
            case "R110":ans="10";break;
        }
        return ans;
    }
}
