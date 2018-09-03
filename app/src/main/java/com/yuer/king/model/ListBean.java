package com.yuer.king.model;

/**
 * Created by yuer on 2018/7/31.
 */

public class ListBean extends IBean<ListBean>{
    private String name = "";
    /**
     * createdAt : 2017-11-23T08:32:29.546Z
     * publishedAt : 2017-11-24T11:08:03.624Z
     * type : 美图
     * url : http://7xi8d6.com1.z0.glb.clouddn.com/20171123083218_5mhRLg_sakura.gun_23_11_2017_8_32_9_312.jpeg
     */

    private String createdAt;
    private String publishedAt;
    private String type;
    private String url;

    public String getName() {

        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


     ListBean parse(String obj) {
        return null;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public void setPublishedAt(String publishedAt) {
        this.publishedAt = publishedAt;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public String getPublishedAt() {
        return publishedAt;
    }

    public String getType() {
        return type;
    }

    public String getUrl() {
        return url;
    }


    @Override
    ListBean parse() {
        return null;
    }
}
