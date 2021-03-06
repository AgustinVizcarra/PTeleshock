<%@ page import="pe.edu.pucp.pteleshock.Beans.BPerfilFarmacia" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="pe.edu.pucp.pteleshock.Beans.BVentasPorFecha" %>
<%@ page import="pe.edu.pucp.pteleshock.Beans.BUsuario" %><%--
  Created by IntelliJ IDEA.
  User: casa
  Date: 5/11/2021
  Time: 19:01
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<% ArrayList<BPerfilFarmacia> perfilfarm=(ArrayList<BPerfilFarmacia>) request.getAttribute("perfilfarm"); %>
<% ArrayList<BVentasPorFecha> listventfecha=(ArrayList<BVentasPorFecha>) request.getAttribute("listventfecha"); %>

<html lang="en">
<head>

    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="css/styles_desplegable.css">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.1/dist/css/bootstrap.min.css"
          rel="stylesheet" crossorigin="anonymous"
          integrity="sha384-F3w7mX95PdgyTmZZMECAngseQB83DfGTowi0iMjiWaeVhAn4FJkqJByhZMI3AhiU">
    <script src="https://kit.fontawesome.com/a8c05a362e.js" crossorigin="anonymous"></script>
    <link href="https://cdn.jsdelivr.net/npm/simple-datatables@latest/dist/style.css" rel="stylesheet"/>
    <link href="stylescss/styles_desplegable.css" rel="stylesheet"/>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.3/js/all.min.js"
            crossorigin="anonymous"></script>
    <title> Perfil </title>
</head>
<body class="sb-nav-fixed" style="background-image: url('img/farmacia_background.PNG'); background-repeat:no-repeat; background-size: cover">

<nav class="sb-topnav navbar navbar-expand" style="background-color: #00152D; opacity: 0.9;">
    <!-- Navbar Brand-->
    <a class="navbar-brand ps-3" style="color: white" href="<%= request.getContextPath()%>/Farm_Index">
        <img style="width: 30px;height: 30px"  class="icon"
             src="img/logo_final.png">eleshock</a>
    <!-- Sidebar Toggle-->

    <button class="btn btn-link btn-sm order-1 order-lg-0 me-4 me-lg-0" style="color: white" id="sidebarToggle" href="#!"><i
            class="fas fa-bars"></i></button>
    <!-- Navbar Search-->
    <form class="d-none d-md-inline-block form-inline ms-auto me-0 me-md-3 my-2 my-md-0">
        <div class="input-group">


        </div>
    </form>

    <!-- Navbar-->
    <ul class="navbar-nav ms-auto ms-md-0 me-3 me-lg-4">
        <li class="nav-item">
            <a class="nav-link" style="color: white" href="#popup1" role="button"><i
                    class="fas fa-sign-out-alt"></i></a>
        </li>
    </ul>


</nav>



<div id="layoutSidenav">


    <div id="layoutSidenav_nav" >
        <nav class="sb-sidenav accordion " style="background-color:#00152D ; opacity: 0.9" id="sidenavAccordion" >
            <div class="sb-sidenav-menu" >
                <div class="nav" style="margin-top: 30px">
                    <a class=" nav-link bg-secondary"  style="color: white" href="<%= request.getContextPath()%>/Farm_Pefil">
                        <div class="sb-nav-link-icon"><i class="fas fa-user"></i></div>
                        Mi perfil
                    </a>

                    <a class="nav-link" style="color: white" href="<%= request.getContextPath()%>/Farm_Registro_Producto">
                        <div class="sb-nav-link-icon"><i class="fas fa-clipboard-list" style="color: #ffffff"></i></div>
                        Registrar Producto
                    </a>

                    <a class="nav-link" style="color: white" href="<%= request.getContextPath()%>/Farm_Vista_ProductosServlet?pag=1">
                        <div class="sb-nav-link-icon"><i class="far fa-eye" style="color: #ffffff"></i></div>
                        Visualizar Producto
                    </a>

                    <a class="nav-link" style="color: white" href="<%= request.getContextPath()%>/Farm_Gestionar_Pedidos">
                        <div class="sb-nav-link-icon"><i class="fas fa-people-arrows" style="color: #ffffff"></i></div>
                        Gestionar Pedidos
                    </a>



                </div>
            </div>
            <div class="sb-sidenav-footer" style="color: darkgrey">
                <div class="small">Logueado como:</div>
                <%BUsuario farmacia = (BUsuario) session.getAttribute("farmaciaSession");%>
                <%=farmacia.getNombre()%>
            </div>
        </nav>

    </div>



    <div id="layoutSidenav_content">
        <main>
            <div class="container-fluid px-4">
                <h1 class="mt-4 text-center"></h1>
                <div class="row">
                    <% for (BPerfilFarmacia perffarm : perfilfarm){ %>
                    <div class="card mb-4 mt-5" >
                        <h1 class="mt-4 text-center">Mi Perfil</h1>
                        <h4>Informacion de la cuenta:</h4>
                        <div class="row">
                            <div class="col-2">
                                <p >Nombre:</p>
                                <p >RUC:</p>
                            </div>
                            <div class="col-3">
                                <p ><%= perffarm.getNombre()%></p>
                                <p ><%= perffarm.getRuc()%></p>
                            </div>
                            <div class="col-2">
                                <p >Direcci??n:</p>
                                <p >Correo</p>
                            </div>
                            <div class="col-3">
                                <p ><%= perffarm.getDireccion()%></p>
                                <p ><%= perffarm.getCorreo()%></p>
                            </div>
                        </div>
                    </div>
                    <% } %>
                </div>
                <div class="card mb-4">
                    <div class="card-header  bg-warning">
                        <i class="fas fa-table me-1"></i>
                        Ultimas 8 ventas realizadas
                    </div>
                    <div class="card-body">
                        <table class="table">
                            <thead>
                            <tr>
                                <th class="text-center">Fechas:</th>
                                <th class="text-center">N??mero de Ventas:</th>
                            </tr>
                            </thead>
                            <tbody>
                            <% for (BVentasPorFecha ventfecha : listventfecha){ %>
                            <tr>
                                <td class="text-center"><%=ventfecha.getFecha()%></td>
                                <td class="text-center"><%=ventfecha.getNumventas()%></td>

                            </tr>
                            <% } %>
                            </tbody>
                        </table>
                    </div>

                </div>
            </div>
        </main>
        <footer class="py-4 bg-light mt-auto">
            <div class="container-fluid px-4">
                <div class="d-flex align-items-center justify-content-between small">
                    <div class="text-muted">Copyright &copy; Your Website 2021</div>
                    <div>
                        <a href="#">Privacy Policy</a>
                        &middot;
                        <a href="#">Terms &amp; Conditions</a>
                    </div>
                </div>
            </div>
        </footer>
    </div>
</div>





















<nav id="popup1" class="overlay" style="display: flex; justify-content: center ; align-items: center">
    <div class=" popup card text-center " style="background-color: white">
        <h5 class="card-header text-center">Teleshock</h5>
        <div class="card-body">
            <h5 class="card-title p-2">??Desea cerrar esta sesi??n?</h5>
            <a href="#" class="btn btn-primary mb-2">Cancelar</a>
            <form method="post" action="<%=request.getContextPath()%>/Login?action=logout">
                <button class="btn btn-danger mb-2"  style=" background-color: #5bc0de " type="submit">
                    Salir
                </button>

            </form>

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
