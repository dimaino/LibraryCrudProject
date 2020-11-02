<%@ include file= "header.jsp" %>

<div class="container">
  	<div class="row justify-content-center">
 		<h2>Book List</h2>
 	</div>
	<div class="container dashboard">
		<div class="row">
				<div class="col-5 offset-1">
					<form class="form-inline">
					  	<input type="text" class="form-control mb-2 mr-sm-2" id="inlineFormInputName2" placeholder="Search Books">
					 	<button type="submit" class="btn btn-primary mb-2">Submit</button>
					    <div class="form-check mb-2 mr-sm-2">
						    <input class="form-check-input" type="checkbox" id="inlineFormCheck">
						    <label class="form-check-label" for="inlineFormCheck">
						      Rented
						    </label>
						    &nbsp;&nbsp;&nbsp;&nbsp;
						    <input class="form-check-input" type="checkbox" id="inlineFormCheck">
						    <label class="form-check-label" for="inlineFormCheck">
						      Overdue
						    </label>
					  	</div>
					</form>
			</div>
		</div>
		<div class="row">
		<div class="col-10" style="margin-top: 1em; padding: 1em; margin-left: auto; margin-right: auto; background-color: white; box-shadow: 0.25em  0.25em 0.75em rgba(0,0,0,.25),
          									0.125em 0.125em 0.25em rgba(0,0,0,.15);">
 			
					<div class="row book-row-titles">
				    	<div class="col-5">
					      	Book Name
				      	</div>
					    <div class="col-2">
					       	ISBN
					    </div>
					    <div class="col-2">
					       Available
					    </div>
								
					</div>
						<c:forEach var="book" items="${allBooks}">
						
							<div class="row book-row">
		
								    <div class="col-5">
										<c:out value="${ book.title }"/>
							      	</div>
								    <div class="col-2">
								       <c:out value="${ book.isbn }"/>
								    </div>
								    <div class="col-2">
								       <c:out value="${ book.rented }"/>
								    </div>
												        			
								    <div class="col-3">
										<a href="view?isbn=<c:out value='${ book.isbn }' />">											
											<button class="dash-btn">View Book</button>
									     </a>
									</div>
							 </div>
											
						</c:forEach>
	        </div>
        </div>
	</div>
	
	
	</div>


<%@ include file= "footer.jsp" %>