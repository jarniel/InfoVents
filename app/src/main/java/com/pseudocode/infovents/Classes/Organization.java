package com.pseudocode.infovents.Classes;

/**
 * Created by Baymax on 09/10/2015.
 */
public class Organization {

    String orgName;
    String orgImage;
    String orgDesc;
    String orgOwner;
    String orgIndustry;
    String orgMembers;
    String orgEmail;
    String orgContact;
    String orgLocation;
    String dateCreated;
    String orgPublicity;
    String orgCover;

    public Organization(String orgName, String orgImage, String orgDesc, String orgOwner, String orgIndustry, String orgMembers, String orgEmail, String orgContact, String orgLocation, String dateCreated, String orgPublicity, String orgCover) {
        this.orgName = orgName;
        this.orgImage = orgImage;
        this.orgDesc = orgDesc;
        this.orgOwner = orgOwner;
        this.orgIndustry = orgIndustry;
        this.orgMembers = orgMembers;
        this.orgEmail = orgEmail;
        this.orgContact = orgContact;
        this.orgLocation = orgLocation;
        this.dateCreated = dateCreated;
        this.orgPublicity = orgPublicity;
        this.orgCover = orgCover;
    }

    public Organization() {

    }

    public String getOrgPublicity() {

        return orgPublicity;
    }

    public void setOrgPublicity(String orgPublicity) {
        this.orgPublicity = orgPublicity;
    }

    public String getOrgCover() {
        return orgCover;
    }

    public void setOrgCover(String orgCover) {
        this.orgCover = orgCover;
    }

    public String getOrgName() {
        return orgName;
    }

    public void setOrgName(String orgName) {
        this.orgName = orgName;
    }

    public String getOrgImage() {
        return orgImage;
    }

    public void setOrgImage(String orgImage) {
        this.orgImage = orgImage;
    }

    public String getOrgDesc() {
        return orgDesc;
    }

    public void setOrgDesc(String orgDesc) {
        this.orgDesc = orgDesc;
    }

    public String getOrgOwner() {
        return orgOwner;
    }

    public void setOrgOwner(String orgOwner) {
        this.orgOwner = orgOwner;
    }

    public String getOrgIndustry() {
        return orgIndustry;
    }

    public void setOrgIndustry(String orgIndustry) {
        this.orgIndustry = orgIndustry;
    }

    public String getOrgMembers() {
        return orgMembers;
    }

    public void setOrgMembers(String orgMembers) {
        this.orgMembers = orgMembers;
    }

    public String getOrgEmail() {
        return orgEmail;
    }

    public void setOrgEmail(String orgEmail) {
        this.orgEmail = orgEmail;
    }

    public String getOrgContact() {
        return orgContact;
    }

    public void setOrgContact(String orgContact) {
        this.orgContact = orgContact;
    }

    public String getOrgLocation() {
        return orgLocation;
    }

    public void setOrgLocation(String orgLocation) {
        this.orgLocation = orgLocation;
    }

    public String getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(String dateCreated) {
        this.dateCreated = dateCreated;
    }
}
