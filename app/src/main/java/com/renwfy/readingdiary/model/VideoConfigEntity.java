package com.renwfy.readingdiary.model;

import java.io.Serializable;

/**
 * Created by LSD on 2018/1/4.
 */

public class VideoConfigEntity implements Serializable {

    /**
     * _id : 2
     * name : 看看屋
     * source : kankanwu
     * level : 1
     * moveType : 1
     * teleplayType : 2
     * cartoonType : 3
     * funType : 4
     * updated_date : 2018-03-30 15:45:54
     */

    private int _id;
    private String name;
    private String source;
    private int level;
    private String moveType;
    private String teleplayType;
    private String cartoonType;
    private String funType;
    private int pageSize;
    private int searchCount;
    private String image;
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

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public String getMoveType() {
        return moveType;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public void setMoveType(String moveType) {
        this.moveType = moveType;
    }

    public String getTeleplayType() {
        return teleplayType;
    }

    public void setTeleplayType(String teleplayType) {
        this.teleplayType = teleplayType;
    }

    public String getCartoonType() {
        return cartoonType;
    }

    public void setCartoonType(String cartoonType) {
        this.cartoonType = cartoonType;
    }

    public String getFunType() {
        return funType;
    }

    public void setFunType(String funType) {
        this.funType = funType;
    }

    public int getSearchCount() {
        return searchCount;
    }

    public void setSearchCount(int searchCount) {
        this.searchCount = searchCount;
    }

    public String getUpdated_date() {
        return updated_date;
    }

    public void setUpdated_date(String updated_date) {
        this.updated_date = updated_date;
    }
}
