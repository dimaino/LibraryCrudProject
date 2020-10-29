<%@ include file= "header.jsp" %>

<div class="container">
  	<div class="row justify-content-center">
 		
 	</div>
	<div class="container" style="background-color: grey; padding: 1em;">
		<div class="row">
			<h2>Title: <c:out value='${book.title}'/></h2>
		</div>
		<div class="row">
			<h3>ISBN: <c:out value='${book.isbn}'/></h3>
		</div>
		<div class="row">
			<h4>Descr: <c:out value='${book.descr}'/></h4>
		</div>
		<div class="row">
			<h4>Rented: <c:out value='${book.rented}'/> </h4>
        </div>
        
        <a href="edit?isbn=<c:out value='${ book.isbn }' />">
			<button class="btn btn-primary">Edit</button>
		</a>
		<button class="btn btn-primary">Checkout</button>
		<button class="btn btn-primary">Exit</button>
		
	</div>
	
	
	</div>


<%@ include file= "footer.jsp" %>