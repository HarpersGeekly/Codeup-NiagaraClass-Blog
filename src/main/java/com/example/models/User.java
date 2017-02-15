package com.example.models;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotBlank;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.util.List;

/**
 * Created by RyanHarper on 2/10/17.
 */
@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(nullable = false)
    @NotBlank(message="Enter a username.")
    private String username;

    @Column(nullable = false)
    @Email(message = "Enter a valid email address.")
    private String email;

    @Column(nullable = false)
    @NotBlank(message="Your password cannot be empty.")
    @Size(min = 8, message="Your password must be at least 8 characters.")
    private String password;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "user")  // Defined at the Object Level.
            // A user can have many posts.
            // Hence, OneToMany relationship. The property is "user" because on the other relationship
            // the instance is called "user". We would then need to modify the Many...(Post.java)
    List<Post> posts; // these are all the posts created by this user.

    public User() {

    }

    // pattern
    //copy constructor -> an alternative to clone
    public User(User user) {
        id = user.id;
        username = user.username;
        password = user.password;
        email = user.email;
        posts = user.posts;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
