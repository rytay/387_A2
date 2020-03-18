/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pkg487.loan.core;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author ryan
 */
@Entity
@Table(name = "loan")
@XmlRootElement(name = "loan")
@NamedQueries({
    @NamedQuery(name = "Loan.findAll", query = "SELECT l FROM Loan l")
    , @NamedQuery(name = "Loan.findById", query = "SELECT l FROM Loan l WHERE l.id = :id")
    , @NamedQuery(name = "Loan.findByBookId", query = "SELECT l FROM Loan l WHERE l.bookId = :bookId")
    , @NamedQuery(name = "Loan.findByUserId", query = "SELECT l FROM Loan l WHERE l.userId = :userId")
    , @NamedQuery(name = "Loan.findByDateBorrowed", query = "SELECT l FROM Loan l WHERE l.dateBorrowed = :dateBorrowed")
    , @NamedQuery(name = "Loan.findByDateReturned", query = "SELECT l FROM Loan l WHERE l.dateReturned = :dateReturned")
    , @NamedQuery(name = "Loan.verifyAvailable", query = "SELECT l FROM Loan l WHERE l.bookId = :bookId AND l.dateReturned IS NULL")})
public class Loan implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Column(name = "book_id")
    private Integer bookId;
    @Column(name = "user_id")
    private Integer userId;
    @Column(name = "date_borrowed")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dateBorrowed;
    @Column(name = "date_returned")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dateReturned;

    public Loan() {
    }

    public Loan(Integer id) {
	this.id = id;
    }

    public Integer getId() {
	return id;
    }

    public void setId(Integer id) {
	this.id = id;
    }

    public Integer getBookId() {
	return bookId;
    }

    public void setBookId(Integer bookId) {
	this.bookId = bookId;
    }

    public Integer getUserId() {
	return userId;
    }

    public void setUserId(Integer userId) {
	this.userId = userId;
    }

    public Date getDateBorrowed() {
	return dateBorrowed;
    }

    public void setDateBorrowed(Date dateBorrowed) {
	this.dateBorrowed = dateBorrowed;
    }

    public Date getDateReturned() {
	return dateReturned;
    }

    public void setDateReturned(Date dateReturned) {
	this.dateReturned = dateReturned;
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
	if (!(object instanceof Loan)) {
	    return false;
	}
	Loan other = (Loan) object;
	if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
	    return false;
	}
	return true;
    }

    @Override
    public String toString() {
	return "pkg487.loan.core.Loan[ id=" + id + " ]";
    }
    
}
