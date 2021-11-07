package com.example.oschina.bean;

import org.itheima.recycler.bean.BasePageBean;

import java.util.List;

//@lombok.NoArgsConstructor
//@lombok.Data
public class NewsBean extends BasePageBean<NewsBean.ResultBean.ItemsBean> {

    //    @com.alibaba.fastjson.annotation.JSONField(name = "code")
    public Integer code;
    //    @com.alibaba.fastjson.annotation.JSONField(name = "message")
    public String message;
    //    @com.alibaba.fastjson.annotation.JSONField(name = "notice")
    public NoticeBean notice;
    //    @com.alibaba.fastjson.annotation.JSONField(name = "result")
    public ResultBean result;
    //    @com.alibaba.fastjson.annotation.JSONField(name = "time")
    public String time;

    @Override
    public List getItemDatas() {
        return result.items;
    }

    //    @lombok.NoArgsConstructor
//    @lombok.Data
    public static class NoticeBean {
        //        @com.alibaba.fastjson.annotation.JSONField(name = "like")
        private Integer like;
        //        @com.alibaba.fastjson.annotation.JSONField(name = "review")
        private Integer review;
        //        @com.alibaba.fastjson.annotation.JSONField(name = "letter")
        private Integer letter;
        //        @com.alibaba.fastjson.annotation.JSONField(name = "mention")
        private Integer mention;
        //        @com.alibaba.fastjson.annotation.JSONField(name = "fans")
        private Integer fans;
    }

    //    @lombok.NoArgsConstructor
//    @lombok.Data
    public static class ResultBean {
        //        @com.alibaba.fastjson.annotation.JSONField(name = "items")
        public List<ItemsBean> items;
        //        @com.alibaba.fastjson.annotation.JSONField(name = "nextPageToken")
        public String nextPageToken;
        //        @com.alibaba.fastjson.annotation.JSONField(name = "prevPageToken")
        public String prevPageToken;
        //        @com.alibaba.fastjson.annotation.JSONField(name = "requestCount")
        public Integer requestCount;
        //        @com.alibaba.fastjson.annotation.JSONField(name = "responseCount")
        public Integer responseCount;
        //        @com.alibaba.fastjson.annotation.JSONField(name = "totalResults")
        public Integer totalResults;

        //        @lombok.NoArgsConstructor
//        @lombok.Data
        public static class ItemsBean {
            //            @com.alibaba.fastjson.annotation.JSONField(name = "author")
            public String author;
            //            @com.alibaba.fastjson.annotation.JSONField(name = "body")
            public String body;
            //            @com.alibaba.fastjson.annotation.JSONField(name = "commentCount")
            public Integer commentCount;
            //            @com.alibaba.fastjson.annotation.JSONField(name = "href")
            public String href;
            //            @com.alibaba.fastjson.annotation.JSONField(name = "id")
            public Integer id;
            //            @com.alibaba.fastjson.annotation.JSONField(name = "pubDate")
            public String pubDate;
            //            @com.alibaba.fastjson.annotation.JSONField(name = "recommend")
            public Boolean recommend;
            //            @com.alibaba.fastjson.annotation.JSONField(name = "title")
            public String title;
            //            @com.alibaba.fastjson.annotation.JSONField(name = "type")
            public Integer type;
            //            @com.alibaba.fastjson.annotation.JSONField(name = "viewCount")
            public Integer viewCount;
        }
    }
}
