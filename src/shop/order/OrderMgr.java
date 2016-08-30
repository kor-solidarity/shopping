package shop.order;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

public class OrderMgr {
	private Connection con;
	private PreparedStatement pst;
	private ResultSet rs;
	private DataSource ds;
	
	
	public OrderMgr() {
		try {
			Context context= new InitialContext();
			ds=(DataSource)context.lookup("java:comp/env/jdbc_maria");
		} catch (Exception e) {
			System.out.println("db 연결실패"+e);
		}
		
	}
	public void insertOrder(OrderBean orderBean){
		try {
			
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
}
