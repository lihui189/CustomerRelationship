package com.example.administrator.customerrelationship;

/**
 * Created by Administrator on 2018\7\9 0009.
 */

public interface HttpCallbackListener {
    void onFinish(String response);
    void onError(Exception e);
}
