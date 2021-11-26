<div class="sb-sidenav-menu " >
    <ul class="nav" style="margin-top: 30px">
        <li class="nav-item ">
            <a class="nav-link"  style="color: white <%=request.getParameter("page").equals("perfil")? "active": "" %>" href="<%= request.getContextPath()%>/Farm_Pefil">
                <div class="sb-nav-link-icon"><i class="fas fa-user"></i></div>
                Mi perfil
            </a>
        </li>
        <li class="nav-item">
            <a class="nav-link"  style="color: white <%=request.getParameter("page").equals("registro")? "active": "" %>" href="<%= request.getContextPath()%>/Farm_Registro_Producto">
                <div class="sb-nav-link-icon"><i class="fas fa-clipboard-list"></i></div>
                Registrar Producto
            </a>
        </li>
        <li class="nav-item">
            <a class="nav-link"  style="color: white <%=request.getParameter("page").equals("vista")? "active": "" %>" href="<%= request.getContextPath()%>/Farm_Vista_ProductosServlet?pag=1">
                <div class="sb-nav-link-icon"><i class="far fa-eye"></i></div>
                Visualizar Producto
            </a>
        </li>
        <li class="nav-item">
            <a class="nav-link"  style="color: white <%=request.getParameter("page").equals("pedidos")? "active": "" %>" href="<%= request.getContextPath()%>/Farm_Gestionar_Pedidos">
                <div class="sb-nav-link-icon"><i class="fas fa-people-arrows"></i></div>
                Gestionar Pedidos
            </a>
        </li>
    </ul>
</div>