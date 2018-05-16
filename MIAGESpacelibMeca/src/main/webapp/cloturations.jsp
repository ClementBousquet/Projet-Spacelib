<%-- 
    Document   : cloturations
    Created on : 15 mai 2018, 21:24:11
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
        <div class="jumbotron">
            <div class="container">
              <h1 class="display-3">Spacelib Meca</h1>
            </div>
        </div>
        <div class="container">
            <div class="row">
                <div class="col-lg-6 col-md-6 col-sm-12 col-xs-12 offset-lg-3 offset-md-3 offset-sm-6 offset-xs-6">
                    <form method="POST" action="cloturation">
                        <input type="hidden" name="id" value="<%out.print(request.getAttribute("id"));%>">
                        <input type="hidden" name="station" value="<%out.print(request.getAttribute("station"));%>">
                        <input type="hidden" name="nav" value="<%out.print(request.getAttribute("nav"));%>">
                        <button class="btn btn-lg btn-primary btn-block" type="submit">Cloturer</button>
                    </form>
                    <a href="/MIAGESpacelibMeca">Déconnexion</a>
                </div>
            </div>
        </div>
    </body>
</html>
