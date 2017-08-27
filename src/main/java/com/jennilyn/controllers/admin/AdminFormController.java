package com.jennilyn.controllers.admin;

import com.jennilyn.models.Product;
import com.jennilyn.models.Supplier;
import com.jennilyn.repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class AdminFormController {

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

    @RequestMapping(value = "/admin/suppliers", method = RequestMethod.POST)
    public String addSupplier(@RequestParam("companyName") String companyName,
                              @RequestParam("repName") String repName,
                              @RequestParam("phone") String phone,
                              @RequestParam("website") String website) {
        Supplier newSupplier = new Supplier();
        newSupplier.setCompanyName(companyName);
        newSupplier.setRepName(repName);
        newSupplier.setPhone(phone);
        newSupplier.setWebsite(website);
        supplierRepo.save(newSupplier);
        return "redirect:/admin/suppliers";
    }

    @RequestMapping(value = "/admin/inventory", method = RequestMethod.POST)
    public String addProduct(@RequestParam("productName") String productName,
                             @RequestParam("description") String description,
                             @RequestParam("purchaseCost") double purchaseCost,
                             @RequestParam("salePrice") double salePrice,
                             @RequestParam("numberInStock") int numberInStock,
                             @RequestParam("supplier") long supplier){
        Product newProduct = new Product();
        Supplier newProductSupplier = supplierRepo.findOne(supplier);
        newProduct.setProductName(productName);
        newProduct.setDescription(description);
        newProduct.setPurchaseCost(purchaseCost);
        newProduct.setSalePrice(salePrice);
        newProduct.setNumberInStock(numberInStock);
        newProduct.setSupplier(newProductSupplier);
        productRepo.save(newProduct);
        return "redirect:/admin/inventory";
    }
}
