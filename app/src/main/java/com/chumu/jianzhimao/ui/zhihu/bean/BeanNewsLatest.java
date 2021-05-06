package com.chumu.jianzhimao.ui.zhihu.bean;

import java.util.List;

public class BeanNewsLatest {

    /**
     * date : 20190517
     * stories : [{"title":"七尺中锋用两记三分逆转战局，如何评价 NBA 东部决赛第一场？","ga_prefix":"051709","images":["https://pic4.zhimg.com/v2-e57ac6ae75ea41867b52c25ca9131a0f.jpg"],"multipic":true,"type":0,"id":9711481},{"images":["https://pic4.zhimg.com/v2-c0cc4498b47f594db385261275c8410f.jpg"],"type":0,"id":9711169,"ga_prefix":"051708","title":"给我一节永远有电的五号电池，还你一个乌托邦"},{"images":["https://pic3.zhimg.com/v2-287e142eebab12dd1a2862fc1db35db6.jpg"],"type":0,"id":9711416,"ga_prefix":"051707","title":"有哪些 Windows 95/98 时代的事物，新生代无法理解？"},{"images":["https://pic2.zhimg.com/v2-6762f6020e710415deda65bd53690409.jpg"],"type":0,"id":9711405,"ga_prefix":"051706","title":"瞎扯 · 如何正确地吐槽"}]
     * top_stories : [{"image":"https://pic4.zhimg.com/v2-55372b9c7f948910485e183f65c18657.jpg","type":0,"id":9711384,"ga_prefix":"051520","title":"《请回答 1988》，是一部无法被翻拍的剧"},{"image":"https://pic2.zhimg.com/v2-50ac5623604175a2a75f8a1e46ea2d41.jpg","type":0,"id":9711366,"ga_prefix":"051607","title":"蜂蜜、海洛因和强力枇杷露：人类止咳简史"},{"image":"https://pic1.zhimg.com/v2-8a856523a64639d66381d225b37b0068.jpg","type":0,"id":9711358,"ga_prefix":"051507","title":"覆巢之下，人到中年"},{"image":"https://pic1.zhimg.com/v2-c1e1de027720dd46c885d0fdfb8ce58c.jpg","type":0,"id":9711160,"ga_prefix":"051508","title":"你是喜欢唐诗还是宋词？"},{"image":"https://pic2.zhimg.com/v2-e3baa40e9762616bf0cc8122c3426a75.jpg","type":0,"id":9711286,"ga_prefix":"051407","title":"马云真的老糊涂了，昏招迭出吗？"}]
     */

    private String date;
    private List<StoriesBean> stories;
    private List<TopStoriesBean> top_stories;

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public List<StoriesBean> getStories() {
        return stories;
    }

    public void setStories(List<StoriesBean> stories) {
        this.stories = stories;
    }

    public List<TopStoriesBean> getTop_stories() {
        return top_stories;
    }

    public void setTop_stories(List<TopStoriesBean> top_stories) {
        this.top_stories = top_stories;
    }

    public static class StoriesBean {
        /**
         * title : 七尺中锋用两记三分逆转战局，如何评价 NBA 东部决赛第一场？
         * ga_prefix : 051709
         * images : ["https://pic4.zhimg.com/v2-e57ac6ae75ea41867b52c25ca9131a0f.jpg"]
         * multipic : true
         * type : 0
         * id : 9711481
         */

        private String title;
        private String ga_prefix;
        private boolean multipic;
        private int type;
        private int id;
        private List<String> images;

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getGa_prefix() {
            return ga_prefix;
        }

        public void setGa_prefix(String ga_prefix) {
            this.ga_prefix = ga_prefix;
        }

        public boolean isMultipic() {
            return multipic;
        }

        public void setMultipic(boolean multipic) {
            this.multipic = multipic;
        }

        public int getType() {
            return type;
        }

        public void setType(int type) {
            this.type = type;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public List<String> getImages() {
            return images;
        }

        public void setImages(List<String> images) {
            this.images = images;
        }
    }

    public static class TopStoriesBean {
        /**
         * image : https://pic4.zhimg.com/v2-55372b9c7f948910485e183f65c18657.jpg
         * type : 0
         * id : 9711384
         * ga_prefix : 051520
         * title : 《请回答 1988》，是一部无法被翻拍的剧
         */

        private String image;
        private int type;
        private int id;
        private String ga_prefix;
        private String title;

        public String getImage() {
            return image;
        }

        public void setImage(String image) {
            this.image = image;
        }

        public int getType() {
            return type;
        }

        public void setType(int type) {
            this.type = type;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getGa_prefix() {
            return ga_prefix;
        }

        public void setGa_prefix(String ga_prefix) {
            this.ga_prefix = ga_prefix;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }
    }
}
