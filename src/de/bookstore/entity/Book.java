package de.bookstore.entity;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Book implements Serializable {

	private static final long serialVersionUID = -7115066071704921577L;

	@GeneratedValue
	@Id
	long id;
	
	private String author;
	private String title;
	private String publisher;
	
	public Book() {}
	
	public Book(String author,String title, String publisher) {
		this.author = author;
		this.title = title;
		this.publisher = publisher;
	}
	
	public long getId() {
		return this.id;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}
	
	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getPublisher() {
		return publisher;
	}

	public void setPublisher(String publisher) {
		this.publisher = publisher;
	}

	@Override
	public String toString() {
		return "Book [id=" + id + ", author=" + author + ", title=" + title + ", publisher=" + publisher + "]";
	}
	
}
