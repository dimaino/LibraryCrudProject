<%@ include file= "header.jsp" %>

<div class="container">
  	<div class="row justify-content-center">
 		
 	</div>
	<div class="container" style="background-color: grey; padding: 1em;">
		<div class="row">
			<h2>Book: <c:out value='${book.title}'/></h2>
		</div>
		<div class="row">
			<h3>ISBN: <c:out value='${book.isbn}'/></h3>
		</div>
		<div class="row">
			<h4>Description: <c:out value='${book.descr}'/></h4>
		</div>
		<div class="row">
			<h4>Checked Out: <c:out value='${book.rented}'/> </h4>
        </div>
        <c:choose>
			<c:when test="${user.getClass().getName() == 'com.cognixia.jump.model.Librarian'}">
        		<a href="edit?isbn=<c:out value='${ book.isbn }' />">
					<button class="btn btn-primary">Edit</button>
				</a>
			</c:when>
			<c:otherwise>
				<c:choose>
					<c:when test="${book.rented == false}">
						<c:choose>
							<c:when test="${user.account_frozen == false}">
								<a href="<%= request.getContextPath() %>/Patron/checkout?isbn=<c:out value='${ book.isbn }' />">
									<button class="btn btn-primary">Checkout</button>
								</a>
							</c:when>
						</c:choose>
					</c:when>
				</c:choose>
			</c:otherwise>
		</c:choose>
	</div>	
</div>

<%@ include file= "footer.jsp" %>