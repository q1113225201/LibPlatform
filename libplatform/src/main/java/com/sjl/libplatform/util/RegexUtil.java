package com.sjl.libplatform.util;

import android.text.TextUtils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 常用正则工具类
 *
 * @author 林zero
 * @date 2019/1/16
 */
public class RegexUtil {
    /**
     * 简单判断手机号
     *
     * @param input
     * @return
     */
    public static boolean isMobileSimple(CharSequence input) {
        return isMatch("^[1]\\d{10}$", input);
    }

    /**
     * 是否Mac地址
     *
     * @param input
     * @return
     */
    public static boolean isMac(CharSequence input) {
        return isMatch("^[0-9a-fA-F]{12}$", input);
    }

    /**
     * 是否邮箱
     *
     * @param input
     * @return
     */
    public static boolean isEmail(CharSequence input) {
        return isMatch("^[a-zA-Z0-9_.-]+@[a-zA-Z0-9-]+(\\.[a-zA-Z0-9-]+)*\\.[a-zA-Z0-9]{2,6}$", input);
    }

    /**
     * 是否身份证号
     * 15位没有X，18位最后可能是X
     *
     * @param input
     * @return
     */
    public static boolean isIDCard(CharSequence input) {
        return isMatch("(^\\d{15}$)|(^\\d{18}$)|(^\\d{17}(\\d|X|x)$)", input);
    }

    /**
     * 正则匹配
     *
     * @param regex
     * @param input
     * @return
     */
    public static boolean isMatch(String regex, CharSequence input) {
        return !TextUtils.isEmpty(input) && Pattern.matches(regex, input);
    }

    /**
     * 获取匹配数据列表
     *
     * @param regex
     * @param input
     * @return
     */
    public static List<String> getMatchs(String regex, CharSequence input) {
        if (TextUtils.isEmpty(input)) {
            return Collections.emptyList();
        } else {
            List<String> matchList = new ArrayList<>();
            Pattern pattern = Pattern.compile(regex);
            Matcher matcher = pattern.matcher(input);
            while (matcher.find()) {
                matchList.add(matcher.group());
            }
            return matchList;
        }
    }

    /**
     * 获取加密手机号
     *
     * @param input
     * @return
     */
    public static String getEncryptMobile(CharSequence input) {
        return getReplaceAll(input, "(\\d{3})\\d{4}(\\d{4})", "$1****$2");
    }

    /**
     * 获取加密身份证号
     *
     * @param input
     * @return
     */
    public static String getEncryptIDCard(CharSequence input) {
        return getReplaceAll(input, "(\\d{6})\\d{8}(\\w)", "$1****$2");
    }

    /**
     * 替换第一个匹配规则后
     *
     * @param input
     * @param regex
     * @param replacement
     * @return
     */
    public static String getReplaceFirst(CharSequence input, String regex, String replacement) {
        if (TextUtils.isEmpty(input)) {
            return "";
        }
        return Pattern.compile(regex).matcher(input).replaceFirst(replacement);
    }

    /**
     * 替换所有匹配规则后
     *
     * @param input
     * @param regex
     * @param replacement
     * @return
     */
    public static String getReplaceAll(CharSequence input, String regex, String replacement) {
        if (TextUtils.isEmpty(input)) {
            return "";
        }
        return Pattern.compile(regex).matcher(input).replaceAll(replacement);
    }
}
