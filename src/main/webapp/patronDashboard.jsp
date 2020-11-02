<%@ include file= "header.jsp" %>

<div class="container">
 	<c:choose>
		<c:when test="${error != null}">
			<p id="access-error"><c:out value="${error}" /></p>
		</c:when>
	</c:choose>
 	<h1>Hello, <c:out value="${user.getFirst_name()} ${user.getLast_name()}" /></h1>
    <div class="container dashboard">
    	<div class="row">
    
	        <div class="dashboard-card col-10">
	       		<div class="row justify-content-center">
        			<h3>Book Manager</h3>
        		</div>
       			<div class="row justify-content-around">
  						<a href="<%= request.getContextPath() %>/books/bookList">
						<button class="dash-btn">View All Books</button>
					</a>
					<a href="<%= request.getContextPath() %>/Patron/history">
						<button class="dash-btn">My Book History</button>
					</a>
       			</div>
       		</div>
       		<div class="dashboard-card col-10">
 			  	<div class="row justify-content-center">
		 			<h3>My Checked Out Books</h3>
			 	</div>
					<div class="row book-row-titles">
				    	<div class="col-3">
					      	Book Name
				      	</div>
					    <div class="col-2">
					       	Checked Out
					    </div>
					    <div class="col-2">
					       Due Date
					    </div>
					    <div class="col-2">
					    	Overdue Status
					    </div>
								
					</div>
						<c:forEach var="bookcheckout" items="${checkoutBooks}">
							<div class="row book-row">
						
						    	<div class="col-3">
									<c:out value="${ bookcheckout.book.title }"/>
						      	</div>
							    <div class="col-2">
									<c:out value="${ bookcheckout.checkedout }"/>
							    </div>
							    <div class="col-2">
									<c:out value="${ bookcheckout.due_date }"/>
							    </div>
							    <div class="col-2">
									<c:out value="${ bookcheckout.overdue }"/>
							    </div>
							    <div class="col-2">
									<a href="<%= request.getContextPath() %>/Patron/return?isbn=<c:out value='${bookcheckout.isbn}' />&checkout_id=<c:out value='${bookcheckout.checkout_id}' />">
										<button class="dash-btn">Return</button>
									</a>							    
								</div>
							
							</div>
					</c:forEach>
	       	 </div>
        </div>
	</div>
		 	
    </div>



<%@ include file= "footer.jsp" %>