<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="/common/admin/taglib.jsp"%>
<!DOCTYPE html>
<html dir="ltr" lang="en">

<head>
<%@include file="/common/admin/Head.jsp"%>
<title>Video Manage</title>
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
							<i class="fas fa-video"></i>&nbsp;Quản lý video
						</h4>
					</div>
				</div>
			</div>

			<!-- Container fluid  -->
			<div class="container-fluid p-0 bg-light">
				<div class="tab">
					<button class="tablinks" onclick="openCity(event, 'London')"
						id="detailVideo">Chi Tiết Video</button>
					<button class="tablinks"
						onclick="openCity(event, 'table'); redirect();" id="listVideo">Danh
						sách Video</button>
				</div>
				<div id="London" class="tabcontent p-0">
					<div class="row m-0">
						<!-- Column -->
						<div class="col-lg-4 col-xlg-3 col-md-12 mt-3"
							style="padding-top: 100px;">
							<img
								src="http://img.youtube.com/vi/${video.href }/maxresdefault.jpg"
								class="m-auto d-block" style="width: 100%" alt="Hinh">
						</div>
						<!-- Column -->
						<!-- Column -->
						<div class="col-lg-8 col-xlg-9 col-md-12 p-0 bg-white">
							<div class="card">
								<div class="card-body">
									<form class="form-horizontal form-material"
										action="/ASSM-Nhom/admin/" method="post">
										<div class="form-group mb-4">
											<label class="col-md-12 p-0 h4">Video ID</label>
											<div class="col-md-12 border-bottom p-0">
												<input name="id" type="text" placeholder="Nhập ID Video"
													class="form-control p-0 border-0" value=${video.id }>
											</div>
										</div>
										<div class="form-group mb-4">
											<label for="example-email" class="col-md-12 p-0 h4">Tiêu
												đề</label>
											<div class="col-md-12 border-bottom p-0">
												<input id="example-email" type="text"
													placeholder="Nhập tiêu đề"
													class="form-control p-0 border-0" name="title"
													value=${video.title }>
											</div>
										</div>
										<div class="form-group mb-4">
											<label class="col-md-12 p-0 h4">Href</label>
											<div class="col-md-12 border-bottom p-0">
												<input name="href" type="text" placeholder="Nhập Href Video"
													class="form-control p-0 border-0" value=${video.href }>
											</div>
										</div>
										<div class="form-group mb-4">
											<label class="col-md-12 p-0 h4">View</label>
											<div class="col-md-12 border-bottom p-0">
												<input name="view" type="number"
													placeholder="Nhập số lượt view của video"
													class="form-control p-0 border-0" value=${video.views }>
											</div>
										</div>
										<div class="form-group mb-4">
											<label class="col-md-12 p-0 h4">Description</label>
											<div class="col-md-12 border-bottom p-0">
												<input name="description" type="text"
													placeholder="Nhập Description Video"
													class="form-control p-0 border-0" value="${video.description }">
											</div>
										</div>
										<div class="form-group mb-4">
											<div class="form-check form-check-inline">
												<input class="form-check-input" type="radio" name="active"
													id="active" value="true"
													${video.active ? "checked" : ""}> <label
													class="form-check-label" for="inlineRadio1">Active</label>
											</div>
											<div class="form-check form-check-inline">
												<input class="form-check-input" type="radio" name="active"
													id="nonactive" value="false"
													${video.active ? "" : "checked"}> <label
													class="form-check-label" for="inlineRadio2">NonActive</label>
											</div>
										</div>

										<div class="form-group mb-4">
											<div class="col-sm-12">
												<c:if test="${message!=null }">
													<div class="alert alert-danger"
														role="alert">
														<i class="fas fa-exclamation"></i> ${message }
													</div>
												</c:if>
												<button name="createBtn" class="btn btn-success me-1"
													formaction="video?action=create">Thêm mới</button>
												<button name="deleteBtn" class="btn btn-danger me-1"
													formaction="video?action=delete">Xóa</button>
												<button name="updateBtn" class="btn btn-success me-1"
													formaction="video?action=update">Cập nhật</button>
												<button name="newBtn" class="btn btn-success"
													formaction="video?action=new">Tạo mới</button>
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
						<table class="table table-hover table-bordered mb-0">
							<thead class="text-center">
								<tr>
									<th class="border-top-0 fw-bold bg-white">Title</th>
									<th class="border-top-0 fw-bold bg-white">Href</th>
									<th class="border-top-0 fw-bold bg-white">Description</th>
									<th class="border-top-0 fw-bold bg-white">Poster</th>
									<th class="border-top-0 fw-bold bg-white">Action</th>
								</tr>
							</thead>
							<tbody>
								<c:forEach var='item' items='${videos}'>
									<tr class="align-middle">
										<td class="fw-bold">${item.title}</td>
										<td><a href="video?action=watch&id=${item.href}"></a>${item.href}</td>
										<td>${item.description}</td>
										<td class="text-center p-1"><img
											src="https://img.youtube.com/vi/${item.href}/mqdefault.jpg"
											alt="Hinh" style="width: 150px;"></td>
										<td>
											<button name="editBtn" type="button" onclick="editVideo('${item.href }')"
												class="btn btn-success">Edit</button>
											<button type="button"
												onclick="deleteVideo('${item.href}','${item.title }')"
												class="btn btn-danger">Delete</button>
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
</body>
<script src="<c:url value='/js/VideoMN.js'/>"></script>
<script>
    function redirect(){
	window.location="http://localhost:8181/ASSM-Nhom/admin/video?action=view";
}
</script>
</html>
