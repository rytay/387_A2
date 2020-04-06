/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pkg487.webclient;
import pkg487.loan.system.LoanServiceInterface;
import java.net.MalformedURLException;
import java.net.URL;
import javax.xml.namespace.QName;
import javax.xml.ws.Service;
import javax.xml.ws.WebServiceClient;
import pkg487.loan.core.Loan;
import pkg487.loan.core.User;
import pkg487.loan.system.LoanUnvailableException;
import pkg487.loan.system.UserLoginException;
import pkg487.loan.system.UserUnvailableException;


/**
 *
 * @author Xavier Vani-Charron
 */
@WebServiceClient(name = "LoanMemberClient")
public class SOAPClient {
    
    private static URL wsdl;
    private static QName qname = new QName("http://service.loan.pkg487/", "LoanService");
    private static Service service;
    private static LoanServiceInterface loanService;
    
    public SOAPClient(){
        URL url = null;
        try{
            url = new URL("http://localhost:8080/487-Loan-Service/LoanService?wsdl");
        }catch(MalformedURLException e){
            e.printStackTrace();
        }
        wsdl = url;
        service = Service.create(wsdl, qname);
        loanService = service.getPort(LoanServiceInterface.class);
    }
    
    public Loan[] listLoans(){
        return loanService.listLoans();
    }
    
    public Loan[] listLoansByBook(int bookId){
        return loanService.listLoansByBook(bookId);
    }
    
    public Loan[] listLoansByMemberId(int memberId){
        return loanService.listLoansByMemberId(memberId);
    }
    
    public Loan getLoan(int loanId){
        return loanService.getLoan(loanId);
    }
    
    public void borrowBook(int userId, int bookId) throws LoanUnvailableException{
        loanService.borrowBook(userId, bookId);
    }
    
    public void updateLoan(int loanId) throws LoanUnvailableException{
        loanService.updateLoan(loanId);
    }
    
    public void returnItem(int loanId) throws LoanUnvailableException{
        loanService.returnItem(loanId);
    }
    
    public void deleteLoan(int loanId) throws LoanUnvailableException{
        loanService.deleteLoan(loanId);
    }
    
    public User[] listMembers(){
        return loanService.listMembers();
    }
    
    public User getMember(int userId){
        return loanService.getMember(userId);
    }
    
    public void addMember(String login, String pass){
        loanService.addMember(login, pass);
    }
    
    public void updateMemebr(int userId, String login, String pass, int authLevel) throws UserUnvailableException{
        loanService.updateMember(userId, login, pass, authLevel);
    }
    
    public void deleteMember(int userId) throws UserUnvailableException{
        loanService.deleteMember(userId);
    }
    
    public User authUser(String login, String pass) throws UserLoginException{
        return loanService.authUser(login, pass);
    }
}
