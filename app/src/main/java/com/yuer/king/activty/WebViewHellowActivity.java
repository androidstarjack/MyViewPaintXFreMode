package com.yuer.king.activty;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.webkit.JavascriptInterface;
import android.webkit.JsPromptResult;
import android.webkit.JsResult;
import android.webkit.ValueCallback;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.Toast;

import com.yuer.king.MyApplication;
import com.yuer.king.R;
import com.yuer.king.utils.GetToast;
import com.yuer.king.utils.LogUtils;
import com.yuer.king.weight.UnfoldButton;

import java.io.BufferedReader;
import java.io.InputStreamReader;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;



  /*

        1、展示webview的activity可以另开一个进程，这样就能和我们app的主进程分开了，即使webview产生了oom崩溃等问题也不会影响到主程序，如何实现呢，其实很简单，在androidmanifest.xml的activity标签里加上android:process="packagename.web"就可以了。运行起来就会发现多了一个进程，哈哈。

        2、webview的创建也是有技巧的，最好不要在layout.xml中使用webview,可以通过一个viewgroup容器，使用代码动态往容器里addview(webview)，这样可以在onDestory()里销毁掉webview及时清理内存，另外需要注意创建webview需要使用applicationContext而不是activity的context，销毁时不再占有activity对象，这个大家应该都知道了，最后离开的时候需要及时销毁webview，onDestory()中应该先从viewgroup中remove掉webview,再调用webview.removeAllViews();webview.destory();


        1. WebViewClient.onPageFinished()。
        你永远无法确定当WebView调用这个方法的时候，网页内容是否真的加载完毕了。当前正在加载的网页产生跳转的时候这个方法可能会被多次调用，StackOverflow上有比较具体的解释（How to listen for a Webview finishing loading a URL in Android?）， 但其中列举的解决方法并不完美。所以当你的WebView需要加载各种各样的网页并且需要在页面加载完成时采取一些操作的话，可能WebChromeClient.onProgressChanged()比WebViewClient.onPageFinished()都要靠谱一些。

        2. WebView后台耗电问题。
        当你的程序调用了WebView加载网页，WebView会自己开启一些线程（？），如果你没有正确地将WebView销毁的话，这些残余的线程（？）会一直在后台运行，由此导致你的应用程序耗电量居高不下。对此我采用的处理方式比较偷懒，简单又粗暴（不建议），即在Activity.onDestroy()中直接调用System.exit(0)，使得应用程序完全被移出虚拟机，这样就不会有任何问题了。

        3. 切换WebView闪屏问题。
        如果你需要在同一个ViewGroup中来回切换不同的WebView（包含了不同的网页内容）的话，你就会发现闪屏是不可避免的。这应该是Android硬件加速的Bug，如果关闭硬件加速这种情况会好很多，但无法获得很好的浏览体验，你会感觉网页滑动的时候一卡一卡的，不跟手。

        4. 在某些手机上，Webview有视频时，activity销毁后，视频资源没有被销毁，甚至还能听到在后台播放。即便是像刚才那样各种销毁webview也无济于事，解决办法：在onDestory之前修改url为空地址。

        5.WebView硬件加速导致页面渲染闪烁问题
        关于Android硬件加速 开始于Android 3.0 (API level 11),开启硬件加速后，WebView渲染页面更加快速，拖动也更加顺滑。但有个副作用就是容易会出现页面加载白块同时界面闪烁现象。解决这个问题的方法是设置WebView暂时关闭硬件加速 代码如下：
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) {
        webview.setLayerType(View.LAYER_TYPE_SOFTWARE, null);
        }
*/
/**
 * 类功能描述：</br>
 *
 * @author 于亚豪
 * @version 1.0 </p> 修改时间：2018/8/28</br> 修改备注：</br>
 */
public class WebViewHellowActivity extends AppCompatActivity {
    private static final String TAG = "WebViewHellowActivity";
    @Bind(R.id.wb_webView)
    WebView wb_webView;
    @Bind(R.id.btn_wb_go)
    Button btn_wb_go;

    @SuppressLint("JavascriptInterface")
    @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_webviewhellow);
        ButterKnife.bind(this);
//        initWeb();

        //设置为可调用js方法*
        // view.setJavaScriptEnabled(true);
        //①调用H5中无参无返回值的方法***
        //   直接可以调用mWebView.loadUrl("JavaScript:show()");图2为H5中show（）方法。*
        //有返回值的
