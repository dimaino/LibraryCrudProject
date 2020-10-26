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
		SELECT_ALL_PATRONS("SELECT * FROM PATRON"),
		SELECT_PATRON_BY_ID("SELECT * FROM PATRON WHERE patron_id = ?"),
		INSERT_PATRON("INSERT INTO patron() VALUES(?, ?, ?)"),
		DELETE_PATRON("DELETE FROM patron WHERE patron_id = ?"),
		UPDATE_PATRON("UPDATE patron SET first_name = ?, last_name = ?, username = ?, password = ? WHERE patron_id = ?");
		
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
		
		try(PreparedStatement pstmt = conn.prepareStatement(SqlStatements.SELECT_ALL_PATRONS.getStatement());
			ResultSet rs = pstmt.executeQuery();) {
			
			while(rs.next()) {
				
				allPatrons.add(new Patron(
						rs.getInt("patron_id"),
						rs.getString("first_name"),
						rs.getString("last_name"),
						rs.getString("username"),
						rs.getString("password"),
						rs.getBoolean("account_frozen")
				));
			}
		} catch(SQLException e) {
			e.printStackTrace();
		}
		return allPatrons;
	}

	@Override
	public Patron getPatronById(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean addPatron(Patron patron) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean deletePatron(int id) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean updatePatron(Patron patron) {
		// TODO Auto-generated method stub
		return false;
	}

}
