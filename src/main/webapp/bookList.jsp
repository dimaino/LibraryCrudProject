<%@ include file= "header.jsp" %>

<div class="container">
  	<div class="row justify-content-center">
 		<h2>Book List</h2>
 	</div>
	<div class="container" style="background-color: grey; padding: 1em;">
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
 			       		<table class="table table-striped">
					<thead>
						<tr>
							<th>Book Name</th>
							<th>ISBN</th>
							<th>Rented?</th>
							<th></th>
						</tr>
					</thead>
					<tbody>
						<c:forEach var="book" items="${allBooks}">
					
							<tr>
								<td>
									<c:out value="${ book.title }"/>
								</td>
								<td>
									<c:out value="${ book.isbn }"/>
								</td>
								<td>
									<c:out value="${ book.rented }"/>
								</td>
				
								<td>
				
									
									<a href="#">
										<button class="btn btn-danger">View</button>
									</a>
								</td>
							</tr>
					
						</c:forEach>
					</tbody>
				</table>
	        </div>
        </div>
	</div>
	
	
	</div>


<%@ include file= "footer.jsp" %>