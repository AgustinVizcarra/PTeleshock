<%@ page import="pe.edu.pucp.pteleshock.Beans.BDetProdFarm" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="pe.edu.pucp.pteleshock.Beans.BUsuario" %>
<%--
  Created by IntelliJ IDEA.
  User: casa
  Date: 5/11/2021
  Time: 18:59
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<% ArrayList<BDetProdFarm> listadetallesP=(ArrayList<BDetProdFarm>) request.getAttribute("listadetallesP"); %>
<jsp:useBean type="java.lang.String" scope="request" id="idp" class="java.lang.String"/>
<jsp:useBean id="val" scope="request" type="java.lang.String" class="java.lang.String"/>



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
                <%BUsuario farmacia = (BUsuario) session.getAttribute("farmaciaSession");%>
                <%=farmacia.getNombre()%>
            </div>
        </nav>


</div>

    <div id="layoutSidenav_content">

<div style="padding-top: 55px">
    <div class="container h-100" style=" opacity: 0.95 ; background-color: #243b50; margin-top: 40px" >
        <div class="row d-flex justify-content-center align-items-center h-100" >
            <div class="card-body p-md-5" style="opacity: 0.95; background-color: #243b50">
                <form class="mx-1 mx-md-4" method="post" action="<%=request.getContextPath()%>/Farm_Editar_Inf_Producto?prod=<%=idp%>" enctype="multipart/form-data">
                    <div class="row justify-content-center">
                        <div class="col-md-10 col-lg-6 col-xl-5 order-1 order-lg-1" style="width: 61%">

                            <p class="text-center h1 fw-bold " style="color: white">Detalles del Producto</p>
                            <% for (BDetProdFarm pdetalles : listadetallesP){ %>


                            <div class="d-flex flex-row align-items-center mb-4">
                                <i class="fas fa-prescription-bottle-alt fa-2x fa-lg me-3 fa-fw" style="color: white"></i>
                                <div class="form-outline flex-fill mb-0">
                                    <label class="form-label" style="color: white; font-size: 23px">  Nombre del Producto</label>
                                </div>
                            </div>
                            <div class="form-floating mb-3">

                                <p style="color: white; font-size: 25px"> <%=pdetalles.getNombre() %></p>
                            </div>

                            <div class="d-flex flex-row align-items-center mb-4">
                                <i class="fas fa-file-medical fa-2x fa-lg me-3 fa-fw" style="color: white"></i>
                                <div class="form-outline flex-fill mb-0">
                                    <label class="form-label"  style="color: white; font-size: 23px"> Descripción </label>
                                </div>
                            </div>
                            <b style="color: #d5d5d5">max:500 caracteres</b>
                            <div class="form-floating mb-3">
                                <textarea name="descripcion" rows="10" cols="50" maxlength="500" ><%=pdetalles.getDescripcion() %></textarea>
                            </div>

                            <div class="d-flex flex-row align-items-center mb-4">
                                <i class="fas fa-database fa-2x fa-lg me-3 fa-fw" style="color: white"></i>
                                <div class="form-outline flex-fill mb-0">
                                    <label class="form-label"  style="color: white; font-size: 23px">Stock</label>
                                </div>
                            </div>
                            <div class="form-floating mb-3">
                                <input class="form-control" id="stock" type="number" name="stock" value=<%=pdetalles.getStock() %> >

                            </div>

                            <div class="d-flex flex-row align-items-center mb-4">
                                <i class="far fa-money-bill-alt fa-2x fa-lg me-3 fa-fw" style="color: white"></i>
                                <div class="form-outline flex-fill mb-0">
                                    <label class="form-label" style="color: white; font-size: 23px">P.Unitario</label>
                                </div>
                            </div>
                            <div class="form-floating mb-3">
                                <input class="form-control" id="precioUnitario" type="number" name="precioUnitario" value=<%=pdetalles.getPreciounitario() %> >

                            </div>

                            <div class="d-flex flex-row align-items-center mb-4">
                                <i class="fas fa-question-circle fa-2x fa-lg me-3 fa-fw" style="color: white"></i>
                                <div class="form-outline flex-fill mb-0">
                                    <label class="form-label" style="color: white; font-size: 23px">Requiere Receta Médica</label>
                                </div>
                            </div>
                            <%if(pdetalles.getRecetamedica().equals("1")){ %>
                            <div class="form-check form-check-inline">
                                <input
                                        class="form-check-input"
                                        type="radio"
                                        name="recetamedica"
                                        id="optionsi"
                                        value="1"
                                        checked

                                />
                                <label class="form-check-label" for="optionsi" style="color: #FFFFFF">Si</label>
                            </div>

                            <div class="form-check form-check-inline">
                                <input
                                        class="form-check-input"
                                        type="radio"
                                        name="recetamedica"
                                        id="optionNo"
                                        value="0"

                                />
                                <label class="form-check-label" for="optionNo" style="color: #FFFFFF">No</label>
                            </div>
                            <%} else {%>
                            <div class="form-check form-check-inline">
                                <input
                                        class="form-check-input"
                                        type="radio"
                                        name="recetamedica"
                                        id="optionsi1"
                                        value="1"


                                />
                                <label class="form-check-label" for="optionsi" style="color: #FFFFFF">Si</label>
                            </div>

                            <div class="form-check form-check-inline">
                                <input
                                        class="form-check-input"
                                        type="radio"
                                        name="recetamedica"
                                        id="optionNo1"
                                        value="0"
                                        checked

                                />
                                <label class="form-check-label" for="optionNo" style="color: #FFFFFF">No</label>
                            </div>


                            <%}%>

                            <div style="display: flex;justify-content: center">

                                <div style="float: left">
                                    <a class="btn btn-primary" href="<%= request.getContextPath()%>/Farm_Detalles_Producto?prod=<%=idp%>" style="margin-left: 80px; margin-top: 30px; background-color: #d9534f" >Cancelar</a>
                                </div>
                                <div style="float: left">
                                    <button class="btn btn-primary"  style="margin-left: 80px; margin-top: 30px; background-color: #5bc0de" type="submit">Guardar</button>
                                </div>

                            </div>



                        </div>
                        <div class="col-md-10 col-lg-6 col-xl-7 d-flex align-items-center order-1 order-lg-2" style="width: 39%">

                            <div>
                                <img src="<%= request.getContextPath()%>/ImgServlet?prod=<%=idp%>" class="circular--square" width="400px">
<%--                                <input class="form-control" type="file" name="foto" style="width: 60% "  id="formFile1">--%>
                                <div class="mt-3" style="display: flex;justify-content: center">
                                    <a type="button" href="#popup123"  class="btn btn-secondary btn-sm">Editar foto</a>
                                </div>
                            </div>
                        </div>

                        <% } %>

                    </div>
                </form>
            </div>
        </div>
    </div>

