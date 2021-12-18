
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<jsp:useBean id="mensajealerta" scope="session" class="java.lang.String" type="java.lang.String"/>
<jsp:useBean id="mail" scope="session" class="java.lang.String" type="java.lang.String"/>
<jsp:useBean id="pwd" scope="session" class="java.lang.String" type="java.lang.String"/>
<html lang="en" xmlns:background-color="http://www.w3.org/1999/xhtml" xmlns: xmlns:>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
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
    <style>
        #popup:target{
            visibility: hidden; /* Se regresa a hidden para ocultar */
            opacity: 0;  /*Se regresa a o para hacerlo "invisible" */
        }
        .overlay1{display: flex; justify-content: center ; align-items: center; position: fixed;
            top: 0;
            bottom: 0;
            left: 0;
            right: 0;
            background: rgba(0, 0, 0, 0.7);
            background-repeat: repeat-y;
            transition: opacity 500ms;
            visibility: visible;
            opacity: 1} </style>
</head>
<body class="sb-nav-fixed"
      style="background-image: url('img/Fondo_login.jpg'); background-repeat:repeat-y; background-size: cover">

<nav class="sb-topnav navbar navbar-expand" style="background-color: #00152D; opacity: 0.9;">
    <!-- Navbar Brand-->
    <a class="navbar-brand ps-3" style="color: white" href="<%= request.getContextPath()%>/Login"><img margin-right class="icon"
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
                                    <form method="post" action="<%= request.getContextPath()%>/Login?action=login">
                                        <div class="row mb-4" >
                                            <label for="inputEmail" class="col-sm-4 col-form-label">Correo: </label>
                                            <div class="col-sm-8">
                                                <input type="email" class="form-control" id="inputEmail" name="correo" pattern="^([\w-]+(?:\.[\w-]+)*)@((?:[\w-]+\.)*\w[\w-]{0,66})\.([a-z]{2,6}(?:\.[a-z]{2})?)$" title="Debe especificar una ruta de correo valida"
                                                value="<%=mail%>">
                                            </div>
                                        </div>
                                        <div class="row mb-4">
                                            <label for="inputPassword" class="col-sm-4 col-form-label">Contraseña: </label>
                                            <div class="col-sm-8">
                                                <input type="password" class="form-control"
                                                       id="inputPassword" name="pass" value="<%=pwd%>">
                                            </div>
                                        </div>
                                        <div class="d-grid gap-3 col-6 mx-auto mb-3">
                                            <button class="btn btn-primary me-md-7" href="#popup1" type="submit">Ingresar</button>
                                            <a class="btn btn-success me-md-7"
                                               href="<%= request.getContextPath()%>/Login_Register?action=anadir">Registrarse</a>
                                        </div>
                                        <div class="text-center mb-2">
                                            <a class="small text-white" href="<%= request.getContextPath()%>/Login_Password">¿Olvidaste tu
                                                contraseña?</a>
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
<%if(!mensajealerta.equalsIgnoreCase("")){%>
<nav id="popup" class="overlay1">
    <div class=" popup card text-center ">
        <h5 class="card-header text-center">Teleshock</h5>
        <div class="card-body">
            <h5 class="card-title p-2"><%=mensajealerta.equalsIgnoreCase("a")?"Se ha ingresado una contraseña o dirección de correo invalidos.":"La farmacia se encuentra bloqueada, por favor ponerse en contacto con el Administrador."%></h5>
            <!--<p>Se ha enviado un correo para confirmar el cambio de contraseña</p>-->
            <%HttpSession session1 = request.getSession();
              session1.removeAttribute("mensajealerta");%>
            <%System.out.println(mensajealerta);%>
            <a href="<%= request.getContextPath()%>/Login" class="btn btn-success mb-2">Intentar nuevamente</a>
        </div>
    </div>
</nav>
<%}%>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.0/dist/js/bootstrap.bundle.min.js"
        crossorigin="anonymous"></script>
<script src="js/scripts.js"></script>
</body>
</html>
