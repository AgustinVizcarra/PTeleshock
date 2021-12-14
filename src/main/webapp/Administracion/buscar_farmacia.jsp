<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<jsp:useBean id="adminSession" scope="session" class="pe.edu.pucp.pteleshock.Beans.BUsuario"/>
<jsp:useBean type="java.lang.String" scope="request" id="mensaje" class="java.lang.String"/>
<jsp:useBean type="java.lang.String" scope="request" id="rucListado" class="java.lang.String"/>
<html lang="en">
    <head>
        <meta charset="utf-8"/>
        <meta http-equiv="X-UA-Compatible" content="IE=edge"/>
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no"/>
        <meta name="description" content=""/>
        <meta name="author" content=""/>
        <title>Buscar Farmacia</title>
        <link href="https://cdn.jsdelivr.net/npm/simple-datatables@latest/dist/style.css" rel="stylesheet"/>
        <link href="css/styles.css" rel="stylesheet"/>
        <!--Sirve para iconos-->
        <script src="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.3/js/all.min.js"
                crossorigin="anonymous"></script>
        <!--Sirve para fuentes(p.e Lilita one)-->
        <link rel="preconnect" href="https://fonts.googleapis.com">
        <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
        <link href="https://fonts.googleapis.com/css2?family=Lilita+One&display=swap" rel="stylesheet">
        <style>
            #popup:target {
                visibility: hidden; /* Se regresa a hidden para ocultar */
                opacity: 0; /*Se regresa a o para hacerlo "invisible" */
            }

            .overlay1 {
                display: flex;
                justify-content: center;
                align-items: center;
                position: fixed;
                top: 0;
                bottom: 0;
                left: 0;
                right: 0;
                background: rgba(0, 0, 0, 0.7);
                background-repeat: repeat-y;
                transition: opacity 500ms;
                visibility: visible;
                opacity: 1
            }

            .overlay_logout {
                display: flex;
                justify-content: center;
                align-items: center;
                position: fixed;
                top: 0;
                bottom: 0;
                left: 0;
                right: 0;
                background: rgba(0, 0, 0, 0.7);
                background-repeat: repeat-y;
                transition: opacity 500ms;
                visibility: hidden;
                opacity: 0;
            }

            .overlay_logout:target {
                visibility: visible; /* Se regresa a hidden para ocultar */
                opacity: 1; /*Se regresa a o para hacerlo "invisible" */
            }

        </style>
    </head>
    <body class="sb-nav-fixed">
        <nav class="sb-topnav navbar navbar-expand navbar-dark bg-dark">
            <!-- Navbar Brand-->
            <a class="navbar-brand ps-3" style="color: white"><img style="width: 30px;height: 30px" class="icon"
                                                                   src="img/logo_final.png">eleshock</a>
            <!-- Sidebar Toggle-->
            <button class="btn btn-link btn-sm order-1 order-lg-0 me-4 me-lg-0" style="color: white" id="sidebarToggle"
                    href="#!"><i
                    class="fas fa-bars"></i></button>
            <!-- Navbar Search-->
            <form class="d-none d-md-inline-block form-inline ms-auto me-0 me-md-3 my-2 my-md-0">
                <div class="input-group">

                </div>
            </form>
            <!-- Navbar-->
            <ul class="navbar-nav ms-auto ms-md-0 me-3 me-lg-4">
                <li class="nav-item">
                    <a class="nav-link" style="color: white" href="#popup_logout" role="button"><i
                            class="fas fa-sign-out-alt"></i></a>
                </li>
            </ul>
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
                        <font color="#00152D"><%=adminSession.getNombre()%>
                        </font>
                    </div>
                </nav>
            </div>
            <div id="layoutSidenav_content">
                <main class="clase_buscar-farmacia" style="height: 91.5%">
                    <div class="container-fluid px-4">
                        <div class="row">
                            <div class="container">
                                <br>
                                <div class="row justify-content-center" style="padding-top: 45px">
                                    <div class="col-lg-5">
                                        <div class="card shadow-lg border-0 rounded-lg mt-5" style="border-top-left-radius: 2rem; border-top-right-radius: 2rem;
                                border-bottom-left-radius: 2rem; border-bottom-right-radius: 2rem;">
                                            <div>
                                                <img src="img/encabezado_buscar-farmacia.png" width="100%"
                                                     style="border-top-left-radius: 2rem; border-top-right-radius: 2rem">
                                            </div>

                                            <div class="card-body"
                                                 style="background-color: #FA9F9F; border-bottom-left-radius: 2rem; border-bottom-right-radius: 2rem;">
                                                <form method="post"
                                                      action="<%=request.getContextPath()%>/Admin_SearchFarm">
                                                    <br>
                                                    <div class="form-floating mb-3">
                                                        <% if (rucListado != "") {%>
                                                        <input class="form-control" id="inputRUC" type="text" name="ruc"
                                                               placeholder="Ingrese su numero de RUC"
                                                               value="<%=rucListado%>">
                                                        <%} else {%>
                                                        <input class="form-control" id="inputRUC" type="text" name="ruc"
                                                               placeholder="Ingrese su numero de RUC">
                                                        <%}%>
                                                        <label for="inputRUC">RUC</label>
                                                    </div>
                                                    <div class="mt-4 mb-0" style="align-content: center">
                                                        <div class="d-grid" style="align-content: center">
                                                            <button class="btn btn-primary" type="submit" style="margin-top: 15px; margin-bottom: 15px; background-color: #E24E34; width: fit-content;
                                                            margin-left: auto; margin-right: auto" href="#popup1">
                                                                BUSCAR
                                                            </button>
                                                        </div>
                                                    </div>

                                                </form>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                                <br>
                            </div>
                            <div class="row">
                                <br>
                                <div class="col" style="position: absolute; bottom: 0; right: 0;">
                                    <a class="stretched-link;nav-link dropdown toogle" role="button"
                                       aria-expanded="false"
                                       href="<%= request.getContextPath()%>/Admin_Index">
                                        <div class="boton_añadir_escape"><i class="fas fa-door-open fs-3"></i></div>
                                    </a>
                                </div>
                            </div>
                        </div>
                    </div>
                </main>
                <footer class="py-4 bg-light mt-auto" style="background: #FA9F9F; height: 8.5%">
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

        <% if (!mensaje.equalsIgnoreCase("")) {%>
        <nav id="popup" class="overlay1">
            <div class=" popup card text-center " style="border: 1px solid #38B6FF">
                <h5 class="card-header text-center">Teleshock</h5>
                <div class="card-body">
                    <h5 class="card-title p-2"><%=mensaje%>
                    </h5>
                    <a href="#popup" class="btn btn-danger mb-2">Intentar
                        nuevamente</a>
                    <a href="<%=request.getContextPath()%>/Admin_Index" class="btn btn-primary mb-2">Ir al Menú
                        Principal</a>
                </div>
            </div>
        </nav>
        <% } %>

        <nav id="popup_logout" class="overlay_logout"
             style="display: flex; justify-content: center ; align-items: center;">
            <div class=" popup card text-center " style="background-color: white">
                <h5 class="card-header text-center">Teleshock</h5>
                <div class="card-body">
                    <h5 class="card-title p-2">¿Desea cerrar esta sesión?</h5>
                    <div class="row align-items-center" style="display: flex; justify-content: center">
                        <a href="#" class="btn btn-primary my-auto col-3" role="button" aria-expanded="true">Cancelar</a>
                        <form class="col-3 my-auto" method="post" action="<%=request.getContextPath()%>/Login?action=logout">
                            <button class="btn btn-danger" style=" background-color: #5bc0de " type="submit">
                                Salir
                            </button>
                        </form>
                    </div>
                </div>
            </div>
        </nav>

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

