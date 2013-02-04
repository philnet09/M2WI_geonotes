function ajouterNote(){
    var request = $.ajax({
        url: "./AjoutNote",
        type: "POST",
        data: {
            titre : $("#inputTitre").val(),
            latitude : $("#inputLatitude").val(),
            longitude : $("#inputLongitude").val(),
            description : $("#description").val()

        },
        dataType: "json"
    });
    request.done(function() {
        $("#succes").html("<div class='alert alert-success'>Note ajouté avec succes</div>").fadeOut().fadeIn();
        initialize();
    });
}
//verifier l'unicité de l'email
$("#email1").live("blur", function() {
    $.getJSON("./connexion",{
        email:$("#email1").val()
    },function(json){
        if(json==1){
            $("#emailCnt").html("<li class='icon-ok'></li>");
            $("#emailCnt").attr('data-original-title','Adresse Email valide');
        }else{
            if(json==2){
                $("#emailCnt").html("<li class='icon-remove'  ></li>");
                $("#emailCnt").attr('data-original-title','Adresse Email est deja utilisé');
            }else{
                $("#emailCnt").html("<li class='icon-remove' ></li>");
                $("#emailCnt").attr('data-original-title','Adresse Email non valide');
            }
        }

    });
});
//verifier l'unicité du nom d'une note
$("#inputTitre").live("blur", function() {
    $.getJSON("./UniqueNomNote",{
        nomNote:$("#inputTitre").val()
    },function(json){
        if(json==1){
            $("#TitreVerif").html("");
        }else{
            $("#inputTitre").val("");
            $("#TitreVerif").html("Nom deja Existe");
        }

    });
});
//supprimer une note
function supprimer_note(id){
    var request = $.ajax({
        url: "./SupprimerNote",
        type: "POST",
        data: {
            id : id
        },
        dataType: "json"
    });
    request.done(function(rep) {
        $('#note'+id).remove();
        initialize();
    });
}
//autoriser la modification d'une note
//supprimer une note
function allow_modif(id){
    $("#nom"+id).attr("contenteditable","true").css("background","#eee");
    $("#desc"+id).attr("contenteditable","true").css("background","#eee");
    $("#action"+id).hide();
    $("#edit"+id).show();
}
//supprimer une note
function modif_note(id){
    var request = $.ajax({
        url: "./ModifierNote",
        type: "POST",
        data: {
            id : id,
            nom : $("#nom"+id).html(),
            desc : $("#desc"+id).html()
        },
        dataType: "json"
    });
    request.done(function(rep) {
        initialize();
    });
}


//supprimer un parcours
function supprimer_parcours(id){
    var request = $.ajax({
        url: "./SupprimerParcours",
        type: "POST",
        data: {
            id : id
        },
        dataType: "json"
    });
    request.done(function(rep) {
        $('#parcours'+id).remove();
        $('#panel').html();
        initialize();
    });
}

function calculerItineraire(){    
        //1)Parcours côté client
        origin      = document.getElementById('inputDepart').value; // Le point départ
        destination = document.getElementById('inputArrivee').value; // Le point d'arrivé
        var waypts = [];
        if(document.getElementById('inputEtape1')){
            waypts.push({
                location:document.getElementById('inputEtape1').value,
                stopover:true
            });
        }
        if(document.getElementById('inputEtape2')){
            waypts.push({
                location:document.getElementById('inputEtape2').value,
                stopover:true
            });
        }
        if(origin && destination){
            var request = {
                origin      : origin,
                destination : destination,
                waypoints: waypts,
                travelMode  : google.maps.DirectionsTravelMode.DRIVING // Mode de conduite
            }
            var directionsService = new google.maps.DirectionsService(); // Service de calcul d'itinéraire
            directionsService.route(request, function(response, status){ // Envoie de la requête pour calculer le parcours
                if(status == google.maps.DirectionsStatus.OK){
                     //1) Création du parcours
                     
                    var indexarr = 0;//Par défaut on n'a que départ/arrivée donc un seul leg
                     if($("#inputEtape1").val()==null){
                         valetape1="null";
                         latetp1 = null;
                         lngetp1 = null;
                       
                     } else{ //Une étape
                         valetape1=$("#inputEtape1").val();
                         latetp1 = response.routes[0].legs[1].start_location.lat();
                         lngetp1 = response.routes[0].legs[1].start_location.lng();
                         indexarr=1;
                     }
                     if($("#inputEtape2").val()==null){
                         valetape2="null";
                         latetp2 = null;
                         lngetp2 = null;
                     } else{
                         valetape2=$("#inputEtape2").val();
                         latetp2 = response.routes[0].legs[2].start_location.lat();
                         lngetp2 = response.routes[0].legs[2].start_location.lng();
                         indexarr=2;
                    }
                    
                    var request = $.ajax({
                       url: "./AjoutParcours",
                       type: "POST",
                        data: {
                           nom : $("#inputNom").val(),
                           //Distance et temps seront mis à jour après
                           distance : response.routes[0].legs[0].distance.value*0.001,
                           temps : response.routes[0].legs[0].duration.value,
                           
                           depart : $("#inputDepart").val(),
                           latdep : response.routes[0].legs[0].start_location.lat(),
                           lngdep : response.routes[0].legs[0].start_location.lng(),
                           
                           arrivee : $("#inputArrivee").val(),
                           latarr : response.routes[0].legs[indexarr].end_location.lat(),
                           lngarr : response.routes[0].legs[indexarr].end_location.lng(),
                           
                           etape1 : valetape1,
                           latetp1 : latetp1,
                           lngetp1 : lngetp1,
                           
                           etape2 : valetape2, 
                           latetp2 : latetp2,
                           lngetp2 : lngetp2
                       },
                       dataType: "json"
                   });
                   request.done(function(rep) {
                //        $('#note'+id).remove();
                        initialize();
                   });
                    
                    
                    directionsDisplay.setDirections(response); // Trace l'itinéraire sur la carte et les différentes étapes du parcours
                }
            });
        }

        
}