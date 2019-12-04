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
         System.out.println(sqlConnections.selectSpecificCar(122));


    }

}
