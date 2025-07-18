/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package TestDB;
import java.sql.*;
public class TestDB {
    public static void main(String[] args) throws Exception {
        String url = "jdbc:sqlserver://localhost:1433;databaseName=LeaveManagementDB;encrypt=true;trustServerCertificate=true";
        try (Connection conn = DriverManager.getConnection(url, "sa", "123")) {
            System.out.println("Connected!");
        }
    }
}