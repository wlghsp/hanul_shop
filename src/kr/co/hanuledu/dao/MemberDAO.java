package kr.co.hanuledu.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import kr.co.hanuledu.dto.MemberDTO;
import kr.co.hanuledu.mybatis.SqlMapConfig;
import kr.co.hanuledu.util.DBManager;

public class MemberDAO {
	String url = "jdbc:oracle:thin:@192.168.0.67:1521:xe";
	String user = "hanul";
	String password = "0000";

	private MemberDAO() {

	}

	private static MemberDAO instance = new MemberDAO();

	public static MemberDAO getInstance() {
		return instance;
	}
	
	//Mybatis 세팅값 불러오기
	SqlSessionFactory sqlSessionFactory = SqlMapConfig.getSqlSession();
	
	//mapper에 접근하기 위한 sqlsession
	SqlSession sqlSession;

	public List<MemberDTO> loginMember() {
		List<MemberDTO> memberList = new ArrayList<>();
		ResultSet rs = null;
		PreparedStatement pstmt = null;
		Connection conn = null;
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");

			conn = DriverManager.getConnection(url, user, password);
			String sql = "SELECT * " + "FROM member " + "WHERE id = 'hanul' " + "AND pwd = '0000'";
			pstmt = conn.prepareStatement(sql);
			// ps.setString(1, hanul);
			// ps.setString(2, );

			rs = pstmt.executeQuery();

			while (rs.next()) {
				String id = rs.getString("id");
				String pwd = rs.getString("pwd");
				String name = rs.getString("name");
				String email = rs.getString("email");
				String address = rs.getString("address");
				String phone = rs.getString("phone");
				String useyn = rs.getString("useyn");
				Date indate = rs.getDate("indate");
				String zip_num = rs.getString("zip_num");

				MemberDTO memberDto = new MemberDTO(id, pwd, name, email, address, phone, useyn, indate, zip_num);
				memberList.add(memberDto);
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBManager.close(conn, pstmt, rs);
		}
		return memberList;
	}
	

	public MemberDTO getMember(String id) {
		MemberDTO mDto = null;
		String sql ="SELECT * "
				+ "FROM member "
				+ "WHERE id=?";
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			conn = DBManager.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, id);
			
			rs = pstmt.executeQuery();
			
			if (rs.next()) {
				mDto = new MemberDTO();
				
				mDto.setId(rs.getString("id"));
				mDto.setPwd(rs.getString("pwd"));
				mDto.setName(rs.getString("name"));
				mDto.setEmail(rs.getString("email"));
				mDto.setZip_num(rs.getString("zip_num"));
				mDto.setAddress(rs.getString("address"));
				mDto.setPhone(rs.getString("phone"));
				mDto.setUseyn(rs.getString("useyn"));
				mDto.setIndate(rs.getDate("indate"));
			}
		} catch (Exception e) {
			// TODO: handle exception
		} finally {
			DBManager.close(conn, pstmt, rs);
		}
		return mDto;
	}
	// 회원 정보 취득
	
	// 회원 등록 INSERT
	// 회원 조회 
	// MyBatis 사용한 회원 조회
		public int confirmID(String userId) {
			// session 열기
			sqlSession = sqlSessionFactory.openSession();
			
			// 회원 유무 판별 변수
			String result = null;
			
			try {
				// 회원 조회 sql 
				result = sqlSession.selectOne("selIdChk", userId);
				
				if (result != null) {
					result = "1";
				} else {
					result = "-1";
				}
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				sqlSession.close();
			}
			
			return Integer.parseInt(result);
		}
	
	public void insertMember(MemberDTO mDto) {
		String sql="INSERT INTO member VALUES(?,?,?,?,?,?,'y',sysdate,?)";
		Connection conn = null;
		PreparedStatement pstmt = null;
		try {
			conn = DBManager.getConnection();
			pstmt= conn.prepareStatement(sql);
			pstmt.setString(1, mDto.getId());
			pstmt.setString(2, mDto.getPwd());
			pstmt.setString(3, mDto.getName());
			pstmt.setString(4, mDto.getEmail());
			pstmt.setString(5, mDto.getAddress());
			pstmt.setString(6, mDto.getPhone());
			pstmt.setString(7, mDto.getZip_num());
			
			int result = pstmt.executeUpdate();
			System.out.println("result ===> " + result);
			if (result > 0) {
				System.out.println("데이터 등록 성공");
			} else {
				System.out.println("데이터 등록 실패");
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBManager.close(conn, pstmt);
		}
		
	}
	
/*	public int confirmID(String userId) {
		// 회원 유무 판별 변수
		int result = -1;
		
		//회원 조회 sql 
		String sql = "SELECT * "
				+ "FROM member "
				+ "WHERE id=?";
		
		Connection conn= null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			conn = DBManager.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, userId);
			rs = pstmt.executeQuery();
			
			if (rs.next()) {
				result = 1;
			} else {
				result = -1;
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBManager.close(conn, pstmt, rs);
		}
		return result;
	}*/
	
	

}
