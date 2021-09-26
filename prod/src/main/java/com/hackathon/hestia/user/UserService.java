package com.hackathon.hestia.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    UserRepository userRepository;

    public Optional<User> getUserByID (long userID) {
        Optional<User> userData = userRepository.findById(userID);
        return userData;
    }

    public List<User> getUsers(String filter){
        List<User> users = new ArrayList<User>();
        userRepository.findByNameContaining(filter).forEach(users::add);
        return users;
    }

    public List<User> getUsers(){
        List<User> users = new ArrayList<User>();
        userRepository.findAll().forEach(users::add);
        return users;

    }

    public boolean postUser(User userAdd){
        try {
            if (userAdd.getName() != null && !userAdd.getName().isEmpty()
                    && userAdd.getPhoneNumber() != null && !userAdd.getPhoneNumber().isEmpty()
                    && userAdd.getLocation() != null
                    && userAdd.getPronouns() != null && !userAdd.getPronouns().isEmpty())
            userRepository.save(new User(
                    userAdd.getName(), userAdd.getPhoneNumber(), userAdd.getEmailAddress(), userAdd.getLocation(), User.encryptPassword(userAdd.getPassword()), userAdd.getPronouns(),
                    userAdd.getPreferredLanguage()
            ));
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public User verify(String email, String password) {
        if (userRepository.findByEmailAddress(email).isEmpty())
            return null;
        User user = userRepository.findByEmailAddress(email).get(0);
        try {
            if (User.encryptPassword(password).equals(userRepository.findByEmailAddress(email).get(0).getPassword()))
                return user;
            return null;
        } catch (NoSuchAlgorithmException e) {
            return null;
        }
    }

    public User addFollower(long userFollowing, long userToFollow ) {
        Optional<User> newFollower = getUserByID(userFollowing);
        Optional<User> followedUser = getUserByID(userToFollow);
        if (newFollower.isPresent() && followedUser.isPresent()) {
            followedUser.get().addFollower(newFollower.get().getUserId());
            userRepository.save(followedUser.get());
            return newFollower.get();
        }
        return null;
    }

    public List<User> getFollowers(long userId) {
        Optional<User> user = getUserByID(userId);
        List<Long> followerIds = new ArrayList<Long>();
        List<User> followers = new ArrayList<User>();
        if (user.isPresent()) {
            followerIds = user.get().getFollowers();
            for (Long id : followerIds) {
                followers.add(getUserByID(id).get());
            }
        }
        return followers;
    }

    public List<User> getFollowing(long userId) {
        Optional<User> followingUser = getUserByID(userId);
        List<Long> followingIds = new ArrayList<Long>();
        List<User> following = new ArrayList<User>();
        if (followingUser.isPresent()) {
            userRepository.findAll().forEach(user -> {
                if (user.getFollowers().contains(userId))
                    following.add(user);
            });
        }
        return following;
    }

    public User putUser(long userId, User user) {
        Optional<User> userData = getUserByID(userId);
        if (userData.isPresent()) {
            User _user = userData.get();
            if (user.getPassword() != null) {
                try {
                    _user.setPassword(User.encryptPassword(user.getPassword()));
                } catch (NoSuchAlgorithmException e) {
                    return null;
                }
            }
            if (user.getPronouns() != null)
                _user.setPronouns(user.getPronouns());
            if(user.getLocation() != null)
                _user.setLocation(user.getLocation());
            if(user.getPhoneNumber() != null)
                _user.setPhoneNumber(user.getPhoneNumber());
            if(user.getEmailAddress() != null)
                _user.setEmailAddress(user.getEmailAddress());
            userRepository.save(_user);
            return _user;
        } else {
            return null;
        }
    }


    public boolean deleteUser(long userID){
        try {
            userRepository.deleteById(userID);
            return true;
        } catch (IllegalArgumentException e){
            return false;
        }
    }

    public boolean deleteAllUsers() {
        try {
            userRepository.deleteAll();
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
