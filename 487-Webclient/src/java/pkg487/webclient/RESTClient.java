/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pkg487.webclient;
import com.sun.xml.rpc.processor.modeler.j2ee.xml.resourceEnvRefType;
import java.util.List;
import javax.ws.rs.ClientErrorException;
import javax.ws.rs.FormParam;
import javax.ws.rs.PathParam;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.Form;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import pkg487.library.core.Book;

/**
 *
 * @author ryan
 * 
 *We can clean up the libraries later, used for testing in main
 */
public class RESTClient {
    
    
    
    private Client client = ClientBuilder.newClient();
    private static final String BASE_URI = "http://localhost:8080/487-Library-Service/webresources/library";
    
    
    public RESTClient(){
	
    }
    
   //Returns JSON/XML of book list in raw string form
   public String listBooksRaw(String mediaType){
	String results = client
		.target(BASE_URI)
		.path(mediaType).path("list")
		.request()
		.get(String.class);
        return results;
    }
   
    //Returns list of books with autoconversion
    public List<Book> listBooks(String mediaPath){
	List<Book> results = client
		.target(BASE_URI)
		.path(mediaPath).path("list")
		.request()
		.get(new GenericType<List<Book>>(){});
        return results;
    }
    
    //Returns list of 1 book in raw XML/JSON format
    public String findBookRaw(String mediaType, String id){
	String result = client
		.target(BASE_URI)
		.path(mediaType).path("list").path(id)
		.request()
		.get(String.class);
        return result;
    }
    
    //Finds and returns book with autoconversion
    public Book findBook(String mediaType, String id){
	//May need to catch not found exception here and handle string conversion to int
	List<Book> results = client
		.target(BASE_URI)
		.path(mediaType).path("list").path(id)
		.request()
		.get(new GenericType<List<Book>>(){});
        return results.get(0);
    }
    
    public String listBooksPlain(){
	return client
		.target(BASE_URI)
		.path("text").path("list")
		.request()
		.get(String.class);
    }
    
    public String findBookPlain(String id){
	return client
		.target(BASE_URI)
		.path("text").path("list").path(id)
		.request()
		.get(String.class);
    }
    
    public String deleteBook(String id){
	
	return client
		.target(BASE_URI)
		.request()
		.get(String.class);
    }
    
    //Creates a book using book object
    public String createBook(Book newBook, String mediaType){
	Response response = client.target(BASE_URI)
		.path("create")
		.request()
		.post(Entity.entity(newBook, mediaType));
	return response.readEntity(String.class);
    }
    
    //Uses string instead of book to create new book in database
    public String createBookFromRaw(String raw, String mediaType){
       Response response = client.target(BASE_URI)
	       .path("create")
	       .request()
	       .post(Entity.entity(raw, mediaType));
       return response.readEntity(String.class);
    }
    
    //Use raw XML/JSON string to edit book
    public String editBookWithRaw(String raw, String mediaType){
	    Response response = client.target(BASE_URI)
	    .path("create")
	    .request()
	    .put(Entity.entity(raw, mediaType));
	return response.readEntity(String.class);
    }
    
    
    public String editBook(Book editedBook,String mediaType){
		Response response = client.target(BASE_URI)
		.path("create")
		.request()
		.put(Entity.entity(editedBook, mediaType));
	return response.readEntity(String.class);
    }
    
    public String formEditBook(String id,
	String title, 
	String description, 
	String author,
	String isbn,
	String publisher){
	Form form = new Form();
	form.param("id", id);
	form.param("title", title);
	form.param("description", description);
	form.param("author", author);
	form.param("isbn",isbn);
	form.param("publisher", publisher);
	Response response = client.target(BASE_URI)
		.path("edit").request()
		.put(Entity.entity(form, MediaType.APPLICATION_FORM_URLENCODED));
	return response.readEntity(String.class);

    }
   
    
    
    
    
}
