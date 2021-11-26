<%--
  Created by IntelliJ IDEA.
  User: casa
  Date: 5/11/2021
  Time: 18:59
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
    <title>Gestión de pedido</title>
    <link href="https://cdn.jsdelivr.net/npm/simple-datatables@latest/dist/style.css" rel="stylesheet"/>
    <link href="stylescss/styles_desplegable.css" rel="stylesheet"/>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.3/js/all.min.js"
            crossorigin="anonymous"></script>
    <title> Detalles de Pedido </title>

</head>
<body class="sb-nav-fixed"
      style="background-image: url('img/farmacia_background.PNG'); background-repeat:no-repeat; background-size: cover; width: 100%">

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

<div style="width: 560px;height: 100px"></div>

<div style="background-color: #192c42;opacity: 0.9" class="col-12">
    <h1 class="text-center" style="color: white">Detalles del Pedido</h1>
</div>

<div class="row">
    <div style="background-color: #192c42;opacity: 0.9" class="col-2">
        <h3 class="text-center" style="color: white">Pedido</h3>
    </div>
    <div style="background-color: #192c42;opacity: 0.9" class="col-3">
        <h3 class="text-center" style="color: white">Cliente</h3>
    </div>
    <div style="background-color: #192c42;opacity: 0.9" class="col-7">
        <h3 class="text-center" style="color: white">Productos</h3>
    </div>
</div>
<div class="row">
    <div style="background-color: #192c42;height: 400px;color: white;opacity: 0.9;padding-top: 35px" class="col-1">
        <p style="padding-left: 40px">Fecha:</p>
        <p style="padding-left: 40px">Codigo:</p>
        <p style="padding-left: 40px">Estado:</p>
    </div>
    <div style="background-color: #192c42;height: 400px;opacity: 0.9;padding-top: 35px" class="col-1">
        <p style="border: 2px;border-style: solid;border-color: black; background-color: white;margin-bottom: 12px">
            10/11/2021</p>
        <p style="border: 2px;border-style: solid;border-color: black; background-color: white;margin-bottom: 12px">
            65455</p>
        <p style="border: 2px;border-style: solid;border-color: black; background-color: white;margin-bottom: 12px">
            Entregado</p>
    </div>
    <div style="background-color: #192c42;height: 400px;color: white;opacity: 0.9;padding-top: 35px" class="col-1">
        <p style="padding-left: 40px">Nombre:</p>
        <p style="padding-left: 40px">Apellido:</p>
        <p style="padding-left: 40px">DNI:</p>
        <p style="padding-left: 40px">Correo:</p>
        <p style="padding-left: 40px">Distrito:</p>
    </div>
    <div style="background-color: #192c42;height: 400px;opacity: 0.9;padding-top: 35px" class="col-2">
        <p style="border: 2px;border-style: solid;border-color: black; background-color: white;margin-bottom: 12px">
            Leo</p>
        <p style="border: 2px;border-style: solid;border-color: black; background-color: white;margin-bottom: 12px">
            Mora</p>
        <p style="border: 2px;border-style: solid;border-color: black; background-color: white;margin-bottom: 12px">
            111111</p>
        <p style="border: 2px;border-style: solid;border-color: black; background-color: white;margin-bottom: 12px">
            mora@gmail.com</p>
        <p style="border: 2px;border-style: solid;border-color: black; background-color: white;margin-bottom: 12px">
            Pueblo Libre</p>
    </div>
    <div style="background-color: #192c42;opacity: 0.9" class="col-7">
        <table class="table table-striped">
            <tr style="color: white">
                <th>Nombre:</th>
                <th>Descripcion:</th>
                <th>Unidades:</th>
                <th>Precio Unitario:</th>
                <th>Receta Medica:</th>
            </tr>
            <tr style="color: white">
                <td class="text-center">Panadol</td>
                <td class="text-center">Te ayuda a sentir mejor</td>
                <td class="text-center">4</td>
                <td class="text-center">S/4.00</td>
                <td class="text-center">Si</td>
            </tr>
            <tr style="color: white">
                <td class="text-center">Panadol</td>
                <td class="text-center">Te ayuda a sentir mejor</td>
                <td class="text-center">4</td>
                <td class="text-center">S/4.00</td>
                <td class="text-center">Si</td>
            </tr>
            <tr style="color: white">
                <td class="text-center">Panadol</td>
                <td class="text-center">Te ayuda a sentir mejor</td>
                <td class="text-center">4</td>
                <td class="text-center">S/4.00</td>
                <td class="text-center">Si</td>
            </tr>
            <tr style="color: white">
                <td class="text-center">Panadol</td>
                <td class="text-center">Te ayuda a sentir mejor</td>
                <td class="text-center">4</td>
                <td class="text-center">S/4.00</td>
                <td class="text-center">Si</td>
            </tr>
            <tr style="color: white">
                <td class="text-center">Panadol</td>
                <td class="text-center">Te ayuda a sentir mejor</td>
                <td class="text-center">4</td>
                <td class="text-center">S/4.00</td>
                <td class="text-center">Si</td>
            </tr>
            <tr style="color: white">
                <td class="text-center">Panadol</td>
                <td class="text-center">Te ayuda a sentir mejor</td>
                <td class="text-center">4</td>
                <td class="text-center">S/4.00</td>
                <td class="text-center">Si</td>
            </tr>
            <tr style="color: white">
                <td class="text-center">Panadol</td>
                <td class="text-center">Te ayuda a sentir mejor</td>
                <td class="text-center">4</td>
                <td class="text-center">S/4.00</td>
                <td class="text-center">Si</td>
            </tr>
            <tr style="color: white">
                <td class="text-center">Panadol</td>
                <td class="text-center">Te ayuda a sentir mejor</td>
                <td class="text-center">4</td>
                <td class="text-center">S/4.00</td>
                <td class="text-center">Si</td>
            </tr>
        </table>
    </div>
    <div class="col-12" style="background-color: #192c42;opacity: 0.9;display: flex;
  justify-content: center">
        <a href="<%= request.getContextPath()%>/Farm_Detalle_Pedido_Mod">
            <button type="button" class="btn btn-secondary" >Modificar Estado</button>
        </a>


    </div>
</div>




<nav id="popup1" class="overlay" style="display: flex; justify-content: center ; align-items: center">
    <div class=" popup card text-center " style="background-color: white">
        <h5 class="card-header text-center">Teleshock</h5>
        <div class="card-body">
            <h5 class="card-title p-2">¿Desea cerrar esta sesión?</h5>
            <a href="#" class="btn btn-primary mb-2">Cancelar</a>
            <a href="<%= request.getContextPath()%>/Login_Exit" class="btn btn-danger mb-2">Salir</a>

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
