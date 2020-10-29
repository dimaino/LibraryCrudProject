<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" isELIgnored="false"%>

<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Product Catalog</title>

<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css" integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh" crossorigin="anonymous">

</head>

<body>

	<%-- navbar, will show for every jsp page --%>

	<nav class="navbar navbar-expand-lg navbar-light bg-light">
	
		<a class="navbar-brand" href="<%= request.getContextPath() %>">Product Catalog</a>
		
		<button class="navbar-toggler" type="button" data-toggle="collapse"
			data-target="#navbarNavAltMarkup" aria-controls="navbarNavAltMarkup"
			aria-expanded="false" aria-label="Toggle navigation">
			<span class="navbar-toggler-icon"></span>
		</button>
		
		<div class="collapse navbar-collapse" id="navbarNavAltMarkup">
		
			<div class="navbar-nav">
				 <%--
				 
				 <a class="nav-item nav-link" href="http://localhost:8080/CrudProject/list">View</a> 
				 
				  
				<a class="nav-item nav-link" href="<%= request.getContextPath() %>/list">View</a> 
				
				<a class="nav-item nav-link" href="<%= request.getRequestURI() %>/list">View</a>
				
				--%>
				
				<a class="nav-item nav-link" href="<%= request.getRequestURI() %>/list">List</a>
					
				
				
			</div>
			
		</div>
		
	</nav>
	
	
	
	
	
	
	
	