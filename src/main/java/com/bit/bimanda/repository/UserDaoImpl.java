package com.bit.bimanda.repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.bit.bimanda.vo.BoardVo;
import com.bit.bimanda.vo.CommentsVo;
import com.bit.bimanda.vo.LocationVo;
import com.bit.bimanda.vo.MentorRequestVo;
import com.bit.bimanda.vo.MentorVo;
import com.bit.bimanda.vo.RecruitVo;
import com.bit.bimanda.vo.RequestVo;
import com.bit.bimanda.vo.ReviewBoardVo;
import com.bit.bimanda.vo.UserVo;

@Repository
public class UserDaoImpl implements UserDao {
	@Autowired
	SqlSession sqlSession;
	@Override
	public int insert(UserVo vo) {
		int insertedCount = 0;
		insertedCount = sqlSession.insert("user.insert", vo);
		return insertedCount;
	}

	@Override
	public UserVo selectUser(String userId, String userPassword) {
		Map<String, String> map = new HashMap<>();
		map.put("userId", userId);
		map.put("userPassword", userPassword);
		return sqlSession.selectOne("user.selectUser2", map);
	}

	@Override
	public UserVo selectUser(String userId) {
		return sqlSession.selectOne("user.selectUser", userId);
	}

	@Override
	public UserVo selectAuthUser(String userName, String userPhone) {
		// TODO Auto-generated method stub
		Map<String, String> map = new HashMap<>();
		map.put("userName", userName);
		map.put("userPhone", userPhone);
		return sqlSession.selectOne("user.selectAuthUser", map);
	}

	@Override
	public int deleteUser(String userId, String userPassword) {
		// TODO Auto-generated method stub
		Map<String, String> map = new HashMap<>();
		map.put("userId", userId);
		map.put("userPassword", userPassword);
		return sqlSession.delete("user.deleteUser", map);
	}
	public int updateUser(UserVo vo) {
		int updatedCount = 0;
		updatedCount = sqlSession.update("user.update", vo);
		return updatedCount;
		
	}

	@Override
	public int updateUser(String userId, String userPassword, String userPhone) {
		// TODO Auto-generated method stub
		int updatedCount = 0;
		Map<String, String> map = new HashMap<>();
		map.put("userId", userId);
		map.put("userPassword", userPassword);
		map.put("userPhone", userPhone);
		updatedCount = sqlSession.update("user.update", map);
		return updatedCount;
	}

	@Override
	public List<LocationVo> selectLocation() {
		// TODO Auto-generated method stub
		List<LocationVo> list=sqlSession.selectList("user.selectLocation");
		return list;
	}
	@Override
	public UserVo selectIdName(Long userNo) {
		return sqlSession.selectOne("user.selectIdName", userNo);
	}
	@Override
	public int updateTeam(Map<String, Long> map) {
		int updatedCount = 0;
		updatedCount = sqlSession.update("user.updateTeam", map);
		return updatedCount;
	}
	
	@Override
	public int insertMentor(MentorVo mentorVo) {
		int insertedCount = 0;
		insertedCount = sqlSession.insert("user.insertMentor", mentorVo);
		return insertedCount;
	}
	@Override
	public int updateMentor(Long userNo) {
		int updatedCount = 0;
		updatedCount = sqlSession.update("user.updateMentor", userNo);
		return updatedCount;
	}
	@Override
	public List<MentorVo> getMentorList() {
		List<MentorVo> mlist = sqlSession.selectList("user.selectAllMentor");
		return mlist;
	}
	@Override
	public int deleteMentor(Long userNo) {
		int deletedCount = 0;
		deletedCount = sqlSession.delete("user.deleteMentor", userNo);
		return deletedCount;
	}
	@Override
	public int confirmMentor(Long userNo) {
		int updatedCount = 0;
		updatedCount = sqlSession.update("user.confirmMentor", userNo);
		return updatedCount;
	}
	@Override
	public int updateScore(Map<String, Long> map) {
		int updatedCount = 0;
		updatedCount = sqlSession.update("user.updateMentorScore", map);
		return updatedCount;
	}
	@Override
	public List<MentorRequestVo> getMenteeList(Long userNo) {
		List<MentorRequestVo> mrlist = sqlSession.selectList("user.AllMenteeReq", userNo);
		return mrlist;
	}
	@Override
	public int deleteMentorReq(Map<String, Long> map) {
		int deletedCount = 0;
		deletedCount = sqlSession.delete("user.deleteMentorReq", map);
		return deletedCount;
	}

	@Override
	public int leaveTeam(String userId, String userPassword) {	// 팀 탈퇴
		Map<String, String> map = new HashMap<>();
		map.put("userId", userId);
		map.put("userPassword", userPassword);
		return sqlSession.update("user.leaveTeam", map);
	}
	
	
	@Override
	public List<BoardVo> getmyboard(Long userNo) {
		List<BoardVo> blist = sqlSession.selectList("user.myboard", userNo);
		return blist;
	}
	@Override
	public List<ReviewBoardVo> getmyreviews(Long userNo) {
		List<ReviewBoardVo> rvlist = sqlSession.selectList("user.myreviewboard", userNo);
		return rvlist;
	}
	@Override
	public List<RecruitVo> getmyrecruit(Long userNo) {
		List<RecruitVo> rlist = sqlSession.selectList("user.myrecruit", userNo);
		return rlist;
	}
	@Override
	public List<MentorVo> getmymentor(Long userNo) {
		List<MentorVo> mlist = sqlSession.selectList("user.mymentor", userNo);
		return mlist;
	}
	@Override
	public List<CommentsVo> getmycomments(Long userNo) {
		List<CommentsVo> clist = sqlSession.selectList("user.mycomments", userNo);
		return clist;
	}
	@Override
	public List<RequestVo> getmyrequest(Long userNo) {
		List<RequestVo> rqlist = sqlSession.selectList("user.myrequest", userNo);
		return rqlist;
	}
}
