package cs377_finalproject;

import java.sql.*;

/**
 *
 * @author daltonrothenberger
 */
public class sqlConnections {

    private static Connection conn;
//    private static Statement stmt;
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

    public static void updateSale() throws SQLException {
//        changes sale price and date
//        could overload to take price, date, or price and date

        try {
            establishConnectionToDatabase();
            String query = "";
            pstmt = conn.prepareStatement(query);

//            insert query shit here (use prepared statement)
        } catch (SQLException e) {

            System.out.println("ERROR: Could not update sale");

        } finally {
            if (conn != null) {
                conn.close();
            }

            if (pstmt != null) {
                pstmt.close();
            }

        }
    }

    public static void updateEmployeeInfo() throws SQLException {
//        vague as fuck not sure what to do with this
//        could take in array with the names and array with values or something

        try {
            establishConnectionToDatabase();
            String query = "";
            pstmt = conn.prepareStatement(query);

//            insert query shit here (use prepared statement)
        } catch (SQLException e) {

            System.out.println("ERROR: Could not update employee info");

        } finally {
            if (conn != null) {
                conn.close();
            }

            if (pstmt != null) {
                pstmt.close();
            }

        }
    }

    public static void updateCarAvailability() throws SQLException {
//        only changes availability bit value

        try {
            establishConnectionToDatabase();
            String query = "";
            pstmt = conn.prepareStatement(query);

//            insert query shit here (use prepared statement)
        } catch (SQLException e) {

            System.out.println("ERROR: Could not update availability");

        } finally {
            if (conn != null) {
                conn.close();
            }

            if (pstmt != null) {
                pstmt.close();
            }

        }
    }

    public static void updateSalary() throws SQLException {
//    only changes salary    
        try {
            establishConnectionToDatabase();
            String query = "";
            pstmt = conn.prepareStatement(query);

//            insert query shit here (use prepared statement)
        } catch (SQLException e) {

            System.out.println("ERROR: Could not update salary");

        } finally {
            if (conn != null) {
                conn.close();
            }

            if (pstmt != null) {
                pstmt.close();
            }

        }
    }

    public static void updateDepartment() throws SQLException {
//        only changes name

        try {
            establishConnectionToDatabase();
            String query = "";
            pstmt = conn.prepareStatement(query);

//            insert query shit here (use prepared statement)
        } catch (SQLException e) {

            System.out.println("ERROR: Could not update department");

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

            System.out.println("ERROR: Could not delete Employee");

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

            System.out.println("ERROR: Could not delete department");

        } finally {
            if (conn != null) {
                conn.close();
            }

            if (pstmt != null) {
                pstmt.close();
            }

        }
    }

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
            return "ERROR: Could not search for the given car";
        } finally {
            if (conn != null) {
                conn.close();
            }

            if (pstmt != null) {
                pstmt.close();
            }

        }
    }

    public static String selectCars() throws SQLException {
//        takes make, model, year and returns all cars of this type

        try {
            establishConnectionToDatabase();
            String query = "";
            pstmt = conn.prepareStatement(query);

//            insert query shit here (use prepared statement)
            return null;

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

    public static String selectSale() throws SQLException {
//        use sale id
        try {
            establishConnectionToDatabase();
            String query = "";
            pstmt = conn.prepareStatement(query);

//            insert query shit here (use prepared statement)
            return null;

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

    public static String selectDepartment() throws SQLException {
//        uses department id
        try {
            establishConnectionToDatabase();
            String query = "";
            pstmt = conn.prepareStatement(query);

//            insert query shit here (use prepared statement)
            return null;

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
            conn = DriverManager.getConnection(connectionUrl);
        } catch (ClassNotFoundException | SQLException e) {
            System.out.println("ERROR: Could not connect to the database.");
        }
    }

    /**
     * Generalized method for executing queries
     *
     * @param s String containing the query
     * @return Result set containing the data gathered by the query
     * @throws SQLException
     */
    private static ResultSet executeQueryStatement(String s) throws SQLException {
        try {
            ResultSet rs;
            rs = pstmt.executeQuery(s);
            return rs;
        } catch (SQLException e) {
            System.out.println("ERROR: Query failed.");
        }
        // Only returns null in the event of an error
        return null;
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
                    toReturn += (rsmd.getColumnLabel(i) + ": " + rs.getString(i)+ "   \n");
                }
                toReturn += "\n";
            }
            
            return toReturn;
            
        } catch (SQLException e) {
            return "ERROR: Could not display results";
        }

    }
}
