<%@ page import="pe.edu.pucp.pteleshock.Beans.BPedidoG" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="pe.edu.pucp.pteleshock.Beans.BPedidoD" %>
<%@ page import="pe.edu.pucp.pteleshock.Beans.BEstado" %>
<%@ page import="pe.edu.pucp.pteleshock.Beans.BUsuario" %><%--
  Created by IntelliJ IDEA.
  User: casa
  Date: 5/11/2021
  Time: 18:59
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<% ArrayList<BPedidoD> listaDPedido=(ArrayList<BPedidoD>) request.getAttribute("listaDPedido");%>
<% ArrayList<BPedidoD> listaproducto=(ArrayList<BPedidoD>) request.getAttribute("listaproducto");%>
<% ArrayList<BEstado> listaEstados=(ArrayList<BEstado>) request.getAttribute("listaEstados");%>
<jsp:useBean type="java.lang.String" scope="request" id="msg" class="java.lang.String"/>
<jsp:useBean type="java.lang.String" scope="request" id="cantPed" class="java.lang.String"/>
<html lang="en">
<head>

    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link href="https://fonts.googleapis.com/css?family=Raleway:200,100,400" rel="stylesheet" type="text/css" />
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
    <style>

        .overlay1{
            display: flex; justify-content: center ; align-items: center; position: fixed;
            top: 0;
            bottom: 0;
            left: 0;
            right: 0;
            background: rgba(0, 0, 0, 0.7);
            transition: opacity 500ms;
            visibility: visible;
            opacity: 1;

        }
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
            <a class="nav-link" style="color: white" href="#popup3" role="button"><i
                    class="fas fa-sign-out-alt"></i></a>
        </li>
    </ul>


</nav>
<div id="layoutSidenav">
    <div id="layoutSidenav_nav" >
        <nav class="sb-sidenav accordion " style="background-color:#00152D ; opacity: 0.9" id="sidenavAccordion" >
