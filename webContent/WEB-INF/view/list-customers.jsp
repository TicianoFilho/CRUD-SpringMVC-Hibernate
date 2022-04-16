<!-- Java Expression Language imports -->
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %> 
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>             <!-- We need it in order to use the form -->

<!-- import the interface SortUtils to sort by table columns -->
<%@ page import="com.luv2code.springdemo.util.SortUtils" %>


<!DOCTYPE html>
<html>
<head>
<title>List Costumers</title>

	<!-- reference our style sheet -->
	<link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/style.css" />

</head>

<body>

	<div id="wrapper">
		<div id="header">
			<h2>CRM - Customer Relationship Manager</h2>
		</div>
	</div>

	<div id="container">
		<div id="content">
		
			<!-- add button -->
			<input type="button" value="Add Customer" class="add-button" onclick="window.location.href='showFormForAdd'; return false;"/>
		
		
			<!-- Adding search field -->
			<form:form action="search" method="GET"> 
			
				Search Customer <input type="text" name="searchName" autocomplete="off" style="width: 200px">
				<input type="submit" value="Search" class="add-button">
				
			</form:form>
			
			
			<!-- creating the URL for sorting by table columns - begin -->
			
			<c:url var="sortLinkFirstName" value="/customer/list">
				<c:param name="sort" value="<%= Integer.toString(SortUtils.FIRST_NAME) %>"></c:param>
			</c:url>
			
			<c:url var="sortLinkLastName" value="/customer/list">
				<c:param name="sort" value="<%= Integer.toString(SortUtils.LAST_NAME) %>"></c:param>
			</c:url>
			
			<c:url var="sortLinkEmail" value="/customer/list">
				<c:param name="sort" value="<%= Integer.toString(SortUtils.EMAIL) %>"></c:param>
			</c:url>
			
			<!-- creating the URL for sorting by table columns - end -->
			
			
			<!-- add out HTML table here -->
			
			<table>
				<tr>
					<th><a href="${sortLinkFirstName}">First Name</a></th>
					<th><a href="${sortLinkLastName}">Last Name</a></th>
					<th><a href="${sortLinkEmail}">Email</a></th>
					<th>Action</th>
				</tr>

				<!-- loop over and print our customers -->

				<c:forEach var="tempCustomer" items="${customers}">

					<!-- Construct an "update" link with customer id -->
					<c:url var="updateLink" value="/customer/showFormForUpdate">
						<c:param name="customerId" value="${tempCustomer.id}"></c:param>
					</c:url>
					
					<!-- Construct a "delete" link with customer id -->
					<c:url var="deleteLink" value="/customer/delete">
						<c:param name="customerId" value="${tempCustomer.id}"></c:param>
					</c:url>

					<tr>
						<td>${tempCustomer.firstName}</td>
						<td>${tempCustomer.lastName}</td>
						<td>${tempCustomer.email}</td>
						
						<td>
							<!-- display the update link -->
							<a href="${updateLink}">Update</a> | <a href="${deleteLink}" 
							onclick="if (!(confirm('Are you sure you want to delete this customer?'))) return false">Delete</a>
						</td>
						
					</tr>

				</c:forEach>

			</table>
		
		</div>
	</div>

<!-- My JavaScript Code - begin -->
<script type="text/javascript">

	<!--
	When I use the below javaScript method colling it "onclick="deleteConfirmation()" it doesn't work, it delete the data even if I click the cancel button, 
	but when I write this javaScript code directly into the onclick attribute this works properly. I still don't understand that.
	
	function deleteConfirmation() {
		if (!(confirm('Are you sure you want to delete this customer?')))
			return false
	}
	-->

</script>
<!-- My JavaScript Code - end -->

</body>
</html>