package com.hackathon.hestia.post;

import com.hackathon.hestia.user.Location;
import com.hackathon.hestia.user.User;
import com.hackathon.hestia.user.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class EventService {

    @Autowired
    EventRepository eventRepository;

    @Autowired
    UserRepository userRepository;

    public Optional<Event> getEventById(long eventId) {
        return eventRepository.findById(eventId);
    }

    public List<Event> getEventByName(String filter) {
        return eventRepository.findByEventNameContaining(filter);
    }

    public List<Event> getAllEvents() {
        return eventRepository.findAll();
    }

    public List<User> getAttendees(long eventId) {
        Optional<Event> event = getEventById(eventId);
        List<User> attendees = new ArrayList<User>();
        if (event.isPresent() && event.get().getAttendees() != null) {
            List<Long> attendeeIds = event.get().getAttendees();
            for (Long id : attendeeIds) {
                if (userRepository.findById(id).isPresent()) {
                    attendees.add(userRepository.findById(id).get());
                }
            }
        }
        return attendees;
    }

    public Event createEvent(String content, long authorId, Date startTime, Date endTime, String eventName, Location location) {
        if (content != null && !content.isEmpty() && startTime != null && endTime != null
            && eventName != null && !eventName.isEmpty()) {
            Event event = new Event(content, authorId, startTime, endTime, eventName, location);
            eventRepository.save(event);
            return event;
        }
        return null;
    }

    public User addAttendee(long eventId, User attendee) {;
        Optional<Event> event = eventRepository.findById(eventId);
        if (event.isPresent()) {
            event.get().getAttendees().add(attendee.getUserId());
            eventRepository.save(event.get());
            return attendee;
        }
        System.err.println("Event: " + event.get());
        return null;
    }

    public boolean deleteEvent(long eventId) {
        try {
            eventRepository.deleteById(eventId);
            return true;
        } catch (IllegalArgumentException e) {
            return false;
        }
    }

    public boolean deleteAllEvents() {
        try {
            eventRepository.deleteAll();
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
