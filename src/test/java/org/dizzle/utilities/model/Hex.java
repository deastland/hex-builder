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

	int xCoord;
	int yCoord;
	
	List<Feature> features = new ArrayList<Feature>();
}
