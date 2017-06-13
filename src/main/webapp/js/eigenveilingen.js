function checkWidth(init) {
    /*If browser resized, check width again */
    if ($(window).width() > 500) {

        $('#collectionwrapper').addClass('offset-s2');
        $('#collectionwrapper').addClass('s9');
        $('#collectionwrapper').removeClass('s12');
        console.log(sessionStorage);
    } else {
        if (!init) {
            $('#collectionwrapper').removeClass('offset-s2');
            $('#collectionwrapper').removeClass('s9');
            $('#collectionwrapper').addClass('s12');
        }
    }
}

$(document).ready(function() {
    checkWidth(true);
    init();
    $(window).resize(function() {
        checkWidth(false);
    });

    $("#veilingcollection").delegate('a', 'click', function() {
        console.log("test");
        $('#veilingpopup').modal();

        $('#veilingpopup').modal('open');
    });
});



$("#itemCollection").delegate('a', 'click', function() {
    voorwerpnummer = $(this).data('voorwerpnummer');
    console.log(voorwerpnummer);
    loadModal(voorwerpnummer);
    $('#veilingpopup').modal();

    $('#veilingpopup').modal('open');

});

function init() {
    $.get("http://localhost:4711/onebid/restservices/voorwerp", (data) => {

            $(data).each(function(index) {
                $("#itemCollection").append('<a id="voorwerp' + this.voorwerpnummer + '"data-voorwerpnummer="' + this.voorwerpnummer + '"class="collection-item avatar"> ' +
                    '<span id="title" class="title">' + this.titel + '</span>' +
                    ' <p>de startprijs is ' + this.startprijs + '<p id="beschrijving" class="black-text">' + this.beschrijving + '</p>' +
                    '  </a>');


            });
        })
        .fail(() => {
            $("#itemCollection").append('<p id="voorwerpfail"> ' +
                '<h4 id="title" class="title">oeps er is iets fout gegaan</h4>' +
                ' <p id="beschrijving" class="black-text">er zijn geen veilingen gevonden</p>' +
                '  </p>');
        });

    console.log(window.sessionStorage.getItem("gebruikerID"));
    console.log(window.sessionStorage.getItem("kanverkopen"));
    console.log(window.sessionStorage.getItem("sessionToken"));
}



function loadModal(voorwerpnummer) {
    $("#biedingencollection").empty();
    $.get("http://localhost:4711/onebid/restservices/voorwerp/" + voorwerpnummer, (data) => {

        $(data).each(function(index) {

            $("#modalTitel").text(this.titel);
            $("#modalStartprijs").text("startprijs: € " + this.startprijs);
            $("#modalStartprijs").data('startprijs', this.startprijs);
            $("#modalBeschrijving").text(this.beschrijving);

        });

    });
    $.get("http://localhost:4711/onebid/restservices/bod/voorwerp/" + voorwerpnummer, (data) => {
        var count = Object.keys(data).length;
        if (count > 0) {
            console.log("testt");
            $(data).each(function(index) {

                $("#biedingencollection").append('<li id="bod' + this.bodID + '"class="collection-item"><span>' + this.gebruiker + '</span> € <span>' + this.bodBedrag + '</span>,-</li>');

            });
        } else {
            console.log("test");
            $("#biedingencollection").append('<li class="collection-item">er is nog niet op geboden . Jij kan het verschil maken</li>');
        }
    });
    sessionStorage.setItem("huidigItem", voorwerpnummer);
    $.get("http://localhost:4711/onebid/restservices/bod/voorwerp/hoogste/" + voorwerpnummer, (data) => {
        $(data).each(function(index) {
            sessionStorage.setItem("hoogste", this.bodBedrag);
            console.log(sessionStorage.getItem("hoogste"));
            hoogste = parseInt(sessionStorage.getItem("hoogste"));
            console.log(hoogste);
            $("#biedingencollection").data('hoogste', this.bodBedrag);
            console.log($("#biedingencollection").data('hoogste'));

        });

    });
}
//HIERBOVEN AllE SElECT DINGEN

//HIERONDER DE REST
