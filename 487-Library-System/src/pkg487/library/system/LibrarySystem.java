/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pkg487.library.system;

import java.util.List;
import pkg487.library.system.LibraryManager;
import pkg487.library.core.Book;

/**
 *
 * @author ryan
 */
public class LibrarySystem {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
	// TODO code application logic here
	LibraryManager lm = new LibraryManager();
	List<Book> books = lm.read(null);
	for(Book b : books){
	    System.out.println(b);
	}
	System.out.println("Deleting with ID 1");
	Book deleted = lm.delete(1);
	
	books = lm.read(null);
	for(Book b : books){
	System.out.println(b);
	}
	
    }
	
}
    
