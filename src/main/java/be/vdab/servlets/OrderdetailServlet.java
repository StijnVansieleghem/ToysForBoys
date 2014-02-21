package be.vdab.servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import be.vdab.entities.Order;
import be.vdab.services.OrderService;

@WebServlet("/orderdetail.htm")
public class OrderdetailServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final String VIEW = "/WEB-INF/JSP/orderdetail.jsp";
	private final OrderService orderService = new OrderService();

	@Override
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		if (request.getParameter("orderDetail") != null) {
			try {
				long orderID = Long.parseLong(request
						.getParameter("orderDetail"));
				Order order = orderService.read(orderID);
				request.setAttribute("order", order);
			} catch (NumberFormatException e) {
			}
		} else {
		}
		request.getRequestDispatcher(VIEW).forward(request, response);
	}
}