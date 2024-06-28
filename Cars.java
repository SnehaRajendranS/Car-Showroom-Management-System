import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class Cars implements Utility {
    private String id;
    private String model;
    private String brand;
    private String price;


    public void get_details() {
        System.out.println("Car ID: " + id);
        System.out.println("Model: " + model);
        System.out.println("Brand: " + brand);
        System.out.println("Price: " + price);

    }

    public void set_details() {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter Car ID: ");
        id = sc.nextLine();
        System.out.print("Enter Car Model: ");
        model = sc.nextLine();
        System.out.print("Enter Car Brand: ");
        brand = sc.nextLine();
        System.out.print("Enter Car Price: ");
        price = sc.nextLine();
    }

    public void saveToDatabase(Connection connection) throws SQLException {
        String query = "INSERT INTO cars (id, model, brand, price) VALUES (?, ?, ?, ?)";
        try (PreparedStatement pstmt = connection.prepareStatement(query)) {
            pstmt.setString(1, id);
            pstmt.setString(2, model);
            pstmt.setString(3, brand);
            pstmt.setString(4, price);

            pstmt.executeUpdate();
        }
    }

    public static void getAllFromDatabase(Connection connection) throws SQLException {
        String query = "SELECT * FROM cars";
        try (PreparedStatement pstmt = connection.prepareStatement(query);
             ResultSet rs = pstmt.executeQuery()) {
            while (rs.next()) {
                System.out.println("ID: " + rs.getString("id"));
                System.out.println("Model: " + rs.getString("model"));
                System.out.println("Brand: " + rs.getString("brand"));
                System.out.println("Price: " + rs.getString("price"));
                System.out.println();
            }
        }
    }
}
