package app.nisargapp.hotelorderapp.Activity;

import android.content.Intent;
import android.util.Log;

import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.google.android.material.tabs.TabLayout;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.ViewById;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import app.nisargapp.hotelorderapp.API;
import app.nisargapp.hotelorderapp.Model.tblCategory;
import app.nisargapp.hotelorderapp.Model.tblItem;
import app.nisargapp.hotelorderapp.R;
import app.nisargapp.hotelorderapp.Utility.AppController;

@EActivity(R.layout.item)
public class Item extends BaseActivity {

    @ViewById
    RecyclerView rvItems;
    @ViewById
    TabLayout tabLayout;

    List<tblItem> list=new ArrayList<>();
    List<tblItem> filter=new ArrayList<>();
    ItemAdapter adapter;
    @AfterViews
    public void init(){

        adapter=new ItemAdapter(this,filter);
        rvItems.setLayoutManager(new GridLayoutManager(this,2));
        rvItems.setAdapter(adapter);
        bindItem();

        tabLayout.addOnTabSelectedListener(new TabLayout.BaseOnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                if(tab.getPosition()==0){
                    filter("7");
                }else if(tab.getPosition()==1){
                    filter("8");
                }else if(tab.getPosition()==2){
                    filter("9");
                }
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

    }
    private void bindItem(){
        showDialog();
        StringRequest request=new StringRequest(Request.Method.GET,
                API.ITEMS,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        hidePDialog();
                        list.clear();
                        filter.clear();
                        Log.i("CATEGRORY RESPONSE",response.toString());
                        if(!response.isEmpty()){
                            try {
                                JSONObject object=new JSONObject(response);
                                if(object!=null){
                                    int code=object.optInt("code");
                                    if(code==200){
                                        JSONArray array=object.optJSONArray("item");
                                        if(array!=null && array.length()>0){
                                            for(int i=0;i<array.length();i++){
                                                tblItem category=new tblItem();
                                                category.ITEM_ID=array.optJSONObject(i).optString("item_id");
                                                category.ITEM_CATEGORY=array.optJSONObject(i).optString("category_id");
                                                category.ITEM_DESCRIPTION=array.optJSONObject(i).optString("description");
                                                category.ITEM_IMAGE=array.optJSONObject(i).optString("image");
                                                category.ITEM_NAME=array.optJSONObject(i).optString("item_name");
                                                category.ITEM_SIZE=array.optJSONObject(i).optString("size");
                                                category.ITEM_PRICE=array.optJSONObject(i).optString("price");
                                                list.add(category);
                                            }
                                        }
                                    }else{

                                    }
                                    filter.addAll(list);
                                    adapter.notifyDataSetChanged();
                                    filter("7");
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
        });
        AppController.getInstance().addToRequestQueue(request);
    }
    private void filter(String i){
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.N) {
            List<tblItem>  filters = list.stream()
                    .filter(p -> p.ITEM_CATEGORY.equalsIgnoreCase(i)).collect(Collectors.toList());
            filter.clear();
            filter.addAll(filters);
        }

        adapter.notifyDataSetChanged();
    }

    @Click
    public void cartImg(){
        startActivity(new Intent(this,Cart_.class));
    }
    @Click
    public void back(){
        finish();
    }
}
