package com.model;

import com.common.util.DateUtils;
import com.jfinal.plugin.activerecord.Db;
import com.jfinal.plugin.activerecord.IBean;
import com.jfinal.plugin.activerecord.Page;
import com.jfinal.plugin.activerecord.Record;
import com.model.bean.Article;

import java.util.*;

/**
 * Created by lizy_java on 2016/11/29.
 */
public class ArticleDao extends Article<ArticleDao> implements IBean {
    public static final ArticleDao articleDao = new ArticleDao();

    public ArticleDao add(Map<String,Object> data){
        ArticleDao article = new ArticleDao();
        article.put("cate_id",data.get("cate_id"));
        article.put("name",data.get("name"));
        article.put("title",data.get("title"));
        article.put("author",data.get("author"));
        article.put("content",data.get("content"));
        article.set("create_date", DateUtils.currentDatetime());
        article.save();
         return article;
    }

    public Map<String,Object> getList(Map<String,Object> data) {
        Map<String,Object> resultData = new HashMap<String,Object>();
        String selectSql = "select ga.id,ga.cate_id,ga.name,ga.author,ga.title,ga.is_top,gac.cate_name";
        StringBuffer fromSql = new StringBuffer(" from gov_article ga,gov_article_category gac ")
                .append(" where ga.cate_id = gac.id ")
                .append( "and gac.cate_flag = '"+data.get("type")+"'");
        if(data.get("cate_id") != null){
            fromSql.append(" and gac.id ="+data.get("cate_id"));
        }
        fromSql.append(" order by ga.create_date desc");
        Page<ArticleDao> articleDaos = articleDao.paginate((Integer)data.get("pageNum"),(Integer)data.get("pageSize"),selectSql,fromSql.toString());
        List<Map<String,Object>> list = new ArrayList<Map<String, Object>>();
        resultData.put("pageNumber", articleDaos.getPageNumber());
        resultData.put("pageSize", articleDaos.getPageSize());
        resultData.put("totalRow", articleDaos.getTotalRow());
        resultData.put("totalPage", articleDaos.getTotalPage());
        resultData.put("firstPage", articleDaos.isFirstPage());
        resultData.put("lastPage", articleDaos.isLastPage());
        for(int i = 0 ; i < articleDaos.getList().size(); i ++ ){
            Map<String,Object>  returnMap = new HashMap();
            returnMap.put("id",articleDaos.getList().get(i).get("id"));
            returnMap.put("cate_id",articleDaos.getList().get(i).get("cate_id"));
            returnMap.put("name",articleDaos.getList().get(i).get("name"));
            returnMap.put("author",articleDaos.getList().get(i).get("author"));
            returnMap.put("title",articleDaos.getList().get(i).get("title"));
            returnMap.put("is_top",articleDaos.getList().get(i).get("is_top"));
            returnMap.put("cate_name",articleDaos.getList().get(i).get("cate_name"));
            list.add(returnMap);
        }
        resultData.put("list",list);
        return resultData;
    }

