package controlador.SERVLET;

import controlador.SQL.SQLProcessData;
import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import modelo.bag_general;
import modelo.paquete;
import modelo.usuario;
@WebServlet(name = "Servlet_Details", urlPatterns = {"/Servlet_Details"})
public class Servlet_Details extends HttpServlet { 
    protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        
        HttpSession session = request.getSession();
        SQLProcessData sql = new SQLProcessData();
        
        usuario user = (usuario)session.getAttribute("user");
        String packcode = request.getParameter("pck_code");
        String packimg = request.getParameter("pck_img");
        String packname = request.getParameter("pck_name");
        String packdesc = request.getParameter("pck_desc");
        double packcost = Double.parseDouble(request.getParameter("pck_cost"));
        String packlink = request.getParameter("pck_link");
        paquete p = new paquete(packcode, packimg, packname, packdesc, packcost, packlink);
        request.setAttribute("pack", p);
        
        
        request.setAttribute("coments", sql.selectWhereFromMSComentario("*", "codanswer","=", 0, p.getCodigo()));
        request.setAttribute("answers", sql.selectWhereFromMSComentario("*", "codanswer","!=", 0, p.getCodigo()));
        request.setAttribute("votes", sql.selectAllFromVoto());
        if (user != null) {
            request.setAttribute("bgeneralcheck", sql.selectSomethingWhereBcGbag("*", new bag_general(user.getCodigo(), p.getCodigo())));
        }
        
        
        RequestDispatcher rd=request.getRequestDispatcher("detalle_paquete.jsp");
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
