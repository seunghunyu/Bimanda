package com.bit.bimanda.repository;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.bit.bimanda.vo.BoardVo;
import com.bit.bimanda.vo.ReviewBoardVo;

@Repository
public class BoardDaoImpl implements BoardDao{
	@Autowired
	SqlSession sqlSession;
	@Override
	public int insert(BoardVo vo) {
		// TODO Auto-generated method stub
		int insertedCount = 0;
		insertedCount = sqlSession.insert("board.insert", vo);
		return insertedCount;
	}
	@Override
	public int insert(ReviewBoardVo rVo) {
		// TODO Auto-generated method stub
		int insertedCount=0;
//		Map<String, Object> map = new HashMap<>();
//		map.put("bVo", bVo);
//		map.put("cateName", cateName);
		insertedCount = sqlSession.insert("board.reviewinsert", rVo);
		return insertedCount;
	}
	@Override
	public List<BoardVo> getJisik(String boardCategory) {
		// TODO Auto-generated method stub
		return sqlSession.selectList("board.getJisik",boardCategory);
	}
	@Override
	public List<ReviewBoardVo> getReviewList(String boardCategory) {
		// TODO Auto-generated method stub
		return sqlSession.selectList("board.getReviewList",boardCategory);
	}
	@Override
	public BoardVo getBoard(Long boardNo) {
		// TODO Auto-generated method stub
		return sqlSession.selectOne("board.getBoard", boardNo);
	}
	@Override
	public ReviewBoardVo getReviewBoard(Long boardNo) {
		// TODO Auto-generated method stub
		return sqlSession.selectOne("board.getReviewBoard", boardNo);
	}
	@Override
	public int update(BoardVo bVo) {
		int updatedCount = 0;
		updatedCount = sqlSession.update("board.update", bVo);
		return updatedCount;
	}
	@Override
	public int reviewupdate(ReviewBoardVo rVo) {
		int updatedCount = 0;
		updatedCount = sqlSession.update("board.reviewupdate", rVo);
		return updatedCount;
	}
	@Override
	public int delete(Long boardNo) {
		int deletedCount = 0;
		deletedCount = sqlSession.delete("board.delete", boardNo);
		return deletedCount;
	}
	@Override
	public int reviewdelete(Long boardNo) {
		int deletedCount = 0;
		deletedCount = sqlSession.delete("board.reviewdelete", boardNo);
		return deletedCount;
	}
	@Override
	public int updatehit(Long boardNo) {
		return sqlSession.update("board.updatehit", boardNo);
	}
	@Override
	public int reviewupdatehit(Long boardNo) {
		return sqlSession.update("board.reviewupdatehit", boardNo);
	}
}
