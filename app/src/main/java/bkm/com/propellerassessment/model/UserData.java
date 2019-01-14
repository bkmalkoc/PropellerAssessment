package bkm.com.propellerassessment.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

public class UserData implements Serializable {

    @SerializedName("user")
    @Expose
    private User user;
    @SerializedName("events")
    @Expose
    private List<Event> events = null;
    private final static long serialVersionUID = -435269370015737295L;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public List<Event> getEvents() {
        return events;
    }

    public void setEvents(List<Event> events) {
        this.events = events;
    }

}