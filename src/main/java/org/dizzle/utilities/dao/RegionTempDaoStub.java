package org.dizzle.utilities.dao;

import org.dizzle.utilities.model.*;

import java.sql.*;

/**
 * Given a particular region (temperate, arctic, etc.) and a terrain type (forest, desert,etc.) and the date - return the low/mid/high ranges.
 * 
 * 
 * @author Zeus
 *
 */
public class RegionTempDaoStub implements RegionTempDao {

	public TempSpanChart getTempSpanChart(ClimaticRegion climaticRegion, WeatherTerrainType weatherTerrainType, Month month) {

		TempSpanChart returnObject = new TempSpanChart();

		returnObject.setHighRange(new TempRange(75, 90));
		returnObject.setMidRange(new TempRange(65, 75));
		returnObject.setLowRange(new TempRange(60, 70));

		return returnObject;
	}

	
	// Test the database temp puller.
	public static void main(String[] args) {
		RegionTempDaoStub dao = new RegionTempDaoStub();
		
		TempSpanChart chart = dao.getTempSpanChart(ClimaticRegion.TEMPERATE, WeatherTerrainType.FOREST, Month.Lewfrang);
		
		System.out.println(chart);
		
		System.out.println("Today's Temp: ");
		
	}
}
