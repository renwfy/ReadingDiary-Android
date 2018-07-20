package com.renwfy.readingdiary.model;

/**
 * Created by LSD on 2018/3/28.
 */

public class BannerEntity {

    /**
     * _id : 6
     * name : 猩球崛起3：终极之战
     * image : https://ww1.sinaimg.cn/large/828dc694gy1fkfrvey5tlj20s20ciai9.jpg
     * url : /Sciencefiction/xingqiujueqizhongjizhizhan/
     * source : jukantv
     * updated_date : 2018-03-28 10:20:29
     */

    private int _id;
    private String name;
    private String image;
    private String url;
    private String source;
    private String updated_date;

    public int get_id() {
        return _id;
    }

    public void set_id(int _id) {
        this._id = _id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getUpdated_date() {
        return updated_date;
    }

    public void setUpdated_date(String updated_date) {
        this.updated_date = updated_date;
    }
}
