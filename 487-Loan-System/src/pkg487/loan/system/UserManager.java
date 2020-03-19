/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pkg487.loan.system;

import java.util.Date;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.Query;
import pkg487.loan.core.*;

/**
 *
 * @author ryan
 */
public class UserManager {
    
    private static final EntityManagerFactory ENTITY_MANAGER_FACTORY = Persistence.createEntityManagerFactory("487-User-CorePU");

    public List<User> read(Integer id){
	
	EntityManager em = ENTITY_MANAGER_FACTORY.createEntityManager();
	List<User> result = null;
	if(id == null){
	   result = em.createNamedQuery("User.findAll").getResultList();
	}
	else{
	    Query query = em.createNamedQuery("User.findById");
	    query.setParameter("id", id);
	    result = query.getResultList();
	}
	em.close();
	
	return result;
    }
 
    public void create(User newUser){
	EntityManager em = ENTITY_MANAGER_FACTORY.createEntityManager();
	User u = null;
	EntityTransaction et = null;
	try {
	   
	    et = em.getTransaction();
	    et.begin();
	    u = new User();
	    u.setLogin(newUser.getLogin());
	    u.setAuthLevel(newUser.getAuthLevel());
	    u.setPass(newUser.getPass());
	    em.persist(u);
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
    
    public void delete(Integer id) throws UserUnvailableException{

        EntityManager em = ENTITY_MANAGER_FACTORY.createEntityManager();
        EntityTransaction et = null;
	User u = null;		
	u = em.find(User.class, id);
	if(u == null){
	    em.close();
	    throw new UserUnvailableException("No user with id : " + id);
	}

        try {
                et = em.getTransaction();
                et.begin();
		em.remove(u);
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
    
  public void update(User editedUser) throws UserUnvailableException{
        EntityManager em = ENTITY_MANAGER_FACTORY.createEntityManager();
        EntityTransaction et = null;
	User u = null;
	u = em.find(User.class, editedUser.getId());
	if(u == null){
	    em.close();
	    throw new UserUnvailableException("No user with id : " + editedUser.getId());
	}
        try {
                et = em.getTransaction();
                et.begin();
		u.setLogin(editedUser.getLogin());
		u.setAuthLevel(editedUser.getAuthLevel());
		u.setPass(editedUser.getPass());
                em.persist(u);
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
  
  public User authenticate(String login, String password) throws UserLoginException{
	EntityManager em = ENTITY_MANAGER_FACTORY.createEntityManager();
	Query query = em.createNamedQuery("User.findByUserPass");
	query.setParameter("login",login);
	query.setParameter("pass", password);
	List<User> result = query.getResultList();
	em.close();
	if(result.isEmpty())
	    throw new UserLoginException("Authentication failed. Bad username or password.");
	else
	    return result.get(0);
	
    }
    
    public Integer getIdFromLoginInfo(String login, String password) throws UserLoginException{
	return authenticate(login, password).getId();
    }
    
}
