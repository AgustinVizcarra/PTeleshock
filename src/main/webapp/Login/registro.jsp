<%@ page import="pe.edu.pucp.pteleshock.Beans.BDistristos" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<jsp:useBean type="java.util.ArrayList<pe.edu.pucp.pteleshock.Beans.BDistristos>" scope="request" id="listaDistritos"/>

<html lang="en">
<head>
    <meta charset="utf-8"/>
    <meta http-equiv="X-UA-Compatible" content="IE=edge"/>
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no"/>
    <meta name="description" content=""/>
    <meta name="author" content=""/>
    <title>Registro</title>
    <link href="css/style.css" rel="stylesheet"/>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.3/js/all.min.js"
            crossorigin="anonymous"></script>
</head>
<body
        style="background-image: url('img/Fondo_login.jpg'); background-repeat: repeat-y; background-size: cover; opacity: 100%">
<nav class="sb-topnav navbar navbar-expand" style="background-color: #00152D; opacity: 0.9;">
    <!-- Navbar Brand-->
    <a class="navbar-brand ps-3" style="color: white" href="<%= request.getContextPath()%>/Login"><img margin-right class="icon"
                                                                             src="img/logo_final.png">eleshock</a>
</nav>


<div id="layoutAuthentication">
    <div id="layoutAuthentication_content">

        <main>
            <div class="container">
                <div class="row justify-content-center">
                    <div class="col-lg-7">
                        <div class="card shadow-lg border-0 rounded-lg mt-5">
                            <div class="card-header bg-blue" style=" background-color: #00152D; opacity: 0.9">

                                <h3 class="text-center text-white font-weight-light my-4">Registrar una
                                    cuenta</h3>
                            </div>

                            <div class="card-body">
                                <form method="post" action="<%=request.getContextPath()%>/Login_Register?action=crear">
                                    <div class="row mb-4">
                                        <label for="nombreC" class="col-sm-4 col-form-label">Nombre
                                            : </label>
                                        <div class="col-sm-8">
                                            <input type="text" class="form-control" name="nombreC" id="nombreC">
                                        </div>
                                    </div>
                                    <div class="row mb-4">
                                        <label for="apellidoC" class="col-sm-4 col-form-label">Apellido
                                            : </label>
                                        <div class="col-sm-8">
                                            <input type="text" class="form-control" name="apellidoC" id="apellidoC">
                                        </div>
                                    </div>
                                    <div class="row mb-4">
                                        <label for="correo" class="col-sm-4 col-form-label">Correo
                                            : </label>
                                        <div class="col-sm-8">
                                            <input type="email" class="form-control" name="correo" id="correo" pattern="^([\w-]+(?:\.[\w-]+)*)@((?:[\w-]+\.)*\w[\w-]{0,66})\.([a-z]{2,6}(?:\.[a-z]{2})?)$" title="Debe especificar una ruta de correo valida">
                                        </div>
                                    </div>
                                    <div class="row mb-4">
                                        <label for="dni" class="col-sm-4 col-form-label">DNI : </label>
                                        <div class="col-sm-8">
                                            <input type="text" class="form-control" name="dni" id="dni" pattern="[0-9]{8}" title="El DNI debe contener 8 dígitos.">
                                        </div>
                                    </div>
                                    <div class="row mb-4">
                                        <label for="contrasenia" class="col-sm-4 col-form-label">Contraseña
                                            : </label>
                                        <div class="col-sm-8">
                                            <input type="password" class="form-control" name="contrasenia" id="contrasenia" pattern="(?=.*\d)(?=.*[\u0021-\u002b\u003c-\u0040])(?=.*[a-z])(?=.*[A-Z]).{5,}" title="La contraseña debe contener como mínimo 5 caracteres. Incluye mayúsculas, minúsculas,
                                                números y un caracter especial."
                                            >
                                            <%--
                                            <input type="password" class="form-control is-invalid"
                                                   id="validationServer03"
                                                   aria-describedby="validationServer03Feedback" required>

                                            <div id="validationServer03Feedback" class="invalid-feedback">
                                                5 caracteres como mínimo. Incluye mayúsculas, minúsculas,
                                                números y un caracter especial.
                                            </div>
                                            ---%>
                                        </div>
                                    </div>

                                    <div class="row mb-4">
                                        <label for="contrasenia2" class="col-sm-4 col-form-label">Confirmar
                                            contraseña : </label>
                                        <div class="col-sm-8">
                                            <input type="password" class="form-control" name="contrasenia2"  id="contrasenia2" pattern="(?=.*\d)(?=.*[a-z])(?=.*[A-Z]).{8,}" title="La contraseña debe contener como mínimo 8 caracteres, 1 número y una letra mayúscula"
                                            >
                                        </div>
                                    </div>

                                    <div class="row mb-4">
                                        <label class="col-sm-4 col-form-label">Distrito : </label>
                                        <div class="col-sm-8">
                                            <select class="form-select" aria-label="Default select example" name="idDis">
                                                <% for(BDistristos dis : listaDistritos){%>
                                                <option value="<%=dis.getIdDistrito()%>"><%=dis.getNombre()%></option>
                                                <%}%>
                                            </select>
                                        </div>
                                    </div>
                                    <div class="text-danger text-center ">
                                        <%if(request.getAttribute("er")!=null){%>
                                            <%=request.getAttribute("er")%>
                                        <%}%>
                                    </div>
                                    <div class="mt-4 mb-0">
                                        <div class="mt-4 mb-0">
                                            <%--<div class="d-grid"><a class="btn btn-primary btn-block"
                                                                   href="#popup1">Registrarme</a></div>--%>
                                            <div class="d-grid">
                                                <button type="submit" class="btn btn-primary btn-block">Registrarme</button>
                                            </div>
                                        </div>
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
        <h5 class="card-header text-center text-light">Teleshock</h5>
        <div class="card-body">
            <h5 class="card-title p-2">¡Estás a un solo paso!</h5>
            <p>Se te ha enviado un correo a la dirección indicada con un link para confirmar tu
                registro.</p>
            <a href="<%= request.getContextPath()%>/Login" class="btn btn-success mb-2">Aceptar</a>

        </div>
    </div>
</nav>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.0/dist/js/bootstrap.bundle.min.js"
        crossorigin="anonymous"></script>
<script src="js/scripts.js"></script>
</body>
</html>
