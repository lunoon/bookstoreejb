package de.bookstore;

import javax.ejb.Stateless; // STRG + Shift + O = automatisches Importieren
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import de.bookstore.entity.Book;

@Stateless
@Path("bookstore")
public class BookEJB {
	
	@PersistenceContext(unitName="BookDB")
	private EntityManager em;

	@POST
	@Path("book/{a}/{t}/{p}")
	@Produces(MediaType.APPLICATION_JSON)
	public Book createBook(@PathParam("a") String author,@PathParam("t") String title,
			@PathParam("p") String publisher) {
		//System.err.println(book);
		Book book = new Book(author,title,publisher);
		em.persist(book);
		return book;
	}
	
	@GET
	@Path("book/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Book findBook(@PathParam("id") long id) {
		return em.find(Book.class, id);
	}
	
	
}
