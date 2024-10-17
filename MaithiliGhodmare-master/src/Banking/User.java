package Banking;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Scanner;

public class User { 
	private Scanner sc;
	private Connection con;

	public User(Connection con, Scanner sc) {
		this.sc=sc;
		this.con=con;
	}

	public void registration() {
		sc.nextLine();
		System.out.println("Full Name : ");
		String full_name = sc.nextLine();
		
		System.out.println("Email : ");
		String email = sc.nextLine();
		
		System.out.println("Password : ");
		String password = sc.nextLine();
		if (user_exist(email)) {
			System.out.println("User Already Exists for this Email Address");
			return;
		}
		
		String query="INSERT INTO user(full_name,email,password) VALUES(?,?,?)";
		try {
			PreparedStatement preparedStatement = con.prepareStatement(query);
			preparedStatement.setString(1,  full_name);
			preparedStatement.setString(2,  email);
			preparedStatement.setString(3,  password);
			int affectedRows = preparedStatement.executeUpdate();
			if(affectedRows>0) {
				System.out.println("Registration Successfull");
			}else {
				System.out.println("Registration Failed");
		}
			
		}catch(Exception e){
			System.out.println(e);
		}
	}

	
	
	
	private boolean user_exist(String email) {
	
		String login_query="SELECT * FROM User WHERE email = ?";
		try {
			PreparedStatement preparedStatement = con.prepareStatement(login_query);
			preparedStatement.setString(1,  email);
			ResultSet resultset = preparedStatement.executeQuery();
			if(resultset.next()) {
				return true;
			}
			else {
				return false;
			}
	}catch(Exception e){
		System.out.println(e);
	}
	return false;
		}
		

	public String login() {
		sc.nextLine();
		System.out.println("Email : ");
		String email = sc.nextLine();
		
		System.out.println("Password : ");
		String password = sc.nextLine();
		String login_query="SELECT * FROM User WHERE email = ? AND password = ? ";
		try {
			PreparedStatement preparedStatement = con.prepareStatement(login_query);
			
			preparedStatement.setString(1,  email);
			preparedStatement.setString(2,  password);
			ResultSet resultset = preparedStatement.executeQuery();
			if(resultset.next()) {
				return email;
			}else {
				return null;
		}
			
		}catch(Exception e){
			System.out.println(e);
		}
		return null;
		
		 
	}
	

}

