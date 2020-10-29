<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" isELIgnored="false"%>

<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Library Management System</title>

<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" integrity="sha384-JcKb8q3iqJ61gNV9KGb8thSsNjpSL0n8PARn9HuZOnIxN0hoP+VmmDGMN5t9UJ0Z" crossorigin="anonymous">
<style><%@include file="css/style.css"%></style>
<style><%@include file="css/access.css"%></style>
<style><%@include file="css/index.css"%></style>
</head>
<body>
	<div id="page-container">
		<div id="content-wrap">
			<nav class="navbar navbar-light navbar-expand-md bg-light justify-content-md-center justify-content-start">
			    <button class="navbar-toggler ml-1" type="button" data-toggle="collapse" data-target="#collapsingNavbar2">
			        <span class="navbar-toggler-icon"></span>
			    </button>
			    <c:choose>
					<c:when test="${user == null}">
					    <a class="nav-link" href="<%= request.getContextPath() %>">
					    	<svg width="30px" height="30px" viewBox="0 0 16 16" class="bi bi-house-door-fill" fill="currentColor" xmlns="http://www.w3.org/2000/svg">
			  					<path d="M6.5 10.995V14.5a.5.5 0 0 1-.5.5H2a.5.5 0 0 1-.5-.5v-7a.5.5 0 0 1 .146-.354l6-6a.5.5 0 0 1 .708 0l6 6a.5.5 0 0 1 .146.354v7a.5.5 0 0 1-.5.5h-4a.5.5 0 0 1-.5-.5V11c0-.25-.25-.5-.5-.5H7c-.25 0-.5.25-.5.495z"/>
			  					<path fill-rule="evenodd" d="M13 2.5V6l-2-2V2.5a.5.5 0 0 1 .5-.5h1a.5.5 0 0 1 .5.5z"/>
			  				</svg>
						</a>
					</c:when>
					<c:otherwise>
						<a class="nav-link" href="<%= request.getContextPath() %>/PatronServlet">
					    	<svg width="30px" height="30px" viewBox="0 0 16 16" class="bi bi-house-door-fill" fill="currentColor" xmlns="http://www.w3.org/2000/svg">
			  					<path d="M6.5 10.995V14.5a.5.5 0 0 1-.5.5H2a.5.5 0 0 1-.5-.5v-7a.5.5 0 0 1 .146-.354l6-6a.5.5 0 0 1 .708 0l6 6a.5.5 0 0 1 .146.354v7a.5.5 0 0 1-.5.5h-4a.5.5 0 0 1-.5-.5V11c0-.25-.25-.5-.5-.5H7c-.25 0-.5.25-.5.495z"/>
			  					<path fill-rule="evenodd" d="M13 2.5V6l-2-2V2.5a.5.5 0 0 1 .5-.5h1a.5.5 0 0 1 .5.5z"/>
			  				</svg>
						</a>
					</c:otherwise>
				</c:choose>
			    <div class="navbar-collapse collapse justify-content-between align-items-center w-100" id="collapsingNavbar2">
			        <ul class="navbar-nav mx-auto text-md-center text-left">
			            <li class="nav-item my-auto" id="nav-center">Library Management System</li>
			        </ul>
			        <ul class="nav navbar-nav flex-row justify-content-md-center justify-content-start flex-nowrap">
			        	<c:choose>
							<c:when test="${user == null}">
				    				<c:choose>
										<c:when test="${url != '/LibraryCrudProject/AccessServlet/signupPage'}">		
				    						<li class="nav-item"><a class="nav-link" href="<%= request.getContextPath() %>/AccessServlet/signupPage">Sign up</a></li>
				    					</c:when>
										<c:otherwise>
				    						<li class="nav-item"><a class="nav-link" href="<%= request.getContextPath() %>/AccessServlet/signinPage">Sign in</a></li> 
										</c:otherwise>
								</c:choose>
							</c:when>
							<c:otherwise>
							 		<li class="nav-item"><a class="nav-link" href="<%= request.getContextPath() %>/AccessServlet/logout">Logout</a></li> 
								
							</c:otherwise>
						</c:choose>
			            
			        </ul>
			    </div>
			</nav>
  		<br>