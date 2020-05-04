package Ulzhan;

import java.util.ArrayList;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        DBManager dbManager = new DBManager();
        dbManager.connect();
        Scanner in = new Scanner(System.in);

        while(true){
            System.out.println("PRESS 1 TO ADD CAR");
            System.out.println("PRESS 2 TO LIST CARS");
            System.out.println("PRESS 3 TO DELETE CAR");
            System.out.println("PRESS 0 TO EXIT");
            String choice = in.next();

            if(choice.equals("1")){
                System.out.println("Insert name:");
                String name = in.next();
                System.out.println("Insert engine volume:");
                double volume = in.nextDouble();
                System.out.println("Insert price:");
                int price = in.nextInt();
                Car c = new Car(null, name, price, volume);
                dbManager.addCar(c);

            }else if(choice.equals("2")){
                ArrayList<Car> cars = dbManager.getAllCars();
                for (Car c : cars){
                    System.out.println(c);
                }

            }else if(choice.equals("3")){
                System.out.println("Insert id of a car");
                Long id = in.nextLong();
                dbManager.deleteCar(id);

            } else if(choice.equals("0")){
                System.exit(0);
            }
        }


    }
}
