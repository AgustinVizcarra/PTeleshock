
<%@ page import="java.util.ArrayList" %>
<jsp:useBean id="adminSession" scope="session" class="pe.edu.pucp.pteleshock.Beans.BUsuario"/>
<%@ page import="java.text.SimpleDateFormat" %>
<%@ page import="java.util.Date" %>
<%@ page import="pe.edu.pucp.pteleshock.Beans.BDistrito" %><%--
  Created by IntelliJ IDEA.
  User: casa
  Date: 5/11/2021
  Time: 18:40
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%ArrayList<BDistrito> distritos = (ArrayList<BDistrito>) request.getAttribute("distritos");%>
<jsp:useBean type="java.lang.String" scope="request" id="mensaje" class="java.lang.String"/>
<jsp:useBean type="java.lang.String" scope="request" id="nombre" class="java.lang.String"/>
<jsp:useBean type="java.lang.String" scope="request" id="ruc" class="java.lang.String"/>
<jsp:useBean type="java.lang.String" scope="request" id="correo" class="java.lang.String"/>
<jsp:useBean type="java.lang.String" scope="request" id="direccion" class="java.lang.String"/>
<jsp:useBean type="java.lang.String" scope="request" id="id_distrito" class="java.lang.String"/>
<html lang="en">
<head>
    <meta charset="utf-8"/>
    <meta http-equiv="X-UA-Compatible" content="IE=edge"/>
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no"/>
    <meta name="description" content=""/>
    <meta name="author" content=""/>
    <title>Añadir Farmacia</title>
    <link href="https://cdn.jsdelivr.net/npm/simple-datatables@latest/dist/style.css" rel="stylesheet" type="text/css"/>
    <link href="css/styles.css" rel="stylesheet" type="text/css"/>
    <!--Sirve para iconos-->
    <script src="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.3/js/all.min.js"
            crossorigin="anonymous"></script>
    <!--Sirve para fuentes(p.e Lilita one)-->
    <link rel="preconnect" href="https://fonts.googleapis.com">
    <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
    <link href="https://fonts.googleapis.com/css2?family=Lilita+One&display=swap" rel="stylesheet">
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
<body class="sb-nav-fixed">
<nav class="sb-topnav navbar navbar-expand navbar-dark bg-dark">
    <!-- Navbar Brand-->
    <!-- Sidebar Toggle-->
    <button class="btn btn-link btn-sm order-1 order-lg-0 me-4 me-lg-0" id="sidebarToggle" href="#!">
        <svg class="svg-inline--fa fa-bars fs-4" aria-hidden="true" focusable="false" data-prefix="fas"
             data-icon="bars" role="img" xmlns="http://www.w3.org/2000/svg" viewBox="0 0 448 512"
             data-fa-i2svg="">
            <path fill="#FFFFFF"
                  d="M16 132h416c8.837 0 16-7.163 16-16V76c0-8.837-7.163-16-16-16H16C7.163 60 0 67.163 0 76v40c0 8.837 7.163 16 16 16zm0 160h416c8.837 0 16-7.163 16-16v-40c0-8.837-7.163-16-16-16H16c-8.837 0-16 7.163-16 16v40c0 8.837 7.163 16 16 16zm0 160h416c8.837 0 16-7.163 16-16v-40c0-8.837-7.163-16-16-16H16c-8.837 0-16 7.163-16 16v40c0 8.837 7.163 16 16 16z"></path>
        </svg><!-- <i class="fas fa-bars"></i> Font Awesome fontawesome.com --></button>
    <!-- Navbar Search-->
    <a class="navbar-brand ps-3" style="color: white"><img style="width: 30px;height: 30px" class="icon"
                                                           src="img/logo_final.png">eleshock</a>
    <!-- Navbar-->
    <form class="d-none d-md-inline-block form-inline ms-auto me-0 me-md-3 my-2 my-md-0">
        <div class="input-group">
            <div class="position-absolute top-50 start-100 translate-middle" style="padding-right:5%">
                <ul class="navbar-nav ms-auto ms-md-0 me-3 me-lg-4">
                    <li class="nav-item dropdown">
                        <a class="nav-link dropdown-toggle" id="navbarDropdown" href="#" role="button"
                           data-bs-toggle="dropdown" aria-expanded="false">
                            <svg class="svg-inline--fa fa-user fa-lg fa-w-14 fa-fw" aria-hidden="true"
                                 focusable="false"
                                 data-prefix="fas" data-icon="user" role="img"
                                 xmlns="http://www.w3.org/2000/svg"
                                 viewBox="0 0 448 512" data-fa-i2svg="">
                                <path fill="#FFFFFF"
                                      d="M224 256c70.7 0 128-57.3 128-128S294.7 0 224 0 96 57.3 96 128s57.3 128 128 128zm89.6 32h-16.7c-22.2 10.2-46.9 16-72.9 16s-50.6-5.8-72.9-16h-16.7C60.2 288 0 348.2 0 422.4V464c0 26.5 21.5 48 48 48h352c26.5 0 48-21.5 48-48v-41.6c0-74.2-60.2-134.4-134.4-134.4z"></path>
                            </svg><!-- <i class="fas fa-user fa-fw"></i> Font Awesome fontawesome.com --></a>
                        <ul class="dropdown-menu dropdown-menu-end" aria-labelledby="navbarDropdown">
                            <li><a class="dropdown-item" href="#!">Settings</a></li>
                            <li><a class="dropdown-item" href="#!">Activity Log</a></li>
                            <li>
                                <hr class="dropdown-divider"/>
                            </li>
                            <li><a class="dropdown-item" href="#!">Logout</a></li>
                        </ul>
                    </li>
                </ul>
            </div>
        </div>
    </form>
