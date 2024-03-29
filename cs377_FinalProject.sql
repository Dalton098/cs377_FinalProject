Create DATABASE FinalProject
Go

Use [FinalProject]

Create Table Department (
    DeptId INT IDENTITY(1,1) PRIMARY KEY NOT NULL,
    Dname VARCHAR(50) NOT NULL
)

-- DeptId and MgrSsn are allowed to be null to prevent cascading deletes
-- Managers are their own managers so their MgrSsn is their own Ssn
-- NOTE: Since MgrSsn references the Employee table itself the "ON DELETE SET NULL"
--       line cannot be used since Managers are their own managers
Create Table Employee (
    Ssn INT PRIMARY KEY NOT NULl,
    EmpName VARCHAR(50) NOT NULL,
    StartDate DATE NOT NULL,
    Salary INT NOT NULL,
    DeptId INT FOREIGN KEY REFERENCES Department(DeptId) ON DELETE SET NULL,
    MgrSsn INT FOREIGN KEY REFERENCES Employee(Ssn)
)

Create Table Car (
    RegistrationNum INT PRIMARY KEY NOT NULL,
    Make VARCHAR(50) NOT NULL,
    Model VARCHAR(50) NOT NULL,
    YearMade INT NOT NULL,
    StickerPrice INT NOT NULL,
    IsAvailable BIT NOT NULL
)

-- EmployeeSsn is allowed to be null to prevent cascading deletes
-- ex: Employee quits and is deleted their sales are not deleted as well
Create Table Sales (
    SaleId INT IDENTITY(1,1) PRIMARY KEY NOT NULL,
    RegistrationNum INT NOT NULL FOREIGN KEY REFERENCES Car(RegistrationNum),
    SalePrice INT NOT NULL,
    EmployeeSsn INT FOREIGN KEY REFERENCES Employee(Ssn) ON DELETE SET NULL,
    SaleDate DATE NOT NULL
)

Go

Use [FinalProject]

Insert Into Department Values ('Sales')
Insert Into Department Values ('Human Resources')
Insert Into Department Values ('Accounting')
Insert Into Department Values ('Management')

-- Managers are their own manager's so their MgrSsn is their own
Insert Into Employee Values (111223333, 'Dalton', '2019-11-26', 70000, 4, 111223333)
Insert Into Employee Values (111224444, 'Dave', '2018-10-26', 70000, 4, 111224444)
Insert Into Employee Values (111225555, 'Bob', '2019-12-26', 60000, 1, 111224444)
Insert Into Employee Values (111226666, 'Bobb', '2010-02-07', 65000, 2, 111223333)
Insert Into Employee Values (111227777, 'Bobbie', '2012-03-10', 70000, 3, 111224444)
Insert Into Employee Values (111228888, 'Bobby', '2011-04-12', 50000, 1, 111223333)
Insert Into Employee Values (111229999, 'Bobert', '2008-05-18', 45000, 3, 111224444)

Insert Into Car Values (123, 'Honda', 'Civic LX', 2018, 18840, 1)
Insert Into Car Values (124, 'Honda', 'Civic EX', 2018, 20050, 1)
Insert Into Car Values (125, 'Subaru', 'Crosstrek', 2017, 27000, 1)
Insert Into Car Values (126, 'Subaru', 'Crosstrek Hybrid', 2017, 35000, 1)
Insert Into Car Values (127, 'Subaru', 'WRX', 2020, 29000, 1)
Insert Into Car Values (128, 'Subaru', 'WRX Limited', 2020, 32000, 1)

-- NOTE: No sales are inserted because those are supposed to be added naturally through
-- the java program component of our project

Go