    public Map<String,Object> articleList(Map<String,Object> data) {
        Map<String,Object> resultData = new HashMap<String,Object>();
        String selectSql = "select ga.id,ga.cate_id,ga.name,ga.author,ga.title,ga.is_top,gac.cate_name";
        StringBuffer fromSql = new StringBuffer(" from gov_article ga,gov_article_category gac ")
                .append(" where ga.cate_id = gac.id ")
                .append( "and gac.cate_flag = '"+data.get("type")+"'");
        if(data.get("cate_id") != null){
            fromSql.append(" and gac.id ="+data.get("cate_id"));
        }
        fromSql.append(" order by ga.create_date desc");
        Page<ArticleDao> articleDaos = articleDao.paginate((Integer)data.get("pageNum"),(Integer)data.get("pageSize"),selectSql,fromSql.toString());
        List<Map<String,Object>> list = new ArrayList<Map<String, Object>>();
        resultData.put("pageNumber", articleDaos.getPageNumber());
        resultData.put("pageSize", articleDaos.getPageSize());
        resultData.put("totalRow", articleDaos.getTotalRow());
        resultData.put("totalPage", articleDaos.getTotalPage());
        resultData.put("firstPage", articleDaos.isFirstPage());
        resultData.put("lastPage", articleDaos.isLastPage());
        for(int i = 0 ; i < articleDaos.getList().size(); i ++ ){
            Map<String,Object>  returnMap = new HashMap();
            returnMap.put("id",articleDaos.getList().get(i).get("id"));
            returnMap.put("cate_id",articleDaos.getList().get(i).get("cate_id"));
            returnMap.put("name",articleDaos.getList().get(i).get("name"));
            returnMap.put("author",articleDaos.getList().get(i).get("author"));
            returnMap.put("title",articleDaos.getList().get(i).get("title"));
            returnMap.put("is_top",articleDaos.getList().get(i).get("is_top"));
            returnMap.put("create_date",DateUtils.formatDatetime((Date) articleDaos.getList().get(i).get("create_date"),"yyyy-MM-dd"));
            returnMap.put("cate_name",articleDaos.getList().get(i).get("cate_name"));
            list.add(returnMap);
        }
        resultData.put("list",list);
        return resultData;
    }

    public Map<String,Object> detail(Long id){
        Map<String,Object> resultData = new HashMap<String,Object>();
        ArticleDao article = articleDao.findById(id);
        resultData.put("current",article);
        String selectSql = "select ga.id from gov_article ga,gov_article_category gac where ga.cate_id = gac.id and ga.cate_id = ( select cate_id from gov_article where id = "+id+") order by ga.create_date desc";
        List<Long> ids = Db.query(selectSql);
        for(int i=0,length=ids.size();i<length;i++){
            if(ids.get(i) == id){
                if(i == 0){
                    resultData.put("before",null);
                    if(length-1>0) {
                        resultData.put("after", articleDao.findById(ids.get(i + 1)));
                    }else{
                        resultData.put("after",null);
                    }
                }else if(i == length-1){
                    if(length-1>0) {
                        resultData.put("before", articleDao.findById(ids.get(i - 1)));
                    }else{
                        resultData.put("before",null);
                    }
                    resultData.put("after",null);
                }else{
                    resultData.put("before",articleDao.findById(ids.get(i-1)));
                    resultData.put("after",articleDao.findById(ids.get(i+1)));
                }
            }
        }
        return resultData;
    }

    public List<Map<String,Object>> list(Map<String,Object> data) {
        Map<String,Object> resultData = new HashMap<String,Object>();
        String selectSql = "select ga.id,ga.cate_id,ga.name,ga.author,ga.title,ga.is_top,ga.create_date,gac.cate_name";
        StringBuffer fromSql = new StringBuffer(" from gov_article ga,gov_article_category gac ")
                .append(" where ga.cate_id = gac.id ")
                .append( "and gac.cate_flag = '"+data.get("type")+"'");
        if(data.get("cate_id") != null){
            fromSql.append(" and gac.id ="+data.get("cate_id"));
        }
        fromSql.append(" order by ga.create_date desc ");
        Page<ArticleDao> articleDaos = articleDao.paginate((Integer)data.get("pageNum"),(Integer)data.get("pageSize"),selectSql,fromSql.toString());
        List<Map<String,Object>> list = new ArrayList<Map<String, Object>>();
        for(int i = 0 ; i < articleDaos.getList().size(); i ++ ){
            Map<String,Object>  returnMap = new HashMap();
            returnMap.put("id",articleDaos.getList().get(i).get("id"));
            returnMap.put("cate_id",articleDaos.getList().get(i).get("cate_id"));
            returnMap.put("name",articleDaos.getList().get(i).get("name"));
            returnMap.put("author",articleDaos.getList().get(i).get("author"));
            returnMap.put("title",articleDaos.getList().get(i).get("title"));
            returnMap.put("is_top",articleDaos.getList().get(i).get("is_top"));
            returnMap.put("create_date",DateUtils.formatDatetime((Date) articleDaos.getList().get(i).get("create_date"),"MM-dd"));
            returnMap.put("cate_name",articleDaos.getList().get(i).get("cate_name"));
            list.add(returnMap);
        }
        return list;
    }

