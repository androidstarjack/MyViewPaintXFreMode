package com.example.test_webview_demo.utils;

import android.app.Activity;
import android.os.Build;
import android.view.KeyEvent;
import android.webkit.WebSettings;
import android.webkit.WebView;


public class WebViewUtils {

    public  static WebView  getMyInitWebView(final WebView wb_load_hoursepersioncompete) {
        wb_load_hoursepersioncompete.getSettings().setJavaScriptEnabled(true);
        WebSettings wSet = wb_load_hoursepersioncompete.getSettings();

//        webSettings.setUseWideViewPort(true);
//        webSettings.setLoadWithOverviewMode(true);

        wSet.setJavaScriptCanOpenWindowsAutomatically(true);
        wSet.setAllowFileAccess(true);
        wSet.setSupportZoom(true);
        wSet.setDisplayZoomControls(false);
        wSet.setDomStorageEnabled(true);
        wSet.setDatabaseEnabled(true);
        //自适应屏幕
        //wSet.setLayoutAlgorithm(WebSettings.LayoutAlgorithm.SINGLE_COLUMN);
        wSet.setLoadWithOverviewMode(true);
        wSet.setUseWideViewPort(true);
        //设置支持缩放
        wSet.setBuiltInZoomControls(true);
        //设置支持加载图片
        wSet.setBlockNetworkImage(false);
        //设置支持缓存
        wSet.setAppCacheEnabled(true);
        //设置缓存模式为默认
        wSet.setCacheMode(WebSettings.LOAD_DEFAULT);
        //设置扩大比例的缩放
        wSet.setUseWideViewPort(true);
        //设置默认的编码格式
        wSet.setDefaultTextEncodingName("UTF-8");
        //设置文件支持
        wSet.setAllowFileAccess(true);
        wb_load_hoursepersioncompete.clearCache(true);
        //对特殊版本做一些处理
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
            wSet.setAllowUniversalAccessFromFileURLs(true);
            wSet.setAllowFileAccessFromFileURLs(true);
        }
//        // 创建WebViewClient对象
//        WebViewClient wvc = new WebViewClient() {
//            @Override
//            public boolean shouldOverrideUrlLoading(WebView view, String url) {
//                // 使用自己的WebView组件来响应Url加载事件，而不是使用默认浏览器器加载页面
//                wb_load_hoursepersioncompete.loadUrl(url);
//                // 记得消耗掉这个事件。给不知道的朋友再解释一下，Android中返回True的意思就是到此为止吧,事件就会不会冒泡传递了，我们称之为消耗掉
//                return true;
//            }
//
//            @Override
//            public void onPageStarted(WebView view, String url, Bitmap favicon) {
//                super.onPageStarted(view, url, favicon);
//            }
//
//            @Override
//            public void onPageFinished(WebView view, String url) {
//                //Toast.makeText(getApplicationContext(), "WebViewClient.onPageFinished", Toast.LENGTH_SHORT).show();
//                super.onPageFinished(view, url);
//                baseActivity.hideLoading();
//            }
//
//
//            @Override
//            public void onLoadResource(WebView view, String url) {
//                //Toast.makeText(getApplicationContext(), "WebViewClient.onLoadResource", Toast.LENGTH_SHORT).show();
//                super.onLoadResource(view, url);
//            }
//            @Override
//            public void onReceivedError(WebView view, int errorCode,String description, String failingUrl) {
//                //Toast.makeText(getApplicationContext(), "WebViewClient.onReceivedError", Toast.LENGTH_SHORT).show();
//                super.onReceivedError(view, errorCode, description, failingUrl);
//                hideLoading();
//            }
//            @Override
//            public void onScaleChanged(WebView view, float oldScale, float newScale) {
//                //Toast.makeText(getApplicationContext(), "WebViewClient.onScaleChanged", Toast.LENGTH_SHORT).show();
//                super.onScaleChanged(view, oldScale, newScale);
//            }
//        };
//        wb_load_hoursepersioncompete.setWebViewClient(wvc);
        return wb_load_hoursepersioncompete;
    }
    //    public static void loadWebUrl(BaseActivity context,final String url,WebView wb_load_hoursepersioncompete){
//        if (NetUtil.isNetworkAvailable(context)) {
//            context.showLoading();
//            wb_load_hoursepersioncompete.loadUrl(url);//"https://www.baidu.com/"
//        }else{//无网的话进行提示
//            context.showToast(context.getResources().getString(R.string.l_loading_connect_err));
//        }
//    }
    public static boolean onBack(int keyCode, KeyEvent event, WebView wb_load_hoursepersioncompete, Activity activity){
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            if (wb_load_hoursepersioncompete.canGoBack()) {
                wb_load_hoursepersioncompete.goBack(); // goBack()表示返回WebView的上一页面
                return true;
            } else {
                activity.finish();
                return true;
            }
        }
        return false;
    }
    public static void onBack(WebView wb_load_hoursepersioncompete, Activity activity){
            if (wb_load_hoursepersioncompete.canGoBack()) {
                wb_load_hoursepersioncompete.goBack(); // goBack()表示返回WebView的上一页面
            } else {
                activity.finish();
            }
    }
}
