package view;

import java.util.Iterator;
import java.util.List;
import java.util.Scanner;

import model.Staff;
import model.Staffaccess;
import model.Student;
import model.Studentaccess;

public class Staffrole {
  static Scanner sc = new Scanner(System.in);

  public static void decide(String email) throws Exception {
    System.out.println("------------------Welcome Staff------------------");
    System.out.println(" 1.Add Student \n 2.Remove Student \n 3.Student Attendance \n 4.Get details \n 5.Exit");
    int choice = sc.nextInt();
    if (choice == 1) {
      if (Signup.student()) {
        System.out.println("Student Added Succesfully");
        decide(email);
      }
    } else if (choice == 2) {
      List<Student> al = Staffaccess.showstudent();
      Iterator itr = al.iterator();
      System.out.println("Student_id Student_name");
      while (itr.hasNext()) {
        Student st = (Student) itr.next();
        System.out.print(st.getid() + "            ");
        System.out.print(st.getname());
        System.out.println();
      }
      System.out.println("Enter Student_id to remove");
      int remove=sc.nextInt();
      if (Staffaccess.removestudent(remove)) {
        System.out.println("Student has been removed");
        decide(email);
      }
    } else if (choice == 3){
      Staffaccess.getdetails();
      List<Integer>al=Staffaccess.studattendance();
      System.out.println("stud_id");
      for(int i:al){
        System.out.print("Attendance For "+i+" : ");
        int att=sc.nextInt();
        if(Staffaccess.markatt(i, att))
        System.out.println("Attendance Entered");
      }
      decide(email);
    } 
    else if (choice==4) {
      // Staffrole.att();System.out.println();
       int id=0;
        System.out.println("------------------Staff details------------------");
        List<Staff>al=Staffaccess.showstaff(email);
        Iterator itr=al.iterator();
      System.out.println("Staff_id staff_name dept ");
      while(itr.hasNext()){
        Staff st=(Staff) itr.next();
        id=st.getid();
        System.out.print(st.getid()+"       ");
        System.out.print(st.getname()+"    ");
        System.out.print(st.getdept()+"    ");
        // System.out.print(st.getsec()+" ");
        System.out.println();
      }
      System.out.println("No of days present");
      int a=Staffaccess.viewattendance(id);
      int b=Staffaccess.total(id);
      System.out.println(a);
      System.out.println("Attendance percentage");
        int c=Staffaccess.peratt(id);
        System.out.println(c);
        System.out.println();
        System.out.println("Enter 1 to perform other operation");
        int d=sc.nextInt();
          Staffrole.decide(email);
        
    }
    else if (choice == 5) {
      System.out.println("------------------Thankyou------------------");
      return;
    } else {
      System.out.println("Enter valid option");
      decide(email);
    }
  }
  public static void att(){

  }

}
