package com.bit.bimanda.repository;

import java.util.List;
import java.util.Map;

import com.bit.bimanda.vo.RequestVo;

public interface RequestDao {
	public int insert(RequestVo vo);			// 댓글 insert
	public List<RequestVo> getList(Long no);	// 해당글의 댓글들 불러오기
	public RequestVo getContent(Long reqNo);	// 댓글 수정 시 내용 불러오기
	// 해당 모집글에 댓글을 쓴 적이 있는지 확인
	public RequestVo getRequest(Map<String, Long> map);
	public int update(RequestVo vo);			// 댓글 수정
	public int delete(Long reqNo);				// 댓글 삭제
}
