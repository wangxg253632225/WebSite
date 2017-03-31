package com.controller;

import com.interceptor.SessionInterceptor;
import com.jfinal.aop.Clear;
import com.jfinal.core.Controller;

/**
 * Created by lizy_java on 2016/12/5.
 */
@Clear(SessionInterceptor.class)
public class FrontController extends Controller {

    public void index(){
        renderJsp("index.jsp");
    }

    public void category(){
        renderJsp("articleCategory.jsp");
    }

    public void article(){
        renderJsp("article.jsp");
    }

    public void general(){
        renderJsp("general.jsp");
    }

    public void manpower(){
        renderJsp("manpower.jsp");
    }

}
