package com.jennilyn.controllers.admin;

import com.jennilyn.models.Order;
import com.jennilyn.models.User;
import com.jennilyn.repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class AdminUserController {

    @Autowired
    OrderRepository orderRepo;

    @Autowired
    UserRepository userRepo;

    @Autowired
    RoleRepository roleRepo;

    @Autowired
    ProductRepository productRepo;

    @Autowired
    SupplierRepository supplierRepo;

    @RequestMapping("/admin/clients")
    public String adminClientList(Model model){
        Iterable<User> customers = userRepo.findAll();
        model.addAttribute("customers", customers);
        model.addAttribute("user", new User());
        return "admin/adminClients";
    }

    @RequestMapping("/admin/clients/{clientId}")
    public String adminClientList(@PathVariable("clientId") long clientId,
                                  Model model){
        User customer = userRepo.findOne(clientId);
        Iterable<Order> orders = orderRepo.findAllByRentaluser(customer);
        model.addAttribute("customer", customer);
        model.addAttribute("orders", orders);
        return "admin/adminOneClient";
    }

    //forms
    @RequestMapping(name = "/admin/clients", method = RequestMethod.POST)
    public String addClient(){
        return "redirect:/admin/adminClients";
    }
}
