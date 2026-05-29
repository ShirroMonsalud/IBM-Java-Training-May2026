package ibm.javatraining.com.day6;

import java.sql.*;
import java.util.Scanner;

public class Main {
	
	private static final String URL = "jdbc:postgresql://localhost:5432/studentDB";
	private static final String USER = "postgres";
	private static final String PASSWORD = "1234";

	public static void main(String[] args) {
		try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
			 Scanner scanner = new Scanner(System.in)) {
			
			System.out.println("Successfully connected to ShirroSQL!");
			
			boolean running = true;
			
			while (running) {
				System.out.println("\n === MENU ===");
				System.out.println("[A]dd");
				System.out.println("[V]iew");
				System.out.println("[U]pdate Password");
				System.out.println("[D]elete");
				System.out.println("[Q]uit");
				System.out.print("Enter choice: ");
				
				String choice = scanner.nextLine().toUpperCase();
				
				switch(choice) {
					case "A":
						System.out.println("\n=== Add New Student ===");
						
						
						System.out.print("Enter Email: ");
						String email = scanner.nextLine();
						
						System.out.print("Enter Password: ");
						String password = scanner.nextLine();
						
						System.out.print("Enter First Name: ");
						String firstName = scanner.nextLine();
						
						System.out.print("Enter Last Name: ");
						String lastName = scanner.nextLine();
						
						String sql = "INSERT INTO student (email, password, firstname, lastname) " +
				                 "VALUES (?, ?, ?, ?)";
						
						try (PreparedStatement ps = connection.prepareStatement(sql)) {
							ps.setString(1, email);
							ps.setString(2, password);
							ps.setString(3, firstName);
							ps.setString(4, lastName);
							
							int rowCount = ps.executeUpdate();
							if (rowCount > 0) {
								System.out.println("Student added successfully!");
							}
						}
						break;
						
					case "V":
						System.out.println("\n=== View Students ===");
						String viewSql = "SELECT * FROM student";
						try (Statement stmt = connection.createStatement();
								 ResultSet rs = stmt.executeQuery(viewSql))
						{
							System.out.println("ID \t \t Name \t \t Email \t Password");
							System.out.println("---------------------------");
							boolean hasStudents = false;
							
							while (rs.next())
							{
								hasStudents = true;
								int id = rs.getInt("studentid");
								String fName = rs.getString("firstname");
								String lName = rs.getString("lastname");
								String email1 = rs.getString("email");
								String passW = rs.getString("password");
								System.out.printf("%-2d | %-20s | %s%n ", id, fName + " " + lName, email1, passW);
							}
							
							if (!hasStudents) {
								System.out.println("No students found in the database.");
							}
							
						}
						break;
						
					case "U":
						System.out.print("Enter Student Email: ");
						String targetEmail = scanner.nextLine();
						
						System.out.print("Enter New Password: ");
						String newPassword = scanner.nextLine();
						
						String updateSql = "UPDATE student SET password = ?, dateupdated = CURRENT_TIMESTAMP WHERE email = ?";
						
						try (PreparedStatement ps = connection.prepareStatement(updateSql)) {
							ps.setString(1, newPassword);
							ps.setString(2, targetEmail);
							
							int rowCount = ps.executeUpdate();
							if (rowCount > 0) {
								System.out.println("Password updated successfully!");
							} else {
								System.out.println("Error: No student found with that email address.");
							}
						}
						break;
					
					case "D":
						System.out.println("\n=== Delete Student ===");
						
						System.out.print("Enter Student Email to delete: ");
						String deleteEmail = scanner.nextLine();
						
						String deleteSql = "DELETE FROM student WHERE email = ?";
						
						try (PreparedStatement ps = connection.prepareStatement(deleteSql)) {
							ps.setString(1, deleteEmail);
							
							int rowCount = ps.executeUpdate();
							
							if (rowCount > 0) {
								System.out.println("Student deleted successfully!");
							} else {
								System.out.println("Error: No email address found.");
							}
						}
						break;
					
					case "Q":
						System.out.println("Thank you!");
						running = false;
						break;
						
					default:
						System.out.println("Invalid choice. Please try again.");
						break;
				}
			
				
			}
			
		} catch (Exception e) {
			System.out.println("Database Error: " + e.getMessage()); 
		}
	}
}