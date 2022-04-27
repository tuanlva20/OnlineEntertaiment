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
<title>Đổi mật khẩu</title>
</head>
<body>
	<%@include file="/common/headerAuth.jsp"%>
	<div class="container">
		<h2>Change Password</h2>
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
				<label class="control-label col-sm-2" for="currentpwd">Current
					Password:</label>
				<div class="col-sm-10">
					<input type="password" class="form-control" id="currentpwd"
						placeholder="Enter Current Password" name="currentpwd">
				</div>
			</div>
			<div class="form-group">
				<label class="control-label col-sm-2" for="newpsw">New
					Password:</label>
				<div class="col-sm-10">
					<input type="password" class="form-control" id="newpsw"
						placeholder="Enter New Password" name="newpsw">
				</div>
			</div>
			<div class="form-group">
				<label class="control-label col-sm-2" for="confirm-new-psw">Confirm
					New Password:</label>
				<div class="col-sm-10">
					<input type="password" class="form-control" id="confirm-new-psw"
						placeholder="Enter Confirm New Password" name="confirm-new-psw">
				</div>
			</div>
			<div class="form-group">
				<div class="col-sm-offset-2 col-sm-10">
					<button type="submit" class="btn btn-default">Change</button>
				</div>
			</div>
		</form>
	</div>
</body>
</html>