package org.example;

import java.sql.Date;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Scanner;

public class CarMenu
{
    private CarRepository carBD = new CarBDRepositoryImpl();
    private static int value;

    public CarMenu()
    {

    }

    public void startMenu()
    {
        System.out.println("\nLecture du fichier data.txt");
        System.out.println("");
        System.out.println("--- \t Affichage de toutes les voitures du parc automobile \t ---");
        System.out.println("");
        
        List<Car> cars = CarFileFunction.getFileCars();
        
        for (Car car : cars)
        {
            System.out.println("numéro d’immatriculation : " + car.getRegistration_number() + ", marque : " + car.getBrand() + ", modèle : " + car.getModel() + ", date de mise en circulation : " + car.getDate_of_first_registration().toString() + ", prix de vente : " + car.getPrice());
        }

        System.out.println("");
        System.out.println("--- \t Affichage de toutes les voitures du parc automobile de moins de 20ans\t ---");
        System.out.println("");

        for (Car car : cars)
        {
            LocalDate carDate = car.getDate_of_first_registration().toLocalDate();
            LocalDate currentDate = LocalDate.now();

            long diffInYears = ChronoUnit.YEARS.between(carDate, currentDate);

            if (diffInYears < 20)
            {
                System.out.println("numéro d’immatriculation : " + car.getRegistration_number() + ", marque : " + car.getBrand() + ", modèle : " + car.getModel() + ", date de mise en circulation : " + car.getDate_of_first_registration().toString() + ", prix de vente : " + car.getPrice());
                carBD.addCar(car);
            }
        }
    }

    private void checkValue(int min, int max)
    {
        Scanner scanner = new Scanner(System.in);

        System.out.println("\nEntrer une valeur entre " + min + " et " + max + " : ");

        try
        {
            value = scanner.nextInt();
        }
        catch (Exception e)
        {
            checkValue(min, max);
        }

        if (value < min || value > max)
        {
            checkValue(min, max);
        }
    }

    public void showMenu()
    {
        System.out.println("\nBienvenue dans le Menu : ");
        System.out.println("");
        System.out.println("1 : Afficher les véhicules");
        System.out.println("2 : Modifier un véhicule");
        System.out.println("3 : Ajouter un véhicule");
        System.out.println("4 : Supprimer un véhicule");
        System.out.println("5 : affciher les véhicules d'un âge donné");
        System.out.println("6 : Afficher les véhicules dont le prix est compris entre prix minimal et maximal");
        System.out.println("7 : Quitter");

        checkValue(1,7);

        switch (value)
        {
            case 1:
                displayAllCar();
                break;
            case 2:
                editCar();
                break;
            case 3:
                newCar();
                break;
            case 4:
                removeCar();
                break;
            case 5:
                displayAllCarByYear();
                break;
            case 6:
                displayAllCarByPrice();
                break;
            case 7:
                System.out.println("\nFermeture de l'application");
                break;
        }
    }
    
    private void newCar()
    {
        System.out.println("--- \t ajout d'un véhicule \t ---");

        System.out.println("\nVeuillez rentrer le  numéro d’immatriculation de la voiture");
        Scanner scanner = new Scanner(System.in);
        String registration_number = scanner.nextLine();

        System.out.println("\nVeuillez rentrer la marque");
        String brand = scanner.nextLine();

        System.out.println("\nVeuillez rentrer la model");
        String model = scanner.nextLine();

        System.out.println("\nVeuillez rentrer la date de mise en circulation, example : yyyy-MM-dd");
        String date_of_first_registration = scanner.nextLine();

        System.out.println("\nVeuillez rentrer le prix");
        checkValue(1,1000000000);

        if (carBD.addCar(new Car(registration_number, brand,model, Date.valueOf(date_of_first_registration), value)))
        {
            CarFileFunction.printLog("L'ajout du véhicule ,c'est passé avec success", "Success");
        }
        else
        {
            CarFileFunction.printLog("L'ajout du véhicule à échoué", "Error");
        }

        showMenu();
    }
    
    private void editCar()
    {
        System.out.println("--- \t Modification d'un véhicule \t ---");

        System.out.println("\nVeuillez rentrer le  numéro d’immatriculation de la voiture à modifier");
        Scanner scanner = new Scanner(System.in);
        String registration_number = scanner.nextLine();

        System.out.println("\nVeuillez rentrer la nouvelle marque");
        String brand = scanner.nextLine();

        System.out.println("\nVeuillez rentrer la nouveau model");
        String model = scanner.nextLine();

        System.out.println("\nVeuillez rentrer la nouvelle date de mise en circulation, example : yyyy-MM-dd");
        String date_of_first_registration = scanner.nextLine();

        System.out.println("\nVeuillez rentrer le nouveau prix");
        checkValue(1,1000000000);

        if (carBD.updateCar(new Car(registration_number, brand,model, Date.valueOf(date_of_first_registration), value)))
        {
            CarFileFunction.printLog("La modification du véhicule ,c'est passé avec success", "Success");
        }
        else
        {
            CarFileFunction.printLog("La modification du véhicule à échoué", "Error");
        }

        showMenu();
    }
    
    private void removeCar()
    {
        System.out.println("--- \t Suppression d'un véhicule \t ---");

        System.out.println("\nVeuillez rentrer le  numéro d’immatriculation de la voiture à supprimer");
        Scanner scanner = new Scanner(System.in);
        String registration_number = scanner.nextLine();

        if (carBD.deleteCar(registration_number))
        {
            CarFileFunction.printLog("La suppression du véhicule ,c'est passé avec success", "Success");
        }
        else
        {
            CarFileFunction.printLog("Echec de la suppression du véhicule", "Error");
        }

        showMenu();
    }
    
    private void displayAllCar()
    {
        System.out.println("--- \t Voici toutes les voitures du parc actuellement \t ---");
        System.out.println("");

        List<Car> cars = carBD.getAllCar();

        for (Car car : cars)
        {
            System.out.println("numéro d’immatriculation : " + car.getRegistration_number() + ", marque : " + car.getBrand() + ", modèle : " + car.getModel() + ", date de mise en circulation : " + car.getDate_of_first_registration().toString() + ", prix de vente : " + car.getPrice());
        }

        showMenu();
    }
    
    private void displayAllCarByYear()
    {
        System.out.println("--- \t Voici toutes les voitures du parc \t ---");
        
        System.out.println("\nVeuillez rentrer un age");
        checkValue(1,100);

        List<Car> cars = carBD.getAllCarByYear(value);
        
        System.out.println("");

        for (Car car : cars)
        {
            System.out.println("numéro d’immatriculation : " + car.getRegistration_number() + ", marque : " + car.getBrand() + ", modèle : " + car.getModel() + ", date de mise en circulation : " + car.getDate_of_first_registration().toString() + ", prix de vente : " + car.getPrice());
        }

        showMenu();
    }
    
    private void displayAllCarByPrice()
    {
        System.out.println("--- \t Voici toutes les voitures du parc \t ---");

        System.out.println("\nVeuillez rentrer un prix minimal");
        checkValue(1,1000000000);
        int min = value;
        
        System.out.println("\nVeuillez rentrer un prix maximal");
        checkValue(1,1000000000);
        int max = value;

        List<Car> cars = carBD.getAllCarByPrice(min,max);

        System.out.println("");

        for (Car car : cars)
        {
            System.out.println("numéro d’immatriculation : " + car.getRegistration_number() + ", marque : " + car.getBrand() + ", modèle : " + car.getModel() + ", date de mise en circulation : " + car.getDate_of_first_registration().toString() + ", prix de vente : " + car.getPrice());
        }

        showMenu();
    }
}