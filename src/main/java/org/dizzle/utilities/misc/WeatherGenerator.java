package org.dizzle.utilities.misc;

import org.dizzle.utilities.dao.RegionPrecipitationDao;
import org.dizzle.utilities.dao.RegionTemperatureDao;
import org.dizzle.utilities.model.ClimaticRegion;
import org.dizzle.utilities.model.Month;
import org.dizzle.utilities.model.PrecipSpanChart;
import org.dizzle.utilities.model.PrecipitationAmount;
import org.dizzle.utilities.model.PrecipitationType;
import org.dizzle.utilities.model.TempRange;
import org.dizzle.utilities.model.TempSpanChart;
import org.dizzle.utilities.model.WeatherTerrainType;

public class WeatherGenerator {
	
	// Temperature
	private TempRange[] possibleTempSpans = new TempRange[3];			// Element 0..1..2 is low..med..high temp spans
	private int currentTempRange = 2;									// The current temp span in effect.
	
	// Precipitation
	private PrecipSpanChart precipSpanChart = new PrecipSpanChart();	// The low/mid/high precipitation amount
	private boolean chancePrecipitation = false;						// Is precipitation possible today?
	private PrecipitationAmount precipAmount = PrecipitationAmount.NONE;// The current precipitation amount
	private PrecipitationType precipType = PrecipitationType.RAIN;		// Is it rain, sleet, or snow.

	// Wind
	private int windSpeed = 0;
	
	// Track weather streaks/waves
	private int streakCounter = 0;
	private boolean inStreak = false;
	
	//////////////// CONSTRUCTORS ///////////////////
	
	public WeatherGenerator() {	
		this(ClimaticRegion.TEMPERATE, WeatherTerrainType.PLAINS, Month.Hjarnsprig);
	}
	
	public WeatherGenerator(ClimaticRegion region, WeatherTerrainType terrainType, Month month) {
		this.newWeatherDay(region, terrainType, month);
	}
	
	/////////////// FUNCTIONALITY ////////////////////
	
	/**
	 * Calculate a new day of weather, given the conditions.
	 * 
	 * @param climaticRegion - The climatic region (arctic, temperate, etc.)
	 * @param weatherTerrainType - The terrain type (plains, forest, mountains, etc.)
	 * @param month - The month of the year.
	 */
	public void newWeatherDay(ClimaticRegion climaticRegion, WeatherTerrainType weatherTerrainType, Month month) {
		// Get the chart of possible temps.
		pullTempSpanChart(climaticRegion, weatherTerrainType, month);
		
		// Get the chart of possible precipitation amounts.
		pullPrecipSpanChart(climaticRegion, weatherTerrainType, month);
		
		// Using the current temp possibilities, calculate temp, wind, and precipitation.
		calculateTodaysWeather(climaticRegion);
	}
	
	private void pullPrecipSpanChart(ClimaticRegion climaticRegion, WeatherTerrainType weatherTerrainType, Month month) {
		
		RegionPrecipitationDao dao = new RegionPrecipitationDao();
		
		this.precipSpanChart = dao.getPercipitationSpanChart(climaticRegion, weatherTerrainType, month);
		
	}

	/**
	 * Uses the day-to-day change logic (Wilderness Survival Guide p. 109) to get the days temp/wind/precip.
	 * 
	 * @param climaticRegion - The climatic region (arctic, tropical, temperate, etc.)
	 */
	private void calculateTodaysWeather(ClimaticRegion climaticRegion) {
		
		int dieRoll1 = DieRoller.rollD6();
		int dieRoll2 = DieRoller.rollD6();
		int result = dieRoll1 + dieRoll2;
		int stepChange = 0;
		
		switch(result) {
		case(2):
			stepChange = -3;
			this.chancePrecipitation = true;
			break;
		case(3):
			stepChange = -2;
			changeWindSpeed(15, climaticRegion);
			this.chancePrecipitation = false;
			break;
		case(4):
			stepChange = -2;
			changeWindSpeed(10, climaticRegion);
			this.chancePrecipitation = true;
			break;
		case(5):
			stepChange = -1;
			changeWindSpeed(15, climaticRegion);
			this.chancePrecipitation = false;
			break;
		case(6):
			stepChange = -1;
			changeWindSpeed(10, climaticRegion);
			this.chancePrecipitation = true;
			
			// If a cold wave, roll of 6 is treated like a roll of 8.
			if (this.currentTempRange == 0 && inStreak) {
				stepChange = 1;
			}
			break;
		case(7):
			// If we are at max temp, treat a roll of 7 like a roll of 6.
			// If we are at min temp, treat a roll of 7 like a roll of 8.
			if (this.currentTempRange == 2) {
				stepChange = -1;
				this.chancePrecipitation = true;
			} else if (this.currentTempRange == 0) {
				stepChange = 1;
				this.chancePrecipitation = true;
			}
			break;
		case(8):
			stepChange = 1;
			this.chancePrecipitation = true;
			changeWindSpeed(-10, climaticRegion);

			// If a heat wave, roll of 8 treated like roll of 6.
			if (this.currentTempRange == 2 && inStreak) {
				stepChange = -1;
			}
			break;
		case(9):
			stepChange = 1;
			changeWindSpeed(-15, climaticRegion);
			this.chancePrecipitation = false;
			break;
		case(10):
			stepChange = 2;
			changeWindSpeed(-10, climaticRegion);
			this.chancePrecipitation = true;
			break;
		case(11):
			stepChange = 2;
			changeWindSpeed(-15, climaticRegion);
			this.chancePrecipitation = false;
			break;
		case(12):
			stepChange = 3;
			this.chancePrecipitation = true;
			break;
		default:
			break;
		}
		
		
		// Modify the temperature range as indicated in the variation roll above.
		// But don't go out of the possible range (0-2).
		currentTempRange += stepChange;
		if (currentTempRange < 0) {
			currentTempRange = 0;
		}
		
		if (currentTempRange > 2) {
			currentTempRange = 2;
		}
		
		// Check to see if we have a hot/cold weather streak ongoing.
		if (currentTempRange == 0 || currentTempRange == 2) {
			streakCounter++;
		} else {
			streakCounter = 0;
		}
		
		// If we have 2 consecutive days at min/max, we are in a streak.
		this.inStreak = streakCounter > 1;
		
		// Check for precipitation amount, if there is any at all.
		if (this.chancePrecipitation) {
			
			// Die rolls above determine precip amount.
			if (dieRoll1 > dieRoll2) {
				this.precipAmount = this.precipSpanChart.getLowPrecip();
			} else if (dieRoll2 > dieRoll1) {
				this.precipAmount = this.precipSpanChart.getMidPrecip();
			} else {
				this.precipAmount = this.precipSpanChart.getHighPrecip();
			}

			// Precip type depends on temp.
			this.precipType = PrecipitationType.RAIN;
			if (this.possibleTempSpans[this.currentTempRange].getHigh() < 40) {
				this.precipType = PrecipitationType.SLEET;
			}
			if (this.possibleTempSpans[this.currentTempRange].getHigh() < 32) {
				this.precipType = PrecipitationType.SNOW;
			}
		} else {
			this.precipAmount = PrecipitationAmount.NONE;
			this.precipType = PrecipitationType.NONE;
		}
		
		
	}
	
