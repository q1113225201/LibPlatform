package com.sjl.libplatform.util;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/**
 * 时间工具类
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
    public static SimpleDateFormat getDefaultFormat() {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault());
        return simpleDateFormat;
    }

    /**
     * 时间格式化
     *
     * @param time
     * @return
     */
    public static String getTimeToString(long time) {
        return getTimeToString(new Date(time));
    }

    /**
     * 时间格式化
     *
     * @param date
     * @return
     */
    public static String getTimeToString(Date date) {
        return getDefaultFormat().format(date);
    }
}
