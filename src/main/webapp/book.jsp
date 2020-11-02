<%@ include file= "header.jsp" %>

<div class="container dashboard">
	<div class="book-card">
		<div class="row">
			<h2><strong>Title: <c:out value='${book.title}'/></strong></h2>
		</div>
		<div class="row">
			<h4>ISBN: <c:out value='${book.isbn}'/></h4>
		</div>
		<div class="row">
			<h4>Description: <c:out value='${book.descr}'/></h4>
		</div>
		<div class="row">
			<c:choose>
				<c:when test="${book.rented == false}">
		
					<h4>Available</h4>
				</c:when>
				<c:otherwise>
					<h4>Currently Unavailable</h4>
				</c:otherwise>
			</c:choose>
        </div>
        <c:choose>
			<c:when test="${user.getClass().getName() == 'com.cognixia.jump.model.Librarian'}">
				<c:choose>
					<c:when test="${book.rented == false}">
						<div class="row justify-content-end">
			        		<a href="edit?isbn=<c:out value='${ book.isbn }' />">
								<button class="edit-btn">Edit</button>
							</a>
							<a href="delete?isbn=<c:out value='${ book.isbn }' />">
								<button class="delete-btn">Delete</button>
							</a>
						</div>
					</c:when>
					<c:otherwise>
						<div class="row justify-content-end">
							<a href="edit?isbn=<c:out value='${ book.isbn }' />">
								<button class="edit-btn">Edit</button>
							</a>
						</div>
					</c:otherwise>
				</c:choose>
			</c:when>
			<c:otherwise>
				<c:choose>
					<c:when test="${book.rented == false}">
						<c:choose>
							<c:when test="${user.account_frozen == false}">
								<div class="row justify-content-end">
									<a href="<%= request.getContextPath() %>/Patron/checkout?isbn=<c:out value='${ book.isbn }' />">
										<button class="dash-btn">Checkout</button>
									</a>
								</div>
							</c:when>
						</c:choose>
					</c:when>
				</c:choose>
			</c:otherwise>
		</c:choose>
	</div>	
</div>

<%@ include file= "footer.jsp" %>