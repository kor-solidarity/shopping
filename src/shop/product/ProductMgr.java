package shop.product;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.servlet.http.HttpServletRequest;
import javax.sql.DataSource;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

import shop.member.ZiptabBean;
import shop.order.OrderBean;

public class ProductMgr {
	private Connection con;
	private PreparedStatement pst;
	private ResultSet rs;
	private DataSource ds;
	
	
	public ProductMgr() {
		try {
			Context context= new InitialContext();
			ds=(DataSource)context.lookup("java:comp/env/jdbc_maria");
		} catch (Exception e) {
			System.out.println("db 연결실패"+e);
		}
	}
	public ArrayList<ProductBean> getProductAll(){
		ArrayList<ProductBean> list =new ArrayList<>();
		String sql="select * from shop_product order by no desc";
		try {
			con=ds.getConnection();
			pst=con.prepareStatement(sql);
			rs=pst.executeQuery();
			while(rs.next()){
				ProductBean bean= new ProductBean();
				bean.setNo(rs.getInt("no"));
				bean.setName(rs.getString("name"));
				bean.setPrice(rs.getString("price"));
				bean.setDetail(rs.getString("detail"));
				bean.setSdate(rs.getString("sdate"));
				bean.setStock(rs.getString("stock"));
				bean.setImage(rs.getString("image"));
				list.add(bean);
			}
			
			
		} catch (Exception e) {
			System.out.println("getproductAll err"+e);
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
	public boolean insertProduct(HttpServletRequest request){
		boolean b=false;
		try {
			
			//upload할 이미지 경로(절대경로)
			String uploadDir="D:/websou/shopping/webContent/data";
			MultipartRequest multi=new MultipartRequest(request, uploadDir,5*1024*1024,"UTF-8",new DefaultFileRenamePolicy());
			System.out.println(multi.getParameter("name"));
			String sql="insert into shop_product(name,price,detail,sdate,stock,image) values(?,?,?,now(),?,?)";
			con=ds.getConnection();
			pst=con.prepareStatement(sql);
			pst.setString(1, multi.getParameter("name"));
			pst.setString(2, multi.getParameter("price"));
			pst.setString(3, multi.getParameter("detail"));
			pst.setString(4, multi.getParameter("stock"));
			if(multi.getFilesystemName("image")==null){
				//신상품 등록시 이미지를 선택하지 않은 경우
				pst.setString(5, "ready.gif");
			}else{
				pst.setString(5, multi.getFilesystemName("image"));
			}
			if(pst.executeUpdate()>0)b =true;
			
		} catch (Exception e) {
			System.out.println("insertProduct err"+e);
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
	public ProductBean getProduct(String no){
		ProductBean bean=null;
		String sql="select * from shop_product where no=?";
		try {
			con=ds.getConnection();
			pst=con.prepareStatement(sql);
			pst.setString(1,no);
			rs=pst.executeQuery();
			if(rs.next()){
				bean= new ProductBean();
				bean.setNo(rs.getInt("no"));
				bean.setName(rs.getString("name"));
				bean.setPrice(rs.getString("price"));
				bean.setDetail(rs.getString("detail"));
				bean.setSdate(rs.getString("sdate"));
				bean.setStock(rs.getString("stock"));
				bean.setImage(rs.getString("image"));
				
			}
			
			
		} catch (Exception e) {
			System.out.println("getproduct err"+e);
		}finally{
			try {
				if(rs!=null)rs.close();
				if(pst!=null)pst.close();
				if(con!=null)con.close();
			} catch (Exception e2) {
			}
		}
		
		return bean;
	}
	
	public boolean updateProduct(HttpServletRequest request){
		boolean b=false;
		
		try {
			String sql="";
			String uploadDir="D:/websou/shopping/webContent/data";
			MultipartRequest multi=new MultipartRequest(request, uploadDir,5*1024*1024,"UTF-8",new DefaultFileRenamePolicy());
			con=ds.getConnection();
			
			if(multi.getFilesystemName("image")==null){
				sql="update shop_product set name=?,price=?,detail=?,stock=? where no=?";
				pst=con.prepareStatement(sql);
				pst.setString(1, multi.getParameter("name"));
				pst.setString(2, multi.getParameter("price"));
				pst.setString(3, multi.getParameter("detail"));
				pst.setString(4, multi.getParameter("stock"));
				pst.setString(5, multi.getParameter("no"));
				
			}else{
				sql="update shop_product set name=?,price=?,detail=?,stock=?,image=? where no=?";
				pst=con.prepareStatement(sql);
				pst.setString(1, multi.getParameter("name"));
				pst.setString(2, multi.getParameter("price"));
				pst.setString(3, multi.getParameter("detail"));
				pst.setString(4, multi.getParameter("stock"));
				pst.setString(5, multi.getFilesystemName("image"));
				pst.setString(6, multi.getParameter("no"));
			}
			
			if(pst.executeUpdate()>0)b=true;
			
		} catch (Exception e) {
			System.out.println("updateproduct err"+e);
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
	public boolean deleteProduct(String no){
		boolean b=false;
		
		try {
			con=ds.getConnection();
			String sql="delete from shop_product where no=?";
			pst=con.prepareStatement(sql);
			pst.setString(1, no);
			if(pst.executeUpdate()>0)b=true;
			
		} catch (Exception e) {
			System.out.println("updateproduct err"+e);
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
	//고객이 상품 주문시 주문 수 만큼 재고에서 빼기자료
	public void reduceProduct(OrderBean order){
		try {
			con=ds.getConnection();
			String sql="update shop_product set stock=(stock-?) where no=?";
			pst=con.prepareStatement(sql);
			pst.setString(1, order.getQuantity());
			pst.setString(2, order.getProduct_no());
			pst.executeUpdate();
		
			
		} catch (Exception e) {
			System.out.println("updateproduct err"+e);
		}finally{
			try {
				if(rs!=null)rs.close();
				if(pst!=null)pst.close();
				if(con!=null)con.close();
			} catch (Exception e2) {
			}
		}
	}
}
