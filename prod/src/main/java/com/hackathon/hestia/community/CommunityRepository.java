package com.hackathon.hestia.community;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.*;
@Repository
public interface CommunityRepository extends JpaRepository<Community,Long>{
    List<Community> findByNameContaining(String name);
}
