<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
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
	<br />
	<br />
	<table>
		<tr>
			<th>Recording ID</th><th>Artist</th><th>Album</th><th>Category</th><th>Number of Tracks</th><th>Price</th><th></th>
		</tr>
		<c:forEach items="${albums}" var="album">
			<tr>
				<td>${album.recordingId}</td>
				<td>${album.artistName}</td>
				<td>${album.title}</td>
				<td>${album.category}</td>
				<td>${album.num_tracks}</td>
				<td>${album.price}</td>
				<td><button>Add</button></td>
			</tr>
		</c:forEach>
	</table>
	
</body>
</html>