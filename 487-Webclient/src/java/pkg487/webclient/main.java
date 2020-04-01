/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pkg487.webclient;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.List;
import javax.ws.rs.core.MediaType;
import pkg487.library.core.Book;

/**
 *
 * @author ryan
 */
public class main {
    
    public static void main(String[] args) throws JsonProcessingException {
	RESTClient restClient = new RESTClient();
	

	Book newBook = new Book();
	newBook.setTitle("nksafnas");
	newBook.setAuthor("95mla;fmsff");
	newBook.setBookDesc("desc");
	newBook.setIsbn("141204150");
	newBook.setPublisher("pub");
	//Converting book to json
	ObjectMapper mapper = new ObjectMapper();
	String bookJson = mapper.writeValueAsString(newBook);
	System.out.println(bookJson);
	//restClient.createBookFromRaw(bookJson, MediaType.APPLICATION_JSON);
	List<Book> books = restClient.listBooks("json");
	for (Book b : books){
	    System.out.println(b);
	}
	


    }
    
}
