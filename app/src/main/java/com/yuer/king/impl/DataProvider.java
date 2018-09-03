package com.yuer.king.impl;

import com.yuer.king.model.ListBean;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by yuer on 2018/7/31.
 */

public class DataProvider {

    private static final ArrayList<ListBean> listBeans;

    static  {
           listBeans = new ArrayList<>();
    }
    public static ArrayList<ListBean> getDataList(){
        for (int i = 0 ; i< 20 ;i ++){
            ListBean listBean = new ListBean();
            listBean.setName("this is the "+i+"  class dex");
            listBean.setUrl("http://7xi8d6.com1.z0.glb.clouddn.com/20171123083218_5mhRLg_sakura.gun_23_11_2017_8_32_9_312.jpeg");
            listBeans.add(listBean);
        }
        return  listBeans;
    }

  public static List<String> getDataListMainData(){
      List<String> list = new ArrayList<>();
      list.add("测试1");
      list.add("测试3");
      list.add("canvas");
      list.add("pathmeasure简单的loading");
      list.add("测试2");
      list.add("path再制造的笑脸");
      list.add("234");
      list.add("path菜单，向四周展开");
      list.add("仿新浪微博#话题#插入EditText");
      list.add("测试");
      list.add("1");
      list.add("贝塞尔曲线之侧滑菜单");
      list.add("卡卡罗特");
      list.add("高仿QQ空间打造下拉头部具变大效果");
      list.add("中国地图");
      list.add("哦也");

        return  list;
    }
    public static List<String> getDataListMainData2(){
        List<String> list = new ArrayList<>();
        list.add("测试1");
        list.add("测试2");
        list.add("测试3");
        list.add("canvas");
        list.add("pathmeasure简单的loading");
        list.add("path再制造的笑脸");
        list.add("path菜单，向四周展开");
        list.add("Android 自定义view-仿新浪微博#话题#插入EditText");
        list.add("贝塞尔曲线之侧滑菜单");
        list.add("高仿QQ空间打造下拉头部具变大效果");
        list.add("中国地图");
        list.add("测试");
        return  list;
    }
}
