package com.bit.bimanda.repository;

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

public interface UserDao {
	public int insert(UserVo vo);
	public UserVo selectAuthUser(String userName,String userPhone);
	public UserVo selectUser(String userId, String userPassword);
	public UserVo selectUser(String userId);
	public int deleteUser(String userId, String userPassword);
	public int updateUser(String userId, String userPassword, String userPhone);
	public List<LocationVo> selectLocation();
	public UserVo selectIdName(Long userNo);	// userNo로 Id, Name 불러오기
	public int updateTeam(Map<String, Long> map);	// user 정보에서 teamNo 변경하기
	public int leaveTeam(String userId, String userPassword);	// 팀 탈퇴하기
	
	public int insertMentor(MentorVo mentorVo);		// 멘토 신청 시 insert
	public int updateMentor(Long userNo);		// user 테이블의 userStatus update
	public int confirmMentor(Long userNo);		// 관리자가 승인한 mentor의 상태 변경
	public int updateScore(Map<String, Long> map); 	// mentor의 점수 update
	public int deleteMentor(Long userNo);		// 멘토 신청 승인 혹은 거절 시 delete
	public List<MentorVo> getMentorList();		// 멘토되기 신청한 사람들 리스트
	public List<MentorRequestVo> getMenteeList(Long userNo);	// 멘토찾기 신청한 사람들 리스트
	public int deleteMentorReq(Map<String, Long> map);	// 멘토 신청 거절
	
	public List<BoardVo> getmyboard(Long userNo);	// 내가 쓴 게시글 불러오기
	public List<ReviewBoardVo> getmyreviews(Long userNo);
	public List<RecruitVo> getmyrecruit(Long userNo);
	public List<MentorVo> getmymentor(Long userNo);
	public List<CommentsVo> getmycomments(Long userNo);
	public List<RequestVo> getmyrequest(Long userNo);
}
