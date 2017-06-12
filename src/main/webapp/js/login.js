$("input, textarea").alphanum({
    allow              : '!@#$%&*(){}|:.,<>~+=_-?/',
    disallow           : '',
    allowSpace         : false,
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

$(document).ready(function() {
console.log(sessionStorage.getItem("sessionToken"));
});
$("#loginForm").validate({
       rules: {
           gebruikersnaam: {
               required: true,
           },
           wachtwoord: {
               required: true,
           },

       },

       messages: {
           gebruikersnaam:{
               required: "voer een gebruikersnaam in.",

           },
           wachtwoord:{
               required: "voer een wachtwoord in."
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
         var data = $("#loginForm").serialize();

         event.preventDefault();
         $.post("http://localhost:4711/onebid/restservices/authentication", data, function(response) {

           sessionStorage.setItem("sessionToken", response);
           console.log(sessionStorage.getItem("sessionToken"));
         }).fail(function(jqXHR, textStatus, errorThrown) {
           console.log(textStatus);
           console.log(errorThrown);
         });
         gebruikersnaam = $("#gebruikersnaam").val();

         $.get("http://localhost:4711/onebid/restservices/gebruiker/username/"+gebruikersnaam, (response) =>{
            console.log(response);
            $(response).each(function(index) {
                console.log(this);
                sessionStorage.setItem("gebruikerID",this.gebruikerID);
                sessionStorage.setItem("kanverkopen", this.kanverkopen);
            });


                console.log(sessionStorage.getItem("gebruikerID"));
                console.log(sessionStorage.getItem("kanverkopen"));

         });
           setTimeout(function(){window.location.replace("veilingen.html"); }, 2000);




  }
    });
