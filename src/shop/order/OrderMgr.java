package shop.order;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

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
			con=ds.getConnection();
			String sql="insert into shop_order(product_no,quantity,sdate,state,id) values(?,?,now(),?,?)";
			pst=con.prepareStatement(sql);
			pst.setString(1, orderBean.getProduct_no());
			pst.setString(2, orderBean.getQuantity());
			pst.setString(3, "1");
			pst.setString(4, orderBean.getId());
			pst.executeUpdate();
			
		} catch (Exception e) {
			System.out.println("insertorder err"+e);
		}finally{
			try {
				if(rs!=null)rs.close();
				if(pst!=null)pst.close();
				if(con!=null)con.close();
			} catch (Exception e2) {
			}
		}
	}
	public ArrayList<OrderBean> getOrder(String id){
		ArrayList<OrderBean> list=new ArrayList<>();
		try {
			con=ds.getConnection();
			String sql="select * from shop_order where id=? order by no desc";
			pst=con.prepareStatement(sql);
			pst.setString(1, id);
			rs=pst.executeQuery();
			while(rs.next()){
				OrderBean bean=new OrderBean();
				bean.setNo(rs.getString("no"));
				bean.setProduct_no(rs.getString("product_no"));
				bean.setQuantity(rs.getString("quantity"));
				bean.setSdate(rs.getString("sdate"));
				bean.setState(rs.getString("state"));
				bean.setId(rs.getString("id"));
				list.add(bean);
			}
		} catch (Exception e) {
			System.out.println("getorder err"+e);
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
}