	/**
	 * For a given climatic regoin, terrain type, and month - pull the low, mid, and high temp range.
	 * 
	 * @param climaticRegion - The climatic region currently being checked (arctic, temperate, etc.)
	 * @param weatherTerrainType - The terrain type (desert, forest, etc.)
	 * @param month - The month of the year, which also gives us the season.
	 */
	private void pullTempSpanChart(ClimaticRegion climaticRegion, WeatherTerrainType weatherTerrainType, Month month) {

		RegionTemperatureDao tempDao = new RegionTemperatureDao();
		
		TempSpanChart todaysChart = tempDao.getTempSpanChart(climaticRegion, weatherTerrainType, month);
		
		this.possibleTempSpans[0] = todaysChart.getLowRange();
		this.possibleTempSpans[1] = todaysChart.getMidRange();
		this.possibleTempSpans[2] = todaysChart.getHighRange();
		
	}
	
	private void changeWindSpeed(int value, ClimaticRegion climaticRegion) {
		int tempWindSpeed = this.windSpeed;
		
		tempWindSpeed += value;
		if (tempWindSpeed > climaticRegion.getMaxWindSpeed()) {
			tempWindSpeed = climaticRegion.getMaxWindSpeed();
		} 
		
		if (tempWindSpeed < 0) {
			tempWindSpeed = 0;
		}
		
		this.windSpeed = tempWindSpeed;
		
	}
	
	public static void main(String[] args) {
		WeatherGenerator gen = new WeatherGenerator(ClimaticRegion.TEMPERATE, WeatherTerrainType.PLAINS, Month.Hjarnfrang);
		
		int numDays = 10;
		
		for (int i=0; i <= numDays; i++) {
			gen.newWeatherDay(ClimaticRegion.TEMPERATE, WeatherTerrainType.PLAINS, Month.Hjarnfrang);
			System.out.println("Day " + i + ": " + gen);
			
		}
	}
	
	@Override
	public String toString() {
		StringBuilder retStr = new StringBuilder("");
		
		retStr.append(" --------------------------------- WEATHER MANAGER -------------------------------------\n");
		retStr.append("Today's TempSpan: ").append(this.possibleTempSpans[this.currentTempRange].toString()).append("\n");
		retStr.append("Today's Windspeed: ").append(this.windSpeed).append("\n");
		retStr.append("Perciptitation? ").append(this.chancePrecipitation).append("\n");
		retStr.append("Precip Amount: ").append(this.precipAmount).append(" of ").append(this.precipType).append("\n");
		
		return retStr.toString();
	}

	public PrecipSpanChart getPrecipSpanChart() {
		return precipSpanChart;
	}

	public void setPrecipSpanChart(PrecipSpanChart precipSpanChart) {
		this.precipSpanChart = precipSpanChart;
	}

	public PrecipitationAmount getPrecipAmount() {
		return precipAmount;
	}

	public void setPrecipAmount(PrecipitationAmount precipAmount) {
		this.precipAmount = precipAmount;
	}

	public PrecipitationType getPrecipType() {
		return precipType;
	}

	public void setPrecipType(PrecipitationType precipType) {
		this.precipType = precipType;
	}

}
