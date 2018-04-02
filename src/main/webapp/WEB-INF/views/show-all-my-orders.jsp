<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html>
<head>
	<title>All My Orders | Congo</title>
</head>
	<t:genericpage>
		<jsp:attribute name="header">
	   	</jsp:attribute>
		<jsp:attribute name="footer">
	   	</jsp:attribute>
		<jsp:body>
		<br/><br/>
			<table class="musicList sortable">
				<tr>
					<th class="sorttable_nosort"></th><th>Artist</th><th>Album</th><th>Price</th><th>Quantity</th><th>Total</th><th>Order Date</th>
				</tr>
				<c:forEach items="${orders}" var="order">
					<tr>
						<td class="center"><a href="<spring:url value="${order.recordingId}"/>">
				    		<img src="${pageContext.request.contextPath}/resources/images/covers/sm/${order.recordingId}"/></a></td>
				    	<td class="left"><a href="<spring:url value="/artist/${order.artistName}"/>">${order.artistName}</a></td>
						<td class="left"><a href="<spring:url value="/albums/${order.recordingId}"/>">${order.title}</a></td>
						<td class="right">£${order.price}</td>
						<td class="center">${order.orderQuantity}</td>
						<td></td>
<%-- 						<td class="right">£${order.totalPrice}</td> --%>
						<td class="right">${order.orderDate}</td>
					</tr>
				</c:forEach>
			</table>
		</jsp:body>
	</t:genericpage>
</html>