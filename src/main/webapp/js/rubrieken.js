
var hoogste= 0;
$(document).ready(function() {
    checkWidth(true);
    loadRubriekenUpper();
      $(".prepage").addClass('hide');

    $(window).resize(function() {
        checkWidth(false);
    });

$("#initRubriekenCollection").delegate('a', 'click', function() {
       rubriek = $(this).data("rubrieknummer");
       $.ajax({
           url: "restservices/rubriek/onder/"+rubriek,
           type: 'GET',
           beforeSend: function(xhr) {
               var token = window.sessionStorage.getItem("sessionToken");
               xhr.setRequestHeader('Authorization', 'Bearer ' + token);
           }

       })
       .done(function(data) {
         $(data).each(function(index) {
             $("#secondRubriekenCollection").append('<a href="#!"  class="collection-item" data-rubrieknummer="' + this.rubrieknummer + '" id="rubriek' + this.rubrieknummer + '">' + this.rubrieknaam + '</a>');


         });
         $('#initRubriekenCollection').addClass('hide');
         $('#secondRubriekenCollection').removeClass('hide');
       })
       .fail(function() {
           console.log("error");
       })
       .always(function() {
           console.log("complete");
       });

});
$("#secondRubriekenCollection").delegate('a', 'click', function() {
       rubriek = $(this).data("rubrieknummer");
       $("#itemCollection").empty();
$.ajax({
  url: "restservices/voorwerp/rubriek/"+rubriek,
  type: 'GET',
  beforeSend: function(xhr) {
      var token = window.sessionStorage.getItem("sessionToken");
      xhr.setRequestHeader('Authorization', 'Bearer ' + token);

  }
})
.done(function(data) {
  $(data).each(function(index) {
      $("#itemCollection").append('<a id="voorwerp'+this.voorwerpnummer+ '"data-voorwerpnummer="'+this.voorwerpnummer+'"class="collection-item avatar"> '+
        '<span id="title" class="title">'+this.titel+'</span>'+
      ' <p>de startprijs is '+this.startprijs+'<p id="beschrijving" class="black-text">'+this.beschrijving+'</p>'+
      '  </a>');


  });
})
.fail(function() {
  $("#itemCollection").append('<p id="voorwerpfail"> '+
    '<h4 id="title" class="title">oeps er is iets fout gegaan</h4>'+
  ' <p id="beschrijving" class="black-text">er zijn geen veilingen gevonden</p>'+
  '  </p>');
})
.always(function() {
  $("#itemCollection").removeClass('hide');
  $('#initRubriekenCollection').addClass('hide');
  $('#secondRubriekenCollection').addClass('hide');
});
});
$("#itemCollection").delegate('a', 'click', function() {
    voorwerpnummer = $(this).data('voorwerpnummer');
    console.log(voorwerpnummer);

  loadModal(voorwerpnummer);
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
function loadRubriekenUpper(){
    $.ajax({
        url: "restservices/rubriek/bovenste",
        type: 'GET',
        beforeSend: function(xhr) {
            var token = window.sessionStorage.getItem("sessionToken");
            xhr.setRequestHeader('Authorization', 'Bearer ' + token);

        }

    })
    .done(function(data) {
        $(data).each(function(index) {
            $("#initRubriekenCollection").append('<a href="#!"  class="collection-item" data-rubrieknummer="' + this.rubrieknummer + '" id="rubriek' + this.rubrieknummer + '">' + this.rubrieknaam + '</a>');


        });
    })
    .fail(function() {
        console.log("error");
    })
    .always(function() {
        console.log("complete");
    });

}

function showUpperhideUnder(){
    $("#itemCollection").addClass('hide');
    $("#initRubriekenCollection").removeClass('hide');
    $("#secondRubriekenCollection").addClass('hide');
    $("#secondRubriekenCollection").empty();
}



$("bodRegistratie").submit(function(e) {
    e.preventDefault();
});




function loadModal(voorwerpnummer) {
    $("#biedingencollection").empty();
    $("#bodBedrag").val('');
    $.ajax({
            url: "restservices/voorwerp/" + voorwerpnummer,
            type: 'GET',
            beforeSend: function(xhr) {
                var token = window.sessionStorage.getItem("sessionToken");
                xhr.setRequestHeader('Authorization', 'Bearer ' + token);
            }
        })
        .done(function(data) {
            $(data).each(function(index) {

                $("#modalTitel").text(this.titel);
                $("#modalStartprijs").text("startprijs: € " + this.startprijs);
                $("#modalStartprijs").data('startprijs', this.startprijs);
                $("#modalBeschrijving").text(this.beschrijving);

            });


        })
        .fail(function() {
            console.log("error");
        })
        .always(function() {
            console.log("complete");
        });
        $.ajax({
                url: "restservices/bod/voorwerp/" + voorwerpnummer,
                type: 'GET',
                beforeSend: function(xhr) {
                    var token = window.sessionStorage.getItem("sessionToken");
                    xhr.setRequestHeader('Authorization', 'Bearer ' + token);
                }
            })
            .done(function(data) {
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
                sessionStorage.setItem("huidigItem", voorwerpnummer);

            })
            .fail(function() {
                console.log("error");
            })
            .always(function() {
                console.log("complete");
            });
            $("#biedingencollection").data('hoogste',0);
        $.ajax({
                url: "restservices/bod/voorwerp/hoogste/" + voorwerpnummer,
                type: 'GET',
                beforeSend: function(xhr) {
                    var token = window.sessionStorage.getItem("sessionToken");
                    xhr.setRequestHeader('Authorization', 'Bearer ' + token);
                }
            })
            .done(function(data) {
                $(data).each(function(index) {
                    hoogste = this.bodBedrag;
                    sessionStorage.setItem("hoogste", this.bodBedrag);

                    console.log(sessionStorage.getItem("hoogste"));
                    hoogste = parseInt(sessionStorage.getItem("hoogste"));
                    console.log(hoogste);

                    $("#biedingencollection").data('hoogste', this.bodBedrag);
                    console.log($("#biedingencollection").data('hoogste'));

                });
            })
            .fail(function() {
                console.log("error");
            })
            .always(function() {
                console.log("complete");
            });

}
//HIERBOVEN AllE SElECT DINGEN

//HIERONDER DE REST
$(document).ready(function() {
$("input, textarea").alphanum({
    allow: '.1234567890',
    disallow: ',":',
    allowSpace: false,
    allowNumeric: true,
    allowUpper: false,
    allowLower: false,
    allowCaseless: false,
    allowLatin: true,
    allowOtherCharSets: true,
    forceUpper: true,
    forceLower: true,
    maxLength: NaN
});

$("#bodRegistratie").validate({

    rules: {
        bodBedrag: {
            required: true,
            min: hoogste,
        },


    },

    messages: {
        bodBedrag: {
            required: "voer een bod in.",
            minStrict: "bedrag moet hoger zijn dan het huidige hoogste bedrag."

        },
    },
    errorElement: 'div',
    errorPlacement: function(error, element) {
        var placement = $(element).data('error');
        if (placement) {
            $(placement).append(error);
        } else {
            error.insertAfter(element);
        }
    },
    submitHandler: function(form) {
        console.log('er is iets ingevuld.');
       }
   });









});
$("#registerBod").click(function(event) {
    if (parseInt($("#bodBedrag").val())>$("#biedingencollection").data('hoogste')){
        Materialize.toast('Bod gedaan', 4000);
        var data = $("#bodRegistratie").serialize();
        var bod = parseInt($("#bodBedrag").val());
        event.preventDefault();
        console.log(sessionStorage);
        $.ajax({
            url: 'restservices/bod/' + sessionStorage.getItem("huidigItem") + '/' + sessionStorage.getItem("gebruikerID") + '/' + bod,
            type: 'POST',
            data: data,
            beforeSend: function (xhr) {
           var token = window.sessionStorage.getItem("sessionToken");
           xhr.setRequestHeader( 'Authorization', 'Bearer ' + token);
       }
        })
        .done(function() {
            console.log(response);
            console.log("success");
        })
        .fail(function() {
            console.log("error");
        })
        .always(function() {
            console.log("complete");
        });
          setTimeout(function(){location.reload(); }, 2000);location.reload();
    }
    else {
        Materialize.toast('Bod lager dan het huidige hoogste bod ', 4000);
    }
});
