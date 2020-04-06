/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pkg487.webclient.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import pkg487.webclient.SOAPClient;

/**
 *
 * @author Xavier Vani-Charron
 */
@WebServlet(name = "BorrowBook", urlPatterns = {"/BorrowBook"})
public class BorrowBook extends HttpServlet {

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
        SOAPClient client = new SOAPClient();
        
        if(null != session.getAttribute("authLevel")){
            if(null != request.getParameter("borrow")){
                               
                Integer userId = -1;
                int bookId = -1;
                
                try{
                    userId = (Integer) session.getAttribute("userId");
                    bookId = Integer.parseInt(request.getParameter("id"));
                    
                    System.out.println(userId);
                    System.out.println(bookId);

                }catch(Exception e){
                    
                }
                
                try{
                    client.borrowBook(userId, bookId);
                    System.out.println("Borrowed book.");
                }catch(Exception e){
                    System.out.println("Error");
                }
                
                response.sendRedirect(request.getContextPath()+"/user/library.jsp");
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
