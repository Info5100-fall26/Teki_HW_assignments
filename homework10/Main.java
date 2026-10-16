// Main.java - the whole Vehicle program

import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        ArrayList<Vehicle> fleet = new ArrayList<Vehicle>();
        fleet.add(new Car("Toyota", "Camry", 2024, 210, 5, 4, "Hybrid"));
        fleet.add(new Motorbike("Harley-Davidson", "Street Glide", 2023, 180, 2, 1868, false));
        fleet.add(new Aircraft("Boeing", "787 Dreamliner", 2022, 900, 296, 13000, 14000));
        fleet.add(new Ship("Royal Caribbean", "Icon of the Seas", 2024, 41, 5610, 250800, 20));

        // Vehicle v = new Vehicle(...);   // would NOT compile: Vehicle is abstract

        System.out.println("===== THE FLEET =====");
        for (Vehicle v : fleet) {
            v.displayInfo();
            System.out.println();
        }

        System.out.println("===== WATCH THEM MOVE =====");
        for (Vehicle v : fleet) {
            v.move();      
        }
        System.out.println();

        // Q1 
        System.out.println("Q1: Which vehicle is the fastest?");
        Vehicle fastest = fleet.get(0);
        for (Vehicle v : fleet) {
            if (v.getMaxSpeed() > fastest.getMaxSpeed()) {
                fastest = v;
            }
        }
        System.out.println("   The " + fastest.getBrand() + " " + fastest.getModel()
                + " at " + fastest.getMaxSpeed() + " km/h.");
        System.out.println();

        //Q2
        double distance = 600;   
        System.out.println("Q2: How long would each vehicle take to travel " + distance + " km?");
        for (Vehicle v : fleet) {
            System.out.printf("   %-10s %6.1f hours%n", v.getVehicleType() + ":", v.calculateTravelTime(distance));
        }
        System.out.println("   (Aircraft includes 0.5 hours for takeoff and landing.)");
        System.out.println();

        //Q3
        System.out.println("Q3: Which vehicles can get from San Francisco to Honolulu (across the Pacific)?");
        for (Vehicle v : fleet) {
            String answer = v.canCrossOcean() ? "YES" : "no  (travels by " + v.getTravelMedium() + ")";
            System.out.println("   " + v.getVehicleType() + ": " + answer);
        }
        System.out.println();

        //Q4
        int groupSize = 250;
        System.out.println("Q4: A group of " + groupSize + " people needs to travel together. Which vehicles fit them all?");
        for (Vehicle v : fleet) {
            if (v.getPassengerCapacity() >= groupSize) {
                System.out.println("   " + v.getVehicleType() + " (" + v.getModel() + ") - holds "
                        + v.getPassengerCapacity());
            }
        }
        System.out.println();

        //Q5
        System.out.println("Q5: What license do you need to operate each vehicle?");
        for (Vehicle v : fleet) {
            System.out.println("   " + v.getVehicleType() + ": " + v.requiredLicense());
        }
        System.out.println();

        
        System.out.println("===== SPECIAL ACTIONS =====");
        
        for (Vehicle v : fleet) {
            if (v instanceof Car) {
                ((Car) v).openTrunk();
            } else if (v instanceof Motorbike) {
                ((Motorbike) v).wheelie();
            } else if (v instanceof Ship) {
                ((Ship) v).dropAnchor();
            } else if (v instanceof Aircraft) {
                System.out.println("The " + v.getModel() + " can fly "
                        + ((Aircraft) v).getRangeKm() + " km without refueling.");
            }
        }
    }
}


// Licensable.java
interface Licensable {
    public String requiredLicense();
}


// Vehicle.java

abstract class Vehicle implements Licensable {

    protected String brand;
    protected String model;
    protected int year;
    protected double maxSpeed;          // km/h
    protected int passengerCapacity;

    public Vehicle(String brand, String model, int year, double maxSpeed, int passengerCapacity) {
        this.brand = brand;
        this.model = model;
        this.year = year;
        this.maxSpeed = maxSpeed;
        this.passengerCapacity = passengerCapacity;
    }

    //Abstract methods
    public abstract String getVehicleType();
    public abstract void move();
    public abstract String getTravelMedium();     
    public abstract boolean canCrossOcean();

    //Regular methods

    public double calculateTravelTime(double distanceKm) {
        return distanceKm / maxSpeed;
    }

    public void displayInfo() {
        System.out.println(getVehicleType() + ": " + year + " " + brand + " " + model);
        System.out.println("   Travels by: " + getTravelMedium()
                + " | Max speed: " + maxSpeed + " km/h"
                + " | Passengers: " + passengerCapacity);
    }