<%--            <jsp:include page="/includes/despegable.jsp">--%>
<%--                <jsp:param name="page" value="pedido"/>--%>
<%--            </jsp:include>--%>
            <div class="sb-sidenav-menu" >
                <div class="nav" style="margin-top: 30px">
                    <a class=" nav-link "  style="color: white" href="<%= request.getContextPath()%>/Farm_Pefil">
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

                    <a class="nav-link bg-secondary" style="color: white" href="<%= request.getContextPath()%>/Farm_Gestionar_Pedidos">
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
            <div class="sb-sidenav-footer" style="color: darkgrey">
            </div>
        </nav>
    </div>
    <div id="layoutSidenav_content">
        <main>
            <div class="container-fluid px-4">
                <h1 class="mt-4">Detalles del Pedido</h1>
                <ol class="breadcrumb mb-4">
                    <li class="breadcrumb-item"><a href="<%= request.getContextPath()%>/Farm_Gestionar_Pedidos">Lista de Pedidos</a></li>
                </ol>
                <div class="card mb-4">
                    <div class="card-body">
                        Se puede realizar en cambio de estado
                    </div>
                </div>
                <div class="row">
                    <%for(BPedidoD bPedidoD:listaDPedido){%>
                    <div class="card col-6 mb-4">
                        <form method="post" action="<%=request.getContextPath()%>/Farm_Detalle_Pedido_Mod?action=editar">
                            <h4 class="text-center">Pedido</h4>
                            <div class="row">
                                <div class="col-3">
                                    <p >Fecha Pedido:</p>
                                    <p >Fecha Entrega:</p>
                                    <p >Codigo:</p>
                                    <p >Estado:</p>
                                </div>
                                <div class="col-4">
                                    <p ><%=bPedidoD.getFecha()%></p>
                                    <p ><%=bPedidoD.getFechaEntrega()%></p>
                                    <p ><%=bPedidoD.getCodigoV()%></p>
                                    <input class="form-control" type="hidden"  name="idPedido" value="<%=bPedidoD.getIdpedido()%>">
                                    <select name="idEstado">
                                        <%for(BEstado bEstado:listaEstados){%>
                                        <option value="<%=bEstado.getIdEstado()%>" <%=bPedidoD.getEstado().equals(bEstado.getEstado()) ? "selected": ""%>><%=bEstado.getEstado()%></option>
                                        <%}%>
                                    </select>
                                </div>
                            </div>
                            <div style="padding-bottom:5px; display: flex;justify-content: center">

                                <button type="submit" class="btn btn-warning" >Actualizar</button>

                            </div>


                        </form>
                    </div>
                    <div class="card col-6 mb-4 ">
                        <h4 class="text-center">Cliente</h4>
                        <div class="row">
                            <div class="col-3">
                                <p >Nombre:</p>
                                <p >Apellido:</p>
                            </div>
                            <div class="col-3">
                                <p ><%=bPedidoD.getNombre()%></p>
                                <p ><%=bPedidoD.getApellido()%></p>
                            </div>
                            <div class="col-2">
                                <p >DNI:</p>
                                <p >Distrito:</p>
                            </div>
                            <div class="col-4">
                                <p><%=bPedidoD.getDni()%></p>
                                <p ><%=bPedidoD.getDistrito()%></p>
                            </div>
                        </div>
                        <div>
                            <div class="row">
                                <div class="col-2">
                                    <p >Correo:</p>
                                </div>
                                <div class="col-4">
                                    <p ><%=bPedidoD.getCorreo()%></p>
                                </div>
                            </div>
                        </div>
                    </div>

                </div>
                <div class="row">
                    <div class="col-9"></div>
                    <%if(bPedidoD.getFechaEstatus().equals("no")){%>
                    <div class="col-3 small fst-italic">No hubo modificaciones </div>
                    <%}else{%>
                    <div class="col-3 small fst-italic">Ultima modificacion:  <%=bPedidoD.getFechaEstatus()%> </div>
                    <%}%>
                </div>
                <%}%>
                <div class="card mb-4">
                    <div class="card-header  bg-warning">
                        <i class="fas fa-table me-1"></i>
                        Lista de productos
                    </div>
                    <div class="card-body">
                        <table class="table">
                            <thead>
                            <tr>
                                <th>Nombre:</th>
                                <th>Descripción:</th>
                                <th>Unidades:</th>
                                <th>Precio Unitario:</th>
                                <th>Receta Médica:</th>
                            </tr>
                            </thead>
                            <%int k = 1;%>
                            <%for(BPedidoD bPedidoD:listaproducto){%>
                            <tbody>
                            <tr>
                                <td><%=bPedidoD.getProducto()%></td>
                                <td><%=bPedidoD.getDescripcion()%></td>
                                <td><%=bPedidoD.getUnidades()%></td>
                                <td>S/<%=bPedidoD.getPrecioUnitario()%></td>
                                <%if(!bPedidoD.isRecetamedica()){%>
                                <td>No</td>
                                <%}else{%>
                                <td><a href="#img<%=k%>">
                                    <img src="<%= request.getContextPath()%>/ImgRecetaServlet?prod=<%=bPedidoD.getIdproducto()%>&idped=<%=session.getAttribute("idpedido")%>&idfarm=<%=session.getAttribute("idFarmacia")%>" width="100px">
                                </a>

                                    <!-- lightbox container hidden with CSS -->
                                    <a href="#" class="lightbox" id="img<%=k%>">
                                        <span style="background-image: url('<%= request.getContextPath()%>/ImgRecetaServlet?prod=<%=bPedidoD.getIdproducto()%>&idped=<%=session.getAttribute("idpedido")%>&idfarm=<%=session.getAttribute("idFarmacia")%>')"></span>
                                    </a></td>
                                <%k++;%>
                                <%}%>
                            </tr>
                            </tbody>
                            <%}%>
                        </table>
                    </div>
                    <nav aria-label="Page navigation example">
                        <ul class="pagination justify-content-end">
                            <% int cantPedInt=Integer.parseInt(cantPed);%>
                            <% int resto= cantPedInt%6==0? 0:1; %>
                            <%int idped= (int) session.getAttribute("idpedido");%>
                            <% for(int i=1; i<Math.floor(cantPedInt/6)+resto+1; i++) { %>
                            <li class="page-item"><a class="page-link" href="<%= request.getContextPath()%>/Farm_Detalle_Pedido_Mod?id=<%=idped%>&pag=<%=i %>"><%=i%></a></li>
                            <% } %>

                        </ul>
                    </nav>

                </div>
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

