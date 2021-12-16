<%@ page import="pe.edu.pucp.pteleshock.Beans.BUsuario" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<jsp:useBean id="detProd" type="pe.edu.pucp.pteleshock.Beans.BDetProd" scope="request"/>
<jsp:useBean id="farmacia" type="pe.edu.pucp.pteleshock.Beans.BFarmaciaPorDistrito" scope="request"/>


<html lang="en">

    <head>
        <meta charset="utf-8"/>
        <meta http-equiv="X-UA-Compatible" content="IE=edge"/>
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no"/>
        <meta name="description" content=""/>
        <meta name="author" content=""/>
        <title>Producto</title>
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

                                <!-- Product -->
                                <section class="py-5">
                                    <div class="container px-4 px-lg-5 my-5">
                                        <div class="row gx-4 gx-lg-5 align-items-center">
                                            <div class="col-md-6"><img class="card-img-top mb-5 mb-md-0"
                                                                       src="<%= request.getContextPath()%>/ImgServlet?prod=<%=detProd.getIdProducto()%>"
                                                                       alt="..."/>
                                            </div>
                                            <div class="col-md-6">
                                                <%--<div class="small mb-1">SKU: BST-498</div>--%>
                                                <h1 class="display-5 fw-bolder"><%=detProd.getNombre()%>
                                                </h1>
                                                <div class="fs-5 mb-5">
                                                    <span>S/.<%=detProd.getPreciounitario()%></span>
                                                </div>
                                                <h5>Descripción:</h5>
                                                <p class="lead"><%=detProd.getDescripcion()%>
                                                </p>
                                                <div class="d-flex">
                                                    <!-- Product actions-->
                                                    <div class="card-footer p-2 pt-0 border-top-0 bg-transparent text-center">
                                                        <form method="POST"
                                                              action="<%=request.getContextPath()%>/Client_Bolsa_Compra?action=agregar">
                                                            <input type="hidden" class="form-control" name="idProd"
                                                                   value="<%=detProd.getIdProducto()%>">
                                                            <input type="hidden" class="form-control" name="cantidad"
                                                                   value="1">
                                                            <input type="hidden" class="form-control" name="idFarmacia"
                                                                   value="<%=farmacia.getIdFarmacia()%>">
                                                            <input type="hidden" class="form-control" name="receta"
                                                                   value="<%=detProd.isRecetaMedicaB()?"1":"0"%>">
                                                            <button class="btn btn-outline-dark mt-auto" href="#popup1"
                                                                    type="submit">Agregar
                                                            </button>
                                                        </form>
                                                    </div>
                                                    <div class="card-footer p-2 pt-0 border-top-0 bg-transparent">
                                                        <div class="text-center"><a class="btn btn-outline-dark "
                                                                                    href="<%= request.getContextPath()%>/Client_Productos_F?idF=<%=farmacia.getIdFarmacia()%>">Seguir
                                                            comprando</a></div>
                                                    </div>


                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                </section>


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
                <h5 class="card-header text-center text-light">Teleshock</h5>
                <div class="card-body">
                    <h5 class="card-title p-2">¡Producto Añadido!</h5>
                    <a href="#" class="btn btn-success mb-2">Seguir Comprando</a>
                    <a href="#" class="btn btn-warning mb-2">Ir a la bolsa de compras</a>

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

        <script src="https://cdn.startbootstrap.com/sb-forms-latest.js"></script>

        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.0/dist/js/bootstrap.bundle.min.js"
                crossorigin="anonymous"></script>
        <script src="js/scripts.js"></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/Chart.js/2.8.0/Chart.min.js"
                crossorigin="anonymous"></script>

        <script src="https://cdn.jsdelivr.net/npm/simple-datatables@latest" crossorigin="anonymous"></script>
    </body>

</html>