    // Getters
    public String getBrand() { return brand; }
    public String getModel() { return model; }
    public int getYear() { return year; }
    public double getMaxSpeed() { return maxSpeed; }
    public int getPassengerCapacity() { return passengerCapacity; }
}


// Car.java
class Car extends Vehicle {
    private int numberOfDoors;
    private String fuelType;

    public Car(String brand, String model, int year, double maxSpeed, int passengerCapacity,
               int numberOfDoors, String fuelType) {
        super(brand, model, year, maxSpeed, passengerCapacity);   // calls Vehicle's constructor
        this.numberOfDoors = numberOfDoors;
        this.fuelType = fuelType;
    }

    public String getVehicleType() { return "Car"; }

    public void move() {
        System.out.println("The " + brand + " " + model + " drives on four wheels down the highway.");
    }

    public String getTravelMedium() { return "road"; }

    public boolean canCrossOcean() { return false; }

    public String requiredLicense() { return "Standard driver's license (Class C)"; }

    // Method only Cars have
    public void openTrunk() {
        System.out.println("The " + model + "'s trunk is open.");
    }

    public void displayInfo() {
        super.displayInfo();
        System.out.println("   Doors: " + numberOfDoors + " | Fuel: " + fuelType);
    }
}


// Motorbike.java
class Motorbike extends Vehicle {
    private int engineCC;
    private boolean hasSidecar;

    public Motorbike(String brand, String model, int year, double maxSpeed, int passengerCapacity,
                     int engineCC, boolean hasSidecar) {
        super(brand, model, year, maxSpeed, passengerCapacity);
        this.engineCC = engineCC;
        this.hasSidecar = hasSidecar;
    }

    public String getVehicleType() { return "Motorbike"; }

    public void move() {
        System.out.println("The " + brand + " " + model + " leans into the curves on two wheels.");
    }

    public String getTravelMedium() { return "road"; }

    public boolean canCrossOcean() { return false; }

    public String requiredLicense() { return "Motorcycle license (Class M1)"; }

    // Method 
    public void wheelie() {
        System.out.println("The " + model + " pops a wheelie!");
    }

    public void displayInfo() {
        super.displayInfo();
        System.out.println("   Engine: " + engineCC + " cc | Sidecar: " + (hasSidecar ? "yes" : "no"));
    }
}


// Aircraft.java
class Aircraft extends Vehicle {
    private double maxAltitude;     
    private double rangeKm;         

    public Aircraft(String brand, String model, int year, double maxSpeed, int passengerCapacity,
                    double maxAltitude, double rangeKm) {
        super(brand, model, year, maxSpeed, passengerCapacity);
        this.maxAltitude = maxAltitude;
        this.rangeKm = rangeKm;
    }

    public String getVehicleType() { return "Aircraft"; }

    public void move() {
        System.out.println("The " + brand + " " + model + " takes off and cruises at "
                + maxAltitude + " meters.");
    }

    public String getTravelMedium() { return "air"; }

    public boolean canCrossOcean() { return rangeKm >= 4000; }

    public String requiredLicense() { return "Airline Transport Pilot license (ATP)"; }

    public double calculateTravelTime(double distanceKm) {
        return super.calculateTravelTime(distanceKm) + 0.5;
    }

    public double getRangeKm() { return rangeKm; }

    public void displayInfo() {
        super.displayInfo();
        System.out.println("   Max altitude: " + maxAltitude + " m | Range: " + rangeKm + " km");
    }
}


// Ship.java
class Ship extends Vehicle {
    private double tonnage;
    private int numberOfDecks;

    public Ship(String brand, String model, int year, double maxSpeed, int passengerCapacity,
                double tonnage, int numberOfDecks) {
        super(brand, model, year, maxSpeed, passengerCapacity);
        this.tonnage = tonnage;
        this.numberOfDecks = numberOfDecks;
    }

    public String getVehicleType() { return "Ship"; }

    public void move() {
        System.out.println("The " + brand + " " + model + " sails across the open sea.");
    }

    public String getTravelMedium() { return "water"; }

    public boolean canCrossOcean() { return true; }

    public String requiredLicense() { return "Master mariner (ship captain) license"; }

    // Method
    public void dropAnchor() {
        System.out.println("The " + model + " drops anchor.");
    }

    public void displayInfo() {
        super.displayInfo();
        System.out.println("   Tonnage: " + tonnage + " tons | Decks: " + numberOfDecks);
    }
}