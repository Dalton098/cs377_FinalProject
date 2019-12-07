package cs377_finalproject;

import java.sql.*;
import java.util.Scanner;

/**
 *
 * @author daltonrothenberger
 */
public class Cs377_FinalProject {

    public static void main(String[] args) throws SQLException {

//        Scanner input = new Scanner(System.in);
//        
//        boolean closed = false;
//        
//        while (!closed){
//            if(input.next().equals("close")){
//                closed = true;
//            }
//        }
//        
//        input.close();
//        
        // select
        // selects done
        // insert
        // inserts need to be done (have prequery checks down tho)
        // update
        // updates done
        // delete
        // deletes need to be done (does not have prequery checks done look at inserts)
        // reports need to be done as well
        // bit more complex than the other queries
//      sqlConnections.updateCarAvailability(123, false);
//      System.out.println(sqlConnections.selectEmployee(111223333));
//      sqlConnections.updateSalary(111223333, 70000);
//      System.out.println(sqlConnections.selectEmployee(111223333));
//      System.out.println(sqlConnections.selectDepartment(1));
//      sqlConnections.updateDepartmentName(1, "Sales");
////      System.out.println(sqlConnections.selectDepartment(1));
//        System.out.println(sqlConnections.selectEmployee(111223333));
//        sqlConnections.updateEmployeeManager(111223333, 111223333);
//        System.out.println(sqlConnections.selectEmployee(111223333));
        String[] opt1 = {"yeet", "tert"};
        System.out.println(readMenuInput(opt1));

    }

    public static int readMenuInput(String[] options) {

        boolean isValidInput = false;
        boolean isError = false;

        String tempInput = "";
        int input = -2;

        Scanner in = new Scanner(System.in);

        do {

            if (isError) {
                System.out.println("\nInalid input, please try again");
            }

            printOptions(options);

            tempInput = in.next();

            for (int i = 0; i < options.length; i++) {

                if (tempInput.equals("" + i + "") || tempInput.equals("-1")) {
                    isValidInput = true;
                    input = Integer.parseInt(tempInput);
                }

            }

            isError = true;

        } while (!isValidInput);

        in.close();

        return input;
    }

    public static void printOptions(String[] options) {

        for (int i = 0; i < options.length; i++) {
            System.out.println("[" + i + "]:\t" + options[i]);
        }

        System.out.println("[-1]:\tQuit");
        System.out.print("Please enter a number: ");

    }

}
