package be.vdab.entities;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "products")
public class Product implements Serializable {
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue
	private long productID;
	private String name;
	private String scale;
	private String description;
	private int quantityInStock;
	private int quantityInOrder;
	private BigDecimal buyPrice;
	
	protected Product() {
	}

	public Product(String name, String scale, String description, int quantity,
			int quantityInOrder, BigDecimal buyPrice) {
		setName(name);
		setScale(scale);
		setDescription(description);
		setQuantityInStock(quantity);
		setQuantityInOrder(quantityInOrder);
		setBuyPrice(buyPrice);
	}
	
	@Override
	public boolean equals(Object obj) {
		if (!(obj instanceof Product)) {
			return false;
		}
		return ((Product) obj).name.equalsIgnoreCase(this.name);
	}

	@Override
	public int hashCode() {
		return name.toUpperCase().hashCode();
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getScale() {
		return scale;
	}

	public void setScale(String scale) {
		this.scale = scale;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public int getQuantityInStock() {
		return quantityInStock;
	}

	public void setQuantityInStock(int quantity) {
		this.quantityInStock = quantity;
	}

	public int getQuantityInOrder() {
		return quantityInOrder;
	}

	public void setQuantityInOrder(int quantityInOrder) {
		this.quantityInOrder = quantityInOrder;
	}

	public BigDecimal getBuyPrice() {
		return buyPrice;
	}

	public void setBuyPrice(BigDecimal buyPrice) {
		this.buyPrice = buyPrice;
	}

	public long getProductID() {
		return productID;
	}
}
