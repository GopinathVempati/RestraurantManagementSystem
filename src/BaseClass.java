import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class BaseClass {

    static ArrayList<Receipe> getRecepiesList(ArrayList<Receipe> receipe) throws IOException {
        File reciepe = new File("/home/gopinath/Desktop/PrepForTech/RestraurantManagementSystem/src/TextFiles/receipe.txt");
        BufferedReader br = new BufferedReader(new FileReader(reciepe));
        String st;
        while ((st = br.readLine()) != null) {
            ArrayList<Ingredients> ingredients = new ArrayList<>();
            String[] values = st.split(" ");
            if(values.length>0) {
                for (int i = 1; i <= values.length - 2; i++) {
                    if (i % 2 == 1) {
                        ingredients.add(new Ingredients(values[i], Double.parseDouble(values[i + 1])));
                    }
                }
                receipe.add(new Receipe(values[0], ingredients));
            }
        }
        return receipe;
    }

    static Accounts getAccountInfo() throws IOException {
        File account = new File("/home/gopinath/Desktop/PrepForTech/RestraurantManagementSystem/src/TextFiles/accounts.txt");
        BufferedReader br = new BufferedReader(new FileReader(account));
        String st;
        Accounts accounts = null;
        while ((st = br.readLine()) != null) {
            accounts = new Accounts(Long.parseLong(st));
        }
        return accounts;
    }

    static void getInventoriesList(ArrayList<Inventory> inventories) throws IOException {
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
