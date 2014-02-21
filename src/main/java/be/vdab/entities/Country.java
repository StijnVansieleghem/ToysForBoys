package be.vdab.entities;

import java.io.Serializable;
import java.util.Collections;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "countries")
public class Country implements Serializable {
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue
	private long countryID;
	private String name;
	@OneToMany(mappedBy = "country")
	private Set<Customer> customers;

	@Override
	public boolean equals(Object obj) {
		if (!(obj instanceof Country)) {
			return false;
		}
		return ((Country) obj).name.equalsIgnoreCase(this.name);
	}

	@Override
	public int hashCode() {
		return name.toUpperCase().hashCode();
	}
	
	public void addCustomer(Customer customer){
		customers.add(customer);
		if(customer.getCountry() == this){
			customer.setCountry(this);
		}
	}
	
	public void removeCustomer(Customer customer){
		customers.remove(customer);
		if(customer.getCountry() == this){
			customer.setCountry(null);
		}
	}
	
	public Country() {
	}

	public Country(long countryID, String name, Set<Customer> customers) {
		this.name = name;
	}

	public long getCountryID() {
		return countryID;
	}

	public void setCountryID(long countryID) {
		this.countryID = countryID;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public Set<Customer> getCustomer(){
		return Collections.unmodifiableSet(customers);
	}
}