    public ArticleDao getDetail(Long id){
        return articleDao.findById(id);
    }

    public int update(Map<String,Object> data){

        StringBuffer updateSql = new StringBuffer(" update gov_article set")
                .append(" cate_id = "+ data.get("cate_id")).append(",")
                .append(" name = '"+ data.get("name")).append("',")
                .append(" title = '"+ data.get("title")).append("',")
                .append(" author = '"+ data.get("author")).append("',")
                .append(" last_update_date = '"+ DateUtils.currentDatetime()).append("',")
                .append(" content = '"+ data.get("content")).append("'")
                .append(" where id = " + data.get("id"));
        return   Db.update(updateSql.toString());
    }

    public boolean deleteByIds(String ids){
        String deleteSql = "delete from gov_article where id in ("+ids+")";
        int count = Db.update(deleteSql);
        if(count > 0){
            return true;
        }else{
            return false;
        }
    }

    public Map<String,Object> findArticleList(Map<String,Object> data) {
        Map<String,Object> resultData = new HashMap<String,Object>();
        String selectSql = "select ga.id,ga.cate_id,ga.name,ga.author,ga.title,ga.is_top,gac.cate_name,ga.create_date,gac.cate_flag";
        StringBuffer fromSql = new StringBuffer(" from gov_article ga,gov_article_category gac ")
                .append(" where ga.cate_id = gac.id ")
                .append( "and gac.cate_flag = '"+data.get("type")+"'");

        if(data.get("cate_id") != null && !"null".equals(data.get("cate_id")) && data.get("cate_id") != "" && !"-1".equals(data.get("cate_id").toString()) ){
            fromSql.append(" and gac.id ="+data.get("cate_id"));
        }
        fromSql.append(" order by ga.create_date desc");
        Page<ArticleDao> articleDaos = articleDao.paginate((Integer)data.get("pageNum"),(Integer)data.get("pageSize"),selectSql,fromSql.toString());
        List<Map<String,Object>> list = new ArrayList<Map<String, Object>>();
        resultData.put("pageNumber", articleDaos.getPageNumber());
        resultData.put("pageSize", articleDaos.getPageSize());
        resultData.put("totalRow", articleDaos.getTotalRow());
        resultData.put("totalPage", articleDaos.getTotalPage());
        resultData.put("firstPage", articleDaos.isFirstPage());
        resultData.put("lastPage", articleDaos.isLastPage());
        for(int i = 0 ; i < articleDaos.getList().size(); i ++ ){
            Map<String,Object>  returnMap = new HashMap();
            returnMap.put("id",articleDaos.getList().get(i).get("id"));
            returnMap.put("cate_id",articleDaos.getList().get(i).get("cate_id"));
            returnMap.put("name",articleDaos.getList().get(i).get("name"));
            returnMap.put("author",articleDaos.getList().get(i).get("author"));
            returnMap.put("title",articleDaos.getList().get(i).get("title"));
            returnMap.put("is_top",articleDaos.getList().get(i).get("is_top"));
            returnMap.put("create_date",(articleDaos.getList().get(i).get("create_date") != null?DateUtils.formatDatetime((Date) articleDaos.getList().get(i).get("create_date"),"yyyy-MM-dd"):null));
            returnMap.put("cate_name",articleDaos.getList().get(i).get("cate_name"));
            returnMap.put("cate_flag",articleDaos.getList().get(i).get("cate_flag"));
            list.add(returnMap);
        }
        resultData.put("list",list);
        return resultData;
    }
}
