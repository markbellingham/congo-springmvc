<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html>
<head>
	<title>Price Picker | Congo</title>
</head>
	<t:genericpage>
		<jsp:attribute name="header">
	   	</jsp:attribute>
		<jsp:attribute name="footer">
	   	</jsp:attribute>
		<jsp:body>
		<br/>
		<br/>
		<div class="selector">
		<form action="<spring:url value="/price-picker"/>" method="get">
			<select name="price" onchange="this.form.submit()">
				<option>Select Price</option>
				<option value="10">Under £10</option>
				<option value="12">£10 < £12</option>
				<option value="14">£12 < £14</option>
				<option value="16">$14 < £16</option>				
			</select>
		</form>
		</div> <!-- ends selector -->
		<br />
		<br />
		<table class="musicList sortable">
			<tr>
				<th class="sorttable_nosort"></th><th>Artist</th><th>Album</th><th>Category</th><th># of Tracks</th><th>Price</th><th class="sorttable_nosort"></th>
			</tr>
			<c:forEach items="${albums}" var="album">
				<tr>
				    <td class="center"><a href="<spring:url value="/albums/${album.recordingId}"/>">
                           <img src="${pageContext.request.contextPath}/resources/images/covers/sm/${album.imageName}"/></a></td>
					<td class="left"><a href="<spring:url value="/artist/${album.artistName}"/>">${album.artistName}</a></td>
					<td class="left"><a href="<spring:url value="/albums/${album.recordingId}"/>">${album.title}</a></td>
					<td class="left"><a href="<spring:url value="/categories/${album.category}"/>">${album.category}</a></td>
					<td class="center">${album.num_tracks}</td>
					<td class="right">£${album.price}</td>
					<td class="center">
						<form action="<spring:url value="/order/add-to-order/${album.recordingId}"/>" method="post">
							<c:choose>
						   		<c:when test="${(customer.loggedIn != null) && (customer.loggedIn == true) && (album.stockCount > '0')}">
							    	<button>Add</button>
								</c:when>
								<c:otherwise>
							    	<button disabled>Add</button>
								</c:otherwise>
							</c:choose>
						</form>
					</td>
				</tr>
			</c:forEach>
		</table>
		</jsp:body>
	</t:genericpage>
</html>