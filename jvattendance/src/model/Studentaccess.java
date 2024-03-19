package model;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class Studentaccess {
    static Connection con=null;

     // show student
    public static List<Student> showstudent(String email) throws Exception {
        PreparedStatement stmt = null;
        con = DBconnectivity.getConnection();
        String sql = "SELECT stud_id,name,dept,sec from stud_details where email=?";
        stmt = con.prepareStatement(sql);
        stmt.setString(1, email);
        ResultSet res=stmt.executeQuery();
        List<Student>li=new ArrayList<>();
        while(res.next()){
            Student obj=new Student();
            obj.setid(res.getInt("stud_id"));
            obj.setname(res.getString("name"));
            obj.setdept(res.getString("dept"));
            obj.setsec(res.getString("sec"));
            li.add(obj);
        }
        return li;

    }

   public static int viewattendance(int id) throws SQLException{
    PreparedStatement stmt=null;
    con=DBconnectivity.getConnection();
    String sql="SELECT COUNT(id) FROM stud_attendance where status='1' AND stud_id=? GROUP BY stud_id";
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
    String sql="SELECT COUNT(id)*100/SUM(COUNT(id)) OVER() FROM stud_attendance where status='1' AND stud_id=? GROUP BY stud_id";
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
    String sql="SELECT COUNT(DISTINCT date)FROM stud_attendance;";
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

    public static boolean authstudent(String email,String pass) throws SQLException{
        PreparedStatement stmt=null;
        con=DBconnectivity.getConnection();
        String sql="SELECT password from stud_details where email=? AND status='1' ";
        stmt=con.prepareStatement(sql);
        stmt.setString(1, email);
        ResultSet res=stmt.executeQuery();
        if(res.next()){
            String dpass=res.getString(1);
            if(dpass.equals(pass)){
                return true;
            }
        }
        return false;
        
        
    }
}
