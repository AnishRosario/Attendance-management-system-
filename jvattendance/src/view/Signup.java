package view;

import java.sql.SQLException;
import java.util.Scanner;

import model.Admainaccess;
import model.Staffaccess;

public class Signup {
   static Scanner sc = new Scanner(System.in);

   // admin signup
   public static Boolean admin() throws Exception {
      System.out.println("------------------Admin_details------------------");
      System.out.println("Enter name");
      String name = sc.nextLine();
      System.out.println("Enter email");
      String email = sc.nextLine();
      System.out.println("Enter password");
      String pass = sc.nextLine();
      System.out.println("Enter phonenumber");
      String phone = sc.nextLine();
      int f = Valid.admin(name, email, pass, phone);
      if (f == 1) {
         if (Admainaccess.addadmin(name, email, pass, phone)) {
            return true;
         }
         return false;
      } else {
         return Signup.admin();
      }
   }

   // staff signup
   public static Boolean staff() throws Exception {
      System.out.println("------------------Staff_details------------------");
      System.out.println("Enter name");
      String name = sc.nextLine();
      System.out.println("Enter email");
      String email = sc.nextLine();
      System.out.println("Enter password");
      String password = sc.nextLine();
      System.out.println("Enter department (Only captial letter)");
      String dept = sc.nextLine().toUpperCase();
      System.out.println("Enter phonenumber");
      String phone = sc.nextLine();
      int f = Valid.staff(name, email, password, dept, phone);
      if (f == 1) {
         if (Admainaccess.addstaff(email, name, dept, password, phone)) {
            return true;
         } else {
            return false;
         }
      } else {
         return Signup.staff();
      }
   }

   // Student Signup
   public static Boolean student() throws SQLException {
      System.out.println("------------------Student_details------------------");
      System.out.println("Enter name");
      String name = sc.nextLine();
      System.out.println("Enter email");
      String email = sc.nextLine();
      System.out.println("Enter password");
      String pass = sc.nextLine();
      System.out.println("Enter department (Only captial letter)");
      String dept = sc.nextLine().toUpperCase();
      System.out.println("Enter Section (Only captial letter)");
      String sec = sc.nextLine().toUpperCase();
      System.out.println("Enter phonenumber");
      String phone = sc.nextLine();
      int f = Valid.student(name, email, pass, dept, sec, phone);
      if (f == 1) {
         if (Staffaccess.addstudent(name, email, pass, dept, sec, phone)) {
            return true;
         }
         return false;
      }
      else{
         return Signup.student();
      }
   }

}
