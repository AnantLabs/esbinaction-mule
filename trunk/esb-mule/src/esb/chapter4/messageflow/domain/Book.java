package esb.chapter4.messageflow.domain;

import java.io.Serializable;

public class Book implements Serializable {
	
	private static final long serialVersionUID = -3324996905858510986L;
	protected String title;
	protected String author;
	protected String isbn;
	
	public String getAuthor() {
		return author;
	}
	public void setAuthor(String author) {
		this.author = author;
	}
	public String getIsbn() {
		return isbn;
	}
	public void setIsbn(String isbn) {
		this.isbn = isbn;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
}
