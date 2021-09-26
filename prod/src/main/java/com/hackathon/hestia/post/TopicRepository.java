package com.hackathon.hestia.post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.*;
@Repository

public interface TopicRepository extends JpaRepository<Topic,Long>{
    List<Topic> findByTitleContaining(String name);
}
