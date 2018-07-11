package org.dizzle.utilities.model;

import java.util.ArrayList;
import java.util.List;

/**
 * This object will represent a single hex on the map. 
 * A hex will have a terrain type.
 * A hex will contain a certain number of features.
 * 
 * @author deastland
 *
 */
public class Hex {

	private int xCoord;
	private int yCoord;
	private TerrainType terrainType;
	
	List<Feature> features = new ArrayList<Feature>();
	
	public Hex() {
		
	}
	
	public Hex(int x, int y) {
		this.xCoord = x;
		this.yCoord = y;
	}

	public int getxCoord() {
		return xCoord;
	}

	public void setxCoord(int xCoord) {
		this.xCoord = xCoord;
	}

	public int getyCoord() {
		return yCoord;
	}

	public void setyCoord(int yCoord) {
		this.yCoord = yCoord;
	}

	public TerrainType getTerrainType() {
		return terrainType;
	}

	public void setTerrainType(TerrainType terrainType) {
		this.terrainType = terrainType;
	}

	public List<Feature> getFeatures() {
		return features;
	}

	public void setFeatures(List<Feature> features) {
		this.features = features;
	}
	
	@Override
	public String toString() {
		StringBuffer retStr = new StringBuffer("");
		
		retStr.append("Coordinates: " + xCoord + ":" + yCoord + "\n");
		retStr.append("Features: \n");
		for (Feature f : features) {
			retStr.append(" - " + f.getName() + "\n");
		}
		
		return retStr.toString();
	}
	

}
