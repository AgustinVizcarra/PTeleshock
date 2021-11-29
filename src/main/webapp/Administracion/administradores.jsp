<%--
  Created by IntelliJ IDEA.
  User: casa
  Date: 5/11/2021
  Time: 18:40
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<jsp:useBean id="adminSession" scope="session" class="pe.edu.pucp.pteleshock.Beans.BUsuario"/>
<head>
    <meta charset="utf-8"/>
    <meta http-equiv="X-UA-Compatible" content="IE=edge"/>
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no"/>
    <meta name="description" content=""/>
    <meta name="author" content=""/>
    <title>Administradores</title>
    <link href="https://cdn.jsdelivr.net/npm/simple-datatables@latest/dist/style.css" rel="stylesheet"/>
    <link href="css/styles.css" rel="stylesheet"/>
    <!--Sirve para iconos-->
    <script src="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.3/js/all.min.js"
            crossorigin="anonymous"></script>
    <!--Sirve para fuentes(p.e Lilita one)-->
    <link rel="preconnect" href="https://fonts.googleapis.com">
    <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
    <link href="https://fonts.googleapis.com/css2?family=Lilita+One&display=swap" rel="stylesheet">
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
                            <svg class="svg-inline--fa fa-user fa-lg fa-w-14 fa-fw" aria-hidden="true" focusable="false"
                                 data-prefix="fas" data-icon="user" role="img" xmlns="http://www.w3.org/2000/svg"
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
        <main class="clase_administradores">
            <div class="container-fluid px-4">
                <h1 class="mt-4 text-center texto-central" style="padding-top:50px">Administradores</h1>
                <ol class="breadcrumb mb-4">
                </ol>
                <div class="row">
                    <div class="col-xl-3 col-md-8">
                    </div>
                    <div class="col-xl-3 col-md-8">
                        <div class="card bg-primary text-white mb-4"
                             style="color:#FFFFFF; background: #38B6FF;border-color: #FFFFFF">
                            <div class="texto-navbar"
                                 style="text-align:center;padding-top: 10px;padding-bottom: 20px;color: #00152D;font-size: 25px">
                                <u>Administrador 1</u></div>
                            <div class="row" style="text-align:center;padding-left: 10px">
                                <div class="sb-nav-link-icon"><i class="fas fa-user-circle fa-8x"></i></div>
                            </div>
                            <div class="row" style="text-align:left;padding-left: 10px">
                                <p style="height: 10px"></p>
                                <div class="texto-navbar" style="padding-bottom: 5px;color:#00152D">* Nombre: Admin1
                                </div>
                                <div class="texto-navbar" style="padding-bottom: 5px;color:#00152D">* Apellido:
                                    Admin1_lastname
                                </div>
                                <div class="texto-navbar" style="padding-bottom: 5px;color:#00152D">* Correo:
                                    Admin1@hotmail.com
                                </div>
                                <p style="height: 10px"></p>
                            </div>
                        </div>
                    </div>
                    <div class="col-xl-3 col-md-8">
                        <div class="card bg-primary text-white mb-4"
                             style="color:#FFFFFF; background: #38B6FF;border-color: #FFFFFF">
                            <div class="texto-navbar"
                                 style="text-align:center;padding-top: 10px;padding-bottom: 20px;color:#00152D;font-size: 25px">
                                <u>Administrador 2</u></div>
                            <div class="row" style="text-align:center;padding-left: 10px">
                                <div class="sb-nav-link-icon"><i class="fas fa-user-circle fa-8x"></i></div>
                            </div>
                            <div class="row" style="text-align:left;padding-left: 10px">
                                <p style="height: 10px"></p>
                                <div class="texto-navbar" style="padding-bottom: 5px;color:#00152D">* Nombre: Admin2
                                </div>
                                <div class="texto-navbar" style="padding-bottom: 5px;color:#00152D">* Apellido:
                                    Admin2_lastname
                                </div>
                                <div class="texto-navbar" style="padding-bottom: 5px;color:#00152D">* Correo:
                                    Admin2@hotmail.com
                                </div>
                                <p style="height: 10px"></p>
                            </div>
                        </div>
                    </div>
                    <div class="col-xl-3 col-md-8">
                    </div>
                </div>
                <div class="row">
                    <div class="col-xl-3 col-md-8">
                    </div>
                    <div class="col-xl-3 col-md-8">
                        <div class="card bg-primary text-white mb-4"
                             style="color:#FFFFFF; background: #38B6FF;border-color: #FFFFFF">
                            <div class="texto-navbar"
                                 style="text-align:center;padding-top: 10px;padding-bottom: 20px;color: #00152D;font-size: 25px">
                                <u>Administrador 3</u></div>
                            <div class="row" style="text-align:center;padding-left: 10px">
                                <div class="sb-nav-link-icon"><i class="fas fa-user-circle fa-8x"></i></div>
                            </div>
                            <div class="row" style="text-align:left;padding-left: 10px">
                                <p style="height: 10px"></p>
                                <div class="texto-navbar" style="padding-bottom: 5px;color:#00152D">* Nombre: Admin3
                                </div>
                                <div class="texto-navbar" style="padding-bottom: 5px;color:#00152D">* Apellido:
                                    Admin3_lastname
                                </div>
                                <div class="texto-navbar" style="padding-bottom: 5px;color:#00152D">* Correo:
                                    Admin3@hotmail.com
                                </div>
                                <p style="height: 10px"></p>
                            </div>
                        </div>
                    </div>
                    <div class="col-xl-3 col-md-8">
                        <div class="card bg-primary text-white mb-4"
                             style="color:#FFFFFF; background: #38B6FF;border-color: #FFFFFF">
                            <div class="texto-navbar"
                                 style="text-align:center;padding-top: 10px;padding-bottom: 20px;color: #00152D;font-size: 25px">
                                <u>Administrador 4</u></div>
                            <div class="row" style="text-align:center;padding-left: 10px">
                                <div class="sb-nav-link-icon"><i class="fas fa-user-circle fa-8x"></i></div>
                            </div>
                            <div class="row" style="text-align:left;padding-left: 10px">
                                <p style="height: 10px"></p>
                                <div class="texto-navbar" style="padding-bottom: 5px;color:#00152D">* Nombre: Admin4
                                </div>
                                <div class="texto-navbar" style="padding-bottom: 5px;color:#00152D">* Apellido:
                                    Admin4_lastname
                                </div>
                                <div class="texto-navbar" style="padding-bottom: 5px;color:#00152D">* Correo:
                                    Admin4@hotmail.com
                                </div>
                                <p style="height: 10px"></p>
                            </div>
                        </div>
                    </div>
                    <div class="col-xl-3 col-md-8">
                    </div>
                    <div>
                        <a class="stretched-link;nav-link dropdown toogle" role="button" aria-expanded="false"
                           href="<%= request.getContextPath()%>/Admin_Index">
                            <div class="boton_añadir_escape"><i class="fas fa-door-open fs-3"></i></div>
                        </a>
                    </div>
                </div>
            </div>
        </main>
        <footer class="py-4 bg-light mt-auto">
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
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.0/dist/js/bootstrap.bundle.min.js"
        crossorigin="anonymous"></script>
<script src="js/scripts.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/Chart.js/2.8.0/Chart.min.js"
        crossorigin="anonymous"></script>
<script></script>
<script src="assets/demo/chart-bar-demo.js"></script>
<script src="https://cdn.jsdelivr.net/npm/simple-datatables@latest" crossorigin="anonymous"></script>
<script src="js/datatables-simple-demo.js"></script>
</body>
</html>

