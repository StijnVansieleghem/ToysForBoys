package be.vdab.entities;

import java.io.Serializable;
import java.util.Collections;
import java.util.LinkedHashSet;
import java.util.Set;

import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import be.vdab.valueobjects.Adres;

@Entity
@Table(name = "customers")
public class Customer implements Serializable {
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue
	private long customerID;
	private String name;
	@Embedded
	private Adres adres;
	@OneToMany(mappedBy = "customer")
	private Set<Order> orders;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "countryID")
	private Country country;

	public Country getCountry() {
		return country;
	}

	public void setCountry(Country country) {
		if (this.country != null && this.country.getCustomer().contains(this)) {
			this.country.removeCustomer(this);
		}
		this.country = country;
		if (country != null && !country.getCustomer().contains(this)) {
			country.addCustomer(this);
		}
	}

	public Set<Order> getOrders() {
		return Collections.unmodifiableSet(orders);
	}

	public void addOrder(Order order) {
		orders.add(order);
		if (order.getCustomer() != this) {
			order.setCustomer(this);
		}
	}

	public void removeOrder(Order order) {
		orders.remove(order);
		if (order.getCustomer() == this) {
			order.setCustomer(null);
		}
	}

	public Customer() {
	}

	public Customer(String name, String streetAndNumber, String city,
			String state, String postalCode) {
		super();
		setName(name);
		setAdres(streetAndNumber, city, state, postalCode);
		orders = new LinkedHashSet<>();
	}

	public long getCustomerID() {
		return customerID;
	}

	public void setCustomerID(long customerID) {
		this.customerID = customerID;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setAdres(String streetAndNumber, String city, String state,
			String postalCode) {
		this.adres = new Adres(streetAndNumber, city, state, postalCode);
	}
	
	public Adres getAdres(){
		return this.adres;
	}
}
