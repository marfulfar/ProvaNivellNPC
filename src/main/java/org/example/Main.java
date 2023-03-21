package org.example;

import Objects.*;
import Utils.WindowManager;
import comparators.CustomComparatorType;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Path;
import java.util.*;
import java.util.stream.Collectors;

import static Utils.Menu.menu7Options;

public class Main {

    static final Scanner SC = new Scanner(System.in);
    static final List<String> TYPES = Arrays.asList(new String[]{"restoration","combat","craft"});


    public static void main(String[] args) throws IOException {

        boolean exit = false;
        List<City> cities = new ArrayList<>();

        Vendor mario = new Farmer("Mario");
        Vendor luigi = new Merchant("Luigi");
        Vendor peach = new Thief("Peach");

        mario.sellOneItem(new Item("Potion","restoration",5,100));
        mario.sellOneItem(new Item("Sword","combat",150,100));
        mario.sellOneItem(new Item("Leather","craft",60,100));
        luigi.sellOneItem(new Item("Charcoal","craft",45,100));
        luigi.sellOneItem(new Item("Shield","combat",75,100));
        peach.sellOneItem(new Item("Bandage","restoration",20,100));

        Vendor sonic = new Merchant("Sonic");
        Vendor tails = new Farmer("Tails");
        Vendor knuckles = new Thief("Knuckles");

        sonic.sellOneItem(new Item("Axe","combat",200,100));
        tails.sellOneItem(new Item("Medical tape","restoration",30,100));
        tails.sellOneItem(new Item("Pills","restoration",50,100));
        knuckles.sellOneItem(new Item("Metal scrap","craft",15,100));
        knuckles.sellOneItem(new Item("Glue","craft",35,100));
        knuckles.sellOneItem(new Item("Dagger","combat",80,100));

        City bcn = new City("Barcelona");
        City girona = new City("Girona");
        cities.add(bcn);
        bcn.getVendors().add(mario);
        bcn.getVendors().add(luigi);
        bcn.getVendors().add(peach);

        cities.add(girona);
        girona.getVendors().add(sonic);
        girona.getVendors().add(tails);
        girona.getVendors().add(knuckles);

        do {
            switch (menu7Options()) {
                case 1:
                    showAllItemsOneVendor(cities);
                    break;
                case 2:
                    showAllVendorsCity(cities);
                    break;
                case 3:
                    showCheapestItemAllVendors(cities);
                    break;
                case 4:
                    showAllItemsSameTypeASC(cities);
                    break;
                case 5:
                    buyOneItem(cities);
                    break;
                case 6:
                    sellOneItem(cities);
                    break;
                case 7:
                    saveDataTXT(cities);
                    break;
                case 0:
                    System.out.println("Thank you for using this app!");
                    exit = true;
                    break;
            }
        } while (!exit);



    }//closes main

    private static void saveDataTXT(List<City> cities) throws IOException {

        System.out.println("Choose a folder from the prompt to save the data");
        String path = WindowManager.getPath();
        if(path.isBlank()){
            path = ".";
        }
        File saveData = new File(path+"/NPC_data.txt");
        FileWriter myFileWriter = new FileWriter(saveData);

        try {
            for (City city : cities) {
                List<Vendor> vendors = city.getVendors();

                for (Vendor vendor : vendors) {
                    List<Item> items = vendor.getStock();

                    for (Item item : items) {
                        myFileWriter.write((city.getName() + "-" + vendor.getName() + "-" + item.getName() + "-" + item.getType() + "-" + item.getPrice() + "-" + item.getHealth())+"\n");
                    }

                }
            }
            System.out.println("Successfully written data!");
        }catch(IOException ioe){
            System.out.println("Error writing the file! Please try again.");
        }finally {
            myFileWriter.close();
        }


    }

    private static void showCheapestItemAllVendors(List<City> cities) {

        City myCity = cityChooser(cities);
        List<Vendor> vendors = myCity.getVendors();

        List<Item> items = new ArrayList<>();

        for (Vendor vendor:vendors) {
            items.addAll(vendor.getStock());
        }

        List<Item> cheapestItem = items.stream().sorted((Item i1, Item i2)-> (int)i1.getPrice() - (int)i2.getPrice()).limit(1).collect(Collectors.toList());

        System.out.println("Cheapest item available in the city is: ");
        System.out.println(cheapestItem.get(0).getName() +" - Price: "+ cheapestItem.get(0).getPrice());

    }

