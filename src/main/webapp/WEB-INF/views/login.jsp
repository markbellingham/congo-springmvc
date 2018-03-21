<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<html>
<head>
	<title>Login | Congo</title>
</head>
	<t:genericpage>
		<jsp:attribute name="header">
	   	</jsp:attribute>
		<jsp:attribute name="footer">
	   	</jsp:attribute>
		<jsp:body>
		<br/>
		<br/>
		<form:form name="submitForm" method="POST">
	        <div align="center">
	        <div>${msg}</div>
	            <table>
	                <tr>
	                    <td>Email Address</td>
	                    <td><input type="text" name="email" /></td>
	                </tr>
	                <tr>
	                    <td>Password</td>
	                    <td><input type="password" name="password" /></td>
	                </tr>
	                <tr>
	                    <td></td>
	                    <td><input type="submit" value="Submit" /></td>
	                </tr>
	            </table>
	            <div style="color: red">${error}</div>
	        </div>
	    </form:form>
		</jsp:body>
	</t:genericpage>
</html>