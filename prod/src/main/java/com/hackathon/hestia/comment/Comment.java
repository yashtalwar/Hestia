package com.hackathon.hestia.comment;

import com.hackathon.hestia.post.Post;

import javax.persistence.*;

@Entity
public class Comment extends Post {

    private long parentId;

    public Comment() {

    }

    public Comment(String content, long authorId, long parentId) {
        super(content, authorId);
        this.parentId = parentId;
    }

    public long getParentId() {
        return parentId;
    }

    public void setParentId(long parentId) {
        this.parentId = parentId;
    }
}
