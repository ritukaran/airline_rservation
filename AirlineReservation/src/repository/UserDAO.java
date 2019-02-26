package repository;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import uEntities.Users;
import utility.DbConnect;



public class UserDAO {

	static Connection dbConnection = null;

	public UserDAO() throws ClassNotFoundException, SQLException, IOException {

	}

	public static int save(Users e) throws ClassNotFoundException, SQLException, IOException {
		dbConnection = DbConnect.connect();

		int status = 0;
		try {
			
			PreparedStatement ps = dbConnection.prepareStatement("insert into usersignup(name,username,password,mobilenumber,email) values (?,?,?,?,?)");
			ps.setString(1, e.getName());
			ps.setString(2, e.getUsername());
			ps.setString(3, e.getPassword());
			ps.setString(4, e.getMobilenumber());
			ps.setString(5, e.getEmail());
			

			status = ps.executeUpdate();
         System.out.println("rows inserted");
			dbConnection.close();
			
		} catch (Exception ex) {
			ex.printStackTrace();
		}

		return status;
	}
	
	public static boolean validate(String name,String pass) throws ClassNotFoundException, SQLException, IOException{  
		dbConnection = DbConnect.connect();
		boolean status=false;  
		try{  
		  
		      System.out.println(name+" "+pass);
		PreparedStatement ps=dbConnection.prepareStatement("select * from usersignup where username=? and password=?");  
		
		ps.setString(1,name);  
		ps.setString(2,pass);  
		      
		ResultSet rs=ps.executeQuery();  
		status=rs.next();  
		       /* while(rs.next())
		        {
		        	System.out.println(rs.getString(3)+"\t"+rs.getString(4));
		        }*/
		}catch(Exception e){System.out.println(e);}  
		return status;  
		}  
	
	
	 public static int update(Users e) throws ClassNotFoundException, SQLException, IOException{  
			dbConnection = DbConnect.connect();

	        int status=0;  
	        try{  
	            
	            PreparedStatement ps=dbConnection.prepareStatement(  
	                         "update usersignup set name=?,username=?,password=?,mobilenumber=?,email=? where id=?");  
	            ps.setString(1,e.getName());  
	            ps.setString(2,e.getUsername());
	            ps.setString(3,e.getPassword());
	            ps.setString(4,e.getMobilenumber());
	            ps.setString(5,e.getEmail());  
	            ps.setInt(6,e.getId());  
	              
	            status=ps.executeUpdate();  
	              
	            dbConnection.close();  
	        }catch(Exception ex){ex.printStackTrace();}  
	          
	        return status;  
	    } 
	 public static int delete(int id) throws ClassNotFoundException, SQLException, IOException{  
			dbConnection = DbConnect.connect();

	        int status=0;  
	        try{  
	          
	            PreparedStatement ps=dbConnection.prepareStatement("delete from usersignup where id=?");  
	            ps.setInt(1,id);  
	            status=ps.executeUpdate();  
	              
	            dbConnection.close();  
	        }catch(Exception e){e.printStackTrace();}  
	          
	        return status;  
	    }  
	 public static List<Users> getAllEmployees() throws ClassNotFoundException, SQLException, IOException{  
			dbConnection = DbConnect.connect();

	        List<Users> list=new ArrayList<Users>();  
	          
	        try{  
	            PreparedStatement ps=dbConnection.prepareStatement("select * from usersignup");  
	            ResultSet rs=ps.executeQuery();  
	            while(rs.next()){  
                    Users e=new Users();  
	                e.setId(rs.getInt(1));  
	                e.setName(rs.getString(2));  
	                e.setUsername(rs.getString(2)); 
	                e.setPassword(rs.getString(3)); 
	                e.setMobilenumber(rs.getString(5));
	                e.setEmail(rs.getString(4));  
	                 
	                list.add(e);  
	            }  
	            dbConnection.close();  
	        }catch(Exception e){e.printStackTrace();}  
	          
	        return list;  
	    }

	public static Users getEmployeeById(int id) throws ClassNotFoundException, SQLException, IOException {
		dbConnection = DbConnect.connect();

		Users e=new Users();  
         
	        try{  
	           
	            PreparedStatement ps=dbConnection.prepareStatement("select * from usersignup where id=?");  
	            ps.setInt(1,id);  
	            ResultSet rs=ps.executeQuery();  
	            if(rs.next()){  
	                e.setId(rs.getInt(1));  
	                e.setName(rs.getString(2));  
	                e.setUsername(rs.getString(3));
	                e.setPassword(rs.getString(4)); 
	                e.setMobilenumber(rs.getString(5)); 
	                e.setEmail(rs.getString(6));  
	                 
	            }  
	            dbConnection.close();  
	        }catch(Exception ex){ex.printStackTrace();}  
	          
	        return e;  
	}  
	

}
