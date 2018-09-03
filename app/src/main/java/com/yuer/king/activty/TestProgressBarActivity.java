package com.yuer.king.activty;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;

import com.yuer.king.R;
import com.yuer.king.model.ListBean;
import com.yuer.king.weight.ProgressCostomerView;
import com.yuer.king.weight.ProgressCostomerView2;

import java.lang.ref.WeakReference;
import java.util.ArrayDeque;

public class TestProgressBarActivity extends Activity implements View.OnClickListener {

    private ProgressCostomerView2 mCustomProgressBar2;
    private ProgressCostomerView mCustomProgressBar;
    private float mProgress;
    private int mStateType;
    private DownloadHandler mDownloadHandler;

    private static final int STATE_DEFAULT = 101;
    private static final int STATE_DOWNLOADING = 102;
    private static final int STATE_PAUSE = 103;
    private static final int STATE_DOWNLOAD_FINISH = 104;

    private static class DownloadHandler extends Handler {

        private WeakReference<Context> reference;

        DownloadHandler(Context context) {
            reference = new WeakReference<>(context);
        }

        @Override
        public void handleMessage(Message msg) {
            TestProgressBarActivity downloadActivity = (TestProgressBarActivity) reference.get();
            if (downloadActivity != null) {
                switch (msg.what) {
                    case 0:
                        if (downloadActivity.mProgress < 100) {
                            downloadActivity.mProgress += 2.0;
                            downloadActivity.mCustomProgressBar.setProgress(downloadActivity.mProgress);
                            downloadActivity.mDownloadHandler.sendEmptyMessageDelayed(0, 100);
                        } else {
                            downloadActivity.mStateType = STATE_DOWNLOAD_FINISH;
                            downloadActivity.mCustomProgressBar.setState(downloadActivity.mStateType);
                        }

                        if (downloadActivity.mProgress < 100) {
                            downloadActivity.mProgress += 2.0;
                            downloadActivity.mCustomProgressBar2.setProgress(downloadActivity.mProgress);
                            downloadActivity.mDownloadHandler.sendEmptyMessageDelayed(0, 2000);
                        } else {
                            downloadActivity.mStateType = STATE_DOWNLOAD_FINISH;
                            downloadActivity.mCustomProgressBar2.setState(downloadActivity.mStateType);
                        }
                        break;

                    default:
                        break;
                }
            }
        }

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_testprogressbar);

        mDownloadHandler = new DownloadHandler(this);
        mCustomProgressBar = (ProgressCostomerView) findViewById(R.id.icon_text_progressbar);
        mCustomProgressBar.setOnClickListener(this);
        mCustomProgressBar.setState(mStateType);
        mStateType = STATE_DEFAULT;

        mCustomProgressBar2 = (ProgressCostomerView2) findViewById(R.id.icon_text_progressbar2);
        mCustomProgressBar2.setOnClickListener(this);
        mStateType = STATE_DEFAULT;
        mCustomProgressBar2.setState(mStateType);
    }

    @Override
    public void onClick(View v) {
        switch (mStateType) {
            case STATE_DEFAULT:
                mProgress = 0;
                mStateType = STATE_DOWNLOADING;
                mCustomProgressBar.setProgress(mProgress);
                mCustomProgressBar.setState(mStateType);
                mCustomProgressBar2.setProgress(mProgress);
                mCustomProgressBar2.setState(mStateType);
                mDownloadHandler.sendEmptyMessageDelayed(0, 500);
                break;

            case STATE_DOWNLOADING:
                mStateType = STATE_PAUSE;
                mCustomProgressBar.setState(mStateType);
                mCustomProgressBar2.setState(mStateType);
                mDownloadHandler.removeMessages(0);
                break;

            case STATE_PAUSE:
                mStateType = STATE_DOWNLOADING;
                mCustomProgressBar.setState(mStateType);
                mCustomProgressBar2.setState(mStateType);
                mDownloadHandler.sendEmptyMessageDelayed(0, 500);
                break;

            case STATE_DOWNLOAD_FINISH:
                mStateType = STATE_DEFAULT;
                mCustomProgressBar.setState(mStateType);
                mCustomProgressBar2.setState(mStateType);
                break;
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mDownloadHandler.removeMessages(0);
    }
    private void asyncnized(){
        ArrayDeque<ListBean> mArrayDeque = new ArrayDeque<ListBean>();
    }
}
