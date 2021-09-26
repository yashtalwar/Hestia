package com.hackathon.hestia.community;


import javax.persistence.*;
import java.util.*;

@Entity
@Table
public class Community {
    @Id
    @SequenceGenerator(
            name = "community_sequence",
            sequenceName = "community_sequence",
            allocationSize = 1

    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "community_sequence"
    )
    private Long communityId;
    private String name;
    @ElementCollection
    private List<String>postIDs;
    @ElementCollection
    private List<Long> members;
    public Community(){

    }

    public Community(Long communityId, String name, List<String> postIDs, List<Long> members) {
        this.communityId = communityId;
        this.name = name;
        this.postIDs = postIDs;
        this.members = members;
    }

    public Community(String name, List<String> postIDs, List<Long> members) {
        this.name = name;
        this.postIDs = postIDs;
        this.members = members;
    }

    public Long getCommunityId() {
        return communityId;
    }

    public void setCommunityId(Long communityId) {
        this.communityId = communityId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<String> getPostIDs() {
        return postIDs;
    }

    public void setPostIDs(List<String> postIDs) {
        this.postIDs = postIDs;
    }

    public List<Long> getMembers() {
        return members;
    }

    public void setMembers(List<Long> members) {
        this.members = members;
    }
}
