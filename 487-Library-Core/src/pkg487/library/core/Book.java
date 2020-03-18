/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pkg487.library.core;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlElement;

/**
 *
 * @author ryan
 */
@Entity
@Table(name = "book")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Book.findAll", query = "SELECT b FROM Book b")
    , @NamedQuery(name = "Book.findById", query = "SELECT b FROM Book b WHERE b.id = :id")
    , @NamedQuery(name = "Book.findByTitle", query = "SELECT b FROM Book b WHERE b.title = :title")
    , @NamedQuery(name = "Book.findByBookDesc", query = "SELECT b FROM Book b WHERE b.bookDesc = :bookDesc")
    , @NamedQuery(name = "Book.findByIsbn", query = "SELECT b FROM Book b WHERE b.isbn = :isbn")
    , @NamedQuery(name = "Book.findByAuthor", query = "SELECT b FROM Book b WHERE b.author = :author")
    , @NamedQuery(name = "Book.findByPublisher", query = "SELECT b FROM Book b WHERE b.publisher = :publisher")})
public class Book implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Column(name = "title")
    private String title;
    @Column(name = "book_desc")
    private String bookDesc;
    @Column(name = "isbn")
    private String isbn;
    @Column(name = "author")
    private String author;
    @Column(name = "publisher")
    private String publisher;

    public Book() {
    }
    
    @XmlElement(name = "id")
    public Integer getId() {
	return id;
    }

    public void setId(Integer id) {
	this.id = id;
    }
    
    @XmlElement(name = "title")
    public String getTitle() {
	return title;
    }

    public void setTitle(String title) {
	this.title = title;
    }
    
    @XmlElement(name = "description")
    public String getBookDesc() {
	return bookDesc;
    }

    public void setBookDesc(String bookDesc) {
	this.bookDesc = bookDesc;
    }
    
    @XmlElement(name = "isbn")
    public String getIsbn() {
	return isbn;
    }

    public void setIsbn(String isbn) {
	this.isbn = isbn;
    }
    
    @XmlElement(name = "author")
    public String getAuthor() {
	return author;
    }

    public void setAuthor(String author) {
	this.author = author;
    }
    
    @XmlElement(name = "publisher")
    public String getPublisher() {
	return publisher;
    }
    
    public void setPublisher(String publisher) {
	this.publisher = publisher;
    }

    @Override
    public int hashCode() {
	int hash = 0;
	hash += (id != null ? id.hashCode() : 0);
	return hash;
    }

    @Override
    public boolean equals(Object object) {
	// TODO: Warning - this method won't work in the case the id fields are not set
	if (!(object instanceof Book)) {
	    return false;
	}
	Book other = (Book) object;
	if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
	    return false;
	}
	return true;
    }

    @Override
    public String toString() {
	return "ID: "+ id +" Title: "+title+" Description: "+bookDesc+ " ISBN: "+ isbn+" Author: "+author+" Publisher "+publisher;
    }
    
}
