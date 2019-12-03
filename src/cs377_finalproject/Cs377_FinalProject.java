package cs377_finalproject;

import java.sql.*;
import java.util.Scanner;

/**
 *
 * @author daltonrothenberger
 */
public class Cs377_FinalProject {

    public static void main(String[] args) throws SQLException {

        // HAVE TO ADD A FINALLY IN THE TRY CATCH TO CLOSE DB POSSIBLY
        String temp = sqlConnections.selectDepartment(1);
        
        System.out.println(temp);

    }

}
