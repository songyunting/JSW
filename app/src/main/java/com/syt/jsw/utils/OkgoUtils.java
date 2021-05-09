package com.syt.jsw.utils;

import com.blankj.utilcode.util.LogUtils;
import com.blankj.utilcode.util.NetworkUtils;
import com.blankj.utilcode.util.StringUtils;
import com.blankj.utilcode.util.ToastUtils;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;
import com.lzy.okgo.model.HttpHeaders;
import com.lzy.okgo.model.Response;
import com.lzy.okgo.request.PostRequest;
import com.lzy.okgo.request.base.Request;
import com.syt.jsw.Interface.OnRequestResult;

import org.json.JSONObject;

import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import okhttp3.MediaType;
import okhttp3.RequestBody;

public class OkgoUtils {

    public static OkgoUtils instance;

    public static OkgoUtils getInstance() {
        if (instance == null) {
            instance = new OkgoUtils();
        }

        return instance;
    }


    /*表单*/


    /**
     * @param url         请求链接
     * @param params      请求参数
     * @param requestCode 请求代码用于区分同一个界面不同的请求
     * @param callback    请求回调
     * @describe post请求
     */


    public void post(String url, Map<String, String> params, HttpHeaders headers, int requestCode, OnRequestResult callback) {
        if (!NetworkUtils.isConnected()) {
            ToastUtils.showShort("网络异常");
            return;
        }
        if (StringUtils.isEmpty(url)) {
            LogUtils.e("请求链接不能为空");
            callback.onFailed(requestCode, "请求链接不能为空");
            return;
        }
        if (params == null) {
            params = new HashMap<>();
        }

        final int reqCode = requestCode;
        final OnRequestResult cb = callback;

        String info = "发送请求，url === " + url + "\n参数 === " + getParamString(params);
        LogUtils.i(info);

        OkGo.<String>post(url).headers(headers)
                .params(params)
                .tag(url)
                .execute(new StringCallback() {

                    @Override
                    public void onStart(Request<String, ? extends Request> request) {
                        super.onStart(request);
                        cb.onRequestStart(reqCode);
                    }

                    @Override
                    public void onSuccess(Response<String> response) {
                        String json = response.body();
                        if (JsonUtils.isJson(json)) {
                            cb.onSuccess(reqCode, json);
                        } else {
                            cb.onFailed(reqCode, "网络请求错误");
                        }
                    }

                    @Override
                    public void onError(Response<String> response) {
                        super.onError(response);
                        cb.onFailed(reqCode, response.message());
                    }

                    @Override
                    public void onFinish() {
                        super.onFinish();
                        cb.onRequestFinish(reqCode);
                    }
                });

    }


    public void post(String url, Map<String, String> params, int requestCode, OnRequestResult callback) {
        if (!NetworkUtils.isConnected()) {
            ToastUtils.showShort("网络异常");
            return;
        }
        if (StringUtils.isEmpty(url)) {
            LogUtils.e("请求链接不能为空");
            callback.onFailed(requestCode, "请求链接不能为空");
            return;
        }
        if (params == null) {
            params = new HashMap<>();
        }

        final int reqCode = requestCode;
        final OnRequestResult cb = callback;

        String info = "发送请求，url === " + url + "\n参数 === " + getParamString(params);
        LogUtils.i(info);

        OkGo.<String>post(url)
                .params(params)
                .tag(url)
                .execute(new StringCallback() {
                    @Override
                    public void onStart(Request<String, ? extends Request> request) {
                        super.onStart(request);
                        cb.onRequestStart(reqCode);
                    }

                    @Override
                    public void onSuccess(Response<String> response) {
                        String json = response.body();
                        if (JsonUtils.isJson(json)) {
                            cb.onSuccess(reqCode, json);
                        } else {
                            cb.onFailed(reqCode, "网络请求错误");
                        }
                    }

                    @Override
                    public void onError(Response<String> response) {
                        super.onError(response);
                        cb.onFailed(reqCode, response.message());
                    }

                    @Override
                    public void onFinish() {
                        super.onFinish();
                        cb.onRequestFinish(reqCode);
                    }
                });

    }


