<%@ page import="pe.edu.pucp.pteleshock.Beans.BUsuario" %><%--
  Created by IntelliJ IDEA.
  User: casa
  Date: 5/11/2021
  Time: 19:00
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
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
    <title> Registro de Producto </title>

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
            <a class="nav-link" style="color: white" href="#popup2" role="button"><i
                    class="fas fa-sign-out-alt"></i></a>
        </li>
    </ul>


</nav>



<div id="layoutSidenav">


    <div id="layoutSidenav_nav" >
        <nav class="sb-sidenav accordion " style="background-color:#00152D ; opacity: 0.9" id="sidenavAccordion" >
            <div class="sb-sidenav-menu" >
                <div class="nav" style="margin-top: 30px">
                    <a class="nav-link"  style="color: white" href="<%= request.getContextPath()%>/Farm_Pefil">
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
                <div class="small">Logeado como:</div>
                <%BUsuario farmacia = (BUsuario) session.getAttribute("farmaciaSession");%>
                <%=farmacia.getNombre()%>
            </div>
        </nav>

    </div>
</div>

<div class="container" >
    <div class="row justify-content-center" style="padding-top: 45px" >
        <div class="col-lg-5">
            <div class="card shadow-lg border-0 rounded-lg mt-5">
                <div >
                    <img src="img/registrarProducto.png" width="100%">
                </div>
                <div class="card-body" ; style="background-color: #243b50">
                    <form>
                        <div class="form-floating mb-3">
                            <input class="form-control"  type="email" placeholder="name@example.com">
                            <label for="inputEmail">Nombre</label>
                        </div>
                        <div class="form-floating mb-3">
                            <input class="form-control"  type="email" placeholder="name@example.com">
                            <label for="inputEmail">Stock</label>
                        </div>
                        <div class="form-floating mb-3">
                            <input class="form-control"  type="email" placeholder="name@example.com">
                            <label for="inputEmail">P.Unitario(Soles)</label>
                        </div>
                        <div class="form-floating mb-3">
                            <input class="form-control" id="inputEmail" type="email" placeholder="name@example.com">
                            <label for="inputEmail">Agregue descripción</label>
                        </div>

                        <div class="col-md-6 mb-4" style="margin-left: 25px">
                            <h6 class="mb-2 pb-1" style="color: #FFFFFF" >Necesita Receta Médica?: </h6>
                            <div class="form-check form-check-inline">
                                <input
                                        class="form-check-input"
                                        type="radio"
                                        name="inlineRadioOptions"
                                        id="femaleGender"
                                        value="option1"
                                        checked
                                />
                                <label class="form-check-label" for="femaleGender" style="color: #FFFFFF">Si</label>
                            </div>

                            <div class="form-check form-check-inline">
                                <input
                                        class="form-check-input"
                                        type="radio"
                                        name="inlineRadioOptions"
                                        id="maleGender"
                                        value="option2"
                                />
                                <label class="form-check-label" for="maleGender" style="color: #FFFFFF">No</label>
                            </div>
                        </div>

                        <div   >
                            <div style="float: left">
                                <h6 class="mb-2 pb-1" style="color: #FFFFFF"> Subir Foto</h6>
                            </div>
                            <div style="float: left">
                                <a href="#">
                                    <i class="far fa-image  fa-2x" style="color: white ; margin-left: 10px"></i>
                                </a>

                            </div>
                            <div style="float: left">
                                <a class="btn btn-primary" href="#popup1" style="margin-left: 95px; margin-top: 30px; background-color: #5bc0de" >REGISTRAR</a>
                            </div>
                        </div>



                    </form>
                </div>

            </div>
        </div>
    </div>


</div>

<nav id="popup1" class="overlay" style="display: flex; justify-content: center ; align-items: center">
    <div class=" popup card text-center " style="background-color: white">
        <div class="card-header" >
            <div style="display: flex;justify-content: center"><i class="far fa-times-circle fa-3x" style="color: red"></i></div>
        </div>
        <div class="card-body">
            <h5 class="card-title p-2">Has llenado los campos incorrectamente o faltan llenar campos</h5>
            <a href="#" class="btn btn-success mb-2">Aceptar</a>

        </div>
    </div>
</nav>



<nav id="popup2" class="overlay" style="display: flex; justify-content: center ; align-items: center">
    <div class=" popup card text-center " style="background-color: white">
        <h5 class="card-header text-center">Teleshock</h5>
        <div class="card-body">
            <h5 class="card-title p-2">¿Desea cerrar esta sesión?</h5>
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
