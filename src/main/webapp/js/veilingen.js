function checkWidth(init){
    /*If browser resized, check width again */
    if ($(window).width() > 500) {

        $('#collectionwrapper').addClass('offset-s2');
    }
    else {
        if (!init) {
            $('#collectionwrapper').removeClass('offset-s2');
        }
    }
}

$(document).ready(function() {
    checkWidth(true);

    $(window).resize(function() {
        checkWidth(false);
    });

$("#veilingcollection").delegate('a', 'click', function() {
    console.log("test");
    $('#veilingpopup').modal();

 $('#veilingpopup').modal('open');

});
});
