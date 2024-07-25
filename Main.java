package com.carRentalSystem;

public class Main {
    public static void main(String[] args) {
        CarRentalSystem rentalSystem=new CarRentalSystem();
        Car car1 =new Car("c001","Toyota","Camry",60.0);
        Car car2 =new Car("c002","Honda","Accord",70.0);
        Car car3 =new Car("c003","Mahindra","Thar",160.0);
        Car car4 =new Car("c004","Tata","nano",10.0);
        Car car5 =new Car("c005","suzuki","xuv100",40.0);

        rentalSystem.addCar(car1);
        rentalSystem.addCar(car2);
        rentalSystem.addCar(car3);
        rentalSystem.addCar(car4);
        rentalSystem.addCar(car5);
        rentalSystem.menu();

    }
    }
