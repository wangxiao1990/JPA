package model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the grouping database table.
 * 
 */
@Entity
@NamedQuery(name="Grouping.findAll", query="SELECT g FROM Grouping g")
public class Grouping implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;

	//bi-directional one-to-one association to Box
	@OneToOne(mappedBy="grouping")
	private Box box;

	//bi-directional many-to-one association to Containment
	@OneToMany(mappedBy="grouping")
	private List<Containment> containments;

	//bi-directional one-to-one association to Invoice
	@OneToOne(mappedBy="grouping")
	private Invoice invoice;

	public Grouping() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Box getBox() {
		return this.box;
	}

	public void setBox(Box box) {
		this.box = box;
	}

	public List<Containment> getContainments() {
		return this.containments;
	}

	public void setContainments(List<Containment> containments) {
		this.containments = containments;
	}

	public Containment addContainment(Containment containment) {
		getContainments().add(containment);
		containment.setGrouping(this);

		return containment;
	}

	public Containment removeContainment(Containment containment) {
		getContainments().remove(containment);
		containment.setGrouping(null);

		return containment;
	}

	public Invoice getInvoice() {
		return this.invoice;
	}

	public void setInvoice(Invoice invoice) {
		this.invoice = invoice;
	}

}