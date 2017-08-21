package com.jennilyn.controllers.home;

import com.jennilyn.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HomeController {

    @Autowired
    ProductRepository productRepo;

    @RequestMapping("/")
    public String index(){

        return "index";
    }

}
