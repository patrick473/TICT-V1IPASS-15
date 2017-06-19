$(document).ready(function() {
    checkWidth(true);
    loadGegevens();
      $(".prepage").addClass('hide');
    $(window).resize(function() {
        checkWidth(false);
    });
});

    function checkWidth(init){
        /*If browser resized, check width again */
        if ($(window).width() > 500) {

            $('#collectionwrapper').addClass('offset-s2');
            $('#collectionwrapper').addClass('s9');
            $('#collectionwrapper').removeClass('s12');
            console.log(sessionStorage);
        }
        else {
            if (!init) {
                $('#collectionwrapper').removeClass('offset-s2');
                $('#collectionwrapper').removeClass('s9');
                $('#collectionwrapper').addClass('s12');
            }
        }
    }

function loadGegevens(){
    $.ajax({
            url: "restservices/gebruiker/" + sessionStorage.getItem("gebruikerID"),
            type: 'GET',
            beforeSend: function(xhr) {
                var token = window.sessionStorage.getItem("sessionToken");
                xhr.setRequestHeader('Authorization', 'Bearer ' + token);
            }
        })
        .done(function(data) {
            $(data).each(function(index) {
                $("#gebruikersnaam").text(this.gebruikersnaam);
                $("#voornaam").text(this.voornaam);
                $("#achternaam").text(this.achternaam);
                $("#adres").text(this.adres);
                $("#postcode").text(this.postcode);
                $("#plaatsnaam").text(this.plaatsnaam);
                $("#land").text(this.land);
                $("#geboortedag").text(this.geboortedag);
                $("#telefoonnummer").text('0'+this.telefoonnummer);
                $("#email").text(this.email);
                console.log("test");
            });


        })
        .fail(function() {
            console.log("error");
        })
        .always(function() {
            console.log("complete");
        });
}
