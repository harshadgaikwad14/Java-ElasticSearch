/*
 * 
 */
package com.example.model;

// TODO: Auto-generated Javadoc
/**
 * The Class BuildingStructure.
 */
public class BuildingStructure {

	
	/** The id. */
	private String id = null;

	
	/** The state information. */
	private BuildingStatusInfo stateInformation = null;

	
	/** The structure name. */
	private String structureName = null;

	/**
	 * Gets the id.
	 *
	 * @return the id
	 */
	public String getId() {
		return id;
	}

	/**
	 * Sets the id.
	 *
	 * @param id the new id
	 */
	public void setId(String id) {
		this.id = id;
	}

	/**
	 * Gets the state information.
	 *
	 * @return the state information
	 */
	public BuildingStatusInfo getStateInformation() {
		return stateInformation;
	}

	/**
	 * Sets the state information.
	 *
	 * @param stateInformation the new state information
	 */
	public void setStateInformation(BuildingStatusInfo stateInformation) {
		this.stateInformation = stateInformation;
	}

	/**
	 * Gets the structure name.
	 *
	 * @return the structure name
	 */
	public String getStructureName() {
		return structureName;
	}

	/**
	 * Sets the structure name.
	 *
	 * @param structureName the new structure name
	 */
	public void setStructureName(String structureName) {
		this.structureName = structureName;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "SweepStructure [id=" + id + ", stateInformation=" + stateInformation + ", structureName="
				+ structureName + "]";
	}

}
