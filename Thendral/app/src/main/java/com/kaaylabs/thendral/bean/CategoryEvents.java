package com.kaaylabs.thendral.bean;

import java.io.Serializable;
import java.util.List;

/**
 * Created by VVeeraperumal on 18-07-2015.
 */
public class CategoryEvents implements Serializable {

    private List<CategoryItem> categoryName;
    private List<CategoryEventsList> articleMaster;


    public List<CategoryItem> getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(List<CategoryItem> categoryName) {
        this.categoryName = categoryName;
    }

    public List<CategoryEventsList> getArticleMaster() {
        return articleMaster;
    }

    public void setArticleMaster(List<CategoryEventsList> articleMaster) {
        this.articleMaster = articleMaster;
    }
}
