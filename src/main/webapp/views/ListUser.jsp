<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/2.1.1/jquery.min.js"></script>
<link rel="stylesheet" href="css/bootstrap.min.css">
<link rel="stylesheet" href="css/bootstrap-theme.min.css">
<script type="text/javascript" src="js/bootstrap.min.js"></script>
<title>Insert title here</title>
</head>
<body>
	
	<table border=1>
		<thead>
			<tr>
				<th> User ID </th>
				<th> First Name </th>
				<th> Surname </th>
				<th> Date of birth </th>
				<th> Telefon </th>
				<th> Email </th>
				<th colspan=2>Action</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${users}" var="user">
				<tr>
					<td><c:out value="${user.userId}" /></td>
					<td><c:out value="${user.imie}"/></td>
					<td><c:out value="${user.nazwisko}"/></td>
					<td><fmt:formatDate pattern="yyyy-MMM-dd" value="${user.dataUrodzenia}" /></td>
					<td><c:out value="${user.telefon }"/></td>
					<td><c:out value="${user.email}"/></td>
					<td><a href="UserController?action=edit&userId=<c:out value="${user.userId}"/>">Update</a></td>
                    <td><a href="UserController?action=delete&userId=<c:out value="${user.userId}"/>">Delete</a></td>
					
				</tr>
			</c:forEach>
		</tbody>
	</table>
	<p><a href="UserController?action=insert">Add User</a></p>
</body>
</html>