//        mWebView.evaluateJavascript("sum(1,2)",new ValueCallback() {
//                    @Override*
//                     public void on ReceiveValue(String value) {
//            Log.e(TAG,"onReceiveValue value=" + value);
//      }
//         });
        //  注意：  调用H5中带参数的方法***
        //    当调用H5中带参数的方法时，势必要传入一个字符串，当传入固定字符串时，用单引号括起来即可；当传入变量名时，需要用到转义符

//        String url  ="file:///android_res/raw/index.html" ;
        //wb_comment_webview.loadData(URLEncoder.encode(url, "utf-8"), "text/html",  "utf-8");
        //wb_webView.loadDataWithBaseURL(null, "file:///android_asset/helloword_android.html", "text/html", "utf-8", null);
        String url  ="file:///android_res/raw/helloword.html" ;
        getMyInitWebView(wb_webView);


        //需要设置的一些基本的东西
        wb_webView =   getMyInitWebView(wb_webView);
        wb_webView.loadUrl(url);
        //JS调用android的代码
        //添加JS接口支持
        wb_webView.addJavascriptInterface(new YuerJavaInterface(),"yuerAndroid");
        // 设置拦截js中的三种弹框
        wb_webView.setWebChromeClient(new MyWebChromeClien());
        // 监听点击的每一个url，做自己的特殊处理用
        wb_webView.setWebViewClient(new MyWebViewClient());
    }

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @OnClick({R.id.btn_wb_go,R.id.btn_wb_go2})
    public void onClick(View v){
        switch (v.getId()){
            case R.id.btn_wb_go:
                wb_webView.loadUrl("JavaScript:onMyClick(1)");
                break;
            case R.id.btn_wb_go2:
                wb_webView.evaluateJavascript("add2(1,2)",new ValueCallback() {
                    @Override
                    public void onReceiveValue(Object value) {
                        LogUtils.e(TAG,"onReceiveValue value=" + value);
                        GetToast.useString(MyApplication.context," " + value);
                    }
                });

                break;
        }
    }

    public  class YuerJavaInterface{

        @JavascriptInterface
        public void showToastAndroid(){
            GetToast.useString("我是android里面的方法，被Js调用啦~~~");
        }

        @JavascriptInterface
        public void showDialog(){
            new AlertDialog.Builder(WebViewHellowActivity.this).setMessage("我是android里面的方法，被Js调用啦~~~")
                    .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            // 当弹出框点击的时候，手动用代码给js返回确认的信息
                            dialog.dismiss();
                        }
                    }).setNegativeButton("取消", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    dialog.dismiss();
                }
            }).show();
        }

        @JavascriptInterface
        public String getLocalToken(){
            String token = "Asdadsfgajafq12nzjohvevbvaasdfbuououoqawiirfq";
            return token;
        }
    }



    /**
     * 初始化webview 允许js后要小心XSS攻击了
     *
     * @JavascriptInterface这个注解 在4.2以上修复了，但是需要注意兼容性
     */

    @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
    @SuppressLint("SetJavaScriptEnabled")
    private void initWeb() {
        WebSettings webSettings = wb_webView.getSettings();
        //
        webSettings.setJavaScriptEnabled(true);
        webSettings.setAllowFileAccessFromFileURLs(true);
        // 第一个参数里面包含了需要从js调用的方法或者反向回馈给js的方法
        // 第二个参数调用的是和js里规定的方法一样，调用function：android中的方法showToast，前缀javascript可有可无
        // js代码：
        // function showToast(toast) {
        // javascript:android.showToast(toast);
        // }
        // 在js中，android.showToast(toast);是不能被执行的，可能在html中执行会报错。因为它是js和原生app协商后定义的方法，不是js原有方法
        //打开js接口給H5调用，参数1为本地类名，参数2为别名；h5用window.别名.类名里的方法名才能调用方法里面的内容，例如：window.android.back();
        wb_webView.addJavascriptInterface(new JsInterface(), "android");
        // 设置拦截js中的三种弹框
        wb_webView.setWebChromeClient(new MyWebChromeClien());
        // 监听点击的每一个url，做自己的特殊处理用
        wb_webView.setWebViewClient(new MyWebViewClient());
        // 加载项目asset文件夹下的本地html
        wb_webView.loadUrl("file:///android_res/raw/index.html");// 加载本地的html布局文件
        // 加载 asset 文件的内容，第二种加载本地html的方法
//        String tpl = getFromAssets("h5/html/index.html");
        // native_web.loadDataWithBaseURL(null, tpl, "text/html", "utf-8",null);
    }


    public    WebView  getMyInitWebView(final WebView wb_load_hoursepersioncompete) {
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
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) {
            wb_load_hoursepersioncompete.setLayerType(View.LAYER_TYPE_SOFTWARE, null);
        }
        //https://blog.csdn.net/w15321970103/article/details/75635454
        return wb_load_hoursepersioncompete;
    }

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

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ButterKnife.unbind(this);
    }


    /*
     * 获取html文件的内容
     */
    public String getFromAssets(String fileName) {
        try {
            InputStreamReader inputReader = new InputStreamReader(getResources().getAssets().open(fileName));
            BufferedReader bufReader = new BufferedReader(inputReader);
            String line = "";
            String Result = "";
            while ((line = bufReader.readLine()) != null)
                Result += line;
            return Result;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }

    /**
     * 监听 所有点击的链接，如果拦截到我们需要的，就跳转到相对应的页面。
     */
    private class MyWebViewClient extends WebViewClient {
        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            Log.d("webview", "url===>" + url);
            try {
                // 如果url满足某种特定的格式，则进行特殊处理
                if (url.contains("http://")) {
                    view.loadUrl(url);
                } else {
                    view.loadUrl(url);
                }
                return true;
            } catch (Exception e) {
                return true;
            }
        }
    }

    /**
     * 拦截js的提示框
     */
    private class MyWebChromeClien extends WebChromeClient {
        // 提示框
        @Override
        public boolean onJsPrompt(WebView view, String url, String message, String defaultValue,
                                  final JsPromptResult result) {
            new AlertDialog.Builder(WebViewHellowActivity.this).setMessage(message)
                    .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            // 当弹出框点击的时候，手动用代码给js返回确认的信息
                            result.confirm("true");
                        }
                    }).setNegativeButton("取消", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    result.cancel();
                }
            }).show();
            // 返回false会弹出原声dialog，这里不让原声的弹出
            return true;
        }

        // 警告框
        @SuppressLint("NewApi")
        @Override
        public boolean onJsAlert(WebView view, String url, String message, final JsResult result) {
            // api 17以后才有onDismiss 接口，这里稍作处理
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
                new AlertDialog.Builder(WebViewHellowActivity.this).setMessage(message)
                        .setOnDismissListener(new DialogInterface.OnDismissListener() {

                            @Override
                            public void onDismiss(DialogInterface arg0) {
                                // TODO Auto-generated method stub
                                result.cancel();
                            }
                        }).show();
            } else {
                AlertDialog alertDialog = new AlertDialog.Builder(WebViewHellowActivity.this).setMessage(message)
                        .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                // 当弹出框点击的时候，手动用代码给js返回确认的信息
                                result.cancel();
                            }
                        }).show();
                alertDialog.setCanceledOnTouchOutside(false);
                alertDialog.setCancelable(false);
            }
            return true;
        }

        // 确认框
        @Override
        public boolean onJsConfirm(WebView view, String url, String message, final JsResult result) {
            new AlertDialog.Builder(WebViewHellowActivity.this).setMessage(message)
                    .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            // 当弹出框点击的时候，手动用代码给js返回确认的信息
                            result.confirm();
                        }
                    }).setNegativeButton("取消", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    result.cancel();
                }
            }).show();
            return true;
        }
    }

    /**
     * 对应js中的调用方法
     */
    public class JsInterface {
        //这个方法就是js中带有android.前缀的方法名
        @JavascriptInterface
        public void showToast(String toast) {
            Toast.makeText(WebViewHellowActivity.this, toast, Toast.LENGTH_SHORT).show();
            // 当显示出toast的时候，就向js反向输出一条log
            log("show toast success");
        }

        //这里是将app产生的信息回馈给js
        public void log(final String msg) {
            wb_webView.post(new Runnable() {
                @Override
                public void run() {
                    // 调用js中指向info的方法
                    // 这个方法会将信息在浏览器的控制台的info中显示出来
                    wb_webView.loadUrl("javascript:info(" + "'" + msg + "'" + ")");
                }
            });
        }
    }
}
