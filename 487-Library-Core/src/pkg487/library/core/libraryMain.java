/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pkg487.library.core;
import java.util.List;

/**
 *
 * @author ryan
 */
public class libraryMain {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
	// TODO code application logic here
	
	Library lib = new Library();
	List<Book> books = lib.read(null);
	for (Book b : books){
	    System.out.println(b);
	}
    }
    
}
