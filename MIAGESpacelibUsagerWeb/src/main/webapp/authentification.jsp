<%-- 
    Document   : authentification
    Created on : 14 mai 2018, 20:42:01
    Author     : clem
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
    <title>Spacelib Authentification</title>
    <script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.3/umd/popper.min.js" integrity="sha384-ZMP7rVo3mIykV+2+9J3UJ46jBk0WLaUAdn689aCwoqbBJiSnjAK/l8WvCWPIPm49" crossorigin="anonymous"></script>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.1/css/bootstrap.min.css" integrity="sha384-WskhaSGFgHYWDcbwN70/dfYBj47jz9qbsMId/iRN3ewGhXQFZCSftd1LZCfmhktB" crossorigin="anonymous">
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.1.1/js/bootstrap.min.js" integrity="sha384-smHYKdLADwkXOn1EmN1qk/HfnUcbVRZyYmZ4qpPea6sjB/pTJ0euyQp0Mk8ck+5T" crossorigin="anonymous"></script>        
    </head>
    <body>
       <form class="form-signin" style="width: 100%;max-width: 420px;padding: 15px;margin: auto;" method="POST" action="authentification">
           <div class="text-center mb-4" style="margin-bottom: 1rem;">
               <h1 class="h3 mb-3 font-weight-normal">Spacelib</h1>
               <p>Veuillez vous identifier au service.</p>
           </div>
           <div class="form-label-group" style="margin-bottom: 1rem;">
               <input type="text" id="inputNomPrenom" name="login" class="form-control" placeholder="nom.prenom" required autofocus>
           </div>
           <div class="form-label-group" style="margin-bottom: 1rem;">
               <input type="password" id="inputPassword" name="pass" class="form-control" placeholder="mot de passe" required>
           </div>
           <button class="btn btn-lg btn-primary btn-block" type="submit">Connexion</button>
           <% if (request.getAttribute("error_login") != null) { 
               out.println("<p style=\"color: red\">"+request.getAttribute("error_login")+"</p>");
           }
           %>
       </form>
    </body>
</html>