    public void post(String url, Map<String, String> params, String filekey, File file, int requestCode, OnRequestResult callback) {
        if (!NetworkUtils.isConnected()) {
            ToastUtils.showShort("网络异常");
            return;
        }
        if (StringUtils.isEmpty(url)) {
            LogUtils.e("请求链接不能为空");
            callback.onFailed(requestCode, "请求链接不能为空");
            return;
        }
        if (file == null) {
            LogUtils.e("文件不能为空");
            return;
        }
        if (params == null) {
            params = new HashMap<>();
        }

        final int reqCode = requestCode;
        final OnRequestResult cb = callback;

        String info = "发送请求，url === " + url + "\n参数 === " + getParamString(params);
        LogUtils.i(info);

        OkGo.<String>post(url)
                .params(params)
                .params(filekey, file)//指定参数
                .tag(url)
                .execute(new StringCallback() {
                    @Override
                    public void onStart(Request<String, ? extends Request> request) {
                        super.onStart(request);
                        cb.onRequestStart(reqCode);
                    }

                    @Override
                    public void onSuccess(Response<String> response) {
                        String json = response.body();
                        if (JsonUtils.isJson(json)) {
                            cb.onSuccess(reqCode, json);
                        } else {
                            cb.onFailed(reqCode, "网络请求错误");
                        }
                    }

                    @Override
                    public void onError(Response<String> response) {
                        super.onError(response);
                        cb.onFailed(reqCode, response.message());
                    }

                    @Override
                    public void onFinish() {
                        super.onFinish();
                        cb.onRequestFinish(reqCode);
                    }
                });

    }

    public void post(String url, Map<String, String> params, String filekey, List<File> files, int requestCode, OnRequestResult callback) {
        if (!NetworkUtils.isConnected()) {
            ToastUtils.showShort("网络异常");
            return;
        }
        if (StringUtils.isEmpty(url)) {
            LogUtils.e("请求链接不能为空");
            callback.onFailed(requestCode, "请求链接不能为空");
            return;
        }
        if (files == null) {
            LogUtils.e("文件不能为空");
            return;
        }
        if (params == null) {
            params = new HashMap<>();
        }

        final int reqCode = requestCode;
        final OnRequestResult cb = callback;

        String info = "发送请求，url === " + url + "\n参数 === " + getParamString(params);
        LogUtils.i(info);

        OkGo.<String>post(url)
                .params(params)
                .addFileParams(filekey, files)//指定参数
                .tag(url)
                .execute(new StringCallback() {

                    @Override
                    public void onStart(Request<String, ? extends Request> request) {
                        super.onStart(request);
                        cb.onRequestStart(reqCode);
                    }

                    @Override
                    public void onSuccess(Response<String> response) {
                        String json = response.body();
                        if (JsonUtils.isJson(json)) {
                            cb.onSuccess(reqCode, json);
                        } else {
                            cb.onFailed(reqCode, "网络请求错误");
                        }
                    }

                    @Override
                    public void onError(Response<String> response) {
                        super.onError(response);
                        cb.onFailed(reqCode, response.message());
                    }

                    @Override
                    public void onFinish() {
                        super.onFinish();
                        cb.onRequestFinish(reqCode);
                    }
                });

    }

    public void post(String url, Map<String, String> params, Map<String, List<File>> files, int requestCode, OnRequestResult callback) {
        if (!NetworkUtils.isConnected()) {
            ToastUtils.showShort("网络异常");
            return;
        }
        if (StringUtils.isEmpty(url)) {
            LogUtils.e("请求链接不能为空");
            callback.onFailed(requestCode, "请求链接不能为空");
            return;
        }
        if (files == null || files.size() == 0) {
            LogUtils.e("文件不能为空");
            return;
        }
        if (params == null) {
            params = new HashMap<>();
        }

        final int reqCode = requestCode;
        final OnRequestResult cb = callback;

        String info = "发送请求，url === " + url + "\n参数 === " + getParamString(params);
        LogUtils.i(info);

        PostRequest<String> request = OkGo.<String>post(url).params(params);

        for (String key : files.keySet()) {
            request = request.addFileParams(key, files.get(key));
        }
        request.tag(url).execute(new StringCallback() {

            @Override
            public void onStart(Request<String, ? extends Request> request) {
                super.onStart(request);
                cb.onRequestStart(reqCode);
            }

            @Override
            public void onSuccess(Response<String> response) {
                String json = response.body();
                if (JsonUtils.isJson(json)) {
                    cb.onSuccess(reqCode, json);
                } else {
                    cb.onFailed(reqCode, "网络请求错误");
                }
            }

            @Override
            public void onError(Response<String> response) {
                super.onError(response);
                cb.onFailed(reqCode, response.message());
            }

            @Override
            public void onFinish() {
                super.onFinish();
                cb.onRequestFinish(reqCode);
            }
        });

    }


