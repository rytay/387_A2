/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pkg487.loan.system;
import java.util.List;
import pkg487.loan.core.Loan;
import pkg487.loan.system.LoanManager;

/**
 *
 * @author ryan
 */
public class loanMain {
        /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
	
	LoanManager lm = new LoanManager();
	List<Loan> loans = lm.read(null);
	for(Loan l : loans){
	    System.out.println(l);
	}
	System.out.println("Deleting with ID 1");
	Loan deleted = lm.delete(1);
	System.out.println(deleted);
	loans = lm.read(null);
	for(Loan l : loans){
	System.out.println(l);
	}
	
    }
    
}
