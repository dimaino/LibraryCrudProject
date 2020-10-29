<%@ include file= "header.jsp" %>

<div class="container">
  	<div class="row justify-content-center">
 		<h2>Patron Dashboard</h2>
 	</div>
 	<h1>Hello, <c:out value="${user.getFirst_name()} ${user.getLast_name()}" /></h1>
    <div class="row" style="background-color: grey">
        <div class="container" style="padding: 1em; box-shadow: 0.25em  0.25em 0.75em rgba(0,0,0,.25),
          									0.125em 0.125em 0.25em rgba(0,0,0,.15);">
	        <div class="row">
	        	<div class="col-10" style="heigt: 100%; padding: 1em;margin-top: 1em; margin-left: auto; margin-right: auto; background-color: white; box-shadow: 0.25em  0.25em 0.75em rgba(0,0,0,.25),
          									0.125em 0.125em 0.25em rgba(0,0,0,.15);">
	        		<div class="row justify-content-center">
	        			<h3>Book Actions</h3>
	        		</div>
	        		<div class="col-4 offset-4">
       		       		<div class="d-flex justify-content-between align-items-center" >
    						<a href="<%= request.getContextPath() %>/PatronServlet/list">
								<button class="btn btn-primary btn-sm">View All Books</button>
							</a>
							<a href="#">
								<button class="btn btn-primary btn-sm">My Book History</button>
							</a>
	        			</div>
	        		</div>
	 
	        	</div>
	        	
	     	</div>
        
	<div class="row">
		<div class="col-11" style="margin-top: 1em; padding: 1em; margin-left: auto; margin-right: auto; background-color: white; box-shadow: 0.25em  0.25em 0.75em rgba(0,0,0,.25),
          									0.125em 0.125em 0.25em rgba(0,0,0,.15);">
 			  	<div class="row justify-content-center">
		 			<h3>My Checked Out Books</h3>
			 	</div>
	       		<table class="table table-striped">
					<thead>
						<tr>
							<th>Book Name</th>
							<th>Checked Out</th>
							<th>Due Date</th>
							<th>Overdue Status</th>
							<th></th>
						</tr>
					</thead>
					<tbody>
						<c:forEach var="bookcheckout" items="${checkoutBooks}">
							<tr>
								<td>
									<c:out value="${ bookcheckout.isbn }"/>
								</td>
								<td>
									<c:out value="${ bookcheckout.checkedout }"/>
								</td>
								<td>
									<c:out value="${ bookcheckout.due_date }"/>
								</td>
								<td>
									TODO
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

</div>


<%@ include file= "footer.jsp" %>