import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class Showroom implements Utility {
    private String id;
    private String name;
    private String location;

    public void get_details() {
        System.out.println("Showroom ID: " + id);
        System.out.println("Name: " + name);
        System.out.println("Location: " + location);
    }

    public void set_details() {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter Showroom ID: ");
        id = sc.nextLine();
        System.out.print("Enter Showroom Name: ");
        name = sc.nextLine();
        System.out.print("Enter Showroom Location: ");
        location = sc.nextLine();
    }

    public void saveToDatabase(Connection connection) throws SQLException {
        String query = "INSERT INTO showroom (id, name, location) VALUES (?, ?, ?)";
        try (PreparedStatement pstmt = connection.prepareStatement(query)) {
            pstmt.setString(1, id);
            pstmt.setString(2, name);
            pstmt.setString(3, location);
            pstmt.executeUpdate();
        }
    }

    public static void getAllFromDatabase(Connection connection) throws SQLException {
        String query = "SELECT * FROM showroom";
        try (PreparedStatement pstmt = connection.prepareStatement(query);
             ResultSet rs = pstmt.executeQuery()) {
            while (rs.next()) {
                System.out.println("ID: " + rs.getString("id"));
                System.out.println("Name: " + rs.getString("name"));
                System.out.println("Location: " + rs.getString("location"));
                System.out.println();
            }
        }
    }
}
