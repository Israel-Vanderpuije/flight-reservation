package com.izzy.flightreservation.controllers;

import com.izzy.flightreservation.entities.User;
import com.izzy.flightreservation.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class UserController {

    @Autowired
    UserRepository userRepository;

    @RequestMapping("/showRegistration")
    public String showRegistrationPage(){
        return "registerUser";
    }

    @RequestMapping(value = "/registerUser", method = RequestMethod.POST)
    public String registerUser(@ModelAttribute("user") User user){
        userRepository.save(user);
        return "login";
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public String login(@RequestParam("email") String email, @RequestParam("password") String password, ModelMap modelMap){
        User user = userRepository.findByEmail(email);
        if (user.getPassword().equals(password)){
            return "findFlights";
        } else {
            modelMap.addAttribute("msg", "Invalid Username or Password!!!");
        }
        return "login";
    }

    @RequestMapping("/showLogin")
    public String showLogin(){
        return "login";
    }

}
