<%--
  Created by IntelliJ IDEA.
  User: casa
  Date: 5/11/2021
  Time: 18:44
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="en">
<head>
    <meta charset="utf-8"/>
    <meta http-equiv="X-UA-Compatible" content="IE=edge"/>
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no"/>
    <meta name="description" content=""/>
    <meta name="author" content=""/>
    <title>Estado de Compras</title>
    <link href="https://cdn.jsdelivr.net/npm/simple-datatables@latest/dist/style.css" rel="stylesheet"/>
    <link href="css/style.css" rel="stylesheet"/>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.3/js/all.min.js"
            crossorigin="anonymous"></script>
</head>
<body class="sb-nav-fixed">
<nav class="sb-topnav navbar navbar-expand" style="background-color: #00152D; opacity: 0.9;">
    <!-- Navbar Brand-->
    <a class="navbar-brand ps-3" style="color: white" href="<%= request.getContextPath()%>/Client_Farmacias"><img margin-right class="icon"
                                                                                 src="img/logo_final.png">eleshock</a>
    <!-- Sidebar Toggle-->
    <button class="btn btn-link btn-sm order-1 order-lg-0 me-4 me-lg-0" style="color: white" id="sidebarToggle"
            href="#!"><i
            class="fas fa-bars"></i></button>
    <!-- Navbar Search-->
    <form class="d-none d-md-inline-block form-inline ms-auto me-0 me-md-3 my-2 my-md-0">
        <div class="input-group">
            <input class="form-control" type="text" placeholder="Buscar..." aria-label="Buscar..."
                   aria-describedby="btnNavbarSearch"/>
            <button class="btn btn-primary" id="btnNavbarSearch" type="button"><i
                    class="fas fa-search"></i></button>
        </div>
    </form>
    <!-- Navbar-->
    <ul class="navbar-nav ms-auto ms-md-0 me-3 me-lg-4">
        <li class="nav-item">
            <a class="nav-link" style="color: white" href="#salir" role="button"><i
                    class="fas fa-sign-out-alt"></i></a>
        </li>
    </ul>
</nav>


