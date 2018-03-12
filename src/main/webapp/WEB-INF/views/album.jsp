<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<head>
    <title>${album.title} by ${album.artistName} | Congo</title>
</head>
<html>
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
			<h2>${album.title} by ${album.artistName}</h2>
			<table class="musicList sortable">
				<tr>	
					<th>Title</th>
					<th>Duration</th>
				</tr>
				<c:forEach items="${tracks}" var="track">
					<tr>
						<td>${track.title}</td>
						<td>${track.strDuration}</td>
					</tr>
				</c:forEach>
			</table>
		</jsp:body>
	</t:genericpage>
</html>