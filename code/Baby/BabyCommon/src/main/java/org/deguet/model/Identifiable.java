package org.deguet.model;

import java.io.Serializable;


public abstract class Identifiable implements Serializable{


	/**
	 * String representation for UUID makes it compatible with Hibernate but certianly not optimal
	 */
	private String id;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}
	
}
