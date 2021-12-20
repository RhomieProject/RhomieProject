package com.example.rhomie.Objects;

public class Request {

    private String fromUserID;
    private String to;
    private String message;

    private boolean status;

    private String itemID;
    public Request() {
        this.fromUserID = "";
        this.to = "";
        this.message = "";
        this.status = false;

    }

    public Request(String fromUserID, String toUserID, String message, boolean status, String itemID) {
        this.fromUserID = fromUserID;
        this.to = toUserID;
        this.message = message;
        this.status = status;
        this.itemID = itemID;
    }

    public String getFromUserID() {
        return fromUserID;
    }

    public void setFromUserID(String fromUserID) {
        this.fromUserID = fromUserID;
    }

    public String getToUserID() {
        return to;
    }

    public void setToUserID(String toUser) {
        this.to = toUser;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public String getItemID() {
        return itemID;
    }

    public void setItemID(String itemID) {
        this.itemID = itemID;
    }
}
