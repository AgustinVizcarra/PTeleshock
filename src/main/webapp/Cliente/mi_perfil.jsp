<%@ page import="pe.edu.pucp.pteleshock.Beans.BUsuario" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%-- Exportamos el cliente --%>
<jsp:useBean type="pe.edu.pucp.pteleshock.Beans.BCliente" scope="request" id="cliente"/>

<html lang="en">
    <head>
        <meta charset="utf-8"/>
        <meta http-equiv="X-UA-Compatible" content="IE=edge"/>
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no"/>
        <title>Mi Perfil</title>
        <link href="https://cdn.jsdelivr.net/npm/simple-datatables@latest/dist/style.css" rel="stylesheet"/>
        <link href="css/style.css" rel="stylesheet"/>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.3/js/all.min.js"
                crossorigin="anonymous"></script>
    </head>
    <body class="sb-nav-fixed"
          style="background-image: url('img/Fondo_login.jpg'); background-repeat:no-repeat; background-size: cover">
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
                                <a class="nav-link text-white bg-secondary" href="<%=request.getContextPath()%>/Client_Perfil">
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
                        <%=cliente.getNombre() + " " + cliente.getApellido()%>
                    </div>
                </nav>
            </div>
        </div>

        <!-- Main -->
        <div id="layoutAuthentication">
            <div id="layoutAuthentication_content">
                <main>
                    <div class="container">
                        <div class="row justify-content-center">
                            <div class="col-lg-5">
                                <br><br><br><br><br>
                                <div class="card shadow-lg border-0 rounded-lg">
                                    <div style="background-color:#00152D; opacity: 0.9;text-align:center;padding-left: 10px">
                                        <br>
                                        <img
                                                src="img/logo_nombre_blanco.png"
                                                width="200px"
                                                alt="Sample photo"
                                                class="img-fluid"
                                                style="border-top-left-radius: .30rem; border-bottom-left-radius: .25rem;"
                                        />
                                    </div>
                                    <div class="card-header text-white"
                                         style="background-color:#00152D; opacity: 0.9">
                                        <h3 class="text-center font-weight-light my-4">Mi perfil</h3></div>
                                    <div class="card-body" style="background-color:#00152D; opacity: 0.9">
                                        <form>
                                            <div class="row mb-3">
                                                <label class="col-sm-3 col-form-label text-white">Nombre : </label>
                                                <div class="col-sm-8">
                                                    <input class="form-control" id="inputNombre" type="text"
                                                           placeholder="<%=cliente.getNombre()%>" readonly="readonly"/>
                                                </div>
                                            </div>
                                            <div class="row mb-3">
                                                <label class="col-sm-3 col-form-label text-white">Apellido
                                                    : </label>
                                                <div class="col-sm-8">
                                                    <input class="form-control" id="inputApellido" type="text"
                                                           placeholder="<%=cliente.getApellido()%>"
                                                           readonly="readonly"/>
                                                </div>
                                            </div>
                                            <div class="row mb-3">
                                                <label class="col-sm-3 col-form-label text-white">DNI : </label>
                                                <div class="col-sm-8">
                                                    <input class="form-control" id="inputDni" type="text"
                                                           placeholder="<%=cliente.getDni()%>" readonly="readonly"/>
                                                </div>
                                            </div>
                                            <div class="row mb-3">
                                                <label class="col-sm-3 col-form-label text-white">Correo : </label>
                                                <div class="col-sm-8">
                                                    <input class="form-control" id="inputEmail" type="email"
                                                           placeholder="<%=cliente.getCorreo()%>" readonly="readonly"/>
                                                </div>
                                            </div>
                                            <div class="row mb-3">
                                                <label class="col-sm-3 col-form-label text-white">Distrito: </label>
                                                <div class="col-sm-8">
                                                    <input class="form-control" id="inputDistrito" type="text"
                                                           placeholder="<%=cliente.getDistrito().getNombre()%>"
                                                           readonly="readonly"/>
                                                </div>
                                            </div>
                                            <div class="d-md-block mt-5 mb-4 text-center font-weight-light my-5">
                                                <a class="btn btn-primary me-md-5"
                                                   href="<%= request.getContextPath()%>/Client_Perfil?action=editar&idC=<%=cliente.getIdCliente()%>">Editar
                                                    perfil</a>
                                                <a class="btn btn-danger " href="#popup1">Eliminar
                                                    cuenta</a>
                                            </div>
                                        </form>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </main>

            </div>
        </div>

        <nav id="popup1" class="overlay">
            <div class=" popup card text-center ">
                <h5 class="card-header text-center">Teleshock</h5>
                <div class="card-body">
                    <h5 class="card-title p-2">¿Desea eliminar su cuenta?</h5>
                    <a href="<%= request.getContextPath()%>/Client_Perfil?action=elimLogico&idC=<%=cliente.getIdCliente()%>" class="btn btn-danger mb-2">Aceptar</a>
                    <a href="#" class="btn btn-primary mb-2">Cancelar</a>
                </div>
            </div>
        </nav>

        <nav id="popup2" class="overlay">
            <div class=" popup card text-center ">
                <h5 class="card-header text-center">Teleshock</h5>
                <div class="card-body">
                    <h5 class="card-title p-2">¡Su cuenta ha sido eliminada!</h5>
                    <img class="img" style="width: 50px; height: 50px;"
                         src="https://cdn-icons-png.flaticon.com/512/142/142311.png">
                    <h5 class="card-title p-2">Esperamos volver a verlo pronto</h5>
                    <form method="post" action="<%=request.getContextPath()%>/Login?action=logout">
                        <button class="btn btn-danger mb-2" type="submit">
                            Aceptar
                        </button>
                    </form>
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

        <nav id="popup3" class="overlay">
            <div class=" popup card text-center ">
                <h5 class="card-header text-center">Teleshock</h5>
                <div class="card-body">
                    <img class="img" style="width: 50px; height: 50px;"
                         src="https://cdn-icons-png.flaticon.com/512/179/179386.png">
                    <br>
                    <h5 class="card-title p-2">Su cuenta no puede ser eliminada, ya que cuenta con pedidos pendientes.</h5>
                    <a href="<%= request.getContextPath()%>/Client_Perfil" class="btn btn-danger mb-2">Aceptar</a>
                </div>
            </div>
        </nav>

        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.0/dist/js/bootstrap.bundle.min.js"
                crossorigin="anonymous"></script>
        <script src="js/scripts.js"></script>
        <script src="https://cdn.jsdelivr.net/npm/simple-datatables@latest" crossorigin="anonymous"></script>
    </body>
</html>