package org.example;

import java.sql.Date;
import java.sql.SQLException;
import java.util.List;

public interface CarRepository
{
    boolean addCar(Car car);

    boolean deleteCar(String registration_number);

    boolean updateCar(Car car);

    List<Car> getAllCar();

    List<Car> getAllCarByYear(int year);
    
    List<Car> getAllCarByPrice(int min, int max);
}