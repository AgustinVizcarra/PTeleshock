<%@ page import="java.util.ArrayList" %>
<%@ page import="pe.edu.pucp.pteleshock.Beans.BPedidoEstado"%>
<%@ page import="pe.edu.pucp.pteleshock.Beans.BUsuario" %>
<%@ page import="java.util.HashMap" %>
<%@ page import="java.util.Map" %>
<%@ page import="java.time.LocalDate" %>
<%@ page import="java.time.LocalTime" %>
<%@ page import="java.time.format.DateTimeFormatter" %>
<%@ page import="java.time.LocalDateTime" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html lang="en">
    <head>
        <meta charset="utf-8"/>
        <meta http-equiv="X-UA-Compatible" content="IE=edge"/>
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no"/>
        <meta name="description" content=""/>
        <meta name="author" content=""/>
        <title>Bolsa de Compras</title>
        <link href="https://cdn.jsdelivr.net/npm/simple-datatables@latest/dist/style.css" rel="stylesheet"/>
        <link href="css/style.css" rel="stylesheet"/>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.3/js/all.min.js"
                crossorigin="anonymous"></script>
    </head>
    <body class="sb-nav-fixed">
        <nav class="sb-topnav navbar navbar-expand" style="background-color: #00152D; opacity: 0.9;">
            <!-- Navbar Brand-->
            <a class="navbar-brand ps-3" style="color: white"
               href="<%= request.getContextPath()%><%!private class LocalDateTime { }
