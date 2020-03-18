/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pkg487.loan.system;

import java.util.Date;
import java.util.List;
import pkg487.loan.core.Loan;
import javax.persistence.*;


/**
 *
 * @author ryan
 */


//TODO: LoanException class instead of generic exception
public class LoanManager {
   
    private static final EntityManagerFactory ENTITY_MANAGER_FACTORY = Persistence.createEntityManagerFactory("487-Loan-CorePU");

    public List<Loan> read(Integer id){
	
	EntityManager em = ENTITY_MANAGER_FACTORY.createEntityManager();
	List<Loan> result = null;
	if(id == null){
	   result = em.createNamedQuery("Loan.findAll").getResultList();
	}
	else{
	    Query query = em.createNamedQuery("Loan.findById");
	    query.setParameter("id", id);
	    result = query.getResultList();
	}
	
	if(!result.isEmpty())
	    return result;
	else
	    return null;
    }
    //Need to check if book exists from client request to Library before doing this method, also user not implemented yet
    public Loan create(Loan newLoan) {
	Integer bookId = newLoan.getBookId();
	EntityManager em = ENTITY_MANAGER_FACTORY.createEntityManager();
	EntityTransaction et = null;
	Loan l = null;
	try {
	    Query query = em.createNamedQuery("Loan.verifyAvailable").setParameter("bookId", bookId);
	    if(!query.getResultList().isEmpty())
		throw new Exception("Book is active in another loan. Cannot create new loan for this book.");
	    et = em.getTransaction();
	    et.begin();
	    l = new Loan();
	    l.setBookId(newLoan.getBookId());
	    l.setUserId(newLoan.getUserId());
	    l.setDateBorrowed(new Date());
	    em.persist(l);
	    et.commit();
	} catch (Exception e) {
	    if (et != null) {
		et.rollback();
	    }
	    e.printStackTrace();

	} finally {
	    em.close();
	    return l;
	}

    }
    
    public Loan delete(Integer id){

        EntityManager em = ENTITY_MANAGER_FACTORY.createEntityManager();
        EntityTransaction et = null;
	Loan l = null;
        try {
                et = em.getTransaction();
                et.begin();
		l = em.find(Loan.class, id);
		if(l == null)
		    throw new Exception("No loan with id : " + id);
		em.remove(l);
                et.commit();
        }catch (Exception e) {
            if (et != null) {
                et.rollback();
            }
            e.printStackTrace();

        } finally {
            em.close();
	    return l;
        }
	
    }
    
  public Loan update(Loan editedLoan){
        EntityManager em = ENTITY_MANAGER_FACTORY.createEntityManager();
        EntityTransaction et = null;
	Loan l = null;
        try {
                et = em.getTransaction();
                et.begin();
		l = em.find(Loan.class,editedLoan.getId());
		if(l == null) 
		    throw new Exception("No loan with id: " + editedLoan.getId());
		l.setDateBorrowed(editedLoan.getDateBorrowed());
		l.setDateReturned(editedLoan.getDateReturned());
                em.persist(l);
                et.commit();
        }catch (Exception e) {
            if (et != null) {
                et.rollback();
            }
            e.printStackTrace();

        } finally {
            em.close();
	    return l;
        }
	
    }
  
  public Loan returnBook(Integer loanId){
        EntityManager em = ENTITY_MANAGER_FACTORY.createEntityManager();
        EntityTransaction et = null;
	Loan l = null;
        try {
		et = em.getTransaction();
                et.begin();
		l = em.find(Loan.class,loanId);
		if(l == null) 
		    throw new Exception("No loan with id: " + loanId);
		if(l.getDateReturned() != null)
		    throw new Exception("Book already returned.");
		l.setDateReturned(new Date());
                em.persist(l);
                et.commit();
        }catch (Exception e) {
            if (et != null) {
                et.rollback();
            }
            e.printStackTrace();

        } finally {
            em.close();
	    return l;
        }
	
    }
    
  
  public Loan[] loansByBookId(int bookId){
      
      if(bookId == -1){
          return new Loan[0];
      }
      
      EntityManager em = ENTITY_MANAGER_FACTORY.createEntityManager();
      List<Loan> result = null;
      
      try{
          Query query = em.createNamedQuery("Loan.findByBookId");
          query.setParameter("bookId", bookId);
          result = query.getResultList();      
      }catch(Exception e){
          e.printStackTrace();
      }
      
      Loan[] loans = new Loan[result.size()];
      result.toArray(loans);
      
      return loans;
  }
  
}


