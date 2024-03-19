package view;

import java.sql.SQLException;
import java.util.regex.Pattern;

public class Valid {
    // admin validation
    public static int admin(String name, String email, String pass, String phone) throws Exception {
        String mail = "^[a-zA-Z0-9_+&-]+(?:\\." + "[a-zA-Z0-9_+&-]+)*@" + "(?:[a-zA-Z0-9-]+\\.)+[a-z" + "A-Z]{2,7}$";
        String number = "^[6-9][0-9]{9}$";

        if (name.isEmpty() && email.isEmpty() && pass.isEmpty() && phone.isEmpty()) {
            System.out.println("No field should not be empty re_enter the details");
            return 0;
        } else if (!email.matches(mail)) {
            System.out.println("Enter valid email address");
          return 0;
        } else if (!phone.matches(number)) {
            System.out.println("Enter valid phone number");
            return 0;
        } else {
            return 1;
        }
    }

    // staff validation
    public static int staff(String name, String email, String password, String dept, String phone) throws Exception {
        String mail = "^[a-zA-Z0-9_+&-]+(?:\\." + "[a-zA-Z0-9_+&-]+)*@" + "(?:[a-zA-Z0-9-]+\\.)+[a-z" + "A-Z]{2,7}$";
        String number = "^[6-9][0-9]{9}$";
        String dep="[a-zA-Z]+";

        if (name.isEmpty() || email.isEmpty() || password.isEmpty() || phone.isEmpty() || dept.isEmpty()) {
            System.out.println("No field should not be empty re_enter the details");
            return 0;
        } else if (!email.matches(mail)) {
            System.out.println("Enter valid email address");
            return 0;
        } else if (!phone.matches(number)) {
            System.out.println("Enter valid phone number");
            return 0;
        } 
        else if (!dept.matches(dep)) {
            System.out.println("Enter department only in letter");
            return 0;
        }
        else {
            return 1;
        }

    }
    public static int student(String name, String email, String pass, String dept, String sec, String phone) throws SQLException{
        String mail = "^[a-zA-Z0-9_+&-]+(?:\\." + "[a-zA-Z0-9_+&-]+)*@" + "(?:[a-zA-Z0-9-]+\\.)+[a-z" + "A-Z]{2,7}$";
        String number = "^[6-9][0-9]{9}$";
        String dep="[a-zA-Z]+";
        String s="[a-zA-Z]";
        if (name.isEmpty() || email.isEmpty() || pass.isEmpty() || phone.isEmpty() || dept.isEmpty() ||sec.isEmpty()) {
            System.out.println("No field should not be empty re_enter the details");
            // Signup.student();
            return 0;
        } 
        else if (!email.matches(mail)) {
            System.out.println("Enter valid email address");
            // Signup.student();
            return 0;
        } else if (!phone.matches(number)) {
            System.out.println("Enter valid phone number");
            // Signup.student();
            return 0;
        }
        else if (!dept.matches(dep)) {
            System.out.println("Enter department only in letter");
            return 0;
        }
        else if (!sec.matches(s)) {
            System.out.println("Enter sec in one character");
            return 0;
        } 
        else {
            return 1;
        }
    }
}
