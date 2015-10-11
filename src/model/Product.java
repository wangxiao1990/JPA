package model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the product database table.
 * 
 */
@Entity
@NamedQuery(name="Product.findAll", query="SELECT p FROM Product p")
public class Product implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;

	private double volume;

	private double weight;

	//bi-directional many-to-one association to Containment
	@OneToMany(mappedBy="product")
	private List<Containment> containments;

	public Product() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public double getVolume() {
		return this.volume;
	}

	public void setVolume(double volume) {
		this.volume = volume;
	}

	public double getWeight() {
		return this.weight;
	}

	public void setWeight(double weight) {
		this.weight = weight;
	}

	public List<Containment> getContainments() {
		return this.containments;
	}

	public void setContainments(List<Containment> containments) {
		this.containments = containments;
	}

	public Containment addContainment(Containment containment) {
		getContainments().add(containment);
		containment.setProduct(this);

		return containment;
	}

	public Containment removeContainment(Containment containment) {
		getContainments().remove(containment);
		containment.setProduct(null);

		return containment;
	}

}