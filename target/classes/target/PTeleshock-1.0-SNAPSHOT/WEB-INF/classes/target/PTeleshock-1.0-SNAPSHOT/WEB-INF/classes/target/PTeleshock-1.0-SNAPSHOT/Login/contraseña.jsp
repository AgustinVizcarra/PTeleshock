<%--
  Created by IntelliJ IDEA.
  User: casa
  Date: 5/11/2021
  Time: 18:42
  To change this template use File | Settings | File Templates.
--%>
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
                                    class="text-center font-weight-light my-4">¿No recuerdas tu contraseña?</h3>
                            </div>
                            <div class="card-body">
                                <div class="medium mb-3 text-muted">¡No te preocupes! Nos sucede a todos.
                                    Ingresa el e-mail con el que te registraste y te ayudaremos.
                                </div>
                                <form>
                                    <div class="form-floating mb-3">
                                        <input class="form-control" id="inputEmail" type="email"
                                               placeholder="name@example.com"/>
                                        <label for="inputEmail" class="mb-3 text-muted">Correo
                                            electrónico</label>
                                    </div>
                                    <div class="d-grid gap-2 col-6 mx-auto mb-3">
                                        <a class="btn btn-success" href="<%= request.getContextPath()%>/Login_Password_Recovery">Solicitar</a>
                                        <a class="small text-center" href="<%= request.getContextPath()%>/Login">Inicio de sesión</a>
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


<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.0/dist/js/bootstrap.bundle.min.js"
        crossorigin="anonymous"></script>
<script src="js/scripts.js"></script>
</body>
</html>

