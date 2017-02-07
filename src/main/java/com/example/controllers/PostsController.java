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
    public String postsIndexPage() {
        return "<h1>Posts index page</h1>";
    }

    @GetMapping("/posts/{id}")
    @ResponseBody
    public String viewPost() {
        return "<h1>view an individual post</h1>";
    }

    @GetMapping("/posts/create")
    @ResponseBody
    public String viewForm() {
        return "<h1>view the form for creating a post</h1>";
    }

    @PostMapping("/posts/create")
    @ResponseBody
    public String createPost() {
        return "<h1>create a new post</h1>";
    }

}
