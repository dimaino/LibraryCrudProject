package com.cognixia.jump.connection;

import com.cognixia.jump.dao.PatronDao;
import com.cognixia.jump.dao.PatronDaoImp;

public class TestStuuf {
public static void main(String[] args) {
	PatronDao pDAO = new PatronDaoImp();
	System.out.println(pDAO.getAllPatrons());
}
}
