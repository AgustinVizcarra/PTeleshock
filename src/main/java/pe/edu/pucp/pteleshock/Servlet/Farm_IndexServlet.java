package pe.edu.pucp.pteleshock.Servlet;

import pe.edu.pucp.pteleshock.Beans.BUsuario;
import pe.edu.pucp.pteleshock.Dao.FarmaciaDao;
import pe.edu.pucp.pteleshock.Dao.PerfilFarmDao;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.HashMap;

@WebServlet(name = "Farm_IndexServlet", value = "/Farm_Index")
public class Farm_IndexServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        BUsuario farmacia = (BUsuario) session.getAttribute("farmaciaSession");

        response.setContentType("text/html");

        FarmaciaDao farmaciaDao= new FarmaciaDao();
        int idFarmacia= farmaciaDao.obtenerIdFarmacia(farmacia.getIdUsuario());


        session.setAttribute("idFarmacia",idFarmacia);


        FarmaciaDao farmdao=new FarmaciaDao();

        Date date = new Date();

        ZoneId timeZone = ZoneId.systemDefault();
        LocalDate getLocalDate = date.toInstant().atZone(timeZone).toLocalDate();

        String year = String.valueOf(getLocalDate.getYear());
        HashMap<Integer, Integer> estadisticasvent=farmdao.estadisticasventasmeses(year,idFarmacia);
        HashMap<Integer, Integer> estadisticasprod=farmdao.estadisticasproductosmeses(year,idFarmacia);


        session.setAttribute("estadisticasvent",estadisticasvent);
        session.setAttribute("estadisticasprod",estadisticasprod);

        RequestDispatcher view = request.getRequestDispatcher("/Farmacia/index_farmacia.jsp");
        view.forward(request,response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
