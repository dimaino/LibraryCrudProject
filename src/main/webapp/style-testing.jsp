<%@ include file= "header.jsp" %>

<div class="container col-12">
  	<div class="row justify-content-center">
 		<h2>Style Testing</h2>
 	</div>
        <div class="container dashboard">
        	<h2 style="color: white">Dashboard Container</h2>
	        <div class="row">
	        	<div class="col-5 dashboard-card">
	        		<div class="row justify-content-center">
	        			<h3>Dashboard Card Buttons</h3>
	        		</div>
		        		<div class="d-flex align-content-center" style="margin-top:2em; height:75%">
		        		
		        			<div class="row justify-content-around">
		        				<a href="<%= request.getContextPath()%>/books/bookList">
									<button class="dash-btn" >View All</button>
								</a>
								<a href="#">
									<button class="dash-btn">View Available</button>
								</a>
						    </div>
								<br>
		        			<div class="row justify-content-around">
								<a href="<%= request.getContextPath()%>/listCurrentBookCheckouts">
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
	        			<h3>Dashboard List Approval/Denials</h3>
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
			      	<div class="row patron-row">
    		        	<div class="col-3">
	        				First Name
		        		</div>
		        		<div class="col-3">
		        			Last Name
		        		</div>
		        		<div class="col-3">
		        			Username
		        		</div>
		        		<div class="col-3">
        					<a href="#">
								<button class="approve-btn">&#x1F5F8</button>
							</a>
							<a href="#">
								<button class="deny-btn">X</button>
							</a>
		        		</div>		
	        		</div>
	        		     	<div class="row patron-row">
    		        	<div class="col-3">
	        				First Name
		        		</div>
		        		<div class="col-3">
		        			Last Name
		        		</div>
		        		<div class="col-3">
		        			Username
		        		</div>
		        		<div class="col-3">
        					<a href="#">
								<button class="approve-btn">&#x1F5F8</button>
							</a>
							<a href="#">
								<button class="deny-btn">X</button>
							</a>
		        		</div>		
	        		</div>
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

</div>


<%@ include file= "footer.jsp" %>