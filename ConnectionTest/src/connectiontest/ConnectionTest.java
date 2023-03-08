/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package connectiontest;
import java.sql.*;
import java.util.Scanner;
/**
 *
 * @author mattw
 */
public class ConnectionTest {
    static final String DB_URL = "jdbc:mysql://localhost:3306/testDB";
    static final String USER = "root";
    static final String PASSWORD = "Mia!CompSci99";
    
    static Scanner scan = new Scanner(System.in);
    static String tableName = "";
    
    public static void main(String[] args) {
        System.out.println("Please enter the tables name: ");
        tableName = scan.nextLine();
        
        System.out.println("Book Manager Test: \n" +
                           " \t1. Create table \n" +
                           " \t2. Insert values into table \n" +
                           " \t3. View table \n" +
                           " \t4. Exit");
        
        int userInput = scan.nextInt();
        
        switch (userInput) {
            case 1:
                /*
                System.out.println("Enter a table name: ");
                String tableName = scan.nextLine();
                */              
                createTable(tableName);
                break;
            case 2:
                insertValue(tableName);
                break;
            case 3:
                viewTable();
                break;
            case 4:
                System.out.println("Goodbye.");
                break;
        }
    }
    
    static void createTable(String tableName){
        
        try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASSWORD);
             Statement stmt = conn.createStatement();) {
            
            String create = "CREATE TABLE " + tableName +
                            " (id INTEGER," +
                            "firstName VARCHAR(20)," +
                            "lastName VARCHAR(20))";
            
            stmt.executeUpdate(create);
            System.out.println("Created table.");
        } catch (SQLException e) {
            System.out.println(e);
        }
    }
    
    static void insertValue(String tableName) {
        try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASSWORD);
             Statement stmt = conn.createStatement();) {
            
            stmt.executeUpdate("INSERT INTO " + tableName + 
                               " VALUES (2, 'LeBron', 'James')");
            
            System.out.println("Values inserted.");
        } catch (SQLException e) {
            System.out.println(e);
        }
    }
    
    static void viewTable(){
        String query = "select id, firstName, lastName from TEST2";
        
        try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASSWORD);
             Statement stmt = conn.createStatement()) {
            
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()) {
                int id = rs.getInt("id");
                String fName = rs.getString("firstName");
                String lName = rs.getString("lastName");
                
                System.out.println(id + ", " + fName + ", " + lName);
            }
            System.out.println("Finished printing.");
        } catch (SQLException e) {
            System.out.println(e);
        }
    }
}
