

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
$("#secondRubriekenCollection").delegate('a', 'click', function() {
       rubriek = $(this).data("rubrieknummer");
       $("#itemCollection").empty();
    $.get("http://localhost:4711/onebid/restservices/voorwerp/rubriek/"+rubriek, (data) => {

            $(data).each(function(index) {
                $("#itemCollection").append('<a id="voorwerp'+this.voorwerpnummer+ '"data-voorwerpnummer="'+this.voorwerpnummer+'"class="collection-item avatar"> '+
                  '<span id="title" class="title">'+this.titel+'</span>'+
                ' <p>de startprijs is '+this.startprijs+'<p id="beschrijving" class="black-text">'+this.beschrijving+'</p>'+
                '  </a>');


            });
        })
        .fail( ()=>{
            $("#itemCollection").append('<p id="voorwerpfail"> '+
              '<h4 id="title" class="title">oeps er is iets fout gegaan</h4>'+
            ' <p id="beschrijving" class="black-text">er zijn geen veilingen gevonden</p>'+
            '  </p>');
        });
        $("#itemCollection").removeClass('hide');
        $('#initRubriekenCollection').addClass('hide');
        $('#secondRubriekenCollection').addClass('hide');
});

$("#itemCollection").delegate('a', 'click', function() {
    voorwerpnummer = $(this).data('voorwerpnummer');
    console.log(voorwerpnummer);
    $.get("http://localhost:4711/onebid/restservices/voorwerp/"+voorwerpnummer, (data) => {
        
        $(data).each(function(index) {

        $("#modalTitel").text(this.titel);
        $("#modalStartprijs").text("startprijs: € "+ this.startprijs);
        $("#modalStartprijs").data('startprijs', this.startprijs);
        $("#modalBeschrijving").text(this.beschrijving);

})

    });
    $('#veilingpopup').modal();

 $('#veilingpopup').modal('open');

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

function showUpperhideUnder(){
    $("#itemCollection").addClass('hide');
    $("#initRubriekenCollection").removeClass('hide');
    $("#secondRubriekenCollection").addClass('hide');
    $("#secondRubriekenCollection").empty();
}
