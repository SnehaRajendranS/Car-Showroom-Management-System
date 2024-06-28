import java.sql.*;
import java.util.Scanner;

interface Utility {
    void get_details();
    void set_details();
}

public class Main {
    private static final String DB_URL = "jdbc:mysql://localhost:3306/showroomdb1";
    private static final String USER = "root";
    private static final String PASS = "sneha@123";

    static void main_menu() {
        System.out.println();
        System.out.println("======================= *** WELCOME TO SHOWROOM MANAGEMENT SYSTEM *** ================================");
        System.out.println();
        System.out.println("=============================== *** ENTER YOUR CHOICE *** ============================================");
        System.out.println();
        System.out.println("1].ADD SHOWROOMS \t\t\t 2].ADD EMPLOYEES \t\t\t 3].ADD CARS");
        System.out.println();
        System.out.println("4].GET SHOWROOMS \t\t\t 5].GET EMPLOYEES \t\t\t 6].GET CARS");
        System.out.println();
        System.out.println("=============================== *** ENTER 0 TO EXIT *** ==============================================");
    }

    public static void main(String[] args) {
        try (Connection connection = DriverManager.getConnection(DB_URL, USER, PASS)) {
            Scanner sc = new Scanner(System.in);
            int choice = 100;

            while (choice != 0) {
                main_menu();
                choice = sc.nextInt();

                switch (choice) {
                    case 1:
                        Showroom showroom = new Showroom();
                        showroom.set_details();
                        showroom.saveToDatabase(connection);
                        break;
                    case 2:
                        Employees employee = new Employees();
                        employee.set_details();
                        employee.saveToDatabase(connection);
                        break;
                    case 3:
                        Cars car = new Cars();
                        car.set_details();
                        car.saveToDatabase(connection);
                        break;
                    case 4:
                        Showroom.getAllFromDatabase(connection);
                        break;
                    case 5:
                        Employees.getAllFromDatabase(connection);
                        break;
                    case 6:
                        Cars.getAllFromDatabase(connection);
                        break;
                    default:
                        System.out.println("ENTER VALID CHOICE: ");
                        break;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
