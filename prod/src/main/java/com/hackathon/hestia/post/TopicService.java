package com.hackathon.hestia.post;

import org.springframework.stereotype.Service;

import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;

@Service
public class TopicService {

    @Autowired
    TopicRepository topicRepository;

    public Optional<Topic> getTopicById(long postId) {
        return topicRepository.findById(postId);
    }

    public List<Topic> getTopics(String filter) {
        List<Topic> topics = new ArrayList<Topic>();
        topicRepository.findByTitleContaining(filter).forEach(topics::add);
        return topics;
    }

    public List<Topic> getAllTopics() {
        List<Topic> topics = topicRepository.findAll();
        return topics;
    }

    public Topic postTopic(String content, String title, long authorId ) {
        if (content != null && !content.isEmpty()
            && title != null && !title.isEmpty()) {
            Topic topic = new Topic(content, title, authorId);
            topicRepository.save(topic);
            return topic;
        }
        return null;
    }

    public boolean deleteTopic(long postID) {
        try {
            topicRepository.deleteById(postID);
            return true;
        } catch (Exception e){
            return false;
        }
    }

    public boolean deleteAllTopics() {
        try {
            topicRepository.deleteAll();
            return true;
        } catch (Exception e) {
            System.out.println("Unable to delete all topics");
            e.printStackTrace();
            return false;
        }
    }
}
