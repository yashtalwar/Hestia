package com.hackathon.hestia.post;

import com.hackathon.hestia.post.Post;

import javax.persistence.*;

@Entity
public class Topic extends Post {

    private String title;

    public Topic(){

    }

    public Topic(String content, String title, Long authorId) {
        super(content, authorId);
        this.title = title;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

}
