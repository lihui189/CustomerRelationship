package com.example.mydownload.upload;

import android.os.Handler;
import android.os.Message;

import com.example.mydownload.callback.UploadCallback;
import com.example.mydownload.Utils.Utils;

import static com.example.mydownload.data.Consts.ERROR;
import static com.example.mydownload.data.Consts.FINISH;
import static com.example.mydownload.data.Consts.PROGRESS;
import static com.example.mydownload.data.Consts.START;

public class UploadProgressHandler {
    private UploadCallback uploadCallback;

    private Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case START:
                    uploadCallback.onStart();
                    break;
                case PROGRESS:
                    uploadCallback.onProgress(msg.arg1, msg.arg2, Utils.getPercentage(msg.arg1, msg.arg2));
                    break;
                case FINISH:
                    uploadCallback.onFinish(msg.obj.toString());
                    break;
                case ERROR:
                    uploadCallback.onError(msg.obj.toString());
                    break;
            }
        }
    };

    public UploadProgressHandler(UploadCallback uploadCallback) {
        this.uploadCallback = uploadCallback;
    }

    public Handler getHandler() {
        return mHandler;
    }
}
