package cs377_finalproject;

import java.sql.*;

/**
 * sqlConnections for the front end
 *
 * Types of connections/Queries include: Inserts, Deletes, Updates, Selects, and
 * Reports
 */
public class sqlConnections {

    private static Connection conn;
    private static PreparedStatement pstmt;

    public static void insertCar() throws SQLException {

        //        regnum, make, model, year, sticker price
        try {
            establishConnectionToDatabase();
            String query = "";
            pstmt = conn.prepareStatement(query);

//            insert query shit here (use prepared statement)
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

    public static void insertSale() throws SQLException {
//        needs reg num, sale price, employee ssn, date

        try {
            establishConnectionToDatabase();
            String query = "";
            pstmt = conn.prepareStatement(query);

//            insert query shit here (use prepared statement)
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

    public static void insertDepartment() throws SQLException {
//        needs department name

        try {
            establishConnectionToDatabase();
            String query = "";
            pstmt = conn.prepareStatement(query);

//            insert query shit here (use prepared statement)
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
            establishConnectionToDatabase();
            String query = "Update [FinalProject].[dbo].[Sales]"
                    + " Set SalePrice = ?"
                    + " Where SaleId = ?";
            pstmt = conn.prepareStatement(query);

            pstmt.setInt(1, salePrice);
            pstmt.setInt(2, saleId);

            pstmt.executeUpdate();

            System.out.println("Update Successful\n");

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

            String temp = selectEmployee(managerSsn);

            // Checking to see if the manager exist
            boolean isValidManager = temp != null || temp.length() != 0;

            if (isValidManager) {
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
                System.out.println("Invalid Manager SSN: The manager does not exist");
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
            establishConnectionToDatabase();
            String query = "Update [FinalProject].[dbo].[Employee]"
                    + " Set Salary = ?"
                    + " Where Ssn = ?";
            pstmt = conn.prepareStatement(query);

            pstmt.setInt(1, salary);
            pstmt.setInt(2, employeeSsn);

            pstmt.executeUpdate();

            System.out.println("Update Successful\n");

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
            establishConnectionToDatabase();
            String query = "Update [FinalProject].[dbo].[Department]"
                    + " Set Dname = ?"
                    + " Where DeptId = ?";
            pstmt = conn.prepareStatement(query);

            pstmt.setString(1, name);
            pstmt.setInt(2, deptId);

            pstmt.executeUpdate();

            System.out.println("Update Successful\n");

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

    public static void deleteEmployee() throws SQLException {
//        i think this will automatically handle setting null to people who use the deleteed employee as a manager
        try {
            establishConnectionToDatabase();
            String query = "";
            pstmt = conn.prepareStatement(query);

//            insert query shit here (use prepared statement)
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

//    public static void deleteCar() {
////        might not need this because then will have to delete the sale
//    }
    public static void deleteDepartment() throws SQLException {
//        should automatically set employees in this department to have a null deptid

        try {
            establishConnectionToDatabase();
            String query = "";
            pstmt = conn.prepareStatement(query);

//            insert query shit here (use prepared statement)
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

    public static String inventoryReport() throws SQLException {
//        count up # of cars by make, model, year and are available
//        This one is gonna be a lot of work
//        Make a map where the key is a concatanation of make model and year and value is the count for the car
//        Get all the records from sql and check if available
//        then check if key already exist (if it does increment that value) (if it does not, make it a key and increment the value)
//        then make a string like follows Key: value for the whole map
//        
        try {
            establishConnectionToDatabase();
            String query = "";
            pstmt = conn.prepareStatement(query);

//            insert query shit here (use prepared statement)
            return null;

        } catch (SQLException e) {

            System.out.println("ERROR: Could not complete inventory report");

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

    public static String salesReport() throws SQLException {
//        shows lowest sale, highest sale, avg sale for each make, model, and year
//        most likely gonna be a hell a lot of joins between the tables but will reuse a lot of the inventory report query
        try {
            establishConnectionToDatabase();
            String query = "";
            pstmt = conn.prepareStatement(query);

//            insert query shit here (use prepared statement)
            return null;

        } catch (SQLException e) {

            System.out.println("ERROR: Could not complete sales report");

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

//------------------------------- Helper Methods Below    ------------------------------------------------------
    /**
     * Connects to the database for the queries
     */
    private static void establishConnectionToDatabase() {
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
//            Uncomment this to test (I am on linux so I have to use a different connection url
//            String connectionUrl = "jdbc:sqlserver://localhost;integratedSecurity=true;";
            String connectionUrl = "jdbc:sqlserver://localhost;databaseName=FinalProject;integratedSecurity=false;user=sa;password=reallyStrongPwd123";
            if(System.getProperty("os.name").equals("Windows")){
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
