package com.controller;

import com.common.result.JsonResult;
import com.common.util.JsonMapUtils;
import com.jfinal.core.Controller;
import com.jfinal.log.Log;
import com.model.ArticleCategoryDao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by lizy_java on 2016/11/28.
 */
public class ArticleCategoryController extends Controller {
    private static Log logger = Log.getLog(ArticleCategoryController.class);

    public void getList(){
        Map<String,Object> map =  new HashMap<String,Object>();
        try{
            map = JsonMapUtils.getRequestObject(this.getRequest());
        }catch (Exception e){
            renderJson(new JsonResult("参数的参数有误", null, "1", null, null));
        }

        if(getPara("type") == null){
            renderJson(new JsonResult("请传入类型", null, "1", null, null));
        }
        String type = getPara("type");
        List<ArticleCategoryDao> daos = ArticleCategoryDao.articelCategoryDao.getArticleCategorys(type);
        renderJson(new JsonResult("success", null, "0", daos, null));
    }

    public void addArticleCategory(){
        Map<String,Object> map =  new HashMap<String,Object>();
        try{
            map = JsonMapUtils.getRequestObject(this.getRequest());
        }catch (Exception e){
            e.printStackTrace();
            renderJson(new JsonResult("参入的参数有误", null, "1", null, null));
        }
        if(map.get("cateFlag") == null){
            renderJson(new JsonResult("请传入类型", null, "1", null, null));
        }
        if(map.get("cateName") == null){
            renderJson(new JsonResult("请传入分类名称", null, "1", null, null));
        }

        ArticleCategoryDao.articelCategoryDao.addArticleCategory(map);
        renderJson(new JsonResult("success", null, "0", null, null));
    }

    public void getDetail(){
        Long id = getParaToLong("id");
        if(id == null){
            renderJson(new JsonResult("请传入ID", null, "1", null, null));
            return;
        }
        ArticleCategoryDao resulrDao = ArticleCategoryDao.articelCategoryDao.findById(id);
        renderJson(new JsonResult("success", null, "0", resulrDao, null));
    }

    /**
     * 更新文章分类
     */
    public void updateCate(){
        Map<String,Object> map =  new HashMap<String,Object>();
        try{
            map = JsonMapUtils.getRequestObject(this.getRequest());
        }catch (Exception e){
            renderJson(new JsonResult("参入的参数有误", null, "1", null, null));
        }
        if(map.get("id") == null){
            renderJson(new JsonResult("传入分类ID为空", null, "1", null, null));
            return;
        }
        int count = ArticleCategoryDao.articelCategoryDao.updateArticleCateGory(map);
        if(count > 0){
            renderJson(new JsonResult("success", null, "0", null, null));
        } else{
            renderJson(new JsonResult("更新失败", null, "1", null, null));
        }

    }

    public void findList(){
        if(getPara("type") == null){
            renderJson(new JsonResult("请传入类型", null, "1", null, null));
        }
        String type = getPara("type");
        List<ArticleCategoryDao> daos = ArticleCategoryDao.articelCategoryDao.getArticleCategorys(type);
        renderJson(new JsonResult("success", null, "0", daos, null));
    }






}
