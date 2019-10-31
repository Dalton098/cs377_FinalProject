package cs377_finalproject;

import java.sql.*;
import java.util.Scanner;


/**
 *
 * @author daltonrothenberger
 */
public class Cs377_FinalProject {
    
    
    private static Connection conn;
    private static Statement stmt;

    /**
     * @param args the command line arguments
     */
   public static void main(String[] args) throws SQLException {
        
        Scanner in = new Scanner(System.in);
        
        System.out.print("Please enter the Student's Name: ");
        
        String studentName = in.nextLine();
        
        
        establishConnectionToDatabase();
        stmt = conn.createStatement();
        String sql = "SELECT gr.[Grade] "
                + "From [Assignment4].[dbo].[Student] s  "
                + "Join [Assignment4].[dbo].[GradeReport] gr ON s.[StudentId] = gr.[StudentId] "
                + "Where s.StudentName = '" + studentName + "'";
        ResultSet rs = executeQueryStatement(sql);
        double gpa = calculateGPA(rs);
        
        System.out.println(studentName + "'s GPA: " + gpa);
        stmt.close();
        conn.close();
    }
   
     private static void establishConnectionToDatabase() {
        
        try {
            
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
//            Since I am on linux I cannot use the below line to connect to my database
//            So uncomment the line below and comment the other connectUrl line for it to work for you
//            String connectionUrl = "jdbc:sqlserver://localhost;integratedSecurity=true;";
            String connectionUrl = "jdbc:sqlserver://localhost;databaseName=Assignment4;integratedSecurity=false;user=sa;password=reallyStrongPwd123";
            conn = DriverManager.getConnection(connectionUrl);
            
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }
     
    private static ResultSet executeQueryStatement(String s) throws SQLException {
        
        ResultSet rs;
        rs = stmt.executeQuery(s);
        return rs;
    }
    
    private static double calculateGPA(ResultSet rs) throws SQLException {
        
        ResultSetMetaData rsmd = rs.getMetaData();

        double gradeAvg = 0.0;
        String grade = "";
        
        int numOfCols = rsmd.getColumnCount();
        int numOfRows = 0;
        
        while (rs.next()) {
            
             numOfRows++;
             
            for (int i = 1; i < numOfCols + 1; i++) {
                
                grade = rs.getString(i);
                
                switch(grade) {
                    case "A":
                        gradeAvg += 4;
                        break;
                    case "B":
                        gradeAvg += 3;
                        break;
                    case "C":
                        gradeAvg += 2;
                        break;
                    case "D":
                        gradeAvg += 1;
                        break;
                    default:
                        break;
                }
            }
        }
        
        gradeAvg = gradeAvg / numOfRows;
        return gradeAvg;
        
    }

}
