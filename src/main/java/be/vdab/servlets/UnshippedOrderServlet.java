package be.vdab.servlets;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import be.vdab.entities.Order;
import be.vdab.services.OrderService;

@WebServlet("/index.htm")
public class UnshippedOrderServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final String VIEW = "/WEB-INF/JSP/index.jsp";
	private static final int AANTAL_RIJEN = 20;
	private final OrderService orderService = new OrderService();

	@Override
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		try {
			int vanafRij = request.getParameter("vanafRij") == null ? 0
					: Integer.parseInt(request.getParameter("vanafRij"));
			request.setAttribute("vanafRij", vanafRij);
			request.setAttribute("aantalRijen", AANTAL_RIJEN);
			Iterable<Order> orders = orderService.findAllUnshippedOrders(
					vanafRij, AANTAL_RIJEN + 1);
			if (!orders.iterator().hasNext()) {
			} else {
				List<Order> ordersList = (List<Order>) orders;
				if (ordersList.size() <= AANTAL_RIJEN) {
					request.setAttribute("laatstePagina", true);
				} else {
					ordersList.remove(AANTAL_RIJEN);
				}
				request.setAttribute("unshippedOrders", ordersList);
			}
			request.getRequestDispatcher(VIEW).forward(request, response);
		} catch (Exception ex) {
			response.sendRedirect(response.encodeRedirectURL(request
					.getContextPath()));
		}
	}

	@Override
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		try {
			for (String orderIDs : request.getParameterValues("orderID")) {
				Long orderID = Long.parseLong(orderIDs);
				orderService.setAsShipped(orderID);
			}
		} catch (Exception ex) {
		}
		response.sendRedirect(response.encodeRedirectURL(request
				.getContextPath()));
	}
}