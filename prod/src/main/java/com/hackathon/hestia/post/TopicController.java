package com.hackathon.hestia.post;

import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class TopicController{
    private final TopicService topicService;

    @Autowired
    public TopicController(TopicService topicService) {
        this.topicService = topicService;
    }

    /*
     * Will get all posts, searching by name if it is provided, and return an HTML friendly list
     * Will return a NO_CONTENT if there was any error
     */
    @GetMapping("/posts")
    public ResponseEntity<List<Topic>> getAllTopics(@RequestParam(required = false) String title) {
        List<Topic> topics = new ArrayList<Topic>();
        if (title == null || title.length() == 0)
            topics = topicService.getAllTopics();
        else
            topics = topicService.getTopics(title);

        if (topics.isEmpty())
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        return new ResponseEntity<>(topics, HttpStatus.OK);
    }

    /*
     * Returns a specific post by ID. If not found, returns NO_CONTENT status.
     */
    @GetMapping("/posts/{id}")
    public ResponseEntity<Topic> getPostById(@PathVariable("id") long id) {
        Optional<Topic> topicData = topicService.getTopicById(id);
        if (topicData != null)
            return new ResponseEntity<Topic>(topicData.get(), HttpStatus.OK);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    /*
     * Uploads a post to be created, and returns the new post created. If it fails, returns server error.
     */
    @PostMapping("/posts")
    public ResponseEntity<Topic> createPost(@RequestBody Topic topic) {
       Topic newTopic = topicService.postTopic(topic.getContent(), topic.getTitle(), topic.getAuthorId());
        if (newTopic != null)
            return new ResponseEntity<Topic>(newTopic, HttpStatus.CREATED);
        return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    /*
     * Deletes post by ID, and returns NO_CONTENT if it worked. Otherwise, returns server error.
     */
    @DeleteMapping("/posts/{id}")
    public ResponseEntity<HttpStatus> deleteTopicById(@PathVariable("id") long id) {
        boolean isPostDeleted = topicService.deleteTopic(id);
        if (isPostDeleted)
            return new ResponseEntity<HttpStatus>(HttpStatus.NO_CONTENT);
        return new ResponseEntity<HttpStatus>(HttpStatus.INTERNAL_SERVER_ERROR);
    }

    /*
     * Deletes all posts (useful for testing
     */
    @DeleteMapping("/posts")
    public ResponseEntity<HttpStatus> deleteAllTopics() {
        boolean isAllPostsDeleted = topicService.deleteAllTopics();
        if (isAllPostsDeleted)
            return new ResponseEntity<HttpStatus>(HttpStatus.NO_CONTENT);
        return new ResponseEntity<HttpStatus>(HttpStatus.INTERNAL_SERVER_ERROR);
    }
}