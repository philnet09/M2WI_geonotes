<!DOCTYPE html>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
    <head>
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <meta name="description" content="GeoNotes">
        <meta name="author" content="Boussetta Islem">

        <!-- Le styles -->
        <script src="js/jquery-1.8.3.min.js"></script>
        <script src="js/ajax.js"></script>
        <style type="text/css">
            body {
                padding-top: 0px;
                padding-bottom: 40px;
                padding-left: 20px;
            }
            
            
            #NoteContent,#ParcoursContent{
                background: rgba(167, 167, 167, 0.39);
                padding: 5px;
                margin: 10px;
                border-radius: 10px;
            }
            
            #listeNote,#listeParcours{
                margin-top: 15px;
                position: absolute;
                left: 555px;
                background-color: rgba(0, 143, 255, 0.22);
                border-radius: 10px;
                padding: 5px;
            }
            
            .parcours{
                background-color: rgb(228, 228, 228);
                border-radius: 5px;
                padding: 6px;
                border:1px solid #fff;
                cursor:pointer;
            }
            
            #panel{
                width: 500px;
                position: absolute;
                right: 20px;
                background-color: rgb(255, 228, 228);
                border-radius: 11px;
                padding: 6px;
                margin-top: 15px;
            }
        </style>
        <title>Admin</title>

        <!-- Le HTML5 shim, for IE6-8 support of HTML5 elements -->
        <!--[if lt IE 9]>
          <script src="http://html5shim.googlecode.com/svn/trunk/html5.js"></script>
        <![endif]-->

        <%--clé : AIzaSyCh9NIMVomQYCYeRdUK5Pvx55RcjtFs7dE --%>
        <script src="https://maps.googleapis.com/maps/api/js?v=3.exp&sensor=false&language=fr"></script>
        <script>
            Object.columns = function(obj) {
                var size = 0, key;
                var textesortie="";
                for (key in obj) {
                    textesortie+=key+"\n";
                }
                alert(textesortie);
                return size;
            };
            
            
           Object.size = function(obj) {
                var size = 0, key;
                for (key in obj) {
                    size++;
                }
                return size;
            };
            
            var map1;
            var map2;
            var initialize;
            var calculate;
            var direction;
            var directionsDisplay;
            var directionsService = new google.maps.DirectionsService();
            
            function secondsToTime(secs)
                {
                    var hours = Math.floor(secs / (60 * 60));

                    var divisor_for_minutes = secs % (60 * 60);
                    var minutes = Math.floor(divisor_for_minutes / 60);

                    var divisor_for_seconds = divisor_for_minutes % 60;
                    var seconds = Math.ceil(divisor_for_seconds);

                    return hours+"h "+minutes+"m";
                }

              function requestDirections(start, end, step1, step2, renderer) {
                if(step2!=null){
                directionsService.route({
                  origin: start,
                  destination: end,
                   waypoints: [
                    {
                      location:step1,
                      stopover:false
                    },{
                      location:step2,
                      stopover:true
                    }],
                  travelMode: google.maps.DirectionsTravelMode.DRIVING
                }, function(result) {
                    renderer.setDirections(result);
                });
                }else if (step1!=null){
                  directionsService.route({
                  origin: start,
                  destination: end,
                   waypoints: [
                    {
                      location:step1,
                      stopover:true
                    }],
                  travelMode: google.maps.DirectionsTravelMode.DRIVING
                }, function(result) {
                    renderer.setDirections(result);
                });  
                }else{
                   directionsService.route({
                  origin: start,
                  destination: end,
                  travelMode: google.maps.DirectionsTravelMode.DRIVING
                }, function(result) {
                    renderer.setDirections(result);
                }); 
                }

              }
            
            
            function initialize() {
                var mapOptions = {
                    zoom: 7,
                    center: new google.maps.LatLng(48.8578, 2.3479),
                    mapTypeId: google.maps.MapTypeId.ROADMAP
                };
                var html="";
                map1 = new google.maps.Map(document.getElementById('map_canvas1'),mapOptions);
                panel    = document.getElementById('panel');
                directionsDisplay = new google.maps.DirectionsRenderer();
                directionsDisplay.setMap(map1);
                directionsDisplay.setPanel(panel);


                //Affichage de toutes les notes
                $.getJSON("./getAllNotes",function(msg){
                    $(msg).each(function(i,cur){
                        var infobulle = new google.maps.InfoWindow({
                            content: "<b style='margin-left: auto;margin-right: auto;'>"+cur.nom+"</b>"+"<p>"+cur.description+"</p>"+
                                "<p>"+cur.date+"</p>",
                            disableAutoPan: false
                        });
                        var marker =new google.maps.Marker({
                            position: new google.maps.LatLng(cur.latitude, cur.longitude),
                            title:cur.nom
                        });
                        google.maps.event.addListener(marker, 'click', function(){
                            infobulle.open(map1, marker);
                            //MAJ
                            var focuselem = $("input:focus");
                             if(focuselem.attr("id")!="inputNom"){
                                focuselem.attr("value",cur.latitude+","+cur.longitude);  
                             } 
                        });
                        marker.setMap(map1);
                        html+="<div id='note"+cur.id+"'><b id='nom"+cur.id+"'>"+cur.nom+"</b><span id='action"+cur.id+"'><input type='button' value='X' onclick='supprimer_note("+cur.id+")'/>\n\
                         <input type='button' value='Edit' onclick='allow_modif("+cur.id+")'/></span>\n\
                         <input type='button' id='edit"+cur.id+"' value='ok' style='display:none;' onclick='modif_note("+cur.id+")'/>\n\
                        <p id='desc"+cur.id+"'>"+cur.description+"</p></div/>";
                    });
                    $("#listeNote").html(html);
                });
                
                
               // Affichage de tous les parcours
                $.getJSON("./getAllParcours",function(msg){
                    var html="";
                    $(msg).each(function(indexparcours,cur){//Pour chaque parcours

                      
                      //1) On récupère les notesparcours
                        var request = $.ajax({
                             url: "./getAllNotesParcours",
                             type: "GET",
                             data: {
                                 id : cur.id
                             },
                             dataType: "json"
                         }).success(function(rep) {
                             var tabnotes = new Array();
                             $(rep).each(function(i,curnoteparc){
                                 //2) On récupère toutes les notes
                                 var request = $.ajax({
                                    url: "./getNoteswithID",
                                    type: "GET",
                                    data: {
                                        id : curnoteparc.noteParcoursPK.refnote
                                    },
                                    dataType: "json"
                                }).success(function(rep2) {                                 
                                    tabnotes[curnoteparc.ordre]=rep2;
   
                                    if((i+1)==rep.length){
//                Object.columns(tabnotes); //FIX   


                                 
                html+="<div class='parcours' id='parcours"+cur.id+"'><b id='nom"+cur.id+"'>"+cur.nom+"</b><span id='action"+cur.id+"'>\n\
                <input type='button' value='X' onclick='supprimer_parcours("+cur.id+")'/>\n\
                <input type='button' class='viewparcours' data-id='"+cur.id+"' value='View'></span>\n\
               <p>Distance : "+(Math.round(cur.distance*100)/100)+" km <br> Temps : "+secondsToTime(cur.temps)+"</p>\n\
                \n\
                <p><u>Départ :</u> "+tabnotes[1]["nom"]+"<br/>";
                    
                    indexarr = 2;
                 if(tabnotes.length>3){ //Si Etape 1
                     html+="<u>Etape 1 :</u> "+tabnotes[2]["nom"]+"<br/>";
                     indexarr = 3;
                 }
                 if(tabnotes.length>4){ //Si Etape 2
                     html+="<u>Etape 2 :</u> "+tabnotes[3]["nom"]+"<br/>";
                     indexarr = 4;
                 }
                
                html+="<u>Arrivée :</u> "+tabnotes[indexarr]["nom"]+"</div/>";
               $("#listeParcours").html(html);

                                    }
                                });
                             });
                         });
                      });   
                });

                
                
                google.maps.event.addListener(map1,'click',function(event) {
                    //map1.setCenter(paris);
                    $("#inputLatitude").val(event.latLng.lat());
                    $("#inputLongitude").val(event.latLng.lng());
                });
            }

            //
            google.maps.event.addDomListener(window, 'load', initialize);
            $(function () {
                $('#myTab a:first').tab('show');
            });


        </script>
 <script>

            $(document).ready(function(){
                
                    $("#ParcoursContent").hide();

                    //Switch tab
                    $("#myTab").find("a").bind("click", function(){
                       var latab = $(this).html();

                        switch(latab){
                            case "Parcours":
                              $("#NoteContent").hide();
                              $("#ParcoursContent").show();
                                break;
                            case "Note":
                              $("#ParcoursContent").hide();
                              $("#NoteContent").show();
                                break;
                            default: 
                              alert('erreur');
                                break;
                        }
                    });
                    
                    //Ajout étape
                    $("#addstep").bind("click", function(){
                        newindex = 1;
                        i=1;
                        found="false";
                        valmax = 2;
                        while((i<=valmax) && (found!="true")){
                         if ($("#inputEtape"+i).length < 1){
                             newindex=i;
                             found="true";
                         }
                         i++;
                        }
                        if(found=="true")$('<div class="controls"> <input type="text" id="inputEtape'+newindex+'"></div>').insertBefore("#ctrl_arrivee");
                        if(newindex==1)$('#removestep').css("visibility","visible");
                        if(newindex==valmax)$('#addstep').css("visibility","hidden");
                        
                    });
                    
                    //Suppression étape
                    $("#removestep").bind("click", function(){
                         $("#inputEtape"+newindex).remove();
                         newindex--;
                         if(newindex==0)$('#removestep').css("visibility","hidden");
                         $('#addstep').css("visibility","visible");
                    });
                    
                    
                    //Affichage du parcours
                    $(".viewparcours").live("click",function(){
                       
                       
                    //1) On récupère les notesparcours
                        var request = $.ajax({
                             url: "./getAllNotesParcours",
                             type: "GET",
                             data: {
                                 id : $(this).attr("data-id")
                             },
                             dataType: "json"
                         }).success(function(rep) {
                             var tabnotes = new Array();
                             $(rep).each(function(i,curnoteparc){
                                 //2) On récupère toutes les notes
                                 var request = $.ajax({
                                    url: "./getNoteswithID",
                                    type: "GET",
                                    data: {
                                        id : curnoteparc.noteParcoursPK.refnote
                                    },
                                    dataType: "json"
                                }).success(function(rep2) {
                                    
                                    tabnotes[curnoteparc.ordre]=rep2;
                                    
                                    if((i+1)==rep.length){
                                    
//                                    Object.columns(tabnotes);
                                    origin=tabnotes[1]["latitude"]+","+tabnotes[1]["longitude"]; 
                                    indexarr = 2;
                                    etape1=null;
                                    etape2=null;
                                     if(tabnotes.length>3){ //Si Etape 1
                                         etape1=tabnotes[2]["latitude"]+","+tabnotes[2]["longitude"]; 
                                         indexarr = 3;
                                     }
                                     if(tabnotes.length>4){ //Si Etape 2
                                         etape2=tabnotes[3]["latitude"]+","+tabnotes[3]["longitude"]; 
                                         indexarr = 4;
                                     }
                                     destination=tabnotes[indexarr]["latitude"]+","+tabnotes[indexarr]["longitude"];
                                     
                                    //Création parcours                                        
                                   requestDirections(origin, destination, etape1, etape2, directionsDisplay);
                                    }
                                });
                             });
                         });
                       
                       
                    });

            });
            
        </script>
    </head>
    <body>
        <ul class="nav nav-tabs" id="myTab">
            <li class="active"><a data-toggle="tab" href="#note">Note</a></li>
            <li><a data-toggle="tab" href="#parcours">Parcours</a></li>
        </ul>

        <div class="tab-content" id="NoteContent">
            <center><h3>Notes</h3></center>
            <form class="form-horizontal">
                <div id="succes"></div>
                <div class="control-group">
                    <label class="control-label" for="inputTitre">Titre</label>
                    <div class="controls">
                        <input type="text" id="inputTitre" placeholder="Titre"><span rel="tooltip" id="TitreVerif"></span>
                    </div>
                </div>
                <div class="control-group">
                    <label class="control-label" for="inputLatitude">Latitude</label>
                    <div class="controls">
                        <input type="text" id="inputLatitude" placeholder="Latitude">
                    </div>
                </div>
                <div class="control-group">
                    <label class="control-label" for="inputLongitude">Longitude</label>
                    <div class="controls">
                        <input type="text" id="inputLongitude" placeholder="Longitude">
                    </div>
                </div>
                <div class="control-group">
                    <label class="control-label" for="Description">Description</label>
                    <div class="controls">
                        <textarea rows="3" id="description"></textarea>
                    </div>
                </div>
                <div class="control-group">
                    <div class="controls">
                        <input type="button" onclick="ajouterNote()" class="btn" value="Enregistrer"/>
                    </div>
                </div>
                <div id="listeNote"></div>
            </form>
        </div>
        
        
          <div class="tab-content" id="ParcoursContent">
              <center> <h3>Parcours</h3> </center>
            <form class="form-horizontal">
                <div id="succes"></div>
                <div class="control-group">
                    <label class="control-label" for="inputNom">Nom</label>
                    <div class="controls">
                        <input type="text" id="inputNom" placeholder="Nom">
                    </div>
                    
                    <label class="control-label" for="inputDepart">Départ</label>
                    <div class="controls">
                        <input type="text" id="inputDepart" placeholder="Depart">
                    </div>
                    
                    <!--<label class="control-label" for="inputEtapes">Étapes</label>-->
                    <div class="controls">                    
                        <input style="visibility:visible" type="button" id="addstep" value="Ajouter étape">
                         <input style="visibility:hidden" type="button" id="removestep" value="Supprimer étape">
                    </div>
                    
                    <label id="ctrl_arrivee" class="control-label" for="inputArrivee">Arrivée</label>
                    <div class="controls">
                        <input type="text" id="inputArrivee" placeholder="Arrivee">
                    </div>
                    
                <div class="control-group">
                    <div class="controls">
                        <input type="button" onclick="calculerItineraire()" class="btn" value="Enregistrer l'itinéraire"/>
                    </div>
                </div>
                <div id="panel"></div>    
                <div id="listeParcours"></div>                    
                                  
                </div>
            </form>
          </div>

            <div id="map_canvas1" style="width: 500px; height: 500px; float: left;"></div>


        <%--
aide auchemins
        <div id="displayinfo_canvas" style="width: 250px; height: 400px;"></div>
        --%>


    </body>
    <script type="text/javascript"  src="bootstrap/js/bootstrap.min.js"></script>
</html>
