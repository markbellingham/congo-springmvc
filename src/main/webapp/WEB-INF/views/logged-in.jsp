<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<html>
<head>
	<title>Welcome | Congo</title>
</head>
	<t:genericpage>
		<jsp:attribute name="header">
	   	</jsp:attribute>
		<jsp:attribute name="footer">
	   	</jsp:attribute>
		<jsp:body>
			<div>${msg}</div>
		</jsp:body>
	</t:genericpage>
</html>