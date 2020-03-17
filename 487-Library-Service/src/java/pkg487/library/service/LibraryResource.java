/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pkg487.library.service;

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

import com.fasterxml.jackson.annotation.*;
import com.fasterxml.jackson.core.*;
import com.fasterxml.jackson.databind.*;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import javax.ws.rs.core.Response;


/**
 * REST Web Service
 *
 * @author Xavier Vani-Charron
 */
@Path("library")
public class LibraryResource {
    
    @Context
    private UriInfo context;

    /**
     * Creates a new instance of LibraryResource
     */
    public LibraryResource() {
        
    }
    
    /**
     * Gets a list of all Books from Library Manager
     * Plain Text Format
     * @return list of books
     */
    
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
    public String xmlType(@PathParam("method") String method, @PathParam("id") Integer id){
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
        }else if(type == MediaType.APPLICATION_XML_TYPE){
            
            XmlMapper mapper = new XmlMapper();
            
            try{
                bookList = mapper.writeValueAsString(books);
            }catch(Exception e){
                e.printStackTrace();
            }
        }
        return bookList;
    }
    
}