package cs377_finalproject;

import java.sql.*;
import java.util.regex.Pattern;

/**
 * CS377_FinalProject SQL Connections
 * @author Dalton Rothenberger, David Singleton
 * 
 * 
 * SQL queries for the various features of the CLI component. 
 * Types of connections/Queries include: Inserts, Deletes, Updates, Selects, and Reports
 */
public class sqlConnections {

    private static Connection conn;
    private static PreparedStatement pstmt;

    /**
     * Inserts the car into the car table
     *
     * @param regNum The registration number of the car
     * @param make The make of the car
     * @param model The model of the car
     * @param year The year the car was made
     * @param price The sticker price of the car
     * @throws SQLException
     */
    public static void insertCar(int regNum, String make, String model, int year, int price) throws SQLException {

        try {

            String temp = selectSpecificCar(regNum);

            boolean isNewCar = temp == null || temp.length() == 0;

            if (isNewCar) {

                establishConnectionToDatabase();

                String query = "INSERT INTO [FinalProject].[dbo].[Car] VALUES(?,?,?,?,?,1)";
                pstmt = conn.prepareStatement(query);
                pstmt.setInt(1, regNum);
                pstmt.setString(2, make);
                pstmt.setString(3, model);
                pstmt.setInt(4, year);
                pstmt.setInt(5, price);
                pstmt.executeUpdate();

                System.out.println("Insert Successful");

            } else {
                System.out.println("A car with that registration number already exist, insert failed");
            }

        } catch (Exception e) {

            System.out.println("ERROR: Could not insert car");

        } finally {
            if (conn != null) {
                conn.close();
            }

            if (pstmt != null) {
                pstmt.close();
            }

        }

    }

    /**
     * Inserts the sale into the sale table
     *
     * @param regNum The registration number of the car
     * @param salePrice The sale price of the car
     * @param employeeSsn The employee who sold the car
     * @param date The date of the sale
     * @throws SQLException
     */
    public static void insertSale(int regNum, int salePrice, int employeeSsn, String date) throws SQLException {

        try {

            String temp = selectEmployee(employeeSsn);

            // Checking to see if the manager exist
            boolean isValidEmployee = temp != null || temp.length() != 0;

            // Checking if valid date
            boolean isValidDate = Pattern.matches("[0-9]{4}-[0-9]{2}-[0-9]{2}", date);

            // Will error if regNum does not exist, may want to check if that is valid as well
            if (isValidEmployee && isValidDate) {

                establishConnectionToDatabase();
                String query = "INSERT INTO [FinalProject].[dbo].[Sales] VALUES(?,?,?,?)";
                pstmt = conn.prepareStatement(query);
                pstmt.setInt(1, regNum);
                pstmt.setInt(2, salePrice);
                pstmt.setInt(3, employeeSsn);
                pstmt.setDate(4, Date.valueOf(date));
                pstmt.executeUpdate();

                System.out.println("Insert Successful");

            } else {
                System.out.println("Invalid Employee SSN or date format");
            }

        } catch (Exception e) {

            System.out.println("ERROR: Could not insert sale");

        } finally {
            if (conn != null) {
                conn.close();
            }

            if (pstmt != null) {
                pstmt.close();
            }

        }

    }

    /**
     * Inserts the given department into the department table
     *
     * @param deptName The name of the department
     * @throws SQLException
     */
    public static void insertDepartment(String deptName) throws SQLException {

        try {
            establishConnectionToDatabase();

            String query = "INSERT INTO [FinalProject].[dbo].[Department] VALUES(?)";
            pstmt = conn.prepareStatement(query);
            pstmt.setString(1, deptName);
            pstmt.executeUpdate();

            System.out.println("Insert Successful");

        } catch (SQLException e) {

            System.out.println("ERROR: Could not insert department");

        } finally {
            if (conn != null) {
                conn.close();
            }

            if (pstmt != null) {
                pstmt.close();
            }

        }

    }

