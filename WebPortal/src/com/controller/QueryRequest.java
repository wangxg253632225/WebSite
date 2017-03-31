package com.controller;


/**
 * Created by wangxiaogang on 2016/11/26.
 */
public class QueryRequest {
    private int type;
    private String key;
    private Paging paging;
    private Sort sort;
    public int getType() {
        return type;
    }
    public void setType(int type) {
        this.type = type;
    }
    public String getKey() {
        return key;
    }
    public void setKey(String key) {
        this.key = key;
    }
    public Paging getPaging() {
        return paging;
    }
    public void setPaging(Paging paging) {
        this.paging = paging;
    }
    public Sort getSort(){
        return sort;
    }
    public void setSort(Sort sort){
        this.sort = sort;
    }
}
