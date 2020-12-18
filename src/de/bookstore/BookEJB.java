package de.bookstore;

import javax.annotation.Resource;
import javax.ejb.Stateless; // STRG + Shift + O = automatisches Importieren
import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.MessageProducer;
import javax.jms.Queue;
import javax.jms.Session;
import javax.jms.TextMessage;
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
	
	@Resource(lookup = "jms/JmsFactory")
	private ConnectionFactory cf;
	@Resource(lookup = "jms/BookQueue")
	private Queue queue;

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
	
	@GET
	@Path("send")
	@Produces(MediaType.APPLICATION_JSON)
	public void sendQueue( ) {
		try {
			Connection con = cf.createConnection();
			Session sess = 	con.createSession(false, Session.SESSION_TRANSACTED);	
			con.start();
			
			MessageProducer sender = sess.createProducer(queue);
			TextMessage msg = sess.createTextMessage();
			msg.setText("Machico Chino;Miniature Garden of Twindle;Tokyopop");
			sender.send(msg);
			
			sender.close();
			sess.close();
			con.close();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
}
