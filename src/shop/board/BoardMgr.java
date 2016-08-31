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


	public void totalList(){ //전체 레코드 건수
		String sql="select count(*) from board";
		try {
			con=ds.getConnection();
			pst=con.prepareStatement(sql);
			rs=pst.executeQuery();
			rs.next();
			tot=rs.getInt(1);
			
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
	
	public int getPageSu(){ //총페이지 수 구하기
		pageSu=tot/pList;
		if(tot%pList>0)pageSu++;	//자투리 페이지 처리
		return pageSu;
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
	
	public ArrayList<BoardDto> getDataAll(int page,String stype,String sword){
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
			for(int i=0;i<(page-1)*pList;i++){
				rs.next();
			}
			int k=0;
			while(rs.next()&&k<pList){
				BoardDto dto=new BoardDto();
				dto.setNum(rs.getInt("num"));
				dto.setName(rs.getString("name"));
				dto.setTitle(rs.getString("title"));
				dto.setBdate(rs.getString("bdate"));
				dto.setReadcnt(rs.getInt("readcnt"));
				dto.setNested(rs.getInt("nested"));
				list.add(dto);
				k++;
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
	public void updateReadcnt(String num){
		String sql="update board set readcnt=readcnt+1 where num=?";
		try {
			con=ds.getConnection();
			pst=con.prepareStatement(sql);
			pst.setString(1, num);
			pst.executeUpdate();
	
			
		} catch (Exception e) {
			System.out.println("readcnt err"+e);
		}finally{
			try {
				if(rs!=null)rs.close();
				if(pst!=null)pst.close();
				if(con!=null)con.close();
			} catch (Exception e2) {
			}
		}
	}
	public BoardDto getData(String num){
		String sql="select * from board where num=?";
		BoardDto dto=new BoardDto();
		try {
			con=ds.getConnection();
			pst=con.prepareStatement(sql);
			pst.setString(1, num);
			rs=pst.executeQuery();
			if(rs.next()){

				dto.setName(rs.getString("name"));
				dto.setPass(rs.getString("pass"));
				dto.setMail(rs.getString("mail"));
				dto.setTitle(rs.getString("title"));
				dto.setCont(rs.getString("cont"));
				dto.setBip(rs.getString("bip"));
				dto.setBdate(rs.getString("bdate"));
				dto.setReadcnt(rs.getInt("readcnt"));
				
			}
		} catch (Exception e) {
			System.out.println("get data err"+e);
		}finally{
			try {
				if(rs!=null)rs.close();
				if(pst!=null)pst.close();
				if(con!=null)con.close();
			} catch (Exception e2) {
			}
		}
		return dto;
		
	}
	public BoardDto getReplyData(String num) {
		String sql="select * from board where num=?";
		BoardDto dto=new BoardDto();
		try {
			con=ds.getConnection();
			pst=con.prepareStatement(sql);
			pst.setString(1, num);
			rs=pst.executeQuery();
			if(rs.next()){

				dto.setTitle(rs.getString("title"));
				dto.setGnum(rs.getInt("gnum"));
				dto.setOnum(rs.getInt("onum"));
				dto.setNested(rs.getInt("nested"));
				
			}
		} catch (Exception e) {
			System.out.println("get data err"+e);
		}finally{
			try {
				if(rs!=null)rs.close();
				if(pst!=null)pst.close();
				if(con!=null)con.close();
			} catch (Exception e2) {
			}
		}
		return dto;

	}
	public void updateOnum(int gnum,int onum) { //댓글용 onum 갱신
		//같은 그룹의 레코드는 모두 작업에 참여 - 같은 그룹의 onum값 변경
		//댓글의 onum은 이미 db에 있는 onum보다 크거나 같은 값을 변경
		String sql="update board set onum=onum+1 where onum>=? and gnum=?";
		try {
			con=ds.getConnection();
			pst=con.prepareStatement(sql);
			pst.setInt(1, onum);
			pst.setInt(2, gnum);
			
			int re=pst.executeUpdate();
			
		} catch (Exception e) {
			System.out.println("get data err"+e);
		}finally{
			try {
				if(rs!=null)rs.close();
				if(pst!=null)pst.close();
				if(con!=null)con.close();
			} catch (Exception e2) {
			}
		}
		

	}
	public void saveReply(BoardBean bean) { //댓글용 저장
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
			pst.setInt(11,bean.getOnum()); //onum
			pst.setInt(12,bean.getNested()); //nested
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
	public boolean checkPass(int num,String new_pass) {
		boolean b=false;
		String sql="select pass from board where num=?";
		try {
			con=ds.getConnection();
			pst=con.prepareStatement(sql);
			pst.setInt(1,num);
			rs=pst.executeQuery();
			if(rs.next()){
				if(new_pass.equals(rs.getString("pass")))b=true;
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
		
		return b;

	}
	
	public void saveEdit(BoardBean bean) {
		String sql="update board set name=?,mail=?,title=?,cont=? where num=?";
		try {
			con=ds.getConnection();
			pst=con.prepareStatement(sql);
			pst.setString(1,bean.getName());
			pst.setString(2,bean.getMail());
			pst.setString(3,bean.getTitle());
			pst.setString(4,bean.getCont());
			pst.setInt(5,bean.getNum());
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
	public void delData(String num){
		String sql="delete from board where num=?";
		try {
			con=ds.getConnection();
			pst=con.prepareStatement(sql);
			pst.setString(1, num);
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
}
