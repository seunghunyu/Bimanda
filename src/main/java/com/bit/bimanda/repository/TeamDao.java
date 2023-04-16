package com.bit.bimanda.repository;

import java.util.List;
import java.util.Map;

import com.bit.bimanda.vo.MentorVo;
import com.bit.bimanda.vo.TeamVo;
import com.bit.bimanda.vo.UserVo;

public interface TeamDao {
	public int createTeam(String teamName);		// 팀테이블에 팀 추가
	public Long getNo(String teamName);			// teamName으로 teamNo 불러오기
	public String getName(Long teamNo);			// teamNo으로 teamName 불러오기
	public int insertMem(Map<String, Long> map);	// teamNo와 userNo으로 해당 팀의 팀원들 저장
	
	public int leaveTeam(Map<String, Long> map);	// 팀 탈퇴
	
	public List<MentorVo> getMentorList();		// 멘토 리스트 불러오기
	public int mentorReq(Map<String, Long> map);	// 멘토찾기 후 신청
	public int updateMentor(Map<String, Long> map);	// team 테이블의 멘토 update
	public List<TeamVo> getTeamList(Long userNo);	// 멘토가 속한 팀리스트 불러오기
	public List<UserVo> getTeamMemList(Long teamNo);	// 팀원 리스트 불러오기
}
