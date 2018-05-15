<%-- 
    Document   : inscription
    Created on : 15 mai 2018, 14:09:03
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
                <div class="col-lg-6 col-md-6 col-sm-6 col-xs-6 offset-lg-3 offset-md-3 offset-sm-3 offset-xs-3">
                    <form method="POST" action="inscription">
                        <div class="text-center mb-4" style="margin-bottom: 1rem;">
                            <h1 class="h3 mb-3 font-weight-normal">Inscription à Spacelib :</h1>
                            <p>Veuillez remplir les champs suivants.</p>
                        </div>
                        <div class="form-label-group" style="margin-bottom: 1rem;">
                            <input type="text" id="inputNom" name="nom" class="form-control" placeholder="Nom" required autofocus>
                        </div>
                        <div class="form-label-group" style="margin-bottom: 1rem;">
                            <input type="text" id="inputPrenom" name="prenom" class="form-control" placeholder="Prenom" required>
                        </div>
                         <div class="form-label-group" style="margin-bottom: 1rem;">
                            <input type="password" id="inputPassword" name="pass" class="form-control" placeholder="Mot de passe" required>
                        </div>
                        <button class="btn btn-lg btn-primary btn-block" type="submit">Inscription</button>
                    </form>  
                </div>
            </div>
        </div>
    </body>
</html>
