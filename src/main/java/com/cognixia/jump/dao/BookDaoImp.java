package com.cognixia.jump.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.cognixia.jump.connection.ConnectionManager;
import com.cognixia.jump.dao.LibrarianDaoImp.SqlStatements;
import com.cognixia.jump.model.Book;
import com.cognixia.jump.model.Librarian;

public class BookDaoImp implements BookDao {
	
	public static final Connection conn = ConnectionManager.getConnection();

	public enum SqlStatements {
		SELECT_ALL_BOOKS("SELECT * FROM book"),
		SELECT_BOOK_BY_ID("SELECT * FROM book WHERE isbn = ?"),
		INSERT_BOOK("INSERT INTO book(isbn, title, descr, rented, added_to_library) VALUES(?, ?, ?, ?, ?)"),
		DELETE_BOOK("DELETE FROM book WHERE isbn = ?"),
		UPDATE_BOOK("UPDATE book SET title = ?, descr = ? WHERE isbn = ?"),
		RETURN_BOOK("UPDATE book SET rented = false WHERE isbn = ?"), // NEEDS TO WORK WITH CHECKOUTBOOKS TABLE
		CHECKOUT_BOOK("UPDATE book SET rented = true WHERE isbn = ?"); // NEEDS TO WORK WITH CHECKOUTBOOKS TABLE
		
		private final String statement;

		SqlStatements(final String statement) {
			this.statement = statement;
		}

		public String getStatement() {
			return statement;
		}
	}

	@Override
	public List<Book> getAllBooks() {
		List<Book> allBooks = new ArrayList<>();

		try(PreparedStatement pstmt = conn.prepareStatement(SqlStatements.SELECT_ALL_BOOKS.getStatement());
			ResultSet rs = pstmt.executeQuery();) {

			while (rs.next()) {
				
				allBooks.add(
						new Book(
								rs.getString("isbn"),
								rs.getString("title"),
								rs.getString("descr"),
								rs.getBoolean("rented"),
								rs.getDate("added_to_library")
						)
				);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return allBooks;
	}

	@Override
	public Book getBookByISBN(String isbn) {
		Book book = null;

		try (PreparedStatement pstmt = conn.prepareStatement(SqlStatements.SELECT_BOOK_BY_ID.getStatement())) {

			pstmt.setString(1, isbn);

			ResultSet rs = pstmt.executeQuery();

			if (rs.next()) {

				book = new Book(
						rs.getString("isbn"),
						rs.getString("title"),
						rs.getString("descr"),
						rs.getBoolean("rented"),
						rs.getDate("added_to_library")
				);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return book;
	}

	@Override
	public boolean addBook(Book book) {
		
		try (PreparedStatement pstmt = conn.prepareStatement(SqlStatements.INSERT_BOOK.getStatement())) {

			pstmt.setString(1, book.getIsbn());
			pstmt.setString(2, book.getTitle());
			pstmt.setString(3, book.getDescr());
			pstmt.setBoolean(4, false);
			pstmt.setDate(5, new Date(System.currentTimeMillis()));
			

			if (pstmt.executeUpdate() > 0) {
				return true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return false;
	}

	@Override
	public boolean deleteBook(String isbn) {
		try (PreparedStatement pstmt = conn.prepareStatement(SqlStatements.DELETE_BOOK.getStatement())) {

			pstmt.setString(1, isbn);

			if (pstmt.executeUpdate() > 0) {
				return true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public boolean updateBook(Book book) {
		try (PreparedStatement pstmt = conn.prepareStatement(SqlStatements.UPDATE_BOOK.getStatement())) {

			pstmt.setString(1, book.getTitle());
			pstmt.setString(2, book.getDescr());
			pstmt.setString(3, book.getIsbn());

			if (pstmt.executeUpdate() > 0) {
				return true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public boolean returnBook(Book book) {
		try (PreparedStatement pstmt = conn.prepareStatement(SqlStatements.RETURN_BOOK.getStatement())) {

			pstmt.setString(1, book.getIsbn());

			if (pstmt.executeUpdate() > 0) {
				return true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public boolean checkoutBook(Book book) {
		try (PreparedStatement pstmt = conn.prepareStatement(SqlStatements.CHECKOUT_BOOK.getStatement())) {

			pstmt.setString(1, book.getIsbn());

			if (pstmt.executeUpdate() > 0) {
				return true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}
	

}
