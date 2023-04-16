package com.bit.bimanda.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bit.bimanda.repository.UserDao;
import com.bit.bimanda.vo.BoardVo;
import com.bit.bimanda.vo.CommentsVo;
import com.bit.bimanda.vo.LocationVo;
import com.bit.bimanda.vo.MentorRequestVo;
import com.bit.bimanda.vo.MentorVo;
import com.bit.bimanda.vo.RecruitVo;
import com.bit.bimanda.vo.RequestVo;
import com.bit.bimanda.vo.ReviewBoardVo;
import com.bit.bimanda.vo.UserVo;

@Service
public class UserServiceImpl implements UserService {
	@Autowired
	UserDao UserDaoImpl;
	@Override
	public boolean join(UserVo vo) {
		int joinCount = UserDaoImpl.insert(vo);
		return 1==joinCount;
	}
	@Override
	public UserVo getUser(String userId) {
		return UserDaoImpl.selectUser(userId);
	}
	@Override
	public UserVo getUser(String userId, String userPassword) {
		return UserDaoImpl.selectUser(userId, userPassword);
	}
	@Override
	public UserVo getAuthUser(String userName, String userPhone) {
		// TODO Auto-generated method stub
		return UserDaoImpl.selectAuthUser(userName, userPhone);
	}
	@Override
	public int delete(String userId, String userPassword) {
		// TODO Auto-generated method stub
		return UserDaoImpl.deleteUser(userId, userPassword);
	}
	@Override
	public int update(String userId,String userPassword,String userPhone) {
		// TODO Auto-generated method stub
		return UserDaoImpl.updateUser(userId, userPassword, userPhone);
	}
	@Override
	public List<LocationVo> selectLocation() {
		// TODO Auto-generated method stub
		return UserDaoImpl.selectLocation();
	}
	@Override
	public UserVo getUserIdName(Long userNo) {
		return UserDaoImpl.selectIdName(userNo);
	}
	@Override
	public boolean updateTeam(Map<String, Long> map) {
		int updatedCount = UserDaoImpl.updateTeam(map);
		return 1==updatedCount;
	}
	@Override
	public boolean insertMentor(MentorVo mentorVo) {
		int insertCount = UserDaoImpl.insertMentor(mentorVo);
		return 1==insertCount;
	}
	@Override
	public boolean updateMentor(Long userNo) {
		int updatedCount = UserDaoImpl.updateMentor(userNo);
		return 1==updatedCount;
	}
	@Override
	public List<MentorVo> getMentorList() {
		List<MentorVo> mlist = UserDaoImpl.getMentorList();
		return mlist;
	}
	@Override
	public boolean deleteMentor(Long userNo) {
		int deletedCount = UserDaoImpl.deleteMentor(userNo);
		return 1==deletedCount;
	}
	@Override
	public boolean confirmMentor(Long userNo) {
		int updatedCount = UserDaoImpl.confirmMentor(userNo);
		return 1==updatedCount;
	}
	@Override
	public boolean updateScore(Map<String, Long> map) {
		int updatedCount = UserDaoImpl.updateScore(map);
		return 1==updatedCount;
	}
	@Override
	public List<MentorRequestVo> getMenteeList(Long userNo) {
		List<MentorRequestVo> mrlist = UserDaoImpl.getMenteeList(userNo);
		return mrlist;
	}
	@Override
	public boolean deleteMentorReq(Map<String, Long> map) {
		int deletedCount = UserDaoImpl.deleteMentorReq(map);
		return 1==deletedCount;
	}
	@Override
	public int leaveTeam(String userId, String userPassword) {
		return UserDaoImpl.leaveTeam(userId, userPassword);
	}
	@Override
	public List<BoardVo> getmyboard(Long userNo) {
		List<BoardVo> blist = UserDaoImpl.getmyboard(userNo);
		return blist;
	}
	@Override
	public List<ReviewBoardVo> getmyreviews(Long userNo) {
		List<ReviewBoardVo> rvlist = UserDaoImpl.getmyreviews(userNo);
		return rvlist;
	}
	@Override
	public List<RecruitVo> getmyrecruit(Long userNo) {
		List<RecruitVo> rlist = UserDaoImpl.getmyrecruit(userNo);
		return rlist;
	}
	@Override
	public List<MentorVo> getmymentor(Long userNo) {
		List<MentorVo> mlist = UserDaoImpl.getmymentor(userNo);
		return mlist;
	}
	@Override
	public List<CommentsVo> getmycomments(Long userNo) {
		List<CommentsVo> clist = UserDaoImpl.getmycomments(userNo);
		return clist;
	}
	@Override
	public List<RequestVo> getmyrequest(Long userNo) {
		List<RequestVo> rqlist = UserDaoImpl.getmyrequest(userNo);
		return rqlist;
	}
}
