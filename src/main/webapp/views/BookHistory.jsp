<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" href="css/responstables.css">
<title>Books list</title>
</head>
<body>
	<h1>Book History</h1>
	<table border=1 style="margin: 0 auto; width: 855px;" class="responstable">
		<thead>
			<tr>
				<th> Book Transaction ID </th>
				<th> User name </th>
				<th> User surname </th>
				<th> Date of lend</th>
				<th> Date of return </th>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${bookTransactions}" var="bookTransaction">
				<tr>
					<td><c:out value="${bookTransaction.id}" /></td>
					<td><c:out value="${bookTransaction.osoba.imie}"/></td>
					<td><c:out value="${bookTransaction.osoba.nazwisko}"/></td>
					<td><fmt:formatDate pattern="yyyy-MMM-dd" value="${bookTransaction.data_wydania}" /></td>
					<td><fmt:formatDate pattern="yyyy-MMM-dd" value="${bookTransaction.data_zwrotu}" /></td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
</body>
</html>