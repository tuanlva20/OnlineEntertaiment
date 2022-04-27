<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<html>
<head>
	<meta charset="UTF-8">
	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">

	<title>Quên mật khẩu</title>
	<style >
		.navbar {
      margin-bottom: 0;
      border-radius: 0;
   	 }
	</style>
</head>
<body>
	<%@include file="/common/headerAuth.jsp" %>
	<div class="container">
	  	<h2>Forgot Password</h2>
	  	<form class="form-horizontal" action="/Assignment1/QuenMKServlet" method="get">
	    	<div class="form-group">
		      	<label class="control-label col-sm-2" for="email">Email:</label>
		      	<div class="col-sm-10">
		        	<input type="email" class="form-control" id="email" placeholder="Enter email" name="email">
		      	</div>
	    	</div>
		    <div class="form-group">
			    <label class="control-label col-sm-2" for="pwd">Password:</label>
			    <div class="col-sm-10">          
			      	<input type="password" class="form-control" id="pwd" placeholder="Enter password" name="pwd">
			    </div>
		    </div>
		    <div class="form-group">        
		      	<div class="col-sm-offset-2 col-sm-10">
		        	<button class="btn btn-default" type="submit">Retrieve</button>
		      	</div>
		    </div>
	  	</form>
	</div>
</body>
</html>
