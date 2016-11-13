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

<link rel="stylesheet" href="css/responstables.css">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Books Registry</title>
</head>
<body>
	<nav class="navbar navbar-default">
	<div class="container-fluid">
		<!-- Brand and toggle get grouped for better mobile display -->
		<div class="navbar-header">
			<button type="button" class="navbar-toggle collapsed"
				data-toggle="collapse" data-target="#bs-example-navbar-collapse-1"
				aria-expanded="false">
				<span class="sr-only">Toggle navigation</span> <span
					class="icon-bar"></span> <span class="icon-bar"></span> <span
					class="icon-bar"></span>
			</button>
			<a class="navbar-brand" href="#">Library</a>
		</div>

		<!-- Collect the nav links, forms, and other content for toggling -->
		<div class="collapse navbar-collapse"
			id="bs-example-navbar-collapse-1">
			<ul class="nav navbar-nav">
				<li class="active"><a href="BooksRegistryController">Registry <span class="sr-only">(current)</span></a></li>
				<li><a href="BooksController?action=listBook">Books </a></li>
				<li><a href="UserController?action=listUser">Users </a></li>
				<li class="dropdown"><a href="#" class="dropdown-toggle"
					data-toggle="dropdown" role="button" aria-haspopup="true"
					aria-expanded="false">Dropdown <span class="caret"></span></a>
					<ul class="dropdown-menu">
						<li><a href="#">Action</a></li>
						<li><a href="#">Another action</a></li>
						<li><a href="#">Something else here</a></li>
						<li role="separator" class="divider"></li>
						<li><a href="#">Separated link</a></li>
						<li role="separator" class="divider"></li>
						<li><a href="#">One more separated link</a></li>
					</ul></li>
			</ul>
			<form class="navbar-form navbar-left">
				<div class="form-group">
					<input type="text" class="form-control" placeholder="Search">
				</div>
				<button type="submit" class="btn btn-default">Submit</button>
			</form>
			<ul class="nav navbar-nav navbar-right">
				<li><a href="#">Link</a></li>
				<li class="dropdown"><a href="#" class="dropdown-toggle"
					data-toggle="dropdown" role="button" aria-haspopup="true"
					aria-expanded="false">Dropdown <span class="caret"></span></a>
					<ul class="dropdown-menu">
						<li><a href="#">Action</a></li>
						<li><a href="#">Another action</a></li>
						<li><a href="#">Something else here</a></li>
						<li role="separator" class="divider"></li>
						<li><a href="#">Separated link</a></li>
					</ul></li>
			</ul>
		</div>
		<!-- /.navbar-collapse -->
	</div>
	<!-- /.container-fluid --> </nav>



	<h1>Books Registry</h1>
	<table class="responstable" border=1
		style="margin: 0 auto; width: 855px;">
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
					<td><img src="${book.URLOkladki}"
						style="width: 70px; height: 100px"></td>
					<td><c:choose>
							<c:when test="${book.currentUsers == 0}">
								<button data-id="${book.id}" class='lend_book'
									data-toggle="modal" data-target="#editModal"
									style="cursor: pointer">Lend</button>
							</c:when>
							<c:otherwise>
								<button data-id="${book.id}" class='retrun_book'
									data-toggle="modal" data-target="#editModal"
									style="cursor: pointer">Return</button>
							</c:otherwise>
						</c:choose></td>
					<td><a
						href="BooksRegistryController?action=bookDetails&bookId=<c:out value="${book.id}"/>">Show
							History</a></td>

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
					<a href="#" data-dismiss="modal"> <small>Back</small></a> <span
						id="myModalLabel" style="margin-left: 20px;"><font
						size="+2"><b>Select user</b></font></span>
				</div>
				<div class="modal-body">


					<table border=1 class="responstable">
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
											data-target="#editModal" data-id="${user.userId}"
											style="cursor: pointer">Select</button></td>

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

				for ( var key in params) {
					if (params.hasOwnProperty(key)) {
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
				$('body').append(
						'<p>' + JSON.stringify(bookId, null, 4) + '</p>');
			}

			var userBookSelected = function(event) {
				var selectedUserID = $(this).data('id');
				$('body').append(
						'<p> userID: '
								+ JSON.stringify(selectedUserID, null, 4)
								+ '</p>');
				post('BooksRegistryController?action=lend', {
					userID : selectedUserID,
					bookID : currentlySelectedBookId
				});
			}

			var bookReturnSelected = function(event) {
				var selectedBook = $(this).data('id');
				post('BooksRegistryController?action=return', {
					bookID : selectedBook
				});
			}
			$(".lend_book").on('click', bookLendButtonClicked);
			$(".user_select_button").on('click', userBookSelected);
			$(".retrun_book").on('click', bookReturnSelected);
		</script>
</body>
</html>