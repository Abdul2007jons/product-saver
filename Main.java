import java.util.*;

// Product class with basic fields and methods
class Product {
    int id;
    String name;
    double price;
    int quantity;
    
    public Product(int id, String name, double price, int quantity) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.quantity = quantity;
    }
    
    @Override
    public String toString() {
        return String.format("ID: %d | Name: %s | Price: $%.2f | Quantity: %d", 
                           id, name, price, quantity);
    }
}

// InventoryManager handles all inventory operations
class InventoryManager {
    private ArrayList<Product> products = new ArrayList<>();
    private int nextId = 1;
    
    // Add new product
    public void addProduct(String name, double price, int quantity) {
        products.add(new Product(nextId++, name, price, quantity));
        System.out.println("Product added successfully!");
    }
    
    // Remove product by ID
    public boolean removeProduct(int id) {
        for (int i = 0; i < products.size(); i++) {
            if (products.get(i).id == id) {
                products.remove(i);
                return true;
            }
        }
        return false;
    }
    
    // Update product by ID
    public boolean updateProduct(int id, String name, double price, int quantity) {
        for (Product p : products) {
            if (p.id == id) {
                p.name = name;
                p.price = price;
                p.quantity = quantity;
                return true;
            }
        }
        return false;
    }
    
    // Search product by ID
    public Product searchById(int id) {
        for (Product p : products) {
            if (p.id == id) return p;
        }
        return null;
    }
    
    // Search products by name
    public void searchByName(String name) {
        boolean found = false;
        for (Product p : products) {
            if (p.name.toLowerCase().contains(name.toLowerCase())) {
                System.out.println(p);
                found = true;
            }
        }
        if (!found) System.out.println("No products found!");
    }
    
    // Display all products sorted by name
    public void displaySortedByName() {
        if (products.isEmpty()) {
            System.out.println("No products in inventory!");
            return;
        }
        
        // Simple bubble sort by name
        for (int i = 0; i < products.size() - 1; i++) {
            for (int j = 0; j < products.size() - i - 1; j++) {
                if (products.get(j).name.compareToIgnoreCase(products.get(j + 1).name) > 0) {
                    Product temp = products.get(j);
                    products.set(j, products.get(j + 1));
                    products.set(j + 1, temp);
                }
            }
        }
        
        System.out.println("\nProducts sorted by name:");
        for (Product p : products) {
            System.out.println(p);
        }
    }
    
    // Display all products sorted by price
    public void displaySortedByPrice() {
        if (products.isEmpty()) {
            System.out.println("No products in inventory!");
            return;
        }
        
        // Simple bubble sort by price
        for (int i = 0; i < products.size() - 1; i++) {
            for (int j = 0; j < products.size() - i - 1; j++) {
                if (products.get(j).price > products.get(j + 1).price) {
                    Product temp = products.get(j);
                    products.set(j, products.get(j + 1));
                    products.set(j + 1, temp);
                }
            }
        }
        
        System.out.println("\nProducts sorted by price:");
        for (Product p : products) {
            System.out.println(p);
        }
    }
    
    // Display all products
    public void displayAll() {
        if (products.isEmpty()) {
            System.out.println("No products in inventory!");
        } else {
            for (Product p : products) {
                System.out.println(p);
            }
        }
    }
}

// Main class with user interface
public class Main {
    private static InventoryManager inventory = new InventoryManager();
    private static Scanner scanner = new Scanner(System.in);
    
    public static void main(String[] args) {
        System.out.println("=== INVENTORY MANAGEMENT SYSTEM ===");
        
        while (true) {
            try {
                showMenu();
                int choice = scanner.nextInt();
                scanner.nextLine(); // consume newline
                
                switch (choice) {
                    case 1: addProduct(); break;
                    case 2: removeProduct(); break;
                    case 3: updateProduct(); break;
                    case 4: searchProduct(); break;
                    case 5: displayProducts(); break;
                    case 6: 
                        System.out.println("Goodbye!");
                        return;
                    default: 
                        System.out.println("Invalid choice! Please try again.");
                }
            } catch (Exception e) {
                System.out.println("Error: " + e.getMessage());
                scanner.nextLine(); // clear invalid input
            }
            System.out.println("\nPress Enter to continue...");
            scanner.nextLine();
        }
    }
    
