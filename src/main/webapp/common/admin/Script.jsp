<!-- All Jquery -->
<script
	src="https://ajax.googleapis.com/ajax/libs/angularjs/1.8.2/angular.min.js"></script>
<script
	src="<c:url value='/templates/admin/plugins/bower_components/jquery/dist/jquery.min.js'/>"></script>
<script src="bootstrap/dist/js/bootstrap.bundle.min.js"></script>
<script src="<c:url value='/js/app-style-switcher.js'/>"></script>
<script
	src="<c:url value='/templates/admin/plugins/bower_components/jquery-sparkline/jquery.sparkline.min.js'/>"></script>
<script src="<c:url value='/js/waves.js'/>"></script>
<script src="<c:url value='/js/sidebarmenu.js'/>"></script>
<script src="<c:url value='/js/custom.js'/>"></script>
<script
	src="<c:url value='/templates/admin/plugins/bower_components/chartist/dist/chartist.min.js'/>"></script>
<script
	src="<c:url value='/templates/admin/plugins/bower_components/chartist-plugin-tooltips/dist/chartist-plugin-tooltip.min.js'/>"></script>
<script src="<c:url value='/js/pages/dashboards/dashboard1.js'/>"></script>

<script>
	/* 		${'#selectVideo'}.ready(function () {
	 console.log('1111');
	 var videoHref=$(this).value();
	 $.ajax({
	 url:'admin/favorites?href='+videoHref,
	 type: 'GET',
	 contentType: 'application/json'    
	 }).done(function(data){
	 $('#table2').DataTable({
	 destroy:true,
	 "paging":true,
	 "lengthChange":false,
	 "searching":false,
	 "ordering":true,
	 "info":true,
	 "autoWidth":false,
	 "responsive":true,
	 "aaData":data,
	 "columns":[
	 {"data":"username"},
	 {"data":"fullname"},
	 {"data":"email"}
	 ]
	 });
	 });
	 }); */

	function getSelectedVideo() {
		var titleVideo = document.getElementById("selectVideo").value;
		var redirect = "/ASSM-Nhom/admin/favorites?href=" + titleVideo;
		window.location = redirect;
	}
</script>