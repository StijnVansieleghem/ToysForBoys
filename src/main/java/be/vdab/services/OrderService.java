package be.vdab.services;

import be.vdab.dao.OrderDAO;
import be.vdab.entities.Order;

public class OrderService {
	private final OrderDAO orderDAO = new OrderDAO();

	public Order read(long orderID) {
		return orderDAO.read(orderID);
	}

	public Iterable<Order> findAllUnshippedOrders(int vanafRij, int aantalRijen) {
		return orderDAO.findAllUnshippedOrders(vanafRij, aantalRijen);
	}

	public void setAsShipped(Long orderID) {
		orderDAO.beginTransaction();
		orderDAO.setAsShipped(orderID);
		orderDAO.commit();
	}

}
