import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class Main extends BaseClass{
    public static void main(String[] args) throws IOException {
        /*Holds the list of invetory present in restrauant*/
        ArrayList<Inventory> inventories = new ArrayList<>();
        /*Holds intial amount present with the restrauant*/
        Accounts accounts;
        /*Holds details of each receipe*/
        ArrayList<Receipe> receipe = new ArrayList<>();

        getInventoriesList(inventories);
        accounts = getAccountInfo();
        System.out.println(accounts.getMoney());

        getRecepiesList(receipe);
        for (Receipe r : receipe){
            for (Ingredients ingredients : r.getIngredients())
                System.out.println(r.getName()+" "+ingredients.getIngredientName()+" "+ingredients.getQuantity());
        }

        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter a command : ");
        int command = scanner.nextInt();
        switch (command) {
            case 0 -> {

            }
        }
    }
}
