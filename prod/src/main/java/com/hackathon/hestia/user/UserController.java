package com.hackathon.hestia.user;

import java.util.List;
import java.util.ArrayList;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class UserController {
    private final UserService userService;

    @Autowired
    public UserController(UserService userService) { this.userService = userService; }

    /*
     * Will get all users, searching by name if it is provided, and return an HTML friendly list
     * Will return a NO_CONTENT if there was any error
     */
    @GetMapping("/users")
    public ResponseEntity<List<User>> getAllUsers(@RequestParam(required = false) String name) {
        List<User> users = new ArrayList<User>();
        if (name == null || name.length() == 0) {
            users = userService.getUsers();
        } else {
            users = userService.getUsers(name);
        }

        if (users.isEmpty())
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        return new ResponseEntity<>(users, HttpStatus.OK);
    }

    /*
     * Returns a specific user by ID. If not found, returns NO_CONTENT status.
     */
    @GetMapping("/users/{id}")
    public ResponseEntity<User> getUserById(@PathVariable("id") long id) {
        Optional<User> userData = userService.getUserByID(id);
        if (userData.isPresent())
            return new ResponseEntity<User>(userData.get(), HttpStatus.OK);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    /*
     * Returns all the followers of a selected user (by id)
     */
    @GetMapping("/users/followers/{id}")
    public ResponseEntity<List<User>> getFollowersById(@PathVariable("id") long id) {
        List<User> followers = userService.getFollowers(id);
        if (!followers.isEmpty())
            return new ResponseEntity<List<User>>(followers, HttpStatus.OK);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("/users/following/{id}")
    public ResponseEntity<List<User>> getFollowingById(@PathVariable("id") long id) {
        List<User> following = userService.getFollowing(id);
        if (!following.isEmpty())
            return new ResponseEntity<List<User>>(following, HttpStatus.OK);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    /*
     * Uploads a user to be created, and returns the new user created. If it fails, returns server error.
     */
    @PostMapping("/users")
    public ResponseEntity<User> createUser(@RequestBody User user) {
        boolean isUserCreated = userService.postUser(user);
        if (isUserCreated)
            return new ResponseEntity<User>(user, HttpStatus.CREATED);
        System.out.println(user.getPronouns());
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);

    }

    /*
     * Checks if provided email and password exist and are valid, otherwise return an unauthorized error.
     */
    @PostMapping("/users/verify")
    public ResponseEntity<User> login(@RequestBody User user) {
        User verifiedUser = userService.verify(user.getEmailAddress(), user.getPassword());
        if (verifiedUser != null)
            return new ResponseEntity<User>(verifiedUser, HttpStatus.ACCEPTED);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PostMapping("/users/followers/{id}")
    public ResponseEntity<User> addFollower(@PathVariable("id") long id, @RequestBody User user) {
        User newFollower = userService.addFollower(user.getUserId(), id);
        if (newFollower != null)
            return new ResponseEntity<User>(user, HttpStatus.OK);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    /*
     * Allows modification of any parameter of a User
     */
    @PutMapping("/users/{id}")
    public ResponseEntity<User> updateUser(@PathVariable("id") long id, @RequestBody User user) {
        User updatedUser = userService.putUser(id, user);
        if (updatedUser != null)
            return new ResponseEntity<>(updatedUser, HttpStatus.OK);
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    /*
     * Deletes user by ID, and returns NO_CONTENT if it worked. Otherwise, returns server error.
     */
    @DeleteMapping("/users/{id}")
    public ResponseEntity<HttpStatus> deleteUser(@PathVariable("id") long id) {
        boolean isUserDeleted = userService.deleteUser(id);
        if (isUserDeleted)
            return new ResponseEntity<>(HttpStatus.OK);
        return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }

    /*
     * Deletes all users (useful for testing)
     */
    @DeleteMapping("/users")
    public ResponseEntity<HttpStatus> deleteAllUsers() {
        boolean isAllUsersDeleted = userService.deleteAllUsers();
        if (isAllUsersDeleted)
            return new ResponseEntity<>(HttpStatus.OK);
        return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
