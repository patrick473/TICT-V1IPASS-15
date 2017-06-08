

$(document).ready(function() {
    checkWidth(true);
    loadRubriekenUpper();

    $(window).resize(function() {
        checkWidth(false);
    });

$("#initRubriekenCollection").delegate('a', 'click', function() {
       rubriek = $(this).data("rubrieknummer");
    $.get("http://localhost:4711/onebid/restservices/rubriek/onder/"+rubriek, (data) => {

            $(data).each(function(index) {
                $("#secondRubriekenCollection").append('<a href="#!"  class="collection-item" data-rubrieknummer="' + this.rubrieknummer + '" id="rubriek' + this.rubrieknummer + '">' + this.rubrieknaam + '</a>');


            });
            $('#initRubriekenCollection').addClass('hide');
            $('#secondRubriekenCollection').removeClass('hide');
        });
});


$("#returnbutton").click(function(event) {
    showUpperhideUnder();
});
});


function checkWidth(init){
    /*If browser resized, check width again */
    if ($(window).width() > 500) {

        $('#collectionwrapper').addClass('offset-s2');
        $('#collectionwrapper').addClass('s9');
        $('#collectionwrapper').removeClass('s12');

    }
    else {
        if (!init) {
            $('#collectionwrapper').removeClass('offset-s2');
            $('#collectionwrapper').removeClass('s9');
            $('#collectionwrapper').addClass('s12');
        }
    }
}
function loadRubriekenUpper(){
    $.get("http://localhost:4711/onebid/restservices/rubriek/bovenste", (data) => {

            $(data).each(function(index) {
                $("#initRubriekenCollection").append('<a href="#!"  class="collection-item" data-rubrieknummer="' + this.rubrieknummer + '" id="rubriek' + this.rubrieknummer + '">' + this.rubrieknaam + '</a>');


            });
        });
}
function loadRubriekenUnder(){

}

function showUpperhideUnder(){
    $("#initRubriekenCollection").removeClass('hide');
    $("#secondRubriekenCollection").addClass('hide');
    $("#secondRubriekenCollection").empty();
}
