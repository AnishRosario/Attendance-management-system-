 package view;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;

import model.Admainaccess;
import model.Staff;


public class Adminrole {
    static Scanner sc=new Scanner(System.in);
 public static void decide() throws Exception{
    System.out.println("------------------Welcome Admin------------------");
    System.out.println(" 1.Add admin \n 2.Add Staff \n 3.Remove staff \n 4.Staff Attendance \n 5.Exit");
    int choice=sc.nextInt();
    if(choice==1){
      if(Signup.admin()){
        System.out.println("Admin Added Succesfully");
        decide();
      }
    }
    else if(choice==2){
      if(Signup.staff()){
        System.out.println("Staff Added Succesfully");
        decide();
      }
    }
    else if(choice==3){
      List<Staff>al=Admainaccess.showstaff();
      Iterator itr=al.iterator();
      System.out.println("Staff_id staff_name");
      while(itr.hasNext()){
        Staff st=(Staff) itr.next();
        System.out.print(st.getid()+"          ");
        System.out.print(st.getname()+" ");
        System.out.println();
      }
      System.out.println("Enter staff_id to remove");
      int remove=sc.nextInt();
      if(Admainaccess.removestaff(remove)){
        System.out.println("Staff has been removed");
        decide();
      }
    }
    else if (choice==4) {
      Admainaccess.getdetails();
      List<Integer>al=Admainaccess.staffattendance();
      System.out.println("------------------Attendance------------------");
      System.out.println("staff_id");
      for(int i:al){
        System.out.print("Attendance For "+i+" : ");
        int att=sc.nextInt();
        Admainaccess.markatt(i, att);
      }
      decide();
    }
    else if (choice==5) {
      System.out.println("------------------Thankyou------------------");
      return;
    }
    else{
        System.out.println("Enter the valid option");
        decide();
    }

 }
    
}