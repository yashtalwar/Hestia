package com.hackathon.hestia.post;

import com.hackathon.hestia.user.User;
import com.hackathon.hestia.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/posts")
public class EventController {

    private final EventService eventService;
    private final UserService userService;

    @Autowired
    public EventController(EventService eventService, UserService userService) { this.eventService = eventService; this.userService  = userService; }

    @GetMapping("/events/{id}")
    public ResponseEntity<Event> getEvent(long eventId) {
        Optional<Event> event = eventService.getEventById(eventId);
        if (event.isPresent())
            return new ResponseEntity<Event>(event.get(), HttpStatus.OK);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("/events")
    public ResponseEntity<List<Event>> getAllEvents(@RequestParam(required = false) String name) {
        if (name != null) {
            return new ResponseEntity<List<Event>>(eventService.getEventByName(name), HttpStatus.OK);
        } else {
            return new ResponseEntity<List<Event>>(eventService.getAllEvents(), HttpStatus.OK);
        }
    }

    @GetMapping("/events/{id}/attendees")
    public ResponseEntity<List<User>> getAttendees(@PathVariable("id") long eventId) {
        List<User> attendees = eventService.getAttendees(eventId);
        if (attendees != null || attendees.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<List<User>>(attendees, HttpStatus.OK);
    }

    @PostMapping("/events")
    public ResponseEntity<Event> addEvent(@RequestBody Event event) {
        if (event != null && event.getEventName() != null && event.getContent() != null && event.getStartTime() != null) {
            Event newEvent = eventService.createEvent(event.getContent(), event.getAuthorId(), event.getStartTime(), event.getEndTime(), event.getEventName(), event.getLocation());
            return new ResponseEntity<Event>(newEvent, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PostMapping("/events/{id}")
    public ResponseEntity<User> addAttendee(@PathVariable("id") long eventId, @RequestBody User attendee) {
        User _attendee = eventService.addAttendee(eventId, attendee);
        if (_attendee != null)
            return new ResponseEntity<User>(_attendee, HttpStatus.OK);
        System.err.println("Can't add the user " + _attendee);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @DeleteMapping("/events/{id}")
    public ResponseEntity<HttpStatus> deleteEvent(long eventId) {
        if(eventService.deleteEvent(eventId))
            return new ResponseEntity<HttpStatus>(HttpStatus.OK);
        return new ResponseEntity<HttpStatus>(HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @DeleteMapping("/events")
    public ResponseEntity<HttpStatus> deleteAllEvents() {
        if (eventService.deleteAllEvents())
            return new ResponseEntity<HttpStatus>(HttpStatus.OK);
        return new ResponseEntity<HttpStatus>(HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
