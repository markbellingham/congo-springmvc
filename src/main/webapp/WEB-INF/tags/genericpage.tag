<%@tag description="Overall Page template" pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<%@attribute name="header" fragment="true" %>
<%@attribute name="footer" fragment="true" %>
<head>
    <spring:url value="/resources/css/home.css" var="mainCSS"/>
    <link href="${mainCSS}" rel="stylesheet" />
</head>
<div id="pageheader">
   	<h1>Congo's Music Store</h1>
	<a href="<spring:url value="/"/>">Home</a> | 
	<a href="<spring:url value="/categories/"/>">Categories</a> | 
	<a href="<spring:url value="/price-picker/"/>">Price Picker</a> | 
	<a href="<spring:url value="/artist-finder/"/>">Artist Finder</a> | 
	<a href="<spring:url value="/show-order/"/>">Show Order</a> | 
	<a href="<spring:url value="/show-all-my-orders/"/>">Show All My Orders</a> | 
	<a href="<spring:url value="/login-register/"/>">Log in/Register</a>
   	<jsp:invoke fragment="header"/>
</div>
<div id="body">
   	<jsp:doBody/>
</div>
<div id="pagefooter">
   	<jsp:invoke fragment="footer"/>
</div>
