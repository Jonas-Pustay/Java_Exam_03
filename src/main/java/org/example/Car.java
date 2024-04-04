package org.example;

import java.sql.Date;

public class Car {

    private String registration_number;
    private String brand;
    private String model;
    private Date date_of_first_registration;
    private int price;
    
    public Car(String registration_number, String brand, String model, Date date_of_first_registration, int price)
    {
        this.registration_number = registration_number;
        this.brand = brand;
        this.model = model;
        this.date_of_first_registration = date_of_first_registration;
        this.price = price;
    }

    public String getRegistration_number() {
        return registration_number;
    }

    public void setRegistration_number(String registration_number) {
        this.registration_number = registration_number;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public Date getDate_of_first_registration() {
        return date_of_first_registration;
    }

    public void setDate_of_first_registration(Date date_of_first_registration) {
        this.date_of_first_registration = date_of_first_registration;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }
}