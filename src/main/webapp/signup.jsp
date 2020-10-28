<%@ include file= "header.jsp" %>

<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" integrity="sha384-JcKb8q3iqJ61gNV9KGb8thSsNjpSL0n8PARn9HuZOnIxN0hoP+VmmDGMN5t9UJ0Z" crossorigin="anonymous">

<div>
<h2 style="text-align: center">User Form</h2>
</div>
<br>

<div class="jumbotron">
<form>
  <div class="row">
    <div class="col">
    <label for="isbn">First Name</label>
      <input type="text" class="form-control" placeholder="Enter first name" min="10" max="10"> 
    </div>
    <div class="col">
        <label for="title">Last Name</label>
      <input type="text" class="form-control" placeholder="Enter last name">
    </div>

</div>
    <label for="username">Username</label>
  <input type="text" class="form-control" id="username" name="username" placeholder="Enter username" required>
      <label for="pw">Password</label>
  <input type="password" class="form-control" placeholder="Enter password" name="pw" required>
      <label for="confirmpw">Confirm pw</label>
  <input type="text" class="form-control" id="confirmpw" name="confirmpw" placeholder="Confirm password">
</div>
</form>

<button style="text-align: right; color: white; float: right" type="button" class="btn btn-primary" data-toggle="button" aria-pressed="false" autocomplete="off">
  Save
</button>

<%@ include file= "footer.jsp" %>