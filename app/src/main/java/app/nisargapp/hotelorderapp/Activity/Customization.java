package app.nisargapp.hotelorderapp.Activity;

import android.content.Intent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.ViewById;

import app.nisargapp.hotelorderapp.Adapter.CustomizeAdapter;
import app.nisargapp.hotelorderapp.Adapter.OnCustomeClick;
import app.nisargapp.hotelorderapp.Model.tblToppings;
import app.nisargapp.hotelorderapp.R;

@EActivity(R.layout.customization)
public class Customization extends BaseActivity {

    @ViewById
    ListView lvItems;

    CustomizeAdapter adapter;
    String DATA="";
    int total=0;
    @AfterViews
    public void init(){
        adapter=new CustomizeAdapter(toppings,this);
        lvItems.setAdapter(adapter);

        adapter.click(new OnCustomeClick() {
            @Override
            public void OnAdd(tblToppings cart) {
                DATA=DATA+cart.Name+"--->"+"Rs."+cart.Price+"\n";
                total=total+cart.Price;
            }
        });
    }

    @Click
    public void add(){
        Intent intent=new Intent();
        intent.putExtra("data",DATA);
        intent.putExtra("total",total+"");
        setResult(RESULT_OK,intent);
        finish();
    }

    @Override
    public void onBackPressed() {
        Intent intent=new Intent();
        intent.putExtra("data",DATA);
        intent.putExtra("total",total+"");
        setResult(RESULT_OK,intent);
        finish();
    }

    @Override
    protected void onDestroy() {
        Intent intent=new Intent();
        intent.putExtra("data",DATA);
        intent.putExtra("total",total+"");

        setResult(RESULT_OK,intent);
        finish();
        super.onDestroy();
    }
}
