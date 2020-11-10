package app.nisargapp.hotelorderapp;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import app.nisargapp.hotelorderapp.Activity.BaseActivity;
import app.nisargapp.hotelorderapp.Activity.OrderStatus;
import app.nisargapp.hotelorderapp.Activity.OrderStatus_;
import app.nisargapp.hotelorderapp.Adapter.CategoryAdapter;
import app.nisargapp.hotelorderapp.Model.tblCategory;
import app.nisargapp.hotelorderapp.Utility.AppController;

public class MainActivity extends BaseActivity {


    List<tblCategory> list=new ArrayList<>();
    CategoryAdapter adapter;
    ListView lvCategory;
    Button btnShow;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        lvCategory=findViewById(R.id.lvCategory);
        btnShow=findViewById(R.id.btnShow);

        adapter=new CategoryAdapter(list,this);
        lvCategory.setAdapter(adapter);
        bindCategory();
        btnShow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, OrderStatus_.class));
            }
        });
    }
    private void bindCategory(){
        showDialog();
        StringRequest  request=new StringRequest(Request.Method.GET,
                API.CATEGORY,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        hidePDialog();
                        Log.i("CATEGRORY RESPONSE",response.toString());
                        if(!response.isEmpty()){
                            try {
                                JSONObject object=new JSONObject(response);
                                if(object!=null){
                                    int code=object.optInt("code");
                                    if(code==200){
                                        JSONArray array=object.optJSONArray("category");
                                        if(array!=null && array.length()>0){
                                            for(int i=0;i<array.length();i++){
                                                tblCategory category=new tblCategory();
                                                category.CATEGORY_ID=array.optJSONObject(i).optString("cat_id");
                                                category.CATEGORY_NAME=array.optJSONObject(i).optString("name");
                                                list.add(category);
                                            }
                                        }
                                    }else{

                                    }
                                    adapter.notifyDataSetChanged();
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
}
