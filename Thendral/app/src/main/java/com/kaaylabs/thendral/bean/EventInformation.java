package com.kaaylabs.thendral.bean;

/**
 * Created by ARaja on 29-06-2015.
 */
public class EventInformation {
    public String eventName;
    public String eventDescription;
    public int photoId;

    public EventInformation(String eventName, String eventDescription, int photoId) {
        this.eventName = eventName;
        this.eventDescription = eventDescription;
        this.photoId = photoId;
    }


}
