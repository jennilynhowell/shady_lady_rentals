package com.jennilyn.controllers.home;

import com.jennilyn.models.User;
import com.jennilyn.repositories.ProductRepository;
import com.jennilyn.repositories.UserRepository;
import com.sun.org.apache.xpath.internal.operations.Mod;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

@Controller
public class HomeController {

    @Autowired
    ProductRepository productRepo;

    @Autowired
    UserRepository userRepository;

    @RequestMapping("/")
    public String index(){

        return "index";
    }

    @RequestMapping("/login")
    public String login(Model model, HttpServletRequest request){
        model.addAttribute("user", new User());

        try {
            Object message = request.getSession().getAttribute("error");
            model.addAttribute("error", message);
            request.getSession().removeAttribute("error");
        } catch (Exception ex) {}

        return "login";
    }

}
