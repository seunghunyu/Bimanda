package com.bit.bimanda.service;

import java.util.List;
import java.util.Map;

import com.bit.bimanda.vo.BoardVo;
import com.bit.bimanda.vo.CommentsVo;
import com.bit.bimanda.vo.LocationVo;
import com.bit.bimanda.vo.MentorRequestVo;
import com.bit.bimanda.vo.MentorVo;
import com.bit.bimanda.vo.RecruitVo;
import com.bit.bimanda.vo.RequestVo;
import com.bit.bimanda.vo.ReviewBoardVo;
import com.bit.bimanda.vo.UserVo;

public interface UserService {
	public boolean join(UserVo vo);
	public UserVo getUser(String userId);
	public UserVo getUser(String userId, String userPassword);
	public UserVo getAuthUser(String userName, String userPhone);
	public int delete(String userId,String userPassword);
	public int update(String userId,String userPassword,String userPhone);
	public List<LocationVo> selectLocation();
	public UserVo getUserIdName(Long userNo);		// userNo으로 Id, Name 불러오기
	public boolean updateTeam(Map<String, Long> map);	// user의 teamNo 변경
	public int leaveTeam(String userId,String userPassword);	// 팀 탈퇴
	
	public boolean insertMentor(MentorVo mentorVo);		// 멘토 신청 시 insert
	public boolean updateMentor(Long userNo);		// user 테이블의 userStatus update
	public boolean confirmMentor(Long userNo);		// 관리자가 승인한 mentor의 상태 변경
	public boolean updateScore(Map<String, Long> map); 	// 멘토 score update
	public boolean deleteMentor(Long userNo);		// 멘토 신청글 승인 혹은 거절 시 delete
	public List<MentorVo> getMentorList();		// 멘토 신청한 사람들 리스트 불러오기
	public List<MentorRequestVo> getMenteeList(Long userNo);	// 멘토찾기 신청한 사람들 리스트
	public boolean deleteMentorReq(Map<String, Long> map);	// 멘토 신청 거절
	
	public List<BoardVo> getmyboard(Long userNo);	// 내가 쓴 게시글 불러오기
	public List<ReviewBoardVo> getmyreviews(Long userNo);
	public List<RecruitVo> getmyrecruit(Long userNo);
	public List<MentorVo> getmymentor(Long userNo);
	public List<CommentsVo> getmycomments(Long userNo);
	public List<RequestVo> getmyrequest(Long userNo);
}
