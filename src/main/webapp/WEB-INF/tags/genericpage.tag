<%@tag description="Overall Page template" pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<%@attribute name="header" fragment="true" %>
<%@attribute name="footer" fragment="true" %>
<head>
    <script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/sorttable.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/congo.js"></script>
    <link href="${pageContext.request.contextPath}/resources/css/congo.css" rel="stylesheet" />
</head>
<div id="wrapper">
		<c:choose>
			<c:when test="${customer.loggedIn == true}">
				Welcome ${customer.fname} ${customer.lname}. <a href="<spring:url value="/customer/logout"/>">Log out</a>
			</c:when>
			<c:otherwise>
				Please log in to add items to the basket.
			</c:otherwise>
		</c:choose>
	<div id="pageheader">
	   	<h1 id="name">Congo's Music Store</h1>
	   	<img id="logo" src="${pageContext.request.contextPath}/resources/images/congo_logo.png"/>
	   	<nav>
			<a href="<spring:url value="/"/>">Home</a> | 
			<a href="<spring:url value="/categories/"/>">Categories</a> | 
			<a href="<spring:url value="/price-picker/"/>">Price Picker</a> | 
			<a href="<spring:url value="/artist-finder/"/>">Artist Finder</a> | 
			<a href="<spring:url value="/order/basket/"/>">Show Order</a> | 
			<a href="<spring:url value="/customer/show-all-my-orders/"/>">Show All My Orders</a> | 
			<a href="<spring:url value="/customer/login/"/>">Log in/Register</a>
		</nav>
	   	<jsp:invoke fragment="header"/>
	</div> <!-- ends pageheader -->
	<div id="body">
	   	<jsp:doBody/>
	</div> <!-- ends body -->
	<div id="pagefooter">
	   	<jsp:invoke fragment="footer"/>
	</div> <!-- ends pagefooter -->
</div> <!-- ends wrapper -->