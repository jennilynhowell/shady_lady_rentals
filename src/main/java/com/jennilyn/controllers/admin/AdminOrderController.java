package com.jennilyn.controllers.admin;

import com.jennilyn.models.Order;
import com.jennilyn.models.OrderProduct;
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

    @Autowired
    OrderProductRepository orderProductRepo;

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
        Iterable<Product> products = productRepo.findAll();
        Iterable<OrderProduct> orderProducts = order.getOrderProducts();
        model.addAttribute("order", order);
        model.addAttribute("products", products);
        model.addAttribute("orderProds", orderProducts);
        return "admin/adminUpdateOrder";
    }

    //creates only the new order
    @RequestMapping(value = "/admin/orders/create", method = RequestMethod.POST)
    public String addOrder(@RequestParam("rentaluser") long rentaluserId,
                           @RequestParam("serviceRequestedAt") String serviceRequestedAt,
                           @RequestParam("location") String location,
                           @RequestParam("notes") String notes){
        Order newOrder = new Order();
        User client = userRepo.findOne(rentaluserId);

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
        return "redirect:/admin/orders/" + id + "/update";
    }

    //adds orderProducts to order
    @RequestMapping(value = "/admin/orders/{orderId}/update", method = RequestMethod.POST)
    public String updateOrder(@PathVariable("orderId") long orderId,
                              @RequestParam("quantity") int quantity,
                              @RequestParam("product") long productId,
                              Model model){
        Order order = orderRepo.findOne(orderId);
        OrderProduct orderProduct = new OrderProduct();
        orderProduct.setCustomer_order(order);
        orderProduct.setQuantity(quantity);
        orderProduct.setProduct(productRepo.findOne(productId));

        Timestamp currentTime = new Timestamp(now.getTime());
        order.setUpdatedAt(currentTime);

        orderProductRepo.save(orderProduct);
        model.addAttribute("order", order);
        return "admin/adminUpdateOrder";
    }

    @RequestMapping(value = "/admin/orders/{orderId}/process", method = RequestMethod.POST)
    public String processOrder(@PathVariable("orderId") long orderId){
        double total = 0.0;
        Order order = orderRepo.findOne(orderId);

        for (OrderProduct orderProduct : order.getOrderProducts()){
            total += orderProduct.getProduct().getSalePrice();
        }

        order.setTotal(total);

        Timestamp currentTime = new Timestamp(now.getTime());
        order.setProcessedAt(currentTime);

        orderRepo.save(order);
        return "redirect:/admin";
    }

    @RequestMapping(value = "/admin/orders/{orderId}/fulfill", method = RequestMethod.POST)
    public String fulfillOrder(@PathVariable("orderId") long orderId){
        Order order = orderRepo.findOne(orderId);
        order.setFulfilled(true);

        Timestamp currentTime = new Timestamp(now.getTime());
        order.setServiceCompletedAt(currentTime);

        orderRepo.save(order);
        return "redirect:/admin";
    }

    @RequestMapping(value = "/admin/orders/{orderId}/delete", method = RequestMethod.POST)
    public String deleteOrder(@PathVariable("orderId") long orderId){
        orderRepo.delete(orderId);

        return "redirect:/admin";
    }


    //TODO: this is broken
    @RequestMapping(value = "/admin/orderProducts/{orderProductId}/delete", method = RequestMethod.POST)
    public String deleteItem(@PathVariable("orderProductId") long orderProductId){
        OrderProduct orderProduct = orderProductRepo.findOne(orderProductId);
        long id = orderProduct.getCustomer_order().getRentaluser().getId();
        orderProductRepo.delete(orderProductId);
        return "redirect:/admin/orders/" + id + "/update";
    }

}
