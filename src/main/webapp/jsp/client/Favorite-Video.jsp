<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="en">

<head>
<title>Yêu thích</title>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>
<link rel="stylesheet" href="ASSM-Nhom/css/ClientHome.css">
</head>

<body>
	<%@include file="/common/header.jsp"%>
	<form action="/ASSM-Nhom/client" method="post">
		<div class="container text-center">
			<h2>Liked Video</h2>
			<br>
			<div class="row">
				<div class="col-sm-4">
						<div class="thumbnail">
							<a
								href="<c:url value='/client/detailll'/>"><img
								src="https://img.youtube.com/vi/xypzmu5mMPY/mqdefault.jpg"
								alt="Paris" width="400" height="300"></a>;
							<p style="text-align: center; margin-top: 10px;">
								<strong style="font-size: 20px;">Muộn rồi mà sao còn -
								Sơn Tùng Mtp</strong>
							</p>
							<div style="text-align: right; padding: 10px;">
								<button type="button" class="btn btn-success btn-like-share" formaction="/client/unlike">
									<img src="/ASSM-Nhom/img/Unlike-20.png"> Unlike
								</button>

								<button type="button" class="btn btn-primary btn-like-share">
									<img src="/ASSM-Nhom/img/Share-20.png" alt="Hinh" formaction=""> Share
								</button>
							</div>

						</div>
					</div>
				<c:forEach var="video" items="${videos}">
					<div class="col-sm-4">
						<div class="thumbnail">
							<a
								href="<c:url value='/client/detail?action=watch&id=${video.href}'/>"><img
								src="https://img.youtube.com/vi/${video.href}/mqdefault.jpg"
								alt="Paris" width="400" height="300"></a>;
							<p style="text-align: center; margin-top: 10px;">
								<strong style="font-size: 20px;">${video.title }</strong>
							</p>
							<div style="text-align: right; padding: 10px;">
								<button type="button" class="btn btn-success btn-like-share">
									<img src="/ASSM-Nhom/img/Unlike-20.png" formaction="/client/unlike"> Unlike
								</button>

								<button type="button" class="btn btn-primary btn-like-share">
									<img src="/ASSM-Nhom/img/Share-20.png" alt="Hinh" formaction="/client/share"> Share
								</button>
							</div>

						</div>
					</div>
				</c:forEach>

			</div>
		</div>
	</form>
	
	<div class="container-fluid bg-3 text-center">
		<ul class="pagination">
			<li><a href="#">Đầu</a></li>
			<li class="active"><a href="#">1</a></li>
			<li><a href="">2</a></li>
		</ul>
	</div>
	<br>
	<%@include file="/common/footer.jsp"%>
	<script
		src="https://ajax.googleapis.com/ajax/libs/angularjs/1.7.9/angular.min.js"></script>
	<script src="javacript/Bai3.js"></script>

</body>
</html>