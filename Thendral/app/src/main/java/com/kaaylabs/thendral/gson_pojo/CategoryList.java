package com.kaaylabs.thendral.gson_pojo;

import java.io.Serializable;

/**
 * Created by ARaja on 12-07-2015.
 */
public class CategoryList implements Serializable{
    /**
     * catId : 1
     * categoryNameInTamil : ஆசிரியர் பக்கம்
     * createdBy : 1
     * displayOrder : 1
     * categoryNameInEnglish : Aasiriya Pakkam
     * isActive : Y
     * createdOn : 1160805474000
     */

    public int category;

    public String isActive;

    public String issueId;

    public String categoryName;



    public int getCategory() {
        return category;
    }

    public void setCategory(int category) {
        this.category = category;
    }

    public String getIsActive() {
        return isActive;
    }

    public void setIsActive(String isActive) {
        this.isActive = isActive;
    }

    public String getIssueId() {
        return issueId;
    }

    public void setIssueId(String issueId) {
        this.issueId = issueId;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }
}