<div id="layoutSidenav">


    <div id="layoutSidenav_nav">
        <nav class="sb-sidenav accordion" style="background-color: #00152D; opacity: 0.9" id="sidenavAccordion">
            <div class="sb-sidenav-menu">
                <div class="nav">
                    <a class="nav-link" style="color: white" href="<%=request.getContextPath()%>/Client_Perfil">
                        <div class="sb-nav-link-icon"><i class="fas fa-user-alt"></i></div>
                        Mi perfil
                    </a>

                    <a class="nav-link" style="color: white" href="<%=request.getContextPath()%>/Client_Bolsa_Compra">
                        <div class="sb-nav-link-icon"><i class="fas fa-shopping-cart"></i></div>
                        Bolsa de Compras
                    </a>
                    <a class="nav-link" style="color: white" href="<%=request.getContextPath()%>/Client_Listado_Producto">
                        <div class="sb-nav-link-icon"><i class="fas fa-clipboard-list"></i></div>
                        Historial de pedidos
                    </a>


                </div>
            </div>
            <div class="sb-sidenav-footer" style="color: gray">
                <div class="small">Logeado como:</div>
                Nombre Apellido
            </div>
        </nav>
    </div>
    <div id="layoutSidenav_content">
        <main>
            <div class="container-fluid px-4 mt-5">
                <ol class="breadcrumb mb-4">
                    <li class="breadcrumb-item"><a href="<%= request.getContextPath()%>/Client_Listado_Producto">Lista de Pedidos</a>
                    </li>
                    <li class="breadcrumb-item"><a href="<%= request.getContextPath()%>/Client_Pedido">Pedido #2030</a></li>
                    <li class="breadcrumb-item active">Ver detalles</li>
                </ol>
                <div class="card mb-4">
                    <div class="card-body">

                        <div class="row">
                            <div class="col-sm-6">
                                <div class="card border-0">
                                    <div class="card-body">
                                        <h5 class="card-title">Estado: </h5>
                                    </div>
                                </div>
                            </div>
                            <div class="col-sm-2">

                            </div>
                            <div class="col-sm-4 text-end">
                                <div class="card">
                                    <div class="card-body text-center">
                                        <p class="card-title"><small>Cancelado</small></p>
                                    </div>
                                </div>
                                <p class="card-title text-center"><small>24/10/2020 4:10:02 p.m</small></p>
                            </div>
                        </div>
                    </div>
                </div>
                <div class="card mb-4">
                    <div class="card-header text-white">
                        <i class="fas fa-table me-1"></i>
                        Detalles del pedido - Farmacia A
                    </div>
                    <div class="card-body">
                        <table class="table">
                            <thead>
                            <tr>
                                <th>Productos</th>
                                <th>Detalles</th>
                                <th>Costo</th>
                            </tr>
                            </thead>
                            <tbody>
                            <tr>
                                <th><img src="img/paltomiel.jpg" class="img-thumbnail" alt="..."
                                         style="width: 100px; height: 100px">PALTOMIEL JARABE ADULTO 200ML
                                </th>
                                <td>
                                    <div id="contenedor2">
                                        <div style=" margin-top: 30px" class="producto2">
                                            <span>Paltomiel</span>
                                            <div class="costo">
                                                <span class="text-center">Cantidad: 1</span>
                                            </div>
                                        </div>
                                    </div>
                                </td>
                                <td>
                                    <div style=" margin-top: 30px;margin-left: 12px">$ 15.50</div>
                                </td>
                            </tr>
                            <tr>
                                <th><img src="img/paltomiel.jpg" class="img-thumbnail" alt="..."
                                         style="width: 100px; height: 100px">PALTOMIEL JARABE NIÑOS 200ML
                                </th>
                                <td>
                                    <div id="contenedor">
                                        <div style=" margin-top: 40px" class="producto">
                                            <span>Paltomiel</span>
                                            <div class="costo">
                                                <span class="text-center">Cantidad: 1</span>
                                            </div>
                                        </div>
                                    </div>
                                </td>
                                <td>
                                    <div style=" margin-top: 40px; margin-left: 12px">$ 15.50</div>
                                </td>
                            </tr>
                            <tr>
                                <th>
                                    <div id="contenedor1">
                                        <div style=" margin-top: 30px" class="producto">
                                                        <span><img class="icon" style="width: 18px; height: 18px;"
                                                                   src="https://cdn-icons-png.flaticon.com/512/2370/2370264.png"> Fecha y hora de recojo: 20/10/2020 05:10:02 pm</span>
                                            <div class="costo">
                                                            <span class="text-center"><img class="icon"
                                                                                           style="width: 25px; height: 25px;"
                                                                                           src="https://cdn-icons-png.flaticon.com/512/1564/1564873.png"> San Juan de Miraflores - Farmacia A</span>
                                            </div>
                                        </div>
                                    </div>
                                </th>
                                <td>
                                    <div class="card-header text-white text-center">Subtotal</div>
                                </td>
                                <td>
                                    <div class="card-header text-white text-center">$31.00</div>
                                </td>
                            </tr>
                            </tbody>
                        </table>
                    </div>
                </div>
            </div>
        </main>
        <footer class="py-4 bg-light mt-auto">
            <div class="container-fluid px-4">
                <div class="d-flex align-items-center justify-content-between small">
                    <div class="text-muted">Copyright &copy; Teleshock 2021</div>
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
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.0/dist/js/bootstrap.bundle.min.js"
        crossorigin="anonymous"></script>
<script src="js/scripts.js"></script>
<script src="https://cdn.jsdelivr.net/npm/simple-datatables@latest" crossorigin="anonymous"></script>
</body>
</html>
