<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html>
<head>
	<title>Current Order | Congo</title>
</head>
	<t:genericpage>
		<jsp:attribute name="header">
	   	</jsp:attribute>
		<jsp:attribute name="footer">
	   	</jsp:attribute>
		<jsp:body>
		<br />
		<h2>Shopping Basket</h2>
		<br />
		<table class="musicList sortable">
			<tr>
				<th class="sorttable_nosort"></th><th>Artist</th><th>Album</th><th>Category</th><th># of Tracks</th><th>Price</th><th>Quantity</th><th>Total</th><th class="sorttable_nosort"></th><th class="sorttable_nosort"></th>
			</tr>
			<c:forEach items="${order}" var="album">
			<tr>
				<td class="center"><a href="<spring:url value="${album.recordingId}"/>">
				    <img src="${pageContext.request.contextPath}/resources/images/covers/sm/${album.imageName}"/></a></td>
				<td class="left"><a href="<spring:url value="${album.artistName}"/>">${album.artistName}</a></td>
				<td class="left"><a href="<spring:url value="${album.recordingId}"/>">${album.title}</a></td>
				<td class="left"><a href="<spring:url value="${album.category}"/>">${album.category}</a></td>
				<td class="center">${album.num_tracks}</td>
				<td class="right">£${album.price}</td>
				<td class="center"><input type="number" value="${album.quantity}" min="1" max="99" style="width: 3.5em;"></td>
				<td class="right">£</td>
				<td class="center"><input type="submit" value="Update"></td>
				<td class="center"><input type="submit" value="Delete"></td>
			</tr>
			</c:forEach>
		</table>
		</jsp:body>
	</t:genericpage>
</html>