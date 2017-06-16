$("input, textarea").alphanum({
    allow              : '!@#$%&*(){}|:.,<>~+=_-?/',
    disallow           : '',
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
$("#registerForm").validate({
       rules: {
           gebruikersnaam: {
               required: true,
               maxlength:  35
           },
           voornaam:{
               required: true,
               maxlength: 30
           },
           achternaam:{
               required: true,
               maxlength:60
           },
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
               date: true
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
           wachtwoord: {
               required: true,
               minlength: 8,
               maxlength: 50
           },
           wachtwoordsecond:{
               required:true,
               equalTo: "#wachtwoord"
           },
           banknummer:{
               minlength:18,
               maxlength:18
           },


       },

       messages: {
           gebruikersnaam:{
               required: "Voer je gebruikersnaam in.",
               maxlength: "Je gebruikersnaam mag maximaal 35 tekens bevatten.",

           },
           voornaam:{
               required: "Voer je voornaam in.",
               maxlength: "Je voornaam mag maximaal 30 tekens bevatten."
           },
           achternaam:{
               required: "Voer je achternaam in.",
               maxlength: "Je achternaam mag maximaal 60 tekens bevatten."

           },
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
           geboortedag:{
               required: "Voer je geboortedag in.",
               minlength: "Een datum bevat minimaal een jaar,datum,dag",
               date: "voer een valide datum in."
           },
           email:{
               required: "Voer je email in.",
               maxlength: "Een email mag maximaal tekens bevatten"
           },
           telefoonnummer:{
               required: "Voer je telefoonnummer in.",
               minlength: "Een telefoonnummer heeft 10 cijfers"
           },
           wachtwoord:{
               required: "Voer je wachtwoord in.",
               minlength: "Een wachtwoord moet minimaal 8 tekens bevatten",
               maxlength: "een wachtwoord mag maximaal 50 tekens bevatten"
           },
           wachtwoordsecond:{
               required: "Je moet je wachtwoord nog een keer invoeren.",
               equalTo: "Wachtwoorden komen niet overeen.",
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
       var data = $("#registerForm").serialize();
       event.preventDefault();
       kanVerkopen = false;
       if ($("#banknummer").val().length > 0){
           kanVerkopen = true;
       }
       console.log(kanVerkopen);

      $.post("restservices/gebruiker/new/"+kanVerkopen, data, function(response) {

       }).fail(function(jqXHR, textStatus, errorThrown) {
         console.log(textStatus);
         console.log(errorThrown);
     });
     window.location.replace("login.html");
   }
    });
    $('#geboortedag').formatter({
          'pattern': '{{9999}}-{{99}}-{{99}}',
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
