package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class Staffaccess {
    static Connection con=null;
     
     public static Boolean addstudent(String name,String email,String pass,String dept,String sec,String phone)throws SQLException{
      PreparedStatement stmt=null;
      con=DBconnectivity.getConnection();
      String sql="INSERT INTO stud_details(name,email,password,dept,sec,phone)VALUES(?,?,?,?,?,?)";
      stmt=con.prepareStatement(sql);
      stmt.setString(1, name);
      stmt.setString(2, email);
      stmt.setString(3, pass);
      stmt.setString(4, dept);
      stmt.setString(5, sec);
      stmt.setString(6, phone);
      int n=stmt.executeUpdate();
      if(n>0){
        return true;
      }
      return false;
     }
     //show student
    public static List<Student> showstudent()throws SQLException{
        PreparedStatement stmt=null;
        con=DBconnectivity.getConnection();
        String sql="SELECT stud_id,name from stud_details where status='1'";
        stmt=con.prepareStatement(sql);
        ResultSet res=stmt.executeQuery();
        List<Student>li=new ArrayList<>();
        while (res.next()) {
            Student obj=new Student();
            obj.setid(res.getInt("stud_id"));
            obj.setname(res.getString("name"));
            li.add(obj);
        }
        return li;
     
    }
     //remove student
     public static Boolean removestudent(int remove)throws SQLException{
       PreparedStatement stmt=null;
       con=DBconnectivity.getConnection();
       String sql="UPDATE stud_details SET status='0' where stud_id=?";
      //  String sql="DELETE FROM stud_details where stud_id=?";
       stmt=con.prepareStatement(sql);
       stmt.setInt(1, remove);
       int n=stmt.executeUpdate();
       if(n>0){
        return true;
       }
       return false;
     }
     //get details from student table
     public static void getdetails()throws SQLException{
      con=DBconnectivity.getConnection();
      PreparedStatement stmt=null;
      String sql="SELECT stud_id from stud_details";
      stmt=con.prepareStatement(sql);
      ResultSet res=stmt.executeQuery();
      String id="";
      while (res.next()) {
        id+=res.getString("stud_id")+",";
      }
      Staffaccess.insertdetails(id);
     }
     //insert the details into stud_attendance
     public static void insertdetails(String id)throws SQLException{
      con=DBconnectivity.getConnection();
      PreparedStatement stmt=null;
      String sql="SELECT date FROM stud_attendance ORDER BY date DESC LIMIT 1";
      stmt=con.prepareStatement(sql);
      ResultSet res=stmt.executeQuery();
      String ldate="";
      while (res.next()) {
        ldate=res.getString("date");
      }
      LocalDate ca=LocalDate.now();
        DateTimeFormatter f=DateTimeFormatter.ofPattern("yyyy-MM-dd");
        String cdate=ca.format(f);
        String ld=ldate.replace("-", "");
        String cd=cdate.replace("-", "");
        if(Integer.valueOf(cd)>Integer.valueOf(ld)){
          String arr[]=id.split(",");
          for(int i=0;i<arr.length;i++){
            String sql1="INSERT INTO stud_attendance(stud_id,date)VALUES(?,CURRENT_TIMESTAMP)";
            PreparedStatement stmt1=con.prepareStatement(sql1);
            stmt1.setString(1, arr[i]);
            stmt1.executeUpdate();
          }
        }
     }
     //stud_attendance
     public static List<Integer> studattendance()throws SQLException{
      PreparedStatement stmt=null;
      con=DBconnectivity.getConnection();
      String sql="SELECT stud_id from stud_details";
      stmt=con.prepareStatement(sql);
      List<Integer>al=new ArrayList<>();
      ResultSet res=stmt.executeQuery();
      while (res.next()) {
        al.add(res.getInt("stud_id"));
      }
      return al;
     }
     //mark stud_attendance
     public static Boolean markatt(int id,int att)throws SQLException{
      con=DBconnectivity.getConnection();
      PreparedStatement stmt=null;
      String sql="UPDATE stud_attendance SET status=? WHERE stud_id=?";
      stmt=con.prepareStatement(sql);
      String a=Integer.toString(att);
      stmt.setString(1, a);
      stmt.setInt(2, id);
      int rt=stmt.executeUpdate();
      if(rt>0)
      return true;
      return false;
     }

    //authentication of staff login
    public static boolean authstaff(String email,String pass) throws SQLException{
       PreparedStatement stmt=null;
       con=DBconnectivity.getConnection();
       String sql="SELECT password from staff_details where email=? AND status='1'";
       stmt=con.prepareStatement(sql);
       stmt.setString(1, email);
       ResultSet res=stmt.executeQuery();
       if (res.next()) {
        String dbpass=res.getString(1);
        if(dbpass.equals(pass)){
            return true;
        }
       }
      return false;
    }


       // show staff
    public static List<Staff> showstaff(String email) throws Exception {
        PreparedStatement stmt = null;
        con = DBconnectivity.getConnection();
        String sql = "SELECT staff_id,name,dept from staff_details where email=?";
        stmt = con.prepareStatement(sql);
        stmt.setString(1, email);
        ResultSet res=stmt.executeQuery();
        List<Staff>li=new ArrayList<>();
        while(res.next()){
            Staff obj=new Staff();
            obj.setid(res.getInt("staff_id"));
            obj.setname(res.getString("name"));
            obj.setdept(res.getString("dept"));
            // obj.setsec(res.getString("sec"));
            li.add(obj);
        }
        return li;

    }

   public static int viewattendance(int id) throws SQLException{
    PreparedStatement stmt=null;
    con=DBconnectivity.getConnection();
    String sql="SELECT COUNT(id) FROM staff_attendance where status='1' AND staff_id=? GROUP BY staff_id";
    stmt=con.prepareStatement(sql);
    stmt.setInt(1, id);
    ResultSet rt=stmt.executeQuery();
    if(rt.next())
    {
        int co=rt.getInt("COUNT(id)");
        // System.out.println(co);
        return co;
    }
    return 0;
   }


   //percentage of attendance
   public static int peratt(int id) throws SQLException{
    PreparedStatement stmt=null;
    con=DBconnectivity.getConnection();
    String sql="SELECT COUNT(id)*100/SUM(COUNT(id)) OVER() FROM staff_attendance where status='1' AND staff_id=? GROUP BY staff_id";
    stmt=con.prepareStatement(sql);
    stmt.setInt(1, id);
    ResultSet rt=stmt.executeQuery();
    if(rt.next())
    {
        int co=rt.getInt(1);
        // System.out.println(co);
        return co;
    }
    return 0;
   }
   //Total no.of days
   public static int total(int id) throws SQLException{
    PreparedStatement stmt=null;
    con=DBconnectivity.getConnection();
    String sql="SELECT COUNT(DISTINCT date)FROM staff_attendance";
    stmt=con.prepareStatement(sql);
    // stmt.setInt(1, id);
    ResultSet rt=stmt.executeQuery();
    if(rt.next())
    {
        int co=rt.getInt(1);
        // System.out.println(co);
        return co;
    }
    return 0;
   }
}
