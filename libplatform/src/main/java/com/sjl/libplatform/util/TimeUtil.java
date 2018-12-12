package com.sjl.libplatform.util;

import java.text.SimpleDateFormat;
import java.util.Locale;

/**
 * TimeUtil
 *
 * @author 林zero
 * @date 2018/12/12
 */
public class TimeUtil {
    /**
     * 获取默认时间格式构造器
     *
     * @return
     */
    private static SimpleDateFormat getDefaultFormat() {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault());
        return simpleDateFormat;
    }
}
