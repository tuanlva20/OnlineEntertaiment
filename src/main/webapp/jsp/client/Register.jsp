<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>
<style>
.navbar {
	margin-bottom: 0;
	border-radius: 0;
}
</style>
<title>DangKy</title>
</head>
<body>
	<%@include file="/common/headerAuth.jsp"%>
	<div class="container">
		<h2>Registration</h2>
		<form class="form-horizontal" action="/action_page.php">
			<div class="form-group">
				<label class="control-label col-sm-2" for="username">User
					Name:</label>
				<div class="col-sm-10">
					<input type="text" class="form-control" id="username"
						placeholder="Enter UserName" name="username">
				</div>
			</div>
			<div class="form-group">
				<label class="control-label col-sm-2" for="pwd">Password:</label>
				<div class="col-sm-10">
					<input type="password" class="form-control" id="pwd"
						placeholder="Enter password" name="password">
				</div>
			</div>
			<div class="form-group">
				<label class="control-label col-sm-2" for="fullName">Full
					Name:</label>
				<div class="col-sm-10">
					<input type="text" class="form-control" id="fullName"
						placeholder="Enter Full Name" name="fullname">
				</div>
			</div>
			<div class="form-group">
				<label class="control-label col-sm-2" for="email">Email:</label>
				<div class="col-sm-10">
					<input type="email" class="form-control" id="email"
						placeholder="Enter email" name="email">
				</div>
			</div>
			<div class="form-group">
				<div class="col-sm-offset-2 col-sm-10">
					<button type="submit" class="btn btn-default">Sign up</button>
				</div>
			</div>
		</form>
	</div>
</body>
</html>