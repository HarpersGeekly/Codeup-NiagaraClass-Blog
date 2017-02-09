package com.example.services;

import com.example.models.Post;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by RyanHarper on 2/8/17.
 */

@Service("PostService")
public class PostService {

    private List<Post> posts = new ArrayList<>();

    public PostService() {
        createPosts();
    }

    //retrieving an individual post object)
    public Post findOnePost(long id) {
        return posts.get((int)id - 1);
    }

    // retrieve all posts
    public List<Post> findAllPosts() {
        return posts;
    }

    public Post savePost(Post post) {
        post.setId(posts.size() + 1);
        posts.add(post);
        return post;
    }

    private void createPosts() {
        for(int i=0;i<10;i++) {
            savePost(new Post(i + 1, "Hello World Post" + " " + (i+1), "World Body" ));
        }
    }

}
