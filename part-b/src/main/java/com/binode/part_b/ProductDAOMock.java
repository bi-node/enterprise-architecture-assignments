package com.binode.part_b;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;

@Repository
@Profile("mock")
public class ProductDAOMock implements IProductDAO {
    @Autowired
    private ILogger logger;

    public ProductDAOMock(ILogger logger) {
        this.logger = logger;
    }
    @Override
    public void save(Product product) {
        // simple sleep
        try {
            Thread.sleep(350);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("Product Saving MOck " + product.getName());
        logger.log("Product is saved in the Mock DB: " + product.getName());

    }
}
