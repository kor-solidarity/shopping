package shop.member;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

public class MemberMgr {

	private Connection con;
	private PreparedStatement pst;
	private ResultSet rs;
	private DataSource ds;
	
	
	public MemberMgr() {
		try {
			Context context= new InitialContext();
			ds=(DataSource)context.lookup("java:comp/env/jdbc_maria");
		} catch (Exception e) {
			System.out.println("db 연결실패"+e);
		}
	}
	//우편번호 검색
	public ArrayList<ZiptabBean> zipcodeRead(String area3){
		ArrayList<ZiptabBean> list =new ArrayList<>();
		String sql="select * from ziptab where area3 like ?";
		try {
			con=ds.getConnection();
			pst=con.prepareStatement(sql);
			pst.setString(1, area3+"%");
			rs=pst.executeQuery();
			rs.next();
			while(rs.next()){
				ZiptabBean bean= new ZiptabBean();
				bean.setZipcode(rs.getString("zipcode"));
				bean.setArea1(rs.getString("area1"));
				bean.setArea2(rs.getString("area2"));
				bean.setArea3(rs.getString("area3"));
				bean.setArea4(rs.getString("area4"));
				list.add(bean);
			}
			
			
		} catch (Exception e) {
			System.out.println("zipcodeRead err"+e);
		}finally{
			try {
				if(rs!=null)rs.close();
				if(pst!=null)pst.close();
				if(con!=null)con.close();
			} catch (Exception e2) {
			}
		}
		return list;
		
	}
	public boolean checkId(String id){
		boolean b=false;
		String sql="select * from member where id= ?";
		try {
			con=ds.getConnection();
			pst=con.prepareStatement(sql);
			pst.setString(1, id);
			rs=pst.executeQuery();
			b=rs.next();
			
		} catch (Exception e) {
			System.out.println("zipcodeRead err"+e);
		}finally{
			try {
				if(rs!=null)rs.close();
				if(pst!=null)pst.close();
				if(con!=null)con.close();
			} catch (Exception e2) {
			}
		}
		return b;
	}

}