    /**
     * Updates the sale price of a given sale
     *
     * @param saleId The id of the sale to be updated
     * @param salePrice The new sale price of the sale
     * @throws SQLException
     */
    public static void updateSalePrice(int saleId, int salePrice) throws SQLException {

        try {

            String temp = selectSale(saleId);

            // Checking to see if the sale exist
            boolean isValidSale = temp != null || temp.length() != 0;

            if (isValidSale) {

                establishConnectionToDatabase();
                String query = "Update [FinalProject].[dbo].[Sales]"
                        + " Set SalePrice = ?"
                        + " Where SaleId = ?";
                pstmt = conn.prepareStatement(query);

                pstmt.setInt(1, salePrice);
                pstmt.setInt(2, saleId);

                pstmt.executeUpdate();

                System.out.println("Update Successful\n");

            } else {
                System.out.println("Invalid Sale ID, update failed");
            }

        } catch (SQLException e) {

            System.out.println("ERROR: Could not update the given sale's sale price");

        } finally {
            if (conn != null) {
                conn.close();
            }

            if (pstmt != null) {
                pstmt.close();
            }

        }
    }

    /**
     * Updates the given employee's manager to be the new manager
     *
     * @param employeeSsn The ssn of the employee who is having their manager
     * changed
     * @param managerSsn The ssn of the new manager
     * @throws SQLException
     */
    public static void updateEmployeeManager(int employeeSsn, int managerSsn) throws SQLException {

        try {

            String temp1 = selectEmployee(managerSsn);
            String temp2 = selectEmployee(employeeSsn);

            // Checking to see if the manager exist
            boolean isValidManager = temp1 != null || temp1.length() != 0;

            // Checking to see if the employee exist
            boolean isValidEmployee = temp2 != null || temp2.length() != 0;

            if (isValidManager && isValidEmployee) {
                establishConnectionToDatabase();
                String query = "Update [FinalProject].[dbo].[Employee]"
                        + " Set MgrSsn = ?"
                        + " Where Ssn = ?";
                pstmt = conn.prepareStatement(query);

                pstmt.setInt(1, managerSsn);
                pstmt.setInt(2, employeeSsn);

                pstmt.executeUpdate();

                System.out.println("Update Successful");

            } else {
                System.out.println("Invalid Employee/Manager SSN, update failed");
            }

        } catch (SQLException e) {

            System.out.println("ERROR: Could not update the given employee's manager");

        } finally {
            if (conn != null) {
                conn.close();
            }

            if (pstmt != null) {
                pstmt.close();
            }

        }
    }

    /**
     * Changes the given car's availability
     *
     * @param regNum Registration Number of the car
     * @param isAvailable The status of whether the car is available or not
     * @throws SQLException
     */
    public static void updateCarAvailability(int regNum, boolean isAvailable) throws SQLException {

        try {

            String temp = selectSpecificCar(regNum);

            // Checking to see if the car exist
            boolean isValidCar = temp != null || temp.length() != 0;

            if (isValidCar) {

                establishConnectionToDatabase();
                String query = "Update [FinalProject].[dbo].[Car]"
                        + " Set IsAvailable = ?"
                        + " Where RegistrationNum = ?";
                pstmt = conn.prepareStatement(query);

                int availability = 0;
                if (isAvailable) {
                    availability = 1;
                }

                pstmt.setInt(1, availability);
                pstmt.setInt(2, regNum);

                pstmt.executeUpdate();

                System.out.println("Update Successful");

            } else {
                System.out.println("Invalid Registration Number, update failed");
            }

        } catch (SQLException e) {

            System.out.println("ERROR: Could not update the given car's availability");

        } finally {
            if (conn != null) {
                conn.close();
            }

            if (pstmt != null) {
                pstmt.close();
            }

        }
    }

