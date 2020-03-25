/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pkg487.library.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.List;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Produces;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PUT;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.MediaType;

import pkg487.library.system.LibraryManager;
import pkg487.library.core.Book;

import javax.ws.rs.FormParam;
import javax.ws.rs.POST;
import javax.ws.rs.core.GenericEntity;
import javax.ws.rs.core.Response;
import pkg487.library.system.LibraryException;


/**
 * REST Web Service
 *
 * @author Xavier Vani-Charron, Ryan Taylor
 */
@Path("library")
@Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON, MediaType.TEXT_PLAIN})
public class LibraryResource {
    
    @Context
    private UriInfo context;

    /**
     * Creates a new instance of LibraryResource
     */
    public LibraryResource() {
        
    }
    
    /*Converts to JSON but i dont think its needed. jax-rs does it automatically
    private String toJson(Object o){
     String result = "";
     try{
	 ObjectMapper mapper = new ObjectMapper();
	 result = mapper.writeValueAsString(o);
	 }catch(Exception e){
	     e.printStackTrace();
	 }

     return result;

     }
    
    
    
    not sure if should always return a list and then handle on client if there are none/one result. 
    or determine whether to send a list or single entity in this class.
    private Response toRequestedType(List<Book> books, String type, boolean singleResult) {

	switch(type){
	    case "json":
		if(singleResult)
		    return Response.ok(books.get(0), MediaType.APPLICATION_JSON).build();
		else
		    return Response.ok(books, MediaType.APPLICATION_JSON).build();
	    case "xml":
		if(singleResult){
		    return Response.ok(books.get(0), MediaType.APPLICATION_XML).build();
		}
		else{
		    GenericEntity<List<Book>> list = new GenericEntity<List<Book>>(books) {};
		    return Response.ok(list, MediaType.APPLICATION_XML).build();
		}

	    case "text":
		if(singleResult)
		    return Response.ok(books.get(0).toString(), MediaType.TEXT_PLAIN).build();
		else
		    return Response.ok(books.toString(), MediaType.TEXT_PLAIN).build();
	    case "html":
		//Need to implement html conversion
		return Response.ok("HTML NOT IMPLEMENTED",MediaType.TEXT_HTML).build();
	    default:
		return Response.status(Response.Status.BAD_REQUEST).entity("No such type : "+type).type(MediaType.TEXT_PLAIN).build();
	}

    }
    */
    //Always returns a list. Empty list if no results. list with 1 element for single book find. List with all books for listing all books
    private Response toRequestedType(List<Book> queryResult, String type) {

	switch(type){
	    case "json":
		    GenericEntity<List<Book>> jsonList = new GenericEntity<List<Book>>(queryResult) {};
		    return Response.ok(jsonList, MediaType.APPLICATION_JSON).build();
	    case "xml":
		    //Need to convert to genericEntity to process xml as list
		    GenericEntity<List<Book>> list = new GenericEntity<List<Book>>(queryResult) {};
		    return Response.ok(list, MediaType.APPLICATION_XML).build();

	    case "text":
		return Response.ok(queryResult.toString(), MediaType.TEXT_PLAIN).build();
	    case "html":
		//Need to implement html conversion
		return Response.ok("HTML NOT IMPLEMENTED",MediaType.TEXT_HTML).build();
	    default:
		return Response.status(Response.Status.BAD_REQUEST).entity("No such type : "+type).type(MediaType.TEXT_PLAIN).build();
	}

    }

    
        /**
     * Gets a list of a single book from LibraryManager
     * 
     * @return list of book with id specified or empty list
     */
    

    @GET
    @Path("/{type}/list/{id}")
    public Response findBook(@PathParam("id") Integer id, @PathParam("type") String type){

	LibraryManager manager = new LibraryManager();
	List<Book> queryResult = manager.read(id);
	/*determine whether to return a single book or a list. No result for query will return an empty list
	boolean singleResult = id != null && !books.isEmpty();
	return toRequestedType(books, type, singleResult);
	*/
	return toRequestedType(queryResult, type);
    }
    
            /**
     * Gets a list of all Books from Library Manager
     * Plain Text Format
     * @return list of books
     */
    @GET
    @Path("/{type}/list")
    public Response listBooks(@PathParam("type") String type){
	return findBook(null, type);
    }
    
    
    /**
     * Creates book in db based on JSON or XML serialized book
     * @param newBook
     * @return 
     */
    @POST
    @Produces(MediaType.TEXT_PLAIN)
    @Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    @Path("/create")
    public Response createBook(Book newBook){
	LibraryManager manager = new LibraryManager();
	manager.create(newBook);
	
	return Response.ok(200).entity("Successfully added book : \n"+ newBook).build();
    }
    
    
       /**
     * Creates book in db based on HTML form
     * @param b
     * @return 
     */
    @POST
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    @Path("/create")
    public Response createBookFromForm(
	    @FormParam("title") String title, 
	    @FormParam("description") String description, 
	    @FormParam("author") String author,
	    @FormParam("isbn") String isbn,
	    @FormParam("publisher") String publisher){
	
	LibraryManager manager = new LibraryManager();
	Book newBook = new Book();
	newBook.setTitle(title);
	newBook.setAuthor(author);
	newBook.setBookDesc(description);
	newBook.setIsbn(isbn);
	newBook.setPublisher(publisher);
	manager.create(newBook);
	
	return Response.ok(200).entity("Successfully added book : \n"+ newBook).build();
	
    }
    
