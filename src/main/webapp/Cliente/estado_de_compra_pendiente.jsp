<%@ page import="pe.edu.pucp.pteleshock.Beans.BPedido" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="pe.edu.pucp.pteleshock.Beans.BPedidoEstado" %>
<%@ page import="pe.edu.pucp.pteleshock.Beans.BUsuario" %>
<%@ page import="java.text.DecimalFormat" %><%--
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    ArrayList<BPedidoEstado> listaPedidoE = (ArrayList<BPedidoEstado>) request.getAttribute("listaPedidoE");
    int sePuedeCancelar = (int) request.getAttribute("sePuedeCancelar");
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
        <link />
        <style>
            .lightbox {
                /* Default to hidden */
                display: none;

                /* Overlay entire screen */
                position: fixed;
                z-index: 999;
                top: 0;
                left: 0;
                right: 0;
                bottom: 0;

                /* A bit of padding around image */
                padding: 1em;

                /* Translucent background */
                background: rgba(0, 0, 0, 0.8);
            }

            /* Unhide the lightbox when it's the target */
            .lightbox:target {
                display: block;
            }

            .lightbox span {
                /* Full width and height */
                display: block;
                width: 800px;
                height: 100%;

                /* Size and position background image */
                background-position: center;
                background-repeat: no-repeat;
                background-size: contain;
                margin-left: auto;
                margin-right: auto;
            }
        </style>

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
                        <ul class="nav">
                            <li>
                                <a class="nav-link text-white" href="<%=request.getContextPath()%>/Client_Perfil">
                                    <div class="sb-nav-link-icon"><i class="fas fa-user-alt"></i></div>
                                    Mi perfil
                                </a>
                            </li>
                            <li>
                                <a class="nav-link text-white" href="<%=request.getContextPath()%>/Client_Farmacias">
                                    <div class="sb-nav-link-icon"><i class="fas fa-clinic-medical"></i></div>
                                    Farmacias
                                </a>
                            </li>
                            <li>
                                <a class="nav-link text-white"
                                   href="<%=request.getContextPath()%>/Client_Bolsa_Compra">
                                    <div class="active sb-nav-link-icon"><i class="fas fa-shopping-cart"></i></div>
                                    Bolsa de Compras
                                </a>
                            </li>
                            <li>
                                <a class="nav-link text-white bg-secondary"
                                   href="<%=request.getContextPath()%>/Client_Listado_Producto">
                                    <div class="sb-nav-link-icon"><i class="fas fa-clipboard-list"></i></div>
                                    Historial de pedidos
                                </a>
                            </li>
                        </ul>
                    </div>
                    <div class="sb-sidenav-footer" style="color: gray">
                        <div class="small">Logueado como:</div>
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
                        <%if(sePuedeCancelar==2){%>
                        <%---
                        <div class="alert alert-danger text-center" role="alert">
                            <p><em><%="Usted se encuentra atrasado en el recojo de su pedido"%></em></p>
                        </div>
                        --%>
                        <div class="alert alert-danger d-flex align-items-center" role="alert">
                            <svg class="bi flex-shrink-0 me-2" width="24" height="24" role="img" aria-label="Danger:"><use xlink:href="#exclamation-triangle-fill"/></svg>
                            <div>
                                Usted se encuentra atrasado en el recojo de su pedido.
                            </div>
                        </div>
                        <%}%>

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
                                        <div class="small fst-italic mt-2">??ltimo
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
                                            <th>Receta M??dica:</th>
                                        </tr>
                                    </thead>


                                    <tbody>
                                        <%int k = 1;%>
                                        <%double subtotal = 0.0;%>
                                        <%for (BPedidoEstado pedidoE : listaPedidoE) {%>

                                        <tr>
                                            <th>
                                                <div class="container">
                                                    <div class="card-group">

                                                        <div class="card-body justify-content-start">
                                                            <img src="<%= request.getContextPath()%>/ImgClienteServlet?prod=<%=pedidoE.getIdProducto()%>&idfarm=<%=listaPedidoE.get(0).getPedido().getIdFarmacia()%>"
<%--                                                                    class="img-thumbnail"--%>
                                                                    style="width: 100px; height: 100px">

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
                                                <%DecimalFormat df = new DecimalFormat("##.##"); %>
                                                <div style=" margin-top: 30px;margin-left: 12px"><%=df.format(pedidoE.getPrecioProducto())%>
                                                </div>
                                            </td>
                                            <%if(pedidoE.isRecetaMedica()){%>

                                            <td>
                                                <a href="#img<%=k%>">
                                                    <img src="<%= request.getContextPath()%>/ImgRecetaServlet?prod=<%=pedidoE.getIdProducto()%>&idped=<%=listaPedidoE.get(0).getPedido().getIdPedido()%>&idfarm=<%=listaPedidoE.get(0).getPedido().getIdFarmacia()%>" width="100px">
                                                </a>

                                                <!-- lightbox container hidden with CSS -->
                                                <a href="#" class="lightbox" id="img<%=k%>">
                                                    <span style="background-image: url('<%= request.getContextPath()%>/ImgRecetaServlet?prod=<%=pedidoE.getIdProducto()%>&idped=<%=listaPedidoE.get(0).getPedido().getIdPedido()%>&idfarm=<%=listaPedidoE.get(0).getPedido().getIdFarmacia()%>')"></span>
                                                </a>
                                                <%k++;%>
                                            </td>
                                            <%}else{%>
                                            <td class="text-center">No requiere</td>
                                            <%}%>
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
                                                <%DecimalFormat df = new DecimalFormat("##.##"); %>
                                                <div class="card-header text-center"><%=df.format(subtotal)%>
                                                </div>
                                            </td>
                                        </tr>
                                    </tbody>
                                </table>

                                <%if (sePuedeCancelar==1) {%>
                                <div class="d-md-block mt-3 mb-3 text-center font-weight-light my-5">
                                    <a class="btn btn-danger me-md-4"
                                       href="#popup1">Cancelar</a>
                                </div>
                                <%} else if(sePuedeCancelar==0){
                                    if(listaPedidoE.get(0).getPedido().getEstadoPedido().equalsIgnoreCase("pendiente")){
                                %>
                                <div class="d-md-block mt-3 mb-3 text-center font-weight-light my-5">
                                    <a tabindex="0" class="btn btn-secondary me-md-4 d-inline-block" data-bs-trigger="hover focus" role="button" data-bs-toggle="popover" title="Teleshock" data-bs-placement="bottom" data-bs-content="No es posible cancelar el pedido porque venci?? fecha l??mite">Cancelar</a>
                                </div>
                                <%} else if(listaPedidoE.get(0).getPedido().getEstadoPedido().equalsIgnoreCase("cancelado")){%>
                                <div class="d-md-block mt-3 mb-3 text-center font-weight-light my-5">
                                    <a tabindex="0" class="btn btn-secondary me-md-4 d-inline-block" role="button" data-bs-trigger="hover focus" data-bs-toggle="popover" title="Teleshock" data-bs-placement="bottom" data-bs-content="El pedido ya se encuentra cancelado">Cancelar</a>
                                </div>
                                <%}else{%>
                                <div class="d-md-block mt-3 mb-3 text-center font-weight-light my-5">
                                    <a tabindex="0" class="btn btn-secondary me-md-4 d-inline-block" role="button" data-bs-trigger="hover focus" data-bs-toggle="popover" title="Teleshock" data-bs-placement="bottom" data-bs-content="Se hizo entrega del producto">Cancelar</a>
                                </div>
                                <%}%>
                                <%}else{%>
                                <div class="d-md-block mt-3 mb-3 text-center font-weight-light my-5">
                                    <a tabindex="0" class="btn btn-secondary me-md-4 d-inline-block" data-bs-trigger="hover focus" role="button" data-bs-toggle="popover" title="Teleshock" data-bs-placement="bottom" data-bs-content="No es posible cancelar el pedido porque venci?? fecha l??mite">Cancelar</a>
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

        <nav id="popup1" class="overlay">
            <div class=" popup card text-center ">
                <h5 class="card-header text-center">Teleshock</h5>
                <div class="card-body">
                    <h5 class="card-title p-2">??Desea cancelar el pedido?</h5>
                    <p>No podr?? rehacer esta acci??n.</p>
                    <a href="<%=request.getContextPath()%>/Client_Bolsa_Compra?action=cancelar&idPG=<%=listaPedidoE.get(0).getPedido().getBoletaVenta()%>&idF=<%=listaPedidoE.get(0).getPedido().getIdFarmacia()%>&idP=<%=listaPedidoE.get(0).getPedido().getIdPedido()%>" class="btn btn-danger mb-2">S??</a>
                    <a href="#" class="btn btn-primary mb-2">No</a>
                </div>
            </div>
        </nav>

        <nav id="salir" class="overlay">
            <div class=" popup card text-center " style="background-color: white">
                <h5 class="card-header text-center">Teleshock</h5>
                <div class="card-body">
                    <h5 class="card-title p-2">??Desea cerrar esta sesi??n?</h5>
                    <a href="#" class="btn btn-primary mb-2">Cancelar</a>
                    <form method="post" action="<%=request.getContextPath()%>/Login?action=logout">
                        <button class="btn btn-danger mb-2" type="submit">
                            Salir
                        </button>
                    </form>

                </div>
            </div>
        </nav>

        <svg xmlns="http://www.w3.org/2000/svg" style="display: none;">
            <symbol id="check-circle-fill" fill="currentColor" viewBox="0 0 16 16">
                <path d="M16 8A8 8 0 1 1 0 8a8 8 0 0 1 16 0zm-3.97-3.03a.75.75 0 0 0-1.08.022L7.477 9.417 5.384 7.323a.75.75 0 0 0-1.06 1.06L6.97 11.03a.75.75 0 0 0 1.079-.02l3.992-4.99a.75.75 0 0 0-.01-1.05z"/>
            </symbol>
            <symbol id="info-fill" fill="currentColor" viewBox="0 0 16 16">
                <path d="M8 16A8 8 0 1 0 8 0a8 8 0 0 0 0 16zm.93-9.412-1 4.705c-.07.34.029.533.304.533.194 0 .487-.07.686-.246l-.088.416c-.287.346-.92.598-1.465.598-.703 0-1.002-.422-.808-1.319l.738-3.468c.064-.293.006-.399-.287-.47l-.451-.081.082-.381 2.29-.287zM8 5.5a1 1 0 1 1 0-2 1 1 0 0 1 0 2z"/>
            </symbol>
            <symbol id="exclamation-triangle-fill" fill="currentColor" viewBox="0 0 16 16">
                <path d="M8.982 1.566a1.13 1.13 0 0 0-1.96 0L.165 13.233c-.457.778.091 1.767.98 1.767h13.713c.889 0 1.438-.99.98-1.767L8.982 1.566zM8 5c.535 0 .954.462.9.995l-.35 3.507a.552.552 0 0 1-1.1 0L7.1 5.995A.905.905 0 0 1 8 5zm.002 6a1 1 0 1 1 0 2 1 1 0 0 1 0-2z"/>
            </symbol>
        </svg>

        <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.10.2/dist/umd/popper.min.js" integrity="sha384-7+zCNj/IqJ95wo16oMtfsKbZ9ccEh31eOz1HGyDuCQ6wgnyJNSYdrPa03rtR1zdB" crossorigin="anonymous"></script>
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.0/dist/js/bootstrap.bundle.min.js"
                crossorigin="anonymous"></script>
        <script src="js/scripts.js"></script>
        <script src="https://cdn.jsdelivr.net/npm/simple-datatables@latest" crossorigin="anonymous"></script>
        <script>
            var popoverTriggerList = [].slice.call(document.querySelectorAll('[data-bs-toggle="popover"]'))
            var popoverList = popoverTriggerList.map(function (popoverTriggerEl) {
                return new bootstrap.Popover(popoverTriggerEl)
            })
        </script>
    </body>
</html>

