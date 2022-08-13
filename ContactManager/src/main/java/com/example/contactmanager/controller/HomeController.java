package com.example.contactmanager.controller;

import com.example.contactmanager.entity.User;
import com.example.contactmanager.helper.Message;
import com.example.contactmanager.repo.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

@Controller
public class HomeController {
        @Autowired
        private UserRepository userRepository;
        @Autowired
        private BCryptPasswordEncoder passwordEncoder;

        @RequestMapping("/")
        public String home(Model model){
            model.addAttribute("title","Home - Smart Contact Manager");
            return "home";
        }
        @RequestMapping("/about")
        public String about(Model model){
            model.addAttribute("title","about - Smart Contact Manager");
            return "about";
        }
        @RequestMapping("/signup")
        public String signup(Model model){
            model.addAttribute("title","Signup - Smart Contact Manager");
            model.addAttribute("user",new User());
            return "signup";
        }

    //  Handler for register User
        @RequestMapping(value = "/do_register", method = RequestMethod.POST)
        public String registerUser(@Valid @ModelAttribute("user") User user, BindingResult result, @RequestParam(value = "agreement",
                defaultValue = "false") boolean agreement, Model model, HttpSession session){

            try {
                if(!agreement){
                    System.out.println("You have not agreed the terms and conditions");
                    throw new Exception("You have not agreed the terms and conditions");
                }
                if (result.hasErrors()){
                    System.out.println("ERROR"+result.toString());
                    model.addAttribute("user", user);
                    return "signup";
                }

                user.setRole("Role_User");
                user.setEnabled(true);
                user.setImageUrl("default.png");
                user.setPassword(passwordEncoder.encode(user.getPassword()));

                System.out.println("Agreement"+agreement);
                System.out.println("USER"+user);
                User result1= this.userRepository.save(user);
                model.addAttribute("user", new User());
                session.setAttribute("message", new Message("Successfully Registered!!","alert-success"));
                return "signup";

                }catch (Exception e){
                e.printStackTrace();
                model.addAttribute("user",user);
                session.setAttribute("message", new Message("Something Went Wrong!! "+e.getMessage(),"alert-danger"));
                return "signup";
                }
        }

    //  Handler for custom login page
        @RequestMapping("/login")
        public String customLogin(Model model){
            model.addAttribute("title","Login Page- Smart Contact Manager");
            return "login";
        }

    }
