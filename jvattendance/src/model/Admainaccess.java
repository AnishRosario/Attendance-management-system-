package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;


public class Admainaccess {
    static Connection con = null;

    public static Boolean addadmin(String name, String email, String pass, String phone) throws SQLException {
        PreparedStatement stmt = null;
        con = DBconnectivity.getConnection();
        String sql = "INSERT INTO admin(name,email,password,phone) VALUES(?,?,?,?)";
        stmt = con.prepareStatement(sql);
        stmt.setString(1, name);
        stmt.setString(2, email);
        stmt.setString(3, pass);
        stmt.setString(4, phone);
        int n=stmt.executeUpdate();
        if(n>0){
            return true;
        }
        return false;
    }

    public static Boolean addstaff(String email, String name, String dept, String pass, String phone) throws Exception {
        PreparedStatement stmt = null;
        con = DBconnectivity.getConnection();
        String sql = "INSERT INTO staff_details(email,name,dept,password,phone) VALUES(?,?,?,?,?)";
        stmt = con.prepareStatement(sql);
        stmt.setString(1, email);
        stmt.setString(2, name);
        stmt.setString(3, dept);
        stmt.setString(4, pass);
        stmt.setString(5, phone);
        int n=stmt.executeUpdate();
        if(n>0){
            return true;
        }
        return false;
    }

    // show staff
    public static List<Staff> showstaff() throws Exception {
        PreparedStatement stmt = null;
        con = DBconnectivity.getConnection();
        String sql = "SELECT staff_id,name from staff_details";
        stmt = con.prepareStatement(sql);
        ResultSet res=stmt.executeQuery();
        List<Staff>li=new ArrayList<>();
        while(res.next()){
            Staff obj=new Staff();
            obj.setid(res.getInt("staff_id"));
            obj.setname(res.getString("name"));
            li.add(obj);
        }
        return li;

    }
     //remove staff
    public static Boolean removestaff(int remove) throws SQLException {
        PreparedStatement stmt = null;
        con = DBconnectivity.getConnection();
        // String sql = "DELETE FROM staff_details where staff_id=?";
        String sql="UPDATE staff_details SET status='0' where staff_id=?";
        stmt = con.prepareStatement(sql);
        // String s=Integer.toString(remove);
        stmt.setInt(1, remove);
        int n=stmt.executeUpdate();
         if(n>0){
            return true;
         }
         else{
            return false;
         }
    }
    //get& details from staff_details table
    public static void getdetails() throws SQLException{
     PreparedStatement stmt=null;
     con=DBconnectivity.getConnection();
     String sql="SELECT staff_id from staff_details";
     stmt=con.prepareStatement(sql);
     ResultSet res=stmt.executeQuery();
     String id="";
     while (res.next()) {
       id+=res.getString("staff_id")+",";
     }
     Admainaccess.insert(id);
    }
    //insert details into attendance table
     public static void insert(String id)throws SQLException{
        PreparedStatement stmt=null;
        con=DBconnectivity.getConnection();
        String sql="SELECT date FROM staff_attendance ORDER BY date DESC LIMIT 1";
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
                String sql1="INSERT INTO staff_attendance (staff_id,date) VALUES(?,CURRENT_TIMESTAMP) ";
                PreparedStatement stmt1=con.prepareStatement(sql1);
                stmt1.setString(1, arr[i]);
                stmt1.executeUpdate();
            }
        }

     }
    public static List<Integer> staffattendance()throws SQLException{
     PreparedStatement stmt=null;
     con=DBconnectivity.getConnection();
     String sql="SELECT staff_id from staff_details";
     stmt=con.prepareStatement(sql);
     List<Integer>al=new ArrayList<>();
     ResultSet res=stmt.executeQuery();
     while (res.next()) {
        al.add(res.getInt("staff_id"));
     }
     return al;
    }
    public static Boolean markatt(int id,int att)throws SQLException{
     con=DBconnectivity.getConnection();
        String sql="UPDATE staff_attendance SET status=? WHERE staff_id=?";
        PreparedStatement stmt=con.prepareStatement(sql);
        String a=Integer.toString(att);
        stmt.setString(1,a);
        stmt.setInt(2,id);
        int rt=stmt.executeUpdate();
        if(rt>0){
            return true;
        }
        return false;
        // System.out.println("true");
    }
     //admin authedication
    public static boolean authadmin(String email, String pass) throws SQLException {
        PreparedStatement stmt = null;
        con = DBconnectivity.getConnection();
        String sql = "SELECT password from admin where email=?";
        stmt = con.prepareStatement(sql);
        stmt.setString(1, email);
        ResultSet res = stmt.executeQuery();
        if (res.next()) {
            String dbpass = res.getString(1);
            if (dbpass.equals(pass)) {
                return true;
            }
        }
        return false;
    }
}
