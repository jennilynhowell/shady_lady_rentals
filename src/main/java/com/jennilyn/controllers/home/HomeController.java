package com.jennilyn.controllers.home;

import com.jennilyn.models.Role;
import com.jennilyn.models.User;
import com.jennilyn.repositories.ProductRepository;
import com.jennilyn.repositories.RoleRepository;
import com.jennilyn.repositories.UserRepository;
import com.sun.org.apache.xpath.internal.operations.Mod;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;

@Controller
public class HomeController {

    @Autowired
    RoleRepository roleRepo;

    @Autowired
    UserRepository userRepo;

    @Autowired
    BCryptPasswordEncoder encoder;

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

    @RequestMapping("/signup")
    public String signup(Model model){
        model.addAttribute("user", new User());
        return "signup";
    }

    @RequestMapping(value = "/signup", method = RequestMethod.POST)
    public String signup(@RequestParam("username") String username,
                         @RequestParam("password") String password,
                         @RequestParam("email") String email,
                         @RequestParam("first") String first,
                         @RequestParam("last") String last){
        User newUser = new User();
        Role userRole = roleRepo.findByName("ROLE_USER");
        newUser.setUsername(username);
        newUser.setPassword(encoder.encode(password));
        newUser.setEmail(email);
        newUser.setFirst(first);
        newUser.setLast(last);
        newUser.setRole(userRole);
        newUser.setActive(true);
        userRepo.save(newUser);
        return "redirect:/login";
    }

}
