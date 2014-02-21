package be.vdab.entities;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Collections;
import java.util.Date;
import java.util.LinkedHashSet;
import java.util.Set;

import javax.persistence.CollectionTable;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import be.vdab.valueobjects.Orderdetail;

@Entity
@Table(name = "orders")
public class Order implements Serializable {
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue
	private long orderID;
	@Temporal(TemporalType.DATE)
	private Date orderDate;
	@Temporal(TemporalType.DATE)
	private Date requiredDate;
	@Temporal(TemporalType.DATE)
	private Date shippedDate;
	private String comments;
	private String status;
	@ElementCollection
	@CollectionTable(name = "orderdetails", joinColumns = @JoinColumn(name = "orderID"))
	private Set<Orderdetail> orderdetails;

	public Set<Orderdetail> getOrderdetails() {
		return Collections.unmodifiableSet(orderdetails);
	}

	public void addOrderdetail(Orderdetail orderdetail) {
		orderdetails.add(orderdetail);
	}

	public void removeOrderdetail(Orderdetail orderdetail) {
		orderdetails.remove(orderdetail);
	}

	@ManyToOne
	@JoinColumn(name = "customerID")
	private Customer customer;

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		if (this.customer != null && this.customer.getOrders().contains(this)) {
			this.customer.removeOrder(this);
		}
		this.customer = customer;
		if (customer != null && !customer.getOrders().contains(this)) {
			customer.addOrder(this);
		}
	}

	protected Order() {

	}

	public Order(Date orderDate, Date requiredDate, Date shippedDate,
			String comments, String status) {
		setOrderDate(orderDate);
		setRequiredDate(requiredDate);
		setShippedDate(shippedDate);
		setComments(comments);
		orderdetails = new LinkedHashSet<Orderdetail>();
	}

	public long getOrderID() {
		return orderID;
	}

	public Date getOrderDate() {
		Date clonedDate = orderDate;
		return clonedDate;
	}

	public void setOrderDate(Date orderDate) {
		this.orderDate = (Date) orderDate.clone();
	}

	public Date getRequiredDate() {
		Date clonedDate = requiredDate;
		return clonedDate;
	}

	public void setRequiredDate(Date requiredDate) {
		this.requiredDate = (Date) requiredDate.clone();
	}

	public Date getShippedDate() {
		Date clonedDate = shippedDate;
		return clonedDate;
	}

	public void setShippedDate(Date shippedDate) {
		this.shippedDate = (Date) shippedDate.clone();
	}

	public String getComments() {
		return comments;
	}

	public void setComments(String comments) {
		this.comments = comments;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public BigDecimal getValue() {
		BigDecimal total = BigDecimal.ZERO;
		for (Orderdetail orderdetail : this.getOrderdetails()) {
			total = total.add(orderdetail.getValue());
		}
		return total;
	}

	@Override
	public boolean equals(Object obj) {
		if (!(obj instanceof Order)) {
			return false;
		}
		return ((Order) obj).orderID == this.orderID;
	}

	@Override
	public int hashCode() {
		return Long.toString(orderID).hashCode();
	}
}
