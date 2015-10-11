package model;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the box database table.
 * 
 */
@Entity
@NamedQuery(name="Box.findAll", query="SELECT b FROM Box b")
public class Box implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;

	private double maximumWeight;

	private double volume;

	//bi-directional one-to-one association to Grouping
	@OneToOne
	@JoinColumn(name="id")
	private Grouping grouping;

	//bi-directional many-to-one association to Invoice
	@ManyToOne
	@JoinColumn(name="fulfills")
	private Invoice invoice;

	public Box() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public double getMaximumWeight() {
		return this.maximumWeight;
	}

	public void setMaximumWeight(double maximumWeight) {
		this.maximumWeight = maximumWeight;
	}

	public double getVolume() {
		return this.volume;
	}

	public void setVolume(double volume) {
		this.volume = volume;
	}

	public Grouping getGrouping() {
		return this.grouping;
	}

	public void setGrouping(Grouping grouping) {
		this.grouping = grouping;
	}

	public Invoice getInvoice() {
		return this.invoice;
	}

	public void setInvoice(Invoice invoice) {
		this.invoice = invoice;
	}

}