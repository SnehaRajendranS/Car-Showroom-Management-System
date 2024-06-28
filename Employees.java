import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class Employees implements Utility {
    private String id;
    private String name;
    private String position;

    public void get_details() {
        System.out.println("Employee ID: " + id);
        System.out.println("Name: " + name);
        System.out.println("Position: " + position);
    }

    public void set_details() {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter Employee ID: ");
        id = sc.nextLine();
        System.out.print("Enter Employee Name: ");
        name = sc.nextLine();
        System.out.print("Enter Employee Position: ");
        position = sc.nextLine();
    }

    public void saveToDatabase(Connection connection) throws SQLException {
        String query = "INSERT INTO employees (id, name, position) VALUES (?, ?, ?)";
        try (PreparedStatement pstmt = connection.prepareStatement(query)) {
            pstmt.setString(1, id);
            pstmt.setString(2, name);
            pstmt.setString(3, position);
            pstmt.executeUpdate();
        }
    }

    public static void getAllFromDatabase(Connection connection) throws SQLException {
        String query = "SELECT * FROM employees";
        try (PreparedStatement pstmt = connection.prepareStatement(query);
             ResultSet rs = pstmt.executeQuery()) {
            while (rs.next()) {
                System.out.println("ID: " + rs.getString("id"));
                System.out.println("Name: " + rs.getString("name"));
                System.out.println("Position: " + rs.getString("position"));
                System.out.println();
            }
        }
    }
}
