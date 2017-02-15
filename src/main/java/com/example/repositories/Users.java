package com.example.repositories;


import com.example.models.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by RyanHarper on 2/10/17.
 */
@Repository
public interface Users extends CrudRepository<User, Long> {
    // select * from users where username = ?. Even if you don't see it,
    // this is what Spring is doing: findBy Username.
    // You don't need to write a query. It's automatic:
    public User findByUsername(String username);
}
