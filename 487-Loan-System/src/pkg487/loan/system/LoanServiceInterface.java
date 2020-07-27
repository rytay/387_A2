/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pkg487.loan.system;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;
import javax.jws.soap.SOAPBinding.Style;
import pkg487.loan.core.Loan;
import pkg487.loan.core.User;
import pkg487.loan.system.LoanUnvailableException;
import pkg487.loan.system.UserUnvailableException;

/**
 *
 * @author Xavier Vani-Charron
 */
@WebService
@SOAPBinding(style = Style.RPC)

public interface LoanServiceInterface {
    
    @WebMethod(operationName = "listLoans")
    public Loan[] listLoans();
    
    @WebMethod(operationName = "listLoansByBookId")
    public Loan[] listLoansByBook(@WebParam(name = "bookId") int bookId);
    
    @WebMethod(operationName = "listLoansByMemberId")
    public Loan[] listLoansByMemberId(@WebParam(name = "memberId") int memberId);
    
    @WebMethod(operationName = "getLoan")
    public Loan getLoan(@WebParam(name = "loanId") int loanId);
    
    @WebMethod(operationName = "borrowBook")
    public void borrowBook(@WebParam(name = "userId")int userId, @WebParam(name = "bookId") int bookId)throws LoanUnvailableException;
    
    @WebMethod(operationName = "updateLoan")
    public void updateLoan(@WebParam(name = "loanId") int loanId)throws LoanUnvailableException;
    
    @WebMethod(operationName = "returnItem")
    public void returnItem(@WebParam(name = "loandId") int loanId)throws LoanUnvailableException;
    
    @WebMethod(operationName = "deletLoan")
    public void deleteLoan(@WebParam(name = "loanId") int loanId)throws LoanUnvailableException;
    
    
    @WebMethod(operationName = "listMembers")
    public User[] listMembers();
    
    @WebMethod(operationName = "getMember")
    public User getMember(@WebParam(name = "userId") int userId);
    
    @WebMethod(operationName = "addMember")
    public void addMember(@WebParam(name = "login") String login, @WebParam(name="pass") String pass);
    
    @WebMethod(operationName = "updateMember")
    public void updateMember(@WebParam(name = "userId") int userId, @WebParam(name = "login") String login, @WebParam(name="pass") String pass, @WebParam(name = "authLevel") int authLevel) throws UserUnvailableException;
    
    @WebMethod(operationName = "deleteMember")
    public void deleteMember(@WebParam(name = "userId") int userId) throws UserUnvailableException;
    
    @WebMethod(operationName = "authenticateUser")
    public User authUser(@WebParam(name="login") String login, @WebParam(name="pass") String pass) throws UserLoginException;

}
