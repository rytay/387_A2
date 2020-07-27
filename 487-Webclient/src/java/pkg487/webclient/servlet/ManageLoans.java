/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pkg487.webclient.servlet;

import java.io.IOException;
import java.util.Arrays;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import pkg487.loan.core.Loan;
import pkg487.loan.system.LoanUnvailableException;
import pkg487.webclient.SOAPClient;

/**
 *
 * @author Xavier Vani-Charron
 */
@WebServlet(name = "ManageLoans", urlPatterns = {"/ManageLoans"})
public class ManageLoans extends HttpServlet {

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
        
        Loan loan = null;
        SOAPClient client = new SOAPClient();
        Map values = null;
        int loanId = -1;
        
        if(null != session.getAttribute("authLevel")){
            if(session.getAttribute("authLevel").equals(0)){
                
                if(null != request.getParameter("save")){
                    //TODO Update Code
                }else if(null != request.getParameter("delete")){
                    //Delete
                    try{
                        loanId = Integer.parseInt(request.getParameter("id"));
                    }catch(NumberFormatException e){
                    }
                    
                    if(loanId != -1){
                        try{
                            client.deleteLoan(loanId);
                        }catch(LoanUnvailableException e){

                        }
                        
                        response.sendRedirect(request.getContextPath()+ "/admin/manageloans/manageloans.jsp");
                    }
                }
                    
//                values = request.getParameterMap();
//                
//                for(Object key : values.entrySet()){
//                    String keyString = (String) key;
//                    String[] value = (String[]) values.get(keyString);
//                    System.out.println(keyString + Arrays.toString(value));
//                }
               
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
