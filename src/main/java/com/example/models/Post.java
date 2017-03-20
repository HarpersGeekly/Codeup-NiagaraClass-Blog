package com.example.models;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.commonmark.node.Node;
import org.commonmark.parser.Parser;
import org.commonmark.renderer.html.HtmlRenderer;
import org.hibernate.validator.constraints.NotBlank;

import javax.persistence.*;
import javax.validation.constraints.Size;

/**
 * Created by RyanHarper on 2/8/17.
 */

@Entity // <-- this will define this model as a table, the Data Model. This now knows about the database!
@Table(name = "posts") // specifies the name of the table as it appears in the database.
public class Post {

    @Id // specifies the primary key in the table and it's strategy type.
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(nullable = false, length = 100) //creates column
    @NotBlank(message="Title cannot be empty")
    private String title;

    @Column(nullable = false, length = 2000)
    @NotBlank(message="Description cannot be empty :/")
    @Size(min = 5, message="Description must be at least 5 characters.")
    private String body;


    @Column
    private String image;

    @ManyToOne
    // will define the foreign key. This class represents the post table and we need a reference to the user
    // The convention is "the_other_table_name_id"
    @JoinColumn(name = "user_id") // Define at the Table Level.  Lots of posts can have one "user"
    @JsonManagedReference
    private User user; // this is the owner/author/poster, etc.

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Post() {

    }

    public Post(String title, String body) {
        this.title = title;
        this.body = body;
    }

    public Post(long id, String title, String body) {
        this.id = id;
        this.title = title;
        this.body = body;
    }

    @JsonProperty
    public String getHtmlBody() {
        Parser parser = Parser.builder().build();
        HtmlRenderer renderer = HtmlRenderer.builder().build();
        return renderer.render(parser.parse(body));
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}
