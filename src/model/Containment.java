package model;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the containment database table.
 * 
 */
@Entity
@NamedQuery(name="Containment.findAll", query="SELECT c FROM Containment c")
public class Containment implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private ContainmentPK id;

	private int count;

	//bi-directional many-to-one association to Product
	@ManyToOne
	@JoinColumn(name="contains")
	private Product product;

	//bi-directional many-to-one association to Grouping
	@ManyToOne
	@JoinColumn(name="isContainedIn")
	private Grouping grouping;

	public Containment() {
	}

	public ContainmentPK getId() {
		return this.id;
	}

	public void setId(ContainmentPK id) {
		this.id = id;
	}

	public int getCount() {
		return this.count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	public Product getProduct() {
		return this.product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	public Grouping getGrouping() {
		return this.grouping;
	}

	public void setGrouping(Grouping grouping) {
		this.grouping = grouping;
	}

}