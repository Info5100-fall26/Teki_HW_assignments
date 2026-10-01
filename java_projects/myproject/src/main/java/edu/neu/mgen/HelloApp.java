package edu.neu.mgen;

// This is my first Java program. Really excited to learn more in the class!
/*
* This is a simple Java program to print a message that shows that this is my first Java program.   
* From the lecture I learned about debugging and how a minor syntax error can change the whole program. 
*/
import java.util.Scanner;

public class HelloApp { 
    public static void main(String[] args) {
        System.out.println("It is my first Java program");
        // HW for Chapter 4
        int intVar1 = 8;
        int intVar2 = 4;

        long longVar1 = 4000000000L;
        long longVar2 = 2000000000L;

        double doubleVar1 = 3.14;
        double doubleVar2 = 6.71;

        boolean boolVar1 = true;
        boolean boolVar2 = false;

        char charVar1 = 'Y';
        char charVar2 = 'T';

    long convertedFromInt = intVar1;
    int convertedFromLong = (int) longVar1; 

    Scanner input = new Scanner(System.in);
    System.out.print("Enter a number: ");
    int userInput = input.nextInt();

    System.out.print("Enter a long: ");
    long userLongInput = input.nextLong();

    System.out.print("Enter a double: ");
    double userDoubleInput = input.nextDouble();

    System.out.print("Enter a true/false: ");
    boolean userBoolInput = input.nextBoolean();

    //Lecture arthemetic requirements 
    int sum = intVar1 + intVar2;
    System.out.println("The sum of: " + sum);

    int difference = intVar1 - intVar2;
    System.out.println("The difference:" + difference);

    boolean andResult = boolVar1 && boolVar2;
    System.out.println("AND result: " + andResult);

    boolean notResult = !boolVar1;
    System.out.println("NOT result: " + notResult);

        


    }
    
}
