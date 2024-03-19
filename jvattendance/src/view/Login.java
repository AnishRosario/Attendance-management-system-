package view;

import java.util.Scanner;
import model.*;
import model.Studentaccess;

public class Login {
    static Scanner sc = new Scanner(System.in);

    public static void adminlogin() throws Exception {
        System.out.println("Enter emailaddress:");
        String email = sc.nextLine();
        System.out.println("Enter password:");
        String pass = sc.nextLine();
        if (Admainaccess.authadmin(email, pass)) {
            System.out.println("Admin Login Success");
            System.out.println();
            Adminrole.decide();
        } else {
            System.out.println();
            System.out.println("Login Failed");
            Auth.dec();
        }
    }

    public static void stafflogin() throws Exception {
        System.out.println("Enter emailaddress:");
        String email = sc.nextLine();
        System.out.println("Enter password:");
        String pass = sc.nextLine();
        if (Staffaccess.authstaff(email, pass)) {
            System.out.println("Staff Login Succesful");
            System.out.println();
            Staffrole.decide(email);
        } else {
            System.out.println();
            System.out.println("Login failed");
            Auth.dec();
        }

    }

    public static void studentlogin() throws Exception {
        System.out.println("Enter emailaddress:");
        String email = sc.nextLine();
        System.out.println("Enter password:");
        String pass = sc.nextLine();

        if (Studentaccess.authstudent(email, pass)) {
            System.out.println("Student Login Successful");
            Studentrole.decide(email);
        } else {
            System.out.println();
            System.out.println("Login failed");
            Auth.dec();
        }

    }
}
