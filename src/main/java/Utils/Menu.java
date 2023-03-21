package Utils;

import java.util.Scanner;

public class Menu {


    public static Byte menu7Options(){
        Scanner sc = new Scanner(System.in);
        byte userOption;
        final byte MINIMUM = 0;
        final byte MAXIMUM = 7;

        do {
            System.out.println("\nMain Menu");
            System.out.println("1. Show all items from one vendor ");
            System.out.println("2. Show all vendors of a city");
            System.out.println("3. Show cheapest item of all vendors");
            System.out.println("4. Show all items of the same type");
            System.out.println("5. Buy one item");
            System.out.println("6. Sell one item ");
            System.out.println("7. Save data in text document ");
            System.out.println("0. Exit the app.\n");
            userOption = sc.nextByte();
            if (userOption < MINIMUM || userOption > MAXIMUM) {
                System.out.println("Choose a valid option");
            }
        } while (userOption < MINIMUM || userOption > MAXIMUM);

        return userOption;
    }



}