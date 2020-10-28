<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" isELIgnored="false"%>

<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Library Management System</title>

<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" integrity="sha384-JcKb8q3iqJ61gNV9KGb8thSsNjpSL0n8PARn9HuZOnIxN0hoP+VmmDGMN5t9UJ0Z" crossorigin="anonymous">

</head>
<body>
<div class="container-fluid p-3 my-3 bg-dark text-white">

	<c:choose>
		<c:when test="${pat == null}">
			<div><center>Library Management System</center>
    			<button onclick="location.href='<%= request.getContextPath() %>/AccessServlet/signupPage'" type="button" class="btn btn-info" style="float: right; margin-top: -30px; margin-right: 50px; color: black">Sign Up</button>
    			<button onclick="location.href='<%= request.getContextPath() %>/AccessServlet/signinPage'" type="button" class="btn btn-info" style="float: right;  margin-top: -30px; margin-right: 20px; color: black">Sign In</button> 
			</div></div>
		</c:when>
		<c:otherwise>
			<div><center>Library Management System</center>
			    <button onclick="location.href='<%= request.getContextPath() %>/AccessServlet/logout'" type="button" class="btn btn-info" style="float: right;  margin-top: -30px; margin-right: 20px; color: black">Log out</button> 
			</div></div>
		</c:otherwise>
		
	</c:choose>

  <br>