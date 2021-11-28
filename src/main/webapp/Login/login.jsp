<%--
  Created by IntelliJ IDEA.
  User: casa
  Date: 5/11/2021
  Time: 18:43
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="en" xmlns:background-color="http://www.w3.org/1999/xhtml" xmlns: xmlns:>
    <head>
        <meta charset="utf-8"/>
        <meta http-equiv="X-UA-Compatible" content="IE=edge"/>
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no"/>
        <meta name="description" content=""/>
        <meta name="author" content=""/>
        <title>Login</title>
        <link href="css/style.css" rel="stylesheet"/>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.3/js/all.min.js"
                crossorigin="anonymous"></script>
        <script src="https://kit.fontawesome.com/a8c05a362e.js" crossorigin="anonymous"></script>
    </head>
    <body class="sb-nav-fixed"
          style="background-image: url('img/Fondo_login.jpg'); background-repeat:repeat-y; background-size: cover">

        <nav class="sb-topnav navbar navbar-expand" style="background-color: #00152D; opacity: 0.9;">
            <!-- Navbar Brand-->
            <a class="navbar-brand ps-3" style="color: white" href="<%= request.getContextPath()%>/Login"><img
                    margin-right class="icon"
                    src="img/logo_final.png">eleshock</a>
        </nav>


        <div id="layoutAuthentication">
            <div id="layoutAuthentication_content">
                <main>
                    <div class="container mt-5">

                        <div class="card-body">

                            <div class="row justify-content-center p-md-5">
                                <div class="col-md-10 col-lg-5">
                                    <img
                                            src="img/login.jpg"
                                            alt="Sample photo"
                                            class="card-img"
                                    />
                                </div>
                                <div class="col-md-10 col-lg-5">
                                    <div class="card-body p-md-5 text-white"
                                         style="background-color:#00152D; opacity: 0.9;text-align:center;padding-left: 10px">
                                        <div class="card-blue" style="text-align:center;padding-left: 10px">
                                            <a style="color: white">
                                                <i class="fas fa-user-alt" style="font-size: xxx-large"></i>
                                            </a>
                                            <h3 class="text-center font-weight-light my-3">Iniciar Sesión</h3>
                                        </div>
                                        <div class="card-body p-3">
                                            <form method="post"
                                                  action="<%= request.getContextPath()%>/Login?action=login">
                                                <div class="row mb-4">
                                                    <label for="inputEmail"
                                                           class="col-sm-4 col-form-label">Correo: </label>
                                                    <div class="col-sm-8">
                                                        <input type="email" class="form-control" id="inputEmail"
                                                               name="correo">
                                                    </div>
                                                </div>
                                                <div class="row mb-4">
                                                    <label for="inputPassword" class="col-sm-4 col-form-label">Contraseña: </label>
                                                    <div class="col-sm-8">
                                                        <input type="password" class="form-control"
                                                               id="inputPassword" name="pass">
                                                    </div>
                                                </div>

                                                <div class="d-grid gap-3 col-6 mx-auto mb-3">

                                                    <button class="btn btn-primary me-md-7" type="submit">
                                                        Ingresar
                                                    </button>
                                                    <a class="btn btn-success me-md-7"
                                                       href="<%= request.getContextPath()%>/Login?action=register">Registrar</a>
                                                </div>
                                                <%if (request.getParameter("error")!=null){%>
                                                <div>
                                                    error en usuario con contraseña
                                                </div>
                                                <%}%>


                                                <div class="text-center mb-2">
                                                    <a class="small text-white"
                                                       href="<%= request.getContextPath()%>/Login_Password">¿Olvidaste
                                                        tu contraseña?</a>
                                                </div>
                                            </form>
                                        </div>
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
