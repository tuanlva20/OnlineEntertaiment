<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>
<title>Đăng nhập</title>
<style>
.checkbox {
	position: relative;
	left: 195px;
}

.navbar {
	margin-bottom: 0;
	border-radius: 0;
}
</style>
</head>
<body>
	<%@include file="/common/headerAuth.jsp" %>
	<div class="container w-50">
		<h2>Login</h2>
		<form class="form-horizontal" action="/ASSM-Nhom/client/login" method="post">
			<div class="form-group">
				<label class="control-label col-sm-2" for="username">User
					Name:</label>
				<div class="col-sm-10">
					<input type="text" class="form-control" id="username"
						placeholder="Enter User Name" name="username">
				</div>
			</div>
			<div class="form-group">
				<label class="control-label col-sm-2" for="pwd">Password:</label>
				<div class="col-sm-10">
					<input type="password" class="form-control" id="pwd"
						placeholder="Enter password" name="password">
				</div>
			</div>
			<br>
			<span class="col-sm-offset-2 col-sm-10" style="color: red;font-style: italic;">${message }</span>
			<br>
			<div class="form-group">
				<div class="col-sm-offset-2 col-sm-10">
					<button type="submit" class="btn btn-default" name="loginBtn">Login</button>
				</div>
			</div>
		</form>
	</div>
</body>
</html>