package com.kaaylabs.thendral.bean;

import java.io.Serializable;

/**
 * Created by VVeeraperumal on 18-07-2015.
 */
public class EventInformationBO implements Serializable {

    private int issueId;
    private int categoryId;
    private int articleId;
    private String headLine;
    private String archiveImageUrl;




    public int getIssueId() {
        return issueId;
    }

    public void setIssueId(int issueId) {
        this.issueId = issueId;
    }

    public int getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
    }

    public int getArticleId() {
        return articleId;
    }

    public void setArticleId(int articleId) {
        this.articleId = articleId;
    }

    public String getHeadLine() {
        return headLine;
    }

    public void setHeadLine(String headLine) {
        this.headLine = headLine;
    }

    public String getArchiveImageUrl() {
        return archiveImageUrl;
    }

    public void setArchiveImageUrl(String archiveImageUrl) {
        this.archiveImageUrl = archiveImageUrl;
    }
}
