package com.example.controllers;
import com.example.models.Post;
import com.example.models.User;
import com.example.repositories.Posts;
import com.example.services.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
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

    @Autowired // The Interface: Posts, the instance variable: postsDao;
    Posts postsDao;

    @GetMapping("/posts")
    public String viewAllPosts(Model model) {
        model.addAttribute("ListOfPosts", postsDao.findAll());
        return "/posts/index";
    }

    @GetMapping("/posts/{id}")
    public String viewSinglePost(@PathVariable long id, Model model) {
        model.addAttribute("post", postsDao.findOne(id));
        return "/posts/show"; // show.html
    }

    @GetMapping("/posts/create")
    public String showCreateForm(Model model) {
        Post post = new Post();
        model.addAttribute("post", post);
        return "/posts/create";
    }

    @PostMapping("/posts/create")
    public String createNewPost(@Valid Post post, Errors validation, Model model) {
        // @Valid calls @ModelAttribute first and calls the validations!
        if (validation.hasErrors()) {
            model.addAttribute("errors", validation);
            model.addAttribute("post", post);
            return "/posts/create";
        }

        // get this from the curriculum:
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        //
        post.setUser(user);
        postsDao.save(post);
        return "redirect:/posts";
    }

    @GetMapping("/posts/{id}/edit")
    public String editPost(@PathVariable long id, @ModelAttribute Post post, Model model) {
//        model.addAttribute("post", postsDao.findOne(id));
        Post editedPost = postsDao.findOne(id);
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
        model.addAttribute("ListOfPosts", postsDao.findWhereTitleLike("%" + term + "%"));
        return "posts/index";
    }

//    @GetMapping("/posts/search")
//    public String searchBody(@RequestParam(name = "term") String term, Model model) {
//        System.out.println(term);
//        model.addAttribute("ListOfPosts", postsDao.findWhereBodyLike("%" + term + "%"));
//        return "posts/index";
//    }
}
