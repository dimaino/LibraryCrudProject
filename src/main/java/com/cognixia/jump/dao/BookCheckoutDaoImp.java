package com.cognixia.jump.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import com.cognixia.jump.connection.ConnectionManager;
import com.cognixia.jump.model.Book;
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
	
	private static final String SELECT_CURRENT_CHECKEDOUTBOOKS= "SELECT * FROM book_checkout WHERE returned IS ?";//is null 
	private static final String SELECT_PAST_CHECKEDOUTBOOKS= "SELECT * FROM book_checkout WHERE returned IS NOT ?";//is not null
	private static final String SELECT_CHECKEDOUTBOOKS_BYDATE="SELECT * FROM book_checkout WHERE checkedout<?";
	private static final String SELECT_CHECKEDOUTBOOKS_BETWEENDATE="SELECT * FROM book_checkout WHERE checkedout BETWEEN ? AND ?";
	
	//this is solely for checking the availability of books as well as popularity 
	private static final String SELECT_CURRENT_CHECKEDOUTBOOKS_BYPATRONID="SELECT * FROM book_checkout WHERE patron_id=? AND returned is ?";
	private static final String SELECT_PAST_CHECKEDOUTBOOKS_BYPATRONID="SELECT * FROM book_checkout WHERE patron_id=? AND returned IS NOT ?";
	private static final String SELECT_CURRENT_CHECKEDOUTBOOKS_BYISBN="SELECT * FROM book_checkout WHERE isbn=? AND returned IS ?";
	private static final String SELECT_PAST_CHECKEDOUTBOOKS_BYISBN="SELECT * FROM book_checkout WHERE isbn=? and returned IS NOT ?";

	private static final String SELECT_CURRENT_CHECKEDOUTBOOKS_BYCHECKOUTID="SELECT * FROM book_checkout WHERE checkout_id=?";
	
	private static final String ADD_BOOKCHECKOUT = "INSERT INTO book_checkout(patron_id,isbn,checkedout,due_date) VALUES (?,?,?,?)";
	private static final String DELETE_BOOKCHECKOUT = "DELETE FROM book_checkout WHERE checkout_id=?";
	
	
	private static final String CHECK_BOOK_AVAILABILTY = "SELECT * FROM book_checkout where isbn=?";
	
	private static final String UPDATE_DUEDATE = "UPDATE book_checkout SET due_date=? WHERE checkout_id=?";
	
	private static final String UPDATE_RETURN_DATE = "UPDATE book_checkout SET returned=? WHERE checkout_id=?";
	
	
	private static final String DELETE_CHECKOUT_BY_PATRON = "DELETE FROM book_checkout WHERE patron_id = ?";
	
	
