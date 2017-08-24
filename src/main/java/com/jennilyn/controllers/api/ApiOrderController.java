package com.jennilyn.controllers.api;

import com.jennilyn.models.Order;
import com.jennilyn.repositories.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ApiOrderController {

    @Autowired
    OrderRepository orderRepo;

    @RequestMapping("/api/orders")
    public Iterable<Order> orderApiList(){
        return orderRepo.findAll();
    }

}
