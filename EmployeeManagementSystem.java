import java.util.Scanner;
// Base Class (Superclass)
class Employee 
{
    protected int id;
    protected String name;
    protected String department;
    public Employee(int id, String name, String department) 
    {
        this.id = id;
        this.name = name;
        this.department = department;
    }
    public int getId() 
    {
        return id;
    }
    // Method to be overridden by subclasses
    public double calculateSalary() 
    {
        return 0.0;
    }
    public void displayDetails() 
    {
        System.out.println("ID: " + id + " | Name: " + name + " | Dept: " + department);
    }
}
// Derived Class 1: Full-Time Employee
class FullTimeEmployee extends Employee 
{
    private double monthlySalary;
    private double bonus;
    public FullTimeEmployee(int id, String name, String department, double monthlySalary, double bonus) 
    {
        super(id, name, department); // Calls parent constructor
        this.monthlySalary = monthlySalary;
        this.bonus = bonus;
    }
    @Override
    public double calculateSalary() 
    {
        return monthlySalary + bonus;
    }
    @Override
    public void displayDetails() 
    {
        super.displayDetails();
        System.out.println("Type: Full-Time | Total Monthly Pay: ₹" + calculateSalary());
        System.out.println("--------------------------------------------------");
    }
}
// Derived Class 2: Part-Time Employee
class PartTimeEmployee extends Employee 
{
    private int hoursWorked;
    private double hourlyRate;
    public PartTimeEmployee(int id, String name, String department, int hoursWorked, double hourlyRate) 
    {
        super(id, name, department); // Calls parent constructor
        this.hoursWorked = hoursWorked;
        this.hourlyRate = hourlyRate;
    }
    @Override
    public double calculateSalary() 
    {
        return hoursWorked * hourlyRate;
    }
    @Override
    public void displayDetails() 
    {
        super.displayDetails();
        System.out.println("Type: Part-Time | Hours: " + hoursWorked + " | Total Pay: ₹" + calculateSalary());
        System.out.println("--------------------------------------------------");
    }
}
// Main Driver Class
public class EmployeeManagementSystem 
{
    public static void main(String[] args) 
    {
        Scanner sc = new Scanner(System.in);    
        // Polymorphic array storing both FullTimeEmployee and PartTimeEmployee objects
        Employee[] employees = new Employee[50];
        int count = 0;
        int choice;
        do 
        {
            System.out.println("\n=== EMPLOYEE MANAGEMENT SYSTEM ===");
            System.out.println("1. Add Full-Time Employee");
            System.out.println("2. Add Part-Time Employee");
            System.out.println("3. View All Employees");
            System.out.println("4. Search Employee by ID");
            System.out.println("5. Exit");
            System.out.print("Enter your choice: ");
            choice = sc.nextInt();
            sc.nextLine(); // Consume leftover newline character
            switch (choice) 
            {
                case 1:
                    if (count >= employees.length) 
                        {
                        System.out.println("Database full!");
                        break;
                    }
                    System.out.print("Enter Employee ID: ");
                    int ftId = sc.nextInt();
                    sc.nextLine(); // Clear buffer
                    System.out.print("Enter Name: ");
                    String ftName = sc.nextLine();
                    System.out.print("Enter Department: ");
                    String ftDept = sc.nextLine();
                    System.out.print("Enter Base Monthly Salary: ");
                    double salary = sc.nextDouble();
                    System.out.print("Enter Bonus: ");
                    double bonus = sc.nextDouble();
                    employees[count++] = new FullTimeEmployee(ftId, ftName, ftDept, salary, bonus);
                    System.out.println("Full-Time Employee added successfully!");
                    break;
                case 2:
                    if (count >= employees.length) 
                        {
                        System.out.println("Database full!");
                        break;
                    }
                    System.out.print("Enter Employee ID: ");
                    int ptId = sc.nextInt();
                    sc.nextLine(); // Clear buffer
                    System.out.print("Enter Name: ");
                    String ptName = sc.nextLine();
                    System.out.print("Enter Department: ");
                    String ptDept = sc.nextLine();
                    System.out.print("Enter Hours Worked: ");
                    int hours = sc.nextInt();
                    System.out.print("Enter Hourly Rate: ");
                    double rate = sc.nextDouble();
                    employees[count++] = new PartTimeEmployee(ptId, ptName, ptDept, hours, rate);
                    System.out.println("Part-Time Employee added successfully!");
                    break;
                case 3:
                    System.out.println("\n--- ALL EMPLOYEE RECORDS ---");
                    if (count == 0) 
                        {
                        System.out.println("No records found.");
                    } 
                    else 
                        {
                        for (int i = 0; i < count; i++) 
                            {
                            // Dynamic method dispatch calls the respective subclass method
                            employees[i].displayDetails();
                        }
                    }
                    break;
                case 4:
                    System.out.print("Enter Employee ID to search: ");
                    int searchId = sc.nextInt();
                    boolean found = false;
                    for (int i = 0; i < count; i++) 
                        {
                        if (employees[i].getId() == searchId) 
                            {
                            System.out.println("\n--- EMPLOYEE FOUND ---");
                            employees[i].displayDetails();
                            found = true;
                            break;
                        }
                    }
                    if (!found) 
                        {
                        System.out.println("Employee with ID " + searchId + " not found.");
                    }
                    break;
                case 5:
                    System.out.println("Exiting system. Goodbye!");
                    break;
                default:
                    System.out.println("Invalid choice! Please select 1-5.");
            }
        } while (choice != 5);
        sc.close();
    }
}