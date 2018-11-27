package com.example.administrator.customerrelationship;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;
import java.io.File;
import com.example.mydownload.download.DownloadManger;
import com.example.mydownload.callback.DownloadCallback;
import com.example.mydownload.Utils.Utils;
import com.example.mydownload.DUtil;
public class DownloadActivity extends AppCompatActivity {

    private TextView mTip;
    private TextView mProgress;
    private TextView mPause;
    private TextView mResume;
    private TextView mCancel;
    private TextView mRestart;
    private ProgressBar progressBar;

    private Context mContext;

    private String url;

    private DownloadManger downloadManger;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_download);

        mContext = this;

        mTip = (TextView) findViewById(R.id.tip);
        mProgress = (TextView) findViewById(R.id.progress);
        mPause = (TextView) findViewById(R.id.pause);
        mResume = (TextView) findViewById(R.id.resume);
        mCancel = (TextView) findViewById(R.id.cancel);
        mRestart = (TextView) findViewById(R.id.restart);
        progressBar = (ProgressBar) findViewById(R.id.progress_bar);

        //Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS).getAbsolutePath()

        final String name = "电信客情信息";
        url = "http://116.208.3.145:998/apk/app-debug.apk";

        downloadManger = DUtil.init(mContext)
                .url(url)
                .path(Environment.getExternalStorageDirectory() + "/DUtil/")
                .name(name + ".apk")
                .childTaskCount(3)
                .build()
                .start(new DownloadCallback() {

                    @Override
                    public void onStart(long currentSize, long totalSize, float progress) {
                        mTip.setText(name + "：准备下载中...");
                        progressBar.setProgress((int) progress);
                        mProgress.setText(Utils.formatSize(currentSize) + " / " + Utils.formatSize(totalSize) + "--------" + progress + "%");
                    }

                    @Override
                    public void onProgress(long currentSize, long totalSize, float progress) {
                        mTip.setText(name + "：下载中...");
                        progressBar.setProgress((int) progress);
                        mProgress.setText(Utils.formatSize(currentSize) + " / " + Utils.formatSize(totalSize) + "--------" + progress + "%");
                    }

                    @Override
                    public void onPause() {
                        mTip.setText(name + "：暂停中...");
                    }

                    @Override
                    public void onCancel() {
                        mTip.setText(name + "：已取消...");
                    }

                    @Override
                    public void onFinish(File file) {
                        mTip.setText(name + "：下载完成...");
                        Uri uri = Uri.fromFile(file);
                        Intent intent = new Intent(Intent.ACTION_VIEW);
                        intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
                        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        intent.setDataAndType(uri, "application/vnd.android.package-archive");
                        startActivity(intent);
                    }

                    @Override
                    public void onWait() {

                    }

                    @Override
                    public void onError(String error) {
                        mTip.setText(name + "：下载出错...");
                    }
                });

        mPause.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                downloadManger.pause(url);
            }
        });

        mResume.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                downloadManger.resume(url);
            }
        });

        mCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                downloadManger.cancel(url);
            }
        });

        mRestart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                downloadManger.restart(url);
            }
        });
    }

    @Override
    protected void onDestroy() {
        downloadManger.destroy(url);
        super.onDestroy();
    }
}
