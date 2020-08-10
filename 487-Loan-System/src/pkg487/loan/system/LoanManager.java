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
 Provides CRUD Operations for Loans
 */

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
	em.close();
	
	return result;
    }
    //Need to check if book exists from client request to Library before doing this method
    public void create(Loan newLoan) throws LoanUnvailableException {
	Integer bookId = newLoan.getBookId();
	EntityManager em = ENTITY_MANAGER_FACTORY.createEntityManager();
	Loan l = null;
	Query query = em.createNamedQuery("Loan.verifyAvailable").setParameter("bookId", bookId);
	if(!query.getResultList().isEmpty()){
	    em.close();
	    throw new LoanUnvailableException("Book is active in another loan. Cannot create new loan for this book.");
	}
	EntityTransaction et = null;
	try {
	   
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
	}

    }
    
    public void delete(Integer id) throws LoanUnvailableException{

        EntityManager em = ENTITY_MANAGER_FACTORY.createEntityManager();
        EntityTransaction et = null;
	Loan l = null;		
	l = em.find(Loan.class, id);
	if(l == null){
	    em.close();
	    throw new LoanUnvailableException("No loan with id : " + id);
	}

        try {
                et = em.getTransaction();
                et.begin();

		em.remove(l);
                et.commit();
        }catch (Exception e) {
            if (et != null) {
                et.rollback();
            }
            e.printStackTrace();

        } finally {
            em.close();
        }
	
    }
    
  public void update(Loan editedLoan) throws LoanUnvailableException{
        EntityManager em = ENTITY_MANAGER_FACTORY.createEntityManager();
        EntityTransaction et = null;
	Loan l = null;
	l = em.find(Loan.class, editedLoan.getId());
	if(l == null){
	    em.close();
	    throw new LoanUnvailableException("No loan with id : " + editedLoan.getId());
	}
        try {
                et = em.getTransaction();
                et.begin();
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
        }
	
    }
  
//Logic for returning a book
  public void returnBook(Integer loanId) throws LoanUnvailableException{
        EntityManager em = ENTITY_MANAGER_FACTORY.createEntityManager();
        EntityTransaction et = null;
	Loan l = null;
	l = em.find(Loan.class,loanId);
	if(l == null){
	    em.close();
	    throw new LoanUnvailableException("No loan with id: " + loanId);
	}
	    
	else if(l.getDateReturned() != null){
	    em.close();
	    throw new LoanUnvailableException("Book already returned.");
	}
	    
        try {
		et = em.getTransaction();
                et.begin();
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
        }
	
    }
  //Get the active loan given bookId and userId
  public Loan getActiveLoan(Integer bookId, Integer userId) throws LoanUnvailableException{
      EntityManager em = ENTITY_MANAGER_FACTORY.createEntityManager();
      Query query = em.createNamedQuery("Loan.findActiveForUser").setParameter("bookId", bookId).setParameter("userId", userId);
      List<Loan> results = query.getResultList();
      if(results.isEmpty()){
	em.close();
	throw new LoanUnvailableException("No active loan with userId :" +userId+ " and bookId : "+bookId);
      }else
	  return results.get(0);
  }
  
 
  //Returns all active loans for a given user
  public Loan[] getAllActiveLoans(Integer userId) throws LoanUnvailableException{
      EntityManager em = ENTITY_MANAGER_FACTORY.createEntityManager();
      Query query = null;
      if(userId == null)
	query = em.createNamedQuery("Loan.findAllActive");
      else
	query = em.createNamedQuery("Loan.findAllActiveForUser").setParameter("userId", userId);
      
      List<Loan> resultList = query.getResultList();
      em.close();
      Loan[] active = new Loan[resultList.size()];
      resultList.toArray(active);
      return active;
    }
  
  //Return loans by book ids
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
      }finally{
	  em.close();

      }
      Loan[] loans = new Loan[result.size()];
      result.toArray(loans);
      return loans;

  }
  
  public Loan[] loansByMemberId(int userId){
      EntityManager em = ENTITY_MANAGER_FACTORY.createEntityManager();
      List<Loan> result = null;
      
      try{
          Query query = em.createNamedQuery("Loan.findByUserId");
          query.setParameter("userId", userId);
          result = query.getResultList();      
      }catch(Exception e){
          e.printStackTrace();
      }finally{
	  em.close();
      }
      
      Loan[] loans = new Loan[result.size()];
      result.toArray(loans);
      return loans;
  }
  
}


