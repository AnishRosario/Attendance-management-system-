package view;

import java.util.Iterator;
import java.util.List;
import java.util.Scanner;

import model.Staff;
import model.Student;
import model.Studentaccess;

public class Studentrole {
    static int id=0;
    static Scanner sc=new Scanner(System.in);
    public static void decide(String email) throws Exception{
        System.out.println();
        System.out.println("------------------Student details------------------");
        List<Student>al=Studentaccess.showstudent(email);
        Iterator itr=al.iterator();
      System.out.println("Stud_id stud_name dept sec");
      while(itr.hasNext()){
        Student st=(Student) itr.next();
        id=st.getid();
        System.out.print(st.getid()+"       ");
        System.out.print(st.getname()+"    ");
        System.out.print(st.getdept()+"    ");
        System.out.print(st.getsec()+" ");
        System.out.println();
      }
      System.out.println("No of days present/Total number of days");
      int a=Studentaccess.viewattendance(id);
      int b=Studentaccess.total(id);
      System.out.println(a+"/"+b);
      System.out.println("Attendance percentage");
        int c=Studentaccess.peratt(id);
        System.out.println(c);
        System.out.println();
        System.out.println("Enter 1 to EXIT");
       int d=sc.nextInt();
       if(d==1){
        return;
       }
    }
}