    /**
     * Deletes a book from db using id
     * @param id
     * @return 
     */
    @POST
    @Path("/delete/{id}")
    public Response deleteById(@PathParam("id") Integer id){
	
	try{
	    LibraryManager manager = new LibraryManager();
	    manager.delete(id);
	    
	}catch(LibraryException e){
	    return Response.ok(e.getMessage(),MediaType.TEXT_PLAIN).build();
	}
	
	return Response.ok(200).entity("Successfully deleted book with id : "+id).build();
	
    }
  
    /**
     * edits a book using serialized version of 
     * @param b
     * @return 
     */
    @PUT
    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    @Path("/edit")
    public Response editBook(Book editedBook){
	try{
	    LibraryManager manager = new LibraryManager();
	    manager.update(editedBook);
	    
	}catch(LibraryException e){
	    return Response.ok(e.getMessage(),MediaType.TEXT_PLAIN).build();
	}
	
	return Response.ok(200).entity("Successfully edited book : \n"+editedBook).build();
    }
    
    @PUT
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    @Path("/edit")
    public Response formEditBook(@FormParam("id") Integer id,
	    @FormParam("title") String title, 
	    @FormParam("description") String description, 
	    @FormParam("author") String author,
	    @FormParam("isbn") String isbn,
	    @FormParam("publisher") String publisher){
	
	LibraryManager manager = new LibraryManager();
	Book editedBook = new Book();
	editedBook.setId(id);
	editedBook.setTitle(title);
	editedBook.setAuthor(author);
	editedBook.setBookDesc(description);
	editedBook.setIsbn(isbn);
	editedBook.setPublisher(publisher);
	try{
	    manager.update(editedBook);
	}catch(LibraryException e){
	    
	    return Response.ok(e.getMessage(),MediaType.TEXT_PLAIN).build();
	}
	
	
	return Response.ok(200).entity("Successfully edited book : \n"+editedBook).build();
	
    }
    
    
    
}
   
    
  
    

    //Xaviers stuff===============================================================================================
    
    
    
    
    /*
    
  
 
    
    @GET
    @Path("/text/{method}/{id}")
    @Produces(MediaType.TEXT_PLAIN)
    public String plainTextType(@PathParam("method") String method, @PathParam("id") Integer id){
        switch(method){
            case "list":
                return list(MediaType.TEXT_PLAIN_TYPE);
            case "getBook":
                return "Hit the getBook method with optional ID " + id;
            default:
                return "Not a correct path.";
        }
    }
    
    @GET
    @Path("/text/{method}")
    @Produces(MediaType.TEXT_PLAIN)
    public String plainTextType(@PathParam("method") String method){
        return plainTextType(method, null);
    }
    
    @GET
    @Path("/json/{method}/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public String jsonType(@PathParam("method") String method, @PathParam("id") Integer id){
        switch(method){
            case "list":
                return list(MediaType.APPLICATION_JSON_TYPE);
            default:
                return "Not a correct path.";
        }
    }
    
    @GET
    @Path("/json/{method}")
    @Produces(MediaType.APPLICATION_JSON)
    public String jsonType(@PathParam("method") String method){
        return jsonType(method, null);
    }
    
    @GET
    @Path("/xml/{method}/{id}")
    @Produces(MediaType.APPLICATION_XML)
    public Response xmlType(@PathParam("method") String method, @PathParam("id") Integer id){
        switch(method){
            case "list":
                return list(MediaType.APPLICATION_XML_TYPE);
            default:
                return "Not a correct path.";
        }
    }
    
    @GET
    @Path("/xml/{method}")
    @Produces(MediaType.APPLICATION_XML)
    public String xmlType(@PathParam("method") String method){
        return xmlType(method, null);
    }
    
    private String list(MediaType type){
        
        LibraryManager manager = new LibraryManager();
        List<Book> books = manager.read(null);
        String bookList = "";
        
        if(type == MediaType.TEXT_PLAIN_TYPE){        
             
            for(Book b : books){
                bookList += b.toString() + "\n";
            }
           
        }else if(type == MediaType.APPLICATION_JSON_TYPE){
            
            ObjectMapper mapper = new ObjectMapper();
                
            try{
                bookList = mapper.writeValueAsString(books);
            }catch(Exception e){
                e.printStackTrace();
            }
	    
        return bookList;
    }
  */

