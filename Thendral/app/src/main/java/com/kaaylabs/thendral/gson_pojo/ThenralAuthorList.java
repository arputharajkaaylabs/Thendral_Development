package com.kaaylabs.thendral.gson_pojo;

import java.io.Serializable;

/**
 * Created by ARaja on 11-07-2015.
 */
public class ThenralAuthorList implements Serializable{
    /**
     * englishName : ?????? Ramabatran
     * authorName : ரஞ்சனி ராமபத்ரன்
     * authorDesc :
     * authorId : 510
     * isActive : Y
     */

    public String englishName;

    public String authorName;

    public String authorDesc;

    public long authorId;

    public String isActive;


    public String getEnglishName() {
        return englishName;
    }

    public void setEnglishName(String englishName) {
        this.englishName = englishName;
    }

    public String getAuthorName() {
        return authorName;
    }

    public void setAuthorName(String authorName) {
        this.authorName = authorName;
    }

    public String getAuthorDesc() {
        return authorDesc;
    }

    public void setAuthorDesc(String authorDesc) {
        this.authorDesc = authorDesc;
    }

    public long getAuthorId() {
        return authorId;
    }

    public void setAuthorId(long authorId) {
        this.authorId = authorId;
    }

    public String getIsActive() {
        return isActive;
    }

    public void setIsActive(String isActive) {
        this.isActive = isActive;
    }
}
