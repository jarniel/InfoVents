package com.pseudocode.infovents.Classes;

/**
 * Created by Baymax on 10/10/2015.
 */
public class News {
    String newsOrgId;
    String newsUserId;
    String newsName;
    String newsDescription;
    String newsDatePosted;
    String newsPublicity;
    String newsImage;
    String newsOrgName;
    String newsId;

    public News(String newsOrgId, String newsUserId, String newsName, String newsDescription, String newsDatePosted, String newsPublicity, String newsImage, String newsOrgName, String newsId) {
        this.newsOrgId = newsOrgId;
        this.newsUserId = newsUserId;
        this.newsName = newsName;
        this.newsDescription = newsDescription;
        this.newsDatePosted = newsDatePosted;
        this.newsPublicity = newsPublicity;
        this.newsImage = newsImage;
        this.newsOrgName = newsOrgName;
        this.newsId = newsId;
    }

    public String getNewsId() {
        return newsId;
    }

    public void setNewsId(String newsId) {
        this.newsId = newsId;
    }


    public String getNewsImage() {
        return newsImage;
    }

    public void setNewsImage(String newsImage) {
        this.newsImage = newsImage;
    }

    public String getNewsOrgName() {
        return newsOrgName;
    }

    public void setNewsOrgName(String newsOrgName) {
        this.newsOrgName = newsOrgName;
    }

    public String getNewsOrgId() {
        return newsOrgId;
    }

    public void setNewsOrgId(String newsOrgId) {
        this.newsOrgId = newsOrgId;
    }

    public String getNewsUserId() {
        return newsUserId;
    }

    public void setNewsUserId(String newsUserId) {
        this.newsUserId = newsUserId;
    }

    public String getNewsName() {
        return newsName;
    }

    public void setNewsName(String newsName) {
        this.newsName = newsName;
    }

    public String getNewsDescription() {
        return newsDescription;
    }

    public void setNewsDescription(String newsDescription) {
        this.newsDescription = newsDescription;
    }

    public String getNewsDatePosted() {
        return newsDatePosted;
    }

    public void setNewsDatePosted(String newsDatePosted) {
        this.newsDatePosted = newsDatePosted;
    }

    public String getNewsPublicity() {
        return newsPublicity;
    }

    public void setNewsPublicity(String newsPublicity) {
        this.newsPublicity = newsPublicity;
    }


    public News() {

    }

}
