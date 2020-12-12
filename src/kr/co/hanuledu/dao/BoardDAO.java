package kr.co.hanuledu.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import kr.co.hanuledu.dto.BoardDTO;
import kr.co.hanuledu.mybatis.SqlMapConfig;

public class BoardDAO {
	
	private static BoardDAO instance = new BoardDAO();
	
	public static BoardDAO getInstance() {
		return instance;
	}
	
	//MyBatis 셋팅 값 불러오기
	SqlSessionFactory sqlSessionFactory = SqlMapConfig.getSqlSession();
	
	// mapper에 접근하기 위한 SqlSession
	SqlSession sqlSession;
	
	// board 테이블에 저장된 전체글 취득 
/*	public List<BoardDTO> boardList() {
		// session 열기
		sqlSession = sqlSessionFactory.openSession();
		
		List<BoardDTO> boardList = null;
		
		try {
			//게시판 조회
			boardList = sqlSession.selectList("selBoardList");
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			sqlSession.close();
		}
		return boardList;
	}*/
	public List<BoardDTO> selAllBoardList(Map<String, Object> boardParmMap) {
		// session 열기
		sqlSession = sqlSessionFactory.openSession();
		
		List<BoardDTO> boardList = null;
		
		try {
			//게시판 조회
			boardList = sqlSession.selectList("selBoardList", boardParmMap);
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			sqlSession.close();
		}
		return boardList;
	}
	
	public int boardListAllCnt(Map<String, Object> boardParmMap) {
		sqlSession = sqlSessionFactory.openSession();
		
		int boardListAllCnt = 0;
		
		try {
			boardListAllCnt = sqlSession.selectOne("boardListAllCnt", boardParmMap);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			sqlSession.close();
		}
		
		return boardListAllCnt;
	}
	
	//글 상세 보기 
	public BoardDTO selBoardView(int articleNo) {
		
		sqlSession =sqlSessionFactory.openSession();
		
		BoardDTO selBoard = null;
		
		try {
			selBoard = sqlSession.selectOne("selectBoardView", articleNo);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			sqlSession.close();
		}
		return selBoard;
	}
	
	// 신규 글 등록시 폴더 생성용 articleNo 취득 
	public Integer getNewArticleNo() {
		// session 열기
		sqlSession = sqlSessionFactory.openSession();
		
		Integer articleNo = 0;
		
		try {
			articleNo = sqlSession.selectOne("getNewArticleNo");
		} catch (Exception e) {
			e.printStackTrace();
		}	finally {
			sqlSession.close();
		}
		return articleNo;
	}
	
	// board 테이블에 글 추가 
	public int create(BoardDTO bDto) {
		sqlSession = sqlSessionFactory.openSession();
		
		int result = 0;
		
		try {
			result = sqlSession.insert("createBoard", bDto);
			sqlSession.commit(); //DML문은 commit이 꼭 필요함. 
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			sqlSession.close();
		}
		return result;
	}
	// 글 수정 (Update문)
	public int modify(BoardDTO bDto) {
		sqlSession = sqlSessionFactory.openSession();
		
		int result = 0;
		
		try {
			result = sqlSession.update("modifyBoard", bDto);
			sqlSession.commit(); 
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			sqlSession.close();
		}
		return result;
	}
	
	// 글 삭제 (update문)
	public int delete(int articleNo) {
		sqlSession = sqlSessionFactory.openSession();
		
		int result = 0;
		
		try {
			result = sqlSession.update("deleteBoard", articleNo);
			sqlSession.commit(); 
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			sqlSession.close();
		}
		return result;
	}
	
	// 댓글 처리 
	public int replyReStepUpdate(BoardDTO bDto) {
		sqlSession = sqlSessionFactory.openSession();
		
		int result = 0;
		
		try {
			int maxsOrder = sqlSession.selectOne("selectBoardReplyMaxOrder", bDto);
			
			if (maxsOrder == 0 ) {
				int selectReStep = sqlSession.selectOne("selectReStep", bDto);
				int re_level  = bDto.getRe_level() + 1;
				
				bDto.setRe_step(selectReStep);
				bDto.setRe_level(re_level);
				
				result = sqlSession.insert("createBoard", bDto);
				sqlSession.commit();
			} else {
				sqlSession.update("replyReStepUpdate", bDto);
				bDto.setRe_step(bDto.getRe_step() + 1);
				int re_level = bDto.getRe_level() + 1;
				bDto.setRe_level(re_level);
				
				result = sqlSession.insert("createBoard", bDto);
				sqlSession.commit();
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			sqlSession.close();
		}
		
		return result;
	}
	
	// 조회수 증가 
	public void updateReadCnt(int articleNo) {
		sqlSession = sqlSessionFactory.openSession();
		
		try {
			sqlSession.update("updateReadCnt", articleNo);
			sqlSession.commit(); 
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			sqlSession.close();
		}
	
	}
	
	

	

	
	
}
