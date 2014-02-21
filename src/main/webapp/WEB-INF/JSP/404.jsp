<%@page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="UTF-8" session="false" trimDirectiveWhitespaces="true"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var='contextPath'
	value='${pageContext.servletContext.contextPath}' />
<!doctype html>
<html lang='en'>
<head>
<title>ToysForBoys - Page not found</title>
<link rel='stylesheet' href='${contextPath}/stylesheets/default.css' />
</head>
<body>
	<c:url value="index.htm" var="index" />
	<nav>
		<h1>
			<a href="${index}">ToysForBoys: unshipped orders</a>
		</h1>
	</nav>
	<header>
		<h1>Pagina niet gevonden</h1>
	</header>
	<section>
		<p class="fouten">De pagina die u zocht bestaat helaas niet op
			deze website.</p>
	</section>
</body>
</html>