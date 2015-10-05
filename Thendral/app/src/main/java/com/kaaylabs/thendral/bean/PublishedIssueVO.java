package com.kaaylabs.thendral.bean;

import java.io.Serializable;

/**
 * Created by VVeeraperumal on 14-07-2015.
 */
public class PublishedIssueVO implements Serializable {

    private String publishIssue;
    private String issueId;
    private String issueName;
    private String displayName;
    private String coverImage;
    private String headerImage;


    public String getPublishIssue() {
        return publishIssue;
    }

    public void setPublishIssue(String publishIssue) {
        this.publishIssue = publishIssue;
    }

    public String getIssueId() {
        return issueId;
    }

    public void setIssueId(String issueId) {
        this.issueId = issueId;
    }

    public String getIssueName() {
        return issueName;
    }

    public void setIssueName(String issueName) {
        this.issueName = issueName;
    }

    public String getDisplayName() {
        return displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    public String getCoverImage() {
        return coverImage;
    }

    public void setCoverImage(String coverImage) {
        this.coverImage = coverImage;
    }

    public String getHeaderImage() {
        return headerImage;
    }

    public void setHeaderImage(String headerImage) {
        this.headerImage = headerImage;
    }
}
