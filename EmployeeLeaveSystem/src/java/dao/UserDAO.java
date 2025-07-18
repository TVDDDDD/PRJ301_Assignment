/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import model.User;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

// Data Access Object for user-related database operations
public class UserDAO {
    private DataSource getDataSource() throws Exception {
        Context initContext = new InitialContext();
        Context envContext = (Context) initContext.lookup("java:/comp/env");
        return (DataSource) envContext.lookup("jdbc/LeaveDB");
    }

    public User validateUser(String username, String password) {
        try (Connection conn = getDataSource().getConnection()) {
            PreparedStatement stmt = conn.prepareStatement(
                "SELECT Username, Password, Role, Department, Manager FROM Users WHERE Username = ? AND Password = ?"
            );
            stmt.setString(1, username);
            stmt.setString(2, password);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return new User(
                    rs.getString("Username"),
                    rs.getString("Password"),
                    rs.getString("Role"),
                    rs.getString("Department"),
                    rs.getString("Manager")
                );
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}