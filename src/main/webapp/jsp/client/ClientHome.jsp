	<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="en">

<head>
<title>Trang chủ</title>
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
	<div id="myCarousel" class="carousel slide" data-ride="carousel">
		<!-- Indicators -->
		<ol class="carousel-indicators">
			<li data-target="#myCarousel" data-slide-to="0" class="active"></li>
			<li data-target="#myCarousel" data-slide-to="1"></li>
			<li data-target="#myCarousel" data-slide-to="2"></li>
		</ol>

		<!-- Wrapper for slides -->
		<div class="carousel-inner" role="listbox">
			<div class="item active">
				<img
					src="https://2.pik.vn/2022e21fbd8d-899a-437e-b0ed-47a585437930.png"
					alt="Image" width="1700" height="400">
				<div class="carousel-caption">
					<!-- <h3>Sell $</h3>
                    <p>Money Money.</p> -->
				</div>
			</div>

			<div class="item">
				<img
					src="https://2.pik.vn/2022ef0d4dba-54ee-4d5b-b76e-2a2ec6e61365.png"
					alt="Image" width="1700" height="400">
				<div class="carousel-caption">
					<!-- <h3>More Sell $</h3>
                    <p>Lorem ipsum...</p> -->
				</div>
			</div>

			<div class="item">
				<img
					src="https://2.pik.vn/20227b54e4a3-e742-41a2-b932-7c72293836e9.jpg"
					alt="Image" width="1700" height="400">
				<div class="carousel-caption">
					<!-- <h3>More Sell $</h3>
                    <p>Lorem ipsum...</p> -->
				</div>
			</div>
		</div>

		<!-- Left and right controls -->
		<a class="left carousel-control" href="#myCarousel" role="button"
			data-slide="prev"> <span class="glyphicon glyphicon-chevron-left"
			aria-hidden="true"></span> <span class="sr-only">Previous</span>
		</a> <a class="right carousel-control" href="#myCarousel" role="button"
			data-slide="next"> <span
			class="glyphicon glyphicon-chevron-right" aria-hidden="true"></span>
			<span class="sr-only">Next</span>
		</a>
	</div>

	<div class="container text-center">
		<h2>Video nổi bật</h2>
		<br>
		<div class="row">
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
								<img
									src="https://2.pik.vn/2022d48ebff1-ffa0-4f66-b03b-aa107d523256.png">
								1020
							</button>

							<button type="button" class="btn btn-primary btn-like-share">
								<img src="/ASSM-Nhom/img/icons8-view-20.png" alt="Hinh">
								${video.views}
							</button>
						</div>

					</div>
				</div>
			</c:forEach>

		</div>
	</div>

	<div class="container-fluid bg-3 text-center">
		<ul class="pagination">
			<c:if test="${currentPage == 1}">
				<li><a href="#" disabled>Previous</a></li>
			</c:if>
			<c:if test="${currentPage > 1}">
				<li><a href="index?page=${currentPage - 1}">Previous</a></li>
			</c:if>
			<c:forEach varStatus="i" begin="1" end="${maxPage }">
				<li class="${currentPage == i.index ? 'active' : ''}"><a
					href="index?page=${i.index}">${i.index}</a></li>
			</c:forEach>
			<c:if test="${currentPage == maxPage}">
				<li><a href="#" disabled>Next Page</a></li>
			</c:if>
			<c:if test="${currentPage < maxPage}">
				<li><a href="home?page=${currentPage + 1}">Next Page</a></li>
			</c:if>
		</ul>
	</div>
	<br>
	<%@include file="/common/footer.jsp"%>
	<script
		src="https://ajax.googleapis.com/ajax/libs/angularjs/1.7.9/angular.min.js"></script>
	<script src="javacript/Bai3.js"></script>

</body>
</html>