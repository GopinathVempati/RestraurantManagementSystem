import java.io.*;
import java.util.*;

public class Main extends BaseClass {
    static ArrayList<Inventory> inventories = new ArrayList<>();
    static ArrayList<TotalSales> totalSales = new ArrayList<>();
    static Accounts accounts;
    static ArrayList<Receipe> receipe = new ArrayList<>();
    static double intialAmount = 0;
    static double expensesBeforeIncome = 0;

    public static void main(String[] args) throws IOException {
        getInventoriesList(inventories);
        accounts = getAccountInfo();
        intialAmount = accounts.getMoney();
        getRecepiesList(receipe);

        mainMenu();
    }

    private static void mainMenu() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter any number from 1 to 7 : ");
        int command = scanner.nextInt();
        switch (command) {
            case 1 -> printAvailalbeIngredients(inventories);
            case 2 -> orderSpecificIngredinets(inventories, scanner, accounts);
            case 3 -> {
                for (TotalSales ts : totalSales) {
                    System.out.println(ts.dishNCost());
                }
            }
            case 4 -> System.out.println("Total Expenses : " + expensesBeforeIncome);
            case 5 -> {
                double totalSalesAmount = 0;
                for (TotalSales ts : totalSales) {
                    totalSalesAmount += ts.getCostOfDish();
                }
                System.out.println("Net Profit : " + (totalSalesAmount - expensesBeforeIncome));
            }
            case 6 -> placeOrder(scanner, receipe, inventories, totalSales);
            case 7 -> {
                System.out.println("Exiting now...");
                scanner.nextLine();
                System.exit(0);
            }
        }
        mainMenu();
    }

    private static void placeOrder(Scanner scanner, ArrayList<Receipe> receipe, ArrayList<Inventory> inventories, ArrayList<TotalSales> totalSales) {
        System.out.println("Please Enter your order details : ");
        System.out.println("Please Enter Name of the dish : ");
        String dishName = scanner.next();
        List<Receipe> filter = receipe.stream().filter(inventory -> inventory.getName().equalsIgnoreCase(dishName)).toList();
        if (!filter.isEmpty()) {
            System.out.println("Enter quantity : ");
            int qty = scanner.nextInt();
            Receipe mReceipe = filter.get(0);
            final Double[] priceOfOrder = {0.0};
            List<Ingredients> mReceipeIngredients = mReceipe.getIngredients();
            int requiredAmount = 0;
            Map<String, Double> internalOrder = new HashMap<>();
            for (Inventory value : inventories) {
                for (Ingredients mReceipeIngredient : mReceipeIngredients) {
                    if (value.getName().equalsIgnoreCase(mReceipeIngredient.getIngredientName())) {
                        if (mReceipeIngredient.getQuantity() * qty > value.getQuantity()) {
                            requiredAmount += mReceipeIngredient.getQuantity() * value.getCost();
                            internalOrder.put(value.getName(), (mReceipeIngredient.getQuantity() * qty) - value.getQuantity());
                        } else {
                            value.setQuantity(value.getQuantity() - (mReceipeIngredient.getQuantity() * qty));
                        }
                    }
                }
            }
            if (requiredAmount > accounts.getMoney()) {
                scanner.nextLine();
            } else {
                if (orderIngrdientsInternally(internalOrder)) {
                    inventories.forEach(inventory -> {
                        mReceipeIngredients.forEach(ingredients -> {
                            if (ingredients.getIngredientName().equalsIgnoreCase(inventory.getName())) {
                                priceOfOrder[0] += inventory.getCost() * ingredients.getQuantity();
                            }
                        });
                    });

                    double costOfDish = qty * priceOfOrder[0];
                    totalSales.add(new TotalSales(filter.get(0).name, qty, costOfDish));
                    System.out.println("Your order amount in total for " + qty + dishName + "(s) : " + costOfDish);
                    System.out.println("Yay! your order has been placed successfully ");
                    System.out.println(qty + " " + dishName + " is being prepared at our kitchen");
                }
            }
        } else {
            System.out.println("Sorry the dish your are trying to order is not available, \n " +
                    "Do you want to try with other dish : Type Yes or No");
            String sn = scanner.next();
            if (sn.equalsIgnoreCase("no")) {
                scanner.close();
            } else {
                mainMenu();
            }
        }
    }

    private static boolean orderIngrdientsInternally(Map<String, Double> map) {
        Set<Map.Entry<String, Double>> set = map.entrySet();
        boolean isOrderAvailable = true;
        for (Map.Entry<String, Double> es : set) {
            double quantity = es.getValue();
            double amount = accounts.getMoney();
            List<Inventory> filter = inventories.stream().filter(inventory -> inventory.getName().equalsIgnoreCase(es.getKey())).toList();
            double remainingAmt = amount - (filter.get(0).getCost() * quantity);
            if (remainingAmt > 0) {
                accounts.setMoney(remainingAmt);
                for (Inventory i : inventories) {
                    if (i.getName().equalsIgnoreCase(filter.get(0).getName())) {
                        double u = i.getQuantity() - filter.get(0).getQuantity();
                        i.setQuantity(u);
                        expensesBeforeIncome += quantity * i.getCost();
                    }
                }
                isOrderAvailable = true;
            } else {
                System.out.println("Sorry, you don't have enough funds to place order.");
                isOrderAvailable = false;
                break;
            }
        }
        return isOrderAvailable;
    }

    private static void orderSpecificIngredinets(ArrayList<Inventory> inventories, Scanner scanner, Accounts accounts) {
        System.out.println("Enter ingredients here : ");
        String ingredients = scanner.next();
        List<Inventory> filter = inventories.stream().filter(inventory -> inventory.getName().equalsIgnoreCase(ingredients)).toList();
        if (!filter.isEmpty()) {
            System.out.println("Enter quantity :");
            double quantity = scanner.nextInt();
            double amount = accounts.getMoney();
            double remainingAmt = amount - (filter.get(0).getCost() * quantity);
            if (remainingAmt > 0) {
                accounts.setMoney(remainingAmt);
                System.out.println(accounts.getMoney());
                System.out.println("Requested ingredient order placed successfully!!");
                for (Inventory i : inventories) {
                    if (i.getName().equalsIgnoreCase(filter.get(0).getName())) {
                        double u = i.getQuantity() + quantity;
                        i.setQuantity(u);
                        System.out.println("Updated quantity for " + i.getName() + " are : " + u);
                        expensesBeforeIncome += quantity * i.getCost();
                    }
                }
            } else {
                System.out.println("Sorry, you don't have enough funds to place order.");
            }
        } else {
            System.out.println("The ingredinet you are trying to order is not available at the moment");
        }
    }

    private static void printAvailalbeIngredients(ArrayList<Inventory> inventories) {
        System.out.println("Available ingredients are : ");
        for (Inventory i : inventories) {
            System.out.println(i.getName() + " : " + i.getQuantity());
        }
    }
}
