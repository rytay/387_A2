/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pkg487.loan.service;

import java.util.List;
import javax.jws.WebService;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.soap.SOAPBinding;
import javax.jws.soap.SOAPBinding.Style;
import pkg487.loan.core.Loan;
import pkg487.loan.system.LoanManager;

/**
 *
 * @author Xavier Vani-Charron
 */
@WebService(serviceName = "LoanService")
@SOAPBinding(style = Style.RPC)
public class LoanService {

    /**
     * This is a sample web service operation
     * @param txt
     * @return String
     */
    @WebMethod(operationName = "hello")
    public String hello(@WebParam(name = "name") String txt) {
        return "Hello " + txt + " !";
    }
    
    @WebMethod(operationName = "listLoans")
    public Loan[] listLoans(){
        
        LoanManager manager = new LoanManager();
        List<Loan> loanList = manager.read(null);
        Loan[] loanArray = new Loan[loanList.size()];
        loanList.toArray(loanArray);
        
        return loanArray;
    }
    
    @WebMethod(operationName = "listLoansByBookId")
    public Loan[] listLoansByBook(@WebParam(name = "bookId") String bookId){
        
        LoanManager manager = new LoanManager();
        
        int bookIdInt = -1;
        
        try{
            bookIdInt = Integer.parseInt(bookId);
        }catch(Exception e){
            e.printStackTrace();
        }
        
        return manager.loansByBookId(bookIdInt);
    }
}
