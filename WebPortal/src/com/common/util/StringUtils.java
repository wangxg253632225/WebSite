package com.common.util;

import org.json.JSONArray;

import java.security.MessageDigest;
import java.util.UUID;

/**
 * 字符串操作工具
 * @author malongbo
 */
public final class StringUtils {

    /**
     * 判断字符串是否为空
     * @param str
     * @return
     */
    public static boolean isEmpty (String str) {
        if (str == null)
            return true;

        if ("".equals(str.trim()))
            return true;

        return false;
    }

    /**
     * 判断字符串是否不为空
     * @param str
     * @return
     */
    public static boolean isNotEmpty (String str) {
        return !isEmpty(str);
    }

    /**
     * 判断字符串是否为空字符串
     * @param str
     * @return
     */
    public static boolean isBlank (String str) {
        return "".equals(str.trim());
    }

    /**
     * 判断字符串是否为非空字符串
     * @param str
     * @return
     */
    public static boolean isNotBlank (String str) {
        return !isBlank(str);
    }


    /**
     * 将字符串转为boolean类型
     * @param value
     * @param defaultValue  设置默认值，默认使用false
     * @return
     */
    public static Boolean toBoolean(String value, boolean defaultValue) {
        if (isEmpty(value))
            return defaultValue;

        try {
            return Boolean.parseBoolean(value);
        } catch (Exception e) {
            return defaultValue;
        }
    }

    /**
     * 将字符串转为boolean类型
     * @param value
     * @return
     */
    public static Boolean toBoolean(String value) {
        return toBoolean(value, false);
    }

    /**
     * 将字符串转为long类型
     * @param value
     * @param defaultValue 设置默认值
     * @return
     */
    public static Long toLong(String value, Long defaultValue) {
        if (isEmpty(value))
            return defaultValue;
        try {
            return Long.parseLong(value);
        }catch (Exception e) {
            return defaultValue;
        }
    }

    /**
     * 将字符串转为int类型
     * @param value
     * @param defaultValue 设置默认值
     * @return
     */
    public static Integer toInt(String value, Integer defaultValue) {
        if (isEmpty(value))
            return defaultValue;
        try {
            return Integer.parseInt(value);
        }catch (Exception e) {
            return defaultValue;
        }
    }

    /**
     * 将字符串转为double类型
     * @param value
     * @param defaultValue
     * @return
     */
    public static Double toDouble(String value, Double defaultValue) {
        if (isEmpty(value))
            return defaultValue;
        try {
            return Double.parseDouble(value);
        }catch (Exception e) {
            return defaultValue;
        }
    }

    /**
     * 将字符串转为float类型
     * @param value
     * @param defaultValue
     * @return
     */
    public static Float toFloat(String value, Float defaultValue) {
        if (isEmpty(value))
            return defaultValue;
        try {
            return Float.parseFloat(value);
        }catch (Exception e) {
            return defaultValue;
        }
    }
    /**
     * 将数组值按英文逗号拼接成字符串
     * @param array
     * @return
     */
    public static String join(Object[] array) {
        return join(array, ",","");
    }
    
    /**
     * 将数组值按指定分隔符拼接成字符串
     * @param array
     * @param delimiter 分割符，默认使用英文逗号
     * @return
     */
    public static String join(Object[] array, String delimiter) {

        return join(array, delimiter,"");
    }

    /**
     *  将数组值按指定分隔符拼接成字符串
     *       <br></br>
     *      <b>示例</b>： <br></br>
     *      array等于new String[]{"a","b"} <br></br>
     *      delimiter等于, <br></br>
     *      surround等于' <br></br>
     *      转换结果为：'a','b'
     * @param array
     * @param delimiter 分割符，默认使用英文逗号
     * @param surround 每个值左右符号，默认无
     * @return
     */
    public static String join(Object[] array, String delimiter, String surround) {
        if (array == null)
            throw new IllegalArgumentException("Array can not be null");

        if (array.length == 0) return "";

        if (surround == null) surround = "";

        if (delimiter == null) surround = ",";

        StringBuffer buffer = new StringBuffer();

        for (Object item : array) {
           buffer.append(surround).append(item.toString()).append(surround).append(delimiter);
        }

        buffer.delete(buffer.length() - delimiter.length(), buffer.length());

        return buffer.toString();
    }

    /**
     * Encode a string using algorithm specified in web.xml and return the
     * resulting encrypted password. If exception, the plain credentials
     * string is returned
     *
     * @param password Password or other credentials to use in authenticating
     *        this username
     * @param algorithm Algorithm used to do the digest
     *
     * @return encypted password based on the algorithm.
     */
    public static String encodePassword(String password, String algorithm) {
        byte[] unencodedPassword = password.getBytes();

        MessageDigest md = null;

        try {
            // first create an instance, given the provider
            md = MessageDigest.getInstance(algorithm);
        } catch (Exception e) {
            return password;
        }

        md.reset();

        // call the update method one or more times
        // (useful when you don't know the size of your data, eg. stream)
        md.update(unencodedPassword);

        // now calculate the hash
        byte[] encodedPassword = md.digest();

        StringBuffer buf = new StringBuffer();

        for (int i = 0; i < encodedPassword.length; i++) {
            if ((encodedPassword[i] & 0xff) < 0x10) {
                buf.append("0");
            }

            buf.append(Long.toString(encodedPassword[i] & 0xff, 16));
        }

        return buf.toString();
    }