    /**
     * Updates the salary of the employee to the given amount
     *
     * @param employeeSsn The employee's social security number
     * @param salary The salary of the employee
     * @throws SQLException
     */
    public static void updateSalary(int employeeSsn, int salary) throws SQLException {

        try {

            String temp = selectEmployee(employeeSsn);

            // Checking to see if the employee exist
            boolean isValidEmployee = temp != null || temp.length() != 0;

            if (isValidEmployee) {

                establishConnectionToDatabase();
                String query = "Update [FinalProject].[dbo].[Employee]"
                        + " Set Salary = ?"
                        + " Where Ssn = ?";
                pstmt = conn.prepareStatement(query);

                pstmt.setInt(1, salary);
                pstmt.setInt(2, employeeSsn);

                pstmt.executeUpdate();

                System.out.println("Update Successful");
            } else {
                System.out.println("Invalid Employee SSN, update failed");
            }

        } catch (SQLException e) {

            System.out.println("ERROR: Could not update the given employee's salary");

        } finally {
            if (conn != null) {
                conn.close();
            }

            if (pstmt != null) {
                pstmt.close();
            }

        }
    }

    /**
     * Updates the department's name to the given name
     *
     * @param deptId The ID of the department that's name is going to be changed
     * @param name The new name for the department
     * @throws SQLException
     */
    public static void updateDepartmentName(int deptId, String name) throws SQLException {

        try {

            String temp = selectDepartment(deptId);

            // Checking to see if the department exist
            boolean isValidDepartment = temp != null || temp.length() != 0;

            if (isValidDepartment) {

                establishConnectionToDatabase();
                String query = "Update [FinalProject].[dbo].[Department]"
                        + " Set Dname = ?"
                        + " Where DeptId = ?";
                pstmt = conn.prepareStatement(query);

                pstmt.setString(1, name);
                pstmt.setInt(2, deptId);

                pstmt.executeUpdate();

                System.out.println("Update Successful");

            } else {
                System.out.println("Invalid Department ID, update failed");
            }

        } catch (SQLException e) {

            System.out.println("ERROR: Could not update the given department's name");

        } finally {
            if (conn != null) {
                conn.close();
            }

            if (pstmt != null) {
                pstmt.close();
            }

        }
    }

    /**
     * Deletes the given employee and sets the MgrSsn = null for any Employee
     * that was managed by the deleted employee
     *
     * @param employeeSsn The employee to delete
     * @throws SQLException
     */
    public static void deleteEmployee(int employeeSsn) throws SQLException {

        try {
            // Sets any Employees referencing the given employee as a manager to null
            setManagerNull(employeeSsn);

            establishConnectionToDatabase();

            String query = "DELETE FROM [FinalProject].[dbo].[Employee] WHERE Ssn = ?";
            pstmt = conn.prepareStatement(query);
            pstmt.setInt(1, employeeSsn);
            pstmt.executeUpdate();

            System.out.println("Delete Successful");

        } catch (SQLException e) {

            System.out.println("ERROR: Could not delete the given employee");

        } finally {
            if (conn != null) {
                conn.close();
            }

            if (pstmt != null) {
                pstmt.close();
            }

        }
    }

    /**
     * Deletes the given department. Any employee in this department now has a
     * DeptId = null
     *
     * @param deptId The ID of the department to delete
     * @throws SQLException
     */
    public static void deleteDepartment(int deptId) throws SQLException {

        try {
            establishConnectionToDatabase();

            String query = "DELETE FROM [FinalProject].[dbo].[Department] WHERE DeptId = ?";
            pstmt = conn.prepareStatement(query);
            pstmt.setInt(1, deptId);
            pstmt.executeUpdate();

            System.out.println("Delete Successful");

        } catch (SQLException e) {

            System.out.println("ERROR: Could not delete the given department");

        } finally {
            if (conn != null) {
                conn.close();
            }

            if (pstmt != null) {
                pstmt.close();
            }

        }
    }

