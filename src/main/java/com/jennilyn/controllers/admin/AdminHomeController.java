package com.jennilyn.controllers.admin;

import com.jennilyn.models.Order;
import com.jennilyn.models.Product;
import com.jennilyn.models.Supplier;
import com.jennilyn.models.User;
import com.jennilyn.repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class AdminHomeController {

    @Autowired
    OrderRepository orderRepo;

    @RequestMapping("/admin")
    public String adminHome(Model model){
        Iterable<Order> activeOrders = orderRepo.findAll();
        model.addAttribute("activeOrders", activeOrders);
        return "admin/adminHome";
    }

}
