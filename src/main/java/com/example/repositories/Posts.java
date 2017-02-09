package com.example.repositories;

import com.example.models.Post;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by RyanHarper on 2/9/17.
 */
@Repository
public interface Posts extends CrudRepository</*the class*/Post, /*default parameter:*/Long> {

// this is basically the Dao.
}
