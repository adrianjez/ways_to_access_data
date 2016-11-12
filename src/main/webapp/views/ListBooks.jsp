<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" href="css/bootstrap.min.css">
<link rel="stylesheet" href="css/bootstrap-theme.min.css">
<script type="text/javascript" src="js/bootstrap.min.js"></script>
<title>Books list</title>
</head>
<body>
	<table border=1 style="margin: 0 auto; width: 855px;">
		<thead>
			<tr>
				<th> Book ID </th>
				<th> Title </th>
				<th> ISBN </th>
				<th> Year of publish </th>
				<th> Description </th>
				<th> Cover </th>
				<th colspan=2>Action</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${books}" var="book">
				<tr>
					<td><c:out value="${book.id}" /></td>
					<td><c:out value="${book.tytul}"/></td>
					<td><c:out value="${book.ISBN}"/></td>
					<td><c:out value="${book.rokWydania}"/></td>
					<td><c:out value="${book.opis }"/></td>
					<td><c:out value="${book.URLOkladki}"/></td>
					<td><a href="BooksController?action=edit&bookId=<c:out value="${book.id}"/>">Update</a></td>
                    <td><a href="BooksController?action=delete&bookId=<c:out value="${book.id}"/>">Delete</a></td>
					
				</tr>
			</c:forEach>
		</tbody>
	</table>
	<p><a href="BooksController?action=insert">Add Book</a></p>
</body>
</html>