</nav>
<div id="layoutSidenav">
    <div id="layoutSidenav_nav">
        <nav class="sb-sidenav accordion sb-sidenav-dark" id="sidenavAccordion" style="opacity:0.9">
            <div class="sb-sidenav-menu">
                <div class="nav texto-navbar">
                    <div class="sb-sidenav-menu-heading">Lista de opciones</div>
                    <a class="nav-link" href="<%= request.getContextPath()%>/Admin_SearchFarm">
                        <div class="sb-nav-link-icon"><i class="fas fa-clinic-medical"></i></div>
                        Buscar farmacia
                    </a>
                    <div class="collapse" id="collapseLayouts" aria-labelledby="headingOne"
                         data-bs-parent="#sidenavAccordion">
                    </div>
                    <div class="collapse" id="collapsePages" aria-labelledby="headingTwo"
                         data-bs-parent="#sidenavAccordion">
                    </div>
                    <a class="nav-link" href="<%= request.getContextPath()%>/Admin_BloqFarm">
                        <div class="sb-nav-link-icon"><i class="fas fa-lock"></i></div>
                        <font>Bloquear farmacias</font>
                    </a>
                    <a class="nav-link" href="<%= request.getContextPath()%>/Admin_UnlockFarm">
                        <div class="sb-nav-link-icon"><i class="fas fa-lock-open"></i></div>
                        Desbloquear farmacias
                    </a>
                    <a class="nav-link" href="<%= request.getContextPath()%>/Admin_Admins">
                        <div class="sb-nav-link-icon"><i class="fas fa-user-shield"></i></div>
                        Ver administradores
                    </a>
                    <a class="nav-link" href="<%= request.getContextPath()%>/Admin_Hist">
                        <div class="sb-nav-link-icon"><i class="far fa-clipboard"></i></div>
                        Ver historial
                    </a>
                    <a class="nav-link" href="<%= request.getContextPath()%>/Admin_AddFarm">
                        <div class="sb-nav-link-icon"><i class="fas fa-plus-circle"></i></div>
                        Añadir farmacias
                    </a>
                </div>
            </div>
            <div class="sb-sidenav-footer">
                <div class="small"><font color="#00152D">Logueado como:</font></div>
                <font color="#00152D"><%=adminSession.getNombre()%></font>
            </div>
        </nav>
    </div>
    <div id="layoutSidenav_content">
        <main class="clase_añadir-farmacia">
            <div class="container-fluid px-4">
                <div class="row">
                    <div class="container">
                        <div class="row justify-content-center" style="padding-top: 45px">
                            <div class="col-lg-5">
                                <div class="card shadow-lg border-0 rounded-lg mt-5" style="border-top-left-radius: 2rem; border-top-right-radius: 2rem;
                                border-bottom-left-radius: 2rem; border-bottom-right-radius: 2rem;">
                                    <div>
                                        <img src="img/encabezado_añadir-farmacia.png" width="100%"
                                             style="border-top-left-radius: 2rem; border-top-right-radius: 2rem">
                                    </div>
                                    <div class="card-body"
                                         style="background-color: #9AB0E0; border-bottom-left-radius: 2rem; border-bottom-right-radius: 2rem;">
                                        <form method="post" action="<%= request.getContextPath()%>/Admin_AddFarm">
                                            <input type="hidden" name="admin" value="<%=adminSession.getNombre()%>">
                                            <!--Luego se hara de manera dinamica-->
                                            <div class="form-floating mb-3">
                                                <input class="form-control" id="inputNombre_Farmacia" type="text"
                                                       placeholder="Ingrese su Nombre" name="nombre_farmacia" value="<%=nombre%>">
                                                <label for="inputNombre_Farmacia">Nombre</label>
                                            </div>
                                            <div class="form-floating mb-3">
                                                <input class="form-control" id="inputRUC_Farmacia" type="text"
                                                       placeholder="Ingrese su numero de RUC" name="ruc_farmacia" value="<%=ruc%>" pattern="[0-9]{10}" title="El RUC debe contener 10 dígitos.">
                                                <label for="inputRUC_Farmacia">RUC</label>
                                            </div>
                                            <select class="form-select mb-3" name="distrito_farmacia" id="inputDistrito_Farmacia" value="<%=id_distrito%>">
                                                <option selected disabled value="0">Seleccione un distrito</option>
                                                <%for(BDistrito distrito:distritos){%>
                                                <option value="<%= distrito.getId_distrito()%>"><%=distrito.getNombre()%></option>
                                                <%}%>
                                            </select>
                                            <div class="form-floating mb-3">
                                                <input class="form-control" id="inputCorreo" type="email"
                                                       placeholder="name@example.com" name="correo_farmacia" value="<%=correo%>">
                                                <label for="inputCorreo">Correo</label>
                                            </div>
                                            <div class="form-floating mb-3">
                                                <input class="form-control" id="inputDireccion" type="text"
                                                       placeholder="Ingrese su Dirección" name="direccion_farmacia" value="<%=direccion%>">
                                                <label for="inputDireccion">Dirección</label>
                                            </div>
                                            <div class="mt-4 mb-0" style="align-content: center">
                                                <div class="d-grid" style="align-content: center">
                                                    <!--
                                                    <a class="btn btn-primary" type="submit"
                                                       style="background-color: #454582;
                                                     width: fit-content; margin-left: auto; margin-right: auto;">GRABAR
                                                        Y CONTINUAR</a>
                                                      -->
                                                    <!--href="#popup1"-->
                                                    <button class="btn btn-success" href="#popup1" type="submit" style="background-color: #454582">
                                                        Grabar y continuar
                                                    </button>
                                                </div>
                                            </div>
                                        </form>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="col-xl-3 col-md-8">
                    </div>
                    <div>
                        <a class="stretched-link;nav-link dropdown toogle" role="button" aria-expanded="false"
                           href="<%=request.getContextPath()%>/Admin_Index">
                            <div class="boton_añadir_escape"><i class="fas fa-door-open fs-3"></i></div>
                        </a>
                    </div>
                </div>
            </div>
        </main>
        <footer class="py-4 bg-light mt-auto" style="background: #9AB0E0">
            <div class="container-fluid px-4">
                <div class="d-flex align-items-center justify-content-between small">
                    <div class="text-muted">Teleshock.Inc &copy; 2021</div>
                    <div>
                        <a href="#">Politica y privacidad</a>
                        &middot;
                        <a href="#">Terminos & condiciones</a>
                    </div>
                </div>
            </div>
        </footer>
    </div>
