package com.example.controllers;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

/**
 * Created by RyanHarper on 2/7/17.
 */
@Controller

public class PostsController {

    @GetMapping("/posts")
    @ResponseBody
    public String viewAllPosts() {
        return "<h1>Posts index page</h1>";
    }

    @GetMapping("/posts/{id}")
    @ResponseBody
    public String viewSinglePost(@PathVariable long id) {
        return "<h1>view an individual post with ID: " + id + ".</h1>";
    }

    // think of the next two as Step 1, and then Step 2 respectively...a doGet, doPost
    @GetMapping("/posts/create")
    @ResponseBody
    public String viewCreatePostForm() {
        return "<h1>view the form for creating a post</h1>";
    }

    @PostMapping("/posts/create")
    @ResponseBody
    public String createNewPost() {
        return "<h1>create a new post</h1>";
    }
}
