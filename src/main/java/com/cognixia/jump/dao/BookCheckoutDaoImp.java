package com.cognixia.jump.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.cognixia.jump.connection.ConnectionManager;
import com.cognixia.jump.model.BookCheckout;

/*
 * Things to take care of for separation of concerns or design choice (in terms of 
 * functionality and who calls which class ?)
 * The point of having a checkout table is to assist with the patron, book and (?librarian) 
 * tables (entities)
 * so technically The Patron will have its own function that gives back the books 
 * that are checked out 
 * 
 * look into : java.sql.Date and java.util.Date 
 * 
 * what role does checkout_id plays ? unique identifier for the checkout !!
 * 
 */
public class BookCheckoutDaoImp implements BookCheckoutDao {
	
	public static final Connection conn = ConnectionManager.getConnection();
	
	//have all the queries here for clarity to see what queries we are writing
	
	private static final String SELECT_CURRENT_CHECKEDOUTBOOKS= "select * from book_checkout where returned is null";//is null 
	private static final String SELECT_PAST_CHECKEDOUTBOOKS= "select * from book_checkout where returned=?";//is not null
	private static final String SELECT_CHECKEDOUTBOOKS_BYDATE="select * from book_checkout where date<?";
	private static final String SELECT_CHECKEDOUTBOOKS_BETWEENDATE="select * from book_checkout where date between ? and ?";
	
	//this is solely for checking the availability of books as well as popularity 
	private static final String SELECT_CURRENT_CHECKEDOUTBOOKS_BYPATRONID="select * from book_checkout where patron_id=? and returned=?";
	private static final String SELECT_PAST_CHECKEDOUTBOOKS_BYPATRONID="select * from book_checkout where patron_id=? and returned=?";
	private static final String SELECT_CURRENT_CHECKEDOUTBOOKS_BYISBN="select * from book_checkout where isbn=? and returned=?";
	private static final String SELECT_PAST_CHECKEDOUTBOOKS_BYISBN="select * from book_checkout where isbn=? and returned=?";

	private static final String SELECT_CURRENT_CHECKEDOUTBOOKS_BYCHECKOUTID="select * from book_checkout where checkout_id=?";

	
	//add update,delete and add functions later 
	
	@Override
	public List<BookCheckout> getAllCurrentBookCheckouts() {
		
		List<BookCheckout> list = new ArrayList<BookCheckout>();
		ResultSet rs = null;
		
		try(PreparedStatement pstmt = conn.prepareStatement(SELECT_CURRENT_CHECKEDOUTBOOKS))
		{
		
		
		rs = pstmt.executeQuery();
			
			
			/*
			 *  private int checkout_id;
				private int patron_id;
				private String isbn;
				private Date checkedout;
				private Date due_date;
				private Date returned;
			 */
			while(rs.next()) {
				
				int checkout_id = rs.getInt("checkout_id");
				int patron_id = rs.getInt("patron_id");
				String isbn =rs.getString("isbn");
				Date checkedout = rs.getDate("checkedout");
				Date due_date = rs.getDate("due_date");
				Date returned =rs.getDate("returned");
				
				list.add(new BookCheckout(checkout_id,patron_id,isbn,checkedout,due_date,returned));
					
			}
			
			
			
			}
		catch(SQLException e){
			e.printStackTrace();
			
		}
		
		
		return list;
	} //end of the function 

	@Override
	public List<BookCheckout> getAllPastBookCheckouts() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<BookCheckout> getAllBookCheckoutsByDate(Date date) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public List<BookCheckout> getAllBookCheckoutsBetweenDate( Date date1, Date date2){
		
		return null;
		
	}

	@Override
	public List<BookCheckout> getAllCurrentCheckoutsByPatronId(int patronID) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<BookCheckout> getAllPastCheckoutsByPatronId(int patronID) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<BookCheckout> getAllCurrentBookCheckoutsByISBN(String ISBN) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<BookCheckout> getAllPastBookCheckoutsByISBN(String ISBN) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public BookCheckout getBookCheckoutByCheckoutId(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean addBookCheckout(BookCheckout bookCheckout) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean deleteBookCheckout(int id) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean updateBookCheckout(BookCheckout bookCheckout) {
		// TODO Auto-generated method stub
		return false;
	}
	

//test as you go 
public static void main(String args[]) {
	BookCheckoutDaoImp obj = new BookCheckoutDaoImp();
	List<BookCheckout> list = obj.getAllCurrentBookCheckouts();
	
	/*
	 *  private int checkout_id;
		private int patron_id;
		private String isbn;
		private Date checkedout;
		private Date due_date;
		private Date returned;
	 */
	
	for(int i=0; i<list.size(); i++) {
		System.out.println("The checkout id is "+list.get(i).getCheckout_id());
		System.out.println("The patron id is "+list.get(i).getPatron_id());
		System.out.println("The ISBN is "+list.get(i).getIsbn());
		System.out.println("The checkout date id is "+list.get(i).getCheckedout());
		System.out.println("The due date id is "+list.get(i).getDue_date());
		System.out.println("The returned date is "+list.get(i).getReturned());
		
		System.out.println("-------------------------------------------------------");
	}
	
	
	
	
	
}

	@Override
	public List<BookCheckout> viewPastCheckedOutBooks() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<BookCheckout> viewCurrentCheckedOutBooks() {
		// TODO Auto-generated method stub
		return null;
	}

}
