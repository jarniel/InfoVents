package com.pseudocode.infovents.Classes;

/**
 * Created by Baymax on 09/10/2015.
 */
public class Event {
    String eventName;
    String eventDesc;
    String eventOrgId;
    String eventImage;
    String eventCover;
    String eventLocation;
    String eventStatus;
    String startDate;
    String endDate;
    String eventUserId;
    String dateAdded;
    String eventPublicity;
    String eventCategory;
    String eventId;

    public Event(String eventName, String eventDesc, String eventOrgId, String eventImage, String eventCover, String eventLocation, String eventStatus, String startDate, String endDate, String eventUserId, String dateAdded, String eventPublicity, String eventCategory, String eventId) {
        this.eventName = eventName;
        this.eventDesc = eventDesc;
        this.eventOrgId = eventOrgId;
        this.eventImage = eventImage;
        this.eventCover = eventCover;
        this.eventLocation = eventLocation;
        this.eventStatus = eventStatus;
        this.startDate = startDate;
        this.endDate = endDate;
        this.eventUserId = eventUserId;
        this.dateAdded = dateAdded;
        this.eventPublicity = eventPublicity;
        this.eventCategory = eventCategory;
        this.eventId = eventId;
    }

    public String getEventName() {
        return eventName;
    }

    public String getEventPublicity() {
        return eventPublicity;
    }

    public void setEventPublicity(String eventPublicity) {
        this.eventPublicity = eventPublicity;
    }

    public void setEventName(String eventName) {
        this.eventName = eventName;
    }

    public String getEventDesc() {
        return eventDesc;
    }

    public void setEventDesc(String eventDesc) {
        this.eventDesc = eventDesc;
    }

    public String getEventOrgId() {
        return eventOrgId;
    }

    public void setEventOrgId(String eventOrgId) {
        this.eventOrgId = eventOrgId;
    }

    public String getEventImage() {
        return eventImage;
    }

    public void setEventImage(String eventImage) {
        this.eventImage = eventImage;
    }

    public String getEventCover() {
        return eventCover;
    }

    public void setEventCover(String eventCover) {
        this.eventCover = eventCover;
    }

    public String getEventLocation() {
        return eventLocation;
    }

    public void setEventLocation(String eventLocation) {
        this.eventLocation = eventLocation;
    }

    public String getEventStatus() {
        return eventStatus;
    }

    public void setEventStatus(String eventStatus) {
        this.eventStatus = eventStatus;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public String getEventUserId() {
        return eventUserId;
    }

    public void setEventUserId(String eventUserId) {
        this.eventUserId = eventUserId;
    }

    public String getDateAdded() {
        return dateAdded;
    }

    public void setDateAdded(String dateAdded) {
        this.dateAdded = dateAdded;
    }

    public String getEventCategory() {
        return eventCategory;
    }

    public void setEventCategory(String eventCategory) {
        this.eventCategory = eventCategory;
    }

    public String getEventId() {
        return eventId;
    }

    public void setEventId(String eventId) {
        this.eventId = eventId;
    }

    public Event() {

    }
}
