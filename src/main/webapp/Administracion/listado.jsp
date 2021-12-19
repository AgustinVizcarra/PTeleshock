<%@ page import="pe.edu.pucp.pteleshock.Beans.BFarmacia" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<jsp:useBean id="listaFarmacias" scope="request"
             type="java.util.ArrayList<pe.edu.pucp.pteleshock.Beans.BFarmacia>"/>
<!--Bfarmacia-->
<jsp:useBean id="filtrar" scope="request" type="java.lang.Integer"/>
<jsp:useBean id="cantidad" scope="request" type="java.lang.Integer"/>
<jsp:useBean id="pag" scope="request" type="java.lang.String"/>
<jsp:useBean id="distrito" scope="request" type="pe.edu.pucp.pteleshock.Beans.BDistrito"/>
<!--BDistrito-->
<jsp:useBean id="textoBusqueda" scope="request" type="java.lang.String" class="java.lang.String"/>
<jsp:useBean id="adminSession" scope="session" class="pe.edu.pucp.pteleshock.Beans.BUsuario"/>
<html lang="en">
    <head>
        <meta charset="utf-8"/>
        <meta http-equiv="X-UA-Compatible" content="IE=edge"/>
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no"/>
        <meta name="description" content=""/>
        <meta name="author" content=""/>
        <title>Listado</title>
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
                <main class="clase_principal" style="height:93%; position: relative">
                    <div class="container-fluid px-4">
                        <div>
                            <%
                                if (filtrar == 0) {%>
                            <h1 class="mt-4 text-center texto-central">Lista de todas las farmacias</h1>
                            <%
                            } else {%>
                            <h1 class="mt-4 text-center texto-central">Lista de farmacias de <%=distrito.getNombre()%>
                            </h1>
                            <% }
                            %>
                        </div>
                        <div class="container-fluid px-4">
                            <br>
                            <div class="row  row-cols-4">
                                <form class="d-none d-md-inline-block form-inline ms-auto me-0 me-md-3 my-2 my-md-0"
                                      method="post"
                                      action="<%=request.getContextPath()%>/Admin_Farm_List?pag=1">
                                    <div class="input-group">
                                        <input class="form-control" type="text" name="textoBuscar"
                                               placeholder="Buscar..." aria-label="Buscar..."
                                               aria-describedby="btnNavbarSearch" <%
                                            if (textoBusqueda != null) {%>
                                               value="<%=textoBusqueda%>"
                                                <% }%>/>

                                        <% if (filtrar == 1) {%>
                                        <input class="form-control" type="text" name="iddistrito"
                                               hidden aria-describedby="btnNavbarSearch"
                                               value="<%=distrito.getId_distrito()%>"/>
                                        <% } %>
                                        <button class="btn btn-primary" id="btnNavbarSearch" type="submit"
                                                style="background-color: #00152D;border-color: #00152D">
                                            <i class="fas fa-search"></i></button>
                                        <% if (filtrar == 1) {%>
                                        <a class="input-group-text"
                                           href="<%=request.getContextPath()%>/Admin_Farm_List?iddistrito=<%=distrito.getId_distrito()%>">
                                            <i class="fas fa-undo"></i>
                                        </a>
                                        <% } else { %>
                                        <a class="input-group-text"
                                           href="<%=request.getContextPath()%>/Admin_Farm_List">
                                            <i class="fas fa-undo"></i>
                                        </a>
                                        <% } %>
                                    </div>
                                </form>
                            </div>
                            <br>

                            <ol class="breadcrumb mb-2">
                                <li class="breadcrumb-item">
                                    <a href="<%= request.getContextPath()%>/Admin_Index">Distritos</a>
                                </li>
                                <% if (filtrar == 1) {%>
                                <li class="breadcrumb-item active"><%=distrito.getNombre()%></li>
                                <% } else { %>
                                <li class="breadcrumb-item active">Todas las Farmacias</li>
                                <% } %>
                                <li class="breadcrumb-item active">Pág. <%=pag%></li>
                            </ol>

                            <div class="row">
                                <%
                                    for (BFarmacia bFarmacia : listaFarmacias) {

                                %>
                                <div class="col-xl-6 col-md-8">
                                    <div class="card bg-primary text-white mb-4"
                                         style="color:#FFFFFF; background: #375880">
                                        <div class="texto-navbar"
                                             style="text-align:center;padding-top: 10px;padding-bottom: 20px;color: #38B6FF;font-size: 25px">
                                            <u><%=bFarmacia.getNombre()%>
                                            </u></div>
                                        <div class="row" style="text-align:left;padding-left: 10px">
                                            <div class="col-md-4">
                                                <div class="texto-navbar"
                                                     style="padding-bottom: 5px">RUC: <%=bFarmacia.getRuc()%>
                                                </div>
                                                <div class="texto-navbar"
                                                     style="padding-bottom: 5px">
                                                    Dirección: <%=bFarmacia.getDireccion()%>
                                                </div>
                                                <div class="texto-navbar"
                                                     style="padding-bottom: 5px">Correo: <%=bFarmacia.getCorreo()%>
                                                </div>
                                                <div class="texto-navbar"
                                                     style="padding-bottom: 5px">Estado: <%=bFarmacia.getEstado()%>
                                                </div>
                                            </div>
                                            <div class="col-md-6">
                                                <table class="table tabla-pedidos rounded" style="float: left">
                                                    <thead class="thead-dark">
                                                        <tr>
                                                            <th scope="col">Pedidos</th>
                                                            <th scope="col"><%=bFarmacia.getPedEntregados() + bFarmacia.getPedCancelados() + bFarmacia.getPedPendientes()%>
                                                            </th>
                                                        </tr>
                                                    </thead>
                                                    <tbody>
                                                        <tr>
                                                            <th scope="row" class="color-fila"
                                                                style="background: #FFFFFF">
                                                                Entregado
                                                            </th>
                                                            <td style="background: #FFFFFF"><%=bFarmacia.getPedEntregados()%>
                                                            </td>
                                                        </tr>
                                                        <tr>
                                                            <th scope="row" class="color-fila"
                                                                style="background: #FFFFFF">
                                                                Pendiente
                                                            </th>
                                                            <td style="background: #FFFFFF"><%=bFarmacia.getPedPendientes()%>
                                                            </td>
                                                        </tr>
                                                        <tr>
                                                            <th scope="row" class="color-fila"
                                                                style="background: #FFFFFF">
                                                                Cancelado
                                                            </th>
                                                            <td style="background: #FFFFFF"><%=bFarmacia.getPedCancelados()%>
                                                            </td>
                                                        </tr>
                                                    </tbody>
                                                </table>
                                            </div>
                                            <div class="col-md-2">
                                                <div style="display: flex; flex-direction: column;justify-content: center;align-items: center; height: 100%">
                                                    <%if (bFarmacia.getEstado().equals("bloqueado")) {%>
                                                    <a class="stretched-link; nav-link dropdown toogle boton_bloqueo_desbloqueo; disabled"
                                                       role="button" aria-expanded="false"
                                                       href="<%=request.getContextPath()%>/Admin_BloqFarm?ruc=<%=bFarmacia.getRuc()%>">
                                                        <div><i class="fas fa-lock fs-3"></i></div>
                                                    </a>
                                                    <p style="height: 30px"></p>
                                                    <a class="stretched-link;nav-link dropdown toogle boton_bloqueo_desbloqueo"
                                                       role="button" aria-expanded="false"
                                                       href="<%=request.getContextPath()%>/Admin_UnlockFarm?idfarmacia=<%=bFarmacia.getIdfarmacia()%>">
                                                        <div><i class="fas fa-lock-open fs-3"></i></div>
                                                    </a>
                                                    <%} else { %>
                                                    <a class="stretched-link; nav-link dropdown toogle boton_bloqueo_desbloqueo"
                                                       role="button" aria-expanded="false"
                                                       href="<%=request.getContextPath()%>/Admin_BloqFarm?ruc=<%=bFarmacia.getRuc()%>">
                                                        <div><i class="fas fa-lock fs-3"></i></div>
                                                    </a>
                                                    <p style="height: 30px"></p>
                                                    <a class="stretched-link; nav-link dropdown toogle boton_bloqueo_desbloqueo; disabled"
                                                       role="button" aria-expanded="false"
                                                       href="<%=request.getContextPath()%>/Admin_UnlockFarm">
                                                        <div><i class="fas fa-lock-open fs-3"></i></div>
                                                    </a>
                                                    <%}%>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                                <%
                                    }
                                %>
                            </div>
                        </div>
                    </div>
                    <div class="col align-self-center">
                        <a style="position: center">
                            <nav aria-label="Page navigation example" style="align-content: center">
                                <% int resto = cantidad % 4 == 0 ? 0 : 1; %>
                                <% if (filtrar == 0) {%>
                                <ul class="pagination justify-content-center">
                                    <% for (int i = 1; i < Math.floor(cantidad / 4) + resto + 1; i++) { %>
                                    <li class="page-item"><a class="page-link"
                                                             href="<%= request.getContextPath()%>/Admin_Farm_List?pag=<%=i %>"><%=i%>
                                    </a></li>
                                    <% } %>
                                </ul>
                                <%
                                } else {%>
                                <ul class="pagination justify-content-center">
                                    <% for (int i = 1; i < Math.floor(cantidad / 4) + resto + 1; i++) { %>
                                    <li class="page-item"><a class="page-link"
                                                             href="<%= request.getContextPath()%>/Admin_Farm_List?iddistrito=<%=distrito.getId_distrito()%>&pag=<%=i %>"><%=i%>
                                    </a></li>
                                    <% } %>
                                </ul>
                                <% } %>
                            </nav>
                        </a>
                    </div>
                    <div class="row" style="margin-right: 0">
                        <br><br>
                        <a class="btn btn-primary" href="<%=request.getContextPath()%>/Admin_Index"
                           style="width: fit-content; position: absolute; right: 15px; height: 38px; background-color: #00152D; border-color: #00152D">Regresar</a>
                    </div>
                </main>
                <footer class="py-4 bg-light mt-auto" style="height: 7%">
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

        <nav id="popup_logout" class="overlay_logout"
             style="display: flex; justify-content: center ; align-items: center;">
            <div class=" popup card text-center " style="background-color: white">
                <h5 class="card-header text-center">Teleshock</h5>
                <div class="card-body">
                    <h5 class="card-title p-2">¿Desea cerrar esta sesión?</h5>
                    <div class="row align-items-center" style="display: flex; justify-content: center">
                        <a href="#" class="btn btn-primary my-auto col-3" role="button"
                           aria-expanded="true">Cancelar</a>
                        <form class="col-3 my-auto" method="post"
                              action="<%=request.getContextPath()%>/Login?action=logout">
                            <button class="btn btn-danger" type="submit">
                                Salir
                            </button>
                        </form>
                    </div>
                </div>
            </div>
        </nav>

        <script src="https://.net/npm/bootstrap@5.1.0/dist/js/bootstrap.bundle.min.js"
                crossorigin="anonymous"></script>
        <script src="js/scripts.js"></script>
        <script src="https://cdnjs.clcdn.jsdelivroudflare.com/ajax/libs/Chart.js/2.8.0/Chart.min.js"
                crossorigin="anonymous"></script>
        <script src="assets/demo/chart-area-demo.js"></script>
        <script src="assets/demo/chart-bar-demo.js"></script>
        <script src="https://cdn.jsdelivr.net/npm/simple-datatables@latest" crossorigin="anonymous"></script>
        <script src="js/datatables-simple-demo.js"></script>
    </body>
</html>



