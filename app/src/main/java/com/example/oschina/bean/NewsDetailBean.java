package com.example.oschina.bean;

import com.alibaba.fastjson.annotation.JSONField;

import org.itheima.recycler.bean.BasePageBean;

import java.util.List;

public class NewsDetailBean extends BasePageBean<NewsDetailBean.ResultBean.AboutsBean> {

    @Override
    public List<ResultBean.AboutsBean> getItemDatas() {
        return result.abouts;
    }

    @JSONField(name = "code")
    private Integer code;
    @JSONField(name = "message")
    private String message;
    @JSONField(name = "result")
    public ResultBean result;
    @JSONField(name = "time")
    private String time;

    public static class ResultBean {
        @JSONField(name = "abouts")
        private List<AboutsBean> abouts;
        @JSONField(name = "author")
        private String author;
        @JSONField(name = "authorId")
        private Integer authorId;
        @JSONField(name = "authorPortrait")
        private String authorPortrait;
        @JSONField(name = "authorRelation")
        private Integer authorRelation;
        @JSONField(name = "body")
        public String body;
        @JSONField(name = "commentCount")
        private Integer commentCount;
        @JSONField(name = "favorite")
        private Boolean favorite;
        @JSONField(name = "href")
        public String href;
        @JSONField(name = "id")
        private Integer id;
        @JSONField(name = "pubDate")
        private String pubDate;
        @JSONField(name = "title")
        private String title;
        @JSONField(name = "viewCount")
        private Integer viewCount;

        public static class AboutsBean {
            @JSONField(name = "commentCount")
            private Integer commentCount;
            @JSONField(name = "content")
            private String content;
            @JSONField(name = "href")
            private String href;
            @JSONField(name = "id")
            private Integer id;
            @JSONField(name = "statistics")
            private StatisticsBean statistics;
            @JSONField(name = "title")
            private String title;
            @JSONField(name = "type")
            private Integer type;
            @JSONField(name = "viewCount")
            private Integer viewCount;

            public static class StatisticsBean {
                @JSONField(name = "comment")
                private Integer comment;
                @JSONField(name = "favCount")
                private Integer favCount;
                @JSONField(name = "like")
                private Integer like;
                @JSONField(name = "transmit")
                private Integer transmit;
                @JSONField(name = "view")
                private Integer view;
            }
        }
    }
}
