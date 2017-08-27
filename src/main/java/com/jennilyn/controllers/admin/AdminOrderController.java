package com.jennilyn.controllers.admin;

import com.jennilyn.repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
public class AdminOrderController {

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
}
