package cs377_finalproject;

import java.sql.*;
import java.util.Scanner;

/**
 *
 * @author daltonrothenberger
 */
public class Cs377_FinalProject {

    // Needs to be global because if it is closed the System.in itself is closed too
    public static Scanner in = new Scanner(System.in);

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
        System.out.println("Welcome to SellCars' interactive database system."
                + "\nBelow you will see possible query options.\n");

        int input = -2;

        while (input != -1) {
            String[] opt1 = {"Insert query", "Update query", "Delete query", "Select query", "Reports"};
            input = readMenuInput(opt1);
        }

        in.close();

    }

    public static int readMenuInput(String[] options) {

        boolean isValidInput = false;
        boolean isError = false;

        String tempInput = "";
        int input = -2;

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
