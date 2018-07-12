package org.dizzle.utilities.model;

import java.util.ArrayList;
import java.util.List;

/**
 * This object will represent a single hex on the map. 
 * A hex will have a terrain type.
 * A hex will contain a certain number of features.
 * 
 * Currently assuming 12-mile wide hexes.
 * 
 * @author deastland
 *
 */
public class Hex {

	private Coordinates location = new Coordinates();			// The Cartesian coordinates of the hex (X,Y).
	private TerrainType terrainType = null;				// The dominant terrain type in this hex.
	private boolean polite = false;						// A polite hex is a hex within distance 2 of a civilization hex (town, city, etc.).
	List<Feature> features = new ArrayList<Feature>();	// A list of things in this hex. Some might be obvious (automatically seen).
	
	public Hex() {
	}
	
	public Hex(int x, int y) {
		this.location.setX(x);
		this.location.setY(y);
	}
	
	public Hex(int x, int y, TerrainType type) {
		this.location.setX(x);
		this.location.setY(y);
		this.terrainType = type;
	}
	
	public Hex(Coordinates loc) {
		this(loc, null);
	}
	
	public Hex(Coordinates loc, TerrainType type) {
		this.location = loc;
		this.terrainType = type;
	}
	
	public boolean isAdjacentTo(Hex h) {
		return MapUtils.getRange(this, h) == 1;
	}

	public int getX() {
		return getxCoord();
	}
	
	public int getxCoord() {
		return this.location.getX();
	}

	public void setxCoord(int xCoord) {
		this.location.setX(xCoord);
	}

	public int getY() {
		return getyCoord();
	}
	
	public int getyCoord() {
		return this.location.getY();
	}

	public void setyCoord(int yCoord) {
		this.location.setY(yCoord);;
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
	
	public Coordinates getLocation() {
		return location;
	}

	public void setLocation(Coordinates location) {
		this.location = location;
	}

	public boolean isPolite() {
		return polite;
	}

	public void setPolite(boolean polite) {
		this.polite = polite;
	}

	public boolean hasObviousFeature() {
		for (Feature f : this.features) {
			if (f.isObvious()) {
				return true;
			}
		}
		
		return false;
	}

	@Override
	public String toString() {
		StringBuffer retStr = new StringBuffer("");
		
		retStr.append("Coordinates: " + location.getX() + ":" + location.getY() + "\n");
		retStr.append("Terrain type: " + terrainType + "\n");
		retStr.append("Features: \n");
		for (Feature f : features) {
			retStr.append(" - " + f.getType() + " : " + f.getName() + " <-- Obvious: " + f.isObvious() + "\n");
		}
		
		return retStr.toString();
	}
	

}