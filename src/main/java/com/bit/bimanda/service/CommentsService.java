package com.bit.bimanda.service;

import java.util.List;

import com.bit.bimanda.vo.CommentsVo;

public interface CommentsService {
	boolean insert(Long boardNo, Long userNo, String commentContent);
	List<CommentsVo> getList(Long boardNo);
	public CommentsVo getContent(Long commentNo);	// 댓글 수정 시 내용 불러오기
	public boolean update(CommentsVo cVo);		// 댓글 수정
	public boolean delete(Long commentsNo);		// 댓글 삭제
}
