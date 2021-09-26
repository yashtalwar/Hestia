package com.hackathon.hestia.community;

import java.util.List;
import java.util.ArrayList;
import java.util.Optional;

import com.hackathon.hestia.user.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/community")
public class CommunityController {

    @Autowired
    private final CommunityService communityService;
    public CommunityController(CommunityService communityService) { this.communityService = communityService; }

    /*
     * Will get all communities, searching by name if it is provided, and return an HTML friendly list
     * Will return a NO_CONTENT if there was any error
     */
    @GetMapping("/communities")
    public ResponseEntity<List<Community>> getCommunity(@RequestParam(required = false) String name) {
        List<Community> communities = new ArrayList<Community>();
        if (name == null) {
            communities = communityService.getCommunities();
        } else {
            communities = communityService.getCommunity(name);
        }

        if (communities.isEmpty())
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        return new ResponseEntity<>(communities, HttpStatus.OK);
    }

    /*
     * Returns a specific community by ID. If not found, returns NO_CONTENT status.
     */
    @GetMapping("/community/{id}")
    public ResponseEntity<Community> getCommunitybyID(@PathVariable("id") long id) {
        Optional<Community> communityData = communityService.getCommunitybyID(id);
        if (communityData.isPresent())
            return new ResponseEntity<Community>(communityData.get(), HttpStatus.OK);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    /*
     * Uploads a community to be created, and returns the new community created. If it fails, returns server error.
     */
    @PostMapping("/community")
    public ResponseEntity<Community> deleteCommunity(@RequestBody Community community) {
        boolean isCommunityCreated = communityService.postCommunity(community);
        if (isCommunityCreated)
            return new ResponseEntity<>(community, HttpStatus.CREATED);
        return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    /*
     * Deletes community by ID, and returns NO_CONTENT if it worked. Otherwise, returns server error.
     */
    @DeleteMapping("/communities/{id}")
    public ResponseEntity<User> deleteCommunity(@PathVariable("id") long id) {
        boolean isCommunityDeleted = communityService.deleteCommunity(id);
        if (isCommunityDeleted)
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }


}
