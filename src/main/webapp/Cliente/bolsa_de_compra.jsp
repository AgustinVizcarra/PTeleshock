<%@ page import="java.util.ArrayList" %>
<%@ page import="pe.edu.pucp.pteleshock.Beans.BPedidoEstado" %>
<%@ page import="pe.edu.pucp.pteleshock.Beans.BUsuario" %>
<%@ page import="java.util.HashMap" %>
<%@ page import="java.util.Map" %>
<%@ page import="java.time.LocalDate" %>
<%@ page import="pe.edu.pucp.pteleshock.Beans.BFarmacia" %>
<%@ page import="java.text.DecimalFormat" %>
<%@ page import="pe.edu.pucp.pteleshock.Dao.PxFarDao" %>
<%@ page import="java.time.LocalTime" %>
<%@ page import="java.time.format.DateTimeFormatter" %>
<%@ page import="java.text.SimpleDateFormat" %>
<%@ page import="java.util.Calendar" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<jsp:useBean type="java.util.ArrayList<pe.edu.pucp.pteleshock.Beans.BFarmacia>" scope="request" id="listaFarmacia"/>
<html lang="en">
    <head>
        <meta charset="utf-8"/>
        <meta http-equiv="X-UA-Compatible" content="IE=edge"/>
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no"/>
        <meta name="description" content=""/>
        <meta name="author" content=""/>
        <title>Bolsa de Compras</title>
        <link href="https://cdn.jsdelivr.net/npm/simple-datatables@latest/dist/style.css" rel="stylesheet"/>
        <link href="css/style.css" rel="stylesheet"/>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.3/js/all.min.js"
                crossorigin="anonymous"></script>
        <style>
<%--            <%for(int l=0;l<50;l++){%>--%>
<%--            .overlay {--%>
<%--                position: fixed;--%>
<%--                top: 0;--%>
<%--                bottom: 0;--%>
<%--                left: 0;--%>
<%--                right: 0;--%>
<%--                background: rgba(0, 0, 0, 0.7);--%>
<%--                transition: opacity 500ms;--%>
<%--                visibility: hidden;--%>
<%--                opacity: 0;--%>
<%--            }--%>
<%--            #popup<%=l%>:target{--%>
<%--                visibility: visible; /* Se regresa a hidden para ocultar */--%>
<%--                opacity: 1; /* Se regresa a o para hacerlo "invisible" */--%>
<%--            }--%>
<%--            #popupBody<%=l%>{--%>
<%--                width: 46%;--%>
<%--                padding: 2%;--%>
<%--                border-radius: 15px;--%>
<%--                box-shadow: 0 0 5px #CCC;--%>
<%--                background: #FFF;--%>
<%--                position: relative;--%>
<%--                transition: all 5s ease-in-out;--%>
<%--                margin: 20% auto;--%>
<%--            }--%>
<%--            #cerrar<%=l%>{--%>
<%--                position: absolute;--%>
<%--                top: 20px;--%>
<%--                right: 30px;--%>
<%--                transition: all 200ms;--%>
<%--                font-size: 30px;--%>
<%--                font-weight: bold;--%>
<%--                text-decoration: none;--%>
<%--                color: #F00;--%>
<%--            }--%>

