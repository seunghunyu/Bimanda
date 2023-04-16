package com.bit.bimanda.service;

import java.util.List;
import java.util.Map;

import com.bit.bimanda.vo.BoardVo;
import com.bit.bimanda.vo.ReviewBoardVo;


public interface BoardService {
	public boolean insert(BoardVo vo);
	public boolean insert(ReviewBoardVo rVo);
	
	public List<BoardVo> getList(String boardCategory);
	public List<ReviewBoardVo> getReviewList(String boardCategory);
	
	public BoardVo getBoard(Long boardNo);
	public ReviewBoardVo getReviewBoard(Long boardNo);
	
	public boolean update(BoardVo bVo);
	public boolean reviewupdate(ReviewBoardVo rVo);
	
	public boolean delete(Long boardNo);
	public boolean reviewdelete(Long boardNo);
	
	public boolean updatehit(Long boardNo);
	public boolean reviewupdatehit(Long boardNo);
}
