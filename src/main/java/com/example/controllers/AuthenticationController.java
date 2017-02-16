package com.example.controllers;
import com.example.models.User;
import com.example.models.UserRole;
import com.example.repositories.Roles;
import com.example.repositories.Users;
import com.example.services.UserSvc;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import javax.validation.Valid;


/**
 * Created by RyanHarper on 2/13/17.
 */
@Controller
public class AuthenticationController {

    private Users usersDao;
    private PasswordEncoder encoder;
    private Roles roles;
    private UserSvc userSvc;

    @Autowired
    public AuthenticationController(Users usersDao, PasswordEncoder encoder, Roles roles, UserSvc userSvc) {
        this.usersDao = usersDao;
        this.encoder = encoder;
        this.roles = roles;
        this.userSvc = userSvc;
    } // constructor injection. We first need a constructor that receives an arguement for use.
    // In this case the repository usersDao.

    @GetMapping("/login")
    public String showLoginForm() {
        System.out.println(new BCryptPasswordEncoder().encode("pass"));
        return "entry/login";
    }

    @GetMapping("/register")
    public String showRegisterForm(Model model) {
        model.addAttribute("user", new User());
        return "entry/register";
    }

    @PostMapping("/users/create")
    public String registerUser(@Valid User user, // create the user from the input values, and apply validations
                               Errors validation,
                               Model model,
                               @RequestParam(name = "password_confirm") String passwordConfirmation) {

        //@Valid includes @ModelAttribute
        //@modelAttribute takes care of all: new Object and setting username, password, email to that object.

        if (validation.hasErrors()) {
            model.addAttribute("errors", validation);
            model.addAttribute("user", user);
            return "entry/register";
        }

        //compare passwords

        if (!passwordConfirmation.equals(user.getPassword())) {
            validation.rejectValue(
                    "password",
                    "user.password",
                    "Your passwords do not match"
            );
        }

        String hashedPassword = encoder.encode(user.getPassword()); // hash the user's password
        user.setPassword(hashedPassword);
        // save the user to the database:
        User newUser = usersDao.save(user);

        UserRole ur = new UserRole();
        ur.setRole("ROLE_USER");
        ur.setUserId(newUser.getId());
        roles.save(ur);
        //redirect the user to the login page:
        return "redirect:/login";
    }

    @GetMapping("users/{id}")
    public String showUser(@PathVariable Long id, Model model) {
        User user = usersDao.findById(id);
        model.addAttribute("user", user);
        model.addAttribute("showEditControls", userSvc.isLoggedIn()
 && user.getUsername() == userSvc.loggedInUser().getUsername());
        return "entry/userprofile";
    }

}