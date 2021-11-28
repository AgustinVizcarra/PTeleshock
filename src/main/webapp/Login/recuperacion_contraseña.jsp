<%--
  Created by IntelliJ IDEA.
  User: casa
  Date: 5/11/2021
  Time: 18:47
  To change this template use File | Settings | File Templates.
--%>
<jsp:useBean id="nombre" type="java.lang.String" scope="request" class="java.lang.String"/>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="en">
<head>
    <meta charset="utf-8"/>
    <meta http-equiv="X-UA-Compatible" content="IE=edge"/>
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no"/>
    <title>Password Reset - SB Admin</title>
    <link href="css/style.css" rel="stylesheet"/>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.3/js/all.min.js"
            crossorigin="anonymous"></script>
</head>
<body class="sb-nav-fixed"
      style="background-image: url('img/Fondo_login.jpg'); background-repeat:no-repeat; background-size: cover">
<nav class="sb-topnav navbar navbar-expand" style="background-color: #00152D; opacity: 0.9;">
    <!-- Navbar Brand-->
    <a class="navbar-brand ps-3" style="color: white" href="<%= request.getContextPath()%>/Login"><img margin-right class="icon"
                                                                             src="img/logo_final.png">eleshock</a>
</nav>
<div id="layoutAuthentication">
    <div id="layoutAuthentication_content">
        <main>
            <div class="container">
                <div class="row justify-content-center">
                    <div class="col-lg-5">
                        <br><br><br><br><br>
                        <div class="card shadow-lg border-0 rounded-lg mt-5">
                            <div class="card-header text-white" style="background-color:black"><h3
                                    class="text-center font-weight-light my-4">Ingresa tu nueva contraseña</h3></div>
                            <div class="card-body">
                                <div class="medium mb-3 text-muted">Crea una nueva contraseña</div>
                                <form method="post" action="<%= request.getContextPath()%>/Login_Password_Recovery" oninput='pwd2.setCustomValidity(pwd2.value != pwd1.value ? "Las contraseñas no coinciden" : "" )'>
                                    <input type="hidden" name="user_mail" value="<%=nombre%>">
                                    <div class="form-floating mb-3">
                                        <input class="form-control" id="inputPassword" type="password" name="pwd1" placeholder="name@example.com" pattern="(?=.*\d)(?=.*[a-z])(?=.*[A-Z]).{8,}" title="La contraseña debe contener como mínimo 8 caracteres, 1 número, una letra mayúscula"/>
                                        <label for="inputPassword" class="mb-3 text-muted">Contraseña</label>
                                    </div>
                                    <div class="form-floating mb-3">
                                        <input class="form-control" id="inputPassword1" type="password"
                                                name="pwd2" pattern="(?=.*\d)(?=.*[a-z])(?=.*[A-Z]).{8,}" placeholder="name@example.com" title="La contraseña debe contener como mínimo 8 caracteres, 1 número, una letra mayúscula"/>
                                        <label for="inputPassword1" class="mb-3 text-muted">Confirmar contraseña</label>
                                    </div>
                                    <div class="d-grid gap-2 col-6 mx-auto mb-3">
                                        <button class="btn btn-success" href="#popup1" type="submit">Actualizar</button>
                                        <a class="small text-center" href="<%= request.getContextPath()%>/Login">Volver</a>
                                    </div>
                                </form>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </main>
    </div>
</div>

<nav id="popup1" class="overlay">
    <div class=" popup card text-center ">
        <h5 class="card-header text-center text-light">Teleshock</h5>
        <div class="card-body">
            <h5 class="card-title p-2">¡Se registró con éxito la contraseña!</h5>
            <!--<p>Se ha enviado un correo para confirmar el cambio de contraseña</p>-->
            <a href="<%= request.getContextPath()%>/Login" class="btn btn-success mb-2">Ir a la página de ingreso</a>
        </div>
    </div>
</nav>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.0/dist/js/bootstrap.bundle.min.js"
        crossorigin="anonymous"></script>
<script src="js/scripts.js"></script>
</body>
</html>
