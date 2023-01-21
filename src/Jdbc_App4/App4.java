package Jdbc_App4;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;
import java.util.*;

//insert multiple rows by single query

public class App4 {
	
	public static void main(String[] args) throws Exception {
		
		Scanner sc=new Scanner(System.in);
		ArrayList<String> list=new ArrayList<String>();
		
		
		while(true) {
			System.out.println("Enter Employee Code");
			int eno=sc.nextInt();
			System.out.println("Enter EmpName");
			String name=sc.next();
			System.out.println("Enter emp Salary");
			double esal=sc.nextDouble();
			sc.nextLine();
			System.out.println("Enter empAdd");
			String empAdd=sc.nextLine();
			
			String value="("+eno+",'"+name+"',"+esal+",'"+empAdd+"')";
			
			list.add(value);
			
			System.out.println("Do you want add more employees(Y/N)");
			
			String option=sc.next();
			
			if(option.equalsIgnoreCase("n")) {
				break;
			}
			
			
			
			
		}
		
		String query=String.format("Insert into employee(eno,ename,esal,eaddr) values");
		
		
		for(String v1:list) {
			
			query+=v1+",";
			
		}
		
		//System.out.println("Final Query:\n"+query);
		
		String subQuery=query.substring(0,query.length()-1);
		System.out.println(subQuery);
		int updatedRows=insertData(subQuery);
		System.out.println(updatedRows+" rows inserted");
		

		
		
		
		
		
		
		
		
		
		
	}
	public static int insertData(String query)throws Exception {
		int updatedRow=0;
		Connection con=getDbConnection();
		if(con!=null) {
			
			Statement st=con.createStatement();
			updatedRow=st.executeUpdate(query);
			return updatedRow;
			
			
		}else {
			System.out.println("Connection to database is failed");
			return 0;
		}
		
		
		
	}
	
	public static Connection getDbConnection() throws IOException,SQLException {
		
		Connection con=null;
		
		FileInputStream fis =new FileInputStream(new File("C:\\Users\\MPawar\\Desktop\\mahesh\\learnings\\java\\git_projects\\JDBC","db.properties"));
		
		Properties props=new Properties();
		props.load(fis);
		
		con=DriverManager.getConnection(props.getProperty("url"), props.getProperty("user"),props.getProperty("password"));
		
		return con;
	}


}
