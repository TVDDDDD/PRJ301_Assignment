-- Define tables for the Employee Leave System
USE LeaveManagementDB;
GO

CREATE TABLE Users (
    Username NVARCHAR(50) PRIMARY KEY,
    Password NVARCHAR(50) NOT NULL,
    Role NVARCHAR(50) NOT NULL CHECK (Role IN ('Nhân viên', 'Trưởng phòng')),
    Department NVARCHAR(50) NOT NULL,
    Manager NVARCHAR(50),
    CONSTRAINT FK_Users_Manager FOREIGN KEY (Manager) REFERENCES Users(Username)
);
GO

CREATE TABLE Leave_Requests (
    Id INT IDENTITY(1,1) PRIMARY KEY,
    CreatedBy NVARCHAR(50) NOT NULL,
    FromDate DATE NOT NULL,
    ToDate DATE NOT NULL,
    Reason NVARCHAR(255) NOT NULL,
    Status NVARCHAR(20) NOT NULL CHECK (Status IN ('Inprogress', 'Approved', 'Rejected')),
    ProcessedBy NVARCHAR(50),
    CONSTRAINT FK_LeaveRequests_CreatedBy FOREIGN KEY (CreatedBy) REFERENCES Users(Username),
    CONSTRAINT FK_LeaveRequests_ProcessedBy FOREIGN KEY (ProcessedBy) REFERENCES Users(Username)
);
GO