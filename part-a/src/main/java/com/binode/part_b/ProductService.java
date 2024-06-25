package com.binode.part_b;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductService implements IProductService {
    @Autowired
    private IProductDAO  productDAO;
    @Autowired
    private IEmailSender emailSender;

    public ProductService(IProductDAO productDAO, IEmailSender emailSender) {
        this.productDAO = productDAO;
        this.emailSender = emailSender;
    }

    @Override
    public void addProduct(int id, String name, double price, String email) {
        Product product = new Product(id, name, price, email);
        productDAO.save(product);
        emailSender.sendEmail(email,"Product is added to DB");

    }

}
