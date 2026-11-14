import java.util.Scanner;
class Student 
{
    private String name;
    private int rollNo;
    private double marks;
    static String college = "ABC College";
    // Parameterized constructor
    Student(String name, int rollNo, double marks) 
    {
        this.name = name;
        this.rollNo = rollNo;
        setMarks(marks);
    }
    // Getters
    public String getName() 
    {
        return name;
    }
    public int getRollNo() 
    {
        return rollNo;
    }
    public double getMarks() 
    {
        return marks;
    }
    // Setter with validation
    public void setMarks(double marks)
    {
        if (marks >= 0 && marks <= 100) 
            {
            this.marks = marks;
        } 
        else 
            {
            System.out.println("Invalid marks! Setting marks to 0.");
            this.marks = 0;
        }
    }
    // Grade calculation
    public String calculateGrade() 
    {
        if (marks >= 90) 
            {
            return "A";
        } 
        else if (marks >= 75) 
            {
            return "B";
        } 
        else if (marks >= 60) 
            {
            return "C";
        } 
        else 
            {
            return "D";
        }
    }
    // Display details
    public void display() 
    {
        System.out.println("Name: " + name);
        System.out.println("Roll No: " + rollNo);
        System.out.println("Marks: " + marks);
        System.out.println("Grade: " + calculateGrade());
        System.out.println("College: " + college);
        System.out.println("----------------");
    }
}
public class StudentDemo 
{
    public static void main(String[] args) 
    {
        Scanner sc = new Scanner(System.in);
        // 1. Ask how many students to register
        System.out.print("How many students do you want to register? ");
        int n = sc.nextInt();
        sc.nextLine(); // Clear buffer
        // Create an array to hold 'n' Student objects
        Student[] students = new Student[n];
        // 2. Register all students using a loop
        for (int i = 0; i < n; i++) 
            {
            System.out.println("\nEnter details for Student " + (i + 1) + ":");
            System.out.print("Name: ");
            String name = sc.nextLine();
            System.out.print("Roll No: ");
            int rollNo = sc.nextInt();
            System.out.print("Marks: ");
            double marks = sc.nextDouble();
            sc.nextLine(); // Clear buffer
            students[i] = new Student(name, rollNo, marks);
        }
        // Show total registered count
        System.out.println("\n==========================================");
        System.out.println("Registration Complete! Total Students Registered: " + students.length);
        System.out.println("==========================================");
        // 3. Management Menu to search or display students
        int choice;
        do 
        {
            System.out.println("\n--- Student Management Menu ---");
            System.out.println("1. Show Total Registered Students Count");
            System.out.println("2. Search and Print a Particular Student (by Roll No)");
            System.out.println("3. Display All Registered Students");
            System.out.println("4. Exit");
            System.out.print("Enter your choice: ");
            choice = sc.nextInt();
            switch (choice) 
            {
                case 1:
                    System.out.println("\nTotal Registered Students: " + students.length);
                    break;
                case 2:
                    System.out.print("\nEnter the Roll No of the student to print: ");
                    int searchRoll = sc.nextInt();
                    boolean found = false;
                    for (int i = 0; i < students.length; i++) 
                        {
                        if (students[i].getRollNo() == searchRoll) 
                            {
                            System.out.println("\n--- Student Found ---");
                            students[i].display();
                            found = true;
                            break;
                        }
                    }
                    if (!found) 
                        {
                        System.out.println("No student found with Roll No: " + searchRoll);
                    }
                    break;
                case 3:
                    System.out.println("\n--- All Registered Students (" + students.length + ") ---");
                    for (int i = 0; i < students.length; i++) 
                        {
                        students[i].display();
                    }
                    break;
                case 4:
                    System.out.println("Exiting Student Management System...");
                    break;
                default:
                    System.out.println("Invalid choice! Please enter 1 to 4.");
            }
        } while (choice != 4);
        sc.close();
    }
}