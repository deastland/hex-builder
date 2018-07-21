package org.dizzle.utilities.model;

/**
 * A Feature is a location in a hex that can be discovered.
 * This can be either a landmark, a lair, or a point of interest that can be discovered in a hex.
 * 
 * @author deastland
 *
 */
public class EncounterLocation extends Encounter {

	private FeatureType type;					// What kind of feature is it? (landmark, dungeon, etc.)
	private boolean visited = false;			// Has the party been here before?
	private FeatureLocation featureLocation;	// Is the feature along a road or river, or just out in the wilderness?
	private int visibility;						// From how many hexes away is the feature visible?

	// The visibility number is the number of hexes from which a feature is visible.
	// -1 : Hidden. Difficult to discover.
	// 0  : Not visible outside normal vision range. Must be discovered.
	// 1  : Can be seen from nearly anywhere within this hex. (Huge tower, impact crater)
	// 2  : Can be seen from adjacent hexes (very tall mountain, floating castle.).
	// etc..
	
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
	
	public FeatureLocation getFeatureLocation() {
		return featureLocation;
	}

	public void setFeatureLocation(FeatureLocation featureLocation) {
		this.featureLocation = featureLocation;
	}
	
	@Override
	public String toString() {
		StringBuffer retStr = new StringBuffer("");
		
		retStr.append("Location Name: " + getName()).append("\n");
		retStr.append("Location Type: " + getType()).append("\n");
		retStr.append("Feature Location: " + getFeatureLocation()).append("\n");
		retStr.append("Feature Visibility: " + getVisibility()).append("\n");
		retStr.append("Feature Visited: " + isVisited()).append("\n");

		return retStr.toString();
	}

}
