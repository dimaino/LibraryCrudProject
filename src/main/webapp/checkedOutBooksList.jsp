<%@ include file= "header.jsp" %>

<div class="container">
  	<div class="row justify-content-center">
 		<h2>Checked Out Books List</h2>
 	</div>
	<div class="container dashboard">
		
		<div class="row">
		<div class="col-10" style="margin-top: 1em; padding: 1em; margin-left: auto; margin-right: auto; background-color: white; box-shadow: 0.25em  0.25em 0.75em rgba(0,0,0,.25),
          									0.125em 0.125em 0.25em rgba(0,0,0,.15);">
 			
					<div class="row book-row-titles">
				    	<div class="col-2">
					      	Checkout Id
				      	</div>
					    <div class="col-2">
					       Patron Id
					    </div>
					    <div class="col-2">
					       ISBN
					    </div>
					    <div class="col-2">
					       Checked Out Date
					    </div>
					    <div class="col-2">
					       Due Date
					    </div>
					    <div class="col-2">
					       Returned Date
					    </div>
								
					</div>
						<c:forEach var="book" items="${currentCheckedOutBooks}">
						
							<div class="row book-row">
		
								    <div class="col-2">
										<c:out value="${ book.checkout_id }"/>
							      	</div>
								    <div class="col-2">
								       <c:out value="${ book.patron_id }"/>
								    </div>
								    <div class="col-2">
								       <c:out value="${ book.isbn }"/>
								    </div>
								    <div class="col-2">
								       <c:out value="${ book.checkedout }"/>
								    </div>
								    <div class="col-2">
								       <c:out value="${ book.due_date }"/>
								    </div>
								     <div class="col-2">
								       <c:out value="${ book.returned }"/>
								    </div>
												        			
							 </div>
											
						</c:forEach>
	        </div>
        </div>
	</div>
	
	
	</div>


<%@ include file= "footer.jsp" %>