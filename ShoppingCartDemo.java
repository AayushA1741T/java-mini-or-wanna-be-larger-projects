import java.util.Scanner;
class Product 
{
    private String productName;
    private int productId;
    private double price;
    private int quantity;
    static String storeName = "SuperMart Store";
    // Parameterized constructor
    Product(String productName, int productId, double price, int quantity) 
    {
        this.productName = productName;
        this.productId = productId;
        setPrice(price);
        setQuantity(quantity);
    }
    // Getters
    public String getProductName() 
    {
        return productName;
    }
    public int getProductId() 
    {
        return productId;
    }
    public double getPrice() 
    {
        return price;
    }
    public int getQuantity() 
    {
        return quantity;
    }
    // Setters with validation
    public void setPrice(double price)
    {
        if (price >= 0) 
            {
            this.price = price;
        } 
        else 
            {
            System.out.println("Invalid price! Setting price to 0.");
            this.price = 0;
        }
    }
    public void setQuantity(int quantity) 
    {
        if (quantity > 0) 
            {
            this.quantity = quantity;
        } 
        else 
            {
            System.out.println("Invalid quantity! Setting quantity to 1.");
            this.quantity = 1;
        }
    }
    // Calculate total cost for this specific item
    public double calculateItemTotal() 
    {
        return price * quantity;
    }
    // Display single product details
    public void display() 
    {
        System.out.println("Product ID   : " + productId);
        System.out.println("Product Name : " + productName);
        System.out.println("Unit Price   : Rs. " + price);
        System.out.println("Quantity     : " + quantity);
        System.out.println("Item Total   : Rs. " + calculateItemTotal());
        System.out.println("----------------------------");
    }
}
public class ShoppingCartDemo 
{
    public static void main(String[] args) 
    {
        Scanner sc = new Scanner(System.in);
        // 1. Ask how many products to add to the cart
        System.out.print("How many products do you want to add to the cart? ");
        int n = sc.nextInt();
        sc.nextLine(); // Clear buffer
        // Array to hold 'n' Product objects
        Product[] cart = new Product[n];
        // 2. Add products using a loop
        for (int i = 0; i < n; i++) 
            {
            System.out.println("\nEnter details for Product " + (i + 1) + ":");
            System.out.print("Product Name: ");
            String name = sc.nextLine();
            System.out.print("Product ID: ");
            int id = sc.nextInt();
            System.out.print("Price per unit: ");
            double price = sc.nextDouble();
            System.out.print("Quantity: ");
            int qty = sc.nextInt();
            sc.nextLine(); // Clear buffer
            cart[i] = new Product(name, id, price, qty);
        }
        System.out.println("\n==========================================");
        System.out.println("Cart Updated! Total Products Added: " + cart.length);
        System.out.println("==========================================");
        // 3. Shopping Cart Menu
        int choice;
        do 
        {
            System.out.println("\n--- Shopping Cart Menu ---");
            System.out.println("1. Show Total Products Count in Cart");
            System.out.println("2. Search and Print a Particular Product (by ID)");
            System.out.println("3. View All Products in Cart");
            System.out.println("4. Generate & Print Final Bill");
            System.out.println("5. Exit");
            System.out.print("Enter your choice: ");
            choice = sc.nextInt();
            switch (choice) 
            {
                case 1:
                    System.out.println("\nTotal Unique Products in Cart: " + cart.length);
                    break;

                case 2:
                    System.out.print("\nEnter the Product ID to search: ");
                    int searchId = sc.nextInt();
                    boolean found = false;
                    for (int i = 0; i < cart.length; i++) 
                        {
                        if (cart[i].getProductId() == searchId) 
                            {
                            System.out.println("\n--- Product Found ---");
                            cart[i].display();
                            found = true;
                            break;
                        }
                    }
                    if (!found) 
                        {
                        System.out.println("No product found with ID: " + searchId);
                    }
                    break;
                case 3:
                    System.out.println("\n--- All Products in Cart ---");
                    for (int i = 0; i < cart.length; i++) 
                        {
                        cart[i].display();
                    }
                    break;
                case 4:
                    double grandTotal = 0;
                    int totalItems = 0;
                    System.out.println("\n=================================================");
                    System.out.println("               " + Product.storeName + " - FINAL BILL");
                    System.out.println("=================================================");
                    System.out.printf("%-6s %-18s %-10s %-6s %-10s\n", "ID", "Product", "Price", "Qty", "Total");
                    System.out.println("-------------------------------------------------");
                    for (int i = 0; i < cart.length; i++) 
                        {
                        double itemTotal = cart[i].calculateItemTotal();
                        grandTotal += itemTotal;
                        totalItems += cart[i].getQuantity();
                        System.out.printf("%-6d %-18s %-10.2f %-6d %-10.2f\n",
                                cart[i].getProductId(),
                                cart[i].getProductName(),
                                cart[i].getPrice(),
                                cart[i].getQuantity(),
                                itemTotal);
                    }
                    System.out.println("-------------------------------------------------");
                    System.out.println("Total Units Purchased : " + totalItems);
                    System.out.printf("GRAND TOTAL           : Rs. %.2f\n", grandTotal);
                    System.out.println("=================================================");
                    break;
                case 5:
                    System.out.println("Thank you for shopping at " + Product.storeName + "!");
                    break;
                default:
                    System.out.println("Invalid choice! Please enter 1 to 5.");
            }
        } while (choice != 5);
        sc.close();
    }
}