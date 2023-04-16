package com.bit.bimanda.service;

import java.util.List;
import java.util.Map;

import com.bit.bimanda.vo.RequestVo;

public interface RequestService {
	public boolean request(RequestVo vo);		// 댓글 작성
	public List<RequestVo> getList(Long no);	// 댓글 리스트 불러오기
	public RequestVo getContent(Long reqNo);	// 댓글 수정 시 내용 불러오기
	
	// 해당 글에 해당 유저가 쓴 댓글이 있는지 확인
	public RequestVo getRequest(Map<String, Long> map);
	public boolean update(RequestVo vo);		// 댓글 수정
	public boolean delete(Long reqNo);			// 댓글 삭제
}
