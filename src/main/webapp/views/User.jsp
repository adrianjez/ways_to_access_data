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

	<script>
		$(function() {
			$("#datepicker").datepicker({
				dateFormat : 'mm/dd/yy',
			});
		});
	</script>
	<form method="POST" action='UserController' name="formAddUser"
		style="margin: 0 auto; width: 855px;">
		<div class="form-group">
			<label for="userIdentity">ID</label> 
			<input readonly type="text"
				value="<c:out value="${user.userId}" />" class="form-control"
				id="example" name="userid">
		</div>
		<div class="form-group">
			<label for="exampleInputEmail">Email address</label> <input
				type="email" value="<c:out value="${user.email}" />"
				class="form-control" name="email" aria-describedby="emailHelp"
				placeholder="Enter email"> <small id="emailHelp"
				class="form-text text-muted">We'll never share your email
				with anyone else.</small>
		</div>
		<div class="form-group">
			<label for="userName">First Name</label> <input type="text"
				value="<c:out value="${user.imie}" />" class="form-control"
				name="firstName" aria-describedby="emailHelp"
				placeholder="Enter your first name">
		</div>

		<div class="form-group">
			<label for="userName">Last Name</label> <input type="text"
				value="<c:out value="${user.nazwisko}" />" class="form-control"
				name="lastName" aria-describedby="emailHelp"
				placeholder="Enter your last name">
		</div>

		<div class="form-group">
			<label for="userName">Date of birth MM/dd/yyyy</label> <input
				type="text" class="form-control" id="datepicker"
				value="<c:out value="${user.dataUrodzenia}" />" name=dob>
		</div>

		<div class="form-group">
			<label for="userName">Phone</label> <input type="tel"
				value="<c:out value="${user.nazwisko}" />" class="form-control"
				name="telephone" aria-describedby="emailHelp"
				placeholder="Enter your phone number">
		</div>

		<div class="form-check">
			<label class="form-check-label"> <input type="checkbox"
				class="form-check-input"> Accept all rules :)
			</label>
		</div>
		<button type="submit" class="btn btn-primary">Submit</button>
	</form>
</body>
</html>