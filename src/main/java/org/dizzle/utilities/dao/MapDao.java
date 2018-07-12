package org.dizzle.utilities.dao;

import org.dizzle.utilities.model.HexMap;

public interface MapDao {

	public HexMap loadMap(String mapName);
	
	public void saveMap(HexMap map);
}
