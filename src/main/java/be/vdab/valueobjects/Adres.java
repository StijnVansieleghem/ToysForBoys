package be.vdab.valueobjects;

import java.io.Serializable;

import javax.persistence.Embeddable;

@Embeddable
public class Adres implements Serializable {
	public static final long serialVersionUID = 1L;
	private String streetAndNumber;
	private String city;
	private String state;
	private String postalCode;

	protected Adres() {
	}

	public Adres(String streetAndNumber, String city, String state,
			String postalcode) {
		setStreetAndNumber(streetAndNumber);
		setCity(city);
		setState(state);
		setPostalCode(postalCode);
	}

	public String getStreetAndNumber() {
		return streetAndNumber;
	}

	public void setStreetAndNumber(String streetAndNumber) {
		this.streetAndNumber = streetAndNumber;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getPostalCode() {
		return postalCode;
	}

	public void setPostalCode(String postalCode) {
		this.postalCode = postalCode;
	}
}
