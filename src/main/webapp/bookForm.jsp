<%@ include file= "header.jsp" %>

<div>
	<h2 style="text-align: center">Book Form</h2>
</div>
<br>

<c:choose>
	<c:when test="${error != null}">
		<p id="access-error"><c:out value="${error}" /></p>
	</c:when>
</c:choose>

<div class="jumbotron">
	<c:choose>
		<c:when test="${ book != null }">
			<form action="update" method="POST">
		</c:when>
		<c:otherwise>
			<form action="insert" method="POST">
		</c:otherwise>
	</c:choose>
	<div class="row">
		<c:choose>
			<c:when test="${ book == null }">
				<div class="col">			
					<label for="isbn">ISBN</label>
					<input type="text" class="form-control" placeholder="Enter ISBN" required min="10" max="10" id="isbn" name= "isbn" value="<c:out value='${book.isbn}'/>"> 
				</div>
			</c:when>
			<c:otherwise>
				<input type="hidden" class="form-control" placeholder="Enter ISBN" min="10" max="10" id="isbn" name= "isbn" value="<c:out value='${book.isbn}'/>">
			</c:otherwise>
		</c:choose>
		<div class="col">
			<label for="title">Book Title</label>
			<input type="text" class="form-control" placeholder="Enter title of the book" name="title" id="title" required value="<c:out value='${book.title}'/>">
		</div>
	</div>
	<br>
	
	<div>
		<label for="descr">Book Description</label>
		<textarea class="form-control" rows="3"  name="descr" id="descr" required><c:out value='${book.descr}'/></textarea>
	</div>

	<button type="submit" style="text-align: right; color: white; float: right" type="button" class="btn btn-primary" data-toggle="button" aria-pressed="false" autocomplete="off">
	Save
	</button>
</form>
<br>
</div>

<%@ include file= "footer.jsp" %>