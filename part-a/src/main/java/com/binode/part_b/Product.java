package com.binode.part_b;

public class Product {
    private int id;
    private String name;
    private double price;
    private String email;

    public Product(int id, String name, double price, String email) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.email = email;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", price=" + price +
                ", email='" + email + '\'' +
                '}';
    }
}
