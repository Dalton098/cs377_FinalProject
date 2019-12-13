package cs377_finalproject;

import java.sql.*;
import java.util.Scanner;

/**
 * CS377 Final Project
 *
 * @author Dalton Rothenberger, David Singleton
 *
 * CLI based database system for a car dealership
 */
public class Cs377_FinalProject {

    // Needs to be global because if it is closed the System.in itself is closed too
    public static Scanner in = new Scanner(System.in);

    public static void main(String[] args) throws SQLException {

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
//        sqlConnections.deleteEmployee(111223333);
//        sqlConnections.deleteDepartment(1);
//          sqlConnections.insertSale(123, 70000, 111223333, "2010-10-25");
//          sqlConnections.deleteSale(1);
        System.out.println("Welcome to SellCars' interactive database system."
                + "\nBelow you will see possible query options.");

        int input = -2;

        while (input != -1) {
            String[] opt = {"Insert query", "Update query", "Delete query", "Select query", "Reports"};
            input = readMenuInput(opt);

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
            }

        }

        in.close();

    }

    /**
     * Handler method for calling the various insert queries and prompting for
     * their inputs
     *
     * @throws SQLException
     */
    public static void insertHandler() throws SQLException {

        String[] opt = {"Insert car", "Insert sale", "Insert department"};
        int input = readMenuInput(opt);

        switch (input) {
            case 0:
                String[] inputs1 = {"Registration Number", "Make", "Model", "Year", "Price"};
                String[] output1 = getQueryInputs(inputs1);
                sqlConnections.insertCar(Integer.parseInt(output1[0]), output1[1], output1[2], Integer.parseInt(output1[3]), Integer.parseInt(output1[4]));
                break;
            case 1:
                String[] inputs2 = {"Registration Number", "Sale Price", "Employee SSN", "Date (yyyy-mm-dd"};
                String[] output2 = getQueryInputs(inputs2);
                sqlConnections.insertSale(Integer.parseInt(output2[0]), Integer.parseInt(output2[1]), Integer.parseInt(output2[2]), output2[3]);
                break;
            case 2:
                String[] inputs3 = {"Department Name"};
                String[] output3 = getQueryInputs(inputs3);
                sqlConnections.insertDepartment(output3[0]);
                break;
        }
    }

    /**
     * Handler method for calling the various update queries and prompting for
     * their inputs
     *
     * @throws SQLException
     */
    public static void updateHandler() throws SQLException {

        // update sale price, update employee manager, update car availability, update salary, update dept name
        String[] opt = {"Update sale price", "Update employee manager", "Update car availability", "Update salary", "Update department name"};
        int input = readMenuInput(opt);

        switch (input) {
            case 0:
                String[] inputs1 = {"Sale ID", "Sale Price"};
                String[] output1 = getQueryInputs(inputs1);
                sqlConnections.updateSalary(Integer.parseInt(output1[0]), Integer.parseInt(output1[1]));
                break;
            case 1:
                String[] inputs2 = {"Employee SSN", "Manager SSN"};
                String[] output2 = getQueryInputs(inputs2);
                sqlConnections.updateEmployeeManager(Integer.parseInt(output2[0]), Integer.parseInt(output2[1]));
                break;
            case 2:
                String[] inputs3 = {"Employee SSN", "Manager SSN"};
                String[] output3 = getQueryInputs(inputs3);
                sqlConnections.updateCarAvailability(Integer.parseInt(output2[0]), Integer.parseInt(output2[1]));
                break;
        }
    }

    /**
     * Handler method for calling the various delete queries and prompting for
     * their inputs
     *
     * @throws SQLException
     */
    public static void deleteHandler() {

        // delete employee, delete department, delete sale
    }

    /**
     * Handler method for calling the various select queries and prompting for
     * their inputs
     *
     * @throws SQLException
     */
    public static void selectHandler() {

        // select employee, select specific car, select cars, select sale, select department
    }

    /**
     * Used to gather inputs for the various queries
     *
     * @param params The various parameters needed for a given query
     * @return A string[] containing all the parameters requested
     */
    public static String[] getQueryInputs(String[] params) {

        String[] toReturn = new String[params.length];

        for (int i = 0; i < params.length; i++) {
            System.out.print("Enter " + params[i] + ": ");
            toReturn[i] = in.next();
        }

        return toReturn;
    }

    /**
     * Reads the input for navigating the menus of the CLI
     *
     * @param options The menu options the user can select from
     * @return An int containing the number they inputted to the menu
     */
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

    /**
     * Displays the options inputted in a list and appends the quit option
     *
     * @param options The menu options the user should see
     */
    public static void printOptions(String[] options) {

        for (int i = 0; i < options.length; i++) {
            System.out.println("[" + i + "]:\t" + options[i]);
        }

        System.out.println("[-1]:\tQuit");
        System.out.print("Please enter a number: ");

    }

}
