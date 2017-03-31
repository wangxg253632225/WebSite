package com.model;

import com.common.util.DateUtils;
import com.jfinal.plugin.activerecord.Db;
import com.jfinal.plugin.activerecord.IBean;
import com.model.bean.ArticleCategory;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * Created by lizy_java on 2016/11/28.
 */
public class ArticleCategoryDao extends ArticleCategory<ArticleCategoryDao> implements IBean {
    private static final long serialVersionUID = -5436299664296094413L;
    public static final ArticleCategoryDao articelCategoryDao = new ArticleCategoryDao();

    public void addArticleCategory(Map<String,Object> map){
        ArticleCategoryDao cateDao = new ArticleCategoryDao();
        cateDao.put("cate_name",map.get("cateName"));
        cateDao.put("level",map.get("level"));
        cateDao.put("cate_title",map.get("cateTitle"));
        cateDao.put("cate_keyword",map.get("keyWord"));
        cateDao.put("remark",map.get("remark"));
        cateDao.put("cate_flag",map.get("cateFlag"));
        cateDao.put("create_date",DateUtils.currentDatetime());
        cateDao.put("version",1);
        cateDao.save();
    }

    public  List<ArticleCategoryDao> getArticleCategorys(String type){
        String sql="select * from gov_article_category where cate_flag = '" +type+"'";
        return  articelCategoryDao.find(sql);
    }

    /**
     * 获取一条分类数据
     * @param id
     * @return
     */
    public ArticleCategoryDao findById(String id ){
        return articelCategoryDao.findById(id);
    }

    /**
     * 更新分类信息
     * @param map
     * @return
     */
    public int updateArticleCateGory(Map<String,Object> map){
        String sql = "update gov_article_category set cate_name=?,cate_title=?,cate_keyword=?,level=?,remark=?,version=? where id=?";
        String id = map.get("id").toString();
        String cateName = "";
        if(map.get("cateName") != null ){
            cateName = map.get("cateName").toString();
        }
        String cateTitle = "";
        if(map.get("cateTitle") != null ){
            cateTitle = map.get("cateTitle").toString();
        }
        String cateKeyword = "";
        if(map.get("keyWord") != null ){
            cateKeyword = map.get("keyWord").toString();
        }
        int level = 1;
        if(map.get("level") != null && !"".equals(map.get("level").toString())){
            level = Integer.parseInt(map.get("level").toString());
        }
        String remark = "";
        if(map.get("remark") != null ){
            remark = map.get("remark").toString()+1;
        }
        String version = "";
        if(map.get("version") != null ){
            version = map.get("version").toString()+1;
        }
        return  Db.update(sql, cateName, cateTitle,cateKeyword,level,remark,version,id);
    }

}
