package cs377_finalproject;

import java.sql.*;

/**
 *
 * @author daltonrothenberger
 */
public class sqlConnections {

    private static Connection conn;
    private static Statement stmt;

    public static void insertCar() {
//        regnum, make, model, year, sticker price
    }

    public static void insertSale() {
//        needs reg num, sale price, employee ssn, date

    }

    public static void insertDepartment() {
//        needs department name

    }

    public static void updateSale() {
//        changes sale price and date
//        could overload to take price, date, or price and date
    }

    public static void updateEmployeeInfo() {
//        vague as fuck not sure what to do with this
//        could take in array with the names and array with values or something
    }

    public static void updateCarAvailability() {
//        only changes availability bit value
    }

    public static void updateSalary() {
//    only changes salary    
    }

    public static void updateDepartment() {
//        only changes name
    }

    public static void deleteEmployee() {
//        i think this will automatically handle setting null to people who use the deleteed employee as a manager
    }

//    public static void deleteCar() {
////        might not need this because then will have to delete the sale
//    }
    public static void deleteDepartment() {
//        should automatically set employees in this department to have a null deptid
    }

    public static String selectSpecificCar() {
//        takes reg num
        return null;
    }

    public static String selectCars() {
//        takes make, model, year and returns all cars of this type
        return null;
    }

    public static String selectSale() {
//        use sale id
        return null;
    }

    public static String selectDepartment() {
//        uses department id
        return null;
    }

    public static String inventoryReport() {
//        count up # of cars by make, model, year and are available
        return null;
    }

    public static String salesReport() {
//        shows lowest sale, highest sale, avg sale for each make, model, and year
//        most likely gonna be a hell a lot of joins between the tables but will reuse a lot of the inventory report query
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
            String connectionUrl = "jdbc:sqlserver://localhost;databaseName=Dalton;integratedSecurity=false;user=sa;password=reallyStrongPwd123";
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
            rs = stmt.executeQuery(s);
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
    private static void displayQueryResults(ResultSet rs) throws SQLException {

        try {
            ResultSetMetaData rsmd = rs.getMetaData();

            int numOfCols = rsmd.getColumnCount();
            while (rs.next()) {
                for (int i = 1; i < numOfCols + 1; i++) {
                    System.out.println((rsmd.getColumnLabel(i) + ": " + rs.getString(i)
                            + "   "));
                }
                System.out.println();
            }
        } catch (SQLException e) {
            System.out.println("ERROR: Could not display results");
        }

    }
}
