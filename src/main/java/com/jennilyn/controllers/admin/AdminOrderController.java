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
import java.text.ParseException;
import java.text.SimpleDateFormat;
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
    private SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss Z");

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
                           @RequestParam("serviceRequestedAt") String serviceRequestedAt,
                           @RequestParam("location") String location,
                           @RequestParam("notes") String notes){
        Order newOrder = new Order();
        User client = userRepo.findOne(userId);
        //parse date string to Timestamp
        Date orderTime;
        Timestamp orderTimestamp;
        try {
            orderTime = dateFormat.parse(serviceRequestedAt);
            orderTimestamp = new Timestamp(orderTime.getTime());
            newOrder.setServiceRequestedAt(orderTimestamp);
        } catch (ParseException e) {}

        Timestamp currentTime = new Timestamp(now.getTime());
        newOrder.setCreatedAt(currentTime);
        newOrder.setRentaluser(client);
        newOrder.setLocation(location);
        newOrder.setNotes(notes);

        orderRepo.save(newOrder);
        long id = newOrder.getId();
        return "redirect:/admin/orders/" + id;
    }

}
