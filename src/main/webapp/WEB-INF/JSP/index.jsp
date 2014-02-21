<%@ page contentType='text/html' pageEncoding='UTF-8' session='false' trimDirectiveWhitespaces="true" %>
<%@taglib prefix='c' uri='http://java.sun.com/jsp/jstl/core'%>
<%@taglib prefix='fmt' uri='http://java.sun.com/jsp/jstl/fmt'%>
<c:set var='contextPath'
	value='${pageContext.servletContext.contextPath}' />
<c:url value="index.htm" var="unshippedOrdersURL" />
<!doctype html>
<html lang='en'>
<head>
<title>ToysForBoys - Unshipped orders</title>
<link rel='stylesheet' href='${contextPath}/stylesheets/default.css' />
</head>
<body>
	<header>
		<h1>Unshipped orders</h1>
	</header>
	<section>
		<form action="${unshippedOrdersURL}" method="post">
			<table class="zebra">
				<tr>
					<th>ID</th>
					<th>Ordered</th>
					<th>Required</th>
					<th>Customer</th>
					<th>Comments</th>
					<th>Status</th>
					<th>Ship</th>
				</tr>
				<c:forEach var="order" items="${unshippedOrders}" varStatus="status">
					<tr>
						<c:url value='/orderdetail.htm' var='orderDetail'>
							<c:param name='orderDetail' value='${order.orderID}' />
						</c:url>
						<td class="align_right"><a href="${orderDetail}"
							title="Order ID: ${order.orderID}">${order.orderID}</a></td>
						<td><fmt:formatDate dateStyle="short" type="date"
								value="${order.orderDate}" /></td>
						<td><fmt:formatDate dateStyle="short" type="date"
								value="${order.requiredDate}" /></td>
						<td>${order.customer.name}</td>
						<td>${order.comments}</td>
						<td class="lowercase">${order.status}</td>
						<td><input name="orderID" type="checkbox"
							value="${order.orderID}" /></td>
					</tr>
				</c:forEach>
			</table>
			<p class="pagination">
				<c:if test='${laatstePagina}'>
					<a href="index.htm" title='Eerste pagina'>&lt;&lt;</a>
				</c:if>
				<c:if test='${vanafRij != 0}'>
					<c:url value='index.htm' var='vorigePaginaURL'>
						<c:param name='vanafRij' value='${vanafRij - aantalRijen}' />
					</c:url>
					<a href="<c:out value='${vorigePaginaURL}'/>" title='vorige pagina'>&nbsp;&lt;</a>
				</c:if>
				<c:if test='${empty laatstePagina}'>
					<c:url value='index.htm' var='volgendePaginaURL'>
						<c:param name='vanafRij' value='${vanafRij + aantalRijen}' />
					</c:url>
					<a href="<c:out value='${volgendePaginaURL}'/>"
						title='volgende pagina'>&gt;&nbsp;</a>
				</c:if>
			</p>
			<input id="setShipped" name="setShipped" type="submit"
				value="Set as shipped" />
		</form>
		<c:import url='/WEB-INF/JSP/fouten.jsp' />
	</section>
</body>
</html>