//	private static final String SELECT_CURRENT_CHECKEDOUTBOOKS_BYPATRONID = "SELECT * FROM book_checkout JOIN book ON book_checkout.isbn = book.isbn WHERE patron_id = ? AND returned = ?";
	
	//add update,delete and add functions later 
	
	
	//This function checks if the book can be checked out or not 
	boolean isBookCheckable(String isbn) {
		ResultSet rs=null;
	
		try(PreparedStatement pstmt = conn.prepareStatement(CHECK_BOOK_AVAILABILTY))
		{
			pstmt.setString(1, isbn);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				if (rs.getDate("returned") ==null) {
					System.out.println("Sorry the book cannot be checked out right now, it has been checked out");
					return false;
					
				}
				
				
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
		
		return true; 
		
	}
	
	@Override
	public List<BookCheckout> getAllCurrentBookCheckouts() {
		
		List<BookCheckout> list = new ArrayList<BookCheckout>();
		ResultSet rs = null;
		
		try(PreparedStatement pstmt = conn.prepareStatement(SELECT_CURRENT_CHECKEDOUTBOOKS))
		{
		
		pstmt.setDate(1, null);
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
		List<BookCheckout> list = new ArrayList<BookCheckout>();
		ResultSet rs = null;
		
		try(PreparedStatement pstmt = conn.prepareStatement(SELECT_PAST_CHECKEDOUTBOOKS))
		{
			
		//java.sql.Date date = java.sql.Date.valueOf("2020-10-27");
		
		
		LocalDate locDate = LocalDate.now();
		//converting today's local date to SQL date for passing it as param in prepared statement 
		java.sql.Date SQLDate = java.sql.Date.valueOf(locDate);
		//pstmt.setDate(1, SQLDate);
		pstmt.setDate(1, null);

		
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
	}

	@Override
	public List<BookCheckout> getAllBookCheckoutsByDate(java.sql.Date date) {
		List<BookCheckout> list = new ArrayList<BookCheckout>();
		ResultSet rs = null;
		
		try(PreparedStatement pstmt = conn.prepareStatement(SELECT_CHECKEDOUTBOOKS_BYDATE))
		{
			
		//java.sql.Date dateSQL = date;
		
		pstmt.setDate(1, date);
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
		
	}
	@Override
	public List<BookCheckout> getAllBookCheckoutsBetweenDate( java.sql.Date date1, java.sql.Date date2){
		
		List<BookCheckout> list = new ArrayList<BookCheckout>();
		ResultSet rs = null;
		
		try(PreparedStatement pstmt = conn.prepareStatement(SELECT_CHECKEDOUTBOOKS_BETWEENDATE))
		{
		
		pstmt.setDate(1,date1 );
		pstmt.setDate(2, date2);
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
		
	}

	@Override
	public List<BookCheckout> getAllCurrentCheckoutsByPatronId(int patronID) {
		List<BookCheckout> list = new ArrayList<BookCheckout>();
		ResultSet rs = null;
		
		try(PreparedStatement pstmt = conn.prepareStatement(SELECT_CURRENT_CHECKEDOUTBOOKS_BYPATRONID))
		{
		
		pstmt.setInt(1, patronID);
		pstmt.setDate(2,null);
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
//				Book book = (Book) rs.getObject("b");
				
//				list.add(new BookCheckout(checkout_id,patron_id,isbn,checkedout,due_date,returned,book));
				list.add(new BookCheckout(checkout_id,patron_id,isbn,checkedout,due_date,returned));
					
			}
			
			
			
			}
		catch(SQLException e){
			e.printStackTrace();
			
		}
		
		
		return list;
	}

	@Override
	public List<BookCheckout> getAllPastCheckoutsByPatronId(int patronID) {
		List<BookCheckout> list = new ArrayList<BookCheckout>();
		ResultSet rs = null;
		
		try(PreparedStatement pstmt = conn.prepareStatement( SELECT_PAST_CHECKEDOUTBOOKS_BYPATRONID))
		{
		
		pstmt.setInt(1, patronID);
		
		LocalDate locDate = LocalDate.now();
		java.sql.Date SQLDate = java.sql.Date.valueOf(locDate);
	    pstmt.setDate(2, SQLDate);
	    
	    pstmt.setDate(2, null);
	    
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
	}

	@Override
	public List<BookCheckout> getAllCurrentBookCheckoutsByISBN(String ISBN) {
		List<BookCheckout> list = new ArrayList<BookCheckout>();
		ResultSet rs = null;
		
		try(PreparedStatement pstmt = conn.prepareStatement(SELECT_CURRENT_CHECKEDOUTBOOKS_BYISBN))
		{
		
		pstmt.setString(1, ISBN);
		pstmt.setDate(2, null);
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
	}

	@Override
	public List<BookCheckout> getAllPastBookCheckoutsByISBN(String ISBN) {
		List<BookCheckout> list = new ArrayList<BookCheckout>();
		ResultSet rs = null;
		
		try(PreparedStatement pstmt = conn.prepareStatement(SELECT_PAST_CHECKEDOUTBOOKS_BYISBN))
		{
		
			
		
			pstmt.setString(1, ISBN);
			//Date date = new Date() //this will give the today's date 
			LocalDate locDate = LocalDate.now();
			java.sql.Date SQLDate = java.sql.Date.valueOf(locDate);
		    pstmt.setDate(2, SQLDate);
		    pstmt.setDate(2, null);
		    
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
	}

	@Override
	public BookCheckout getBookCheckoutByCheckoutId(int id) {
		
		BookCheckout book =null;
		ResultSet rs = null;
		try(PreparedStatement pstmt = conn.prepareStatement(SELECT_CURRENT_CHECKEDOUTBOOKS_BYCHECKOUTID))
		{
		
		
		pstmt.setInt(1, id);	
		rs = pstmt.executeQuery();
		if(rs.next()==false) {
			System.out.println("Sorry the checout id does not exist ");
			return book;
		}
		//rs.next();
			
			
			/*
			 *  private int checkout_id;
				private int patron_id;
				private String isbn;
				private Date checkedout;
				private Date due_date;
				private Date returned;
			 */
			
				
				int checkout_id = rs.getInt("checkout_id");
				int patron_id = rs.getInt("patron_id");
				String isbn =rs.getString("isbn");
				Date checkedout = rs.getDate("checkedout");
				Date due_date = rs.getDate("due_date");
				Date returned =rs.getDate("returned");
				
				book = new BookCheckout(checkout_id,patron_id,isbn,checkedout,due_date,returned);
					
			}
			
			
			
			
		catch(SQLException e){
			e.printStackTrace();
			
		}
		
		return book;
		
	}

	@Override
	public boolean addBookCheckout(BookCheckout bookCheckout) {
		
		//logic1 , book should be checkable that is returned field should have valid date 
		//logic 2, book's returned should be set to null automatically once this function return true
		//logic 3, we can hardcode the today's date within this function for checkedoutdate
		try(PreparedStatement pstmt = conn.prepareStatement(ADD_BOOKCHECKOUT))
		{
			/*
			 *  private int checkout_id;
				private int patron_id;
				private String isbn;
				private Date checkedout;
				private Date due_date;
				private Date returned;
				
				
			 */
			
			if(isBookCheckable(bookCheckout.getIsbn())) {
				
			
			
			pstmt.setInt(1, bookCheckout.getPatron_id());
			pstmt.setString(2, bookCheckout.getIsbn());
			pstmt.setDate(3, bookCheckout.getCheckedout());
			pstmt.setDate(4, bookCheckout.getDue_date());
			
			
			
			if(pstmt.executeUpdate() > 0) {
				//System.out.println("The bookcheckout with id "+bookCheckout.getCheckout_id()+"added");
				return true;
			}
			}
			else {
				System.out.println("sorry the book has been checked out , please visit again");
				return false;
			}
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		System.out.println("Sorry the add process was not successful");
		return false;
	}

	@Override
	public boolean deleteBookCheckout(int id) {
		try(PreparedStatement pstmt = conn.prepareStatement(DELETE_BOOKCHECKOUT))
		{
			pstmt.setInt(1, id);
			if(pstmt.executeUpdate()>0) {
				System.out.println("The bookcheckout with id "+id+"deleted");
				return true; 
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("Sorry the delete process was not successful");
		return false;
	}

	@Override
	public boolean updateBookCheckout(java.sql.Date date, int checkout_id) {
		try(PreparedStatement pstmt = conn.prepareStatement(UPDATE_DUEDATE))
		{
			pstmt.setDate(1, date);
			pstmt.setInt(2, checkout_id);
			if(pstmt.executeUpdate()>0) {
				System.out.println("The update on due date is successful !");
				return true; 
			}
		}catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		System.out.println("Sorry the update was not successful");
		return false;
	} //end of the updateBookCheckout()
	

//test as you go 
public static void main(String args[]) {
	BookCheckoutDaoImp obj = new BookCheckoutDaoImp();
	
	//List<BookCheckout> list = obj.getAllCurrentBookCheckouts(); //function 1 test 
	//List<BookCheckout> list = obj.getAllPastBookCheckouts(); //function 2 test
	/*
	LocalDate locDate = LocalDate.now();
	java.sql.Date SQLDate = java.sql.Date.valueOf(locDate);
	SQLDate = java.sql.Date.valueOf("2020-10-18");
	//throw date not found exception using custom exception
	List<BookCheckout> list = obj.getAllBookCheckoutsByDate(SQLDate); //function 3 test 
	*/
	
	/*
	java.sql.Date SQLDate1 = java.sql.Date.valueOf("2020-01-01");
	java.sql.Date SQLDate2 = java.sql.Date.valueOf("2020-10-27");
	List<BookCheckout> list = obj.getAllBookCheckoutsBetweenDate(SQLDate1,SQLDate2); //function 4 test 
	*/
	
	//List<BookCheckout> list = obj.getAllCurrentCheckoutsByPatronId(1); //function 5 test
	//List<BookCheckout> list = obj.getAllPastCheckoutsByPatronId(1); //function 6 test 
	
	//List<BookCheckout> list = obj.getAllCurrentBookCheckoutsByISBN("1234567893"); //function 7 test 
	List<BookCheckout> list = obj.getAllPastBookCheckoutsByISBN("1234567893"); //function 8 test 
	
	
	for(int i=0; i<list.size(); i++) {
		System.out.println("The checkout id is "+list.get(i).getCheckout_id());
		System.out.println("The patron id is "+list.get(i).getPatron_id());
		System.out.println("The ISBN is "+list.get(i).getIsbn());
		System.out.println("The checkout date id is "+list.get(i).getCheckedout());
		System.out.println("The due date id is "+list.get(i).getDue_date());
		System.out.println("The returned date is "+list.get(i).getReturned());
		
		System.out.println("-------------------------------------------------------");
	}
	
	   /* private int checkout_id;
		private int patron_id;
		private String isbn;
		private Date checkedout;
		private Date due_date;
		private Date returned;
		*/
	 
	BookCheckout bcheck = new BookCheckout(1,"1234567895",java.sql.Date.valueOf("2020-10-27"),java.sql.Date.valueOf("2020-11-04"));
	//obj.addBookCheckout(bcheck);
	obj.deleteBookCheckout(1);
	
	obj.updateBookCheckout(java.sql.Date.valueOf("2020-11-27"), 2);
	

	
	System.out.println("The following is the book listed by checkout Id"); //function 9 test 
	
	BookCheckout book = obj.getBookCheckoutByCheckoutId(2);
	
	System.out.println("The checkout id is "+book.getCheckout_id());
	System.out.println("The patron id is "+book.getPatron_id());
	System.out.println("The ISBN is "+book.getIsbn());
	System.out.println("The checkout date id is "+book.getCheckedout());
	System.out.println("The due date id is "+book.getDue_date());
	System.out.println("The returned date is "+book.getReturned());
	
	
	
}

@Override
public boolean returnBook(Date date, int checkout_id) {
	try(PreparedStatement pstmt = conn.prepareStatement(UPDATE_RETURN_DATE))
	{
		pstmt.setDate(1, date);
		pstmt.setInt(2, checkout_id);
		if(pstmt.executeUpdate()>0) {
			System.out.println("The update on due date is successful !");
			return true; 
		}
	}catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	System.out.println("Sorry the update was not successful");
	return false;
}

@Override
public boolean deleteCheckoutByPatron(int id) {
	try(PreparedStatement pstmt = conn.prepareStatement(DELETE_CHECKOUT_BY_PATRON))
	{
		pstmt.setInt(1, id);
		if(pstmt.executeUpdate() > 0) {
			return true; 
		}
		
	} catch (SQLException e) {
		e.printStackTrace();
	}
	return false;
}



}