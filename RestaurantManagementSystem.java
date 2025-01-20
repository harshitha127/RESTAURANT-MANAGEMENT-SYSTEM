import java.util.Scanner;

// Abstract class for Cuisine
abstract class Cuisine {
    public abstract void getMenu();
    public abstract double getPrice(String item);
}

// Italian Cuisine Class
class ItalianCuisine extends Cuisine {
    @Override
    public void getMenu() {
        System.out.println("Italian Cuisine:");
        System.out.println("1. Spaghetti Carbonara - $12.00");
        System.out.println("2. Lasagna - $15.00");
        System.out.println("3. Margherita Pizza - $10.00");
        System.out.println("4. Tiramisu - $6.00");
    }

    @Override
    public double getPrice(String item) {
        switch (item) {
            case "Spaghetti Carbonara": return 12.00;
            case "Lasagna": return 15.00;
            case "Margherita Pizza": return 10.00;
            case "Tiramisu": return 6.00;
            default: return 0;
        }
    }
}

// Chinese Cuisine Class
class ChineseCuisine extends Cuisine {
    @Override
    public void getMenu() {
        System.out.println("Chinese Cuisine:");
        System.out.println("1. Dumplings - $8.00");
        System.out.println("2. Chow Mein - $10.00");
        System.out.println("3. Fried Rice - $7.00");
        System.out.println("4. Hot and Sour Soup - $5.00");
    }

    @Override
    public double getPrice(String item) {
        switch (item) {
            case "Dumplings": return 8.00;
            case "Chow Mein": return 10.00;
            case "Fried Rice": return 7.00;
            case "Hot and Sour Soup": return 5.00;
            default: return 0;
        }
    }
}

// Indian Cuisine Class
class IndianCuisine extends Cuisine {
    @Override
    public void getMenu() {
        System.out.println("Indian Cuisine:");
        System.out.println("1. Butter Chicken - $14.00");
        System.out.println("2. Naan - $3.00");
        System.out.println("3. Biryani - $12.00");
        System.out.println("4. Gulab Jamun - $4.00");
    }

    @Override
    public double getPrice(String item) {
        switch (item) {
            case "Butter Chicken": return 14.00;
            case "Naan": return 3.00;
            case "Biryani": return 12.00;
            case "Gulab Jamun": return 4.00;
            default: return 0;
        }
    }
}

// Main class to interact with the user
public class RestaurantManagementSystem {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        double totalCost = 0;
        StringBuilder bill = new StringBuilder();

        System.out.println("Welcome to the Restaurant Management System!");
        boolean continueOrdering = true;

        while (continueOrdering) {
            System.out.println("\nPlease select a cuisine:");
            System.out.println("1. Italian");
            System.out.println("2. Chinese");
            System.out.println("3. Indian");
            System.out.println("4. Exit");
            System.out.print("Enter your choice (1-4): ");
            int cuisineChoice = scanner.nextInt();
            Cuisine cuisine = null;

            switch (cuisineChoice) {
                case 1:
                    cuisine = new ItalianCuisine();
                    break;
                case 2:
                    cuisine = new ChineseCuisine();
                    break;
                case 3:
                    cuisine = new IndianCuisine();
                    break;
                case 4:
                    continueOrdering = false;
                    break;
                default:
                    System.out.println("Invalid choice! Please try again.");
                    continue;
            }

            if (cuisine != null) {
                cuisine.getMenu(); // Display menu
                scanner.nextLine(); // Clear buffer

                boolean continueOrderingCuisine = true;
                while (continueOrderingCuisine) {
                    System.out.print("\nEnter the dish you want to order (or type 'done' to finish this cuisine): ");
                    String dish = scanner.nextLine();

                    if (dish.equalsIgnoreCase("done")) {
                        continueOrderingCuisine = false;
                    } else {
                        System.out.print("How many plates of " + dish + " would you like? ");
                        int plates = scanner.nextInt();
                        scanner.nextLine(); // Clear buffer

                        double dishPrice = cuisine.getPrice(dish);
                        if (dishPrice != 0) {
                            double cost = dishPrice * plates;
                            totalCost += cost;
                            bill.append(plates + " plates of " + dish + " - $" + cost + "\n");
                            System.out.println(plates + " plates of " + dish + " will cost $" + cost);
                        } else {
                            System.out.println("Sorry, we don't have that dish in the menu.");
                        }

                        // Ask if the user wants to continue ordering from the same cuisine or change
                        System.out.print("\nWould you like to continue with the same cuisine, choose another cuisine, or finish the order? (type 'same', 'different', or 'done'): ");
                        String decision = scanner.nextLine();

                        if (decision.equalsIgnoreCase("different")) {
                            continueOrderingCuisine = false; // Exit current cuisine menu
                        } else if (decision.equalsIgnoreCase("done")) {
                            continueOrderingCuisine = false; // End the order
                            continueOrdering = false; // Exit the main loop
                        }
                    }
                }
            }
        }

        // Display final bill and total
        System.out.println("\nThank you for ordering with us!");
        System.out.println("Here is your bill:\n" + bill.toString());
        System.out.println("Your total cost is: $" + totalCost);
        System.out.println("We hope to serve you again soon. Have a great day!");

        scanner.close();
    }
}

