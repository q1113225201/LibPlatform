package com.sjl.libplatform.util;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;

/**
 * 剪切板工具类
 *
 * @author 林zero
 * @date 2019/1/23
 */
public class ClipboardUtil {
    private static final String TEXT = "text";

    /**
     * 复制到剪切板
     *
     * @param context
     * @param text
     */
    public static void copyText(Context context, CharSequence text) {
        if (text == null) {
            return;
        }
        ClipboardManager clipboardManager = (ClipboardManager) context.getSystemService(Context.CLIPBOARD_SERVICE);
        ClipData clipData = ClipData.newPlainText(TEXT, text);
        clipboardManager.setPrimaryClip(clipData);
    }

    /**
     * 粘贴文本
     *
     * @param context
     * @return
     */
    public static CharSequence pasteText(Context context) {
        ClipboardManager clipboardManager = (ClipboardManager) context.getSystemService(Context.CLIPBOARD_SERVICE);
        ClipData clipData = clipboardManager.getPrimaryClip();
        if (clipData == null) {
            return "";
        }
        return clipData.getItemAt(0).getText();
    }
}
