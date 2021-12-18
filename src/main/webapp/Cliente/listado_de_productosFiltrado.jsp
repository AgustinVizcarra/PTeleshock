<%@ page import="pe.edu.pucp.pteleshock.Beans.BPedidoGeneral" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="pe.edu.pucp.pteleshock.Beans.BUsuario" %><%--
  Created by IntelliJ IDEA.
  User: casa
  Date: 5/11/2021
  Time: 18:46
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>


<jsp:useBean type="java.lang.String" scope="request" id="textbuscar" class="java.lang.String"/>
<jsp:useBean type="java.lang.String" scope="request" id="cantPed" class="java.lang.String"/>


<%
    ArrayList<BPedidoGeneral> listaPedidosG = (ArrayList<BPedidoGeneral>) request.getAttribute("listaPedidosG");

%>

<html lang="en">
    <head>
        <meta charset="utf-8"/>
        <meta http-equiv="X-UA-Compatible" content="IE=edge"/>
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no"/>
        <meta name="description" content=""/>
        <meta name="author" content=""/>
        <title>Listado de Productos</title>
        <link href="https://cdn.jsdelivr.net/npm/simple-datatables@latest/dist/style.css" rel="stylesheet"/>
        <link href="css/style.css" rel="stylesheet"/>

        <script src="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.3/js/all.min.js"
                crossorigin="anonymous"></script>
    </head>
    <body class="sb-nav-fixed">
        <nav class="sb-topnav navbar navbar-expand" style="background-color: #00152D; opacity: 0.9;">
            <!-- Navbar Brand-->
            <a class="navbar-brand ps-3" style="color: white"
               href="<%= request.getContextPath()%>/Client_Farmacias"><img margin-right class="icon"
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

                            <a class="nav-link" style="color: white"
                               href="<%=request.getContextPath()%>/Client_Bolsa_Compra">
                                <div class="sb-nav-link-icon"><i class="fas fa-shopping-cart"></i></div>
                                Bolsa de Compras
                            </a>
                            <a class="nav-link" style="color: white"
                               href="<%=request.getContextPath()%>/Client_Listado_Producto">
                                <div class="sb-nav-link-icon"><i class="fas fa-clipboard-list"></i></div>
                                Historial de pedidos
                            </a>


                        </div>
                    </div>
                    <div class="sb-sidenav-footer" style="color: gray">
                        <div class="small">Logeado como:</div>
                        <%BUsuario cliente = (BUsuario) session.getAttribute("clienteSession");%>
                        <%=cliente.getNombre()+" "+cliente.getApellido()%>
                    </div>
                </nav>
            </div>

            <!-- Main -->
            <div id="layoutSidenav_content">
                <main>
                    <div class="container-fluid px-4">
                        <h1 class="mt-4">Lista de Pedidos</h1>
                       <!-- <ol class="breadcrumb mb-4">
                            <li class="breadcrumb-item active">Lista de Pedidos</li>
                        </ol> -->
                        <div class="row   row-cols-4">
                            <form class="d-none d-md-inline-block form-inline ms-auto me-0 me-md-3 my-2 my-md-0" method="post" action="<%=request.getContextPath()%>/Client_Listado_Producto" >
                                <div class="input-group">
                                    <input class="form-control" type="text" name="textoBuscar" value="<%=textbuscar%>" placeholder="Buscar..." aria-label="Buscar..."
                                           aria-describedby="btnNavbarSearch"/>
                                    <button class="btn btn-primary" id="btnNavbarSearch" type="submit">
                                        <i class="fas fa-search"></i></button>
                                    <a class="input-group-text" href="<%=request.getContextPath()%>/Client_Listado_Producto">
                                        <i class="fas fa-undo"></i>
                                    </a>
                                </div>
                            </form>

                        </div>

                        <div class="card mb-4">
                            <div class="card-body">
                                A continuación, se mostrarán los pedidos realizados por usted!
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
                                            <th>Número de pedido</th>
                                            <th class="text-center">Fecha del pedido</th>
                                            <th>Monto Total</th>
                                            <th>Detalles</th>
                                        </tr>
                                    </thead>
                                    <tbody>
                                        <% for (BPedidoGeneral pedidoG : listaPedidosG) {%>
                                        <tr>
                                            <td><%=pedidoG.getCodigoVenta()%>
                                            </td>

                                            <td class="text-center"><%=pedidoG.getFechaPedido()%>
                                            </td>
                                            <td><%=pedidoG.getMontoTotal()%>
                                            </td>
                                            <td><a class="btn btn-primary"
                                                   href="<%= request.getContextPath()%>/Client_Pedido?idPG=<%=pedidoG.getCodigoVenta()%>">Ver
                                                detalles</a></td>

                                        </tr>
                                        <%}%>


                                    </tbody>
                                </table>
                            </div>
                        </div>

                        <nav aria-label="Page navigation example">
                            <ul class="pagination justify-content-end">
                                <% int cantPedInt=Integer.parseInt(cantPed);%>
                                <% int resto= cantPedInt%3==0? 0:1; %>
                                <% for(int i=1; i<Math.floor(cantPedInt/5)+resto+1; i++) { %>

                                <form method="post"   action="<%=request.getContextPath()%>/Client_Listado_Producto?pag=<%=i%>&textoBuscar=<%=textbuscar %> ">
                                    <!-- <input class="form-control" type="hidden"   value="" placeholder="Buscar..." aria-label="Buscar..."
                                            aria-describedby="btnNavbarSearch" /> -->
                                    <button class="page-item"><a class="page-link" type="submit"><%=i%></a></button>

                                </form>


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

        <nav id="salir" class="overlay">
            <div class=" popup card text-center " style="background-color: white">
                <h5 class="card-header text-center">Teleshock</h5>
                <div class="card-body">
                    <h5 class="card-title p-2">¿Desea cerrar esta sesión?</h5>
                    <a href="#" class="btn btn-primary mb-2">Cancelar</a>
                    <form method="post" action="<%=request.getContextPath()%>/Login?action=logout">
                        <button class="btn btn-danger mb-2" type="submit">
                            Salir
                        </button>
                    </form>

                </div>
            </div>
        </nav>

        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.0/dist/js/bootstrap.bundle.min.js"
                crossorigin="anonymous"></script>
        <script src="js/scripts.js"></script>
        <script src="https://cdn.jsdelivr.net/npm/simple-datatables@latest" crossorigin="anonymous"></script>
    </body>
</html>