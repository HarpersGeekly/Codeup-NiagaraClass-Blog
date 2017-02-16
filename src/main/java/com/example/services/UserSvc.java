package com.example.services;

import com.example.models.User;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

/**
 * Created by RyanHarper on 2/15/17.
 */

@Service("usersSvc")
public class UserSvc {

    public boolean isLoggedIn() {
        boolean isAnonymousUser =
                SecurityContextHolder.getContext().getAuthentication() instanceof AnonymousAuthenticationToken;
        return !isAnonymousUser;
    }

    public User loggedInUser() {
        if (! isLoggedIn()) {
            return null;
        }
        return (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    }
}
