package com.example.repositories;

import com.example.models.UserRole;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by RyanHarper on 2/13/17.
 */
@Repository
public interface Roles extends CrudRepository<UserRole, Long> {

    // normally we write SQL, structured query language that queries tables.
    // Hibernate Query Language. The difference is that HQL queries objects -> Entities

    @Query("select ur.role from UserRole ur, User u where u.username=?1 and ur.userId = u.id")
    public List<String> ofUserWith(String username);
}
