import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {
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
        // System.out.println("Enter a command : ");
        int command = scanner.nextInt();
        switch (command) {
            case 0 -> {

            }
        }
    }

    private static ArrayList<Receipe> getRecepiesList(ArrayList<Receipe> receipe) throws IOException {
        File reciepe = new File("/home/gopinath/Desktop/PrepForTech/RestraurantManagementSystem/src/TextFiles/receipe.txt");
        BufferedReader br = new BufferedReader(new FileReader(reciepe));
        String st;
        while ((st = br.readLine()) != null) {
            ArrayList<Ingredients> ingredients = new ArrayList<>();
            String[] values = st.split(" ");
            for (int i = 1; i <= values.length - 2; i++) {
                if (i % 2 == 1) {
                    ingredients.add(new Ingredients(values[i], Double.parseDouble(values[i + 1])));
                }
            }
            receipe.add(new Receipe(values[0], ingredients));
        }
        return receipe;
    }

    private static Accounts getAccountInfo() throws IOException {
        File account = new File("/home/gopinath/Desktop/PrepForTech/RestraurantManagementSystem/src/TextFiles/accounts.txt");
        BufferedReader br = new BufferedReader(new FileReader(account));
        String st;
        Accounts accounts = null;
        while ((st = br.readLine()) != null) {
            accounts = new Accounts(Long.parseLong(st));
        }
        return accounts;
    }

    private static void getInventoriesList(ArrayList<Inventory> inventories) throws IOException {
        File ingredientTxtFile = new File("/home/gopinath/Desktop/PrepForTech/RestraurantManagementSystem/src/TextFiles/inventory.txt");
        BufferedReader br = new BufferedReader(new FileReader(ingredientTxtFile));
        String st;
        while ((st = br.readLine()) != null) {
            String[] arr = st.split(" ");
            Inventory ingredients1 = new Inventory(arr[0], Integer.parseInt(arr[1]), Integer.parseInt(arr[2]));
            inventories.add(ingredients1);
        }
    }
}
