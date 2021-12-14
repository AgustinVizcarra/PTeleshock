<%@ page import="java.util.ArrayList" %>
<%@ page import="pe.edu.pucp.pteleshock.Beans.BPedidoG" %>
<%@ page import="pe.edu.pucp.pteleshock.Beans.BUsuario" %><%--
  Created by IntelliJ IDEA.
  User: casa
  Date: 5/11/2021
  Time: 19:00
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<% ArrayList<BPedidoG> listaPedido=(ArrayList<BPedidoG>) request.getAttribute("listaPedido");%>
<jsp:useBean type="java.lang.String" scope="request" id="cantPed" class="java.lang.String"/>

<head>

    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="css/styles_desplegable.css">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.1/dist/css/bootstrap.min.css"
          rel="stylesheet" crossorigin="anonymous"
          integrity="sha384-F3w7mX95PdgyTmZZMECAngseQB83DfGTowi0iMjiWaeVhAn4FJkqJByhZMI3AhiU">
    <script src="https://kit.fontawesome.com/a8c05a362e.js" crossorigin="anonymous"></script>
    <title>Gestion de pedido</title>
    <link href="https://cdn.jsdelivr.net/npm/simple-datatables@latest/dist/style.css" rel="stylesheet"/>
    <link href="stylescss/styles_desplegable.css" rel="stylesheet"/>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.3/js/all.min.js"
            crossorigin="anonymous"></script>
    <title> Gestionar Pedido </title>

</head>
<body class="sb-nav-fixed">

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
            <jsp:include page="/includes/despegable.jsp">
                <jsp:param name="page" value="pedidos"/>
            </jsp:include>
            <div class="sb-sidenav-footer" style="color: darkgrey">
                <div class="small">Logged in as:</div>
                <%BUsuario farmacia = (BUsuario) session.getAttribute("farmaciaSession");%>
                <%=farmacia.getNombre()%>
            </div>
            <div class="sb-sidenav-footer" style="color: darkgrey">
            </div>
        </nav>
    </div>
    <div id="layoutSidenav_content">
        <main>
            <div class="container-fluid px-4">
                <h1 class="mt-4">Lista de Pedidos</h1>
                <div class="row   row-cols-4">
                    <form class="d-none d-md-inline-block form-inline ms-auto me-0 me-md-3 my-2 my-md-0" method="post" action="<%=request.getContextPath()%>/Farm_Gestionar_Pedidos?pag=1" >
                        <div class="input-group">
                            <input class="form-control" type="text" name="textoBuscar" placeholder="Buscar..." aria-label="Buscar..."
                                   aria-describedby="btnNavbarSearch"/>
                            <button class="btn btn-primary" id="btnNavbarSearch" type="submit">
                                <i class="fas fa-search"></i></button>
                            <a class="input-group-text" href="<%=request.getContextPath()%>/Farm_Gestionar_Pedidos">
                                <i class="fas fa-undo"></i>
                            </a>
                        </div>
                    </form>
                </div>

                <div class="card mb-4">
                    <div class="card-body">
                        A continuación, se mostrarán los ultimos pedidos vendidos!
                    </div>
                </div>
                <div class="card mb-4">
                    <div class="card-header  bg-warning">
                        <i class="fas fa-table me-1"></i>
                        Lista de pedidos
                    </div>
                    <div class="card-body">
                        <table class="table">
                            <thead>
                            <tr>
                                <th>Fecha:</th>
                                <th>Nombre:</th>
                                <th>Apellido:</th>
                                <th>Dni:</th>
                                <th>Pedidos:</th>
                                <th>Costo Total:</th>
                                <th>Detalles:</th>
                            </tr>
                            </thead>
                            <tbody>
                            <%for(BPedidoG bPedidoG : listaPedido){%>
                            <tr>
                                <td><%=bPedidoG.getFecha()%></td>
                                <td ><%=bPedidoG.getNombre()%></td>
                                <td><%=bPedidoG.getApellido()%></td>
                                <td><%=bPedidoG.getDni()%></td>
                                <td><%=bPedidoG.getCodigoV()%></td>
                                <td> <%=bPedidoG.getPrecioTotal()%></td>
                                <td><a href="<%= request.getContextPath()%>/Farm_Detalles_Pedido?id=<%=bPedidoG.getIdPedido()%>">Ver más detalles</a></td>
                            </tr>
                            <%}%>
                            </tbody>
                        </table>
                    </div>
                </div>
                <nav aria-label="Page navigation example">
                    <ul class="pagination justify-content-end">
                        <% int cantPedInt=Integer.parseInt(cantPed);%>
                        <% int resto= cantPedInt%6==0? 0:1; %>
                        <% for(int i=1; i<Math.floor(cantPedInt/6)+resto+1; i++) { %>
                        <li class="page-item"><a class="page-link" href="<%= request.getContextPath()%>/Farm_Gestionar_Pedidos?pag=<%=i %>"><%=i%></a></li>
                        <% } %>

                    </ul>
                </nav>

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
