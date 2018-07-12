package org.dizzle.utilities.model;

/**
 * On any given watch, a party will be in one of these modes.
 * This will effect what, if any, encounters may be had in a hex.
 * 
 * It may have other effects such as odds of getting lost or exhaustion accumulation.
 * 
 * @author deastland
 *
 */
public enum TravelMode {

	SLOW,
	STANDARD,
	FAST,
	SEARCH,
	STATIONARY;
}
