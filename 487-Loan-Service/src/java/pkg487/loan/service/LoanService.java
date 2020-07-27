/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pkg487.loan.service;

import java.util.List;
import javax.jws.WebService;
import javax.jws.WebParam;
import pkg487.loan.core.*;
import pkg487.loan.system.*;

/**
 *
 * @author Xavier Vani-Charron
 */
@WebService(portName="LoanServicePort", serviceName="LoanService", endpointInterface = "pkg487.loan.system.LoanServiceInterface")
public class LoanService implements LoanServiceInterface {
    
    /**
     * Loan Operations
     * @return 
     */
    
    public Loan[] listLoans(){
        LoanManager manager = new LoanManager();
        List<Loan> loanList = manager.read(null);
        Loan[] loanArray = new Loan[loanList.size()];
        loanList.toArray(loanArray);
        return loanArray;
    }
    
    public Loan[] listLoansByBook(@WebParam(name = "bookId") int bookId){
        LoanManager manager = new LoanManager();
        return manager.loansByBookId(bookId);
    }
    
    public Loan[] listLoansByMemberId(@WebParam(name = "memberId") int memberId){
        LoanManager manager = new LoanManager();
        return manager.loansByMemberId(memberId);
    }
    
    public Loan getLoan(@WebParam(name = "loanId") int loanId){
        LoanManager manager = new LoanManager();
        List<Loan> loanList = manager.read(loanId);
        return loanList.get(0);
    }
    
    public void borrowBook(@WebParam(name = "userId") int userId, @WebParam(name = "bookId") int bookId) throws LoanUnvailableException{
        Loan newLoan = new Loan();
        newLoan.setBookId(bookId);
        newLoan.setUserId(userId);
        LoanManager manager = new LoanManager();
        manager.create(newLoan);
    }
    
    public void updateLoan(@WebParam(name = "loanId") int loanId) throws LoanUnvailableException{
        Loan loanToUpdate = new Loan();
        loanToUpdate.setId(loanId);
        LoanManager manager = new LoanManager();
        manager.update(loanToUpdate);
    }
    
    public void returnItem(@WebParam(name = "loandId") int loanId) throws LoanUnvailableException{
        LoanManager manager = new LoanManager();
        manager.returnBook(loanId);
    }
    
    public void deleteLoan(@WebParam(name = "loanId") int loanId) throws LoanUnvailableException{
        LoanManager manager = new LoanManager();
        manager.delete(loanId);
    }
    
    /**
     * 
     * User Operations
     * 
     */
    
    public User[] listMembers(){
        UserManager manager = new UserManager();
        List<User> userList = manager.read(null);
        User[] userArray = new User[userList.size()];
        userList.toArray(userArray);
        return userArray;
    }
    
    public User getMember(@WebParam(name = "userId") int userId){
        UserManager manager = new UserManager();
        User result = new User();
        List<User> singleUser = manager.read(userId);
        
        if(!singleUser.isEmpty()){
            result = singleUser.get(0);
        }
        
        return result;
    }
    
    public void addMember(@WebParam(name = "login") String login, @WebParam(name="pass") String pass){
        User newUser = new User();
        newUser.setLogin(login);
        newUser.setPass(pass);
        newUser.setAuthLevel(1);
        UserManager manager = new UserManager();
        manager.create(newUser);
    }
    
    public void updateMember(@WebParam(name = "userId") int userId, @WebParam(name = "login") String login, @WebParam(name="pass") String pass, @WebParam(name = "authLevel") int authLevel) throws UserUnvailableException{
        
        User userToUpdate = new User();
        UserManager manager = new UserManager();
        List<User> userList = manager.read(userId);
        
        if(!userList.isEmpty()){
            userToUpdate = userList.get(0);
        }
        
        userToUpdate.setLogin(login);
        userToUpdate.setPass(pass);
        userToUpdate.setAuthLevel(authLevel);
        
        manager.update(userToUpdate);
    }
    
    public void deleteMember(@WebParam(name = "userId") int userId) throws UserUnvailableException{
        UserManager manager = new UserManager();
        manager.delete(userId);
    }
    
    public User authUser(@WebParam(name="login") String login, @WebParam(name="pass") String pass) throws UserLoginException{
        
        UserManager manager = new UserManager();
        User user = manager.authenticate(login, pass);
        
        if(user == null){
            throw new UserLoginException("Login invalid.");
        }
        
        return user;
    }
}
