<%@ include file= "header.jsp" %>

<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" integrity="sha384-JcKb8q3iqJ61gNV9KGb8thSsNjpSL0n8PARn9HuZOnIxN0hoP+VmmDGMN5t9UJ0Z" crossorigin="anonymous">

<div>
<h2 style="text-align: center">Book Form</h2>
</div>
<br>

<div class="jumbotron">
<form>
  <div class="row">
    <div class="col">
    <label for="isbn">ISBN</label>
      <input type="text" class="form-control" placeholder="Enter ISBN" min="10" max="10"> 
    </div>
    <div class="col">
        <label for="title">Book Title</label>
      <input type="text" class="form-control" placeholder="Enter title of the book">
    </div></div><br>

    <div>
    <label for="description">Book Description</label>
  <textarea class="form-control" id="description" name="description" rows="3" placeholder="Enter short description of the book"></textarea>
</div>
</form>
<br>

<button style="text-align: right; color: white; float: right" type="button" class="btn btn-primary" data-toggle="button" aria-pressed="false" autocomplete="off">
  Save
</button>
</div>

<%@ include file= "footer.jsp" %>