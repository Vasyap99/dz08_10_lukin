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
import kok.spring21.dto.UserDto;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import java.util.Random;
import kok.spring21.myFilter;


@Controller
public class AuthController {

    @Autowired
    AuthService as;

    public String getToken() {
        int leftLimit = 48; // numeral '0'
        int rightLimit = 122; // letter 'z'
        int targetStringLength = 10;
        Random random = new Random();

        String generatedString = random.ints(leftLimit, rightLimit + 1)
          .filter(i -> (i <= 57 || i >= 65) && (i <= 90 || i >= 97))
          .limit(targetStringLength)
          .collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
          .toString();

        System.out.println(">>>TOKEN="+generatedString+"<<");

        return generatedString;
    }

    @Autowired
    UserRepository ur;
    @GetMapping("/auth")
    public String auth1(@ModelAttribute("u") UserDto u){
        return "auth";
    }
    @PostMapping("/auth")
    public String auth2(@ModelAttribute("u") UserDto u,HttpServletRequest request){
        if(as.authUser(u) ) {
            //
            HttpSession session = request.getSession();
            // Сохраняем данные в сессии
            String tkn=getToken();
            myFilter.tkns1.add(tkn);
            System.out.println((java.util.HashSet)myFilter.tkns1);
            session.setAttribute("tkn", tkn);
            //

            return "redirect:hello";
        }
        else return "redirect:auth";
    }

    @GetMapping("/auth/logout")
    public String logout(HttpServletRequest request){
        //
        HttpSession session = request.getSession();
        // удаляем токен из сессии
        try{
            myFilter.tkns1.remove(session.getAttribute("tkn"));
            System.out.println((java.util.HashSet)myFilter.tkns1);
        }catch(Exception e){}
        session.removeAttribute("tkn");
        //
        return "auth";
    }
}
