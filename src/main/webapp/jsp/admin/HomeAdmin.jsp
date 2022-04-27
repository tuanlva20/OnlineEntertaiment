<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="/common/admin/taglib.jsp" %>
<!DOCTYPE html>
<html dir="ltr" lang="en">

<head>
<%@include file="/common/admin/Head.jsp" %>
<title>Home Admin</title>
</head>

<body ng-app="myApp" ng-controller="myCtrl">
	<!-- Main wrapper -->
	<div id="main-wrapper" data-layout="vertical" data-navbarbg="skin5"
		data-sidebartype="full" data-sidebar-position="absolute"
		data-header-position="absolute" data-boxed-layout="full">
		<%@include file="/common/admin/header.jsp" %>
		<%@include file="/common/admin/navLeft.jsp" %>

		<!-- Page wrapper  -->
		<div class="page-wrapper">
			<div class="page-breadcrumb bg-white">
				<div class="row align-items-center">
					<div class="col-lg-3 col-md-4 col-sm-4 col-xs-12">
						<h4 class="page-title text-uppercase">
							<i class="fas fa-home"></i>&nbsp;Trang chủ
						</h4>
					</div>
				</div>
			</div>

			<!-- Container fluid  -->
			<div class="container-fluid">
				<!-- Three charts -->
				<div class="row justify-content-center">
					<div class="col-lg-4 col-md-12">
						<div class="white-box analytics-info">
							<h3 class="box-title">Tổng Lượt Đăng Nhập</h3>
							<ul class="list-inline two-part d-flex align-items-center mb-0">
								<li>
									<div id="sparklinedash">
										<canvas width="67" height="30"
											style="display: inline-block; width: 67px; height: 30px; vertical-align: top;"></canvas>
									</div>
								</li>
								<li class="ms-auto"><span class="counter text-success">659</span></li>
							</ul>
						</div>
					</div>
					<div class="col-lg-4 col-md-12">
						<div class="white-box analytics-info">
							<h3 class="box-title">Tổng Tài Khoản Đăng Ký</h3>
							<ul class="list-inline two-part d-flex align-items-center mb-0">
								<li>
									<div id="sparklinedash2">
										<canvas width="67" height="30"
											style="display: inline-block; width: 67px; height: 30px; vertical-align: top;"></canvas>
									</div>
								</li>
								<li class="ms-auto"><span class="counter text-purple">869</span></li>
							</ul>
						</div>
					</div>
					<div class="col-lg-4 col-md-12">
						<div class="white-box analytics-info">
							<h3 class="box-title">Tổng Số Video Đang Công Chiếu</h3>
							<ul class="list-inline two-part d-flex align-items-center mb-0">
								<li>
									<div id="sparklinedash3">
										<canvas width="67" height="30"
											style="display: inline-block; width: 67px; height: 30px; vertical-align: top;"></canvas>
									</div>
								</li>
								<li class="ms-auto"><span class="counter text-info">911</span>
								</li>
							</ul>
						</div>
					</div>
				</div>

				<!-- RECENT SALES -->
				<div class="row">
					<div class="col-md-12 col-lg-12 col-sm-12">
						<div class="white-box">
							<div class="d-md-flex mb-3">
								<h3 class="box-title mb-0 text-uppercase">Danh sách số lượt
									thích theo video</h3>
								<div class="col-md-3 col-sm-4 col-xs-6 ms-auto">
									<select class="form-select shadow-none row border-top">
										<option>March 2021</option>
										<option>April 2021</option>
										<option>May 2021</option>
										<option>June 2021</option>
										<option>July 2021</option>
									</select>
								</div>
							</div>
							<div class="table-responsive">
								<table class="table table-striped table-hover table-bordered">
									<thead>
										<tr>
											<th class="border-top-0 fw-bold">Title</th>
											<th class="border-top-0 fw-bold">Href</th>
											<th class="border-top-0 fw-bold">Total Like</th>
										</tr>
									</thead>
									<tbody>
										<c:forEach var='item' items='${videos}'>
											<tr>
												<td>${item.title}</td>
												<td><a href="video?action=watch&id=${item.href}"></a>${item.href}</td>
												<td>${item.totalLike}</td>
											</tr>
										</c:forEach>
									</tbody>
								</table>
							</div>
						</div>
					</div>
				</div>

				<!--List user liked video by videoHref -->
				<div class="row">
					<div class="col-md-12 col-lg-12 col-sm-12">
						<div class="white-box">
							<div class="d-md-flex mb-3">
								<h3 class="box-title mb-0 text-uppercase">Danh sách người
									dùng thích Video</h3>
								<div class="col-md-3 col-sm-4 col-xs-6 ms-auto">
									<select id="selectVideo" onchange="getSelectedVideo();"
										class="form-select shadow-none row border-top">
										<option selected disabled>Chọn tên phim</option>
										<c:forEach var="item" items="${videos}">
											<c:choose>
												<c:when test="${item.href==videoHref}">
													<option selected value="${item.href}">${item.title}</option>
												</c:when>
												<c:otherwise>
													<option value="${item.href}">${item.title}</option>
												</c:otherwise>
											</c:choose>
										</c:forEach>
									</select>
								</div>

							</div>
							<div class="table-responsive">
								<table id="table2"
									class="table table-striped table-hover table-bordered">
									<thead>
										<tr>
											<th class="border-top-0 fw-bold">Username</th>
											<th class="border-top-0 fw-bold">Fullname</th>
											<th class="border-top-0 fw-bold">Gmail</th>
										</tr>
									</thead>
									<tbody>
										<c:forEach var='user' items='${users }'>
											<tr>
												<td>${user.username}</td>
												<td>${user.fullname}</td>
												<td>${user.email}</td>
											</tr>
										</c:forEach>
									</tbody>
								</table>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
		<%@include file="/common/admin/footer.jsp" %>
		<%@include file="/common/admin/Script.jsp" %>
</body>
</html>