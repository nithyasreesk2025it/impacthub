import java.util.*;
import java.sql.*;

public class impacthub {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        while (true) {

            System.out.println("\n===== IMPACTHUB =====");
            System.out.println("1. Register Volunteer");
            System.out.println("2. View Volunteers");
            System.out.println("3. Add Activity");
            System.out.println("4. View Activities");
            System.out.println("5. Register for Activity");
            System.out.println("6. View Registrations");
            System.out.println("7. Cancel Registration");
            System.out.println("8. Exit");

            System.out.print("Enter your choice: ");
            int choice = sc.nextInt();

            switch (choice) {

        case 1:
    try {
        Connection con = DBConnection.getConnection();
        Statement st = con.createStatement();

        sc.nextLine();

        System.out.print("Enter Name: ");
        String name = sc.nextLine();

        System.out.print("Enter Phone: ");
        String phone = sc.nextLine();

        System.out.print("Enter Email: ");
        String email = sc.nextLine();

        st.executeUpdate("INSERT INTO Volunteers(name, phone, email) VALUES ('"
                + name + "','" + phone + "','" + email + "')");

        ResultSet rs = st.executeQuery("SELECT MAX(volunteer_id) FROM Volunteers");

        if (rs.next()) {
            System.out.println("\nVolunteer Registered Successfully!");
            System.out.println("Your Volunteer ID is: " + rs.getInt(1));
            System.out.println("Please save this ID for activity registration.");
        }

        con.close();
    }
    catch(Exception e) {
        System.out.println(e);
    }
    break;

         case 2:
    try {
        Connection con = DBConnection.getConnection();
        Statement st = con.createStatement();

        ResultSet rs = st.executeQuery("SELECT * FROM Volunteers");

        while (rs.next()) {
            System.out.println("ID: " + rs.getInt("volunteer_id"));
            System.out.println("Name: " + rs.getString("name"));
            System.out.println("Phone: " + rs.getString("phone"));
            System.out.println("Email: " + rs.getString("email"));
            System.out.println("--------------------");
        }

        con.close();
    }
    catch(Exception e) {
        System.out.println(e);
    }
    break;

       case 3:
    try {
        Connection con = DBConnection.getConnection();
        Statement st = con.createStatement();

        sc.nextLine();

        System.out.print("Enter Activity Name: ");
        String activity = sc.nextLine();

        System.out.print("Enter Location: ");
        String location = sc.nextLine();

        st.executeUpdate("INSERT INTO Activities(activity_name, location, status) VALUES ('"
                + activity + "','" + location + "','Open')");

        System.out.println("Activity Added Successfully!");

        con.close();
    }
    catch(Exception e) {
        System.out.println(e);
    }
    break;

    case 4:
    try {
        Connection con = DBConnection.getConnection();
        Statement st = con.createStatement();

        ResultSet rs = st.executeQuery("SELECT * FROM Activities");

        while (rs.next()) {
            System.out.println("Activity ID: " + rs.getInt("activity_id"));
            System.out.println("Activity: " + rs.getString("activity_name"));
            System.out.println("Location: " + rs.getString("location"));
            System.out.println("Status: " + rs.getString("status"));
            System.out.println("--------------------");
        }

        con.close();
    }
    catch(Exception e) {
        System.out.println(e);
    }
    break;

        case 5:
    try {
        Connection con = DBConnection.getConnection();
        Statement st = con.createStatement();

        System.out.print("Enter Volunteer ID: ");
        int vid = sc.nextInt();

        System.out.print("Enter Activity ID: ");
        int aid = sc.nextInt();

        st.executeUpdate("INSERT INTO Registrations(volunteer_id, activity_id) VALUES ("
                + vid + "," + aid + ")");

        System.out.println("Registered for Activity Successfully!");

        con.close();
    }
    catch(Exception e) {
        System.out.println("Registration Failed: " + e);
    }
    break;

       case 6:
    try {
        Connection con = DBConnection.getConnection();
        Statement st = con.createStatement();

        ResultSet rs = st.executeQuery(
            "SELECT Registrations.registration_id, Volunteers.name, Activities.activity_name " +
            "FROM Registrations " +
            "JOIN Volunteers ON Registrations.volunteer_id = Volunteers.volunteer_id " +
            "JOIN Activities ON Registrations.activity_id = Activities.activity_id"
        );

        while (rs.next()) {
            System.out.println("Registration ID: " + rs.getInt("registration_id"));
            System.out.println("Volunteer: " + rs.getString("name"));
            System.out.println("Activity: " + rs.getString("activity_name"));
            System.out.println("--------------------");
        }

        con.close();
    }
    catch(Exception e) {
        System.out.println(e);
    }
    break;

        case 7:
    try {
        Connection con = DBConnection.getConnection();
        Statement st = con.createStatement();

        System.out.print("Enter Registration ID to cancel: ");
        int id = sc.nextInt();

        int result = st.executeUpdate(
            "DELETE FROM Registrations WHERE registration_id = " + id
        );

        if (result > 0) {
            System.out.println("Registration Cancelled Successfully!");
        } else {
            System.out.println("Registration ID not found!");
        }

        con.close();
    }
    catch(Exception e) {
        System.out.println(e);
    }
    break;

                case 8:
                    System.out.println("Thank you for using ImpactHub!");
                    return;

                default:
                    System.out.println("Invalid choice!");
            }
        }
    }
}