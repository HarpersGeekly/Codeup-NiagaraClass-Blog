package com.example.controllers;
import com.example.models.Post;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by RyanHarper on 2/7/17.
 */
@Controller
public class PostsController {

    @GetMapping("/posts")
    public String viewAllPosts(Model model) {

        // array list with several post objects
        List<Post> posts = new ArrayList<>();

        // pass the list to the view (through a view model)
        posts.add(new Post("My first post", "body of post"));
        posts.add(new Post("this is a post", "this is the body"));
        model.addAttribute("ListOfposts", posts);

        return "/posts/index";
    } // index.html

    @GetMapping("/posts/{id}")
    public String viewSinglePost(@PathVariable long id, Model model) {
//        Inside the method that shows an individual post, create a new post object and pass it to the view.
        Post post = new Post("Hello World", "World body");
        model.addAttribute("post", post);
        return "/posts/show"; // show.html
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
