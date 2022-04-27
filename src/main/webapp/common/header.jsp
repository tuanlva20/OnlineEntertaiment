<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!-- Nav Bar -->
<nav class="navbar navbar-inverse"
	style="height: 60px; padding-top: 5px; margin-bottom: 0px;">
	<div class="container-fluid">
		<div class="navbar-header">
			<button type="button" class="navbar-toggle" data-toggle="collapse"
				data-target="#myNavbar">
				<span class="icon-bar"></span> <span class="icon-bar"></span> <span
					class="icon-bar"></span>
			</button>
			<a class="navbar-brand" href="#" style="padding-top: 0px;"><img
				src="https://2.pik.vn/20224be01c1b-6cb5-410f-9e43-1ef3714336c8.png"
				alt="Logo"></a>
		</div>
		<div class="collapse navbar-collapse" id="myNavbar">
			<c:choose>
				<c:when test="${not empty sessionScope.currentUser}">
					<ul class="nav navbar-nav">
						<li ><a href="/ASSM-Nhom/client/home">Trang
								chủ</a></li>
						<li><a href="/ASSM-Nhom/client/favorite">Yêu thích</a></li>
						<li><a href="/ASSM-Nhom/client/changepassword">Đổi mật khẩu  </a></li>
						<li><a href="">Welcome, ${sessionScope.currentUser.username}</a></li>
					</ul>
					<ul class="nav navbar-nav navbar-right">
						<li><a href="/ASSM-Nhom/client/logout"><span
								class="glyphicon glyphicon-log-out"></span> Logout</a></li>
					</ul>
				</c:when>
				<c:otherwise>
					<ul class="nav navbar-nav">
						<li class="active"><a href="/client/home">Trang
								chủ</a></li>
						<li><a href="register">Đăng ký</a></li>
						<li><a href="forgotpass">Quên mật khẩu</a></li>
					</ul>
					<ul class="nav navbar-nav navbar-right">
						<li><a href="/ASSM-Nhom/client/login"><span
								class="glyphicon glyphicon-log-in"></span> Login</a></li>
					</ul>
				</c:otherwise>
			</c:choose>




			<!-- Search -->
			<form class="navbar-form navbar-right" action="#">
				<div class="input-group">
					<input type="text" class="form-control" placeholder="Search">
					<div class="input-group-btn">
						<button class="btn btn-primary" type="submit">
							<i class="glyphicon glyphicon-search"></i>
						</button>
					</div>
				</div>
			</form>

		</div>
	</div>
</nav>