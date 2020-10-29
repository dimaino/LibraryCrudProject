<%@ include file= "header.jsp" %>

<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" integrity="sha384-JcKb8q3iqJ61gNV9KGb8thSsNjpSL0n8PARn9HuZOnIxN0hoP+VmmDGMN5t9UJ0Z" crossorigin="anonymous">

<div>
<h2 style="text-align: center">Book Form</h2>
</div>
<br>

<div class="jumbotron">
	<c:choose>
		<c:when test="${ book != null }">
			<form action="update" method="post">
		</c:when>
		
		<c:otherwise>
			<form action="insert" method="post">
		</c:otherwise>
	</c:choose>
	<div class="row">
	   <div class="col">
	   

		   <label for="isbn">ISBN</label>
		     <input type="text" class="form-control" placeholder="Enter ISBN" min="10" max="10" id="isbn" name= "isbn" value="<c:out value='${book.isbn}'/>"> 
		   </div>
		   <div class="col">
		       <label for="title">Book Title</label>
		     <input type="text" class="form-control" placeholder="Enter title of the book" name="title" id="title" value="<c:out value='${book.title}'/>">
		  </div>
	  </div>
	  <br>

    <div>
    <label for="descr">Book Description</label>
  <textarea class="form-control" rows="3"  name="descr" id="descr"><c:out value='${book.descr}'/></textarea>
</div>
<button type="submit" style="text-align: right; color: white; float: right" type="button" class="btn btn-primary" data-toggle="button" aria-pressed="false" autocomplete="off">
  Save
</button>
</form>
<br>


</div>

<%@ include file= "footer.jsp" %>