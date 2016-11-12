<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/2.1.1/jquery.min.js"></script>
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap.min.css">
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/js/bootstrap.min.js"></script>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<table border=1 style="margin: 0 auto; width: 855px;">
		<thead>
			<tr>
				<th>Book ID</th>
				<th>Title</th>
				<th>ISBN</th>
				<th>Year of publish</th>
				<th>Description</th>
				<th>Cover</th>
				<th colspan=2>Action</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${extended_books}" var="book">
				<tr>
					<td><c:out value="${book.id}" /></td>
					<td><c:out value="${book.tytul}" /></td>
					<td><c:out value="${book.ISBN}" /></td>
					<td><c:out value="${book.rokWydania}" /></td>
					<td><c:out value="${book.opis }" /></td>
					<td><c:out value="${book.URLOkladki}" /></td>
					<td><c:choose>
							<c:when test="${book.currentUsers == 0}">
								<button data-id="${book.id}" class='lend_book' data-toggle="modal"
									data-target="#editModal" style="cursor: pointer">Lend this book</button>
							</c:when>
							<c:otherwise>
								<button data-id="${book.id}" class='retrun_book' style="cursor: pointer">Return book</button>
							</c:otherwise>
						</c:choose>
						<div class="hidden entity-key">"${book.id}"</div></td>

				</tr>
			</c:forEach>
		</tbody>
	</table>


	<button type="button" class="close" data-dismiss="modal"
		aria-hidden="true">&times;</button>
	<div class="modal fade" id="editModal" tabindex="-1" role="dialog"
		aria-labelledby="basicModal" aria-hidden="true">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<a href="#" data-dismiss="modal"> <img
						src="images/arrow-back-512.png" width="30px" height="30px">
						<small>Back</small></a> <span id="myModalLabel"
						style="margin-left: 20px;"><font size="+2"><b>Select
								user</b></font></span>
				</div>
				<div class="modal-body">


					<table border=1>
						<thead>
							<tr>
								<th>User ID</th>
								<th>First Name</th>
								<th>Surname</th>
								<th>Date of birth</th>
								<th>Telefon</th>
								<th>Email</th>
								<th colspan=1>Action</th>
							</tr>
						</thead>
						<tbody>
							<c:forEach items="${users_list}" var="user">
								<tr>
									<td><c:out value="${user.userId}" /></td>
									<td><c:out value="${user.imie}" /></td>
									<td><c:out value="${user.nazwisko}" /></td>
									<td><fmt:formatDate pattern="yyyy-MMM-dd"
											value="${user.dataUrodzenia}" /></td>
									<td><c:out value="${user.telefon }" /></td>
									<td><c:out value="${user.email}" /></td>
									  
									<td><button class='user_select_button' data-toggle="modal"
											data-target="#editModal" data-id="${user.userId}" style="cursor: pointer">Select</button></td>
									
								</tr>
							</c:forEach>
						</tbody>
					</table>

				</div>
			</div>
		</div>

		<script>
		function post(path, params, method) {
		    method = method || "post"; // Set method to post by default if not specified.

		    // The rest of this code assumes you are not using a library.
		    // It can be made less wordy if you use one.
		    var form = document.createElement("form");
		    form.setAttribute("method", method);
		    form.setAttribute("action", path);

		    for(var key in params) {
		        if(params.hasOwnProperty(key)) {
		            var hiddenField = document.createElement("input");
		            hiddenField.setAttribute("type", "hidden");
		            hiddenField.setAttribute("name", key);
		            hiddenField.setAttribute("value", params[key]);

		            form.appendChild(hiddenField);
		         }
		    }

		    document.body.appendChild(form);
		    form.submit();
		}
		
		var currentlySelectedBookId = -1;
		var bookLendButtonClicked = function(event) {
		    var bookId = $(this).data('id');
		    currentlySelectedBookId = bookId;
		    $('body').append('<p>'+JSON.stringify(bookId, null, 4)+'</p>');
		}
		
		var userBookSelected = function(event) {
			var selectedUserID = $(this).data('id');
			$('body').append('<p> userID: ' + JSON.stringify(selectedUserID, null, 4) + '</p>');
			post('BooksRegistryController?action=lend', {userID: selectedUserID, bookID: currentlySelectedBookId});
		}
		
		var bookReturnSelected = function(event) {
			var selectedBook = $(this).data('id');
			post('BooksRegistryController?action=return', {bookID: selectedBook});
		}
		$(".lend_book").on('click', bookLendButtonClicked);
		$(".user_select_button").on('click', userBookSelected);
		$(".retrun_book").on('click', bookReturnSelected);
		
		</script>
</body>
</html>