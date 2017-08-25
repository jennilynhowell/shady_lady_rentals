package com.jennilyn.controllers.api;

import com.jennilyn.models.Order;
import com.jennilyn.models.OrderProduct;
import com.jennilyn.repositories.OrderProductRepository;
import com.jennilyn.repositories.OrderRepository;
import org.aspectj.weaver.ast.Or;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ApiOrderController {

    @Autowired
    OrderRepository orderRepo;

    @RequestMapping("/api/orders")
    public Iterable<Order> orderApiList(){
        return orderRepo.findAll();
    }

    @RequestMapping("/api/orders/{orderId}")
    public Order orderApiById(@PathVariable("orderId") long orderId){
        return orderRepo.findOne(orderId);
    }

}
