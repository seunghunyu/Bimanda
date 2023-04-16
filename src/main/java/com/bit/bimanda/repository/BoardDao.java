package com.bit.bimanda.repository;

import java.util.List;
import java.util.Map;

import com.bit.bimanda.vo.BoardVo;
import com.bit.bimanda.vo.ReviewBoardVo;

public interface BoardDao {
	public int insert(BoardVo vo);
	public int insert(ReviewBoardVo rVo);

	public List<BoardVo> getJisik(String boardCategory);
	public List<ReviewBoardVo> getReviewList(String boardCategory);

	public BoardVo getBoard(Long boardNo);
	public ReviewBoardVo getReviewBoard(Long boardNo);
	
	public int update(BoardVo bVo);
	public int reviewupdate(ReviewBoardVo rVo);
	
	public int delete(Long boardNo);
	public int reviewdelete(Long boardNo);
	
	public int updatehit(Long boardNo);
	public int reviewupdatehit(Long boardNo);
}
