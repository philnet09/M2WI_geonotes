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
                padding-top: 60px;
                padding-bottom: 40px;
                padding-left: 20px;
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

            var map1;
            var map2;
            var directionsDisplay;
            var directionsService = new google.maps.DirectionsService();
            function initialize() {
                var mapOptions = {
                    zoom: 7,
                    center: new google.maps.LatLng(48.8578, 2.3479),
                    mapTypeId: google.maps.MapTypeId.ROADMAP
                };
                var html="";
                map1 = new google.maps.Map(document.getElementById('map_canvas1'),mapOptions);
                directionsDisplay = new google.maps.DirectionsRenderer();
                directionsDisplay.setMap(map1);

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
                        });
                        marker.setMap(map1);
                        html+="<div id='note"+cur.id+"'><b id='nom"+cur.id+"'>"+cur.nom+"</b><span id='action"+cur.id+"'><input type='button' value='X' onclick='supprimer_note("+cur.id+")'/>\n\
                         <input type='button' value='Edit' onclick='allow_modif("+cur.id+")'/></span>\n\
                         <input type='button' id='edit"+cur.id+"' value='ok' style='display:none;' onclick='modif_note("+cur.id+")'/>\n\
                        <p id='desc"+cur.id+"'>"+cur.description+"</p></div/>";
                    });
                    $("#listeNote").html(html);
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
                $('#myTab a:last').tab('show');
            })
        </script>
    </head>
    <body>
        <ul class="nav nav-tabs" id="myTab">
            <li class="active"><a data-toggle="tab" href="#note">Note</a></li>
        </ul>

        <div class="tab-content" id="myTabContent">
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

            </form>
            <div id="note" class="tab-pane active" >
                <div id="map_canvas1" style="width: 500px; height: 500px; float: left;"></div>
            </div>
            <div id="listeNote" style="float: left">
            </div>

        </div>


        <%--
aide auchemins
        <div id="displayinfo_canvas" style="width: 250px; height: 400px;"></div>
        --%>


    </body>
    <script type="text/javascript"  src="bootstrap/js/bootstrap.min.js"></script>
</html>
