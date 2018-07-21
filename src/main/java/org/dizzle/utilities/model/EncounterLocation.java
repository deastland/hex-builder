package org.dizzle.utilities.model;

/**
 * A Feature is something in a hex that can be discovered.
 * This can be either a landmark, a lair, or a point of interest that can be discovered in a hex.
 * 
 * @author deastland
 *
 */
public class EncounterLocation extends Encounter {

	private FeatureType type;
	private boolean visited = false;
	private FeatureLocation featureLocation;
	
	// Optional idea: visibility index. 
	// The visibility number is the number of hexes from which a feature is visible.
	// -1 : Hidden. Difficult to discover.
	// 0  : Not visible outside normal vision range. Must be discovered.
	// 1  : Can be seen from nearly anywhere within this hex. (Huge tower, impact crater)
	// 2  : Can be seen from adjacent hexes (very tall mountain, flying castle.).
	// etc..
	private int visibility;
	
	
	/// Getters and Setters
	public FeatureType getType() {
		return type;
	}

	public void setType(FeatureType type) {
		this.type = type;
	}

	public boolean isObvious() {
		return visibility > 0;
	}

	public boolean isVisited() {
		return visited;
	}

	public void setVisited(boolean visited) {
		this.visited = visited;
	}

	public int getVisibility() {
		return visibility;
	}

	public void setVisibility(int visibility) {
		this.visibility = visibility;
	}
	
}