<%@ taglib prefix="c" uri="http://www.springframework.org/tags/form" %>

<!DOCTYPE html>

<html>

<head>

	<title>Customer Form</title>
	
	<link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/style.css" />	
	<link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/add-customer-style.css" />	
	
</head>

<body>

	<div id="wrapper">
		<div id="header">
			<h2>CRM - Customer Relationship Manager</h2>
		</div>
	</div>
	
	<div id="container">
		<h3>Save Customer</h3>
		
		<c:form action="saveCustomer" modelAttribute="customer" method="POST">
		
			<!-- need to associate this data with customer id 
			(for updating, id will be sent -not empty- by submitting the form so hibernate will update the data and not insert a new one) -->
			<c:hidden path="id"/>
		
			<table>
				<tbody>
				
					<tr>
						<td><label>First Name:</label></td>
						<td><c:input path="firstName" autocomplete="off"/> </td>
					</tr>
					
					<tr>
						<td><label>Last Name:</label></td>
						<td><c:input path="lastName" autocomplete="off"/> </td>
					</tr>
					
					<tr>
						<td><label>Email:</label></td>
						<td><c:input path="email" autocomplete="off"/> </td>
					</tr>
					
					<tr>
						<td></td>
						<td><input type="submit" value="Save" class="save"></td>
					</tr>
					
				</tbody>
			</table>
		
		</c:form>
			
			<p>
				<a href="${pageContext.request.contextPath}/customer/list?sort=">Back to List</a>
			</p>
	</div>

</body>

</html>