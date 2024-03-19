package view;

import java.util.Scanner;

public class Auth {
    static Scanner sc=new Scanner(System.in);
    public static void  dec() throws Exception{
       System.out.println("------------------Attendance Management------------------");
       System.out.println(" 1.Adminlogin \n 2.Stafflogin \n 3.Studentlogin ");
       int choice =sc.nextInt();
       if(choice==1){
       Login.adminlogin();
       }
       else if(choice==2){
        Login.stafflogin();
       }
       else if (choice==3) {
        Login.studentlogin();
       }
       else{
        System.out.println("Enter Valid Input");
        dec();
       }
    }
}