%>/Client_Farmacias"><img margin-right class="icon"
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
                        <%=cliente.getNombre()+" "+cliente.getApellido()%>
                    </div>
                </nav>
            </div>

            <!-- Main -->
            <div id="layoutSidenav_content">
                <main>
                    <div class="container-fluid px-5 mt-5">
                        <ol class="breadcrumb mb-4">
                            <li class="breadcrumb-item"><a href="<%= request.getContextPath()%>/Client_Farmacias">Farmacias</a>
                            </li>
                            <li class="breadcrumb-item active">Bolsa de compras</li>
                        </ol>

                        <div class="row px-3">
                            <div class="col-7">
                                <div class="card-body">
                                    <h5 class="card-title">Bolsa de Compras</h5>
                                    <div class="accordion" id="accordionExample">
                                        <%HashMap<String, ArrayList<BPedidoEstado>> map=(HashMap<String, ArrayList<BPedidoEstado>>) session.getAttribute("map");
                                            HashMap<String, Double> map2 = new HashMap<>();%>
                                        <%for (Map.Entry<String, ArrayList<BPedidoEstado>> ee : map.entrySet()) {
                                            String key = ee.getKey();
                                            ArrayList<BPedidoEstado> values = ee.getValue();
                                        %>
                                        <div class="accordion-item">
                                            <h2 class="accordion-header" id="headingOne">
                                                <div class="row">
                                                    <div class="col">
                                                        <button class="accordion-button" type="button" data-bs-toggle="collapse" data-bs-target="#collapseOne" aria-expanded="true" aria-controls="collapseOne">
                                                            <div class="col">
                                                                <h6 class="card-title"><%=ee.getKey()%>
                                                                </h6>
                                                            </div>
                                                            <div class="col">
                                                                <div>
                                                                    <form method="post" action="<%=request.getContextPath()%>/Client_Bolsa_Compra?action=comprar">
                                                                        <div>
                                                                            <input id="party" type="datetime-local" name="partydate"
                                                                                   value="<%=LocalDate.now()+"T"+"09:30"%>"
                                                                                   min="<%=LocalDate.now()+"T"+"09:30"%>" max=""
                                                                                   pattern="[0-9]{4}-[0-9]{2}-[0-9]{2}T[0-9]{2}:[0-9]{2}" required>
                                                                            <span class="validity"></span>
                                                                        </div>
                                                                    </form>
                                                                </div>
                                                            </div>
                                                        </button>
                                                    </div>
                                                </div>
                                            </h2>
                                            <div id="collapseOne" class="accordion-collapse collapse show" aria-labelledby="headingOne" data-bs-parent="#accordionExample">
                                                <div class="accordion-body">
                                                    <table class="table align-content-center">
                                                        <thead>
                                                            <tr>
                                                                <th scope="col">Producto</th>
                                                                <th scope="col"></th>
                                                                <th scope="col">Unidad</th>
                                                                <th scope="col">Precio</th>
                                                                <th scope="col" style="text-align:center">Receta</th>
                                                                <th scope="col"></th>
                                                            </tr>
                                                        </thead>
                                                        <%double subtotal = 0.0;%>
                                                        <% for(int i=0;i<values.size();i++) {%>
                                                        <tbody>
                                                            <tr>
                                                                <th scope="row"><img  src="<%= request.getContextPath()%>/ImgServlet?prod=<%=ee.getValue().get(i).getFotoProducto()%>"
                                                                                      class="img-thumbnail"
                                                                                      alt="..."
                                                                                      style="width: 100px; height: 100px"></th>
                                                                <td><small><%=ee.getValue().get(i).getNombreProducto()%>
                                                                </small></td>
                                                                <td>
                                                                    <input class="form-control" id="unidadProd" type="number" name="unidadProd" min="1" value="1" max="10">
                                                                </td>
                                                                <td><%=(ee.getValue().get(i).getPrecioUnitario() * ee.getValue().get(i).getCantidad())%>
                                                                </td>
                                                                <%subtotal = subtotal + (ee.getValue().get(i).getPrecioUnitario() * ee.getValue().get(i).getCantidad());
                                                                %>

                                                                <%if (ee.getValue().get(i).isRecetaMedica()) {%>
                                                                <td>
                                                                    <div class="mb-3">
                                                                        <input class="form-control" name="fotoReceta" type="file" id="formFile1">
                                                                    </div>
                                                                </td>
                                                                <%} else {%>
                                                                <td class="text-center">No requiere</td>
                                                                <%}%>
                                                                <td class="align-content-md-center">
                                                                    <a class="btn btn-danger" href="<%=request.getContextPath()%>/Client_Bolsa_Compra?action=borrar&idProd=<%=ee.getValue().get(i).getIdProducto()%>&idF=<%=ee.getValue().get(i).getPedido().getIdFarmacia()%>&idP=<%=ee.getValue().get(i).getPedido().getIdPedido()%>"><span class="fa fa-trash"></span></a>
                                                                </td>
                                                            </tr>
                                                        </tbody>
                                                        <%}%>
                                                        <%
                                                            map2.put(ee.getKey(),subtotal);
                                                        %>
                                                    </table>
                                                </div>
                                            </div>
                                        </div>
                                        <%}%>
                                    </div>
                                </div>

                            </div>

                            <div class=" col-5 my-3">
                                <h5 class="card-title mb-4" >Resumen de compra</h5>
                                <form method="POST" action="<%=request.getContextPath()%>/Client_Bolsa_Compra?action=comprar">
                                    <%double total = 0.0;%>
                                    <%for (Map.Entry<String, Double> ee : map2.entrySet()) {
                                        String key = ee.getKey();
                                        Double values = ee.getValue();
                                    %>
                                    <div class="row">
                                        <div class="col"><h6><%=ee.getKey()%></h6></div>
                                        <div class="col"><h6><%=ee.getValue()%></h6></div>
                                        <%total=total+ee.getValue();
                                        session.setAttribute("total",total);
                                        %>
                                    </div>
                                    <%}%>
                                    <hr style="color: black; background-color: black; width:60%;" />
                                    <div class="row">
                                        <div class="col"><h6>Total</h6></div>
                                        <div class="col"><h6><%=total%></h6></div>
                                    </div>
                                    <button type="submit" class="btn btn-warning">Realizar Pedido </button>
                                </form>
                            </div>
                        </div>
                    </div>
                </main>
                <footer class="py-4 bg-light mt-auto">
                    <div class="container-fluid px-4">
                        <div class="d-flex align-items-center justify-content-between small">
                            <div class="text-muted">Copyright &copy; Your Website 2021</div>
                            <div>
                                <a href="#">Privacy Policy</a>
                                <a href="#">Terms &amp; Conditions</a>
                            </div>
                        </div>
                    </div>
                </footer>
            </div>
        </div>

        <nav id="salir" class="overlay">
            <div class=" popup card text-center ">
                <h5 class="card-header text-center text-light">Teleshock</h5>
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
        <script src="https://cdn.jsdelivr.net/npm/simple-datatables@latest" crossorigin="anonymous"></script>
    </body>
</html>
