<%@ page import="pe.edu.pucp.pteleshock.Beans.BDistristos" %>
<%@ page import="pe.edu.pucp.pteleshock.Beans.BUsuario" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<jsp:useBean id="cliente" type="pe.edu.pucp.pteleshock.Beans.BCliente" scope="request"/>
<jsp:useBean type="java.util.ArrayList<pe.edu.pucp.pteleshock.Beans.BDistristos>" scope="request" id="listaDistritos"/>
<jsp:useBean id="valor" scope="request" type="java.lang.String" class="java.lang.String"/>

<html lang="en">
    <head>


        <meta charset="utf-8"/>
        <meta http-equiv="X-UA-Compatible" content="IE=edge"/>
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no"/>
        <title>Password Reset - SB Admin</title>
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
                                <a class="nav-link text-white  "
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
                                    <div style="background-color:#00152D; opacity: 0.9; text-align:center;padding-left: 10px">
                                        <br>
                                        <img
                                                src="img/logo_nombre_blanco.png"
                                                width="200px"
                                                alt="Sample photo"
                                                class="img-fluid"
                                                style="border-top-left-radius: .30rem; border-bottom-left-radius: .25rem"
                                        />
                                    </div>
                                    <div class="card-header text-white" style="background-color:#00152D; opacity: 0.9">
                                        <h3 class="text-center font-weight-light my-4">Mi perfil</h3></div>
                                    <div class="card-body" style="background-color:#00152D; opacity: 0.9">
                                        <form method="post"
                                              action="<%=request.getContextPath()%>/Client_Perfil?action=actualizar">
                                            <div class="row mb-3">
                                                <div class="col-sm-8">
                                                    <input class="form-control" type="hidden" name="idC"
                                                           value="<%=cliente.getIdCliente()%>">
                                                </div>
                                            </div>
                                            <div class="row mb-3">
                                                <label class="col-sm-3 col-form-label text-white">Nombre : </label>
                                                <div class="col-sm-8">
                                                    <input class="form-control" type="text" name="nombreC" required id="nombreC" pattern="([a-zA-Z]).{0,254}" title="Ingrese datos correctos."
                                                           value="<%=cliente.getNombre()%>">
                                                </div>
                                            </div>
                                            <div class="row mb-3">
                                                <label class="col-sm-3 col-form-label text-white">Apellido : </label>
                                                <div class="col-sm-8">
                                                    <input class="form-control" type="text" name="apellidoC" required pattern="([a-zA-Z]).{0,254}" title="Ingrese datos correctos."
                                                           id="apellidoC" value="<%=cliente.getApellido()%>">

                                                </div>
                                            </div>

                                            <div class="row mb-3">
                                                <label class="col-sm-3 col-form-label text-white">DNI : </label>
                                                <div class="col-sm-8">
                                                    <input class="form-control" id="inputDni" type="dni"
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
                                                    <select class="form-select" aria-label="Default select example"
                                                            name="idDis">
                                                        <% for (BDistristos dis : listaDistritos) {%>
                                                        <option value="<%=dis.getIdDistrito()%>"<%=dis.getIdDistrito() == cliente.getDistrito().getIdDistrito() ? "selected" : ""%>><%=dis.getNombre()%>
                                                        </option>
                                                        <%}%>
                                                    </select>
                                                </div>
                                            </div>
                                            <div class="text-danger text-center ">
                                                <%if (request.getAttribute("er") != null) {%>
                                                <%=request.getAttribute("er")%>
                                                <%}%>
                                            </div>
                                            <div class="d-md-block mt-5 mb-4 text-center font-weight-light my-5">
                                                <button type="submit" class="btn btn-primary" href="popup2">Guardar
                                                </button>
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

        <% if (valor.equals("edit")) {%>
        <nav id="popup2" style="display: flex; justify-content: center ; align-items: center;position: fixed;
top: 0;
bottom: 0;
left: 0;
right: 0;
background: rgba(0, 0, 0, 0.7);
transition: opacity 500ms;
visibility: visible;
opacity: 1;">
            <div class=" popup card text-center " style="background-color: white">
                <h5 class="card-header text-center ">Teleshock</h5>
                <div class="card-body">
                    <h5 class="card-title p-2">¡Se han guardado con éxito sus datos!</h5>
                    <img class="img mt-3 mb-3" style="width: 50px; height: 50px;"
                         src="https://cdn-icons-png.flaticon.com/512/179/179372.png">
                    <div class="mt-3">
                        <a href="<%= request.getContextPath()%>/Client_Perfil" class="btn btn-success mb-2">Aceptar</a>
                    </div>
                </div>
            </div>
        </nav>
        <%}%>


        <%---
        <nav id="popup1" class="overlay">
            <div class=" popup card text-center ">
                <h5 class="card-header text-center text-light">Teleshock</h5>
                <div class="card-body">
                    <h5 class="card-title p-2">¡Se han guardado con éxito sus datos!</h5>
                    <img class="img mt-3 mb-3" style="width: 50px; height: 50px;"
                         src="https://cdn-icons-png.flaticon.com/512/179/179372.png">
                    <div class="mt-3">
                        <a href="<%= request.getContextPath()%>/Client_Perfil" class="btn btn-success mb-2">Aceptar</a>
                    </div>
                </div>
            </div>
        </nav>
        --%>
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
    </body>
</html>
