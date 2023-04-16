package com.bit.bimanda.repository;

import java.util.List;

import com.bit.bimanda.vo.RecruitVo;

public interface RecruitDao {
	public int insert(RecruitVo vo);		// 팀모집글 insert
	public List<RecruitVo> getList();		// 팀모집글 리스트 불러오기
	public RecruitVo detail(Long no);		// 팀모집글 리스트에서 제목 클릭 시 내용 불러오기
	public int update(RecruitVo vo);		// 팀모집글 수정
	public int delete(Long no);				// 팀모집글 삭제
	public int updatehit(Long no);			// 조회수 올리기
}
