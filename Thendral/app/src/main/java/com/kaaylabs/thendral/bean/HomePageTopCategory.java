package com.kaaylabs.thendral.bean;

import java.io.Serializable;

/**
 * Created by VVeeraperumal on 14-07-2015.
 */
public class HomePageTopCategory implements Serializable {

    private short issueid ;
    private int articleid;
    private short categoryid;
    private String headline;
    private String authornames;
    private String issueName;
    private String abstractcontent;
    private String thumbNailImage;
    private String dispname;
    private String categoryName;
    private String iImage;
    private String uniCodeContent;


    public short getIssueid() {
        return issueid;
    }

    public void setIssueid(short issueid) {
        this.issueid = issueid;
    }

    public int getArticleid() {
        return articleid;
    }

    public void setArticleid(int articleid) {
        this.articleid = articleid;
    }

    public short getCategoryid() {
        return categoryid;
    }

    public void setCategoryid(short categoryid) {
        this.categoryid = categoryid;
    }

    public String getHeadline() {
        return headline;
    }

    public void setHeadline(String headline) {
        this.headline = headline;
    }

    public String getAuthornames() {
        return authornames;
    }

    public void setAuthornames(String authornames) {
        this.authornames = authornames;
    }

    public String getIssueName() {
        return issueName;
    }

    public void setIssueName(String issueName) {
        this.issueName = issueName;
    }

    public String getAbstractcontent() {
        return abstractcontent;
    }

    public void setAbstractcontent(String abstractcontent) {
        this.abstractcontent = abstractcontent;
    }

    public String getThumbNailImage() {
        return thumbNailImage;
    }

    public void setThumbNailImage(String thumbNailImage) {
        this.thumbNailImage = thumbNailImage;
    }

    public String getDispname() {
        return dispname;
    }

    public void setDispname(String dispname) {
        this.dispname = dispname;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public String getiImage() {
        return iImage;
    }

    public void setiImage(String iImage) {
        this.iImage = iImage;
    }

    public String getUniCodeContent() {
        return uniCodeContent;
    }

    public void setUniCodeContent(String uniCodeContent) {
        this.uniCodeContent = uniCodeContent;
    }
}
