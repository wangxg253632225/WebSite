package com.common.util;

import net.sf.json.JSONObject;
import javax.servlet.http.HttpServletRequest;

import java.io.BufferedReader;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * Created by lizy_java on 2016/11/27.
 */
public class JsonMapUtils {

    public static Map<String,Object> getRequestObject(HttpServletRequest request) throws Exception {
        HashMap<String, Object> data = new HashMap<String, Object>();
        StringBuilder json = new StringBuilder();
        BufferedReader reader = request.getReader();
        String line = null;
        while((line = reader.readLine()) != null){
            json.append(line);
        }
        reader.close();
        JSONObject jsonObject = JSONObject.fromObject(json.toString());

        Iterator it = jsonObject.keys();
        // 遍历jsonObject数据，添加到Map对象
        while (it.hasNext())
        {
            String key = String.valueOf(it.next());
            Object value = jsonObject.get(key);
            data.put(key, value);
        }
        return data;
    }
}
