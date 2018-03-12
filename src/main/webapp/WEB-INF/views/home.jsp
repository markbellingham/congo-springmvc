<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<html>
<head>
	<title>Home | Congo</title>
	<spring:url value="/resources/css/home.css" var="mainCSS"/>
	<link href="${mainCSS}" rel="stylesheet" />
</head>
	<t:genericpage>
		<jsp:attribute name="header">
	   	</jsp:attribute>
		<jsp:attribute name="footer">
	   	</jsp:attribute>
		<jsp:body>
		    <br/><br/>
			<center><p><a href="<spring:url value="/albums/"/>">See a list of all our albums!</a></p></center>		
		</jsp:body>
	</t:genericpage>
</html>