<%--
  Created by IntelliJ IDEA.
  User: casa
  Date: 5/11/2021
  Time: 19:00
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="en">
<head>
    <meta charset="UTF-8">
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
    <title> Editar Producto </title>

</head>
<body class="sb-nav-fixed"
      style="background-image: url('img/farmacia_background.PNG'); background-repeat:repeat-y; background-size: cover">
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
                <div class="small">Logged in as:</div>
                Mifarma
            </div>
        </nav>

    </div>
</div>


<div style="padding-top: 55px">
    <div class="container h-100" style=" opacity: 0.95 ; background-color: #243b50; margin-top: 40px" >
        <div class="row d-flex justify-content-center align-items-center h-100" >
            <div class="card-body p-md-5" style="opacity: 0.95; background-color: #243b50">
                <div class="row justify-content-center">
                    <div class="col-md-10 col-lg-6 col-xl-5 order-1 order-lg-1" style="width: 61%">

                        <p class="text-center h1 fw-bold " style="color: white">Detalles del Producto</p>

                        <form class="mx-1 mx-md-4">

                            <div class="d-flex flex-row align-items-center mb-4">
                                <i class="fas fa-prescription-bottle-alt fa-2x fa-lg me-3 fa-fw" style="color: white"></i>
                                <div class="form-outline flex-fill mb-0">
                                    <label class="form-label" style="color: white; font-size: 23px">  Nombre del Producto</label>
                                </div>
                            </div>
                            <div style="margin-left: 50px">
                                <p style="color: white; font-size: 18px"> PARACETAMOL</p>
                            </div>

                            <div class="d-flex flex-row align-items-center mb-4">
                                <i class="fas fa-file-medical fa-2x fa-lg me-3 fa-fw" style="color: white"></i>
                                <div class="form-outline flex-fill mb-0">
                                    <label class="form-label"  style="color: white; font-size: 23px"> Descripción </label>
                                </div>
                            </div>
                            <div style="margin-left: 50px">
                                <p style="border: 2px;border-style: solid;border-color: black; background-color: white;height: 60px">
                                    El jarabe como forma farmacéutica empleada en formulación magistral tiene como principal utilidad terapéutica la administración de medicamentos en pediatría</p>
                            </div>

                            <div class="d-flex flex-row align-items-center mb-4">
                                <i class="fas fa-database fa-2x fa-lg me-3 fa-fw" style="color: white"></i>
                                <div class="form-outline flex-fill mb-0">
                                    <label class="form-label"  style="color: white; font-size: 23px">Stock</label>
                                </div>
                            </div>
                            <div style="margin-left: 50px">
                                <p style="border: 2px;border-style: solid;border-color: black; background-color: white;height: 45px">
                                    25 unidades</p>
                            </div>

                            <div class="d-flex flex-row align-items-center mb-4">
                                <i class="far fa-money-bill-alt fa-2x fa-lg me-3 fa-fw" style="color: white"></i>
                                <div class="form-outline flex-fill mb-0">
                                    <label class="form-label" style="color: white; font-size: 23px">P.Unitario</label>
                                </div>
                            </div>
                            <div style="margin-left: 50px">
                                <<p style="border: 2px;border-style: solid;border-color: black; background-color: white;height: 45px">
                                25 Soles C/U</p>
                            </div>

                            <div class="d-flex flex-row align-items-center mb-4">
                                <i class="fas fa-question-circle fa-2x fa-lg me-3 fa-fw" style="color: white"></i>
                                <div class="form-outline flex-fill mb-0">
                                    <label class="form-label" style="color: white; font-size: 23px">Requiere Receta Médica</label>
                                </div>
                            </div>
                            <div style="margin-left: 50px">
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


                            <div style="display: flex;justify-content: center">
                                <div style="float: left">
                                    <a class="btn btn-primary" href="<%= request.getContextPath()%>/Farm_Detalles_Producto" style="margin-left: 80px; margin-top: 30px; background-color: #d9534f" >Cancelar</a>
                                </div>
                                <div style="float: left">
                                    <a class="btn btn-primary" href="#popup1" style="margin-left: 80px; margin-top: 30px; background-color: #5bc0de" >Guardar</a>
                                </div>

                            </div>

                        </form>

                    </div>
                    <div class="col-md-10 col-lg-6 col-xl-7 d-flex align-items-center order-1 order-lg-2" style="width: 39%">

                        <div>
                            <img src="img/jarabe.png" class="circular--square" width="400px">
                            <i class="far fa-file-image fa-3x" style="color: white"></i>
                        </div>
                    </div>

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
            <h5 class="card-title p-2">Por favor llene todos los campos de información</h5>
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
            <a href="<%= request.getContextPath()%>/Login" class="btn btn-danger mb-2">Salir</a>

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
