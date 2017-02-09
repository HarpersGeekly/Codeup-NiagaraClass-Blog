package com.example.controllers;
import com.example.models.Post;
import com.example.repositories.Posts;
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

//    private PostService service;

//    @Autowired
//    public PostsController(PostService postService) {
//        this.service = postService;
//    }

    @Autowired
    Posts postsDao; //<- this can be named anything.
    // Autowired connects between the Service and Controller. Creating an instance of PostService (postService) and using it within the Controller.
    //This allows postService to be used with methods in PostsController.

    @GetMapping("/posts")
    public String viewAllPosts(Model model) {

        // array list with several post objects
//        List<Post> posts = new ArrayList<>();
//        Now we incorporate the postservice method findAllPosts() which is an arraylist.
//        List<Post> posts = service.findAllPosts();

        // pass the list to the view (through a view model)
//        posts.add(new Post("My first post", "body of first post"));  NO LONGER NEEDED BECAUSE THE POSTSERVICE WILL CREATE NOW
//        posts.add(new Post("this is also a post", "this is the body")); NO LONGER NEEDED BECAUSE THE POSTSERVICE WILL CREATE NOW
        model.addAttribute("ListOfPosts", postsDao.findAll());

        return "/posts/index";
    } // index.html

    @GetMapping("/posts/{id}")
    public String viewSinglePost(@PathVariable long id, Model model) {
//        Inside the method that shows an individual post, create a new post object and pass it to the view.
//        Post post = new Post("Hello World", "World body");
//        Post post = service.findOnePost(id);
        model.addAttribute("post", postsDao.findOne(id));
        return "/posts/show"; // show.html
    }

    // think of the next two as Step 1, and then Step 2 respectively...a doGet, doPost

    @GetMapping("/posts/create")
    public String showCreateForm(Model model) {
        Post post = new Post();
        model.addAttribute("post", post);
        return "/posts/create";
    }

    @PostMapping("/posts/create")
    public String createNewPost(//@RequestParam(name="title") String title,
                                //@RequestParam(name="description") String body,
                                @ModelAttribute Post post,
                                Model model
    ) {
        //create a new Post and pass it to the view:
        //Post post = new Post(title, body);
        postsDao.save(post); //constructor for the empty post.
//        model.addAttribute("post", post); // and passed to the view
        //in a real situation we would insert into the corresponding table, using a dao
        // service.sav(post); -> {posts.add(post);} (array list in your service)

//        return "/posts/create";
        return "redirect:/posts";
    }

    @GetMapping("/posts/{id}/edit")
    public String editPost(@PathVariable long id, @ModelAttribute Post post, Model model) {
//        model.addAttribute("post", postsDao.findOne(id));
        Post editedPost = postsDao.findOne(id);
        editedPost.setTitle(post.getTitle());
        editedPost.setBody(post.getBody());
        postsDao.save(editedPost);
        return "/posts/edit";
    }

}
