package louerv;



import java.sql.Connection;
import java.sql.DriverManager;

public class Connectage {
	Connection con;
	public Connectage(){
		try{
			Class.forName("com.mysql.jdbc.Driver");
			con=DriverManager.getConnection("jdbc:mysql://localhost/dblouervoiture","root","");
			System.out.println("bien connectée!");
		}
		catch(Exception ex){
			
			System.out.println("probleme de connection!");	
		}
	}
	public Connection laconnexion(){
		return con;
	}

	}
