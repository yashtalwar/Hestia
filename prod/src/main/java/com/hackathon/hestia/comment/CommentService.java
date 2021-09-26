package com.hackathon.hestia.comment;

import com.hackathon.hestia.post.Topic;
import com.hackathon.hestia.post.TopicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CommentService {

    @Autowired
    CommentRepository commentRepository;

    @Autowired
    TopicService topicService;

    public Optional<Comment> getCommentById(long commentId) {
        return commentRepository.findById(commentId);
    }

    public List<Comment> getComments(String filter) {
        List<Comment> comments = new ArrayList<Comment>();
        commentRepository.findByContentContaining(filter).forEach(comments::add);
        return comments;
    }

    public List<Comment> getAllComments() {
        return commentRepository.findAll();
    }

    public List<Comment> getAllCommentsOfTopic(long parentId) {
        List<Comment> comments = new ArrayList<Comment>();
        for (Comment comment : getAllComments()) {
            if (comment.getParentId() == parentId)
                comments.add(comment);
        }
        return comments;
    }

    public Comment postComment(String content, long authorId, long parentId ) {
        Optional<Topic> parentTopic = topicService.getTopicById(parentId);
        if (content != null && !content.isEmpty() && parentTopic.isPresent()) {
            Comment comment = new Comment(content, authorId, parentId);
            commentRepository.save(comment);
            return comment;
        }
        return null;
    }

    public boolean deleteComment(long commentId, long parentId) {
        try {
            commentRepository.deleteById(commentId);
            return true;
        } catch (Exception e){
            return false;
        }
    }

    public boolean deleteAllComments() {
        try {
            List<Topic> topics = topicService.getAllTopics();
            commentRepository.deleteAll();
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
