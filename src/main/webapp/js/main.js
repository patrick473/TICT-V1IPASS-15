$(document).ready(function() {
    $(".sideNavDropOut").sideNav();
$(".username").html(sessionStorage.getItem("gebruikersnaam"));
var v = (sessionStorage.getItem('kanverkopen')=='true');
if(v){
    console.log("gebruiker kan verkopen");
}
else {
    console.log("gebruiker kan niet verkopen");
    $(".verkoperItem").addClass('hide');
}

});
