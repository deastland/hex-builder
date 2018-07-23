package org.dizzle.utilities.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


public class DbTest {

	public static void main(String[] args) {

		try {
			//Class.forName("com.mysql.jdbc.Driver");
			
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/hex", "root", "root");
			
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery("select * from hex.hexes");
			
			while (rs.next()) {
				System.out.println(rs.getInt(1) + "|" + rs.getString(4));
			}
			
			stmt.close();
			con.close();
		
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}
}
