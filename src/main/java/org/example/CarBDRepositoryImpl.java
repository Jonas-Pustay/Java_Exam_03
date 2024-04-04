package org.example;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class CarBDRepositoryImpl implements CarRepository
{
    private static String driver = "com.mysql.cj.jdbc.Driver";
    private static String url = "jdbc:mysql://localhost:3306/auto_dealer";
    private static String user = "root";
    private static String password = "";

    public static Connection connection;

    static
    {
        try
        {
            Class.forName(driver);
            connection = DriverManager.getConnection(url, user,password);
        }
        catch (Exception e)
        {
            CarFileFunction.printLog(e.getMessage(), "Error");
        }
    }

    @Override
    public boolean addCar(Car car)
    {
        boolean state;
        
        try
        {
            String insertionQuery = "INSERT INTO auto_dealer.car (registration_number, brand, model, date_of_first_registration, price) VALUES(?, ?, ?, ?, ?)";
            PreparedStatement preparedStatement = connection.prepareStatement(insertionQuery);
            preparedStatement.setString(1, car.getRegistration_number());
            preparedStatement.setString(2, car.getBrand());
            preparedStatement.setString(3, car.getModel());
            preparedStatement.setDate(4, car.getDate_of_first_registration());
            preparedStatement.setInt(5, car.getPrice());
            int rowsAffected = preparedStatement.executeUpdate();
            state = rowsAffected > 0;

            preparedStatement.close();
            return state;
        }
        catch (Exception e)
        {
            state = false;
            CarFileFunction.printLog(e.getMessage(), "Error");
        }
        
        return state;
    }

    @Override
    public boolean deleteCar(String registration_number)
    {
        boolean state = false;
        try
        {
            String deleteQuery = "DELETE FROM auto_dealer.car WHERE registration_number=?;";
            PreparedStatement preparedStatement = connection.prepareStatement(deleteQuery);
            preparedStatement.setString(1, registration_number);
            int rowsAffected = preparedStatement.executeUpdate();
            state = rowsAffected > 0;
        }
        catch (Exception e)
        {
            CarFileFunction.printLog(e.getMessage(), "Error");
        }
        return state;
    }

    @Override
    public boolean updateCar(Car car)
    {
        boolean state = false;
        try
        {
            String updateQuery = "UPDATE auto_dealer.car SET brand=?, model=?, date_of_first_registration=?, price=? WHERE registration_number=?";
            PreparedStatement preparedStatement = connection.prepareStatement(updateQuery);
            preparedStatement.setString(1, car.getBrand());
            preparedStatement.setString(2, car.getModel());
            preparedStatement.setDate(3, car.getDate_of_first_registration());
            preparedStatement.setInt(4, car.getPrice());
            preparedStatement.setString(5, car.getRegistration_number());
            int rowsAffected = preparedStatement.executeUpdate();
            state = rowsAffected > 0;
        }
        catch (Exception e)
        {
            CarFileFunction.printLog(e.getMessage(), "Error");
        }
        return state;
    }

    @Override
    public List<Car> getAllCar()
    {
        List<Car> cars = new ArrayList<Car>(); 
        
        try
        {
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * from car");
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next())
            {
                String registration_number = resultSet.getString("registration_number");
                String brand = resultSet.getString("brand");
                String model = resultSet.getString("model");
                Date date_of_first_registration = resultSet.getDate("date_of_first_registration");
                int price = resultSet.getInt("price");
                
                cars.add(new Car(registration_number,brand,model,date_of_first_registration,price));
            }
            resultSet.close();
            preparedStatement.close();
        }
        catch (Exception e)
        {
            CarFileFunction.printLog(e.getMessage(), "Error");
        }
        
        return cars;
    }

    @Override
    public List<Car> getAllCarByYear(int year)
    {
        Date years =  Date.valueOf(LocalDate.now().minusYears(year));
        
        List<Car> cars = new ArrayList<Car>(); 

        try
        {
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT registration_number, brand, model, date_of_first_registration, price FROM auto_dealer.car where date_of_first_registration BETWEEN ? AND current_date()");
            preparedStatement.setDate(1, years);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next())
            {
                String registration_number = resultSet.getString("registration_number");
                String brand = resultSet.getString("brand");
                String model = resultSet.getString("model");
                Date date_of_first_registration = resultSet.getDate("date_of_first_registration");
                int price = resultSet.getInt("price");

                cars.add(new Car(registration_number,brand,model,date_of_first_registration,price));
            }
            resultSet.close();
            preparedStatement.close();
        }
        catch (Exception e)
        {
            CarFileFunction.printLog(e.getMessage(), "Error");
        }

        return cars;
    }

    @Override
    public List<Car> getAllCarByPrice(int min, int max)
    {
        List<Car> cars = new ArrayList<Car>(); 

        try
        {
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT registration_number, brand, model, date_of_first_registration, price FROM auto_dealer.car where price BETWEEN ? AND ?");
            preparedStatement.setInt(1, min);
            preparedStatement.setInt(2, max);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next())
            {
                String registration_number = resultSet.getString("registration_number");
                String brand = resultSet.getString("brand");
                String model = resultSet.getString("model");
                Date date_of_first_registration = resultSet.getDate("date_of_first_registration");
                int price = resultSet.getInt("price");

                cars.add(new Car(registration_number,brand,model,date_of_first_registration,price));
            }
            resultSet.close();
            preparedStatement.close();
        }
        catch (Exception e)
        {
            CarFileFunction.printLog(e.getMessage(), "Error");
        }

        return cars;
    }
}