    /**
     * Deletes the given sale
     *
     * @param saleId The ID of the sale to delete
     * @throws SQLException
     */
    public static void deleteSale(int saleId) throws SQLException {

        try {
            establishConnectionToDatabase();

            String query = "DELETE FROM [FinalProject].[dbo].[Sales] WHERE SaleId = ?";
            pstmt = conn.prepareStatement(query);
            pstmt.setInt(1, saleId);
            pstmt.executeUpdate();

            System.out.println("Delete Successful");

        } catch (SQLException e) {

            System.out.println("ERROR: Could not delete the given sale");

        } finally {
            if (conn != null) {
                conn.close();
            }

            if (pstmt != null) {
                pstmt.close();
            }

        }
    }

    /**
     * Takes the social security number of an employee and returns the info
     * pertaining to that employee
     *
     * @param ssn The social security number of the employee
     * @return The information related to the given employee, returns "" if no
     * records and returns null if an exception occurs
     * @throws SQLException
     */
    public static String selectEmployee(int ssn) throws SQLException {

        try {
            establishConnectionToDatabase();
            String query = "SELECT * FROM [FinalProject].[dbo].[Employee] e"
                    + " WHERE e.Ssn = ?";

            pstmt = conn.prepareStatement(query);
            pstmt.setInt(1, ssn);

            ResultSet rs = pstmt.executeQuery();
            String toReturn = stringQueryResults(rs);

            return toReturn;

        } catch (SQLException e) {
            System.out.println("ERROR: Could not search for the given employee");
        } finally {
            if (conn != null) {
                conn.close();
            }

            if (pstmt != null) {
                pstmt.close();
            }

        }

        return null;
    }

    /**
     * Takes a specific registration number and returns the info pertaining to
     * that car
     *
     * @param regNum The registration number of the car
     * @return The information related to the given car, returns "" if no
     * records and returns null if an exception occurs
     * @throws SQLException
     */
    public static String selectSpecificCar(int regNum) throws SQLException {

        try {
            establishConnectionToDatabase();
            String query = "SELECT * FROM [FinalProject].[dbo].[Car] c"
                    + " WHERE c.RegistrationNum = ?";

            pstmt = conn.prepareStatement(query);
            pstmt.setInt(1, regNum);

            ResultSet rs = pstmt.executeQuery();
            String toReturn = stringQueryResults(rs);

            return toReturn;

        } catch (SQLException e) {
            System.out.println("ERROR: Could not search for the given car");
        } finally {
            if (conn != null) {
                conn.close();
            }

            if (pstmt != null) {
                pstmt.close();
            }

        }

        return null;
    }

    /**
     * Takes a given make, model, and year and returns any cars that match
     *
     * @param make The make of the car
     * @param model The model of the car
     * @param year The year of the car
     * @return The cars that matched on make, model, and year, returns "" if no
     * matches and returns null if an exception occurs
     * @throws SQLException
     */
    public static String selectCars(String make, String model, int year) throws SQLException {

        try {
            establishConnectionToDatabase();
            String query = "SELECT * FROM [FinalProject].[dbo].[Car] c"
                    + " Where c.Make = ? AND c.Model = ? AND c.YearMade = ?";
            pstmt = conn.prepareStatement(query);

            pstmt.setString(1, make);
            pstmt.setString(2, model);
            pstmt.setInt(3, year);

            ResultSet rs = pstmt.executeQuery();
            String toReturn = stringQueryResults(rs);

            return toReturn;

        } catch (SQLException e) {

            System.out.println("ERROR: Could not search for the given cars");

        } finally {
            if (conn != null) {
                conn.close();
            }

            if (pstmt != null) {
                pstmt.close();
            }

        }

        return null;
    }

    /**
     * Takes in a saleId and returns the info pertaining to the sale
     *
     * @param saleId The id of the sale to be looked for
     * @return A string containing the information from the sale, returns "" if
     * no records and returns null if an exception occurs
     * @throws SQLException
     */
    public static String selectSale(int saleId) throws SQLException {

        try {
            establishConnectionToDatabase();
            String query = "SELECT * FROM [FinalProject].[dbo].[Sales] s"
                    + " Where s.SaleId = ?";
            pstmt = conn.prepareStatement(query);

            pstmt.setInt(1, saleId);

            ResultSet rs = pstmt.executeQuery();
            String toReturn = stringQueryResults(rs);

            return toReturn;

        } catch (SQLException e) {

            System.out.println("ERROR: Could not search for the given sale");

        } finally {
            if (conn != null) {
                conn.close();
            }

            if (pstmt != null) {
                pstmt.close();
            }

        }

        return null;
    }

