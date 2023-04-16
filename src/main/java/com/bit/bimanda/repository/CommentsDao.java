package com.bit.bimanda.repository;

import java.util.List;

import com.bit.bimanda.vo.CommentsVo;

public interface CommentsDao {
	int insert(Long boardNo, Long userNo, String commentContent);
	List<CommentsVo> getList(Long boardNo);
	public CommentsVo getContent(Long commentNo);	// 댓글 수정 시 내용 불러오기
	public int update(CommentsVo cVo);			// 댓글 수정
	public int delete(Long commentsNo);			// 댓글 삭제
}
