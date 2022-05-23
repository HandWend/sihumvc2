package mapper;

import java.sql.*;
import java.util.ArrayList;
import java.util.Collection;

import domain.BoardVO;

public class ListMapper {

	public Collection<BoardVO> read() {
		//DB불러오기
		String url = "jdbc:mysql://localhost:3306/smart?characterEncoding=UTF-8&serverTimezone=Asia/Seoul";
		String user = "root";
		String password = "smart";
		String sql = " SELECT * FROM board ORDER BY num DESC ";
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		
		
		ArrayList<BoardVO> list = new ArrayList<BoardVO>();
		BoardVO vo = null;
		
		try{
			Class.forName("com.mysql.cj.jdbc.Driver");
			
			conn = DriverManager.getConnection(url, user, password);
			stmt = conn.prepareStatement(sql);

			rs = stmt.executeQuery();
			//rs의 내용을 한 건씩 들고 오겠다.
			while (rs.next()) {
				vo = new BoardVO();
				vo.setNum(rs.getInt("num"));
				vo.setTitle(rs.getString("title"));
				vo.setWriter(rs.getString("writer"));
				//vo 자체에 timestamp
				vo.setWriterDate(rs.getTimestamp("writerDate"));
				list.add(vo);
				
			}
		} catch (Exception e){
			e.getLocalizedMessage();
		} finally {
			try{
				if(rs != null) rs.close();
				if(stmt != null) stmt.close();
				if(conn != null) conn.close();
			} catch(Exception e){
				e.getLocalizedMessage();
			}
		}
		//controller로 돌아간다.
		return list;
	}

	public int totalRow() {
		//DB불러오기
				String url = "jdbc:mysql://localhost:3306/smart?characterEncoding=UTF-8&serverTimezone=Asia/Seoul";
				String user = "root";
				String password = "smart";
				String sql = " SELECT count(*) as cnt FROM board ORDER BY num DESC ";
				Connection conn = null;
				PreparedStatement stmt = null;
				ResultSet rs = null;
				
				
				int totalRow = 0;
				
				try{
					Class.forName("com.mysql.cj.jdbc.Driver");
					
					conn = DriverManager.getConnection(url, user, password);
					stmt = conn.prepareStatement(sql);

					rs = stmt.executeQuery();
					//rs의 내용을 한 건씩 들고 오겠다.
					if (rs.next()) {
						totalRow = rs.getInt("cnt");
						
					}
				} catch (Exception e){
					e.getLocalizedMessage();
				} finally {
					try{
						if(rs != null) rs.close();
						if(stmt != null) stmt.close();
						if(conn != null) conn.close();
					} catch(Exception e){
						e.getLocalizedMessage();
					}
				}
				//controller로 돌아간다.
				return totalRow;
	}

	// (1) read() Ctrl + C, V
	// (2) SQL문에 LIMIT ?, ? 추가
	public Collection<BoardVO> read(int startPage, int pageRow) {
		//DB불러오기
				String url = "jdbc:mysql://localhost:3306/smart?characterEncoding=UTF-8&serverTimezone=Asia/Seoul";
				String user = "root";
				String password = "smart";
				String sql = " SELECT * FROM board ORDER BY num DESC LIMIT ?, ?";
				Connection conn = null;
				PreparedStatement stmt = null;
				ResultSet rs = null;
				
				
				ArrayList<BoardVO> list = new ArrayList<BoardVO>();
				BoardVO vo = null;
				
				try{
					Class.forName("com.mysql.cj.jdbc.Driver");
					
					conn = DriverManager.getConnection(url, user, password);
					stmt = conn.prepareStatement(sql);
					
					// (3) ?에 들어갈 값 2개 setInt로 던져주기
					stmt.setInt(1, startPage);
					stmt.setInt(2, pageRow);
					
					rs = stmt.executeQuery();
					//rs의 내용을 한 건씩 들고 오겠다.
					while (rs.next()) {
						vo = new BoardVO();
						vo.setNum(rs.getInt("num"));
						vo.setTitle(rs.getString("title"));
						vo.setWriter(rs.getString("writer"));
						//vo 자체에 timestamp
						vo.setWriterDate(rs.getTimestamp("writerDate"));
						list.add(vo);
						
					}
				} catch (Exception e){
					e.getLocalizedMessage();
				} finally {
					try{
						if(rs != null) rs.close();
						if(stmt != null) stmt.close();
						if(conn != null) conn.close();
					} catch(Exception e){
						e.getLocalizedMessage();
					}
				}
				//controller로 돌아간다.
				return list;
		
	}

}
