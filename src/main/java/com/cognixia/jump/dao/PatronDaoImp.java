package com.cognixia.jump.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.cognixia.jump.connection.ConnectionManager;
import com.cognixia.jump.model.Patron;

public class PatronDaoImp implements PatronDao {

	public static final Connection conn = ConnectionManager.getConnection();

	public enum SqlStatements {
		SELECT_ALL_PATRONS("SELECT * FROM patron"),
		SELECT_PATRON_BY_ID("SELECT * FROM patron WHERE patron_id = ?"),
		INSERT_PATRON("INSERT INTO patron(first_name, last_name, username, password, account_frozen) VALUES(?, ?, ?, ?, ?)"),
		DELETE_PATRON("DELETE FROM patron WHERE patron_id = ?"),
		UPDATE_PATRON("UPDATE patron SET first_name = ?, last_name = ?, username = ? WHERE patron_id = ?"),
		APPROVE_PATRON("UPDATE patron SET account_frozen = false WHERE patron_id = ?"),
		FREEZE_PATRON("UPDATE patron SET account_frozen = true WHERE patron_id = ?"),
		SELECT_PATRON_BY_USERNAME("SELECT * FROM patron WHERE username = ?");
		
		private final String statement;

		SqlStatements(final String statement) {
			this.statement = statement;
		}

		public String getStatement() {
			return statement;
		}
	}

	@Override
	public List<Patron> getAllPatrons() {
		List<Patron> allPatrons = new ArrayList<>();

		try (PreparedStatement pstmt = conn.prepareStatement(SqlStatements.SELECT_ALL_PATRONS.getStatement());
				ResultSet rs = pstmt.executeQuery();) {

			while (rs.next()) {

				allPatrons.add(new Patron(rs.getInt("patron_id"), rs.getString("first_name"), rs.getString("last_name"),
						rs.getString("username"), rs.getString("password"), rs.getBoolean("account_frozen")));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return allPatrons;
	}

	@Override
	public Patron getPatronById(int id) {
		Patron patron = null;

		try (PreparedStatement pstmt = conn.prepareStatement(SqlStatements.SELECT_PATRON_BY_ID.getStatement())) {

			pstmt.setInt(1, id);

			ResultSet rs = pstmt.executeQuery();

			if (rs.next()) {

				patron = new Patron(rs.getInt("patron_id"), rs.getString("first_name"), rs.getString("last_name"),
						rs.getString("username"), rs.getString("password"), rs.getBoolean("account_frozen"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return patron;
	}

	@Override
	public boolean addPatron(Patron patron) {

		try (PreparedStatement pstmt = conn.prepareStatement(SqlStatements.INSERT_PATRON.getStatement())) {

			pstmt.setString(1, patron.getFirst_name());
			pstmt.setString(2, patron.getLast_name());
			pstmt.setString(3, patron.getUsername());
			pstmt.setString(4, patron.getPassword());
			pstmt.setBoolean(5, true);

			if (pstmt.executeUpdate() > 0) {
				return true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return false;
	}

	@Override
	public boolean deletePatron(int id) {
		try (PreparedStatement pstmt = conn.prepareStatement(SqlStatements.DELETE_PATRON.getStatement())) {

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
	public boolean updatePatron(Patron patron) {
		try (PreparedStatement pstmt = conn.prepareStatement(SqlStatements.UPDATE_PATRON.getStatement())) {

			pstmt.setString(1, patron.getFirst_name());
			pstmt.setString(2, patron.getLast_name());
			pstmt.setString(3, patron.getUsername());
			pstmt.setInt(4, patron.getPatron_id());

			if (pstmt.executeUpdate() > 0) {
				return true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public boolean approveAccount(Patron patron) {
		try (PreparedStatement pstmt = conn.prepareStatement(SqlStatements.APPROVE_PATRON.getStatement())) {

			pstmt.setInt(1, patron.getPatron_id());

			if (pstmt.executeUpdate() > 0) {
				return true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public boolean freezeAccount(Patron patron) {
		try (PreparedStatement pstmt = conn.prepareStatement(SqlStatements.FREEZE_PATRON.getStatement())) {

			pstmt.setInt(1, patron.getPatron_id());

			if (pstmt.executeUpdate() > 0) {
				return true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public Patron getPatronLogin(String username) {
		try(PreparedStatement pstmt = conn.prepareStatement(SqlStatements.SELECT_PATRON_BY_USERNAME.getStatement());) {

			pstmt.setString(1, username);
			
			ResultSet rs = pstmt.executeQuery();
			
			if (rs.next()) {
				return new Patron(
						rs.getInt("patron_id"), 
						rs.getString("first_name"), 
						rs.getString("last_name"),
						rs.getString("username"), 
						rs.getString("password"), 
						rs.getBoolean("account_frozen")
				);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
}
