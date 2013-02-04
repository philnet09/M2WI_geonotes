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
        <link href="bootstrap/css/bootstrap.css" rel="stylesheet">
        <script src="js/jquery-1.8.3.min.js"></script>
        <script type="text/javascript"  src="bootstrap/js/bootstrap.min.js"></script>
        <script src="js/ajax.js"></script>
        <style type="text/css">
            body {
                padding-top: 60px;
                padding-bottom: 40px;
                padding-left: 20px;
            }
        </style>
        <link href="bootstrap/css/bootstrap-responsive.css" rel="stylesheet">
        <title>Connexion</title>

        <!-- Le HTML5 shim, for IE6-8 support of HTML5 elements -->
        <!--[if lt IE 9]>
          <script src="http://html5shim.googlecode.com/svn/trunk/html5.js"></script>
        <![endif]-->

    </head>
    <body>
        <div class="form-horizontal" >
            <c:if test="${erreurCnx !=null}">
                <div class="alert alert-block alert-error fade in" style="width: 500px">
                    <button type="button" class="close" data-dismiss="alert">×</button>
                    ${erreurCnx}
                </div>
            </c:if>
            <form action="./connexion" method="POST" >
                <div class="control-group">
                    <label class="control-label" for="inputEmail">Email</label>
                    <div class="controls">
                        <input type="text" id="email" name="email" class="span3" required placeholder="Email">
                    </div>
                </div>
                <div class="control-group">
                    <label class="control-label" for="inputPassword">Mot de passe</label>
                    <div class="controls">
                        <input type="password" id="mdp" name="mdp" class="span3" required placeholder="Mot de passe">
                    </div>
                </div>
                <div class="control-group">
                    <div class="controls">
                        <button type="submit" class="btn btn-primary">Se connecter</button>
                    </div>
                </div>
                <input type="hidden" name="cnx" value="1"/>
            </form>
            <c:if test="${erreurInsc !=null}">
                <div class="alert alert-block alert-error fade in" style="width: 500px">
                    <button type="button" class="close" data-dismiss="alert">×</button>
                    ${erreurInsc}
                </div>
            </c:if>
            <form action="./connexion" method="POST" >
                <div class="control-group">
                    <label class="control-label" for="inputEmail">Nom</label>
                    <div class="controls">
                        <input type="text" name="nom" id="nom" class="span3" required placeholder="Nom" />
                    </div>
                </div>
                <div class="control-group">
                    <label class="control-label" for="inputEmail">Prénom</label>
                    <div class="controls">
                        <input type="text" name="prenom" id="prenom" class="span3" required placeholder="Prénom" />
                    </div>
                </div>
                <div class="control-group">
                    <label class="control-label" for="inputEmail">E-mail</label>
                    <div class="controls">
                        <input type="email" name="email1" class="span3" id="email1" required placeholder="E-mail"/><span rel="tooltip" id="emailCnt"></span>
                    </div>
                </div>
                <div class="control-group">
                    <label class="control-label" for="inputEmail">Mot de Passe</label>
                    <div class="controls">
                        <input type="password" name="mdp1" class="span3" id="mdp1" placeholder="Mot de Passe" required step="6"/>
                    </div>
                </div>
                <div class="control-group">
                    <div class="controls">
                        <button class="btn btn-primary" ><b>S'inscrire</b></button>
                    </div>
                </div>
                <input type="hidden" name="cnx" value="0"/>
            </form>
        </div>
    </body>
</html>
