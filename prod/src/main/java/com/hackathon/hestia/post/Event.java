package com.hackathon.hestia.post;

import com.hackathon.hestia.user.Location;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Table(name="events")
public class Event {
    @Id
    @SequenceGenerator(
            name = "event_sequence",
            sequenceName = "event_sequence",
            allocationSize = 1

    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "event_sequence"
    )
    private long eventId;

    private long authorId;
    private String content;
    private Date created;
    private Date startTime;
    private Date endTime;
    private String eventName;
    private Location location;

    @ElementCollection
    @CollectionTable(name = "event_attendees", joinColumns = @JoinColumn(name = "eventId"))
    @Column(name = "userId")
    private List<Long> attendees = new ArrayList<Long>();

    public Event() {

    }

    public Event(String content, long authorId, Date startTime, Date endTime, String eventName, Location location) {
        this.content = content;
        this.authorId = authorId;
        this.startTime = startTime;
        this.endTime = endTime;
        this.eventName = eventName;
        this.created = new Date();
        this.location = location;
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public String getEventName() {
        return eventName;
    }

    public void setEventName(String eventName) {
        this.eventName = eventName;
    }

    public List<Long> getAttendees() {
        return attendees;
    }

    @Override
    public String toString() {
        return "Event{" +
                "startTime=" + startTime +
                ", endTime=" + endTime +
                ", eventName='" + eventName + '\'' +
                ", attendees=" + attendees +
                '}';
    }

    public long getAuthorId() {
        return authorId;
    }

    public void setAuthorId(long authorId) {
        this.authorId = authorId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }

    public long getEventId() { return eventId; }

    public void setEventId(long eventId) { this.eventId = eventId; }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }
}
