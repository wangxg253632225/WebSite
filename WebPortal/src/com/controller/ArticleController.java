package com.controller;

import com.common.result.JsonResult;
import com.common.util.DesEncryptionUtils;
import com.common.util.JsonMapUtils;
import com.interceptor.SessionInterceptor;
import com.jfinal.aop.Clear;
import com.jfinal.core.Controller;
import com.model.ArticleDao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutionException;

/**
 * Created by lizy_java on 2016/11/29.
 */
public class ArticleController extends Controller {

    public void add(){
        Map<String,Object> map =  new HashMap<String,Object>();
        try{
            map = JsonMapUtils.getRequestObject(this.getRequest());
            String longText = DesEncryptionUtils.toHexString(DesEncryptionUtils.encrypt((String)map.get("content")));
            map.put("content",longText);

        }catch (Exception e){
            e.printStackTrace();
            renderJson(new JsonResult("参数的参数有误", null, "1", null, null));
            return;
        }

        ArticleDao article =  ArticleDao.articleDao.add(map);
        renderJson(new JsonResult("success", null, "0", article, null));
    }


    public void getList(){
        Map<String,Object> map =  new HashMap<String,Object>();
        try{
            map = JsonMapUtils.getRequestObject(this.getRequest());
        }catch (Exception e){
            renderJson(new JsonResult("参数的参数有误", null, "1", null, null));
            return;
        }

        if(map.get("pageNum") == null){
            map.put("pageNum",1);
        }
        if(map.get("pageSize") == null){
            map.put("pageSize",10);
        }
        String type = getPara("type");
        map.put("type",type);
        Map<String,Object> resultDate = ArticleDao.articleDao.getList(map);
        renderJson(new JsonResult("success", null, "0", resultDate, null));
    }

    public void getDetail(){
        Long id = getParaToLong("id");
        if(id == null){
            renderJson(new JsonResult("请传入ID", null, "1", null, null));
            return;
        }
        ArticleDao resulrDao = ArticleDao.articleDao.getDetail(id);
        try{
            String content = DesEncryptionUtils.decrypt(resulrDao.getStr("content"));
            resulrDao.put("content",content);
        }catch (Exception e){
            renderJson(new JsonResult("解析文章内容出错", null, "1", null, null));
            return;
        }

        renderJson(new JsonResult("success", null, "0", resulrDao, null));
    }

    public void update(){
        Map<String,Object> map =  new HashMap<String,Object>();
        try{
            map = JsonMapUtils.getRequestObject(this.getRequest());
            String longText = DesEncryptionUtils.toHexString(DesEncryptionUtils.encrypt((String)map.get("content")));
            map.put("content",longText);

        }catch (Exception e){
            renderJson(new JsonResult("参数的参数有误", null, "1", null, null));
            return;
        }

        if(map.get("id") == null){
            renderJson(new JsonResult("请参入id", null, "1", null, null));
            return;
        }

        if(map.get("name") == null){
            renderJson(new JsonResult("请参入名称", null, "1", null, null));
            return;
        }

        int count = ArticleDao.articleDao.update(map);
        if(count > 0){
            renderJson(new JsonResult("success", null, "0", null, null));
        } else{
            renderJson(new JsonResult("更新失败", null, "1", null, null));
        }
    }

    public void delete(){
        Long id = getParaToLong("id");
        if(id == null){
            renderJson(new JsonResult("请参入ID", null, "1", null, null));
            return;
        }

        boolean isDelete =ArticleDao.articleDao.deleteById(id);

        if(isDelete){
            renderJson(new JsonResult("success", null, "0", null, null));
        }else{
            renderJson(new JsonResult("删除失败", null, "1", null, null));
        }
    }

    public void deleteIds(){
        Map<String,Object> map =  new HashMap<String,Object>();
        try{
            map = JsonMapUtils.getRequestObject(this.getRequest());
        }catch (Exception e){
            renderJson(new JsonResult("参数的参数有误", null, "1", null, null));
            return;
        }

        if(map.get("ids") == null){
            renderJson(new JsonResult("请参入集合ID", null, "1", null, null));
            return;
        }
        String ids = (String)map.get("ids");

        boolean isDelete = ArticleDao.articleDao.deleteByIds(ids);

        if(isDelete){
            renderJson(new JsonResult("success", null, "0", null, null));
        }else{
            renderJson(new JsonResult("删除失败", null, "1", null, null));
        }
    }

    @Clear(SessionInterceptor.class)
    public void list(){
        Map<String,Object> map =  new HashMap<String,Object>();
        try{
            map = JsonMapUtils.getRequestObject(this.getRequest());
        }catch (Exception e){
            renderJson(new JsonResult("参数的参数有误", null, "1", null, null));
            return;
        }

        if(map.get("pageNum") == null){
            map.put("pageNum",1);
        }
        if(map.get("pageSize") == null){
            map.put("pageSize",10);
        }
        String type = getPara("type");
        map.put("type",type);
        List<Map<String,Object>> resultDate = ArticleDao.articleDao.list(map);
        renderJson(new JsonResult("success", null, "0", resultDate, null));
    }

    @Clear(SessionInterceptor.class)
    public void articleList(){
        Map<String,Object> map =  new HashMap<String,Object>();
        try{
            map = JsonMapUtils.getRequestObject(this.getRequest());
        }catch (Exception e){
            renderJson(new JsonResult("参数的参数有误", null, "1", null, null));
            return;
        }

        if(map.get("pageNum") == null){
            map.put("pageNum",1);
        }
        if(map.get("pageSize") == null){
            map.put("pageSize",10);
        }
        String type = getPara("type");
        map.put("type",type);
        Map<String,Object> resultDate = ArticleDao.articleDao.articleList(map);
        renderJson(new JsonResult("success", null, "0", resultDate, null));
    }

    @Clear(SessionInterceptor.class)
    public void detail(){
        Long id = getParaToLong("id");
        Map<String,Object> resultDate = ArticleDao.articleDao.detail(id);
        ArticleDao current = (ArticleDao)resultDate.get("current");
        try{
            String content = DesEncryptionUtils.decrypt((String) current.get("content"));
            current.put("content",content);
        }catch (Exception e){
            renderJson(new JsonResult("解析文章内容出错", null, "1", null, null));
            return;
        }
        resultDate.put("current",current);
        renderJson(new JsonResult("success", null, "0", resultDate, null));
    }

    public void findList(){
        Map<String,Object> map =  new HashMap<String,Object>();
        try{
            map = JsonMapUtils.getRequestObject(this.getRequest());
        }catch (Exception e){
            renderJson(new JsonResult("参数的参数有误", null, "1", null, null));
            return;
        }

        if(map.get("pageNum") == null){
            map.put("pageNum",1);
        }
        if(map.get("pageSize") == null){
            map.put("pageSize",10);
        }

//        map.put("pageNum",getParaToInt("pageNum"));
//        map.put("pageSize",getParaToInt("pageSize"));
//        if(map.get("pageNum") == null){
//            map.put("pageNum",1);
//        }
//        if(map.get("pageSize") == null){
//            map.put("pageSize",10);
//        }
//        map.put("type",getPara("type"));
//        map.put("cate_id",getPara("cate_id"));

        Map<String,Object> resultDate = ArticleDao.articleDao.findArticleList(map);
        renderJson(new JsonResult("success", null, "0", resultDate, null));
    }
}
