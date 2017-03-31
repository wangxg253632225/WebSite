package com.controller;

import com.common.result.JsonResult;
import com.common.util.JsonMapUtils;
import com.common.util.StringUtils;
import com.common.validator.UserValidator;
import com.exception.ServiceException;
import com.interceptor.SessionInterceptor;
import com.jfinal.aop.Before;
import com.jfinal.aop.Clear;
import com.jfinal.core.Controller;
import com.jfinal.log.Log;
import com.model.UserDao;

import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * Created by xg on 16/11/14.
 */
public class UserController extends Controller {
    private static Log logger = Log.getLog(UserController.class);

    /**用户登录*/
    @Clear(SessionInterceptor.class)
//    @Before(UserValidator.class)
    public void login() throws Exception {

        Map<String, Object> map = new HashMap<String, Object>();
        try {
            map = JsonMapUtils.getRequestObject(this.getRequest());
        } catch (Exception e) {
            e.printStackTrace();
            renderJson(new JsonResult("参入的参数有误", null, "1", null, null));
        }

        String username = (String)map.get("username");
        String password = (String)map.get("password");

        String sessionToken ;
        List<UserDao> users = UserDao.userDao.getUser(username, password);
        if (users.size() == 0) {
            throw new ServiceException("用户名不存在或密码错误");
        }else{
            sessionToken  = StringUtils.getUUID();
            setSessionAttr("sessionToken ", sessionToken);
        }
        renderJson(new JsonResult("success", null, "0", sessionToken , null));
    }

    public void getList() throws Exception {
        Map<String, Object> map = new HashMap<String, Object>();
        try {
            map = JsonMapUtils.getRequestObject(this.getRequest());
        } catch (Exception e) {
            renderJson(new JsonResult("参数的参数有误", null, "1", null, null));
            return;
        }
        int pageNum = (Integer) map.get("pageNum");/** 第几页*/
        int pageSize = (Integer) map.get("pageSize");/** 页大小*/
        Map<String, Object> retData = UserDao.userDao.getUserList(pageNum, pageSize);
        renderJson(new JsonResult("success", null, "0", retData, null));
    }


    /**
     * 新增用户
     * @throws Exception
     */
    public void addUser() throws Exception {
        Map<String,Object> map =  new HashMap<String,Object>();
        try{
            map = JsonMapUtils.getRequestObject(this.getRequest());
        }catch (Exception e){
            e.printStackTrace();
            renderJson(new JsonResult("参入的参数有误", null, "1", null, null));
        }

        if(map.get("username") == null || map.get("password") == null){
            throw new ServiceException("用户名或密码不能为空");
        } else {
            int count = UserDao.userDao.findOneRecord(map.get("username").toString()); //校验用户名是否已经存在
            if (count > 0) {
                renderJson(new JsonResult("fail", "0000", "1", null, null));
                return;
            }
            UserDao.userDao.addUser(map);
            renderJson(new JsonResult("success", null, "0", null, null));
        }
    }

    /**
     * 查看一条用户数据
     */
    public void getDetail() {
        long id = getParaToLong("id");
        if (StringUtils.isEmpty(id)) {
            throw new ServiceException("查询用户ID不能为空");
        }
        UserDao user = UserDao.userDao.findById(id);
        renderJson(new JsonResult("success", null, "0", user, null));
    }

    public void updateUser() {
        Map<String, Object> map = new HashMap<String, Object>();
        try {
            map = JsonMapUtils.getRequestObject(this.getRequest());
        } catch (Exception e) {
            e.printStackTrace();
            renderJson(new JsonResult("参入的参数有误", null, "1", null, null));
        }
        if (StringUtils.isEmpty(map.get("username"))) {
            renderJson(new JsonResult("传入用户名为空", null, "1", null, null));
        }

        int count = UserDao.userDao.updateUser(map);
        if (count > 0) {
            renderJson(new JsonResult("success", null, "0", null, null));
        } else {
            renderJson(new JsonResult("更新失败", null, "1", null, null));
        }
    }

    /**
     * 刪除用戶
     */
    public void delUserById(){
        long id = getParaToLong("id");
        if(UserDao.userDao.deleteById(id)){
            renderJson(new JsonResult("success", null, "0", null, null));
        } else {
            renderJson(new JsonResult("删除失败", null, "1", null, null));
        }
    }

    /**
     * 用户注销登录
     */
    public void loginOut(){
        HttpSession session = getSession();
        if(StringUtils.isEmpty(session.getAttribute("username"))){
            throw new ServiceException("用户不存在或未登录！");
        }else{
            session.removeAttribute("username"); /**session中移除改username用户信息删除*/
            session.invalidate();
            renderJson(new JsonResult("用户退出成功",null,"0",null,null));
        }
    }

}
