<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="/common/admin/taglib.jsp"%>
<!DOCTYPE html>
<html dir="ltr" lang="en">

<head>
<%@include file="/common/admin/Head.jsp"%>
<title>User Manage</title>
<link rel="stylesheet" href="<c:url value='/css/VideoMN.css'/>">
</head>

<body>
	<!-- Main wrapper -->
	<div id="main-wrapper" data-layout="vertical" data-navbarbg="skin5"
		data-sidebartype="full" data-sidebar-position="absolute"
		data-header-position="absolute" data-boxed-layout="full">
		<%@include file="/common/admin/header.jsp"%>
		<%@include file="/common/admin/navLeft.jsp"%>

		<!-- Page wrapper  -->
		<div class="page-wrapper">
			<div class="page-breadcrumb bg-white">
				<div class="row align-items-center">
					<div class="col-lg-3 col-md-4 col-sm-4 col-xs-12">
						<h4 class="page-title text-uppercase">
							<i class="fas fa-user"></i>&nbsp;Quản lý người dùng
						</h4>
					</div>
				</div>
			</div>

			<!-- Container fluid  -->
			<div class="container-fluid p-0 bg-light">
				<div class="tab">
					<button class="tablinks" onclick="openCity(event, 'London')"
						id="detailUser">Chi Tiết tài khoản</button>
					<button class="tablinks" onclick="openCity(event, 'table')"
						id="listUser">
						<i class="fas fa-list"></i> Danh sách tài khoản
					</button>
				</div>
				<div id="London" class="tabcontent p-0">
					<div class="row m-0">
						<!-- Column -->
						<div class="col-lg-4 col-xlg-3 col-md-12 mt-3">
							<img src="<c:url value='/img/Image.jpeg'/>"
								class="m-auto d-block pb-3" style="width: 100%; height: 420px;"
								alt="Hinh">
						</div>
						<!-- Column -->
						<!-- Column -->
						<div class="col-lg-8 col-xlg-9 col-md-12 p-0 bg-white">
							<div class="card">
								<div class="card-body">
									<form class="form-horizontal form-material"
										action="/ASSM-Nhom/admin/user/" method="post">
										<div class="row">
											<div class="form-group mb-4 col-sm-6">
												<label class="col-md-12 p-0 h4">Tài khoản:</label>
												<div class="col-md-12 border-bottom p-0">
													<c:choose>
														<c:when test="${index>=0 }">
															<input type="text" placeholder="Nhập tài khoản"
																class="form-control p-0 border-0" name="username"
																value=${user.username } readonly>
														</c:when>
														<c:otherwise>
															<input type="text" placeholder="Nhập tài khoản"
																class="form-control p-0 border-0" name="username"
																value=${user.username }>
														</c:otherwise>
													</c:choose>
												</div>
											</div>
											<div class="form-group mb-4 col-sm-6">
												<label for="example-email" class="col-md-12 p-0 h4">Mật
													khẩu</label>
												<div class="col-md-12 border-bottom p-0">
													<input type="text" placeholder="Nhập Password"
														class="form-control p-0 border-0" name="password"
														value=${user.password }>
												</div>
											</div>
										</div>

										<div class="form-group mb-4">
											<label class="col-md-12 p-0 h4">Họ và tên</label>
											<div class="col-md-12 border-bottom p-0">
												<input type="text" placeholder="Nhập họ và tên"
													class="form-control p-0 border-0" name="fullname"
													value=${user.fullname }>
											</div>
										</div>
										<div class="form-group mb-4">
											<label class="col-md-12 p-0 h4">Email</label>
											<div class="col-md-12 border-bottom p-0">
												<input type="email" placeholder="Nhập email"
													class="form-control p-0 border-0" name="email"
													value=${user.email }>
											</div>
										</div>

										<div class="form-check form-check-inline">
											<input class="form-check-input" type="radio" name="admin"
												id="inlineRadio1" value="true"
												${user.admin ? "checked" : ""}> <label
												class="form-check-label" for="inlineRadio1">Admin</label>
										</div>
										<div class="form-check form-check-inline mb-2">
											<input class="form-check-input" type="radio" name="admin"
												id="inlineRadio2" value="false"
												${user.admin ? "" : "checked"}> <label
												class="form-check-label" for="inlineRadio2">Client</label>
										</div>

										<div class="form-group mb-4">
											<div class="col-sm-12">
												<c:if test="${message!=null }">
													<p>
														<em name="thongbao" class="text-danger fst-italic"><i
															class="fas fa-exclamation"></i> ${message }</em>
													</p>
												</c:if>
												<c:if test="${button==false}">
													<button name="createBtn" class="btn btn-success mx-1" formaction="create">
														<i class="fas fa-plus"></i> Tạo mới
													</button>
												</c:if>
												<c:if test="${button==true}">
													<button name="updateBtn" class="btn btn-success mx-1" formaction="update">
														<i class="fas fa-edit"></i> Cập nhật
													</button>
													<button name="deleteBtn" class="btn btn-success mx-1" formaction="delete">
														<i class="fas fa-trash"></i> Xóa
													</button>
													<button name="newBtn" class="btn btn-success mx-1" formaction="new">
														<i class="fas fa-file"></i> Thêm mới
													</button>
												</c:if>

											</div>
										</div>
									</form>
								</div>
							</div>
						</div>
						<!-- Column -->
					</div>
				</div>



				<div id="table" class="tabcontent p-1">
					<div class="table-responsive">
						<table class="table table-hover table-bordered mb-0 table-striped">
							<thead class="text-center">
								<tr>
									<th class="border-top-0 fw-bold bg-white">Username</th>
									<th class="border-top-0 fw-bold bg-white">Mật khẩu</th>
									<th class="border-top-0 fw-bold bg-white">Họ và tên</th>
									<th class="border-top-0 fw-bold bg-white">Email</th>
									<th class="border-top-0 fw-bold bg-white">Admin</th>
									<th class="border-top-0 fw-bold bg-white">Action</th>
								</tr>
							</thead>
							<tbody>
								<c:forEach var='item' items='${users}' varStatus="myIndex">
									<tr class="align-middle">
										<td class="fw-bold">${item.username}</td>
										<td>${item.password}</td>
										<td>${item.fullname}</td>
										<td>${item.email }</td>
										<td>${item.admin }</td>
										<td class="text-center">
											<button name="edit" type="button" class="btn btn-success"
												onclick="editUser('${item.username}','${myIndex.index}')">Edit</button>
											<button type="button" class="btn btn-danger"
												onclick="deleteUser('${item.username}')">Delete</button>
										</td>
									</tr>
								</c:forEach>
							</tbody>
						</table>
					</div>
				</div>
			</div>
		</div>
	</div>
	<%@include file="/common/admin/footer.jsp"%>
	<%@include file="/common/admin/Script.jsp"%>
	<script src="<c:url value='/js/UserMN.js'/>"></script>
	<script>
	function editUser(uname,index) {
	    window.location.href="http://localhost:8181/ASSM-Nhom/admin/user/edit?uname="+uname+"&index="+index;
	    console.log("edit User!!");
	}
	</script>
</body>

</html>