package com.cognixia.jump.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.cognixia.jump.connection.ConnectionManager;
import com.cognixia.jump.model.Librarian;

public class LibrarianDaoImp implements LibrarianDao {

	public static final Connection conn = ConnectionManager.getConnection();

	public enum SqlStatements {
		SELECT_ALL_LIBRARIANS("SELECT * FROM librarian"),
		SELECT_LIBRARIAN_BY_ID("SELECT * FROM librarian WHERE librarian_id = ?"),
		INSERT_LIBRARIAN("INSERT INTO librarian(username, password) VALUES(?, ?)"),
		DELETE_LIBRARIAN("DELETE FROM librarian WHERE librarian_id = ?"),
		UPDATE_LIBRARIAN("UPDATE librarian SET username = ?, password = ? WHERE librarian_id = ?");

		private final String statement;

		SqlStatements(final String statement) {
			this.statement = statement;
		}

		public String getStatement() {
			return statement;
		}
	}

	@Override
	public List<Librarian> getAllLibrarians() {
		List<Librarian> allLibrarians = new ArrayList<>();

		try(PreparedStatement pstmt = conn.prepareStatement(SqlStatements.SELECT_ALL_LIBRARIANS.getStatement());
			ResultSet rs = pstmt.executeQuery();) {

			while (rs.next()) {

				allLibrarians.add(
						new Librarian(
								rs.getInt("librarian_id"), 
								rs.getString("username"), 
								rs.getString("password")
						)
				);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return allLibrarians;
	}

	@Override
	public Librarian getLibrarianById(int id) {
		Librarian librarian = null;

		try (PreparedStatement pstmt = conn.prepareStatement(SqlStatements.SELECT_LIBRARIAN_BY_ID.getStatement())) {

			pstmt.setInt(1, id);

			ResultSet rs = pstmt.executeQuery();

			if (rs.next()) {

				librarian = new Librarian(
						rs.getInt("librarian_id"), 
						rs.getString("username"),
						rs.getString("password")
				);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return librarian;
	}

	@Override
	public boolean addLibrarian(Librarian librarian) {

		try (PreparedStatement pstmt = conn.prepareStatement(SqlStatements.INSERT_LIBRARIAN.getStatement())) {

			pstmt.setString(1, librarian.getUsername());
			pstmt.setString(2, librarian.getPassword());

			if (pstmt.executeUpdate() > 0) {
				return true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return false;
	}

	@Override
	public boolean deleteLibrarian(int id) {
		try (PreparedStatement pstmt = conn.prepareStatement(SqlStatements.DELETE_LIBRARIAN.getStatement())) {

			pstmt.setInt(1, id);

			if (pstmt.executeUpdate() > 0) {
				return true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public boolean updateLibrarian(Librarian librarian) {
		try (PreparedStatement pstmt = conn.prepareStatement(SqlStatements.UPDATE_LIBRARIAN.getStatement())) {

			pstmt.setString(1, librarian.getUsername());
			pstmt.setString(2, librarian.getPassword());
			pstmt.setInt(3, librarian.getLibrarian_id());

			if (pstmt.executeUpdate() > 0) {
				return true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

}
