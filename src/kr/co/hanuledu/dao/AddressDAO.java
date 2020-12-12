package kr.co.hanuledu.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import kr.co.hanuledu.dto.AddressDTO;
import kr.co.hanuledu.util.DBManager;

public class AddressDAO {
		private AddressDAO() {
			
		}
		
		private static AddressDAO instance = new AddressDAO();
		
		public static AddressDAO getInstance() {
			return instance;
		}
		
		public ArrayList<AddressDTO> selectAddressByDong(String dong) {
			ArrayList<AddressDTO> list = new ArrayList<>();
			
			String sql = "SELECT * FROM address WHERE dong LIKE '%'||?||'%'";
			
			Connection conn = null;
			PreparedStatement pstmt = null;
			ResultSet rs = null;
			
			try {
				conn = DBManager.getConnection();
				pstmt = conn.prepareStatement(sql);
				pstmt.setString(1, dong);
				rs = pstmt.executeQuery();
				
				while(rs.next()) {
					AddressDTO aDto = new AddressDTO();
					
					aDto.setZipNum(rs.getString("zip_num"));
					aDto.setSido(rs.getString("sido"));
					aDto.setGugun(rs.getString("gugun"));
					aDto.setDong(rs.getString("dong"));
					aDto.setZipCode(rs.getString("zip_code"));
					aDto.setBunji(rs.getString("bunji"));
					
					list.add(aDto);
				}
				
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				DBManager.close(conn, pstmt, rs);
			}
			
			return list;
		}
}
