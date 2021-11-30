<%@ page import="pe.edu.pucp.pteleshock.Beans.BListaPFarmacia" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="pe.edu.pucp.pteleshock.Beans.BUsuario" %>
<%--
  Created by IntelliJ IDEA.
  User: casa
  Date: 5/11/2021
  Time: 19:01
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<% ArrayList<BListaPFarmacia> listaxFarmacia=(ArrayList<BListaPFarmacia>) request.getAttribute("listaxFarmacia"); %>
<jsp:useBean type="java.lang.String" scope="request" id="cantProd" class="java.lang.String"/>
<jsp:useBean type="java.lang.String" scope="request" id="textbuscar" class="java.lang.String"/>


<html lang="en">

<head>
    <meta charset="utf-8"/>
    <meta http-equiv="X-UA-Compatible" content="IE=edge"/>
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no"/>
    <meta name="description" content=""/>
    <meta name="author" content=""/>
    <title>Farmacia A</title>
    <link href="https://cdn.jsdelivr.net/npm/simple-datatables@latest/dist/style.css" rel="stylesheet"/>
    <link href="css/styles_desplegable.css" rel="stylesheet"/>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.3/js/all.min.js"
            crossorigin="anonymous"></script>
    <title> Visualizar Producto </title>

</head>

<body class="sb-nav-fixed"
      style="background-image: url('img/farmacia_background.PNG'); background-repeat: repeat-y; background-size: cover;">

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
                <%BUsuario farmacia = (BUsuario) session.getAttribute("farmaciaSession");%>
                <%=farmacia.getNombre()%>
            </div>
        </nav>

    </div>


<div id="layoutSidenav_content">
    <main>
        <div class="container-fluid px-4" style="padding-top: 70px">
            <br>
            <br>
            <div class="card mb-4" style="opacity: 0.90;">
                <div class="card-body">
                    <div>
                        <div class="row gx-4 gx-lg-5 row-cols-2">   <h1 class=" mt-4">Lista de Productos</h1> </div>

                        <div class="row   row-cols-4">
                            <form class="d-none d-md-inline-block form-inline ms-auto me-0 me-md-3 my-2 my-md-0" method="post" action="<%=request.getContextPath()%>/Farm_Vista_ProductosServlet" >
                                <div class="input-group">
                                    <input class="form-control" type="text" name="textoBuscar" value="<%=textbuscar%>"  placeholder="Buscar..." aria-label="Buscar..."
                                           aria-describedby="btnNavbarSearch" />
                                    <button class="btn btn-primary" id="btnNavbarSearch" type="submit">
                                        <i class="fas fa-search"></i></button>
                                    <a class="input-group-text" href="<%=request.getContextPath()%>/Farm_Vista_ProductosServlet">
                                        <i class="fas fa-undo"></i>
                                    </a>
                                </div>
                            </form>
                        </div>

                    </div>




                    <nav aria-label="Page navigation example">
                        <ul class="pagination justify-content-end">
                        </ul>
                    </nav>
                    <section class="py-0">
                        <div class="container px-4 px-lg-5 mt-5">
                            <div class="row gx-4 gx-lg-5 row-cols-2 row-cols-md-3 row-cols-xl-4 justify-content-center">



                                <% for (BListaPFarmacia plistaFarm : listaxFarmacia){ %>
                                    <div class="col mb-5">
                                        <div class="card h-100">
                                            <img src="<%= request.getContextPath()%>/ImgServlet?prod=<%=plistaFarm.getIdProducto()%>"
                                                 class="card-img-top circular--square">
                                            <div class="card-body p-2">
                                                <div class="text-center">
                                                    <h5 class="fw-bolder"> <%= plistaFarm.getNombre() %> </h5>
                                                    <div class="text-center"><a class="btn btn-outline-dark mt-auto"
                                                                                href="<%= request.getContextPath()%>/Farm_Detalles_Producto?prod=<%= plistaFarm.getIdProducto() %>">Ver detalles</a></div>
                                                </div>
                                            </div>
                                        </div>
                                    </div>

                                <% } %>





                                <!--
                                <div class="col mb-5">
                                    <div class="card h-100">
                                        <img class="card-img-top" src="img/paltomiel.PNG" alt="...">
                                        <div class="card-body p-2">
                                            <div class="text-center">
                                                <h5 class="fw-bolder">Paltomiel</h5>
                                                <div class="text-center"><a class="btn btn-outline-dark mt-auto"
                                                                            href="/Farm_Detalles_Producto">Ver detalles</a></div>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                                -->



                            </div>
                        </div>
                    </section>

                    <nav aria-label="Page navigation example">
                        <ul class="pagination justify-content-end">

                            <% int cantProdInt=Integer.parseInt(cantProd);%>
                            <% for(int i=1; i<Math.floor(cantProdInt/12)+2; i++) { %>



                            <form method="post"   action="<%=request.getContextPath()%>/Farm_Vista_ProductosServlet?pag=<%=i%>&textoBuscar=<%=textbuscar %> ">
                               <!-- <input class="form-control" type="hidden"   value="" placeholder="Buscar..." aria-label="Buscar..."
                                       aria-describedby="btnNavbarSearch" /> -->
                                <button class="page-item"><a class="page-link" type="submit"><%=i%></a></button>

                            </form>

                            <% } %>

                        </ul>
                    </nav>

                </div>

            </div>


        </div>
    </main>
</div>
</div>
</div>


<nav id="popup1" class="overlay" style="display: flex; justify-content: center ; align-items: center">
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
