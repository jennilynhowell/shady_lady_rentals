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
public class AdminController {

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

    @RequestMapping("/admin")
    public String adminHome(Model model){
        Iterable<Order> activeOrders = orderRepo.findAll();
        model.addAttribute("activeOrders", activeOrders);
        return "adminHome";
    }

    @RequestMapping("/admin/clients")
    public String adminClientList(Model model){
        Iterable<User> customers = userRepo.findAll();
        model.addAttribute("customers", customers);
        return "adminClients";
    }

    @RequestMapping("/admin/clients/{clientId}")
    public String adminClientList(@PathVariable("clientId") long clientId,
                                  Model model){
        User customer = userRepo.findOne(clientId);
        Iterable<Order> orders = orderRepo.findAllByRentaluser(customer);
        model.addAttribute("customer", customer);
        model.addAttribute("orders", orders);
        return "adminOneClient";
    }

    @RequestMapping("/admin/inventory")
    public String inventoryList(Model model){
        Iterable<Product> products = productRepo.findAll();
        model.addAttribute("products", products);
        return "adminInventory";
    }

    @RequestMapping("/admin/suppliers/{supplierId}")
    public String adminSupplierList(@PathVariable("supplierId") long supplierId,
                                    Model model){
        Supplier supplier = supplierRepo.findOne(supplierId);
        Iterable<Product> products = productRepo.findAllBySupplier(supplier);
        model.addAttribute("supplier", supplier);
        model.addAttribute("products", products);
        return "adminOneSupplier";
    }

}
