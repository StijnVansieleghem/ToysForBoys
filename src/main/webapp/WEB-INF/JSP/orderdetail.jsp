<%@ page contentType='text/html' pageEncoding='UTF-8' session='false'
	trimDirectiveWhitespaces="true"%>
<%@taglib prefix='c' uri='http://java.sun.com/jsp/jstl/core'%>
<%@taglib prefix='fmt' uri='http://java.sun.com/jsp/jstl/fmt'%>
<c:set var='contextPath'
	value='${pageContext.servletContext.contextPath}' />
<c:url value="orderdetail.htm" var="orderDetailURL" />
<!doctype html>
<html lang='en'>
<head>
<title>ToysForBoys - Orderdetail</title>
<link rel='stylesheet' href='${contextPath}/stylesheets/default.css' />
</head>
<body>
	<c:if test="${not empty order}">
		<nav>
			<a href="${contextPath}/index.htm">Unshipped orders</a>
		</nav>
		<header>
			<h1>Order ${order.orderID}</h1>
		</header>
		<section>
			<p>
				Ordered: <br /> <span class="bold"><fmt:formatDate
						dateStyle="short" type="date" value="${order.orderDate}" /></span>
			</p>
			<p>
				Required: <br /> <span class="bold"> <fmt:formatDate
						dateStyle="short" type="date" value="${order.requiredDate}" /></span>
			</p>
			<p>
				Customer: <span class="bold"><br />${order.customer.name}<br />${order.customer.adres.streetAndNumber}<br />${order.customer.adres.postalCode}&nbsp;${order.customer.adres.state}<br />${order.customer.country.name}</span>
			</p>
			<p>
				Details: <br />
			</p>
			<table>
				<tr>
					<th>Product</th>
					<th>Price each</th>
					<th>Quantity</th>
					<th>Value</th>
				</tr>
				<c:forEach var="orderDetail" items="${order.orderdetails}">
					<tr class="bold">
						<td>${orderDetail.product.name}</td>
						<td class="align_right"><fmt:formatNumber
								value='${orderDetail.priceEach}' minFractionDigits='2' /></td>
						<td class="align_right"><fmt:formatNumber value=''
								minFractionDigits='2' />${orderDetail.quantityOrdered}</td>
						<td><fmt:formatNumber value='' />${orderDetail.value}</td>
					</tr>
				</c:forEach>
			</table>
			<p>
				Value: <span class="bold"><br /> <fmt:formatNumber
						value='${order.value}' minFractionDigits='2' /></span>

			</p>
			<c:import url='/WEB-INF/JSP/fouten.jsp' />
		</section>
	</c:if>
</body>
</html>