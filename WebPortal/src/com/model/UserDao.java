package com.model;

import com.common.util.DateUtils;
import com.common.util.StringUtils;
import com.jfinal.plugin.activerecord.Db;
import com.jfinal.plugin.activerecord.IBean;
import com.jfinal.plugin.activerecord.Page;
import com.model.bean.UserBasicInfo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by xg on 16/11/14.
 */
public class UserDao extends UserBasicInfo<UserDao> implements IBean {

    private static final long serialVersionUID = -5436299664296094413L;
    public static final UserDao userDao = new UserDao();


    /**
     * 获取用户列表
     *
     * @param username
     * @param password
     * @return
     */
    public List<UserDao> getUser(String username, String password) {
        List<UserDao> userDaoList = new ArrayList<UserDao>();
        String sql = "select * from gov_user where username=?  and  password=? ";
        if (username != null && password != null) {
            userDaoList = userDao.find(sql, new Object[]{username, password});
        }
        return userDaoList;
    }

    /**
     * 获取具体用户信息
     *
     * @param username
     * @param password
     * @return
     */
    public List<UserDao> getUserByUsernameAndPassword(String username, String password) {
        List<UserDao> userDaoList = new ArrayList<UserDao>();
        String sql = "select * from gov_user where username=?  and  password=? ";
        if (username != null && password != null) {
            userDaoList = userDao.find(sql, new Object[]{username, password});
        }
        return userDaoList;
    }

    public Map<String,Object> getUserList(int pageNum, int pageSize) {
        Map<String,Object> returnData = new HashMap<String,Object>();
        Page<UserDao> userDaoPage = userDao.paginate(pageNum, pageSize, "select *", "from gov_user");
            List<Map<String,Object>> list = new ArrayList<Map<String, Object>>();
            returnData.put("pageNumber", userDaoPage.getPageNumber());
            returnData.put("pageSize", userDaoPage.getPageSize());
            returnData.put("totalRow", userDaoPage.getTotalRow());
            returnData.put("totalPage", userDaoPage.getTotalPage());
            returnData.put("firstPage", userDaoPage.isFirstPage());
            returnData.put("lastPage", userDaoPage.isLastPage());
            for(int i = 0 ; i < userDaoPage.getList().size(); i ++ ){
                Map<String,Object>  returnMap = new HashMap();
                returnMap.put("id",userDaoPage.getList().get(i).get("id"));
                returnMap.put("username",userDaoPage.getList().get(i).get("username"));
                returnMap.put("password",userDaoPage.getList().get(i).get("password"));
                returnMap.put("createDate",userDaoPage.getList().get(i).get("create_date"));
                returnMap.put("lastUpdateDate",userDaoPage.getList().get(i).get("last_update_date"));
                returnMap.put("remark",userDaoPage.getList().get(i).get("remark"));
                list.add(returnMap);
            }
            returnData.put("list",list);
        return returnData;
    }

    /**
     * 校验用户是否已经存在
     *
     * @param username
     * @return
     */
    public int findOneRecord(String username) {
        int count = 0;
        String sql = "select * from gov_user where username=?";
        List<UserDao> lists = userDao.find(sql, new Object[]{username});
        if (lists != null && lists.size() > 0) {
            count = lists.size();
        }
        return count;
    }

    public void addUser(Map<String,Object> map) {
        UserDao user = new UserDao();
        if(!StringUtils.isEmpty(map.get("username"))){
            user.set("username", map.get("username").toString());
        }
        if(!StringUtils.isEmpty(map.get("password"))){
            user.set("password", map.get("password").toString());
        }
        if(!StringUtils.isEmpty(map.get("remark"))){
            user.set("remark", map.get("remark").toString());
        }
        user.set("version",1);
        user.set("create_date", DateUtils.currentDatetime());
        user.save();
    }

    public int updateUser(Map<String,Object> map) {
        String id = map.get("id").toString();
        String sql = "update gov_user set username=?,password=?,last_update_date=?,remark=?,version=? where id=?";
        String username = "";
        if(!StringUtils.isEmpty(map.get("username"))){
            username = map.get("username").toString();
        }
        String password = "";
        if(!StringUtils.isEmpty(map.get("password"))){
            password = map.get("password").toString();
        }
        int version = 1;
        if(!StringUtils.isEmpty(map.get("version"))){
            version = Integer.parseInt(map.get("version").toString())+1;
        }
        String lastUpdateDate = DateUtils.currentDatetime();
        String remark = "";
        if(!StringUtils.isEmpty(map.get("remark"))){
            remark = map.get("remark").toString();
        }
        return  Db.update(sql, username, password,lastUpdateDate,remark,version,id);
    }

}
