package com.jennilyn.controllers.admin;

import com.jennilyn.models.Order;
import com.jennilyn.models.Product;
import com.jennilyn.models.User;
import com.jennilyn.repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.sql.Timestamp;
import java.util.Calendar;
import java.util.Date;

@Controller
public class AdminOrderController {

    @Autowired
    OrderRepository orderRepo;

    @Autowired
    UserRepository userRepo;

    @Autowired
    ProductRepository productRepo;

    private Calendar calendar = Calendar.getInstance();
    private Date now = calendar.getTime();

    @RequestMapping("/admin/orders/create")
    public String addOrder(Model model){
        Iterable<User> clients = userRepo.findAll();
        Iterable<Product> products = productRepo.findAll();
        model.addAttribute("order", new Order());
        model.addAttribute("clients", clients);
        model.addAttribute("products", products);
        return "admin/adminCreateOrder";
    }

    @RequestMapping("/admin/orders/{orderId}/update")
    public String updateOrder(@PathVariable("orderId") long orderId,
                              Model model){
        Order order = orderRepo.findOne(orderId);
        model.addAttribute("order", order);
        return "admin/adminUpdateOrder";
    }

    @RequestMapping(value = "/admin/orders/create", method = RequestMethod.POST)
    public String addOrder(@RequestParam("rentaluser") long userId,
                           @RequestParam("serviceRequestedAt") Timestamp serviceRequestedAt,
                           @RequestParam("location") String location,
                           @RequestParam("notes") String notes){
        Order newOrder = new Order();
        Timestamp time = new Timestamp(now.getTime());
        newOrder.setCreatedAt(time);
        newOrder.setRentaluser(userRepo.findOne(userId));
        newOrder.setLocation(location);
        newOrder.setNotes(notes);
        newOrder.setServiceRequestedAt(serviceRequestedAt);
        orderRepo.save(newOrder);
        long id = newOrder.getId();
        return "redirect:/admin/orders/" + id;
    }

}
