package be.vdab.valueobjects;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.Embeddable;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import be.vdab.entities.Product;

@Embeddable
public class Orderdetail implements Serializable {
	private static final long serialVersionUID = 1L;
	private int quantityOrdered;
	private BigDecimal priceEach;
	@ManyToOne()
	@JoinColumn(name = "productID")
	private Product product;
	
	protected Orderdetail() {
	}

	public Orderdetail(Product product, int quantityOrdered,
			BigDecimal priceEach) {
		super();
		this.product = product;
		this.quantityOrdered = quantityOrdered;
		this.priceEach = priceEach;
	}

	public Product getProduct() {
		return product;
	}

	public int getQuantityOrdered() {
		return quantityOrdered;
	}

	public BigDecimal getPriceEach() {
		return priceEach;
	}

	public void setProduct(Product product) {
			this.product = product;
	}
	
	public BigDecimal getValue() {
		return priceEach.multiply(new BigDecimal(quantityOrdered));
	}

}
