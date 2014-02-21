package be.vdab.dao;

import javax.persistence.LockModeType;
import javax.persistence.TypedQuery;

import be.vdab.entities.Order;
import be.vdab.entities.Product;
import be.vdab.valueobjects.Orderdetail;

public class OrderDAO extends AbstractDAO {
	public Order read(long orderID) {
		return getEntityManager().find(Order.class, orderID);
	}

	public Order readWithLock(long orderID) {
		return getEntityManager().find(Order.class, orderID,
				LockModeType.PESSIMISTIC_WRITE);
	}

	public Iterable<Order> findAllUnshippedOrders(int vanafRij, int aantalRijen) {
		TypedQuery<Order> query = getEntityManager().createNamedQuery(
				"Order.findAllUnshippedOrders", Order.class);
		query.setFirstResult(vanafRij);
		query.setMaxResults(aantalRijen);
		return query.getResultList();
	}

	public void setAsShipped(Long orderID) {
		Order order = readWithLock(orderID);
		order.setStatus("SHIPPED");
		for (Orderdetail orderdetail : order.getOrderdetails()) {
			int quantity = orderdetail.getQuantityOrdered();
			Product product = orderdetail.getProduct();
			getEntityManager().lock(product, LockModeType.PESSIMISTIC_WRITE);
			product.setQuantityInStock(product.getQuantityInStock() - quantity);
			product.setQuantityInOrder(product.getQuantityInOrder() - quantity);
		}
	}

}