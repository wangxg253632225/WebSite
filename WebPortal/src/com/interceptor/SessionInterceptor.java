package com.interceptor;

import javax.servlet.http.HttpSession;

import com.common.ErrorCodeEnum;
import com.common.result.JsonResult;
import com.common.util.JsonMapUtils;
import com.jfinal.aop.Interceptor;
import com.jfinal.aop.Invocation;
import com.jfinal.core.Controller;
import com.jfinal.log.Log;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by xg on 2016/12/5.
 */
public class SessionInterceptor implements Interceptor {
    static Log logger = Log.getLog(SessionInterceptor.class);

    /**
     * 全局拦截器,用于判断该用户是否存在一个session中
     * @param inv
     */
    public void intercept(Invocation inv) {
            Controller control=inv.getController();
            String sessionToken = control.getPara("sessionToken");

            HttpSession session = control.getSession();
                if(session.getAttribute("sessionToken ")==null || session.getAttribute("sessionToken ").toString().isEmpty()){
                    control.renderJson(new JsonResult(ErrorCodeEnum.ERR_NOT_LOGIN));
                }else {
                  inv.invoke();
                }
            }

        }
