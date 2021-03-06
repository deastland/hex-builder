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

	private int id;										// Unique ID of hex. For database storage.
	private Coordinates location = new Coordinates();	// The Cartesian coordinates of the hex (X,Y).
	private TravelTerrainType terrainType = null;		// The dominant terrain type in this hex.
	private ClimaticRegion climaticRegion = 
			ClimaticRegion.TEMPERATE;					// The climatic region (arctic, tropical, etc.) the hex is in.
	private WeatherTerrainType weatherTerrainType = 
			WeatherTerrainType.PLAINS;					// The terrain type for weather.
	private boolean polite = false;						// A polite hex is a hex within distance 2 of a civilization hex (town, city, etc.).
	List<EncounterLocation> features = 
			new ArrayList<EncounterLocation>();			// A list of things in this hex. Some might be obvious (automatically seen).
	
	////// CONSTRUCTORS ////////
	public Hex() {
	}
	
	public Hex(int x, int y) {
		this.location.setX(x);
		this.location.setY(y);
	}
	
	public Hex(int x, int y, TravelTerrainType type) {
		this.location.setX(x);
		this.location.setY(y);
		this.terrainType = type;
	}
	
	public Hex(int x, int y, TravelTerrainType terrainType, ClimaticRegion climaticRegion, WeatherTerrainType weatherType) {
		this.location.setX(x);
		this.location.setY(y);
		this.terrainType = terrainType;
		this.climaticRegion = climaticRegion;
		this.weatherTerrainType = weatherType;
	}
	
	public Hex(Coordinates loc) {
		this(loc, null);
	}
	
	public Hex(Coordinates loc, TravelTerrainType type) {
		this.location = loc;
		this.terrainType = type;
	}
	
	public Hex(Coordinates loc, TravelTerrainType terrainType, ClimaticRegion climate, WeatherTerrainType weatherType) {
		this.location = loc;
		this.terrainType = terrainType;
		this.climaticRegion = climate;
		this.weatherTerrainType = weatherType;
	}
	
	// Functional methods
	public boolean isAdjacentTo(Hex h) {
		return MapUtils.getRange(this, h) == 1;
	}

	public boolean hasObviousFeature() {
		for (EncounterLocation f : this.features) {
			if (f.isObvious()) {
				return true;
			}
		}
		
		return false;
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

	/////////////// GETTERS AND SETTERS /////////////////
	public TravelTerrainType getTerrainType() {
		return terrainType;
	}

	public void setTerrainType(TravelTerrainType terrainType) {
		this.terrainType = terrainType;
	}

	public List<EncounterLocation> getFeatures() {
		return features;
	}

	public void setFeatures(List<EncounterLocation> features) {
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

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}


	@Override
	public String toString() {
		StringBuffer retStr = new StringBuffer("");
		
		retStr.append(" ------------------- HEX -------------------\n");
		retStr.append("HexId: " + this.id).append("\n");
		retStr.append("Coordinates: ").append(location.getX()).append(":").append(location.getY()).append("\n");
		retStr.append("Terrain type: ").append(terrainType).append("\n");
		retStr.append("Polite: ").append(polite).append("\n");
		retStr.append("Features: \n");
		for (EncounterLocation f : features) {
			retStr.append(" - ").append(f.getType()).append(" : ").append(f.getName())
			.append("| Obvious: ").append(f.isObvious()).append(" | SpecialVenue: ").append(f.getSpecialVenue()).append("\n");
		}
		
		return retStr.toString();
	}

	public WeatherTerrainType getWeatherTerrainType() {
		return weatherTerrainType;
	}

	public void setWeatherTerrainType(WeatherTerrainType weatherTerrainType) {
		this.weatherTerrainType = weatherTerrainType;
	}

	public ClimaticRegion getClimaticRegion() {
		return climaticRegion;
	}

	public void setClimaticRegion(ClimaticRegion climaticRegion) {
		this.climaticRegion = climaticRegion;
	}
	

}
