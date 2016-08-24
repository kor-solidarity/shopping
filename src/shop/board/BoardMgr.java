package shop.board;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;



public class BoardMgr {
	private Connection con;
	private PreparedStatement pst;
	private ResultSet rs;
	private DataSource ds;
	//paging 
	private int tot; //전체 레코드수
	private int pList=8;//페이지당 출력 행 수
	private int pageSu; //전체 페이지 수
	
	
	public BoardMgr(){
		try {
			Context context= new InitialContext();
			ds=(DataSource)context.lookup("java:comp/env/jdbc_maria");
		} catch (Exception e) {
			System.out.println("db 연결실패"+e);
		}
	}
	
	public int curruntGetNum(){ //최대 num 구하기
		String sql="select max(num) from board";
		int cnt=0;
		try {
			con=ds.getConnection();
			pst=con.prepareStatement(sql);
			rs=pst.executeQuery();
			if(rs.next())cnt=rs.getInt(1);
		} catch (Exception e) {
			System.out.println("getnum err"+e);
		}finally{
			try {
				if(rs!=null)rs.close();
				if(pst!=null)pst.close();
				if(con!=null)con.close();
			} catch (Exception e2) {
				// TODO: handle exception
			}
		}
		return cnt;
	}
	public void saveData(BoardBean bean){
		String sql="insert into board values(?,?,?,?,?,?,?,?,?,?,?,?)";
		try {
			con=ds.getConnection();
			pst=con.prepareStatement(sql);
			pst.setInt(1,bean.getNum());
			pst.setString(2, bean.getName());
			pst.setString(3, bean.getPass());
			pst.setString(4, bean.getMail());
			pst.setString(5, bean.getTitle());
			pst.setString(6, bean.getCont());
			pst.setString(7, bean.getBip());
			pst.setString(8, bean.getBdate());
			pst.setInt(9,0); //readcnt
			pst.setInt(10,bean.getGnum());
			pst.setInt(11,0); //onum
			pst.setInt(12,0); //nested
			pst.executeUpdate();
			
		} catch (Exception e) {
			System.out.println("save err"+e);
		}finally{
			try {
				if(rs!=null)rs.close();
				if(pst!=null)pst.close();
				if(con!=null)con.close();
			} catch (Exception e2) {
			}
		}
	}
	public ArrayList<BoardDto> getDataAll(){
		ArrayList<BoardDto> list=new ArrayList<>();
		String sql="select * from board order by gnum desc, onum asc";
		try {
			con=ds.getConnection();
			pst=con.prepareStatement(sql);
			rs=pst.executeQuery();
			while(rs.next()){
				BoardDto dto=new BoardDto();
				dto.setNum(rs.getInt("num"));
				dto.setName(rs.getString("name"));
				dto.setTitle(rs.getString("title"));
				dto.setBdate(rs.getString("bdate"));
				dto.setReadcnt(rs.getInt("readcnt"));
				dto.setNested(rs.getInt("nested"));
				list.add(dto);

			}
			
		} catch (Exception e) {
			System.out.println("save err"+e);
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
	
	public ArrayList<BoardDto> getDataAll(String stype,String sword){
		ArrayList<BoardDto> list=new ArrayList<>();
		String sql="select * from board";
		try {
			con=ds.getConnection();
			if(sword==null){
				sql+=" order by gnum desc,onum asc";
				pst=con.prepareStatement(sql);
			}else{
				sql+=" where "+stype+" like ?";
				sql+=" order by gnum desc, onum asc";
				
				pst=con.prepareStatement(sql);
				pst.setString(1, "%"+sword+"%");
			}
			rs=pst.executeQuery();
			while(rs.next()){
				BoardDto dto=new BoardDto();
				dto.setNum(rs.getInt("num"));
				dto.setName(rs.getString("name"));
				dto.setTitle(rs.getString("title"));
				dto.setBdate(rs.getString("bdate"));
				dto.setReadcnt(rs.getInt("readcnt"));
				dto.setNested(rs.getInt("nested"));
				list.add(dto);

			}
			
		} catch (Exception e) {
			System.out.println("save err"+e);
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
