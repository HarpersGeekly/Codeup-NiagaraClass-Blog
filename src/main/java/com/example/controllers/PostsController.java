package com.example.controllers;
import com.example.models.Post;
import com.example.models.User;
import com.example.repositories.Posts;
import com.example.services.PostService;
import com.example.services.UserSvc;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.Collections;
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

    @Autowired // The Interface: Posts, the instance variable: postsDao;
    Posts postsDao;

    @Autowired
    UserSvc usersSvc; // now we can use the UserSvc class...

    @GetMapping("/posts")
    public String viewAllPosts(Model model) {
        model.addAttribute("ListOfPosts", Collections.emptyList()); /*postsDao.findAll());*/
        return "/posts/index";
    }

    @GetMapping("/posts.json")
    public @ResponseBody List<Post>retrieveAllAds() {
        return (List<Post>) postsDao.findAll();
    }

    @GetMapping("/posts/{id}")
    public String viewSinglePost(@PathVariable long id, Model model) {
        Post post = postsDao.findOne(id);
        model.addAttribute("isEditable", usersSvc.isLoggedInAndPostMatchesUser(post.getUser()));
        model.addAttribute("post", post);
        return "/posts/show"; // show.html
    }

    @GetMapping("/posts/create")
    public String showCreateForm(Model model) {
        Post post = new Post();
        model.addAttribute("post", post);
        return "/posts/create";
    }

    @Value("${uploads}")
    private String uploadsPath;
    @PostMapping("/posts/create")
    public String createNewPost(
            @Valid Post post,
            Errors validation,
            Model model,
            // FILE UPLOAD FEATURE:
            @RequestParam(name = "image_file") MultipartFile uploadedFile) {
        // @Valid calls @ModelAttribute first and calls the validations!
        if (validation.hasErrors()) {
            model.addAttribute("errors", validation);
            model.addAttribute("post", post);
            return "/posts/create";
        }
        // FILE UPLOAD FEATURE: =====
        //unix based : mac, linux -> the folder for temporary files is always /tmp
        //kadsadja12334
        String filename = uploadedFile.getOriginalFilename();
        String filepath = Paths.get(uploadsPath, filename).toString();
        File destinationFile = new File(filepath);
        try {
            uploadedFile.transferTo(destinationFile); // moves file in this step
        } catch (IOException e) {
            e.printStackTrace();
        }
        // ==========================

        post.setUser(usersSvc.loggedInUser());
        post.setImage(filename);
        postsDao.save(post);
        return "redirect:/posts";
    }

    @GetMapping("/posts/{id}/edit")
    public String editPost(@PathVariable long id, @ModelAttribute Post post, Model model) {
//        model.addAttribute("post", postsDao.findOne(id));
        Post editedPost = postsDao.findOne(id);
        model.addAttribute("isEditable", usersSvc.isLoggedInAndPostMatchesUser(editedPost.getUser()));
        model.addAttribute("post", editedPost);
        return "/posts/edit";
    }

    @PostMapping("/posts/{id}/edit")
    public String updatePost(@ModelAttribute Post editedPost) {
        postsDao.save(editedPost);
        return "redirect:/posts";
    }

    @PostMapping("/posts/delete")
    public String deletePost(@ModelAttribute Post post) {
        postsDao.delete(postsDao.findOne(post.getId()));
        return "redirect:/posts";
    }

    @GetMapping("/posts/search")
    public String searchTitle(@RequestParam(name = "term") String term, Model model) {
        System.out.println(term);
        model.addAttribute("ListOfPosts", postsDao.findByTitleIsLikeOrBodyIsLike("%" + term + "%", "%" + term + "%"));
        return "posts/search";
    }
}
