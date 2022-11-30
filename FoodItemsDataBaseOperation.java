package edu.airbnb.foodappdao;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.Properties;

import edu.airbnb.foodappdto.FoodItems;
import edu.airbnb.foodappdto.SortFoodByName;

public class FoodItemsDataBaseOperation {
	
	public Connection registerDriver()
	{
		File f =new File("./myfiles/credentials.properties"); 
		Connection con=null;
		try {
			FileReader read_file= new FileReader(f);
			Properties info=new Properties(); 
			info.load(read_file); 
			
			String driver= info.getProperty("driverclass");
			String dburl=info.getProperty("dburl");
			Class.forName(driver); 
			
			con=DriverManager.getConnection(dburl, info);
			return con;
		} catch (IOException | SQLException | ClassNotFoundException e) {
			e.printStackTrace();
		} 
		return con;
		
	}
	
	public boolean addFoodItems(FoodItems f)
	{
		Connection con=null; 
		PreparedStatement pstmt=null; 
		
		con=registerDriver();
		String query="INSERT INTO food_items VALUES (?,?,?,?,0)";
		try {
			pstmt=con.prepareStatement(query);
			pstmt.setInt(1,f.getFood_id());
			pstmt.setString(2, f.getFood_name());
			pstmt.setDouble(3, f.getPrice());
			pstmt.setDouble(4, f.getRating());
			
			int rows_affected=pstmt.executeUpdate(); 
			
			return rows_affected!=0;
		
		} catch (SQLException e) {
			// TODO Auto-generated catch block
		} 
		return false;
	}
	
	public ArrayList<FoodItems> viewAllFoodItems()
	{
		Connection con=null;
		Statement stmt=null; 
		ResultSet rs=null;
		ArrayList<FoodItems> food_list=new ArrayList<>(); 
		
		con=registerDriver();
		String query="SELECT * FROM food_items"; 
		try {
			stmt=con.createStatement();
			rs=stmt.executeQuery(query);
			
			while(rs.next())
			{
				FoodItems f = new FoodItems(); 
				f.setFood_id(rs.getInt(1));
				f.setFood_name(rs.getString(2));
				f.setPrice(rs.getDouble(3));
				f.setRating(rs.getDouble(4));
				f.setCustomerRating(rs.getDouble(5));
				
				food_list.add(f);
			}
			return food_list;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return food_list;
	}
	
	public boolean removeFoodItems(FoodItems f)
	{
		Connection con=null; 
		PreparedStatement pstmt=null;
	
		con=registerDriver(); 
	
		String query="DELETE FROM food_items WHERE food_id=?";
		try {
			pstmt=con.prepareStatement(query);
			pstmt.setInt(1, f.getFood_id());
			int rows_affected=pstmt.executeUpdate(); 
			
			return rows_affected!=0;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		finally
		{
			if(con!=null) {
				try {
					con.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if(pstmt!=null)
			{
				try {
					pstmt.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		return false; 
	}
	
	public FoodItems orderFood(FoodItems f)
	{
		Connection con=null; 
		con=registerDriver(); 
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		
		String query="SELECT * FROM food_items WHERE food_id=?";
		try {
			pstmt=con.prepareStatement(query);
			pstmt.setInt(1, f.getFood_id());
			rs=pstmt.executeQuery(); 
			
			while(rs.next())
			{
				f.setFood_id(rs.getInt(1));
				f.setFood_name(rs.getString(2));
				f.setPrice(rs.getDouble(3));
			}
			
			return f;
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally
		{
			if(con!=null) {
				try {
					con.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if(pstmt!=null)
			{
				try {
					pstmt.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if(rs!=null)
			{
				try {
					rs.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		return f; 
	}
	
	public FoodItems searchFoodItem(String foodname)
	{
		Connection con=null;
		PreparedStatement pstmt=null; 
		ResultSet rs=null;
		
		con=registerDriver();
		String query="SELECT * FROM food_items WHERE food_name=?";
		try {
			FoodItems f= new FoodItems();
			pstmt=con.prepareStatement(query);
			pstmt.setString(1, foodname);
			rs=pstmt.executeQuery();
			
			while(rs.next())
			{
				f.setFood_id(rs.getInt(1));
				f.setFood_name(rs.getString(2));
				f.setPrice(rs.getDouble(3));
			}
			return f;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		finally
		{
			if(con!=null) {
				try {
					con.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if(pstmt!=null)
			{
				try {
					pstmt.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if(rs!=null)
			{
				try {
					rs.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		
		return null;
	}
	
	public boolean feedback(FoodItems f)
	{
		Connection con=null; 
		PreparedStatement pstmt=null; 
		
		con=registerDriver(); 
		String query="UPDATE food_items SET crating =? WHERE (food_id = ?)";
		try {
			pstmt=con.prepareStatement(query);
//			pstmt.setInt(1, f.getFood_id());
//			pstmt.setDouble(2, f.getPrice());
//			pstmt.setDouble(3, f.getRating());
			pstmt.setDouble(1, f.getCustomerRating());
			pstmt.setInt(2, f.getFood_id());
			
			int rows_affected=pstmt.executeUpdate();
			
			return rows_affected!=0;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		finally
		{
			if(con!=null) {
				try {
					con.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if(pstmt!=null)
			{
				try {
					pstmt.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		return false;
		
	}
	
	
	public PriorityQueue<FoodItems> viewFoodBasedOnRating(int ch)
	{
		ArrayList<FoodItems> a= viewAllFoodItems();
		if(ch==1)	
		{
			PriorityQueue<FoodItems> f= new PriorityQueue<>(a);
			return f;
		}else 
		{
			PriorityQueue<FoodItems> fs= new PriorityQueue<>(new SortFoodByName());
			fs.addAll(a);
			return fs;
		}
		
	}
	
	
}
