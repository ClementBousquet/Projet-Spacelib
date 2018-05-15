<%-- 
    Document   : index
    Created on : 14 mai 2018, 23:12:25
    Author     : clem
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title>Start Page</title>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.3/umd/popper.min.js" integrity="sha384-ZMP7rVo3mIykV+2+9J3UJ46jBk0WLaUAdn689aCwoqbBJiSnjAK/l8WvCWPIPm49" crossorigin="anonymous"></script>
        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.1/css/bootstrap.min.css" integrity="sha384-WskhaSGFgHYWDcbwN70/dfYBj47jz9qbsMId/iRN3ewGhXQFZCSftd1LZCfmhktB" crossorigin="anonymous">
        <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.1.1/js/bootstrap.min.js" integrity="sha384-smHYKdLADwkXOn1EmN1qk/HfnUcbVRZyYmZ4qpPea6sjB/pTJ0euyQp0Mk8ck+5T" crossorigin="anonymous"></script>
    </head>
    <body>
        <div class="jumbotron">
            <div class="container">
              <h1 class="display-3">Spacelib Usager</h1>
            </div>
        </div>
        <div class="container">
            <div class="row">
                <p>Bienvenue chez Spacelib, le service public de transports spatiaux intergalactiques du XIIème siècle.</p>
            </div>
            <div class="row">
                <p><a class="btn btn-primary btn-lg" href="authentification.jsp" role="button" style="margin-right:15px;">Connexion &raquo;</a></p>
                <p><a class="btn btn-primary btn-lg" href="inscription.jsp" role="button">Inscription &raquo;</a></p>
            </div>
        </div>
    </body>
</html>
