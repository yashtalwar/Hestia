package com.hackathon.hestia.comment;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/posts")
public class CommentController {
    private final CommentService commentService;

    @Autowired
    public CommentController(CommentService commentService) {
        this.commentService = commentService;
    }

    /*
     * Will get all comments of a post, and return an HTML friendly list
     * Will return a NO_CONTENT if there was any error
     */
    @GetMapping("{id}/comments")
    public ResponseEntity<List<Comment>> getAllCommentsFromTopic(@PathVariable("id") long parentId) {
        List<Comment> comments = commentService.getAllCommentsOfTopic(parentId);
        if (comments.isEmpty())
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        return new ResponseEntity<List<Comment>>(comments, HttpStatus.OK);
    }

    /*
     * Returns a specific comment by ID. If not found, returns NO_CONTENT status.
     */
    @GetMapping("{parentId}/comments/{id}")
    public ResponseEntity<Comment> getPostById(@PathVariable("parentId") long parentId, @PathVariable("id") long id) {
        Optional<Comment> commentData = commentService.getCommentById(id);
        if (commentData != null)
            return new ResponseEntity<Comment>(commentData.get(), HttpStatus.OK);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("/comments")
    public ResponseEntity<List<Comment>> getAllComments() {
        List<Comment> comments = commentService.getAllComments();
        if (!comments.isEmpty())
            return new ResponseEntity<List<Comment>>(comments, HttpStatus.OK);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    /*
     * Uploads a comment to be created, and returns the new comment created. If it fails, returns server error.
     */
    @PostMapping("/{parentId}/comments")
    public ResponseEntity<Comment> createComment(@PathVariable("parentId") long parentId, @RequestBody Comment comment) {
       Comment newComment = commentService.postComment(comment.getContent(), comment.getAuthorId(), parentId);
        if (newComment != null)
            return new ResponseEntity<Comment>(newComment, HttpStatus.CREATED);
        return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    /*
     * Deletes post by ID, and returns NO_CONTENT if it worked. Otherwise, returns server error.
     */
    @DeleteMapping("/{parentId}/comments/{id}")
    public ResponseEntity<HttpStatus> deleteCommentsById(@PathVariable("parentId") long parentId, @PathVariable("id") long id) {
        boolean isPostDeleted = commentService.deleteComment(id, parentId);
        if (isPostDeleted)
            return new ResponseEntity<HttpStatus>(HttpStatus.NO_CONTENT);
        return new ResponseEntity<HttpStatus>(HttpStatus.INTERNAL_SERVER_ERROR);
    }

    /*
     * Deletes all posts (useful for testing
     */
    @DeleteMapping("/comments")
    public ResponseEntity<HttpStatus> deleteAllComments() {
        boolean isAllPostsDeleted = commentService.deleteAllComments();
        if (isAllPostsDeleted)
            return new ResponseEntity<HttpStatus>(HttpStatus.NO_CONTENT);
        return new ResponseEntity<HttpStatus>(HttpStatus.INTERNAL_SERVER_ERROR);
    }
}