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