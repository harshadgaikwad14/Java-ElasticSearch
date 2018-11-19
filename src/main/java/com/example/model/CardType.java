/*
 * 
 */
package com.example.model;

// TODO: Auto-generated Javadoc
/**
 * The Class CardType.
 */
public class CardType {

	/** The code. */
	private String code = null;

	/** The description. */
	private String description = null;

	/**
	 * Gets the code.
	 *
	 * @return the code
	 */
	public String getCode() {
		return code;
	}

	/**
	 * Sets the code.
	 *
	 * @param code the new code
	 */
	public void setCode(String code) {
		this.code = code;
	}

	/**
	 * Gets the description.
	 *
	 * @return the description
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * Sets the description.
	 *
	 * @param description the new description
	 */
	public void setDescription(String description) {
		this.description = description;
	}
	

	/**
	 * Instantiates a new card type.
	 *
	 * @param code the code
	 * @param description the description
	 */
	public CardType(String code, String description) {
		super();
		this.code = code;
		this.description = description;
	}

	/**
	 * Instantiates a new card type.
	 */
	public CardType() {
		super();
		// TODO Auto-generated constructor stub
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "CardType [code=" + code + ", description=" + description + "]";
	}

}
