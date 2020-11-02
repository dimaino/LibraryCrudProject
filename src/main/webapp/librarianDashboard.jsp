<%@ include file= "header.jsp" %>

<div class="container col-12">
        <div class="container dashboard">
        	<h2 style="color: white">Librarian Dashboard</h2>
	        <div class="row">
	        	<div class="col-5 dashboard-card">
	        		<div class="row justify-content-center">
	        			<h3>Book Manager</h3>
	        		</div>
	        		<div class="d-flex align-content-center" style="margin-top:2em; height:75%">
	        		
	        			<div class="row justify-content-around">
	        				<a href="<%= request.getContextPath()%>/books/bookList">
								<button class="dash-btn" >View All</button>
							</a>
							<a href="http://localhost:8080/LibraryCrudProject/Librarian/available">
								<button class="dash-btn">View Available</button>
							</a>
					    </div>
							<br>
	        			<div class="row justify-content-around">
							<a href="http://localhost:8080/LibraryCrudProject/Librarian/checkout">
								<button class="dash-btn">View Checked Out</button>
							</a>
							<a href="<%= request.getContextPath()%>/books/new">
								<button class="dash-btn">Add New Book</button>
							</a>
	        			
	        			</div>
        			</div>
	  			</div>
	        	<div class="col-6 dashboard-card">
	        		<div class="row justify-content-center">
	        			<h3>Patron Approvals</h3>
	        		</div>
       		
       			    <div class="row patron-row-titles">
 			            <div class="col-3">
	        				First Name
		        		</div>
		        		<div class="col-3">
		        			Last Name
		        		</div>
		        		<div class="col-3">
		        			Username
		        		</div>
       				</div>
       				<c:forEach var="patron" items="${frozenPatrons}">
       					<div class="row patron-row">
							<div class="col-3">
       							<c:out value="${ patron.first_name }"/>
        					</div>
				
							<div class="col-3">
       							<c:out value="${ patron.last_name }"/>
        					</div>
							<div class="col-3">
       							<c:out value="${ patron.username }"/>
        					</div>
        					
        			   		<div class="col-3">
       							<a href="<%= request.getContextPath()%>/Librarian/approve?patron_id=<c:out value='${patron.patron_id}' />">
									<button class="approve-btn">&#x1F5F8</button>
								</a>
       							<a href="<%= request.getContextPath()%>/Librarian/delete?patron_id=<c:out value='${patron.patron_id}' />">
									<button class="deny-btn">X</button>
								</a>
																	
        					</div>	
						</div>
					</c:forEach>
    		
	        	</div>
	        </div>
        
		 	<div class="col-12 dashboard-card">
 			  	<div class="row justify-content-center">
		 			<h3>Past Due Books</h3>
			 	</div>
				
				<%@ include file= "bookListTemplate.jsp" %>
				
	        </div>
        </div>
    </div>




<%@ include file= "footer.jsp" %>