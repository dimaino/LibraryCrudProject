package com.cognixia.jump.model;

import java.sql.Date;

public class BookCheckout {

	private int checkout_id;
	private int patron_id;
	private String isbn;
	private Date checkedout;
	private Date due_date;
	private Date returned;
	private boolean overdue;
	
	private Book book;
	private Patron patron;
	
	public BookCheckout(int checkout_id, int patron_id, String isbn, Date checkedout, Date due_date, Date returned) {
		super();
		this.checkout_id = checkout_id;
		this.patron_id = patron_id;
		this.isbn = isbn;
		this.checkedout = checkedout;
		this.due_date = due_date;
		this.returned = returned;
		this.book = null;
		this.patron = null;
		this.overdue = false;
	}

	public BookCheckout(int checkout_id, int patron_id, String isbn, Date checkedout, Date due_date, Date returned, Book book, Patron patron, boolean overdue) {
		super();
		this.checkout_id = checkout_id;
		this.patron_id = patron_id;
		this.isbn = isbn;
		this.checkedout = checkedout;
		this.due_date = due_date;
		this.returned = returned;
		this.book = book;
		this.patron = patron;
		this.overdue = overdue;
	}

	public BookCheckout(int patron_id, String isbn, Date checkedout, Date due_date) {
		super();
		this.patron_id = patron_id;
		this.isbn = isbn;
		this.checkedout = checkedout;
		this.due_date = due_date;
	}

	public int getCheckout_id() {
		return checkout_id;
	}

	public void setCheckout_id(int checkout_id) {
		this.checkout_id = checkout_id;
	}

	public int getPatron_id() {
		return patron_id;
	}

	public void setPatron_id(int patron_id) {
		this.patron_id = patron_id;
	}

	public String getIsbn() {
		return isbn;
	}

	public void setIsbn(String isbn) {
		this.isbn = isbn;
	}

	public Date getCheckedout() {
		return checkedout;
	}

	public void setCheckedout(Date checkedout) {
		this.checkedout = checkedout;
	}

	public Date getDue_date() {
		return due_date;
	}

	public void setDue_date(Date due_date) {
		this.due_date = due_date;
	}

	public Date getReturned() {
		return returned;
	}

	public void setReturned(Date returned) {
		this.returned = returned;
	}
	
	public boolean isOverdue() {
		return overdue;
	}

	public void setOverdue(boolean overdue) {
		this.overdue = overdue;
	}

	public Book getBook() {
		return book;
	}

	public void setBook(Book book) {
		this.book = book;
	}	

	public Patron getPatron() {
		return patron;
	}

	public void setPatron(Patron patron) {
		this.patron = patron;
	}

	@Override
	public String toString() {
		return "BookCheckout [checkout_id=" + checkout_id + ", patron_id=" + patron_id + ", isbn=" + isbn
				+ ", checkedout=" + checkedout + ", due_date=" + due_date + ", returned=" + returned + "]";
	}
}
