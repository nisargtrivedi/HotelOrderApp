package app.nisargapp.hotelorderapp.Activity;

import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.ViewById;

import app.nisargapp.hotelorderapp.API;
import app.nisargapp.hotelorderapp.R;

@EActivity(R.layout.order_status)
public class OrderStatus extends BaseActivity {

    @ViewById
    WebView wb;

    @AfterViews
    public void init(){

        wb.setWebViewClient(new MyBrowser());
        wb.getSettings().setLoadsImagesAutomatically(true);
        wb.getSettings().setJavaScriptEnabled(true);
        wb.setScrollBarStyle(View.SCROLLBARS_INSIDE_OVERLAY);
        wb.loadUrl(API.ORDER_STATUS);

    }

    private class MyBrowser extends WebViewClient {
        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            view.loadUrl(url);
            return true;
        }
    }
}


