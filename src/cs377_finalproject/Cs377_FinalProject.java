package cs377_finalproject;

import java.sql.*;
import java.util.Scanner;

/**
 *
 * @author daltonrothenberger
 */
public class Cs377_FinalProject {

    public static void main(String[] args) throws SQLException {

//       sqlConnections.updateCarAvailability(123, false);
        System.out.println(sqlConnections.selectEmployee(111223333));
        sqlConnections.updateSalary(111223333, 70000);
        System.out.println(sqlConnections.selectEmployee(111223333));

    }

}
