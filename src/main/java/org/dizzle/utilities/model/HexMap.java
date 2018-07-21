package org.dizzle.utilities.model;

import java.util.ArrayList;
import java.util.List;

/**
 * This is the entire map, which is a collection of hexes.
 * @author deastland
 *
 */
public class HexMap {

	private int mapId;											// ID number of the map. Helps with database storage.
	private String mapName;										// Name of this map
	private Coordinates startingPoint = new Coordinates(0,0);	// The "base" for exploration. (not sure if I need this)
	
	private List<Hex> hexes = new ArrayList<Hex>();				// This is all the hexes within the map.

	
	// Given a set of coordinates, return the hex at that location.
	public Hex getHex(Coordinates c) {
		
		for (Hex h : hexes) {
			if (h.getLocation().equals(c)) {
				return h;
			}
		}
		
		return null;
	}
	
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
