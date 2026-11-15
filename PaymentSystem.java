import java.util.Scanner;
// Base Class (Superclass)
class Payment 
{
    protected double amount;
    public Payment(double amount) 
    {
        this.amount = amount;
    }
    // Method to be overridden by subclasses
    public void processPayment() 
    {
        System.out.println("Processing generic payment of ₹" + amount);
    }
}
// Subclass 1: UPI Payment
class UPIPayment extends Payment 
{
    private String upiId;
    public UPIPayment(double amount, String upiId) 
    {
        super(amount);
        this.upiId = upiId;
    }
    @Override
    public void processPayment() 
    {
        System.out.println("\n--- UPI TRANSACTION ---");
        System.out.println("Verifying UPI ID: " + upiId);
        System.out.println("Debited ₹" + amount + " via UPI. Transaction Successful!");
    }
}
// Subclass 2: Card Payment
class CardPayment extends Payment 
{
    private String cardNumber;
    private String cardHolder;
    public CardPayment(double amount, String cardNumber, String cardHolder) 
    {
        super(amount);
        this.cardNumber = cardNumber;
        this.cardHolder = cardHolder;
    }
    @Override
    public void processPayment() 
    {
        // Masking all digits except the last 4
        String maskedCard = "XXXX-XXXX-XXXX-" + cardNumber.substring(Math.max(0, cardNumber.length() - 4));
        System.out.println("\n--- CARD TRANSACTION ---");
        System.out.println("Card Holder: " + cardHolder);
        System.out.println("Card Number: " + maskedCard);
        System.out.println("Authorized payment of ₹" + amount + ". Receipt Generated!");
    }
}
// Subclass 3: Cash Payment
class CashPayment extends Payment 
{
    private double cashTendered;
    public CashPayment(double amount, double cashTendered) 
    {
        super(amount);
        this.cashTendered = cashTendered;
    }
    @Override
    public void processPayment() 
    {
        System.out.println("\n--- CASH TRANSACTION ---");
        if (cashTendered < amount) 
            {
            System.out.println("Insufficient cash! Short by ₹" + (amount - cashTendered));
        } 
        else 
            {
            double change = cashTendered - amount;
            System.out.println("Bill Amount: ₹" + amount);
            System.out.println("Cash Received: ₹" + cashTendered);
            System.out.println("Change Returned: ₹" + change + ". Payment Complete!");
        }
    }
}
// Main Driver Class
public class PaymentSystem 
{
    public static void main(String[] args) 
    {
        Scanner sc = new Scanner(System.in);
        // Superclass reference variable used for Dynamic Method Dispatch
        Payment paymentRef = null;
        int choice;
        do 
        {
            System.out.println("\n=== PAYMENT GATEWAY ===");
            System.out.println("1. Pay via UPI");
            System.out.println("2. Pay via Card");
            System.out.println("3. Pay via Cash");
            System.out.println("4. Exit");
            System.out.print("Select payment method: ");
            choice = sc.nextInt();
            if (choice == 4) 
                {
                System.out.println("Exiting Payment Gateway.");
                break;
            }
            if (choice < 1 || choice > 4) 
                {
                System.out.println("Invalid choice! Try again.");
                continue;
            }
            System.out.print("Enter amount to pay (₹): ");
            double amount = sc.nextDouble();
            sc.nextLine(); // Clear buffer
            switch (choice) 
            {
                case 1:
                    System.out.print("Enter UPI ID (e.g., user@okaxis): ");
                    String upiId = sc.nextLine();
                    // Upcasting: Superclass reference points to UPIPayment object
                    paymentRef = new UPIPayment(amount, upiId);
                    break;
                case 2:
                    System.out.print("Enter Card Holder Name: ");
                    String holder = sc.nextLine();
                    System.out.print("Enter 16-digit Card Number: ");
                    String cardNo = sc.nextLine();
                    // Upcasting: Superclass reference points to CardPayment object
                    paymentRef = new CardPayment(amount, cardNo, holder);
                    break;
                case 3:
                    System.out.print("Enter Cash Given by Customer (₹): ");
                    double cash = sc.nextDouble();
                    // Upcasting: Superclass reference points to CashPayment object
                    paymentRef = new CashPayment(amount, cash);
                    break;
            }
            // DYNAMIC METHOD DISPATCH:
            // At compile time, Java checks Payment.processPayment().
            // At runtime, JVM executes the version belonging to the actual object assigned to paymentRef.
            if (paymentRef != null) 
                {
                paymentRef.processPayment();
            }
        } while (choice != 4);
        sc.close();
    }
}