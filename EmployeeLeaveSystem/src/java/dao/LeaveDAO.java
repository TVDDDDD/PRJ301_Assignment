/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import model.Leave;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

// Data Access Object for leave request operations
public class LeaveDAO {
    private DataSource getDataSource() throws Exception {
        Context initContext = new InitialContext();
        Context envContext = (Context) initContext.lookup("java:/comp/env");
        return (DataSource) envContext.lookup("jdbc/LeaveDB");
    }

    // Create a new leave request
    public void addLeave(Leave leave) {
        try (Connection conn = getDataSource().getConnection()) {
            PreparedStatement stmt = conn.prepareStatement(
                "INSERT INTO Leave_Requests (CreatedBy, FromDate, ToDate, Reason, Status) VALUES (?, ?, ?, ?, ?)"
            );
            stmt.setString(1, leave.getUserUsername());
            stmt.setDate(2, java.sql.Date.valueOf(leave.getStartDate()));
            stmt.setDate(3, java.sql.Date.valueOf(leave.getEndDate()));
            stmt.setString(4, leave.getReason());
            stmt.setString(5, leave.getStatus());
            stmt.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Retrieve leave requests for a user or their subordinates
    public List<Leave> getLeavesByUser(String username) {
        List<Leave> leaves = new ArrayList<>();
        try (Connection conn = getDataSource().getConnection()) {
            PreparedStatement stmt = conn.prepareStatement(
                "SELECT Id, CreatedBy, FromDate, ToDate, Reason, Status, ProcessedBy " +
                "FROM Leave_Requests WHERE CreatedBy = ? OR CreatedBy IN (SELECT Username FROM Users WHERE Manager = ?)"
            );
            stmt.setString(1, username);
            stmt.setString(2, username);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                leaves.add(new Leave(
                    rs.getInt("Id"),
                    rs.getString("CreatedBy"),
                    rs.getDate("FromDate").toLocalDate(),
                    rs.getDate("ToDate").toLocalDate(),
                    rs.getString("Reason"),
                    rs.getString("Status"),
                    rs.getString("ProcessedBy")
                ));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return leaves;
    }

    // Update the status of a leave request
    public void updateLeave(int id, String status, String processedBy) {
        try (Connection conn = getDataSource().getConnection()) {
            PreparedStatement stmt = conn.prepareStatement(
                "UPDATE Leave_Requests SET Status = ?, ProcessedBy = ? WHERE Id = ?"
            );
            stmt.setString(1, status);
            stmt.setString(2, processedBy);
            stmt.setInt(3, id);
            stmt.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}