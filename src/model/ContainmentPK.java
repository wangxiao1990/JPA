package model;

import java.io.Serializable;
import javax.persistence.*;

/**
 * The primary key class for the containment database table.
 * 
 */
@Embeddable
public class ContainmentPK implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	@Column(insertable=false, updatable=false)
	private int contains;

	@Column(insertable=false, updatable=false)
	private int isContainedIn;

	public ContainmentPK() {
	}
	public int getContains() {
		return this.contains;
	}
	public void setContains(int contains) {
		this.contains = contains;
	}
	public int getIsContainedIn() {
		return this.isContainedIn;
	}
	public void setIsContainedIn(int isContainedIn) {
		this.isContainedIn = isContainedIn;
	}

	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof ContainmentPK)) {
			return false;
		}
		ContainmentPK castOther = (ContainmentPK)other;
		return 
			(this.contains == castOther.contains)
			&& (this.isContainedIn == castOther.isContainedIn);
	}

	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + this.contains;
		hash = hash * prime + this.isContainedIn;
		
		return hash;
	}
}