<%--
  Created by IntelliJ IDEA.
  User: casa
  Date: 5/11/2021
  Time: 18:43
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="css/style.css">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.1/dist/css/bootstrap.min.css"
          rel="stylesheet" crossorigin="anonymous"
          integrity="sha384-F3w7mX95PdgyTmZZMECAngseQB83DfGTowi0iMjiWaeVhAn4FJkqJByhZMI3AhiU">
    <script src="https://kit.fontawesome.com/a8c05a362e.js" crossorigin="anonymous"></script>
</head>
<body class="sb-nav-fixed"
      style="background-image: url('img/Fondo_login.jpg'); background-repeat:repeat-y; background-size: cover">

<nav class="sb-topnav navbar" style="background-color: #182b41; opacity: 0.9">
    <div style="background: #182b41 ; opacity: 0.9">
        <div>
            <a href="<%= request.getContextPath()%>/Login_Password_Recovery">
                <img src="img/new_logo.png" width="70%" alt="perfil">
            </a>
        </div>
    </div>
</nav>

<div>
    <div class="sobre">
        <div class="col-lg-12">
            <div class="card shadow-lg border-0 rounded-lg mt-5" style="margin-left: 500px;margin-right: 450px">
                <div class="card-header" style="width: 600px"><h3 class="text-center font-weight-light my-2">Mensaje</h3></div>
                <div class="card-body" style="height: 300px;width: 600px">
                    <form class="text-center">
                        <p></p><br>
                        <h5>¿Desea cerrar está sesión?</h5>
                        <div class="d-md-block mt-5 mb-4 text-center font-weight-light my-5">
                            <a class="btn btn-primary me-md-4" href="<%= request.getContextPath()%>/Login">Cancelar</a>
                            <a class="btn btn-danger me-md-4" href="mensajes_emergentes4.html">Salir</a>
                            <!--no se esta considerando estos mensajes emergentes-->
                        </div>
                    </form>
                </div>
            </div>
        </div>
    </div>
</div>

</body>
</html>
