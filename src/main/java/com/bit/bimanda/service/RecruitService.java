package com.bit.bimanda.service;

import java.util.List;

import com.bit.bimanda.vo.RecruitVo;

public interface RecruitService {
	public boolean recruit(RecruitVo vo);	// 팀모집글 생성
	public List<RecruitVo> getList();		// 팀모집글 리스트
	public RecruitVo getDetail(Long no);	// 제목 클릭시 내용 불러오기
	public boolean update(RecruitVo vo);	// 팀모집글 수정
	public boolean delete(Long no);			// 팀모집글 삭제
	public boolean updatehit(Long no);		// 조회수 올리기
}
