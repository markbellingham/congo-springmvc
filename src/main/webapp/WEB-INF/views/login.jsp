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
		<div id="login-register-container">
			<!-- Tab Buttons -->
			<button class="tablink" onclick="change_tab('login', this, '#0b5000')" id="login_tab">Login</button>
			<button class="tablink" onclick="change_tab('register', this, '#777')">Register</button>
			
			<!-- Tab Content -->
			<div id="login" class="login-register tabcontent">
				<form:form name="login" method="POST" modelAttribute="CongoCustomers">
			        <div align="center">
			        <div>${msg}</div>
			            <table>
			                <tr>
			                    <td>Email Address</td>
			                    <td><form:input path="email" /></td>
			                </tr>
			                <tr>
			                    <td>Password</td>
			                    <td><form:input path="password" type="password" /></td>
			                </tr>
			                <tr>
			                    <td></td>
			                    <td><input type="submit" value="Submit" /></td>
			                </tr>
			            </table>
			            <div style="color: red">${error}</div>
			        </div> <!-- ends centre align -->
			    </form:form>
		    </div> <!-- ends login -->
		    <div id="register" class="login-register tabcontent">
		    	<form:form name="register" method="POST" modelAttribute="CongoCustomers">
		    		<div align="center">
		    			<div>${msg2}</div>
		    			<table>
		    				<tr>
		    					<td class="right">First Name</td><td class="left"><form:input path="fname" required="required" /></td>
		    				</tr>
		    				<tr>
		    					<td class="right">Surname</td><td class="left"><form:input path="lname" required="required" /></td>
		    				</tr>
		    				<tr>	    				
		    					<td class="right">Address</td><td class="left"><form:input path="address1" required="required" /></td>
		    				</tr>
		    				<tr>	    					
		    					<td class="right">Address</td><td class="left"><form:input path="address2" /></td>
		    				</tr>
		    				<tr>	    					
		    					<td class="right">City</td><td class="left"><form:input path="city" required="required" /></td>
		    				</tr>
		    				<tr>	    					
		    					<td class="right">Postcode</td><td class="left"><form:input path="postcode" required="required" /></td>
		    				</tr>
		    				<tr>	    					
		    					<td class="right">Phone Number</td><td class="left"><form:input path="phone" required="required" /></td>
		    				</tr>
		    				<tr>	    					
		    					<td class="right">Email</td><td class="left"><form:input path="email" required="required" /></td>
		    				</tr>
		    				<tr>	    					
		    					<td class="right">Password</td><td class="left"><form:input path="password" type="password" required="required" /></td>
		    				</tr>
		    				<tr>
		    					<td></td><td><input type="submit" value="Submit"></td>
		    				</tr>
		    			</table>
		    		</div> <!-- ends centre align -->
		    	</form:form>
		    </div> <!-- ends register -->
		</div> <!-- ends login-register-container -->
		</jsp:body>
	</t:genericpage>
</html>

<script>
// Get the element with id="defaultOpen" and click on it
document.getElementById("login_tab").click();
</script>