</div>

<% if(mensaje.equalsIgnoreCase("Se logro registrar de manera correcta")){%>
<nav id="popup" class="overlay1">
    <div class=" popup card text-center " style="border: 1px solid #38B6FF">
        <h5 class="card-header text-center">Teleshock</h5>
        <div class="card-body">
            <h5 class="card-title p-2">¡Farmacia añadida correctamente!</h5>
            <a href="<%=request.getContextPath()%>/Admin_AddFarm" class="btn btn-info mb-2">Añadir una nueva farmacia</a>
            <a href="<%=request.getContextPath()%>/Admin_Index" class="btn btn-primary mb-2">Ir al Menú Principal</a>
        </div>
    </div>
</nav>
<%}else if(!mensaje.equalsIgnoreCase("Se logro registrar de manera correcta") && !mensaje.equalsIgnoreCase("")){%>
<nav id="popup" class="overlay1">
    <div class=" popup card text-center " style="border: 1px solid #38B6FF">
        <h5 class="card-header text-center">Teleshock</h5>
        <div class="card-body">
            <h5 class="card-title p-2"><%= mensaje%></h5>
            <a href="#popup" class="btn btn-danger mb-2">Volver a intentar</a> <!--Importante-->
            <a href="<%=request.getContextPath()%>/Admin_Index" class="btn btn-primary mb-2">Ir al Menú Principal</a>
        </div>
    </div>
</nav>
<%}else{%>
<%}%>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.0/dist/js/bootstrap.bundle.min.js"
        crossorigin="anonymous"></script>
<script src="js/scripts.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/Chart.js/2.8.0/Chart.min.js"
        crossorigin="anonymous"></script>
<script src="assets/demo/chart-area-demo.js"></script>
<script src="assets/demo/chart-bar-demo.js"></script>
<script src="https://cdn.jsdelivr.net/npm/simple-datatables@latest" crossorigin="anonymous"></script>
<script src="js/datatables-simple-demo.js"></script>
</body>
</html>
