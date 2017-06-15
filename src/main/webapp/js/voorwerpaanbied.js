$(document).ready(function() {
    checkWidth(true);



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


// VAIDATION

$("input, textarea").alphanum({
    allow              : '!@#$%&*(){}|:.,<>~+=_-?/',
    disallow           : ';"',
    allowSpace         : true,
    allowNumeric       : true,
    allowUpper         : true,
    allowLower         : true,
    allowCaseless      : true,
    allowLatin         : true,
    allowOtherCharSets : true,
    forceUpper         : false,
    forceLower         : false,
    maxLength          : NaN
});
$("#startprijs").alphanum({
    allow              : '.1234567890',
    disallow           : ';"',
    allowSpace         : false,
    allowNumeric       : true,
    allowUpper         : false,
    allowLower         : false,
    allowCaseless      : false,
    allowLatin         : false,
    allowOtherCharSets : false,
    forceUpper         : false,
    forceLower         : false,
    maxLength          : NaN
});
$("#verzendkosten").alphanum({
    allow              : '.1234567890',
    disallow           : ';"',
    allowSpace         : false,
    allowNumeric       : true,
    allowUpper         : false,
    allowLower         : false,
    allowCaseless      : false,
    allowLatin         : false,
    allowOtherCharSets : false,
    forceUpper         : false,
    forceLower         : false,
    maxLength          : NaN
});


$(document).ready(function(){
    $('.tooltipped').tooltip({delay: 50});
         $('select').material_select();
         $("#verkoper").val(sessionStorage.getItem("gebruikerID"));
            $("#voorwerpForm").validate({
                   rules: {
                       titel: {
                           required: true,
                           maxlength:  60
                       },
                       beschrijving:{
                           required: true,
                           maxlength: 1000
                       },
                       startprijs:{
                           required: true,
                           maxlength:41
                       },
                       betalingswijze:{
                           required:true,
                           maxlength:40
                       },
                       verzendkosten:{
                           maxlength:41
                       },
                       verzendinstructie:{
                           required:true,
                           maxlength:60
                       },
                   },

                   messages: {
                       titel:{
                           required: "Om een voorwerp goed te kunnen verkopen is een titel nodig.",
                           maxlength: "Je titel mag maximaal 60 tekens bevatten.",

                       },
                       beschrijving:{
                           required: "Voer een beschrijving in van het voorwerp.",
                           maxlength: "De beschrijving mag maximaal 1000 tekens bevatten."
                       },
                       startprijs:{
                           required: "De veiling moet ergens beginnen, voer een start prijs in.",
                           maxlength: "Jouw voorwerp kost waarschijnlijk niet meer dan 99 hexilioen."

                       },
                       betalingswijze:{
                           required: "Voer de gewenste betalingswijze in.",
                           maxlength: "Betalingswijze mag maximaal 40 tekens bevatten."
                       },
                       verzendkosten:{
                           maxlength: "Verzendkosten zijn waarschijnlijk niet meer dan 99 Hexilioen.",
                       },
                       verzendinstructie:{
                           required: "Een verzendinstructie is handig voor de koper zodat zij weten hoe zij het voorwerp kunnen verkrijgen.",
                           maxlength: "De verzendinstructie mag maximaal 60 tekens bevatten."
                       },

                   },
                   errorElement : 'div',
                   errorPlacement: function(error, element) {
                     var placement = $(element).data('error');
                     if (placement) {
                       $(placement).append(error);
                     } else {
                       error.insertAfter(element);
                     }
                 },
                 submitHandler: function(form,event) {
                   var data = $("#voorwerpForm").serialize();
                   console.log(data);
                   event.preventDefault();
                   $.ajax({
                       url: "http://localhost:4711/onebid/restservices/voorwerp/new/",
                       type: 'POST',
                       data:data,
                       beforeSend: function (xhr) {
                      var token = window.sessionStorage.getItem("sessionToken");
                      xhr.setRequestHeader( 'Authorization', 'Bearer ' + token);
                  }

                   })
                   .done(function() {
                       console.log(response);
                       Materialize.toast('Voorwerp is geplaatst ', 4000);
                       setTimeout(function(){window.location.replace("eigenveilingen.html"); }, 2000);
                   })
                   .fail(function() {
                       console.log("error");
                   })
                   .always(function() {
                       console.log("complete");
                   });

               }
                });

  });
