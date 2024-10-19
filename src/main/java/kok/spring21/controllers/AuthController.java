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

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;


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
    public String auth2(@ModelAttribute("u") User u,HttpServletRequest request){
        if(as.authUser(new User(u.getName(),u.getPass())) ) {
            //
            HttpSession session = request.getSession();
            // Сохраняем данные в сессии
            session.setAttribute("tkn", "111");
            //

            return "redirect:hello";
        }
        else return "redirect:auth";
    }

    @GetMapping("/auth/logout")
    public String logout(HttpServletRequest request){
        //
        HttpSession session = request.getSession();
        // Сохраняем данные в сессии
        session.removeAttribute("tkn");
        //
        return "auth";
    }
}