<%if(msg.equals("ok")){ %>
<nav id="popup" class="overlay1" style="display: flex; justify-content: center ; align-items: center">
    <div class=" popup card text-center " style="background-color: white">
        <h5 class="card-header text-center ">Teleshock</h5>
        <div class="card-body">
            <form>
                <h5 class="card-title p-2">Se ha editado el estado correctamente</h5>
                <div class="btn-group" role="group" aria-label="Basic example" style="display: flex;justify-content: center">

                    <%for(BPedidoD bPedidoD1:listaDPedido){%>
                    <a href="<%= request.getContextPath()%>/Farm_Detalles_Pedido?id=<%=bPedidoD1.getIdpedido()%>" class="btn btn-success mb-2">Aceptar</a>
                    <%}%>
                </div>
            </form>
        </div>
    </div>
</nav>
<% }else if(msg.equals("cancelado") || msg.equals("pendiente") || msg.equals("entregado")){%>
    <nav id="popup" class="overlay1" style="display: flex; justify-content: center ; align-items: center">
        <div class=" popup card text-center " style="background-color: white">
            <h5 class="card-header text-center ">Teleshock</h5>
            <div class="card-body">
                <form>
                    <h5 class="card-title p-2">El estado del pedido ya se encuentra <%=msg%></h5>
                    <div class="btn-group" role="group" aria-label="Basic example" style="display: flex;justify-content: center">


                        <a href="<%= request.getContextPath()%>/Farm_Detalle_Pedido_Mod?id=<%=idped%>" class="btn btn-success mb-2">Aceptar</a>

                    </div>
                </form>
            </div>
        </div>
    </nav>
<%}else if(msg.equals("cancel")){%>
    <nav id="popup" class="overlay1" style="display: flex; justify-content: center ; align-items: center">
        <div class=" popup card text-center " style="background-color: white">
            <h5 class="card-header text-center ">Teleshock</h5>
            <div class="card-body">
                <form>
                    <h5 class="card-title p-2">No se puede actualizar el estado, el pedido se encuentra cancelado</h5>
                    <div class="btn-group" role="group" aria-label="Basic example" style="display: flex;justify-content: center">


                        <a href="<%= request.getContextPath()%>/Farm_Detalle_Pedido_Mod?id=<%=idped%>" class="btn btn-success mb-2">Aceptar</a>

                    </div>
                </form>
            </div>
        </div>
    </nav>
    <%}else if(msg.equals("entreg")){%>
    <nav id="popup" class="overlay1" style="display: flex; justify-content: center ; align-items: center">
        <div class=" popup card text-center " style="background-color: white">
            <h5 class="card-header text-center ">Teleshock</h5>
            <div class="card-body">
                <form>
                    <h5 class="card-title p-2">No se puede actualizar el estado, el pedido se encuentra entregado</h5>
                    <div class="btn-group" role="group" aria-label="Basic example" style="display: flex;justify-content: center">


                        <a href="<%= request.getContextPath()%>/Farm_Detalle_Pedido_Mod?id=<%=idped%>" class="btn btn-success mb-2">Aceptar</a>

                    </div>
                </form>
            </div>
        </div>
    </nav>
    <%}%>

<nav id="popup2" class="overlay" style="display: flex; justify-content: center ; align-items: center">
    <div class=" popup card text-center " style="background-color: white">
        <h5 class="card-header text-center ">Teleshock</h5>
        <div class="card-body">
            <h5 class="card-title p-2">Se ha editado el estado correctamente</h5>
            <%for(BPedidoD bPedidoD1:listaDPedido){%>
            <a href="<%= request.getContextPath()%>/Farm_Detalles_Pedido?id=<%=bPedidoD1.getIdpedido()%>" class="btn btn-success mb-2">Aceptar</a>
            <%}%>
        </div>
    </div>
</nav>


<nav id="popup3" class="overlay" style="display: flex; justify-content: center ; align-items: center">
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