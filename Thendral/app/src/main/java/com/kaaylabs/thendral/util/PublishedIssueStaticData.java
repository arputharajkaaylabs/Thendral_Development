package com.kaaylabs.thendral.util;

import com.kaaylabs.thendral.bean.PublishedIssueVO;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by VVeeraperumal on 14-07-2015.
 */
public class PublishedIssueStaticData {

    private static List<PublishedIssueVO> publishedIssueVO = new ArrayList<PublishedIssueVO>();

    public static List<PublishedIssueVO> getPublishedIssueVO() {
        return publishedIssueVO;
    }

    public static void setPublishedIssueVO(List<PublishedIssueVO> publishedIssueVO) {
        PublishedIssueStaticData.publishedIssueVO = publishedIssueVO;
    }
}
