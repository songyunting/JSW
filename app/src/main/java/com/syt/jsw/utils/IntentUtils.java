package com.syt.jsw.utils;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

public class IntentUtils {

    public static void startActivity(Context context, Class<?> cls) {
        Intent intent = new Intent(context, cls);
        context.startActivity(intent);
    }

    public static void startActivity(Context context, Class<?> cls, Bundle bundle) {
        Intent intent = new Intent(context,
                cls);
        intent.putExtra("bundle", bundle);
        context.startActivity(intent);
    }


    public static void startActivity(Context context, Class<?> cls, String key, Object value) {
        Intent intent = new Intent(context,
                cls);
        String type = value.getClass().getSimpleName();

        if ("String".equals(type)) {
            intent.putExtra(key, (String) value);
        } else if ("Integer".equals(type)) {
            intent.putExtra(key, (int) value);
        } else if ("Boolean".equals(type)) {
            intent.putExtra(key, (Boolean) value);
        } else if ("Float".equals(type)) {
            intent.putExtra(key, (Float) value);
        } else if ("Long".equals(type)) {
            intent.putExtra(key, (Long) value);
        } else if ("Boolean".equals(type)) {
            intent.putExtra(key, (Boolean) value);
        }
        context.startActivity(intent);

    }


    public static Bundle getBundle(Activity context) {
        return context.getIntent().getBundleExtra("bundle");

    }


}
