<%@ include file= "header.jsp" %>

	<div class="container">
	
		<br>
		<h1 class="display-3">History of Book Checkout List</h1>
		<br>
		
		<table class="table table-striped">
		
			<thead>
				<tr>
					
					<th>checkout_id</th>
					<th>patron_id</th>
					<th>isbn</th>
					<th>checkedout</th>
					<th>due_date</th>
					<th>returned</th>
				</tr>
			</thead>
			
			<tbody>
			
				<c:forEach var="checkout" items="${currentCheckedOutBooks}">
				
					<tr>
						<td>
							<c:out value="${ checkout.checkout_id }"/>
						</td>
						<td>
							<c:out value="${ checkout.patron_id }"/>
						</td>
						<td>
							<c:out value="${ checkout.isbn }"/>
						</td>
						<td>
							<c:out value="${ checkout.checkedout }"/>
						</td>
						<td>
							<c:out value="${ checkout.due_date }"/>
						</td>
						<td>
							<c:out value="${ checkout.returned }"/>
						</td>
						
					</tr>
				
				</c:forEach>
				
			</tbody>
		
		</table>
		
	</div>

<%@ include file= "footer.jsp" %>