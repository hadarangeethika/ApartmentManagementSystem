package helper;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
//import java.util.ArrayList;

import model.Employee;


public class EmployeeHelper {
    
               private static String sql=null;
               @SuppressWarnings("unused")
			private static ResultSet res=null;
               private static PreparedStatement ps=null;
               
               //Add  an Employee
               public static boolean addEmployee(Employee emp) throws SQLException, ClassNotFoundException{
               
                        if(DBConnect.getInstance() != null){
                        
                                Connection con= DBConnect.getConnction();//get Connection details
                        
                                sql ="INSERT INTO employee(eid,fname,lname,dob,address,tpNo,salary,position,bankAccountNo)"
                                        +"VALUES(?,?,?,?,?,?,?,?,?,?,?)";
                                ps = con.prepareStatement(sql);//returns statement that is editable for custom values
                                
                                //set values to the query 
                                ps.setString(1, emp.getEid());
                                ps.setString(2,emp.getFname());
                                ps.setString(3, emp.getLname());
                                ps.setString(4,emp.getDob() );
                                ps.setString(5, emp.getAddress());
                                ps.setString(5,emp.getTpNo() );
                                ps.setDouble(6,emp.getSalary() );
                                ps.setString(7, emp.getPosition());
                                ps.setString(8, emp.getBankAccNo() );
                                
                                int query1 = ps.executeUpdate();//returns boolean
                                
                               //array of qualification 
                                sql= " INSERT INTO empQualification(eid,qualify)"+"VALUES(?,?)";
                                ps = con.prepareStatement(sql);
                                 
                                  String[] qualify= emp.getQualification();
                                 
                                for(int i=0;i<5;i++)
                                {
                                    if(qualify[i]!=null){
                                   
                                        ps.setString(1, emp.getEid());
                                        ps.setString(2, qualify[i]);
                                    }   
                                }
                                
                                int query2 = ps.executeUpdate();//returns boolean
                                
                                //array of school
                                sql="INSERT INTO empSchool(eid,school) "+"VALUES(?,?)";
                                ps = con.prepareStatement(sql);
                                
                                String[] school= emp.getSchool();
                                for(int i=0;i<5;i++)
                                {
                                    if(school[i]!=null){
                                   
                                        ps.setString(1, emp.getEid());
                                        ps.setString(2, school[i]);
                                    }   
                                }
                                
                                 int query3 = ps.executeUpdate();//returns boolean
                                 
                                 
                                 if(query1==1 && query2==1 && query3==1)
                                           return true; //Successfully executed      
                                 else 
                                           return false;                       
                        }
                        
                      return false;      
               }
               

}
