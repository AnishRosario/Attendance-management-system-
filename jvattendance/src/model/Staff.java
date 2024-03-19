package model;

public class Staff {
    private int id;
    private String email;
     private String name;
     private String dept;
     private String pass;
     private String phone;
     
     public void setid(int id){
        this.id=id;
    }
     public void setemail(String email){
        this.email=email;
    }
    public void setname(String name){
        this.name=name;
    }
    public void setdept(String dept){
        this.dept=dept;
    }
    public void setpass(String pass){
        this.pass=pass;
    }
    public void setphone(String ph){
        this.phone=phone;
    }

    public int getid(){
        return id;
    }
    public String getname(){
        return name;
    }
    public String getemail(){
        return email;
    }
    public String getpass(){
        return pass;
    }
    public String getphone(){
        return phone;
    }
    public String getdept(){
        return dept;
    }
}