    private String getParamString(Map<String, String> params) {
        String p = "";
        for (String key : params.keySet()) {
            p += key + " : " + params.get(key) + ",\n";
        }
        return p;
    }


    /**
     * @param url         请求链接
     * @param params      请求参数
     * @param requestCode 请求代码用于区分同一个界面不同的请求
     * @param callback    请求回调
     * @describe get请求
     */
    public void get(String url, Map<String, String> params, int requestCode, OnRequestResult callback) {
        if (!NetworkUtils.isConnected()) {
            ToastUtils.showShort("网络异常");
            return;
        }
        if (StringUtils.isEmpty(url)) {
            LogUtils.e("请求链接不能为空");
            callback.onFailed(requestCode, "请求链接不能为空");
            return;
        }
        if (params == null) {
            params = new HashMap<>();
        }

        final int reqCode = requestCode;
        final OnRequestResult cb = callback;

        OkGo.<String>get(url)
                .params(params)
                .tag(url)
                .execute(new StringCallback() {

                    @Override
                    public void onStart(Request<String, ? extends Request> request) {
                        super.onStart(request);
                        cb.onRequestStart(reqCode);
                    }

                    @Override
                    public void onSuccess(Response<String> response) {
                        String json = response.body();
                        if (JsonUtils.isJson(json)) {
                            cb.onSuccess(reqCode, json);
                        } else {
                            cb.onFailed(reqCode, "网络请求错误");
                        }
                    }

                    @Override
                    public void onError(Response<String> response) {
                        super.onError(response);
                        cb.onFailed(reqCode, response.message());
                    }

                    @Override
                    public void onFinish() {
                        super.onFinish();
                        cb.onRequestFinish(reqCode);
                    }
                });
    }



    /*JSON*/

    /**
     * @param url         请求链接
     * @param jsonObject      请求参数
     * @param requestCode 请求代码用于区分同一个界面不同的请求
     * @param callback    请求回调
     * @describe post请求
     */
    public void post(String url, JSONObject jsonObject, int requestCode, OnRequestResult callback) {
        if (!NetworkUtils.isConnected()) {
            ToastUtils.showShort("网络异常");
            return;
        }
        if (StringUtils.isEmpty(url)) {
            LogUtils.e("请求链接不能为空");
            callback.onFailed(requestCode, "请求链接不能为空");
            return;
        }
        if (jsonObject == null) {
             jsonObject = new JSONObject();
        }

        final int reqCode = requestCode;
        final OnRequestResult cb = callback;

        String info = "发送请求，url === " + url + "\n参数 === " + jsonObject;
        LogUtils.i(info);


        MediaType JSON = MediaType.parse("application/json; charset=utf-8");
        RequestBody body = RequestBody.create(JSON, String.valueOf(jsonObject));
        OkGo.<String>post(url)
                .upRequestBody(body)
                .execute(new StringCallback() {

                    @Override
                    public void onStart(Request<String, ? extends Request> request) {
                        super.onStart(request);
                        cb.onRequestStart(reqCode);
                    }

                    @Override
                    public void onSuccess(Response<String> response) {
                        String json = response.body();
                        if (JsonUtils.isJson(json)) {
                            cb.onSuccess(reqCode, json);
                        } else {
                            cb.onFailed(reqCode, "网络请求错误");
                        }
                    }

                    @Override
                    public void onError(Response<String> response) {
                        super.onError(response);
                        cb.onFailed(reqCode, response.message());
                    }

                    @Override
                    public void onFinish() {
                        super.onFinish();
                        cb.onRequestFinish(reqCode);
                    }
                });

    }







    public void Cancel() {
        OkGo.cancelAll(OkGo.getInstance().getOkHttpClient());
    }
}
