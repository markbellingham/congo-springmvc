<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html>
<head>
	<title>Categories | Congo</title>
	<spring:url value="/resources/css/home.css" var="mainCSS"/>
	<link href="${mainCSS}" rel="stylesheet" />
</head>
	<t:genericpage>
		<jsp:attribute name="header">
	   	</jsp:attribute>
		<jsp:attribute name="footer">
	   	</jsp:attribute>
		<jsp:body>
		<br/>
		<br/>
		<form action="<spring:url value="/categories"/>" method="get">
			<select name="category" onchange="this.form.submit()">
				<option>Select Category</option>
				<c:forEach items="${categories}" var="category">
					<option>${category.name}</option>
				</c:forEach>
			</select>
		</form>
		<br />
		<br />
		<table>
			<tr>
				<th>Artist</th><th>Album</th><th>Category</th><th>Number of Tracks</th><th>Price</th><th></th>
			</tr>
			<c:forEach items="${albums}" var="album">
				<tr>
					<td><a href="<spring:url value="/artist/${album.artistName}"/>">${album.artistName}</a></td>
					<td><a href="<spring:url value="/albums/${album.recordingId}"/>">${album.title}</a></td>
					<td>${album.category}</td>
					<td>${album.num_tracks}</td>
					<td>${album.price}</td>
					<td><button>Add</button></td>
				</tr>
			</c:forEach>
		</table>
		</jsp:body>
	</t:genericpage>
</html>