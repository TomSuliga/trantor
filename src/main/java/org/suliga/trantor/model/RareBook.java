package org.suliga.trantor.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="RARE_BOOKS")
public class RareBook {
	@Id
	@GeneratedValue
	private Long id;
	
	@Column(name="title", length=50, nullable=false, unique=true)
	private String title;
	
	@Column(name = "author", length=50)
	private String author;
	
	@Enumerated(EnumType.STRING)
	@Column(name = "book_condition", length=50)
	private Condition condition;
	
	@Column(name = "available")
	private boolean available;

	public RareBook() {}
	
	
	public RareBook(String title, String author, Condition condition, boolean available) {
		super();
		this.title = title;
		this.author = author;
		this.condition = condition;
		this.available = available;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public Condition getCondition() {
		return condition;
	}

	public void setCondition(Condition condition) {
		this.condition = condition;
	}

	public boolean isAvailable() {
		return available;
	}

	public void setAvailable(boolean available) {
		this.available = available;
	}
	
}