    private static void showAllItemsSameTypeASC(List<City> cities) {

        boolean typeInRange = false;

        City myCity = cityChooser(cities);

        List<Vendor> vendors = myCity.getVendors();
        List<Item> items = new ArrayList<>();

        for (Vendor vendor:vendors) {
            items.addAll(vendor.getStock());
        }

        do{
            System.out.println("Write what type of item do you want to see:");
            for (String type:TYPES) {
                System.out.println(type);
            }
            String userTypeChoice = SC.next();

            if (TYPES.contains(userTypeChoice.toLowerCase())){
                typeInRange=true;
                System.out.println("List of items of type: "+ userTypeChoice +" available. Ordered by Price ascending.");
                List<Item> filteredList = items.stream().filter(i-> i.getType().equalsIgnoreCase(userTypeChoice)).sorted(new CustomComparatorType()).collect(Collectors.toList());

                for (Item item:filteredList) {
                    System.out.println(item.getName() +" - price:  "+ item.getPrice());
                }
            }else{
                System.out.println("Type not recognized. Please try again!");
            }
        }while(!typeInRange);


    }

    private static void buyOneItem(List<City> cities) {

        Vendor myVendor = showAllItemsOneVendor(cities);

        System.out.println("What item do you want to buy?");
        int userChoice = SC.nextInt();

        myVendor.getStock().remove(userChoice-1);
        System.out.println("Item bought correctly!");

    }

    private static void showAllVendorsCity(List<City> cities) {

        City myCity = cityChooser(cities);

        List<Vendor> vendors = myCity.getVendors();

        System.out.println("These are all the vendors of the chosen city:");
        for (Vendor vendor:vendors) {
            System.out.println(vendor.getName());
        }

    }

    private static void sellOneItem(List<City> cities) {
        boolean typeInRange = false;
        String userTypeChoice;

        City myCity = cityChooser(cities);
        List<Vendor> vendors = myCity.getVendors();

        Vendor myVendor = vendorChooser(vendors);

        if (myVendor.getStock().size() < myVendor.getStockLimit()) {

            System.out.println("Type in the following fields:\nName of the item:");
            String userNameItem = SC.next();

            do{
                System.out.println("Type of item:");
                for (String type:TYPES) {
                    System.out.println(type);
                }
                userTypeChoice = SC.next();
                if (TYPES.contains(userTypeChoice.toLowerCase())){
                    typeInRange=true;
                }else{
                    System.out.println("Type not recognized. Please try again!");
                }
            }while(!typeInRange);

            System.out.println("Price of the item");
            float userPriceItem = SC.nextFloat();

            myVendor.sellOneItem(new Item(userNameItem,userTypeChoice,userPriceItem,100));
            System.out.println("Item sold to the vendor!");

        } else {
            System.out.println("Stock full! The vendor cannot buy your item!");

        }

    }

    private static Vendor showAllItemsOneVendor(List<City> cities) {

        City myCity = cityChooser(cities);
        List<Vendor> vendors = myCity.getVendors();

        Vendor myVendor = vendorChooser(vendors);

        List<Item> stock = myVendor.getStock();

        System.out.println("This is the stock of the vendor:");
        int i = 1;
        for (Item item:stock) {
            System.out.println(i + " - " + item.getName() + " - price: " + item.getPrice() + " - health: " + item.getHealth());
            i++;
        }

        return myVendor;
    }


    private static City cityChooser(List<City> cities){
        boolean inRange = false;
        int userChoice = 0;

        do{
            System.out.println("Choose what city do you want to check");
            int i =1;
            for (City city:cities) {
                System.out.println(i + " - " + city.getName());
                i++;
            }

            userChoice = SC.nextInt();
            if (userChoice >=1 && userChoice <= cities.size()){
                inRange=true;
            }else{
                System.out.println("Your choice is not in the correct range. Please try again.");
            }

        }while(inRange==false);

        City myCity = cities.get(userChoice -1);

        return myCity;
    }

    private static Vendor vendorChooser(List<Vendor> vendors){
        boolean vendorInRange = false;
        int userVendor = 0;

        do{
            System.out.println("Choose what vendor do you want to check");
            int i =1;
            for (Vendor vendor:vendors) {
                System.out.println(i + " - " + vendor.getName());
                i++;
            }

            userVendor = SC.nextInt();
            if (userVendor >=1 && userVendor <= vendors.size()){
                vendorInRange=true;
            }else{
                System.out.println("Your choice is not in the correct range. Please try again.");
            }
        }while(vendorInRange == false);

        Vendor myVendor = vendors.get(userVendor-1);

        return myVendor;
    }


}//closes class