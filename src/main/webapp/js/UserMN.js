let url=location.href;

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

if(url=="http://localhost:8181/ASSM-Nhom/admin/user/view"){
    document.getElementById('listUser').click();
}else{
    document.getElementById('detailUser').click();
}

//function editUser(uname,index) {
//    window.location.href="http://localhost:8181/ASSM-Nhom/admin/user/edit?uname="+uname+"&index="+index;
//    console.log("edit User!!");
//}

function deleteUser(uname){
    if (confirm("Bạn muốn xóa user "+uname+"??")==true) {
        $.ajax({
        url:"http://localhost:8181/ASSM-Nhom/admin/user/delete?uname="+uname
        }).then(function(data){
        window.location.href="http://localhost:8181/ASSM-Nhom/admin/user/view";
        }).fail(function(error){
        alert("Lỗi! Vui lòng thử lại!");
        });
    }
}

function openTable(){
    window.location.href="http://localhost:8181/ASSM-Nhom/admin/user/view";
}