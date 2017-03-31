package com.model;

import com.common.util.DateUtils;
import com.jfinal.plugin.activerecord.Db;
import com.jfinal.plugin.activerecord.IBean;
import com.model.bean.FriendLink;

import java.util.List;
import java.util.Map;

/**
 * Created by wangxiaogang on 2016/11/30.
 */
public class FriendLinkDao extends FriendLink<FriendLinkDao> implements IBean {

    private static final long serialVersionUID = 4102730734321767493L;
    public static final FriendLinkDao friendDao = new FriendLinkDao();

    /**
     * 获取友情链接列表
     * @return
     */
    public List<FriendLinkDao> getLinkList() {
        String sql = "select * from gov_friendship_link ";
        return friendDao.find(sql);
    }

    public void addLink(Map<String,Object> map ){
        FriendLinkDao friend = new FriendLinkDao();
        friend.set("link_name",map.get("linkName"));
        if(map.get("linkUrl") != null && !"".equals(map.get("linkUrl"))){
            friend.set("link_url",map.get("linkUrl").toString());
        }
        if(map.get("level") != null && !"".equals(map.get("level"))){
            friend.set("level",Integer.parseInt(map.get("level").toString()));
        }
        if(map.get("remark") != null && !"".equals(map.get("remark"))){
            friend.set("remark",map.get("remark").toString());
        }
        friend.set("version",1);
        friend.set("create_date", DateUtils.currentDatetime());
        friend.save();
    }

    public FriendLinkDao findDetailById(long id){
        return friendDao.findById(id);
    }

    public int updateLink(Map<String,Object> map){
        String sql = "update gov_friendship_link set link_name=?,link_url=?,level=?,remark=?,last_update_date=?,version=? where id=?";
        String id = map.get("id").toString();
        String linkName = "";
        if(map.get("linkName") != null && !"".equals(map.get("linkName")) ){
            linkName = map.get("linkName").toString();
        }
        String linkUrl = "";
        if(map.get("linkUrl") != null && !"".equals(map.get("linkUrl"))){
            linkUrl = map.get("linkUrl").toString();
        }
        int level = 1;
        if(map.get("level") != null && !"".equals(map.get("level"))){
            level = Integer.parseInt(map.get("level").toString());
        }
        String remark = "";
        if(map.get("remark") != null  && !"".equals(map.get("remark"))){
            remark = map.get("remark").toString();
        }
        String lastUpdateDate = DateUtils.currentDatetime();
        int version = 1;
        if(map.get("version") != null  && !"".equals(map.get("version"))){
            version = Integer.parseInt(map.get("version").toString())+1;
        }
        return  Db.update(sql, linkName, linkUrl,level,remark,lastUpdateDate,version,id);
    }

    /**
     * 删除一条记录
     * @param id
     * @return
     */
    public boolean del(Long id ){
        return friendDao.deleteById(id);
    }

    /**
     * 获取友情链接列表
     * @return
     */
    public List<FriendLinkDao> list() {
        String sql = "select * from gov_friendship_link order by level asc";
        return friendDao.find(sql);
    }

}
