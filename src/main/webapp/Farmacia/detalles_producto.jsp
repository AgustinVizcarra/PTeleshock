<%@ page import="pe.edu.pucp.pteleshock.Beans.BDetProdFarm" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="pe.edu.pucp.pteleshock.Beans.BUsuario" %><%--
  Created by IntelliJ IDEA.
  User: casa
  Date: 5/11/2021
  Time: 18:59
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<% ArrayList<BDetProdFarm> listadetallesP = (ArrayList<BDetProdFarm>) request.getAttribute("listadetallesP"); %>
<jsp:useBean type="java.lang.String" scope="request" id="idp"/>
<jsp:useBean id="valor" scope="request" type="java.lang.String" class="java.lang.String"/>

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
    <title> Detalles de Producto </title>

</head>
<body class="sb-nav-fixed"
      style="background-image: url('img/farmacia_background.PNG'); background-repeat: no-repeat; background-size: cover; background-attachment: fixed; background-position: center center;">
<nav class="sb-topnav navbar navbar-expand" style="background-color: #00152D; opacity: 0.9;">
    <!-- Navbar Brand-->
    <a class="navbar-brand ps-3" style="color: white" href="<%= request.getContextPath()%>/Farm_Index">
        <img style="width: 30px;height: 30px" class="icon"
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
            <a class="nav-link" style="color: white" href="#popup3" role="button"><i
                    class="fas fa-sign-out-alt"></i></a>
        </li>
    </ul>


</nav>


<div id="layoutSidenav">


    <div id="layoutSidenav_nav">
        <nav class="sb-sidenav accordion " style="background-color:#00152D ; opacity: 0.9" id="sidenavAccordion">
            <div class="sb-sidenav-menu">
                <div class="nav" style="margin-top: 30px">
                    <a class=" nav-link " style="color: white" href="<%= request.getContextPath()%>/Farm_Pefil">
                        <div class="sb-nav-link-icon"><i class="fas fa-user"></i></div>
                        Mi perfil
                    </a>

                    <a class="nav-link" style="color: white"
                       href="<%= request.getContextPath()%>/Farm_Registro_Producto">
                        <div class="sb-nav-link-icon"><i class="fas fa-clipboard-list" style="color: #ffffff"></i></div>
                        Registrar Producto
                    </a>

                    <a class="nav-link  bg-secondary" style="color: white"
                       href="<%= request.getContextPath()%>/Farm_Vista_ProductosServlet?pag=1">
                        <div class="sb-nav-link-icon"><i class="far fa-eye" style="color: #ffffff"></i></div>
                        Visualizar Producto
                    </a>

                    <a class="nav-link" style="color: white"
                       href="<%= request.getContextPath()%>/Farm_Gestionar_Pedidos">
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
        <div style="padding-top: 55px">
            <div class="container h-100" style=" opacity: 0.95 ; background-color: #243b50; margin-top: 40px">
                <div class="row d-flex justify-content-center align-items-center h-100">
                    <div class="card-body p-md-5" style="opacity: 0.95; background-color: #243b50">

                        <% if(listadetallesP.isEmpty()){ %>
                        <div class="row justify-content-center">
                            <div class="col-md-10 col-lg-6 col-xl-5 order-1 order-lg-1" style="width: 110%">
                                <p class="text-center h1 fw-bold alert alert-warning " role="alert" style="font-size: medium">No se han encontrado resultados</p>
                            </div>
                        </div>

                        <%}else{%>
                        <div class="row justify-content-center">
                            <div class="col-md-10 col-lg-6 col-xl-5 order-1 order-lg-1" style="width: 61%">

                                <p class="text-center h1 fw-bold " style="color: white">Detalles del Producto</p>

                                <% for (BDetProdFarm pdetalles : listadetallesP) { %>
                                <form class="mx-1 mx-md-4">
                                    <div class="d-flex flex-row align-items-center mb-4">
                                        <i class="fas fa-prescription-bottle-alt fa-2x fa-lg me-3 fa-fw"
                                           style="color: white"></i>
                                        <div class="form-outline flex-fill mb-0">
                                            <label class="form-label" style="color: white; font-size: 23px"> Nombre del
                                                Producto</label>
                                        </div>
                                    </div>
                                    <div style="margin-left: 50px">

                                        <p style="color: white; font-size: 18px"><%=pdetalles.getNombre() %>
                                        </p>

                                    </div>

                                    <div class="d-flex flex-row align-items-center mb-4">
                                        <i class="fas fa-file-medical fa-2x fa-lg me-3 fa-fw" style="color: white"></i>
                                        <div class="form-outline flex-fill mb-0">
                                            <label class="form-label" style="color: white; font-size: 23px">
                                                Descripción </label>
                                        </div>
                                    </div>
                                    <div style="margin-left: 50px">
                                        <p style="color: white; font-size: 18px"><%=pdetalles.getDescripcion() %>
                                        </p>
                                    </div>

                                    <div class="d-flex flex-row align-items-center mb-4">
                                        <i class="fas fa-database fa-2x fa-lg me-3 fa-fw" style="color: white"></i>
                                        <div class="form-outline flex-fill mb-0">
                                            <label class="form-label"
                                                   style="color: white; font-size: 23px">Stock</label>
                                        </div>
                                    </div>
                                    <div style="margin-left: 50px">
                                        <% if (pdetalles.getStock() == 0) {%>
                                        <p style="color:red; font-size: 18px"><%=pdetalles.getStock()%> unidades</p>
                                        <%} else {%>
                                        <p style="color:white; font-size: 18px"><%=pdetalles.getStock()%> unidades</p>

                                        <%}%>
                                    </div>

                                    <div class="d-flex flex-row align-items-center mb-4">
                                        <i class="far fa-money-bill-alt fa-2x fa-lg me-3 fa-fw"
                                           style="color: white"></i>
                                        <div class="form-outline flex-fill mb-0">
                                            <label class="form-label"
                                                   style="color: white; font-size: 23px">P.Unitario</label>
                                        </div>
                                    </div>
                                    <div style="margin-left: 50px">
                                        <p style="color: white; font-size: 18px"><%=pdetalles.getPreciounitario()%>
                                            Soles C/U</p>
                                    </div>

                                    <div class="d-flex flex-row align-items-center mb-4">
                                        <i class="fas fa-question-circle fa-2x fa-lg me-3 fa-fw"
                                           style="color: white"></i>
                                        <div class="form-outline flex-fill mb-0">
                                            <label class="form-label" style="color: white; font-size: 23px">Requiere
                                                Receta Médica</label>
                                        </div>
                                    </div>
                                    <div style="margin-left: 50px">
                                        <% if (pdetalles.getRecetamedica().equals("0")) {%>
                                        <p style="color: white; font-size: 18px">No</p>

                                        <%} else {%>
                                        <p style="color: white; font-size: 18px">Sí</p>

                                        <%}%>


                                    </div>


                                    <div>
                                        <div style="float: left">
                                            <a class="btn btn-primary"
                                               href="<%= request.getContextPath()%>/Farm_Editar_Inf_Producto?prod=<%=idp%>"
                                               style="margin-left: 80px; margin-top: 30px; background-color: #5bc0de">Editar
                                                Producto</a>
                                        </div>
                                        <div style="float: left">
                                            <a class="btn btn-primary" href="#popup1"
                                               style="margin-left: 80px; margin-top: 30px; background-color: #d9534f">Eliminar
                                                Producto</a>
                                        </div>
                                    </div>


                                </form>

                            </div>
                            <div class="col-md-10 col-lg-6 col-xl-7 d-flex align-items-center order-1 order-lg-2"
                                 style="width: 39%">
                                <img src="<%= request.getContextPath()%>/ImgServlet?prod=<%=idp%>"
                                     class="circular--square" width="400px">

                            </div>
                            <% } %>


                        </div>
                        <%}%>

                    </div>
                </div>
            </div>


        </div>
    </div>
