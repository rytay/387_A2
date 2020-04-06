/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pkg487.webclient.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import pkg487.library.core.Book;
import pkg487.webclient.RESTClient;

/**
 *
 * @author Xavier Vani-Charron
 */
@WebServlet(name = "ManageBooks", urlPatterns = {"/ManageBooks"})
public class ManageBooks extends HttpServlet {

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
        RESTClient client = new RESTClient();
        
        if(null != session.getAttribute("authLevel")){
            if(null != request.getParameter("save")){
                //TODO Update
            }else if(null != request.getParameter("delete")){
                client.deleteBook(request.getParameter("id"));
                response.sendRedirect(request.getContextPath()+"/admin/managebooks/managebooks.jsp");
            }else if(null != request.getParameter("create")){
                
                Book book = new Book();
                book.setAuthor(request.getParameter("author"));
                book.setBookDesc(request.getParameter("desc"));
                book.setIsbn(request.getParameter("isbn"));
                book.setPublisher(request.getParameter("pub"));
                book.setTitle(request.getParameter("title"));
                
                client.createBook(book, "application/json");
                response.sendRedirect(request.getContextPath()+"/admin/managebooks/managebooks.jsp");
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
