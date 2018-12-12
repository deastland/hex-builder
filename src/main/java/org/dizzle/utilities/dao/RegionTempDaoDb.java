package org.dizzle.utilities.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.dizzle.utilities.model.ClimaticRegion;
import org.dizzle.utilities.model.Month;
import org.dizzle.utilities.model.TempRange;
import org.dizzle.utilities.model.TempSpanChart;
import org.dizzle.utilities.model.WeatherTerrainType;

/**
 * Given a particular region (temperate, arctic, etc.) and a terrain type (forest, desert,etc.) and the date - return the low/mid/high ranges.
 * 
 * 
 * @author Zeus
 *
 */
public class RegionTempDaoDb implements RegionTempDao {

	public TempSpanChart getTempSpanChart(ClimaticRegion climaticRegion, WeatherTerrainType weatherTerrainType, Month month) {
		TempSpanChart returnObject = null;
		
		Connection con = null;
		PreparedStatement ps = null;
		
		try {
			//Class.forName("com.mysql.jdbc.Driver");
			
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/hex", "root", "root");
			
			String sql = 
					"select v.climatic_region, v.weather_terrain, s1.low_temp, s1.high_temp, s2.low_temp, s2.high_temp, s3.low_temp, s3.high_temp " + 
					"from hex.temperature_variation v, hex.temp_span s1, hex.temp_span s2, hex.temp_span s3 " + 
					"where v.low_span_id = s1.id " + 
					"and v.mid_span_id = s2.id " + 
					"and v.high_span_id = s3.id " + 
					"and v.climatic_region = ? " + 
					"and v.weather_terrain = ? " + 
					"and v.month = ?";

			
			ps = con.prepareStatement(sql);
			ps.setString(1, climaticRegion.toString());
			ps.setString(2, weatherTerrainType.toString());
			ps.setInt(3, month.getMonthNumber());
			
			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				TempRange lowRange = new TempRange(rs.getInt(3), rs.getInt(4));
				TempRange midRange = new TempRange(rs.getInt(5), rs.getInt(6));
				TempRange highRange = new TempRange(rs.getInt(7), rs.getInt(8));
				returnObject = new TempSpanChart(lowRange, midRange, highRange);
				
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				ps.close();
				con.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		
		return returnObject;
	}

	
	// Test the database temp puller.
	public static void main(String[] args) {
		RegionTempDaoDb dao = new RegionTempDaoDb();
		
		TempSpanChart chart = dao.getTempSpanChart(ClimaticRegion.TEMPERATE, WeatherTerrainType.FOREST, Month.Lewfrang);
		
		System.out.println(chart);
		
		System.out.println("Today's Temp: ");
		
	}
}
