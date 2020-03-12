/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pkg487.library.core;
import java.util.List;
import pkg487.library.core.Book;
import javax.persistence.*;

/**
 *
 * @author ryan
 */
public class Library {
    
    private static final EntityManagerFactory ENTITY_MANAGER_FACTORY = Persistence.createEntityManagerFactory("487-Library-CorePU");
    
    public List<Book> read(Integer id){
	
	EntityManager em = ENTITY_MANAGER_FACTORY.createEntityManager();
	List<Book> result = null;
	if(id == null){
	   result = em.createNamedQuery("Book.findAll").getResultList();
	}
	else{
	    Query query = em.createNamedQuery("Book.findById");
	    query.setParameter("id", id);
	    result = query.getResultList();
	}
	
	if(!result.isEmpty())
	    return result;
	else
	    return null;
	    
	
    }
    public Book create(Book newBook){

        EntityManager em = ENTITY_MANAGER_FACTORY.createEntityManager();
        EntityTransaction et = null;
	Book b = null;
        try {
                et = em.getTransaction();
                et.begin();
		b = new Book();
                b.setTitle(newBook.getTitle());
                b.setIsbn(newBook.getIsbn());
                b.setAuthor(newBook.getAuthor());
                b.setBookDesc(newBook.getBookDesc());
                b.setPublisher(newBook.getPublisher());
                em.persist(b);
                et.commit();
        }catch (Exception e) {
            if (et != null) {
                et.rollback();
            }
            e.printStackTrace();

        } finally {
            em.close();
	    return b;
        }
	
    }
    
    public Book delete(Integer id){

        EntityManager em = ENTITY_MANAGER_FACTORY.createEntityManager();
        EntityTransaction et = null;
	Book b = null;
        try {
                et = em.getTransaction();
                et.begin();
		b = em.find(Book.class, id);
		em.remove(b);
                et.commit();
        }catch (Exception e) {
            if (et != null) {
                et.rollback();
            }
            e.printStackTrace();

        } finally {
            em.close();
	    return b;
        }
	
    }
    
    
    public Book update(Book editedBook){
        EntityManager em = ENTITY_MANAGER_FACTORY.createEntityManager();
        EntityTransaction et = null;
	Book b = null;
        try {
                et = em.getTransaction();
                et.begin();
		b = em.find(Book.class,editedBook.getId());
                b.setTitle(editedBook.getTitle());
                b.setIsbn(editedBook.getIsbn());
                b.setAuthor(editedBook.getAuthor());
                b.setBookDesc(editedBook.getBookDesc());
                b.setPublisher(editedBook.getPublisher());
                em.persist(b);
                et.commit();
        }catch (Exception e) {
            if (et != null) {
                et.rollback();
            }
            e.printStackTrace();

        } finally {
            em.close();
	    return b;
        }
	
    }
    
    
 }
    
