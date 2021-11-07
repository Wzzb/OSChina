package com.example.oschina.bean;

import org.itheima.recycler.bean.BasePageBean;

import java.io.Serializable;
import java.util.List;

public class BannerBean extends BasePageBean<BannerBean.ResultBean.ItemsBean> {


    @com.alibaba.fastjson.annotation.JSONField(name = "code")
    private Integer code;
    @com.alibaba.fastjson.annotation.JSONField(name = "message")
    private String message;
    @com.alibaba.fastjson.annotation.JSONField(name = "notice")
    private NoticeBean notice;
    @com.alibaba.fastjson.annotation.JSONField(name = "result")
    private ResultBean result;
    @com.alibaba.fastjson.annotation.JSONField(name = "time")
    private String time;

    @Override
    public List<ResultBean.ItemsBean> getItemDatas() {
        return result.items;
    }

    public static class NoticeBean implements Serializable {
        @com.alibaba.fastjson.annotation.JSONField(name = "softwareCount")
        private Integer softwareCount;
        @com.alibaba.fastjson.annotation.JSONField(name = "newsCount")
        private Integer newsCount;
    }

    public static class ResultBean implements Serializable {
        @com.alibaba.fastjson.annotation.JSONField(name = "nextPageToken")
        private String nextPageToken;
        @com.alibaba.fastjson.annotation.JSONField(name = "prevPageToken")
        private String prevPageToken;
        @com.alibaba.fastjson.annotation.JSONField(name = "requestCount")
        private Integer requestCount;
        @com.alibaba.fastjson.annotation.JSONField(name = "responseCount")
        private Integer responseCount;
        @com.alibaba.fastjson.annotation.JSONField(name = "totalResults")
        private Integer totalResults;
        @com.alibaba.fastjson.annotation.JSONField(name = "items")
        private List<ItemsBean> items;

        public static class ItemsBean implements Serializable {
            @com.alibaba.fastjson.annotation.JSONField(name = "detail")
            private String detail;
            @com.alibaba.fastjson.annotation.JSONField(name = "href")
            public String href;
            @com.alibaba.fastjson.annotation.JSONField(name = "id")
            private Integer id;
            @com.alibaba.fastjson.annotation.JSONField(name = "img")
            public String img;
            @com.alibaba.fastjson.annotation.JSONField(name = "name")
            public String name;
            @com.alibaba.fastjson.annotation.JSONField(name = "pubDate")
            private String pubDate;
            @com.alibaba.fastjson.annotation.JSONField(name = "type")
            private Integer type;
            @com.alibaba.fastjson.annotation.JSONField(name = "weight")
            private Integer weight;
        }
    }
}
