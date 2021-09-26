package com.hackathon.hestia.post;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository

public interface EventRepository extends JpaRepository<Event,Long>{
    List<Event> findByEventNameContaining(String name);
}
