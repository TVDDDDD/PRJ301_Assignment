-- Seed initial data for testing
USE LeaveManagementDB;
GO

INSERT INTO Users (Username, Password, Role, Department, Manager)
VALUES 
    ('user1', '123', 'Nhân viên', 'IT', 'manager1'),
    ('manager1', '123', 'Trưởng phòng', 'IT', NULL),
    ('user2', '123', 'Nhân viên', 'IT', 'manager1');
GO

INSERT INTO Leave_Requests (CreatedBy, FromDate, ToDate, Reason, Status, ProcessedBy)
VALUES 
    ('user1', '2025-02-01', '2025-02-03', 'Nghỉ phép năm', 'Inprogress', NULL),
    ('user2', '2025-02-05', '2025-02-07', 'Nghỉ bệnh', 'Approved', 'manager1');
GO