    private static void showMenu() {
        System.out.println("\n--- MAIN MENU ---");
        System.out.println("1. Add Product");
        System.out.println("2. Remove Product");
        System.out.println("3. Update Product");
        System.out.println("4. Search Product");
        System.out.println("5. Display Products");
        System.out.println("6. Exit");
        System.out.print("Enter choice: ");
    }
    
    private static void addProduct() {
        try {
            System.out.print("Enter name: ");
            String name = scanner.nextLine();
            System.out.print("Enter price: ");
            double price = scanner.nextDouble();
            System.out.print("Enter quantity: ");
            int quantity = scanner.nextInt();
            
            if (price < 0 || quantity < 0) {
                System.out.println("Price and quantity must be positive!");
                return;
            }
            
            inventory.addProduct(name, price, quantity);
        } catch (Exception e) {
            System.out.println("Invalid input! Please try again.");
        }
    }
    
    private static void removeProduct() {
        try {
            System.out.print("Enter product ID to remove: ");
            int id = scanner.nextInt();
            
            if (inventory.removeProduct(id)) {
                System.out.println("Product removed successfully!");
            } else {
                System.out.println("Product not found!");
            }
        } catch (Exception e) {
            System.out.println("Invalid input! Please enter a valid ID.");
        }
    }
    
    private static void updateProduct() {
        try {
            System.out.print("Enter product ID to update: ");
            int id = scanner.nextInt();
            scanner.nextLine(); // consume newline
            
            System.out.print("Enter new name: ");
            String name = scanner.nextLine();
            System.out.print("Enter new price: ");
            double price = scanner.nextDouble();
            System.out.print("Enter new quantity: ");
            int quantity = scanner.nextInt();
            
            if (price < 0 || quantity < 0) {
                System.out.println("Price and quantity must be positive!");
                return;
            }
            
            if (inventory.updateProduct(id, name, price, quantity)) {
                System.out.println("Product updated successfully!");
            } else {
                System.out.println("Product not found!");
            }
        } catch (Exception e) {
            System.out.println("Invalid input! Please try again.");
        }
    }
    
    private static void searchProduct() {
        System.out.println("1. Search by ID");
        System.out.println("2. Search by Name");
        System.out.print("Enter choice: ");
        
        try {
            int choice = scanner.nextInt();
            scanner.nextLine(); // consume newline
            
            if (choice == 1) {
                System.out.print("Enter product ID: ");
                int id = scanner.nextInt();
                Product product = inventory.searchById(id);
                if (product != null) {
                    System.out.println("Found: " + product);
                } else {
                    System.out.println("Product not found!");
                }
            } else if (choice == 2) {
                System.out.print("Enter product name: ");
                String name = scanner.nextLine();
                inventory.searchByName(name);
            } else {
                System.out.println("Invalid choice!");
            }
        } catch (Exception e) {
            System.out.println("Invalid input! Please try again.");
        }
    }
    
    private static void displayProducts() {
        System.out.println("1. Display all");
        System.out.println("2. Sort by name");
        System.out.println("3. Sort by price");
        System.out.print("Enter choice: ");
        
        try {
            int choice = scanner.nextInt();
            switch (choice) {
                case 1: inventory.displayAll(); break;
                case 2: inventory.displaySortedByName(); break;
                case 3: inventory.displaySortedByPrice(); break;
                default: System.out.println("Invalid choice!");
            }
        } catch (Exception e) {
            System.out.println("Invalid input! Please try again.");
        }
    }
} 