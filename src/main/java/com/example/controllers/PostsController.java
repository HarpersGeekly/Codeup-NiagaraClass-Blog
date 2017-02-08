package com.example.controllers;
import com.example.models.Post;
import com.example.services.PostService;
import org.springframework.beans.factory.annotation.Autowired;
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

    @Autowired
    PostService postService;
    // connection between the Service and Controller. Creating an instance of PostService, postService, and using it within the Controller.

    @GetMapping("/posts")
    public String viewAllPosts(Model model) {

        // array list with several post objects
//        List<Post> posts = new ArrayList<>();
//        Now we incorporate the postservice method findAllPosts() which is an arraylist.
        List<Post> posts = postService.findAllPosts();

        // pass the list to the view (through a view model)
//        posts.add(new Post("My first post", "body of first post"));  NO LONGER NEEDED BECAUSE THE POSTSERVICE WILL CREATE NOW
//        posts.add(new Post("this is also a post", "this is the body")); NO LONGER NEEDED BECAUSE THE POSTSERVICE WILL CREATE NOW
        model.addAttribute("ListOfPosts", posts);

        return "/posts/index";
    } // index.html

    @GetMapping("/posts/{id}")
    public String viewSinglePost(@PathVariable long id, Model model) {
//        Inside the method that shows an individual post, create a new post object and pass it to the view.
//        Post post = new Post("Hello World", "World body");
        Post post = postService.findOnePost(id);
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
        

        return "/posts/create";
    }
}
