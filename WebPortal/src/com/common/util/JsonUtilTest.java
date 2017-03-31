package com.common.util;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by Administrator on 2016/4/15.
 */
public class JsonUtilTest {


    public static void jsonToObject(String json){
        try{
            String str = "{\"a\":\"b\", \"c\":\"d\"}";
            JSONObject a = new JSONObject(str);
            System.out.print(a.toString());

            System.out.print(a.get("a"));
        }catch (JSONException e){
        }

    }

}
