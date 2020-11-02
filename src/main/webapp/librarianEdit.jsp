<%@ include file= "header.jsp" %>

<div id="access">
	<h2>Edit Account</h2>
</div>
<c:choose>
	<c:when test="${error != null}">
		<p id="access-error"><c:out value="${error}" /></p>
	</c:when>
</c:choose>
<div id="access-page">
	<div id="form">
	    <form action="<%= request.getContextPath() %>/Librarian/edit" method="POST" id="login-form">
	      <input type="text" name="username" placeholder="username" value="<c:out value='${user.username}'/>" required/>
	      <input type="password" name="password" placeholder="password" value="<c:out value='${user.password}'/>" required/>
	      <input type="password" name="passwordConfirmation" placeholder="password confirmation" value="<c:out value='${user.password}'/>" required/>
	      <button class="dash-btn" type="submit">Save</button>
	    </form>
	</div>
</div>	

<%@ include file= "footer.jsp" %>