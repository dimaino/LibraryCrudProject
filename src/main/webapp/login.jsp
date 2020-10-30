<%@ include file= "header.jsp" %>

<div id="access">Login</div>
<c:choose>
	<c:when test="${error != null}">
		<p id="access-error"><c:out value="${error}" /></p>
	</c:when>
</c:choose>
<div id="access-page">
  <div id="form">
    <form action="<%= request.getContextPath() %>/Access/signin" method="POST" id="login-form">
      <input type="text" name="username" placeholder="username" required/>
      <input type="password" name="password" placeholder="password" required/>
      <br>
      <button type="submit">login</button>
      <p id="message">Not registered? <a href="<%= request.getContextPath() %>/Access/signupPage">Create an account</a></p>
    </form>
  </div>
</div>

<%@ include file= "footer.jsp" %>