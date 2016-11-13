<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet"
	href="//code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">
<link rel="stylesheet" href="/resources/demos/style.css">
<script src="https://code.jquery.com/jquery-1.12.4.js"></script>
<script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>
<link rel="stylesheet" href="css/bootstrap.min.css">
<link rel="stylesheet" href="css/bootstrap-theme.min.css">
<link rel="stylesheet" href="css/responstables.css">
<script type="text/javascript" src="js/bootstrap.min.js"></script>
<title>User details</title>
</head>
<body>

	<form method="POST" action='BooksController' name="formAddBook"
		style="margin: 0 auto; width: 855px;">
		<div class="form-group">
			<label for="userIdentity">Book ID</label> 
			<input readonly type="email"
				value="<c:out value="${book.id}" />" class="form-control" name="bookid"
				id="example">
		</div>
		<div class="form-group">
			<label>Title</label> 
			<input type="text" value="<c:out value="${book.tytul}" />"
				class="form-control" name="title" aria-describedby="emailHelp"
				placeholder="Enter title">
		</div>
		<div class="form-group">
			<label>ISBN</label> 
			<input type="text" value="<c:out value="${book.ISBN}" />" class="form-control"
				name="ISBN" aria-describedby="emailHelp"
				placeholder="Enter ISBN">
		</div>

		<div class="form-group">
			<label for="userName">Publish year</label> 
			<input type="text" value="<c:out value="${book.rokWydania}" />" class="form-control"
				name="publishYear" aria-describedby="emailHelp"
				placeholder="Enter Publish Year">
		</div>

		<div class="form-group">
			<label for="userName">Description</label> 
			<input type="text"
				value="<c:out value="${book.opis}" />" class="form-control"
				name="description" aria-describedby="emailHelp"
				placeholder="Enter Description">
		</div>

		<div class="form-group">
			<label for="userName">Cover URL</label> 
			<input type="text"
				value="<c:out value="${book.URLOkladki}" />" class="form-control"
				name="coverURL" aria-describedby="emailHelp"
				placeholder="Enter Cover URL">
		</div>

		<button type="submit" class="btn btn-primary">Submit</button>
	</form>
</body>
</html>