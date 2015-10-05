package com.kaaylabs.thendral.bean;

import java.io.Serializable;
import java.util.List;

/**
 * Created by VVeeraperumal on 18-07-2015.
 */
public class ArticleDetailMainBO implements Serializable {

   /* private List<Objects[]> issueNameVal;
    private List<Objects[]> issueMonthVal;
    private List<Objects[]> issueYearVa;*/
    private List<ArticleDetailsPageBO> articleContent;
   /* private List<Objects[]> articleImages;
    private List<Objects[]> moreArticles;
    private List<Objects[]> articleComment;
    private List<Objects[]> lHMenuCategories;
    private  List<Objects[]> previousIssue;
    private List<Objects[]>  nextIssue;
    private List<Objects[]> authorName;*/


    public List<ArticleDetailsPageBO> getArticleContent() {
        return articleContent;
    }

    public void setArticleContent(List<ArticleDetailsPageBO> articleContent) {
        this.articleContent = articleContent;
    }
}
