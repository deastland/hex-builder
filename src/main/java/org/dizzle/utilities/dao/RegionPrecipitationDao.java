package org.dizzle.utilities.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.dizzle.utilities.model.ClimaticRegion;
import org.dizzle.utilities.model.Month;
import org.dizzle.utilities.model.PrecipSpanChart;
import org.dizzle.utilities.model.WeatherTerrainType;

/**
 * Pull the low/mid/high range for precipitation in the given region and terrain for the time of year.
 * 
 * @author Zeus
 *
 */
public class RegionPrecipitationDao {

	public PrecipSpanChart getPercipitationSpanChart(ClimaticRegion climaticRegion, WeatherTerrainType weatherTerrainType, Month month) {
		PrecipSpanChart precipChart = null;
		
		Connection con = null;
		PreparedStatement ps = null;
		
		try {
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/hex", "root", "root");
			
			String sql = 
					"select v.low_precipitation, v.mid_precipitation, v.high_precipitation " + 
					"from hex.temperature_variation v " + 
					"where v.climatic_region = ? " + 
					"and v.weather_terrain = ? " + 
					"and v.month = ?";
			
			ps = con.prepareStatement(sql);
			ps.setString(1, climaticRegion.toString());
			ps.setString(2, weatherTerrainType.toString());
			ps.setInt(3, month.getMonthNumber());
			
			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				precipChart = new PrecipSpanChart(rs.getInt(1), rs.getInt(2), rs.getInt(3));
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

		return precipChart;
	}
	
	public static void main(String[] args) {
		RegionPrecipitationDao dao = new RegionPrecipitationDao();
		
		PrecipSpanChart precipChart = dao.getPercipitationSpanChart(ClimaticRegion.TEMPERATE, WeatherTerrainType.SEACOAST, Month.Hjarnsprig);
		
		System.out.println(precipChart);
	}
}
