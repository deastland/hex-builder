package org.dizzle.utilities.model;

/**
 * This will be the type of terrain in a party is traveling through on a given hex.
 * The number is a percent modifier of the base move rate.
 * 
 * For example: A party of humans (30' move) can travel 12 miles in a single watch. In FOREST(50) this is reduced to 50%
 * of this speed. Resulting in 6 miles traveled during the watch.
 * 
 * Always use only the worst modifier, unless on a HIGHWAY, which always gives full move rate.
 * 
 * @author Zeus
 *
 */
public enum TerrainType {

	// All terrain types assume "trackless" areas of the terrain. If traveling along a road, use HIGHWAY special venue.
	FOREST(50),
	GRASSLAND(75),
	MOUNTAIN(50),
	HILL(50),
	SWAMP(50),
	MOOR(25),				// Includes any terrain type covered in water/mud that must be walked through.
	WATER(100),				// Assumes water craft or swim speed
	SEA(100),				// Assumes water craft or swim speed
	RIVER(100),				// Assumes water craft or swim speed
	BADLAND(75),
	DESERT(50),
	JUNGLE(25),
	TUNDRA(75),
	VOLCANIC(25);			// Assumes enough safe surface to navigate past lava/steam vents/boiling pools/etc.
	
	private int speedModifier;
	
	public int getSpeedModifier() {
		return this.speedModifier;
	}
	
	private TerrainType(int speedModifier) {
		this.speedModifier = speedModifier;
	}
	
}
