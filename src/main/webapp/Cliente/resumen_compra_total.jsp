<%@ page import="pe.edu.pucp.pteleshock.Beans.BUsuario" %><%--
  Created by IntelliJ IDEA.
  User: casa
  Date: 5/11/2021
  Time: 18:48
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
        <title>Bolsa de Compras</title>
        <link href="https://cdn.jsdelivr.net/npm/simple-datatables@latest/dist/style.css" rel="stylesheet"/>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.3/js/all.min.js"
                crossorigin="anonymous"></script>
        <link href="css/style.css" rel="stylesheet"/>
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
                        <ol class="breadcrumb mb-4">
                            <li class="breadcrumb-item"><a href="<%= request.getContextPath()%>/Client_Farmacias">Farmacias</a>
                            </li>
                            <li class="breadcrumb-item active">Bolsa de compras</li>
                        </ol>

                        <div class="row align-content-center">
                            <div class="col-md-10 col-lg-10">
                                <div class="card">
                                    <div class="card-body">
                                        <h5 class="card-title">Resumen de compra total</h5>
                                        <h6>Farmacia A</h6>
                                        <table class="table">
                                            <thead>
                                                <tr>
                                                    <th scope="col"></th>
                                                    <th scope="col"></th>
                                                </tr>
                                            </thead>
                                            <tbody>
                                                <tr>
                                                    <th scope="row">Fecha de recojo:</th>
                                                    <td>
                                                        <div class="card border-warning">
                                                            <div class="card-header text-center justify-content-center">
                                                                24/11/2020 <img class="icon"
                                                                                style="width: 25px; height: 25px"
                                                                                src="https://cdn-icons-png.flaticon.com/512/2370/2370264.png">
                                                            </div>
                                                        </div>
                                                    </td>
                                                </tr>
                                                <tr>
                                                    <th scope="row">Hora de recojo:</th>
                                                    <td>
                                                        <div class="card border-warning mb-3">
                                                            <div class="card-header text-center justify-content-center">
                                                                05:10:20pm <img class="icon"
                                                                                style="width: 25px; height: 25px"
                                                                                src="https://cdn-icons-png.flaticon.com/512/709/709511.png">
                                                            </div>
                                                        </div>
                                                    </td>
                                                </tr>
                                                <tr>
                                                    <th scope="row">Subtotal:</th>
                                                    <td style="text-align: center">$ 31.00</td>
                                                </tr>
                                            </tbody>
                                        </table>
                                        <h6>Farmacia B</h6>
                                        <table class="table">
                                            <thead>
                                                <tr>
                                                    <th scope="col"></th>
                                                    <th scope="col"></th>
                                                </tr>
                                            </thead>
                                            <tbody>
                                                <tr>
                                                    <th scope="row">Fecha de recojo:</th>
                                                    <td>
                                                        <div class="card border-warning ">
                                                            <div class="card-header text-center justify-content-center">
                                                                24/11/2020 <img class="icon"
                                                                                style="width: 25px; height: 25px"
                                                                                src="https://cdn-icons-png.flaticon.com/512/2370/2370264.png">
                                                            </div>
                                                        </div>
                                                    </td>
                                                </tr>
                                                <tr>
                                                    <th scope="row">Hora de recojo:</th>
                                                    <td>
                                                        <div class="card border-warning mb-3">
                                                            <div class="card-header text-center justify-content-center">
                                                                05:10:20pm <img class="icon"
                                                                                style="width: 25px; height: 25px"
                                                                                src="https://cdn-icons-png.flaticon.com/512/709/709511.png">
                                                            </div>
                                                        </div>
                                                    </td>
                                                </tr>
                                                <tr>
                                                    <th scope="row">Subtotal:</th>
                                                    <td style="text-align: center">$ 31.00</td>
                                                </tr>
                                            </tbody>
                                        </table>
                                        <h6>Farmacia C</h6>
                                        <table class="table">
                                            <thead>
                                                <tr>
                                                    <th scope="col"></th>
                                                    <th scope="col"></th>
                                                </tr>
                                            </thead>
                                            <tbody>
                                                <tr>
                                                    <th scope="row">Fecha de recojo:</th>
                                                    <td>
                                                        <div class="card border-warning align-content-center">
                                                            <div class="card-header text-center justify-content-center">
                                                                24/11/2020 <img class="icon"
                                                                                style="width: 25px; height: 25px"
                                                                                src="https://cdn-icons-png.flaticon.com/512/2370/2370264.png">
                                                            </div>
                                                        </div>
                                                    </td>
                                                </tr>
                                                <tr>
                                                    <th scope="row">Hora de recojo:</th>
                                                    <td>
                                                        <div class="card border-warning mb-3">
                                                            <div class="card-header text-center justify-content-centerr">
                                                                05:10:20pm <img class="icon"
                                                                                style="width: 25px; height: 25px"
                                                                                src="https://cdn-icons-png.flaticon.com/512/709/709511.png">
                                                            </div>
                                                        </div>
                                                    </td>
                                                </tr>
                                                <tr>
                                                    <th scope="row">Subtotal:</th>
                                                    <td style="text-align: center">$ 31.00</td>
                                                </tr>
                                            </tbody>
                                        </table>
                                        <h6></h6>
                                        <table class="table">
                                            <thead>
                                                <tr>
                                                    <th scope="col"></th>
                                                    <th scope="col"></th>
                                                </tr>
                                            </thead>
                                            <tbody>
                                                <tr>
                                                    <th scope="row">Monto Total:</th>
                                                    <td style="text-align: center">$ 93.00</td>
                                                </tr>
                                            </tbody>
                                        </table>
                                    </div>
                                </div>
                                <br>
                                <div class="d-grid gap-2 col-6 mx-auto">
                                    <button class="btn btn-warning" type="button"
                                            style="color: white;background-color: yellow;border-color: yellow; lighting-color: yellow;">
                                        Comprar
                                    </button>

                                </div>
                                <br>
                                <nav aria-label="Page navigation example">
                                    <ul class="pagination justify-content-center">
                                        <li class="page-item"><a class="page-link"
                                                                 href="<%= request.getContextPath()%>/Client_Bolsa_Compra">Previous</a>
                                        </li>
                                        <li class="page-item"><a class="page-link"
                                                                 href="<%= request.getContextPath()%>/Client_Bolsa_Compra">Paso
                                            1</a></li>
                                        <li class="page-item"><a class="page-link"
                                                                 href="<%= request.getContextPath()%>/Client_Resumen_Compratotal">Paso
                                            2</a></li>
                                    </ul>
                                </nav>
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
