package com.sjl.libplatform.util;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.content.ContextCompat;

/**
 * ActivityUtil
 *
 * @author 沈建林
 * @date 2018/12/11
 */
public class ActivityUtil {

    public static void startActivity(@NonNull final Context context, @NonNull final Class<? extends Activity> clz) {
        Intent intent = new Intent(context, clz);
        startActivity(context, intent, null);
    }

    public static void startActivity(@NonNull final Context context, @NonNull final Intent intent) {
        startActivity(context, intent, null);
    }

    public static void startActivity(@NonNull final Context context, @NonNull final Intent intent, final Bundle options) {
        if (!(context instanceof Activity)) {
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        }
        ContextCompat.startActivity(context, intent, options);
    }
}
