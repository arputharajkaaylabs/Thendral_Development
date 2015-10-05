package com.kaaylabs.thendral.bean;

import java.io.Serializable;

/**
 * Created by VVeeraperumal on 17-07-2015.
 */
public class CurrentPublishedIssueDetails implements Serializable {

    private static String publishIssue;
    private static String issueId;
    private static String issueName;
    private static String displayName;
    private static String coverImage;
    private static String headerImage;


    public static String getPublishIssue() {
        return publishIssue;
    }

    public static void setPublishIssue(String publishIssue) {
        CurrentPublishedIssueDetails.publishIssue = publishIssue;
    }

    public static String getIssueId() {
        return issueId;
    }

    public static void setIssueId(String issueId) {
        CurrentPublishedIssueDetails.issueId = issueId;
    }

    public static String getIssueName() {
        return issueName;
    }

    public static void setIssueName(String issueName) {
        CurrentPublishedIssueDetails.issueName = issueName;
    }

    public static String getDisplayName() {
        return displayName;
    }

    public static void setDisplayName(String displayName) {
        CurrentPublishedIssueDetails.displayName = displayName;
    }

    public static String getCoverImage() {
        return coverImage;
    }

    public static void setCoverImage(String coverImage) {
        CurrentPublishedIssueDetails.coverImage = coverImage;
    }

    public static String getHeaderImage() {
        return headerImage;
    }

    public static void setHeaderImage(String headerImage) {
        CurrentPublishedIssueDetails.headerImage = headerImage;
    }
}
