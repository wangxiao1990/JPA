package model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;
import java.util.List;


/**
 * The persistent class for the invoice database table.
 * 
 */
@Entity
@NamedQuery(name="Invoice.findAll", query="SELECT i FROM Invoice i")
public class Invoice implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;

	@Temporal(TemporalType.DATE)
	private Date shippedOn;

	//bi-directional many-to-one association to Box
	@OneToMany(mappedBy="invoice")
	private List<Box> boxs;

	//bi-directional one-to-one association to Grouping
	@OneToOne
	@JoinColumn(name="id")
	private Grouping grouping;

	public Invoice() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Date getShippedOn() {
		return this.shippedOn;
	}

	public void setShippedOn(Date shippedOn) {
		this.shippedOn = shippedOn;
	}

	public List<Box> getBoxs() {
		return this.boxs;
	}

	public void setBoxs(List<Box> boxs) {
		this.boxs = boxs;
	}

	public Box addBox(Box box) {
		getBoxs().add(box);
		box.setInvoice(this);

		return box;
	}

	public Box removeBox(Box box) {
		getBoxs().remove(box);
		box.setInvoice(null);

		return box;
	}

	public Grouping getGrouping() {
		return this.grouping;
	}

	public void setGrouping(Grouping grouping) {
		this.grouping = grouping;
	}

}