<%@ include file= "header.jsp" %>

<div class="login">
  <h2><strong><center>Login</center></strong></h2><br>
<form action="<%= request.getContextPath() %>/signin" method="POST">

<!--	<fieldset>-->
<!--        <legend>Select User Type</legend>-->
<!--        <div class="btn-group btn-group-toggle radio-buttons" data-toggle="buttons">-->
<!--	        <label class="btn btn-secondary active" for="patron"><strong><center>Patron</center></strong>-->
<!--	        	<input type="radio" name="user" id="patron" value="patron" autocomplete="off" checked>-->
<!--	        </label>-->
<!--	        <label class="btn btn-secondary" for="librarian"><strong>Librarian</strong>-->
<!--	        	<input type="radio" name="user" id="librarian" value="librarian" autocomplete="off">-->
<!--	        </label>-->
<!--        </div>-->
<!--     </fieldset>-->
<!--     <br>-->

  <div class="container-fluid">
    <label for="uname"><b>Username</b></label>
    <input type="text" placeholder="Enter Username" name="uname" required>
    <label for="psw"><b>Password</b></label>
    <input type="password" placeholder="Enter Password" name="psw" required><br>
    <button style="float:right" type="submit">Reset</button>
    <button style="float:right" type="submit">Login</button>
  </div>
</form></div>

<%@ include file= "footer.jsp" %>