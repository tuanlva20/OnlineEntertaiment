let url=location.href;
let editHref={};

function openCity(evt, cityName) {
	var i, tabcontent, tablinks;
	tabcontent = document.getElementsByClassName("tabcontent");
	for (i = 0; i < tabcontent.length; i++) {
		tabcontent[i].style.display = "none";
	}
	tablinks = document.getElementsByClassName("tablinks");
	for (i = 0; i < tablinks.length; i++) {
		tablinks[i].className = tablinks[i].className.replace(" active", "");
	}
	document.getElementById(cityName).style.display = "block";
	evt.currentTarget.className += " active";
}

function deleteVideo(href,title) {
	if (confirm("Bạn muốn xóa video " + title+"??") == true) {
		$.ajax({
			url: '/ASSM-Nhom/admin/video?action=delete&href=' + href
		}).then(function(data) {
			window.location.href = "http://localhost:8181/ASSM-Nhom/admin/video?action=view";
		}).fail(function(error) {
			alert("Lỗi! Vui lòng thử lại!");
		});
	}
}

function confirmDelete(){
	if (confirm()) {}
}

function editVideo(href){
		window.location.href="http://localhost:8181/ASSM-Nhom/admin/video?action=edit&href="+href;
}

if (url=="http://localhost:8181/ASSM-Nhom/admin/video?action=view") {
	document.getElementById("listVideo").click();
}else{
	document.getElementById("detailVideo").click();
}

// function redirect(){
// 	window.location="http://localhost:8181/ASSM-Nhom/admin/video?action=view";
// }
//Tự động click DefaultOpen
//document.getElementById("listVideo").click();