    /**
     * Takes in a department Id and returns information pertaining to the
     * department
     *
     * @param deptId The id of the department
     * @return The name and deptId of the department, returns "" if no
     * department matches, and returns null if an exception occurs
     * @throws SQLException
     */
    public static String selectDepartment(int deptId) throws SQLException {

        try {
            establishConnectionToDatabase();
            String query = "SELECT * FROM [FinalProject].[dbo].[Department] d"
                    + " Where d.DeptId = ?";
            pstmt = conn.prepareStatement(query);

            pstmt.setInt(1, deptId);

            ResultSet rs = pstmt.executeQuery();
            String toReturn = stringQueryResults(rs);

            if (toReturn.length() == 0) {
                toReturn = "Unable to find any records";
            }

            return toReturn;

        } catch (SQLException e) {

            System.out.println("ERROR: Could not search for the given department");

        } finally {
            if (conn != null) {
                conn.close();
            }

            if (pstmt != null) {
                pstmt.close();
            }

        }

        return null;
    }

    /**
     * Updates all foreign keys of the given MgrSsn to be null
     *
     * @param managerSsn The ssn of the manager
     * @throws SQLException
     */
    public static void setManagerNull(int managerSsn) throws SQLException {

        try {

            String temp1 = selectEmployee(managerSsn);

            // Checking to see if the manager exist
            boolean isValidManager = temp1 != null || temp1.length() != 0;

            if (isValidManager) {
                establishConnectionToDatabase();
                String query = "Update [FinalProject].[dbo].[Employee]"
                        + " Set MgrSsn = ?"
                        + " Where MgrSsn = ?";
                pstmt = conn.prepareStatement(query);

                pstmt.setNull(1, java.sql.Types.INTEGER);
                pstmt.setInt(2, managerSsn);

                pstmt.executeUpdate();

            } else {
                System.out.println("Invalid Manager SSN, update failed");
            }

        } catch (SQLException e) {

            System.out.println("ERROR: Could not update the update the given MgrSsn to be NULL");

        } finally {
            if (conn != null) {
                conn.close();
            }

            if (pstmt != null) {
                pstmt.close();
            }

        }
    }

    //------------------------------- Helper Methods Below    ------------------------------------------------------
    /**
     * Connects to the database for the queries
     */
    private static void establishConnectionToDatabase() {
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            String connectionUrl = "jdbc:sqlserver://localhost;databaseName=FinalProject;integratedSecurity=false;user=sa;password=reallyStrongPwd123";
            if (System.getProperty("os.name").equals("Windows 10")) {
                connectionUrl = "jdbc:sqlserver://localhost;integratedSecurity=true;";
            }
            conn = DriverManager.getConnection(connectionUrl);
        } catch (ClassNotFoundException | SQLException e) {
            System.out.println("ERROR: Could not connect to the database.");
        }
    }

    /**
     * Generalized method for displaying result set from a query
     *
     * @param rs The result set to display
     * @throws SQLException
     */
    private static String stringQueryResults(ResultSet rs) throws SQLException {

        try {
            ResultSetMetaData rsmd = rs.getMetaData();

            String toReturn = "";

            int numOfCols = rsmd.getColumnCount();
            while (rs.next()) {
                for (int i = 1; i < numOfCols + 1; i++) {
                    toReturn += (rsmd.getColumnLabel(i) + ": " + rs.getString(i) + "   \n");
                }
                toReturn += "\n";
            }
            
            return toReturn;

        } catch (SQLException e) {
            return "ERROR: Could not display results";
        }

    }
}
