package com.kaaylabs.thendral.bean;

import java.io.Serializable;

/**
 * Created by VVeeraperumal on 18-07-2015.
 */
public class CategoryEventsList implements Serializable {

    private long articleId;
    private int issueId;
    private int categoryId;
    private String headLine;
    private String abstractContent;
    private String archiveImageUrl;
    private String issueDate;
    private int comments;
    private String unicodecontent1;


    public long getArticleId() {
        return articleId;
    }

    public void setArticleId(long articleId) {
        this.articleId = articleId;
    }

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

    public String getHeadLine() {
        return headLine;
    }

    public void setHeadLine(String headLine) {
        this.headLine = headLine;
    }

    public String getAbstractContent() {
        return abstractContent;
    }

    public void setAbstractContent(String abstractContent) {
        this.abstractContent = abstractContent;
    }

    public String getArchiveImageUrl() {
        return archiveImageUrl;
    }

    public void setArchiveImageUrl(String archiveImageUrl) {
        this.archiveImageUrl = archiveImageUrl;
    }

    public String getIssueDate() {
        return issueDate;
    }

    public void setIssueDate(String issueDate) {
        this.issueDate = issueDate;
    }

    public int getComments() {
        return comments;
    }

    public void setComments(int comments) {
        this.comments = comments;
    }

    public String getUnicodecontent1() {
        return unicodecontent1;
    }

    public void setUnicodecontent1(String unicodecontent1) {
        this.unicodecontent1 = unicodecontent1;
    }
}
