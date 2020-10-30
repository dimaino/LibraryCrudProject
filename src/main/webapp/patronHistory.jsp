<%@ include file= "header.jsp" %>
<h1 style="text-align: center">HERE</h1>
<div class="container">
	<input id="myInput" type="text" placeholder="Search title">
	<br>
	<table id="patronHistoryTable" class="table table-striped">
		<thead>
			<tr>
				<th>Title</th>
				<th>Checkout Date</th>
				<th>Due Date</th>
				<th>Returned Date</th>
			</tr>
		</thead>
		<tbody id="myTable">
			<c:forEach var="bHistory" items="${bookHistory}">
				<tr>
					<td>
						<c:out value="${ bHistory.book.title }"/>
					</td>
					<td>
						<c:out value="${ bHistory.checkedout }"/>
					</td>
					<td>
						<c:out value="${ bHistory.due_date }"/>
					</td>
					<td>
						<c:out value="${ bHistory.returned }"/>
					</td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
</div>

<%@ include file= "footer.jsp" %>