<%--            <%}%>--%>

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


            .number-input input[type="number"] {
                -webkit-appearance: textfield;
                -moz-appearance: textfield;
            <%--appearance: textfield;--%>
            }

            .number-input input[type=number]::-webkit-inner-spin-button,
            .number-input input[type=number]::-webkit-outer-spin-button {
                -webkit-appearance: none;
            }

            .number-input {
                display: flex;
                justify-content: space-around;
                align-items: center;
            }

            .number-input button {
                -webkit-appearance: none;
                background-color: transparent;
                border: none;
                align-items: center;
                justify-content: center;
                cursor: pointer;
                margin: 0;
                position: relative;
            }

            .number-input button:before,
            .number-input button:after {
                display: inline-block;
                position: absolute;
                content: '';
                height: 2px;
                transform: translate(-50%, -50%);
            }

            .number-input button.plus:after {
                transform: translate(-50%, -50%) rotate(90deg);
            }

            .number-input input[type=number] {
                text-align: center;
            }

            .number-input.number-input {
                border: 1px solid #ced4da;
                width: 10rem;
                border-radius: .25rem;
            }

            .number-input.number-input button {
                width: 2.6rem;
                height: .7rem;
            }

            .number-input.number-input button.minus {
                padding-left: 10px;
            }

            .number-input.number-input button:before,
            .number-input.number-input button:after {
                width: .7rem;
                background-color: #495057;
            }

            .number-input.number-input input[type=number] {
                max-width: 4rem;
                padding: .5rem;
                border: 1px solid #ced4da;
                border-width: 0 1px;
                font-size: 1rem;
                height: 2rem;
                color: #495057;
            }

            @media not all and (min-resolution: .001dpcm) {
                @supports (-webkit-appearance: none) and (stroke-color:transparent) {

                    .number-input.def-number-input.safari_only button:before,
                    .number-input.def-number-input.safari_only button:after {
                        margin-top: -.3rem;
                    }
                }
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
                                <a class="nav-link text-white bg-secondary "
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
                    <div class="container-fluid px-5 mt-5">
                        <ol class="breadcrumb mb-4">
                            <li class="breadcrumb-item"><a href="<%= request.getContextPath()%>/Client_Farmacias">Farmacias</a>
                            </li>
                            <li class="breadcrumb-item active">Bolsa de compras</li>
                        </ol>

                        <div class="row px-3">
                            <div class="col-7">
                                <div class="card-body">
                                    <h5 class="card-title">Bolsa de Compras</h5>
                                    <div class="accordion" id="accordionExample">
                                        <%
                                            HashMap<Integer, ArrayList<BPedidoEstado>> map = (HashMap<Integer, ArrayList<BPedidoEstado>>) session.getAttribute("map");
                                            HashMap<Integer, Double> map2 = new HashMap<>();
                                        %>
                                        <%if (map.isEmpty()) {%>
                                        <div class="alert alert-warning"
                                             role="alert"><%=(String) session.getAttribute("msg")%>
                                        </div>
                                        <%session.removeAttribute("msg");%>
                                        <%} else {%>
                                        <%
                                            for (Map.Entry<Integer, ArrayList<BPedidoEstado>> ee : map.entrySet()) {
                                                int key = ee.getKey();
                                                ArrayList<BPedidoEstado> values = ee.getValue();
                                        %>
                                        <div class="accordion-item">
                                            <h2 class="accordion-header" id="headingOne">
                                                <div class="row">
                                                    <div class="col">
                                                        <button class="accordion-button" type="button"
                                                                data-bs-toggle="collapse" data-bs-target="#collapseOne"
                                                                aria-expanded="true" aria-controls="collapseOne">
                                                            <div class="col">
                                                                <%
                                                                    for (BFarmacia bf : listaFarmacia) {
                                                                        if (bf.getIdfarmacia() == key) {
                                                                %>
                                                                <h6 class="card-title"><%=bf.getNombre()%>-<%=bf.getNombreDistrito()%></h6>
                                                                <p><em><%=bf.getDireccion()%></em></p>
                                                                <%}%>
                                                                <%}%>
                                                            </div>
                                                        </button>
                                                    </div>
                                                </div>
                                            </h2>
                                            <div id="collapseOne" class="accordion-collapse collapse show"
                                                 aria-labelledby="headingOne" data-bs-parent="#accordionExample">
                                                <div class="accordion-body">
                                                    <table class="table align-content-center">
                                                        <thead>
                                                            <tr>
                                                                <th scope="col">Producto</th>
                                                                <th scope="col"></th>
                                                                <th scope="col">Unidad</th>
                                                                <th scope="col">Precio</th>
                                                                <th scope="col" style="text-align:center">Receta</th>
                                                                <th scope="col"></th>
                                                            </tr>
                                                        </thead>
                                                        <%double subtotal = 0.0;%>
                                                        <%DecimalFormat df = new DecimalFormat("##.##"); %>
                                                        <% for (int i = 0; i < values.size(); i++) {%>
                                                        <tbody>
                                                            <tr>
                                                                <th scope="row"><img
                                                                        src="<%= request.getContextPath()%>/ImgClienteServlet?prod=<%=ee.getValue().get(i).getIdProducto()%>&idfarm=<%=ee.getValue().get(i).getPedido().getIdFarmacia()%>"
                                                                        class="img-thumbnail"
                                                                        alt="..."
                                                                        style="width: 100px; height: 100px"></th>
                                                                <td><small><%=ee.getValue().get(i).getNombreProducto()%>
                                                                </small></td>
                                                                <td>
                                                                    <form method="POST"
                                                                          action="<%=request.getContextPath()%>/Client_Bolsa_Compra?action=actualizar&idProd=<%=ee.getValue().get(i).getIdProducto()%>&idF=<%=ee.getValue().get(i).getPedido().getIdFarmacia()%>&idP=<%=ee.getValue().get(i).getPedido().getIdPedido()%>">
                                                                        <div class="def-number-input number-input safari_only">
                                                                            <button onclick="this.parentNode.querySelector('input[type=number]').stepDown()"
                                                                                    class="minus"></button>
                                                                            <% PxFarDao pxf = new PxFarDao();
                                                                                int stock = pxf.obtenerStock(ee.getKey(), ee.getValue().get(i).getIdProducto());
                                                                                if (stock > 11) {
                                                                            %>
                                                                            <input class="quantity" min="1" max="11"
                                                                                   name="cant" readonly
                                                                                   value="<%=ee.getValue().get(i).getCantidad()%>"
                                                                                   type="number">
                                                                            <%} else {%>
                                                                            <input class="quantity" min="1"
                                                                                   max="<%=stock%>" readonly name="cant"
                                                                                   value="<%=ee.getValue().get(i).getCantidad()%>"
                                                                                   type="number">
                                                                            <%}%>
                                                                            <button onclick="this.parentNode.querySelector('input[type=number]').stepUp()"
                                                                                    class="plus"></button>
                                                                        </div>
                                                                    </form>
                                                                </td>
                                                                <td>
                                                                    <%="S/." + df.format((ee.getValue().get(i).getPrecioUnitario()) * ee.getValue().get(i).getCantidad())%>
                                                                </td>
                                                                <%
                                                                    subtotal = subtotal + (ee.getValue().get(i).getPrecioUnitario() * ee.getValue().get(i).getCantidad());
                                                                %>
                                                                <%if (ee.getValue().get(i).isRecetaMedica()) {%>
                                                                <td>
                                                                    <form method="POST"
                                                                          action="<%=request.getContextPath()%>/ImgRecetaServlet?idProd=<%=ee.getValue().get(i).getIdProducto()%>&idF=<%=ee.getValue().get(i).getPedido().getIdFarmacia()%>&idP=<%=ee.getValue().get(i).getPedido().getIdPedido()%>" enctype="multipart/form-data">
                                                                        <div class="row">

                                                                            <div class="col-6"><input accept="image/png,image/jpeg,image/jpg" style="width: 97px" class="form-control"
                                                                                                      name="fotoReceta" type="file"
                                                                                                      id="formFile1"></div>
                                                                            <div class="col-2"><button type="submit" class="btn btn-secondary btn-sm" data-bs-container="body" data-bs-toggle="popover" data-bs-placement="bottom" title="Teleshock" data-bs-content="Solo se aceptan imágenes"  data-bs-trigger="hover focus"><i class="fas fa-upload"></i></button></div>


<%--                                                                            <div class="col-1"><a href="#popup<%=i%>" class="btn btn-secondary btn-sm"><i class="fas fa-eye"></i></a></div>--%>
                                                                            <div class="col-1"><a href="#img<%=i%>"><img src="<%=request.getContextPath()%>/ImgRecetaServlet?prod=<%=ee.getValue().get(i).getIdProducto()%>&idfarm=<%=ee.getValue().get(i).getPedido().getIdFarmacia()%>&idped=<%=ee.getValue().get(i).getPedido().getIdPedido()%>" width="50px"></a></div>
                                                                            <!-- lightbox container hidden with CSS -->
                                                                            <a href="#" class="lightbox" id="img<%=i%>">
                                                                                <span style="background-image: url('<%=request.getContextPath()%>/ImgRecetaServlet?prod=<%=ee.getValue().get(i).getIdProducto()%>&idfarm=<%=ee.getValue().get(i).getPedido().getIdFarmacia()%>&idped=<%=ee.getValue().get(i).getPedido().getIdPedido()%>')"></span>
                                                                            </a>

<%--                                                                            <div id="popup<%=i%>" class="overlay">--%>
<%--                                                                                <div id="popupBody<%=i%>">--%>
<%--                                                                                    <h2>Título de la ventana</h2>--%>
<%--                                                                                    <a id="cerrar<%=i%>" href="#">&times;</a>--%>
<%--                                                                                    <div class="popupContent" style="display: flex; justify-content: center">--%>
<%--                                                                                        <img src="<%=request.getContextPath()%>/ImgRecetaServlet?prod=<%=ee.getValue().get(i).getIdProducto()%>&idfarm=<%=ee.getValue().get(i).getPedido().getIdFarmacia()%>&idped=<%=ee.getValue().get(i).getPedido().getIdPedido()%>" width="500px">--%>
<%--                                                                                    </div>--%>
<%--                                                                                </div>--%>
<%--                                                                            </div>--%>
                                                                        </div>

                                                                    </form>

                                                                </td>
                                                                <%} else {%>
                                                                <td class="text-center">No requiere</td>
                                                                <%}%>
                                                                <td class="align-content-md-center">
                                                                    <!-- Button trigger modal -->
                                                                    <a class="btn btn-danger" href="<%=request.getContextPath()%>/Client_Bolsa_Compra?action=borrar&idProd=<%=ee.getValue().get(i).getIdProducto()%>&idF=<%=ee.getValue().get(i).getPedido().getIdFarmacia()%>&idP=<%=ee.getValue().get(i).getPedido().getIdPedido()%>"><span
                                                                            class="fa fa-trash"></span></a>
                                                                </td>
                                                            </tr>
                                                        </tbody>
                                                        <%}%>
                                                        <%
                                                            map2.put(ee.getKey(), subtotal);
                                                        %>
                                                    </table>
                                                </div>
                                            </div>
                                        </div>
                                        <%}%>
                                        <%}%>
                                    </div>
                                </div>

                            </div>

                            <div class=" col-5 my-3">
                                <% String msgr= (String) session.getAttribute("msg1");
                                    System.out.println("msg"+msgr);
                                    if(msgr!=null){
                                %>
                                <div class="alert alert-warning d-flex align-items-center" role="alert">
                                    <svg class="bi flex-shrink-0 me-2" width="24" height="24" role="img" aria-label="Warning:"><use xlink:href="#exclamation-triangle-fill"/></svg>
                                    <div>
                                        <%=msgr%>
                                    </div>
                                </div>
                                <%}%>
                                <h5 class="card-title mb-4">Resumen de compra</h5>
                                <form method="POST" id="myForm"
                                      action="<%=request.getContextPath()%>/Client_Bolsa_Compra?action=comprar">
                                    <%double total = 0.0;%>
                                    <%int i = 0;%>
                                    <%
                                        for (Map.Entry<Integer, Double> ee : map2.entrySet()) {
                                            int key = ee.getKey();
                                            Double values = ee.getValue();
                                            i++;
                                    %>
                                    <div class="row">
                                        <%
                                            for (BFarmacia bf : listaFarmacia) {
                                                if (bf.getIdfarmacia() == key) {
                                        %>
                                        <div class="col"><h6><%=bf.getNombre()%>
                                        </h6></div>
                                        <%}%>
                                        <%}%>
                                        <%DecimalFormat df = new DecimalFormat("##.##"); %>
                                        <div class="col"><h6>S/.<%=df.format(ee.getValue())%>
                                        </h6></div>
                                        <%
                                            total = total + ee.getValue();
                                        %>
                                    </div>
                                    <div class="col">
                                        <div>
                                            <label class="me-2" for="party">Fecha de entrega:</label>
                                            <input id="party" type="datetime-local" name="horaEnt<%=i%>"
                                                <% LocalTime time=LocalTime.now();
                                                        DateTimeFormatter formatter2 = DateTimeFormatter.ofPattern("HH:mm");
                                                        System.out.println(time.format(formatter2));
                                                        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
                                                        SimpleDateFormat format1 = new SimpleDateFormat("HH:mm");
                                                        Calendar cal = Calendar.getInstance();
                                                        cal.add(Calendar.MONTH, +1);
                                                                                                   %>
                                                   value="<%=LocalDate.now()+"T"+time.format(formatter2)%>"
                                                   min="<%=LocalDate.now()+"T"+"09:30"%>" max="<%=format.format(cal.getTime())+"T"+format1.format(cal.getTime())%>"
                                                   pattern="[0-9]{4}-[0-9]{2}-[0-9]{2}T[0-9]{2}:[0-9]{2}" required>
                                            <span class="validity"></span>
                                        </div>
                                    </div>
                                    <%
                                        session.setAttribute("Subtotal", map2);
                                    %>
                                    <%}%>
                                    <%
                                        session.setAttribute("contador", i);
                                    %>
                                    <hr style="color: black; background-color: black; width:60%;"/>
                                    <div class="row">
                                        <div class="col"><h6>Total</h6></div>
                                        <%DecimalFormat df = new DecimalFormat("##.##"); %>
                                        <div class="col"><h6>S/.<%=df.format(total)%>
                                        </h6></div>
                                    </div>
                                    <%if (map2.isEmpty()) {%>
                                    <a class="disabled btn btn-dark btn- me-md-4" href="#">Realizar Pedido</a>
                                    <a class="btn btn-primary btn- me-md-4"
                                       href="<%=request.getContextPath()%>/Client_Farmacias">Ir a Farmacias</a>
                                    <%} else {%>
                                    <%ArrayList<BPedidoEstado> bolsita = (ArrayList<BPedidoEstado>) session.getAttribute("bolsita");%>
                                    <button type="submit" class="btn btn-warning">Realizar Pedido</button>
                                    <%if (!bolsita.isEmpty()) {%>
                                    <a class="btn btn-primary btn- me-md-4"
                                       href="<%=request.getContextPath()%>/Client_Productos_F?idF=<%=bolsita.get(bolsita.size()-1).getPedido().getIdFarmacia()%>">Seguir
                                        comprando</a>
                                    <%}%>

                                    <%}%>
                                </form>
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
                    <h5 class="card-title p-2">¡Pedido realizado!</h5>
                    <p>No podrá rehacer esta acción.</p>
                    <a href="<%= request.getContextPath()%>/Client_Bolsa_Compra" class="btn btn-warning mb-2">Ir a la
                        bolsa de compras</a>
                </div>
            </div>
        </nav>

        <nav id="popup2" class="overlay">
            <div class=" popup card text-center ">
                <h5 class="card-header text-center text-light">Teleshock</h5>
                <div class="card-body">
                    <h5 class="card-title p-2"><%=session.getAttribute("msg2")%></h5>
                    <a href="<%= request.getContextPath()%>/Client_Bolsa_Compra" class="btn btn-warning mb-2">Aceptar</a>
                </div>
            </div>
        </nav>

        <nav id="popup3" class="overlay">
            <div class=" popup card text-center ">
                <h5 class="card-header text-center text-light">Teleshock</h5>
                <div class="card-body">
                    <h5 class="card-title p-2"><%=session.getAttribute("msg3")%></h5>
                    <a href="<%= request.getContextPath()%>/Client_Bolsa_Compra" class="btn btn-warning mb-2">Aceptar</a>
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
        <%--Icon--%>
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
        <%--F--%>
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
