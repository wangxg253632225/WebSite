package com.common.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by Administrator on 2016/4/14.
 */
public class ValidateHelp {
    private static final String mobilePattern="\\b(1[3,5,7,8,9]\\d{9})\\b";
    /*验证手机号*/
    public static boolean  validateMobilePattern(String value,  boolean isCaseSensitive){
        Pattern pattern = isCaseSensitive ? Pattern.compile(mobilePattern) : Pattern.compile(mobilePattern, Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(value);
        if (!matcher.matches()){
            return false;
        }
        return true;
    }
}
