package org.dizzle.utilities.model;

import java.util.ArrayList;
import java.util.List;

/**
 * This is the entire map, which is a collection of hexes.
 * @author deastland
 *
 */
public class HexMap {

	private int mapId;
	private String mapName;
	private Coordinates startingPoint = new Coordinates(0,0);
	
	private List<Hex> hexes = new ArrayList<Hex>();

	
	
	
	
	
	////// Getters & Setters
	public int getMapId() {
		return mapId;
	}

	public void setMapId(int mapId) {
		this.mapId = mapId;
	}

	public String getMapName() {
		return mapName;
	}

	public void setMapName(String mapName) {
		this.mapName = mapName;
	}

	public Coordinates getStartingPoint() {
		return startingPoint;
	}

	public void setStartingPoint(Coordinates startingPoint) {
		this.startingPoint = startingPoint;
	}

	public List<Hex> getHexes() {
		return hexes;
	}

	public void setHexes(List<Hex> hexes) {
		this.hexes = hexes;
	}
	
}
