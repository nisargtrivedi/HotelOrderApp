package app.nisargapp.hotelorderapp.Activity;

import android.app.ProgressDialog;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.analytics.FirebaseAnalytics;

import java.util.ArrayList;
import java.util.List;

import app.nisargapp.hotelorderapp.DB.DataContext;
import app.nisargapp.hotelorderapp.Model.tblToppings;
import app.nisargapp.hotelorderapp.Utility.AppPreferences;

public class BaseActivity extends AppCompatActivity {
    public FirebaseAnalytics mFirebaseAnalytics;
    public AppPreferences appPreferences;
    public DataContext dataContext;
    public ProgressDialog pDialog;
    public List<tblToppings> toppings=new ArrayList<>();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Obtain the FirebaseAnalytics instance.
        mFirebaseAnalytics = FirebaseAnalytics.getInstance(this);
        appPreferences=new AppPreferences(this);
        dataContext=new DataContext(this);
        loadList();
    }

    public void showDialog(){
        pDialog = new ProgressDialog(this);
        // Showing progress dialog before making http request
        pDialog.setMessage("Loading...");
        pDialog.setCancelable(true);
        pDialog.show();
    }

    public void hidePDialog() {
        if (pDialog != null) {
            pDialog.dismiss();
            pDialog = null;
        }
    }
    public void loadList(){
        toppings.add(new tblToppings("ONION",30));
        toppings.add(new tblToppings("CAPSICUM",30));
        toppings.add(new tblToppings("PANEER",30));
        toppings.add(new tblToppings("OLIVES",30));
        toppings.add(new tblToppings("RED PEPRIKA",30));
        toppings.add(new tblToppings("CHEESE DIP",30));
        toppings.add(new tblToppings("JALEPENO DIP",30));
        toppings.add(new tblToppings("HOT AND GARLIC DIP",30));
        toppings.add(new tblToppings("PERI PERI DIP",30));
        toppings.add(new tblToppings("EXTRA CHEESE",50));
    }
}
