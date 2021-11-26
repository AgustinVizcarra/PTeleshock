<%--
  Created by IntelliJ IDEA.
  User: casa
  Date: 5/11/2021
  Time: 18:44
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

                                <form>
                                    <div class="row mb-4">
                                        <label for="inputEmail" class="col-sm-4 col-form-label">Nombres
                                            : </label>
                                        <div class="col-sm-8">
                                            <input type="name" class="form-control" id="inputName">
                                        </div>
                                    </div>
                                    <div class="row mb-4">
                                        <label for="inputEmail" class="col-sm-4 col-form-label">Apellidos
                                            : </label>
                                        <div class="col-sm-8">
                                            <input type="lastname" class="form-control" id="inputLastName">
                                        </div>
                                    </div>
                                    <div class="row mb-4">
                                        <label for="inputEmail" class="col-sm-4 col-form-label">Correo
                                            : </label>
                                        <div class="col-sm-8">
                                            <input type="email" class="form-control" id="inputEmail">
                                        </div>
                                    </div>
                                    <div class="row mb-4">
                                        <label for="inputEmail" class="col-sm-4 col-form-label">DNI : </label>
                                        <div class="col-sm-8">
                                            <input type="dni" class="form-control" id="inputDNI">
                                        </div>
                                    </div>
                                    <div class="row mb-4">
                                        <label for="validationServer03" class="col-sm-4 col-form-label">Contraseña
                                            : </label>
                                        <div class="col-sm-8">
                                            <input type="password" class="form-control is-invalid"
                                                   id="validationServer03"
                                                   aria-describedby="validationServer03Feedback" required>
                                            <div id="validationServer03Feedback" class="invalid-feedback">
                                                5 caracteres como mínimo. Incluye mayúsculas, minúsculas,
                                                números y un caracter especial.
                                            </div>
                                        </div>
                                    </div>

                                    <div class="row mb-4">
                                        <label for="inputPassword2" class="col-sm-4 col-form-label">Confirmar
                                            contraseña : </label>
                                        <div class="col-sm-8">
                                            <input type="password" class="form-control" id="inputPassword2">
                                        </div>
                                    </div>

                                    <div class="row mb-4">
                                        <label class="col-sm-4 col-form-label">Distrito : </label>
                                        <div class="col-sm-8">
                                            <select class="form-select" aria-label="Default select example">
                                                <option selected>Seleccione el distrito</option>
                                                <option value="1">La Molina</option>
                                                <option value="2">Callao</option>
                                                <option value="3">San Isidro</option>
                                                <option value="4">Callao</option>
                                                <option value="5">Chaclacayo</option>
                                                <option value="6">Callao</option>
                                                <option value="7">San Miguel</option>
                                                <option value="8">SJL</option>
                                                <option value="9">SJM</option>
                                                <option value="10">Surco</option>
                                                <option value="11">Breña</option>
                                                <option value="12">La Victoria</option>
                                            </select>
                                        </div>


                                    </div>
                                    <div class="mt-4 mb-0">
                                        <div class="mt-4 mb-0">
                                            <div class="d-grid"><a class="btn btn-primary btn-block"
                                                                   href="#popup1">Registrarme</a></div>
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
