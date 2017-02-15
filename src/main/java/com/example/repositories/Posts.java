package com.example.repositories;

import com.example.models.Post;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by RyanHarper on 2/9/17.
 */

    //insert -> need: table name, values, columns
    //select -> need: table, columns, values
    //update -> need: table, columns, values
    //delete -> need: table, values
    // This is CRUD. That's why Hibernate provides the CrudRepository built-in.

@Repository
public interface Posts extends CrudRepository
        </*the entity to return*/Post, /*type of identifier:*/Long> {
@Query("select p from Post p where title like ?1")
    public List<Post> findWhereTitleLike(String title);

//@Query("select p from Post p where body like ?1")
//    public List<Post> findWhereBodyLike(String body);
// this is basically the Dao.
// This Interface is going to be used in the Controller.
}
