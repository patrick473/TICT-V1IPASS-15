$(document).ready(function() {
    checkWidth(true);
    loadGegevens();
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
                $("#adres").val(this.adres);
                $("#postcode").val(this.postcode);
                $("#plaatsnaam").val(this.plaatsnaam);
                $("#land").val(this.land);
                $("#email").val(this.email);
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
