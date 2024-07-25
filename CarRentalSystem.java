package com.carRentalSystem;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class CarRentalSystem {
    private List<Car> cars;
    private List<Customer> customers;
    private List<Rental> rentals;

    public CarRentalSystem(){
        cars=new ArrayList<>();
        customers=new ArrayList<>();
        rentals=new ArrayList<>();
    }
    public void addCar(Car car){
        cars.add(car);
    }
    public void addCustomer(Customer customer){
    customers.add(customer);
    }
    public void rentCar(Car car, Customer customer, int days){
        if(car.isAvailable()){
            car.rent();
            rentals.add(new Rental(car,customer,days));
        }
        else{
            System.out.println("Car is not available for rent. ");
        }
    }
    public void returnCar(Car car){
        car.returnCar();
        Rental rentalToRemove = null;
        for(Rental rental : rentals){
            if(rental.getCar() == car){
                rentalToRemove = rental;
                break;
            }
        }
        if(rentalToRemove != null){
            rentals.remove(rentalToRemove);
            System.out.println("Car returned Successfullly ");
        }else{
            System.out.println("Car was not rented.");
        }
    }
    public void menu(){
        Scanner sc=new Scanner(System.in);
        while(true){
            System.out.println("============================= Road Trip Rentals   ================================= ");
            System.out.println("  1. Rent a Car  ");
            System.out.println("  2. Return a car  ");
            System.out.println("  3. Exit  ");
            System.out.print("  Enter Your Choise :");
            int choise =sc.nextInt();
            sc.nextLine();

            if(choise ==1){
                System.out.println("\n ===== Rent a Car =====\n");
                System.out.print("Enter your Name : ");
                String customerName=sc.nextLine();

                System.out.println("\n Available Cars : ");
                for(Car car:cars){
                    if(car.isAvailable()){
                        System.out.println(car.getCarId() +"  -  " +car.getCarBrand() +"   " +car.getCarModel());
                    }
                }

                System.out.print("\n Enter the car ID you want to rent :");
                String carId=sc.nextLine();

                System.out.print("Enter the number of days for rental : ");
                int days=sc.nextInt();
                sc.nextLine();

                Customer newcustomer=new Customer("cus" +(customers.size()+1),customerName);
                addCustomer(newcustomer);

                Car selectedCar =null;
                for(Car car: cars){
                    if(car.getCarId().equals(carId) && car.isAvailable()){
                        selectedCar =car;
                        break;
                    }
                }
                if(selectedCar !=null){

                    double totalPrice =selectedCar.calculatePrice(days);
                    System.out.println("\n=====  RENTAL INFORMATION ======\n");
                    System.out.println("Customer Name : " +newcustomer.getCustomerId());
                    System.out.println(" Customer Id: " +newcustomer.getName());
                    System.out.println("Car : " +selectedCar.getCarBrand()  +" " +selectedCar.getCarModel());
                    System.out.println("Rental Days :" +days);
                    System.out.println("Total Price : $%.2f%n");
                    System.out.println(totalPrice);

                    System.out.print("\n Confirm Rental (Y/N) : ");
                    String confirm=sc.nextLine();

                    if(confirm.equalsIgnoreCase("Y")){
                        rentCar(selectedCar,newcustomer,days);
                        System.out.println("\n Car Rented Successfully. ");
                    }else {
                        System.out.println("\nRental canceled.");
                    }
                }else{
                    System.out.println("\n Invalid car selection or car not available for rent.");
                }
                System.out.println();
            }
            else if (choise==2) {
                System.out.println("\n===== Return a car =====\n");
                System.out.println("Enter the new car ID you want to return. ");
                String carId =sc.nextLine();

                Car carToReturn=null;
                for(Car car: cars){
                    if(car.getCarId().equals(carId) &&! car.isAvailable()){
                        carToReturn =car;
                        break;
                    }
                }
                if(carToReturn !=null){
                    Customer customer=null;
                    for(Rental rental:rentals){
                        if(rental.getCar()==carToReturn){
                            customer =rental.getCustomer();
                            break;
                        }
                    }
                    if (customer !=null){
                        returnCar(carToReturn);
                        System.out.println("Car returned successfully by : " +customer.getName());
                    }else{
                        System.out.println("Car was not rented or rental information is missing. ");
                    }
                }else{
                    System.out.println("Invalid car ID or car is not rented. ");
                }
                System.out.println();
            } else if (choise==3) {
                break;
            }else{
                System.out.println("Invalid choise. please enter a valid option. ");
            }
        }
        System.out.println("\n Thanks you for using the car rental System. ");
    }

}
