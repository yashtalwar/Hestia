package com.hackathon.hestia.post;

import javax.persistence.*;
import java.util.Date;

@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public abstract class Post {
    @Id
    @SequenceGenerator(
            name = "post_sequence",
            sequenceName = "post_sequence",
            allocationSize = 1

    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "post_sequence"
    )
    private long postId;

    private long authorId;
    private String content;
    private Date created;

    public Post() {

    }

    public Post(String content, long authorId) {
        this.content = content;
        this.authorId = authorId;
        this.created = new Date();
    }

    public long getPostId() {
        return this.postId;
    }

    public long getAuthorId() {
        return this.authorId;
    }

    public String getContent(){
        return this.content;
    }

    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }
}