    public static String insertStr(String oldStr,String str){
        if(isEmpty(oldStr))
            return str;
        else
           return  oldStr.concat(",").concat(str);
    }

    public static boolean isEmpty(Object obj) {
        if (obj == null) {
            return true;
        }
        if ((obj instanceof String)) {
            return isEmpty((String) obj);
        }
        if ((obj instanceof String[])) {
            for (String s : (String[]) obj) {
                if (!isEmpty(s)) {
                    return false;
                }
            }
            return true;
        }
        if ((obj instanceof Long)) {
            return ((Long) obj).longValue() == 0L;
        }
        if ((obj instanceof Integer)) {
            return ((Integer) obj).intValue() == 0;
        }
        if ((obj instanceof Float)) {
            return ((Float) obj).floatValue() == 0.0F;
        }
        return false;
    }

    public static boolean isNotEmpty(Object obj){
        if(!isEmpty(obj)){
            return true ;
        }else{
            return false ;
        }
    }


    /**
     * 生成UUID
     * @return
     */
    public static String getUUID() {
        /*UUID uuid = UUID.randomUUID();
        String str = uuid.toString();
        // 去掉"-"符号
        String temp = str.substring(0, 8) + str.substring(9, 13)
                + str.substring(14, 18) + str.substring(19, 23)
                + str.substring(24);
        return temp;*/

        return UUID.randomUUID().toString().replace("-", "");
    }

    public static String parseStringToArray(String json){
        if(isNotEmpty(json)){
            try{
                StringBuffer sb=new StringBuffer();
                JSONArray jsonArray=new JSONArray(json);
                for(int i=0;i<jsonArray.length();i++){
                    sb.append(jsonArray.get(i).toString());
                    if(i!=jsonArray.length()-1)
                        sb.append(",");
                }
                return sb.toString();

            }catch (Exception e){
                throw new RuntimeException("转化数组出错");
            }
        }

        return "";
    }


//    public static void main(String args[]){
//        String str="{\"reply_list_info\":[{\"type\":\"img\",\"content\":" +
//                "\"7nH6rW8N4R0neG5Zr1jcGJTEnVVclopMJQ0OQaJ3rn0pL31A7y9glGCdfLY-UQ4h\"}," +
//                "{\"type\":\"voice\",\"content\":\"9mD97FbPUkIFP302ANG2BUNd01Cbht7ApfJtt0Q4YBataY6MGtoYXLkgxtabzNu8\"}," +
//                "{\"type\":\"video\",\"content\":\"http://mp.weixin.qq.com/mp/mp/video?__biz=MzIyNDEyMTc5Nw==&mid=503076223&sn=8269d145a48295742fc0bfd3e5395127&vid=r1305ipru4e&idx=1&scene=1#rd\"},{\"news_info\":{\"list\":[{\"cover_url\":\"http://mmbiz.qpic.cn/mmbiz/iam9aECbOTFn7308syQ9DqUOKe9vYO48YRf3wTMicJlmKjuGb4bOOAfmicWvnhwlGfPJzHzmtjIuKcYrbiatH95xxA/0?wx_fmt=jpeg\",\"show_cover\":0,\"author\":\"asdf3r23\",\"digest\":\"asdfasdfasdf\",\"content_url\":\"http://mp.weixin.qq.com/s?__biz=MzIyNDEyMTc5Nw==&mid=503076259&idx=1&sn=7fdf86e2cc49aebd17e9349c15fe2173#rd\",\"title\":\"yutut\",\"source_url\":\"\"}]},\"type\":\"news\",\"content\":\"YLcQYmN80mTJ_JbrPd6L61FMKSFWANwKaocVOejswHg\"}],\"create_time\":\"2016-05-11 11:32:26\",\"rule_name\":\"你好吗？？\",\"from_wx\":0,\"content_text\":null,\"category_type\":\"key_word\",\"is_add_friend_reply_open\":0,\"content_type\":null,\"is_autoreply_open\":0,\"keyword_list_info\":[\"罗丹\"],\"reply_mode\":\"random_one\",\"id\":9,\"app_id\":\"wxa57328c88752444a\",\"status\":false}";
//        try {
//            JSONObject jo=new JSONObject(str);
//
//            if(jo.get("id").toString().equals("1")){
//
//
//            }
//          String str1=  parseStringToArray(jo.get("keyword_list_info").toString());
//            System.out.printf(str1);
//        }catch (Exception e){
//
//        }
//    }
}
