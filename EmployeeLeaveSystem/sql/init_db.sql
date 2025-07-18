-- Initialize the LeaveManagementDB database
USE master;
GO

IF EXISTS (SELECT name FROM sys.databases WHERE name = N'LeaveManagementDB')
    DROP DATABASE LeaveManagementDB;
GO

CREATE DATABASE LeaveManagementDB COLLATE Vietnamese_CI_AS;
GO

USE LeaveManagementDB;
GO