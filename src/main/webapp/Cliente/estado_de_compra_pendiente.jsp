<%@ page import="pe.edu.pucp.pteleshock.Beans.BPedido" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="pe.edu.pucp.pteleshock.Beans.BPedidoEstado" %>
<%@ page import="pe.edu.pucp.pteleshock.Beans.BUsuario" %><%--
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    ArrayList<BPedidoEstado> listaPedidoE = (ArrayList<BPedidoEstado>) request.getAttribute("listaPedidoE");
    Boolean sePuedeCancelar = (Boolean) request.getAttribute("sePuedeCancelar");

%>

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
                        <div class="small">Logged in as:</div>
                        <%BUsuario cliente = (BUsuario) session.getAttribute("clienteSession");%>
                        <%=cliente.getNombre() + " " + cliente.getApellido()%>
                    </div>
                </nav>
            </div>
            <div id="layoutSidenav_content">
                <main>
                    <div class="container-fluid px-4 mt-3">

                        <h2><%=listaPedidoE.get(0).getPedido().getNombreFarmacia()%>
                        </h2>
                        <ol class="breadcrumb mb-4">
                            <li class="breadcrumb-item"><a
                                    href="<%= request.getContextPath()%>/Client_Listado_Producto">Lista de Pedidos</a>
                            </li>
                            <li class="breadcrumb-item">
                                <a href="<%= request.getContextPath()%>/Client_Pedido?idPG=<%=listaPedidoE.get(0).getPedido().getBoletaVenta()%>">Pedido
                                    #<%=listaPedidoE.get(0).getPedido().getBoletaVenta()%>
                                </a></li>
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
                                                <p class="card-title">
                                                    <small><%=listaPedidoE.get(0).getPedido().getEstadoPedido()%>
                                                    </small></p>
                                                <!-- hacer if() para los botones-->
                                            </div>

                                        </div>
                                        <%if (listaPedidoE.get(0).getFechaStatus() != null) {%>
                                        <div class="small fst-italic mt-2">Último
                                            cambio: <%=listaPedidoE.get(0).getFechaStatus()%>
                                        </div>

                                        <%}%>
                                    </div>
                                </div>
                            </div>
                        </div>
                        <div class="card mb-4">


                            <div class="card-header bg-warning">
                                <i class="fas fa-table me-1"></i>
                                Detalles de del pedido #<%=listaPedidoE.get(0).getPedido().getBoletaVenta()%>
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
                                        <%double subtotal = 0.0;%>
                                        <%for (BPedidoEstado pedidoE : listaPedidoE) {%>


                                        <tr>
                                            <th>
                                                <div class="container">
                                                    <div class="card-group">

                                                        <div class="card-body justify-content-start">
                                                            <a><img src="<%= request.getContextPath()%>/ImgServlet?prod=<%=pedidoE.getIdProducto()%>"
                                                                    class="img-thumbnail"
                                                                    alt="..."
                                                                    style="width: 100px; height: 100px"></a>

                                                        </div>
                                                        <div class="card-body mt-lg-4 mt-md-4 justify-content-center">

                                                            <a><%=pedidoE.getNombreProducto()%>
                                                            </a>
                                                        </div>
                                                    </div>

                                                </div>

                                            </th>
                                            <td>
                                                <div id="contenedor2">
                                                    <div style=" margin-top: 30px" class="producto2">
                                                        <div class="costo">
                                                            <span class="text-center">Cantidad: <%=pedidoE.getCantidad()%></span>
                                                            <br>
                                                            <span class="text-center">Precio unitario: <%=pedidoE.getPrecioUnitario()%></span>

                                                        </div>
                                                    </div>
                                                </div>
                                            </td>
                                            <td>
                                                <div style=" margin-top: 30px;margin-left: 12px"><%=pedidoE.getPrecioProducto()%>
                                                </div>
                                            </td>
                                        </tr>

                                        <%subtotal = subtotal + pedidoE.getPrecioProducto();%>
                                        <%}%>

                                        <tr>
                                            <th>
                                                <div id="contenedor1">
                                                    <div class="producto mt-5 ">
                                                        <div>
                                                            <img class="card-img text-start"
                                                                 style="width: 25px; height: 25px;"
                                                                 src="https://cdn-icons-png.flaticon.com/512/2370/2370264.png">
                                                            Fecha y hora de
                                                            recojo: <%=listaPedidoE.get(0).getPedido().getFechaEntrega()%>

                                                        </div>

                                                        <div class="mt-2">
                                                            <img class="card-img text-start"
                                                                 style="width: 35px; height: 35px;"
                                                                 src="https://cdn-icons-png.flaticon.com/512/1564/1564873.png"> <%=listaPedidoE.get(0).getDireccionFarm()%>
                                                            - <%=listaPedidoE.get(0).getDistristos().getNombre()%>
                                                        </div>
                                                    </div>
                                                </div>
                                            </th>
                                            <th>
                                                <div class="card-header text-black">Subtotal</div>
                                            </th>
                                            <td>
                                                <div class="card-header text-center"><%=subtotal%>
                                                </div>
                                            </td>
                                        </tr>
                                    </tbody>
                                </table>

                                <%if (sePuedeCancelar) {%>
                                <div class="d-md-block mt-3 mb-3 text-center font-weight-light my-5">
                                    <a class="btn btn-danger me-md-4"
                                       href="<%=request.getContextPath()%>/Client_Bolsa_Compra?action=cancelar&idPG=<%=listaPedidoE.get(0).getPedido().getBoletaVenta()%>&idF=<%=listaPedidoE.get(0).getPedido().getIdFarmacia()%>&idP=<%=listaPedidoE.get(0).getPedido().getIdPedido()%>">Cancelar</a>
                                </div>
                                <%} else {%>
                                <div class="d-md-block mt-3 mb-3 text-center font-weight-light my-5">
                                    <a class="disabled btn btn-dark btn- me-md-4" href="mensajes_emergentes7.html">Cancelar</a>
                                </div>
                                <%}%>

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

