<%@ page import="pe.edu.pucp.pteleshock.Beans.BFarmaciaPorDistrito" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="pe.edu.pucp.pteleshock.Beans.BDistristos" %>
<%@ page import="pe.edu.pucp.pteleshock.Beans.BUsuario" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<jsp:useBean type="java.lang.Integer" scope="request" id="totalPag"/>
<jsp:useBean type="java.lang.Integer" scope="request" id="pag"/>

<jsp:useBean type="java.lang.String" scope="request" id="nombreFarmacia" class="java.lang.String"/>
<jsp:useBean type="pe.edu.pucp.pteleshock.Beans.BDistristos" scope="request" id="distrito"/>
<jsp:useBean id="listaxFarmacias" scope="request"
             type="java.util.ArrayList<pe.edu.pucp.pteleshock.Beans.BFarmaciaPorDistrito>" class="java.util.ArrayList"/>
<jsp:useBean id="listaDistritos" scope="request" type="java.util.ArrayList<pe.edu.pucp.pteleshock.Beans.BDistristos>"
             class="java.util.ArrayList"/>

<html lang="en">

    <head>
        <meta charset="utf-8"/>
        <meta http-equiv="X-UA-Compatible" content="IE=edge"/>
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no"/>
        <meta name="description" content=""/>
        <meta name="author" content=""/>
        <title>Farmacias</title>
        <link href="https://cdn.jsdelivr.net/npm/simple-datatables@latest/dist/style.css" rel="stylesheet"/>
        <link href="css/style.css" rel="stylesheet"/>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.3/js/all.min.js"
                crossorigin="anonymous"></script>
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

                    <!-- al button le quité esta linea dentro de sus parámetros  id="btnNavbarSearch" -->
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
                                <a class="nav-link text-white bg-secondary"
                                   href="<%=request.getContextPath()%>/Client_Farmacias">
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
                        <div class="small">Logueado como:</div>
                        <%BUsuario cliente = (BUsuario) session.getAttribute("clienteSession");%>
                        <%=cliente.getNombre() + " " + cliente.getApellido()%>
                    </div>
                </nav>
            </div>


            <!-- Main -->
            <div id="layoutSidenav_content">

                <main>
                    <div class="container-fluid px-4">
                        <p></p>
                        <div class="card mb-4" style="opacity: 0.90;">
                            <div class="card-body">
                                <h1 class=" mt-4 ms-3">Farmacias - <%=distrito.getNombre()%>
                                </h1>

                                <form method="POST" action="<%=request.getContextPath()%>/Client_Farmacias">
                                    <div class="row mb-3 ms-3">
                                        <label class=" breadcrumb-item active">Seleccione un distrito:</label>
                                        <div class="col">
                                            <select class="form-select" aria-label="Default select example" name="idD">
                                                <%for (BDistristos dis : listaDistritos) {%>
                                                <option value="<%=dis.getIdDistrito()%>" <%=dis.getIdDistrito() == distrito.getIdDistrito() ? "selected" : ""%>><%=dis.getNombre()%>
                                                </option>
                                                <%}%>
                                            </select>
                                        </div>
                                        <div class="col">
                                            <button class="btn btn-primary" type="submit">Buscar</button>
                                        </div>
                                    </div>
                                </form>


                                <div class="row  row-cols-4">
                                    <form class="d-none d-md-inline-block form-inline ms-auto me-0 me-md-3 my-2 my-md-0"
                                          method="post"
                                          action="<%=request.getContextPath()%>/Client_Farmacias?pag=1">
                                        <div class="input-group">
                                            <input class="form-control" type="text" name="nombreFarmacia"
                                                   placeholder="Buscar..." aria-label="Buscar..."
                                                   aria-describedby="btnNavbarSearch"
                                                    <% if (nombreFarmacia != null) {%>
                                                   value="<%=nombreFarmacia%>"
                                                    <% } %>/>
                                            <input class="form-control" type="text" name="idD"
                                                   hidden aria-describedby="btnNavbarSearch"
                                                   value="<%=distrito.getIdDistrito()%>"/>
                                            <button class="btn btn-primary" id="btnNavbarSearch" type="submit"
                                                    style="background-color: #00152D;border-color: #00152D">
                                                <i class="fas fa-search"></i></button>
                                            <a class="input-group-text"
                                               href="<%=request.getContextPath()%>/Client_Farmacias?idD=<%=distrito.getIdDistrito()%>">
                                                <i class="fas fa-undo"></i>
                                            </a>
                                        </div>
                                    </form>
                                </div>
                                <br><br>
                                <%if (!nombreFarmacia.equals("") && listaxFarmacias.isEmpty()) {%>
                                <div class="text-center">
                                    <div class="alert alert-warning text-center" role="alert">
                                        <p><em>No se han encontrado resultados para "<%=nombreFarmacia%>"</em></p>
                                    </div>
                                </div>
                                <%} else {%>
                                <%if (nombreFarmacia.equals("") && listaxFarmacias.isEmpty()) {%>
                                <div class="text-center">
                                    <div class="alert alert-warning text-center" role="alert">
                                        <p><em>No se han encontrado farmacias disponibles para el distrito
                                            de <%=distrito.getNombre()%>
                                        </em></p>
                                    </div>
                                </div>
                                <%} else {%>
                                <%---
                                <nav aria-label="Page navigation">
                                    <ul class="pagination justify-content-end text-primary">
                                        <li class="page-item  <%=pag==1?"disabled":""%>  ">
                                            <a class="page-link"
                                               href="<%=request.getContextPath()%>/Client_Farmacias?idD=<%=listaxFarmacias.get(0).getIdDistritoF()%>&pag=<%=pag-1%>">Anterior</a>
                                        </li>
                                        <%for (int i = 1; i <= totalPag; i++) {%>
                                        <li class="page-item <%=pag==i?"active":""%>">
                                            <a class="page-link"
                                               href="<%=request.getContextPath()%>/Client_Farmacias?idD=<%=listaxFarmacias.get(0).getIdDistritoF()%>&pag=<%=i%>"><%=i%>
                                            </a></li>
                                        <%}%>
                                        <li class="page-item <%=pag.equals(totalPag)?"disabled":""%>  ">
                                            <a class="page-link"
                                               href="<%=request.getContextPath()%>/Client_Farmacias?idD=<%=listaxFarmacias.get(0).getIdDistritoF()%>&pag=<%=pag+1%>">Siguiente</a>
                                        </li>
                                    </ul>
                                </nav>
                                --%>

                                <!-- Farmacias -->
                                <section class="py-0">
                                    <div class="container px-4 px-lg-5 mt-1">


                                        <div class="row">

                                            <%for (BFarmaciaPorDistrito fxD : listaxFarmacias) {%>
                                            <div class="col-sm-6 p-3">
                                                <div class="card border-primary border-3 border-start-0 border-top-0">
                                                    <div class="card-body text-center">
                                                        <h5 class="card-title fw-bolder"><%=fxD.getNombreFarmacia()%>
                                                        </h5>
                                                        <p class="card-text"><%=fxD.getDireccionFarmacia()%>
                                                            <br><%=fxD.getDistritoFarmacia()%>
                                                        </p>
                                                        <a href="<%=request.getContextPath()%>/Client_Productos_F?idF=<%=fxD.getIdFarmacia()%>"
                                                           class="btn btn-primary">Ir a la farmacia</a>
                                                    </div>
                                                </div>
                                            </div>

                                            <%}%>
                                        </div>

                                    </div>
                                </section>
                                <nav aria-label="Page navigation">
                                    <ul class="pagination justify-content-end text-primary">
                                        <li class="page-item  <%=pag==1?"disabled":""%>  ">
                                            <a class="page-link"
                                               href="<%=request.getContextPath()%>/Client_Farmacias?idD=<%=listaxFarmacias.get(0).getIdDistritoF()%>&pag=<%=pag-1%>">Anterior</a>
                                        </li>
                                        <%for (int i = 1; i <= totalPag; i++) {%>
                                        <li class="page-item <%=pag==i?"active":""%>">
                                            <a class="page-link"
                                               href="<%=request.getContextPath()%>/Client_Farmacias?idD=<%=listaxFarmacias.get(0).getIdDistritoF()%>&pag=<%=i%>"><%=i%>
                                            </a></li>
                                        <%}%>

                                        <li class="page-item <%=pag.equals(totalPag)?"disabled":""%>  ">
                                            <a class="page-link"
                                               href="<%=request.getContextPath()%>/Client_Farmacias?idD=<%=listaxFarmacias.get(0).getIdDistritoF()%>&pag=<%=pag+1%>">Siguiente</a>
                                        </li>
                                    </ul>
                                </nav>
                                <%}%>
                                <%}%>
                            </div>

                        </div>


                    </div>


                </main>


                <footer class="py-4 bg-light mt-auto">
                    <div class="container-fluid px-4">
                        <div class="d-flex align-items-center justify-content-between small">
                            <div class="text-muted">Teleshock.Inc &copy; 2021</div>
                            <div>
                                <a href="#">Politica y privacidad</a>
                                &middot;
                                <a href="#">Terminos & condiciones</a>
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

        <script src="https://cdn.startbootstrap.com/sb-forms-latest.js"></script>

        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.0/dist/js/bootstrap.bundle.min.js"
                crossorigin="anonymous"></script>
        <script src="js/scripts.js"></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/Chart.js/2.8.0/Chart.min.js"
                crossorigin="anonymous"></script>
        <script src="https://cdn.jsdelivr.net/npm/simple-datatables@latest" crossorigin="anonymous"></script>
    </body>

</html>
