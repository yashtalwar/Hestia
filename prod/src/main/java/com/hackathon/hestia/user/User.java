package com.hackathon.hestia.user;

import javax.persistence.*;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="users")
public class User {
    @Id
    @SequenceGenerator(
            name = "user_sequence",
            sequenceName = "user_sequence",
            allocationSize = 1

    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "user_sequence"
    )
    private Long userId;
    private String name;
    private String phoneNumber;
    private String emailAddress;
    private String password;
    private Location location;
    private String pronouns;
    public Language preferredLanguage;

    @ElementCollection
    @CollectionTable(name = "user_followers", joinColumns = @JoinColumn(name = "user_id"))
    @Column(name = "followers")
    private List<Long> followers = new ArrayList<Long>();

    public User(String name, String phoneNumber, String emailAddress, String password, Location location) {
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.emailAddress = emailAddress;
        this.password = password;
        this.location = location;
    }

    public User() {
    }

    public User(String emailAddress, String password) {
        this.emailAddress = emailAddress;
        this.password = password;
    }
    
    public User(String name, String phoneNumber, String emailAddress, Location location, String password, String pronouns, Language preferredLanguage) {
        this(emailAddress, password);
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.location = location;
        this.pronouns = pronouns;
        this.preferredLanguage = preferredLanguage;
    }

    public User(Long userId, String name, String phoneNumber, String emailAddress, Location location, String password, String pronouns, Language preferredLanguage) {
        this(name, phoneNumber, emailAddress, location, password, pronouns, preferredLanguage);
        this.userId = userId;
    }

    public static String encryptPassword(String unsafePassword) throws NoSuchAlgorithmException {
          // Hash the User's password using MD5 so we never save a plaintext of their password
          MessageDigest m = MessageDigest.getInstance("MD5");
          m.update(unsafePassword.getBytes());

          byte[] bytes = m.digest();
          StringBuilder s = new StringBuilder();
          for (int i = 0; i < bytes.length; i++) {
              s.append(Integer.toString((bytes[i] & 0xff) + 0x100, 16).substring(1));
          }
          return s.toString();
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public String getPronouns() {
        return pronouns;
    }

    public void setPronouns(String pronouns) {
        this.pronouns = pronouns;
    }

    public Language getPreferredLanguage() {
        return this.preferredLanguage;
    }

    public void setPreferredLanguage(Language language) {
        this.preferredLanguage = language;
    }

    @Override
    public String toString() {
        return "User{" +
                "userId=" + userId +
                ", name='" + name + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", emailAddress='" + emailAddress + '\'' +
                ", password='" + password + '\'' +
                ", location=" + location +
                ", pronouns='" + pronouns + '\'' +
                ", preferredLanguage=" + preferredLanguage +
                '}';
    }

    public List<Long> getFollowers() {
        return followers;
    }

    public void addFollower(Long userId) {
        followers.add(userId);
    }
}
