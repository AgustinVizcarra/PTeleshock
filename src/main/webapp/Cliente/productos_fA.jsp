<%@page import="java.util.ArrayList" %>
<%@ page import="pe.edu.pucp.pteleshock.Beans.BDetProd" %>
<%@ page import="pe.edu.pucp.pteleshock.Beans.BUsuario" %>
<%@ page import="pe.edu.pucp.pteleshock.Beans.BPedidoEstado" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<jsp:useBean id="farmacia" type="pe.edu.pucp.pteleshock.Beans.BFarmaciaPorDistrito" scope="request"/>
<jsp:useBean type="java.lang.Integer" scope="request" id="totalPag"/>
<jsp:useBean type="java.lang.Integer" scope="request" id="pag"/>
<jsp:useBean type="java.lang.String" scope="request" id="nombreProducto" class="java.lang.String"/>
<%
    ArrayList<BDetProd> listaProductosF = (ArrayList<BDetProd>) request.getAttribute("listaProductosF");
%>
<html lang="en">

    <head>
        <meta charset="utf-8"/>
        <meta http-equiv="X-UA-Compatible" content="IE=edge"/>
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no"/>
        <meta name="description" content=""/>
        <meta name="author" content=""/>
        <title>Farmacia A</title>
        <link href="https://cdn.jsdelivr.net/npm/simple-datatables@latest/dist/style.css" rel="stylesheet"/>
        <link href="css/style.css" rel="stylesheet"/>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.3/js/all.min.js"
                crossorigin="anonymous"></script>
        <script>
            function mandar(){
                document.getElementById("popup1").submit();
            }
            document.getElementById("boton").onclick = function (){
                mandar();
            }
        </script>
    </head>

    <body class="sb-nav-fixed"
          style="background-image: url('img/Fondo_login.jpg'); background-repeat: repeat-y; background-size: cover;">

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
                                <a class="nav-link text-white bg-secondary" href="<%=request.getContextPath()%>/Client_Farmacias">
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
                                <a class="nav-link text-white"
                                   href="<%=request.getContextPath()%>/Client_Listado_Producto">
                                    <div class="sb-nav-link-icon"><i class="fas fa-clipboard-list"></i></div>
                                    Historial de pedidos
                                </a>
                            </li>
                        </ul>
                    </div>
                    <div class="sb-sidenav-footer" style="color: gray">
                        <div class="small">Logged in as:</div>
                        <%BUsuario cliente = (BUsuario) session.getAttribute("clienteSession");%>
                        <%=cliente.getNombre() + " " + cliente.getApellido()%>
                    </div>
                </nav>
            </div>

            <!-- Main -->
            <div id="layoutSidenav_content">
                <main>
                    <div class="container-fluid px-4">

                        <br>
                        <br>

                        <div class="card mb-4" style="opacity: 0.90;">
                            <div class="card-body">
                                <h1 class=" mt-4"><%=farmacia.getNombreFarmacia()%>
                                </h1>


                                <ol class="breadcrumb mb-2">
                                    <li class="breadcrumb-item"><a
                                            href="<%= request.getContextPath()%>/Client_Farmacias?idD=<%=farmacia.getIdDistritoF()%>">Farmacias</a></li>
                                    <li class="breadcrumb-item active"><%=farmacia.getNombreFarmacia()%>
                                    </li>

                                </ol>

                                <%--Buscador--%>

                                <div class="row  row-cols-4">
                                    <form class="d-none d-md-inline-block form-inline ms-auto me-0 me-md-3 my-2 my-md-0"
                                          method="post"
                                          action="<%=request.getContextPath()%>/Client_Productos_F">
                                        <div class="input-group">
                                            <input class="form-control" type="text" name="nombreProducto"
                                                   placeholder="Buscar..." aria-label="Buscar..."
                                                   aria-describedby="btnNavbarSearch"
                                                    <% if (nombreProducto != null) {%>
                                                   value="<%=nombreProducto%>"
                                                    <% } %> />
                                            <input class="form-control" type="text" name="idF"
                                                   hidden aria-describedby="btnNavbarSearch"
                                                   value="<%=farmacia.getIdFarmacia()%>"/>
                                            <button class="btn btn-primary" id="btnNavbarSearch" type="submit"
                                                    style="background-color: #00152D;border-color: #00152D">
                                                <i class="fas fa-search"></i></button>
                                            <a class="input-group-text"
                                               href="<%=request.getContextPath()%>/Client_Productos_F?idF=<%=farmacia.getIdFarmacia()%>">
                                                <i class="fas fa-undo"></i>
                                            </a>
                                        </div>
                                    </form>
                                </div>
                                <br><br>

                                <%if (listaProductosF.isEmpty()) {%>
                                <div class="text-center">
                                    <div class="alert alert-warning text-center" role="alert">
                                        <p><em>No se han encontrado productos disponibles con el nombre de "<%=nombreProducto%>"</em></p>
                                    </div>
                                    <a class="btn btn-primary me-md-4 text-center"
                                       href="<%=request.getContextPath()%>/Client_Farmacias">Ir a Farmacias</a>
                                </div>
                                <%} else {%>
                                <%--
                                <nav aria-label="Page navigation">
                                    <ul class="pagination justify-content-end text-primary">
                                        <li class="page-item  <%=pag==1?"disabled":""%>  ">
                                            <a class="page-link"
                                               href="<%=request.getContextPath()%>/Client_Productos_F?idF=<%=farmacia.getIdFarmacia()%>&pag=<%=pag-1%>">Anterior</a>
                                        </li>
                                        <%for (int i = 1; i <= totalPag; i++) {%>
                                        <li class="page-item <%=pag==i?"active":""%>">
                                            <a class="page-link"
                                               href="<%=request.getContextPath()%>/Client_Productos_F?idF=<%=farmacia.getIdFarmacia()%>&pag=<%=i%>"><%=i%>
                                            </a></li>
                                        <%}%>

                                        <li class="page-item <%=pag.equals(totalPag)?"disabled":""%>  ">
                                            <a class="page-link"
                                               href="<%=request.getContextPath()%>/Client_Productos_F?idF=<%=farmacia.getIdFarmacia()%>&pag=<%=pag+1%>">Siguiente</a>
                                        </li>
                                    </ul>
                                </nav>
                                ---%>
                                <section class="py-0">
                                    <div class="container px-4 px-lg-5 mt-5">

                                        <%if (session.getAttribute("proAgregado")!=null){%>
                                        <div class="alert alert-info" role="alert"><%=(String) session.getAttribute("proAgregado")%>
                                        </div>
                                        <%session.removeAttribute("proAgregado");%>
                                        <%}%>
                                        <div class="row gx-4 gx-lg-5 row-cols-2 row-cols-md-3 row-cols-xl-4 justify-content-center">
                                            <% for (BDetProd listaProd : listaProductosF) {%>
                                            <div class="col mb-5">
                                                <div class="card h-100">
                                                    <!-- Sale badge
                                                    <div class="badge bg-dark text-white position-absolute"
                                                        style="top: 0.5rem; right: 0.5rem">Sale</div>-->

                                                    <!-- Product image-->
                                                    <img class="card-img-top"
                                                         src="<%= request.getContextPath()%>/ImgClienteServlet?prod=<%=listaProd.getIdProducto()%>&idfarm=<%=farmacia.getIdFarmacia()%>">
                                                    <!-- Product details-->
                                                    <div class="card-body p-2">
                                                        <div class="text-center">
                                                            <!-- Product name-->
                                                            <h5 class="fw-bolder"><%=listaProd.getNombre()%>
                                                            </h5>
                                                            <!-- Product price-->
                                                            <a>Stock: <%=listaProd.getStock()%>
                                                            </a>
                                                            <br>
                                                            <a>S/. <%=listaProd.getPreciounitario()%>
                                                            </a>
                                                        </div>
                                                    </div>
                                                    <!-- Product actions-->
                                                    <div class="card-footer p-2 pt-0 border-top-0 bg-transparent text-center">
                                                        <form method="POST"
                                                              action="<%=request.getContextPath()%>/Client_Bolsa_Compra?action=agregar">
                                                            <input type="hidden" class="form-control" name="idProd"
                                                                   value="<%=listaProd.getIdProducto()%>">
                                                            <input type="hidden" class="form-control" name="cantidad"
                                                                   value="1">
                                                            <input type="hidden" class="form-control" name="idFarmacia"
                                                                   value="<%=farmacia.getIdFarmacia()%>">
                                                            <input type="hidden" class="form-control" name="receta"
                                                                   value="<%=listaProd.isRecetaMedicaB()?"1":"0"%>">
                                                           <%-- <input type="button" value="Agregar" onclick="closeSelf();">-- <button class="btn btn-outline-dark mt-auto" type="submit">Agregar</button> --%>
                                                            <button id="boton" class="btn btn-outline-dark mt-auto" type="submit">Agregar</button>
                                                        </form>
                                                    </div>
                                                    <div class="card-footer p-4 pt-0 border-top-0  bg-transparent">
                                                        <div class="text-center"><a class="btn btn-outline-dark mt-auto"
                                                                                    href="<%= request.getContextPath()%>/Client_Detalles_Producto?idF=<%=farmacia.getIdFarmacia()%>&idP=<%=listaProd.getIdProducto()%>">Ver
                                                            detalles</a>
                                                        </div>
                                                    </div>
                                                </div>
                                            </div>
                                            <%}%>
                                        </div>
                                    </div>
                                </section>
                                <div>
                                    <a class="btn btn-secondary" href="<%= request.getContextPath()%>/Client_Farmacias?idD=<%=farmacia.getIdDistritoF()%>">Regresar a farmacias</a>
                                </div>

                                <nav aria-label="Page navigation">
                                    <ul class="pagination justify-content-end text-primary">
                                        <li class="page-item  <%=pag==1?"disabled":""%>  ">
                                            <a class="page-link"
                                               href="<%=request.getContextPath()%>/Client_Productos_F?idF=<%=farmacia.getIdFarmacia()%>&pag=<%=pag-1%>">Anterior</a>
                                        </li>
                                        <%for (int i = 1; i <= totalPag; i++) {%>
                                        <li class="page-item <%=pag==i?"active":""%>">
                                            <a class="page-link"
                                               href="<%=request.getContextPath()%>/Client_Productos_F?idF=<%=farmacia.getIdFarmacia()%>&pag=<%=i%>"><%=i%>
                                            </a></li>
                                        <%}%>

                                        <li class="page-item <%=pag.equals(totalPag)?"disabled":""%>  ">
                                            <a class="page-link"
                                               href="<%=request.getContextPath()%>/Client_Productos_F?idF=<%=farmacia.getIdFarmacia()%>&pag=<%=pag+1%>">Siguiente</a>
                                        </li>
                                    </ul>
                                </nav>
                                <%}%>
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
                <h5 class="card-header text-center text-light">Teleshock</h5>
                <div class="card-body">
                    <h5 class="card-title p-2">¡Producto Añadido!</h5>
                    <a href="#" class="btn btn-success mb-2">Seguir Comprando</a>
                    <a href="<%= request.getContextPath()%>/Client_Bolsa_Compra" class="btn btn-warning mb-2">Ir a la
                        bolsa de compras</a>

                </div>
            </div>
        </nav>

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
        <script src="https://cdnjs.cloudflare.com/ajax/libs/Chart.js/2.8.0/Chart.min.js"
                crossorigin="anonymous"></script>
        <script src="https://cdn.jsdelivr.net/npm/simple-datatables@latest" crossorigin="anonymous"></script>

    </body>

</html>