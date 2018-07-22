package org.dizzle.utilities.misc;

import java.util.HashMap;
import java.util.Map;

import org.dizzle.utilities.model.TempRange;
import org.dizzle.utilities.model.TempSpanChart;

public class WeatherTempTables {
	
	public static final Map<String, TempRange> tempTable = new HashMap<String, TempRange>();
	public static final TempSpanChart[] desertTemps = new TempSpanChart[12];
	
	public WeatherTempTables() {
		init();
	}
	
	//TODO: Should these be stored in persistence (file or DB) and loaded?
	

	public void init() {
		TempRange tr = new TempRange(-20, -40);
		tempTable.put("A", tr);
		tr = new TempRange(-15, -30);
		tempTable.put("B", tr);
		tr = new TempRange(-5, -20);
		tempTable.put("C", tr);
		tr = new TempRange(0, -10);
		tempTable.put("D", tr);
		tr = new TempRange(10, 0);
		tempTable.put("E", tr);
		tr = new TempRange(18, 10);
		tempTable.put("F", tr);
		tr = new TempRange(25, 10);
		tempTable.put("G", tr);
		tr = new TempRange(30, 15);
		tempTable.put("H", tr);
		tr = new TempRange(35, 15);
		tempTable.put("I", tr);
		tr = new TempRange(40, 20);
		tempTable.put("J", tr);
		tr = new TempRange(50, 30);
		tempTable.put("K", tr);
		tr = new TempRange(60, 40);
		tempTable.put("L", tr);
		tr = new TempRange(65, 45);
		tempTable.put("M", tr);
		tr = new TempRange(70, 50);
		tempTable.put("N", tr);
		tr = new TempRange(70, 55);
		tempTable.put("O", tr);
		tr = new TempRange(75, 55);
		tempTable.put("P", tr);
		tr = new TempRange(80, 60);
		tempTable.put("Q", tr);
		tr = new TempRange(80, 65);
		tempTable.put("R", tr);
		tr = new TempRange(85, 65);
		tempTable.put("S", tr);
		tr = new TempRange(85, 70);
		tempTable.put("T", tr);
		tr = new TempRange(90, 70);
		tempTable.put("U", tr);
		tr = new TempRange(90, 75);
		tempTable.put("V", tr);
		tr = new TempRange(95, 75);
		tempTable.put("W", tr);
		tr = new TempRange(100, 80);
		tempTable.put("X", tr);
		tr = new TempRange(105, 80);
		tempTable.put("Y", tr);
		tr = new TempRange(115, 85);
		tempTable.put("Z", tr);
	}
	
	
	public static void main(String[] args) {
		WeatherTempTables tables = new WeatherTempTables();
		//tables.init();
		
		for (String name : WeatherTempTables.getTemptable().keySet()) {
			String key = name;
			TempRange value = WeatherTempTables.getTemptable().get(key);
			System.out.println(key + "|" + value);
		}
	}


	public static Map<String, TempRange> getTemptable() {
		return tempTable;
	}
	
	
	
}
