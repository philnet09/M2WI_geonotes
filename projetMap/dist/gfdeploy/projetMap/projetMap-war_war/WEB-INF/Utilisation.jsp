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
        <title>${user.nom} ${user.prenom}</title>

        <!-- Le HTML5 shim, for IE6-8 support of HTML5 elements -->
        <!--[if lt IE 9]>
          <script src="http://html5shim.googlecode.com/svn/trunk/html5.js"></script>
        <![endif]-->

        <%--clé : AIzaSyCh9NIMVomQYCYeRdUK5Pvx55RcjtFs7dE --%>
        <script src="https://maps.googleapis.com/maps/api/js?v=3.exp&sensor=false&language=fr"></script>
        <script>
            
            var map;
            var directionsDisplay;
            var directionsService = new google.maps.DirectionsService();
           
            function initialize() {             
                var mapOptions = {
                    zoom: 9,
                    center: new google.maps.LatLng(48.8578, 2.3479),
                    mapTypeId: google.maps.MapTypeId.ROADMAP
                };
                map = new google.maps.Map(document.getElementById('map_canvas1'),mapOptions);
                directionsDisplay = new google.maps.DirectionsRenderer();
                directionsDisplay.setMap(map);

                $.getJSON("./getAllNotes",function(msg){
                    if(msg.length>0){
                        $(msg).each(function(i,cur){
                            var infobulle = new google.maps.InfoWindow({
                                content: "<b style='margin-left: auto;margin-right: auto;'>"+cur.nom+"</b>"+"<p>"+cur.description+"</p>"+
                                    "<p>"+cur.date+"</p>",
                                disableAutoPan: false
                            });
                            var marker =new google.maps.Marker({
                                position: new google.maps.LatLng(cur.latitude, cur.longitude),
                                title:cur.nom,
                                icon: new google.maps.MarkerImage(
                                "http://chart.googleapis.com/chart?chst=d_bubble_text_small&chld=bb|"+cur.nom+"|FFFFFF|000000",
                                null, null, new google.maps.Point(0, 42))
                            });
                            google.maps.event.addListener(marker, 'click', function(){
                                infobulle.open(map, marker);
                            });
                            marker.setMap(map);
                        });
                    }
                });
                google.maps.event.addListener(map,'click',function(event) {
                    $("#inputLatitude").val(event.latLng.lat());
                    $("#inputLongitude").val(event.latLng.lng());
                });
                

            }
            google.maps.event.addDomListener(window, 'load', initialize);
           
        </script>
    </head>
    <body>
        Bonjour ${user.nom} <button onclick="getLocation()">Géolocalisation</button>
        <div id="res"></div>
        <script type="text/javascript" >
            var x=document.getElementById("res");
            function getLocation()
            {  if (navigator.geolocation)
                {
                    navigator.geolocation.getCurrentPosition(showPosition,showError);
                }
                else{x.innerHTML="Geolocation is not supported by this browser.";}
       
            }
            function showPosition(position)
            {
                lat=position.coords.latitude;
                lon=position.coords.longitude;
                latlon=new google.maps.LatLng(lat, lon)
                mapholder=document.getElementById('map_canvas1')
                var myOptions={
                    center:latlon,zoom:9,
                    mapTypeId:google.maps.MapTypeId.ROADMAP,
                    mapTypeControl:false,
                    navigationControlOptions:{style:google.maps.NavigationControlStyle.SMALL}
                };
                map=new google.maps.Map(document.getElementById("map_canvas1"),myOptions);
                var marker =new google.maps.Marker({
                    position: latlon,
                    title:"vous êtes ici",
                    icon: new google.maps.MarkerImage(
                    "http://chart.googleapis.com/chart?chst=d_bubble_text_small&chld=bb|vous êtes ici|FF00FF|000000",
                    null, null, new google.maps.Point(0, 42))
                });
                /*google.maps.event.addListener(map, 'zoom_changed', function() {
                });*/
                //recuperer les points proches de l'utilisateur
                $.getJSON("./getAllNotes",function(msg){
                    if(msg.length>0){
                        $(msg).each(function(i,cur){
                            if((cur.latitude>lat-2 && cur.latitude<lat+2) && (cur.longitude>lon-2 && cur.longitude<lon+2)){
                                var infobulle = new google.maps.InfoWindow({
                                    content: "<b style='margin-left: auto;margin-right: auto;'>"+cur.nom+"</b>"+"<p>"+cur.description+"</p>"+
                                        "<p>"+cur.date+"</p>",
                                    disableAutoPan: false
                                });
                                var marker =new google.maps.Marker({
                                    position: new google.maps.LatLng(cur.latitude, cur.longitude),
                                    title:cur.nom,
                                    icon: new google.maps.MarkerImage(
                                    "http://chart.googleapis.com/chart?chst=d_bubble_text_small&chld=bb|"+cur.nom+"|FFFFFF|000000",
                                    null, null, new google.maps.Point(0, 42))
                                });
                                google.maps.event.addListener(marker, 'click', function(){
                                    infobulle.open(map, marker);
                                });
                                marker.setMap(map);
                            }
                        });
                    }
                });
                //
                marker.setMap(map);
            }
            function showError(error)
            {
                switch(error.code)
                {
                    case error.PERMISSION_DENIED:
                        x.innerHTML="User denied the request for Geolocation."
                        break;
                    case error.POSITION_UNAVAILABLE:
                        x.innerHTML="Location information is unavailable."
                        break;
                    case error.TIMEOUT:
                        x.innerHTML="The request to get user location timed out."
                        break;
                    case error.UNKNOWN_ERROR:
                        x.innerHTML="An unknown error occurred."
                        break;
                }
            }
        </script>
        <div class="tab-content" id="myTabContent">
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
