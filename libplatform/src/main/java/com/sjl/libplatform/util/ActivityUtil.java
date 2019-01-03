package com.sjl.libplatform.util;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;

/**
 * ActivityUtil
 *
 * @author 林zero
 * @date 2018/12/11
 */
public class ActivityUtil {

    public static void startActivityForResult(@NonNull final Fragment fragment, @NonNull final Intent intent, int requestCode) {
        startActivityForResult(fragment, intent, requestCode, null);
    }

    public static void startActivityForResult(@NonNull final Fragment fragment, @NonNull final Class<? extends Activity> clz, int requestCode) {
        startActivityForResult(fragment, clz, requestCode, null);
    }

    public static void startActivityForResult(@NonNull final Fragment fragment, @NonNull final Class<? extends Activity> clz, int requestCode, Bundle bundle) {
        Intent intent = new Intent(fragment.getContext(), clz);
        if (bundle != null) {
            intent.putExtras(bundle);
        }
        startActivityForResult(fragment, intent, requestCode, null);
    }

    public static void startActivityForResult(@NonNull final Fragment fragment, @NonNull final Intent intent, int requestCode, Bundle options) {
        fragment.startActivityForResult(intent, requestCode, options);
    }

    public static void startActivityForResult(@NonNull final Activity activity, @NonNull final Intent intent, int requestCode, int enterAnim, int exitAnim) {
        startActivityForResult(activity, intent, requestCode, getOptions(activity, enterAnim, exitAnim));
    }

    public static void startActivityForResult(@NonNull final Activity activity, @NonNull final Class<? extends Activity> clz, int requestCode, int enterAnim, int exitAnim) {
        startActivityForResult(activity, clz, requestCode, getOptions(activity, enterAnim, exitAnim));
    }

    public static void startActivityForResult(@NonNull final Activity activity, @NonNull final Class<? extends Activity> clz, int requestCode) {
        startActivityForResult(activity, clz, requestCode, null);
    }

    public static void startActivityForResult(@NonNull final Activity activity, @NonNull final Class<? extends Activity> clz, int requestCode, Bundle bundle) {
        Intent intent = new Intent(activity, clz);
        if (bundle != null) {
            intent.putExtras(bundle);
        }
        startActivityForResult(activity, intent, requestCode, null);
    }

    public static void startActivityForResult(@NonNull final Activity activity, @NonNull final Intent intent, int requestCode) {
        startActivityForResult(activity, intent, requestCode, null);
    }

    public static void startActivityForResult(@NonNull final Activity activity, @NonNull final Intent intent, int requestCode, Bundle options) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
            activity.startActivityForResult(intent, requestCode, options);
        } else {
            activity.startActivityForResult(intent, requestCode);
        }
    }

    public static void startActivity(@NonNull final Context context, @NonNull final Class<? extends Activity> clz, int enterAnim, int exitAnim) {
        Intent intent = new Intent(context, clz);
        startActivity(context, intent, enterAnim, exitAnim);
    }

    public static void startActivity(@NonNull final Context context, @NonNull final Intent intent, int enterAnim, int exitAnim) {
        startActivity(context, intent, getOptions(context, enterAnim, exitAnim));
    }

    public static void startActivity(@NonNull final Context context, @NonNull final Class<? extends Activity> clz) {
        startActivity(context, clz, null);
    }

    public static void startActivity(@NonNull final Context context, @NonNull final Class<? extends Activity> clz, Bundle bundle) {
        Intent intent = new Intent(context, clz);
        if (bundle != null) {
            intent.putExtras(bundle);
        }
        startActivity(context, intent);
    }

    public static void startActivity(@NonNull final Context context, @NonNull final Intent intent) {
        startActivity(context, intent, null);
    }

    private static Bundle getOptions(Context context, int enterAnim, int exitAnim) {
        return ActivityOptionsCompat.makeCustomAnimation(context, enterAnim, exitAnim).toBundle();
    }

    public static void startActivity(@NonNull final Context context, @NonNull final Intent intent, final Bundle options) {
        if (!(context instanceof Activity)) {
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        }
        ContextCompat.startActivity(context, intent, options);
    }
}
