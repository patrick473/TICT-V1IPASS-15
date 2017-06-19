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
                $("#telefoonnummer").val('0'+this.telefoonnummer);
                $("#banknummer").val(this.banknummer);

                Materialize.updateTextFields();
            });

            Materialize.toast('Bod gedaan', 4000);
        })
        .fail(function() {
            console.log("error");
        })
        .always(function() {
            console.log("complete");
        });
}

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
$("#updateForm").validate({
       rules: {
           adres:{
               required:true,
               maxlength:60
           },
           postcode:{
               required: true
           },
           plaatsnaam:{
               required:true,
               maxlength:60
           },
           land:{
               required:true,
               maxlength: 40
           },
           geboortedag:{
               required:true,
               minlength:10,
               maxlength:10,
               dateISO:true
           },
           email:{
               required:true,
               email :true,
               maxlength:80
           },
           telefoonnummer:{
               required:true,
               minlength:10,
               maxlength:10
           },
           banknummer:{
               minlength:18,
               maxlength:18
           },


       },

       messages: {
           adres:{
               required: "Voer je adres in.",
               maxlength: "Je adres mag maximaal 60 tekens bevatten."
           },
           postcode:{
               required: "Voer je postcode in.",
           },
           plaatsnaam:{
               required: "Voer je plaatsnaam in.",
               maxlength: "Je plaatsnaam mag maximaal 60 tekens bevatten."
           },
           land:{
               required: "Voer je land in.",
               maxlength: "je land mag maximaal 40 tekens bevatten."
           },
           email:{
               required: "Voer je email in.",
               maxlength: "Een email mag maximaal tekens bevatten"
           },
           telefoonnummer:{
               required: "Voer je telefoonnummer in.",
               minlength: "Een telefoonnummer heeft 10 cijfers"
           },
           banknummer:{
               minlength: "Een banknummer heeft 18 tekens."
           }
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
     submitHandler: function(form) {
       var data = $("#updateForm").serialize();
       event.preventDefault();
       kanVerkopen = false;
       if ($("#banknummer").val().length > 0){
           kanVerkopen = true;
       }
       console.log(kanVerkopen);
       $.ajax({
           url: 'restservices/gebruiker/'+sessionStorage.getItem("gebruikerID")+'/'+kanVerkopen,
           type: 'PUT',
           data: data,

           beforeSend: function(xhr) {
               var token = window.sessionStorage.getItem("sessionToken");
               xhr.setRequestHeader('Authorization', 'Bearer ' + token);
           }
       })
       .done(function() {
           console.log("success");
       })
       .fail(function() {

       })
       .always(function() {
          Materialize.toast('Gegevens aangepast', 4000);
       });

     setTimeout(window.location.replace("gebruikersgegevens.html"),1000);
   }
    });

$('#postcode').formatter({
      'pattern': '{{9999aa}}',
});
$('#telefoonnummer').formatter({
      'pattern': '{{9999999999}}',
});
$('#banknummer').formatter({
      'pattern': '{{aa99aaaa9999999999}}',
});

$(document).ready(function(){
    $('.tooltipped').tooltip({delay: 50});
  });
