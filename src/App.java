import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.List;
import java.util.OptionalDouble;
import java.util.Scanner;

public class App {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        List<Employee> employees = EmployeeDatabase.readDataset("employees.csv");
        int choice;

        do {
            // Display the menu
            System.out.println("\n-- Welcome to Employee Database --");
            System.out.println("1. Show all employee data.");
            System.out.println("2. Show all employees with age filter.");
            System.out.println("3. Show all employees with department filter.");
            System.out.println("4. Exit");
            System.out.print("Enter your choice: ");
            choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    // Show all employee data
                    printEmployeeInfo(employees, employee -> true); // no filter
                    break;
                case 2:
                    // Show employees with age filter
                    System.out.print("Enter age threshold for employees: ");
                    int ageThreshold = scanner.nextInt();
                    printEmployeeInfo(employees, employee -> employee.getAge() > ageThreshold);
                    break;
                case 3:
                    // Show employees with department filter
                    System.out.print("Enter department: ");
                    scanner.nextLine(); // Consume newline
                    String department = scanner.nextLine();
                    printEmployeeInfo(employees, employee -> employee.getDepartment().equalsIgnoreCase(department));
                    break;
                case 4:
                    // Exit
                    System.out.println("Exiting...");
                    break;
                default:
                    System.out.println("Invalid choice. Please enter a number between 1 and 4.");
            }
        } while (choice != 4);
    }

    private static void printEmployeeInfo(List<Employee> employees, Function<Employee, Boolean> filter) {
        employees.stream()
                 .filter(filter::apply)
                 .map(employee -> employee.getName() + " - " + employee.getDepartment())
                 .forEach(System.out::println);
    }
}
