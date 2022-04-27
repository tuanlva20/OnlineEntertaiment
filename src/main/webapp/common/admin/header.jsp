<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<header class="topbar" data-navbarbg="skin5">
	<nav class="navbar top-navbar navbar-expand-md navbar-dark">
		<div class="navbar-header" data-logobg="skin6">

			<!-- Logo -->
			<a class="navbar-brand" href="/ASSM-Nhom/admin"> <b
				class="logo-icon"> <img
					src="<c:url value='/templates/admin/plugins/images/logo-icon.png'/>"
					alt="homepage" />
			</b> <span class="logo-text"> <img
					src="<c:url value='/img/Logo-text.png'/>" class="w-100"
					alt="homepage" />
			</span>
			</a> <a
				class="nav-toggler waves-effect waves-light text-dark d-block d-md-none"
				href="javascript:void(0)"><i class="ti-menu ti-close"></i></a>
		</div>

		<div class="navbar-collapse collapse" id="navbarSupportedContent"
			data-navbarbg="skin5">

			<ul class="navbar-nav ms-auto d-flex align-items-center">

				<!-- Search -->
				<li class=" in">
					<form role="search" class="app-search d-none d-md-block me-3">
						<input type="text" placeholder="Search..."
							class="form-control mt-0"> <a href="" class="active">
							<i class="fa fa-search"></i>
						</a>
					</form>
				</li>

				<!-- User profile and search -->
				<li><a class="profile-pic" href="#"> <img
						src="<c:url value='/templates/admin/plugins/images/users/varun.jpg'/>"
						alt="user-img" width="36" class="img-circle"><span
						class="text-white font-medium">ANH TUẤN</span></a></li>
			</ul>
		</div>
	</nav>
</header>