package com.bit.bimanda.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bit.bimanda.repository.BoardDao;
import com.bit.bimanda.vo.BoardVo;
import com.bit.bimanda.vo.ReviewBoardVo;

@Service
public class BoardServiceImpl implements BoardService{
	@Autowired
	BoardDao boardDaoImpl;
	@Override
	public boolean insert(BoardVo vo) {
		// TODO Auto-generated method stub
		int insertedCount = boardDaoImpl.insert(vo);
		return 1==insertedCount;
	}
	@Override
	public boolean insert(ReviewBoardVo rVo) {
		// TODO Auto-generated method stub
		int insertedCount = boardDaoImpl.insert(rVo);
		return 1==insertedCount;
	}
	@Override
	public List<BoardVo> getList(String boardCategory) {
		// TODO Auto-generated method stub
		return boardDaoImpl.getJisik(boardCategory);
	}
	@Override
	public List<ReviewBoardVo> getReviewList(String BoardCategory) {
		// TODO Auto-generated method stub
		return boardDaoImpl.getReviewList(BoardCategory);
	}
	@Override
	public BoardVo getBoard(Long boardNo) {
		// TODO Auto-generated method stub
		return boardDaoImpl.getBoard(boardNo);
	}
	@Override
	public ReviewBoardVo getReviewBoard(Long boardNo) {
		// TODO Auto-generated method stub
		return boardDaoImpl.getReviewBoard(boardNo);
	}
	@Override
	public boolean update(BoardVo bVo) {
		int updatedCount = boardDaoImpl.update(bVo);
		return 1==updatedCount;
	}
	@Override
	public boolean reviewupdate(ReviewBoardVo rVo) {
		int updatedCount = boardDaoImpl.reviewupdate(rVo);
		return 1==updatedCount;
	}
	@Override
	public boolean delete(Long boardNo) {
		int deletedCount = boardDaoImpl.delete(boardNo);
		return 1==deletedCount;
	}
	@Override
	public boolean reviewdelete(Long boardNo) {
		int deletedCount = boardDaoImpl.reviewdelete(boardNo);
		return 1==deletedCount;
	}
	@Override
	public boolean updatehit(Long boardNo) {
		return 1==boardDaoImpl.updatehit(boardNo);
	}
	@Override
	public boolean reviewupdatehit(Long boardNo) {
		return 1==boardDaoImpl.reviewupdatehit(boardNo);
	}
}
