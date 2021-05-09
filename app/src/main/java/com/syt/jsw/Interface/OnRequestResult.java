package com.syt.jsw.Interface;

public interface OnRequestResult {

    void onRequestStart(int reqCode);

    void onSuccess(int code, String json);

    void onFailed(int code, String msg);

    void onRequestFinish(int reqCode);
}
