/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pkg487.webclient.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import pkg487.loan.core.User;
import pkg487.loan.system.UserLoginException;
import pkg487.webclient.SOAPClient;

/**
 *
 * @author Xavier Vani-Charron
 */
@WebServlet(name = "LoginServlet", urlPatterns = {"/LoginServlet"})
public class LoginServlet extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        HttpSession session = request.getSession(false);
        String username = "";
        String password = "";
        SOAPClient client = null;
        User user = null;
        RequestDispatcher rd;
        
        if(session == null){
            
            username = request.getParameter("username");
            password = request.getParameter("password");
            client = new SOAPClient();
            
            try{
                user = client.authUser(username, password);
            }catch(UserLoginException e){
                
            }
            
            if(user != null){
                
                session = request.getSession(true);
                session.setAttribute("username", user.getLogin());
                session.setAttribute("password", user.getPass());
                session.setAttribute("authLevel", user.getAuthLevel());
                
                if(user.getAuthLevel() == 0){
                     response.sendRedirect("/487-Webclient/admin/home.jsp");
                }else if(user.getAuthLevel() == 1){
                     response.sendRedirect("/487-Webclient/user/home.jsp");
                }
            }
            
        }else if(null == session.getAttribute("authLevel")){
            
            session.invalidate();
            
            username = request.getParameter("username");
            password = request.getParameter("password");
            client = new SOAPClient();
            
            try{
                user = client.authUser(username, password);
            }catch(UserLoginException e){
                
            }
            
            if(user != null){
                
                session = request.getSession(true);
                session.setAttribute("username", user.getLogin());
                session.setAttribute("password", user.getPass());
                session.setAttribute("authLevel", user.getAuthLevel());
                
                if(user.getAuthLevel() == 0){
                    response.sendRedirect("/487-Webclient/admin/home.jsp");
                    
                }else if(user.getAuthLevel() == 1){
                    response.sendRedirect("/487-Webclient/user/home.jsp");
                }
            }
        }else{
            if(session.getAttribute("authLevel").toString().equals("0")){
                response.sendRedirect("/487-Webclient/admin/home.jsp");           
            }else if(session.getAttribute("authLevel").toString().equals("1")){
                response.sendRedirect("/487-Webclient/user/home.jsp");
            }
        }
        
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