</div>

<nav id="popup1" class="overlay" style="display: flex; justify-content: center ; align-items: center">
    <div class=" popup card text-center " style="background-color: white">
        <h5 class="card-header text-center ">Teleshock</h5>
        <div class="card-body">
            <form>
                <h5 class="card-title p-2">¿Seguro que desea eliminar el producto?</h5>
                <div class="btn-group" role="group" aria-label="Basic example"
                     style="display: flex;justify-content: center">
                    <a href="<%= request.getContextPath()%>/Farm_Detalles_Producto?action=borrar&prod=<%=idp%>"
                       class="btn btn-success mb-2">Sí</a>
                    <a href="#" class="btn btn-success mb-2">No</a>
                </div>
            </form>
        </div>
    </div>
</nav>

<% if (valor.equals("borr")) {%>
<nav id="popup2" style="display: flex; justify-content: center ; align-items: center;position: fixed;
    top: 0;
    bottom: 0;
    left: 0;
    right: 0;
    background: rgba(0, 0, 0, 0.7);
    transition: opacity 500ms;
    visibility: visible;
    opacity: 1;">
    <div class=" popup card text-center " style="background-color: white">
        <h5 class="card-header text-center ">Teleshock</h5>
        <div class="card-body">
            <h5 class="card-title p-2">Se ha eliminado el producto correctamente</h5>
            <a href="<%= request.getContextPath()%>/Farm_Vista_ProductosServlet"
               class="btn btn-success mb-2">Aceptar</a>
        </div>
    </div>
</nav>
<% } else if (valor.equals("noborr")) {%>

<nav id="popup2" style="display: flex; justify-content: center ; align-items: center ;position: fixed;
    top: 0;
    bottom: 0;
    left: 0;
    right: 0;
    background: rgba(0, 0, 0, 0.7);
    transition: opacity 500ms;
    visibility: visible;
    opacity: 1;">
    <div class=" popup card text-center " style="background-color: white">
        <div class="card-header">
            <div style="display: flex;justify-content: center"><i class="far fa-times-circle fa-3x"
                                                                  style="color: red"></i></div>
        </div>
        <div class="card-body">
            <h5 class="card-title p-2">No se puede eliminar un producto que está incluido en algún pedido</h5>
            <a href="<%= request.getContextPath()%>/Farm_Detalles_Producto?prod=<%=idp%>" class="btn btn-success mb-2">Aceptar</a>
        </div>
    </div>
</nav>
<% } else {%>
<% } %>
<nav id="popup3" class="overlay" style="display: flex; justify-content: center ; align-items: center">
    <div class=" popup card text-center " style="background-color: white">
        <h5 class="card-header text-center">Teleshock</h5>
        <div class="card-body">
            <h5 class="card-title p-2">¿Desea cerrar esta sesión?</h5>
            <a href="#" class="btn btn-primary mb-2">Cancelar</a>
            <form method="post" action="<%=request.getContextPath()%>/Login?action=logout">
                <button class="btn btn-danger mb-2" style=" background-color: #5bc0de " type="submit">
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
