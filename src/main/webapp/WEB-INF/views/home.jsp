<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<link rel="stylesheet" href="<spring:url value="/resources/css/home.css"/>" type="text/css"/>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Congo's Music Store</title>
</head>
<body>

	<jsp:include page="layout/header.jsp"></jsp:include>

	<p><a href="<spring:url value="/albums/"/>">See a list of all our albums!</a></p> 
	
</body>
</html>