</div>
</div>



<nav id="popup1" class="overlay" style="display: flex; justify-content: center ; align-items: center">
    <div class=" popup card text-center " style="background-color: white">
        <h5 class="card-header text-center ">Teleshock</h5>
        <div class="card-body">
            <h5 class="card-title p-2">Se ha editado el producto correctamente</h5>
            <a href="<%= request.getContextPath()%>/Farm_Detalles_Producto" class="btn btn-success mb-2">Aceptar</a>

        </div>
    </div>
</nav>

<% if (val.equals("ok")) {%>


<nav id="popup1"  style="display: flex; justify-content: center ; align-items: center; position: fixed;
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
            <h5 class="card-title p-2">Se ha editado el producto correctamente</h5>
            <a href="<%= request.getContextPath()%>/Farm_Detalles_Producto?prod=<%=idp%>" class="btn btn-success mb-2" >Aceptar</a>

        </div>
    </div>
</nav>
<% } else if (val.equals("error")){%>
    <nav id="popup1"  style="display: flex; justify-content: center ; align-items: center; position: fixed;
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
                <h5 class="card-title p-2">Por favor llenar todos los campos</h5>
                <a href="<%= request.getContextPath()%>/Farm_Editar_Inf_Producto?prod=<%=idp%>" class="btn btn-success mb-2" >Aceptar</a>

            </div>
        </div>
    </nav>
<% } else if (val.equals("errorNeg")){%>
    <nav id="popup1"  style="display: flex; justify-content: center ; align-items: center; position: fixed;
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
                <h5 class="card-title p-2">Por favor ingrese solo valores positivos</h5>
                <a href="<%= request.getContextPath()%>/Farm_Editar_Inf_Producto?prod=<%=idp%>" class="btn btn-success mb-2" >Aceptar</a>

            </div>
        </div>
    </nav>
<% } else if (val.equals("muchoTxt")){%>
    <nav id="popup1"  style="display: flex; justify-content: center ; align-items: center; position: fixed;
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
                <h5 class="card-title p-2">La cantidad maxima de caracteres es 500</h5>
                <a href="<%= request.getContextPath()%>/Farm_Editar_Inf_Producto?prod=<%=idp%>" class="btn btn-success mb-2" >Aceptar</a>

            </div>
        </div>
    </nav>

<% } else {%>

<% } %>


    <nav id="popup123" class="overlay" style="display: flex; justify-content: center ; align-items: center">
        <div class=" popup card text-center " style="background-color: white">
            <h5 class="card-header text-center">Teleshock</h5>
            <div class="card-body">
                <form method="post" action="<%=request.getContextPath()%>/ImgServlet?prod=<%=idp%>" enctype="multipart/form-data">
                    <img src="<%= request.getContextPath()%>/ImgServlet?prod=<%=idp%>" class="circular--square" width="400px">
                    <div  class="my-3" style="display: flex; justify-content: center">
                        <input class="form-control" type="file" name="foto" style="width: 60% "  id="formFile1">
                    </div>

                    <a href="#" class="btn btn-danger mb-2">Cancelar</a>
                    <button class="btn btn-primary mb-2"  style="margin-left: 80px; background-color: #5bc0de" type="submit">Actualizar</button>

                </form>


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
