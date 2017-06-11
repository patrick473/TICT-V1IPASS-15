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
$("#loginForm").validate({
       rules: {
           gebruikersnaam: {
               required: true,
           },
           password: {
               required: true,
           },

       },
       //For custom messages
       messages: {
           gebruikersnaam:{
               required: "voer een gebruikersnaam in.",

           },
           password:{
               required: "voer een wachtwoord in."
           }
       },
       errorElement : 'div',
       errorPlacement: function(error, element) {
         var placement = $(element).data('error');
         if (placement) {
           $(placement).append(error)
         } else {
           error.insertAfter(element);
         }
       }
    });
