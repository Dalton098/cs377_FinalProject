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
////    System.out.println(sqlConnections.selectDepartment(1));
//      System.out.println(sqlConnections.selectEmployee(111223333));
//      sqlConnections.updateEmployeeManager(111223333, 111223333);
//      System.out.println(sqlConnections.selectEmployee(111223333));
        
        System.out.println("Welcome to SellCars' interactive database system."
                + "\nBelow you will see possible query options.");

        int input = -2;

        while (input != -1) {
            String[] opt1 = {"Insert query", "Update query", "Delete query", "Select query", "Reports"};
            input = readMenuInput(opt1);

            // reset input to -2 so that menu reappears after query is done
            switch (input) {
                case 0:
                    insertHandler();
                    input = -2;
                    break;
                case 1:
                    updateHandler();
                    input = -2;
                    break;
                case 2:
                    deleteHandler();
                    input = -2;
                    break;
                case 3:
                    selectHandler();
                    input = -2;
                    break;
                case 4:
                    reportHandler();
                    input = -2;
                    break;
            }

        }

        in.close();

    }

    public static void insertHandler() throws SQLException {
        System.out.println("insert");
        
        String[] opt1 = {"Insert car", "Insert sale", "Insert department"};
        int input = readMenuInput(opt1);
        
        switch (input) {
            case 0:
                String[] inputs1 = {"Registration Number", "Make", "Model", "Year", "Price"};
                String[] output1 = {"", "", "", "", ""};
                for(int i = 0; i < inputs1.length; i++){
                    System.out.println("Insert " + inputs1[i] + ": ");
                    in.nextLine();
                    output1[i] = in.nextLine();
                }
                sqlConnections.insertCar(Integer.parseInt(output1[0]), output1[1], output1[2], Integer.parseInt(output1[3]), Integer.parseInt(output1[4]));
                break;
            case 1:
                String[] inputs2 = {"Registration Number", "Sale Price", "Employee SSN", "Date (yyyy-mm-dd"};
                String[] output2 = {"", "", "", ""};
                for(int i = 0; i < inputs2.length; i++){
                    System.out.println("Insert " + inputs2[i] + ": ");
                    in.nextLine();
                    output2[i] = in.nextLine();
                }
                sqlConnections.insertSale(Integer.parseInt(output2[0]), Integer.parseInt(output2[1]), Integer.parseInt(output2[2]), output2[3]);
                break;
            case 2:
                String[] inputs3 = {"Department Name"};
                String[] output3 = {""};
                for(int i = 0; i < inputs3.length; i++){
                    System.out.println("Insert " + inputs3[i] + ": ");
                    in.nextLine();
                    output3[i] = in.nextLine();
                }
                sqlConnections.insertDepartment(output3[0]);
                break;
        }
    }

    public static void updateHandler() {
        System.out.println("update");

    }

    public static void deleteHandler() {
        System.out.println("delete");

    }

    public static void selectHandler() {
        System.out.println("select");

    }

    public static void reportHandler() {
        System.out.println("report");

    }

    public static int readMenuInput(String[] options) {

        boolean isValidInput = false;
        boolean isError = false;

        String tempInput = "";
        int input = -2;

        // spacing
        System.out.print("\n");

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

            if (input != -3) {
                isError = true;
            }

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
