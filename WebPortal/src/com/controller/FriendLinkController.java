package com.controller;

import com.common.result.JsonResult;
import com.common.util.JsonMapUtils;
import com.common.util.StringUtils;
import com.exception.ServiceException;
import com.interceptor.SessionInterceptor;
import com.jfinal.aop.Clear;
import com.jfinal.core.Controller;
import com.model.ArticleCategoryDao;
import com.model.FriendLinkDao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by wangxiaogang on 2016/11/30.
 */
public class FriendLinkController extends Controller {

    /**
     * 获取友情链接列表
     */
    public void getList(){
        List<FriendLinkDao> data = new ArrayList<FriendLinkDao>();
        try{
           data = FriendLinkDao.friendDao.getLinkList();
        }catch (Exception e ){
            renderJson(new JsonResult("fail", null, "0", null, null));
        }
        renderJson(new JsonResult("success", null, "0", data, null));
    }

    public void addLink(){
        Map<String,Object> map =  new HashMap<String,Object>();
        try{
            map = JsonMapUtils.getRequestObject(this.getRequest());
        }catch (Exception e){
            e.printStackTrace();
            renderJson(new JsonResult("参入的参数有误", null, "1", null, null));
        }
        if(map.get("linkName") == null){
            renderJson(new JsonResult("链接名称有误", null, "1", null, null));
        }
        if(map.get("order") == null){
            renderJson(new JsonResult("链接位置不允许为空", null, "1", null, null));
        }
        try{
            FriendLinkDao.friendDao.addLink(map);
        }catch(ServiceException e){
            renderJson(new JsonResult("链接新增失败", null, "1", null, null));
        }
        renderJson(new JsonResult("success", null, "0", null, null));
    }

    /**
     * 通过id获取单一条链接记录
     */
    public void getDetail(){
        Long id = getParaToLong("id");
        if(id == null){
            renderJson(new JsonResult("请传入ID", null, "1", null, null));
            return;
        }
        FriendLinkDao friendDao = FriendLinkDao.friendDao.findDetailById(id);
        renderJson(new JsonResult("success", null, "0", friendDao, null));
    }

    /**
     * 更新一个链接信息
     */
    public void updateLink(){
        Map<String,Object> map =  new HashMap<String,Object>();
        try{
            map = JsonMapUtils.getRequestObject(this.getRequest());
        }catch (Exception e){
            e.printStackTrace();
            renderJson(new JsonResult("参入的参数有误", null, "1", null, null));
        }
        if(map.get("linkName") == null){
            renderJson(new JsonResult("链接名称有误", null, "1", null, null));
        }
        if(map.get("order") == null){
            renderJson(new JsonResult("链接位置不允许为空", null, "1", null, null));
        }

        int count = FriendLinkDao.friendDao.updateLink(map);
        if(count > 0){
            renderJson(new JsonResult("success", null, "0", null, null));
        } else{
            renderJson(new JsonResult("更新失败", null, "1", null, null));
        }
    }

    public void del(){
        Long id = getParaToLong("id");
        if(StringUtils.isEmpty(getParaToLong("id"))){
            renderJson(new JsonResult("传入记录ID为空", null, "1", null, null));
        }
        boolean flag = FriendLinkDao.friendDao.del(id);
        if(flag){
            renderJson(new JsonResult("success", null, "0", null, null));
        }else{
            renderJson(new JsonResult("删除失败", null, "1", null, null));
        }
    }

    /**
     * 获取友情链接列表
     */
    @Clear(SessionInterceptor.class)
    public void list(){
        List<FriendLinkDao> data = new ArrayList<FriendLinkDao>();
        try{
            data = FriendLinkDao.friendDao.list();
        }catch (Exception e ){
            renderJson(new JsonResult("fail", null, "0", null, null));
        }
        renderJson(new JsonResult("success", null, "0", data, null));
    }


}
