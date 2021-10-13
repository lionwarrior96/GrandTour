package controlador.SERVLET;

import controlador.SQL.SQLProcessData;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import modelo.*;

@WebServlet(name = "Servlet_LoadBags", urlPatterns = {"/Servlet_LoadBags"})
public class Servlet_LoadBags extends HttpServlet {
    protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        
        HttpSession session = request.getSession();
        SQLProcessData sql = new SQLProcessData();
        
        usuario user = (usuario)session.getAttribute("user");
        
        //Load General Bag==================================================================
        List<bag_general> bgitems = sql.selectSomethingWhereMcGbag("*", user.getCodigo());
        List<String> bgcodes = new ArrayList<>();
        for (bag_general bg:bgitems) {bgcodes.add(bg.getCodpack());}
        List<paquete> packs = sql.selectMultiFromPaquete(bgcodes);
        request.setAttribute("bgitems", packs);
        //==================================================================================
        //Load Specific Bag=================================================================
        request.setAttribute("bslists", sql.selectAllWhereSbag(user.getCodigo()));
        //==================================================================================
        
        RequestDispatcher rd=request.getRequestDispatcher("mochila.jsp");
        rd.forward(request, response);
        
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
