package com.example.lc.floatbutton;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.TextView;

import java.util.Arrays;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {
  private final String TAG = this.getClass().getSimpleName();

  @BindView(R.id.textview)
  TextView textView;
  @BindView(R.id.webview)
  WebView webView;
  @BindView(R.id.contentView)
  RecyclerView mRv;
  @BindView(R.id.contentView1)
  RecyclerView mRv1;
  private MyAdapter mAdapter;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);
    ButterKnife.bind(this);
    textView.setOnClickListener(new View.OnClickListener() {

      @Override
      public void onClick(View view) {
        if (mRv.isShown()) {
          mRv.setVisibility(View.GONE);
        } else {
          mRv.setVisibility(View.VISIBLE);
        }
        if (webView.isShown()) {
          webView.setVisibility(View.GONE);
        } else {
          webView.setVisibility(View.VISIBLE);
        }
      }
    });


    mRv.setAdapter(mAdapter = new MyAdapter(this, this));
    mRv.setLayoutManager(new LinearLayoutManager(this));

    mAdapter.setDataList(Arrays.asList(
        "1", "2", "3", "4", "5",
        "6", "7", "8", "9", "10",
        "11", "12", "13", "14", "15",
        "16", "17", "18", "19", "20"
    ));

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

    webView.setWebViewClient(new WebViewClient() {
      @Override
      public boolean shouldOverrideUrlLoading(WebView view, String url) {
        if (url == null) return false;
        try {
          if (!url.startsWith("http://") && !url.startsWith("https://")) {
            Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
            startActivity(intent);
            return true;
          }
        } catch (Exception e) {//防止crash (如果手机上没有安装处理某个scheme开头的url的APP, 会导致crash)
          return true;//没有安装该app时，返回true，表示拦截自定义链接，但不跳转，避免弹出上面的错误页面
        }

        // TODO Auto-generated method stub
        //返回值是true的时候控制去WebView打开，为false调用系统浏览器或第三方浏览器
        view.loadUrl(url);
        return true;
      }
    });

    webView.loadUrl("http://www.baidu.com/");
  }

}
