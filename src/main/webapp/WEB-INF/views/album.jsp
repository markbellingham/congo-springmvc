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
				<center><a href="<spring:url value="/albums/"/>">See a list of all our albums!</a></center>
			</p>
			<br />
			<br />
			<div id="album-container">
				<h2>${album.title} by ${album.artistName}</h2>
				<div id="album-cover">
				   <img src="${pageContext.request.contextPath}/resources/images/covers/${album.imageName}"/>
				</div> <!-- ends album-cover -->
				<div id="album-tracks">
					<table class="musicList sortable" id="trackLister">
						<tr>
						    <th>Track #</th>
							<th>Title</th>
							<th>Duration</th>
						</tr>
						<c:forEach items="${tracks}" var="track">
							<tr>
							   <td>${track.trackNumber}</td>
							   <td>${track.title}</td>
							   <td class="right">${track.strDuration}</td>
							</tr>
						</c:forEach>
						<tr>
							<td class="right" colspan="3" style="border: none">
								<form action="<spring:url value="/order/add-to-order/${album.recordingId}"/>" method="post">
									<c:choose>
								   		<c:when test="${(customer.loggedIn != null) && (customer.loggedIn == true) && (album.stockCount > '0')}">
									    	<button>Add to basket</button>
										</c:when>
										<c:otherwise>
									    	<button disabled>Add to basket</button>
										</c:otherwise>
									</c:choose>
								</form>
							</td>
						</tr>
					</table>
				</div> <!-- ends album-tracks -->
			</div> <!-- ends album-container -->
		</jsp:body>
	</t:genericpage>
</html>