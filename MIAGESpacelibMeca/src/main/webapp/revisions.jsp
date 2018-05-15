<%-- 
    Document   : revisions
    Created on : 15 mai 2018, 17:18:17
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
              <h1 class="display-3">Spacelib Meca</h1>
            </div>
        </div>
        <div class="container">
            <div class="row">
                <div class="col-lg-6 col-md-6 col-sm-12 col-xs-12 offset-lg-3 offset-md-3 offset-sm-6 offset-xs-6">
                    <div class="text-center mb-4" style="margin-bottom: 1rem;">
                        <h1 class="h3 mb-3 font-weight-normal">Navettes nécéssitant une révision :</h1>
                    </div>
                    <%
                    String [] stations = (String [])request.getAttribute("stations_revision");
                    if (stations.length == 0) {
                        out.println("<div class=\"text-center mb-4\" style=\"margin-bottom: 1rem;\">");
                        out.println("<p>Aucune navette ne nécéssite une révision.");
                        out.println("</div>");
                        
                    }
                    else {
                        out.println("<form  method=\"POST\" action=\"initierRevision\">");
                        out.println("<div class=\"form-label-group\" style=\"margin-bottom: 1rem;\">");
                        out.println("<p>Quelle navette souhaitez vous mettre en révision ?</p>");
                        out.println("<select class=\"form-control\" name=\"navette\">");
                        for (int i=0; i<stations.length; i++) {
                            out.println("<option value="+stations[i]+"> Navette n°"+stations[i]+"</option>");
                            out.println(stations[i]);
                        }
                        out.println("</select>");
                        out.println("<input type=\"hidden\" name=\"id\" value="+request.getAttribute("id")+">");
                        out.println("<input type=\"hidden\" name=\"station\" value="+request.getAttribute("station")+">");
                        out.println("</div>");
                        out.println("<button class=\"btn btn-lg btn-primary btn-block\" type=\"submit\">Valider</button>");
                    }
                    %>
                </div>
            </div>
        </div>
    </body>
</html>