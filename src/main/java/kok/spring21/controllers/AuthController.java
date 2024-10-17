package kok.spring21.controllers;


import kok.spring21.AuthService;
import kok.spring21.repo.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.validation.BindingResult;

import java.util.List;
import java.util.Optional;

import kok.spring21.models.User;


@Controller
public class AuthController {

    @Autowired
    AuthService as;

    @Autowired
    UserRepository ur;
    @GetMapping("/auth")
    public String auth1(@ModelAttribute("u") User u){
        return "auth";
    }
    @PostMapping("/auth")
    public String auth2(@ModelAttribute("u") User u){
        if(as.authUser(new User(u.getName(),u.getPass())) )    return "redirect:hello";
        else return "redirect:auth";
    }
}
