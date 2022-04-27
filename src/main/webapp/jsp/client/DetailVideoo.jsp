<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<!DOCTYPE html>
<html lang="en">

<head>
<title>Chi tiết</title>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>
<link rel="stylesheet" href="ASSM-Nhom/css/DetailVideo.css">
</head>

<body>
	<!-- Nav Bar -->
	<%@include file="/common/header.jsp"%>
	<div class="container" style="width: 1500px;">
		<div class="row">

			<!-- 	Video -->
			<div class="col-lg-9">
				<section class="py-5">
					<div class="container px-3 px-lg-5 my-5"
						style="margin: 0px; padding: 0px;">
						<div class="row gx-4 gx-lg-5 align-items-center">
							<div class="col-md-9">
								<iframe width="1000" height="550"
									src="https://www.youtube.com/embed/xypzmu5mMPY"
									title="Muộn rồi mà sao còn - Sơn Tùng Mtp" frameborder="0"
									allow=" autoplay; clipboard-write;  gyroscope; picture-in-picture"
									allowfullscreen style="margin-top: 10px;"></iframe>
								<h1 class="display-5 fw-bolder">Muộn rồi mà sao còn - Sơn Tùng Mtp</h1>
								<div class="fs-5 mb-4">
									<span><span style="font-weight: bold;">3000000</span>
										lượt xem - Đã công chiếu vào 28 thg 11, 2021</span>
								</div>
								<p class="lead" style="margin-top: 15px;">
									Mô tả:<br>
								<hr>
								Singer: Dinh Dung <br> Composer: Dinh Dung <br> Music
								Executive: Le Cuong <br> Mix & Master: Kumix <br>
								Music Arranger: Trung Ngon <br>
								<hr>
								</p>
								<div class="d-flex">
								</div>
							</div>
						</div>
					</div>
				</section>
			</div>

			<!-- List_Video -->
			<div class="col-lg-3">
				<c:forEach var="video" items="${videos}">
					<div class="thumbnail">
						<img
							src="https://img.youtube.com/vi/${video.href }/mqdefault.jpg"
							alt="Paris" width="400" height="300">
						<p style="text-align: center; margin-top: 10px;">
							<strong style="font-size: 15px;">${video.title }</strong>
						</p>
					</div>
				</c:forEach>

				<div class="badge bg-danger text-white position-absolute"
					style="top: 0.5rem; right: 0.5rem; background-color: red;">Hot</div>
				<div class="thumbnail">
					<img
						src="https://2.pik.vn/20227b5df822-7617-482e-977a-0f85413aa1a1.jpg"
						alt="Paris" width="400" height="300">
					<p style="text-align: center; margin-top: 10px;">
						<strong style="font-size: 20px;">Yummy</strong>
					</p>
				</div>
			</div>
		</div>

		<hr>
	</div>

	<%@include file="/common/footer.jsp"%>

</body>

</html>