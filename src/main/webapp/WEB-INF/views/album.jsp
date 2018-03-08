<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<html>
<head>
	<title>Congo</title>
	<spring:url value="/resources/css/home.css" var="mainCSS"/>
	<link href="${mainCSS}" rel="stylesheet" />
</head>
	<t:genericpage>
		<jsp:attribute name="header">
	   	</jsp:attribute>
		<jsp:attribute name="footer">
	   	</jsp:attribute>
		<jsp:body>
			<p>
				<a href="<spring:url value="/albums/"/>">See a list of all our albums!</a>
			</p>
			<br />
			<br />
			${album.title} by ${album.artistName}
			<br/>
			<br/>
			<table>
				<tr>	
					<th>Title</th>
					<th>Duration</th>
				</tr>
				<c:forEach items="${tracks}" var="tracks">
					<tr>
						<td>${tracks.title}</td>
						<td>${tracks.duration}</td>
					</tr>
				</c:forEach>
			</table>
		</jsp:body>
	</t:genericpage>
</html>