package com.example.contactmanager.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping ("/user")
public class UserController {
    @RequestMapping( "/index")
    public String dashboard(Model model){
        model.addAttribute("title","User Dashboard Page- Smart Contact Manager");
        return "normal/user_dashboard";
    }
}
