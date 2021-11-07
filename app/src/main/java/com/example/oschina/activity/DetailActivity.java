package com.example.oschina.activity;

import android.app.ActionBar;
import android.os.Bundle;
import android.text.TextUtils;
import android.webkit.WebView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.oschina.R;
import com.example.oschina.bean.NewsDetailBean;
import com.itheima.retrofitutils.ItheimaHttp;
import com.itheima.retrofitutils.Request;
import com.itheima.retrofitutils.listener.HttpResponseListener;

import butterknife.BindView;
import butterknife.ButterKnife;
import okhttp3.Headers;
import retrofit2.Call;

public class DetailActivity extends AppCompatActivity {

    @BindView(R.id.webview)
    WebView webview;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        int id = getIntent().getIntExtra("id", -1);

        String href = getIntent().getStringExtra("href");

        setContentView(R.layout.activity_detail);
        ButterKnife.bind(this);

        ActionBar actionBar = getActionBar();
        getSupportActionBar().setTitle("资讯详情");

        if (!TextUtils.isEmpty(href)) {
            webview.loadUrl(href);
        } else if (id != -1) {
            Request request = ItheimaHttp.newGetRequest("action/apiv2/news?id=" + id);
            Call call = ItheimaHttp.send(request, new HttpResponseListener<NewsDetailBean>() {
                @Override
                public void onResponse(NewsDetailBean newsDetailBean, Headers headers) {
                    System.out.println("--------" + newsDetailBean.result.href);
                    webview.loadUrl(newsDetailBean.result.href);

                }
            });
        }
    }
}
