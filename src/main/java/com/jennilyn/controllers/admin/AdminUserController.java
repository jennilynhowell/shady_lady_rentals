package com.jennilyn.controllers.admin;

import com.jennilyn.enums.States;
import com.jennilyn.models.Order;
import com.jennilyn.models.Role;
import com.jennilyn.models.User;
import com.jennilyn.repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.swing.plaf.nimbus.State;
import java.util.Arrays;
import java.util.List;

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
        Iterable<Role> roles = roleRepo.findAll();
        States[] states = States.values();
        model.addAttribute("customers", customers);
        model.addAttribute("user", new User());
        model.addAttribute("states", states);
        model.addAttribute("roles", roles);
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
    @RequestMapping(value = "/admin/clients", method = RequestMethod.POST)
    public String addClient(@RequestParam("first") String first,
                            @RequestParam("last") String last,
                            @RequestParam("username") String username,
                            @RequestParam("password") String password,
                            @RequestParam("email") String email,
                            @RequestParam("address") String address,
                            @RequestParam("city") String city,
                            @RequestParam("state") String state,
                            @RequestParam("zipCode") String zipCode,
                            @RequestParam("phone") String phone,
                            @RequestParam("role") long roleId){
        User newUser = new User();
        Role role = roleRepo.findOne(roleId);
        States selectedState = States.valueOf(state);
        newUser.setFirst(first);
        newUser.setLast(last);
        newUser.setUsername(username);
        newUser.setPassword(password);
        newUser.setEmail(email);
        newUser.setAddress(address);
        newUser.setCity(city);
        newUser.setState(selectedState);
        newUser.setZipCode(zipCode);
        newUser.setPhone(phone);
        newUser.setRole(role);
        userRepo.save(newUser);
        return "redirect:/admin/clients";
    }
}
