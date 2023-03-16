package com.example.lc.floatbutton;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import java.util.Arrays;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {
  private final String TAG = this.getClass().getSimpleName();

  @BindView(R.id.webview)
  WebView webView;
  @BindView(R.id.contentView1)
  RecyclerView mRv1;
  private MyAdapter mAdapter;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);
    ButterKnife.bind(this);

//        mRv.setAdapter(mAdapter = new MyAdapter(this, this));
//        mRv.setLayoutManager(new LinearLayoutManager(this));
//
//        mAdapter.setDataList(Arrays.asList(
//                "1", "2", "3", "4", "5",
//                "6", "7", "8", "9", "10",
//                "11", "12", "13", "14", "15",
//                "16", "17", "18", "19", "20"
//        ));

    mRv1.setAdapter(mAdapter = new MyAdapter(this, this));
    mRv1.setLayoutManager(new LinearLayoutManager(this));

    mAdapter.setDataList(Arrays.asList(
        "1", "2", "3", "4", "5",
        "6", "7", "8", "9", "10",
        "11", "12", "13", "14", "15",
        "16", "17", "18", "19", "20"
    ));

    WebSettings webSettings = webView.getSettings();
    webSettings.setUseWideViewPort(true);
    webSettings.setLoadWithOverviewMode(true);
    webSettings.setCacheMode(WebSettings.LOAD_DEFAULT);
    webSettings.setDomStorageEnabled(true);
    webSettings.setBuiltInZoomControls(true);
    webSettings.setDisplayZoomControls(false);
    webSettings.setMixedContentMode(WebSettings.MIXED_CONTENT_ALWAYS_ALLOW);
    webSettings.setJavaScriptEnabled(true);
    webView.setWebViewClient(new WebViewClient() {
      @Override
      public void onPageStarted(WebView view, String url, Bitmap favicon) {
        super.onPageStarted(view, url, favicon);
        Log.e(TAG, "onPageStarted: 我开始加载玩网页了，你可以显示加载动画了");
      }

      @Override
      public void onPageFinished(WebView view, String url) {
        super.onPageFinished(view, url);
        Log.e(TAG, "onPageFinished: 加载网页结束了，你可以取消加载动画了，但图片资源未必加载完成了");
      }
    });

    webView.setWebViewClient(new WebViewClient(){
      @Override
      public boolean shouldOverrideUrlLoading(WebView view, String url) {
        if (url.equals("targetUrl")){
          Log.d(TAG, "shouldOverrideUrlLoading: 这里就需要你自己做逻辑处理了，系统并不会去加载资源");
          return true;
        } else {
          Log.d(TAG, "shouldOverrideUrlLoading: 返回false，意味着应用不做处理，交由WebView去处理");
          return false;
        }
      }
    });

    webView.loadUrl("http://www.baidu.com/");
  }

}
