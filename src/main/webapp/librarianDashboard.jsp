<%@ include file= "header.jsp" %>

<div class="container">
  	<div class="row justify-content-center">
 		<h2>Library Dashboard</h2>
 	</div>
    <div class="row" style="background-color: grey">
        <div class="container" style="box-shadow: 0.25em  0.25em 0.75em rgba(0,0,0,.25),
          									0.125em 0.125em 0.25em rgba(0,0,0,.15);">
	        <div class="row">
	        	<div class="col-5" style="margin-top: 1em; margin-left: auto; margin-right: auto; background-color: white; box-shadow: 0.25em  0.25em 0.75em rgba(0,0,0,.25),
          									0.125em 0.125em 0.25em rgba(0,0,0,.15);">
	        		<div class="row justify-content-center">
	        			<h3>Book Actions</h3>
	        		</div>
	        		<div class="d-flex justify-content-between align-items-center" style="height:100%" >
    						<a href="<%= request.getContextPath()%>/books/bookList">
								<button class="btn btn-primary btn-sm" >View All</button>
							</a>
							<a href="#">
								<button class="btn btn-primary btn-sm">View Available</button>
							</a>
							<a href="#">
								<button class="btn btn-primary btn-sm">View Available</button>
							</a>
							<a href="#">
								<button class="btn btn-danger btn-sm">Add Book</button>
							</a>
	        		</div>
	  

	        	</div>
	        	<div class="col-6" style="margin-top: 1em; margin-left: auto; margin-right: auto; background-color: white; box-shadow: 0.25em  0.25em 0.75em rgba(0,0,0,.25),
          									0.125em 0.125em 0.25em rgba(0,0,0,.15);">
	        		<div class="row justify-content-center">
	        			<h3>Patron Appoval</h3>
	        		</div>	        		
	        		<table class="table table-striped">
						<thead>
							<tr>
								<th>First Name</th>
								<th>Last Name</th>
								<th>Username</th>
								<th></th>
								<th></th>
							</tr>
						</thead>
						<tbody>
								<tr>
									<td>
										Test
									</td>
									<td>
										Testington
									</td>
									<td>
										testUsername
									</td>
									<td>
										<a href="#">
											<button class="btn btn-primary btn-sm">Approve</button>
										</a>
										<a href="#">
											<button class="btn btn-danger btn-sm">Delete</button>
										</a>
									</td>
								</tr>
						</tbody>
					</table>
	        	</div>
	        </div>
        
	
		 	<div class="col-12" style="margin-top: 1em; margin-left: auto; margin-right: auto; background-color: white; box-shadow: 0.25em  0.25em 0.75em rgba(0,0,0,.25),
          									0.125em 0.125em 0.25em rgba(0,0,0,.15);">
 			  	<div class="row justify-content-center">
		 			<h3>Book List</h3>
			 	</div>
	 <!--       		<table class="table table-striped">
					<thead>
						<tr>
							<th>Book Name</th>
							<th>ISBN</th>
							<th>Rented?</th>
							<th>Added To Library</th>
							<th></th>
						</tr>
					</thead>
					<tbody>
							<tr>
								<td>
									Harry Potter
								</td>
								<td>
									12345asdfg
								</td>
								<td>
									false
								</td>
								<td>
									12-30-1999
								</td>
								
								<td>
									<a href="#">
										<button class="btn btn-primary btn-sm">View</button>
									</a>
								</td>
							</tr>
					</tbody>
				</table>--> 
				
				
				<%@ include file= "bookListTemplate.jsp" %>
				
	        </div>
        </div>
    </div>

</div>


<%@ include file= "footer.jsp" %>