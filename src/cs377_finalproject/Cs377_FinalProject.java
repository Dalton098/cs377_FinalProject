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
//        System.out.println(sqlConnections.selectEmployee(111223333));
//        sqlConnections.updateSalary(111223333, 70000);
//        System.out.println(sqlConnections.selectEmployee(111223333));
//        System.out.println(sqlConnections.selectDepartment(1));
//        sqlConnections.updateDepartmentName(1, "Sales");
//        System.out.println(sqlConnections.selectDepartment(1));
        System.out.println(sqlConnections.selectEmployee(111223333));
        sqlConnections.updateEmployeeManager(111223333, 111223333);
        System.out.println(sqlConnections.selectEmployee(111223333));

    }

}