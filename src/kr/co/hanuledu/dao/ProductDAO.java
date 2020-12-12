
package kr.co.hanuledu.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import org.apache.jasper.tagplugins.jstl.core.ForEach;

import kr.co.hanuledu.dto.ProductDTO;
import kr.co.hanuledu.util.DBManager;

public class ProductDAO {

	String url = "jdbc:oracle:thin:@192.168.0.67:1521:xe";
	String user = "hanul";
	String password = "0000";
	
		private ProductDAO() {
			
		}
		
		private static ProductDAO instance = new ProductDAO();
		
		public static ProductDAO getInstance() {
			return instance;
		}
		
		
		// 신상품 리스트 취득
		public List<ProductDTO> listNewProduct() {
			List<ProductDTO> newProductList = new ArrayList<>(); 
			ResultSet rs=null;
			PreparedStatement pstmt=null;
			Connection conn=null;
			try {
			Class.forName("oracle.jdbc.driver.OracleDriver");

			conn = DriverManager.getConnection(url, user, password);
			String sql = "SELECT * "
				       + "FROM new_pro_view"; 
			pstmt = conn.prepareStatement(sql);
		
			
			rs = pstmt.executeQuery();
			
			while (rs.next()) {
				String p_code = rs.getString("p_code");
				String p_name = rs.getString("p_name");
				int p_price2 = rs.getInt("p_price2");
				String p_img = rs.getString("p_img");
									
				ProductDTO productDto = new ProductDTO();
				productDto.setP_code(p_code);
				productDto.setP_name(p_name);
				productDto.setP_price2(p_price2);
				productDto.setP_img(p_img);
				newProductList.add(productDto);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBManager.close(conn, pstmt, rs);
		}
		return newProductList;
		}
		//베스트상품 리스트 취득
		public List<ProductDTO> listBestProduct() {
			List<ProductDTO> bestProductList = new ArrayList<>(); 
			ResultSet rs=null;
			PreparedStatement pstmt=null;
			Connection conn=null;
			try {
				Class.forName("oracle.jdbc.driver.OracleDriver");

				conn = DriverManager.getConnection(url, user, password);
				String sql = "SELECT * "
					       + "FROM best_pro_view"; 
				pstmt = conn.prepareStatement(sql);
			
				
				rs = pstmt.executeQuery();
				
				while (rs.next()) {
					String p_code = rs.getString("p_code");
					String p_name = rs.getString("p_name");
					int p_price2 = rs.getInt("p_price2");
					String p_img = rs.getString("p_img");
										
					ProductDTO productDto = new ProductDTO();
					productDto.setP_code(p_code);
					productDto.setP_name(p_name);
					productDto.setP_price2(p_price2);
					productDto.setP_img(p_img);
					bestProductList.add(productDto);
				}
			
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				DBManager.close(conn, pstmt, rs);
			}
			return bestProductList;
			}
		
		public ProductDTO detailProduct(String pcode) {
			ProductDTO detailList = null; 
			ResultSet rs=null;
			PreparedStatement pstmt=null;
			Connection conn=null;
			try {
				Class.forName("oracle.jdbc.driver.OracleDriver");

				conn = DriverManager.getConnection(url, user, password);
				String sql = "SELECT * FROM product WHERE p_code = ? "; 
				pstmt = conn.prepareStatement(sql);
				pstmt.setString(1, pcode);				
				rs = pstmt.executeQuery();
				
				if(rs.next()) {
					String p_code = rs.getString("p_code");
					String p_name = rs.getString("p_name");
					String p_kind = rs.getString("p_kind");
					int p_price1 = rs.getInt("p_price1");
					int p_price2 = rs.getInt("p_price2");
					int p_price3 = rs.getInt("p_price3");
					String p_content = rs.getString("p_content");
					String p_img = rs.getString("p_img");
					String p_useyn = rs.getString("p_useyn");
					String p_bestyn = rs.getString("p_bestyn");
					Date p_indate = rs.getDate("p_indate");					
					detailList = new ProductDTO(p_code, p_name, p_kind, p_price1, p_price2, p_price3,p_content, p_img, p_useyn, p_bestyn, p_indate);
					
				}
			
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				DBManager.close(conn, pstmt, rs);
			}
			return detailList;
			}
		
}
