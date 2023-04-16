package com.bit.bimanda.repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.bit.bimanda.vo.MentorVo;
import com.bit.bimanda.vo.TeamVo;
import com.bit.bimanda.vo.UserVo;

@Repository
public class TeamDaoImpl implements TeamDao {
	@Autowired
	SqlSession sqlSession;
	
	@Override
	public int createTeam(String teamName) {
		int insertedCount = 0;
		insertedCount = sqlSession.insert("team.insertTeam", teamName);
		return insertedCount;
	}
	@Override
	public Long getNo(String teamName) {
		Long no = sqlSession.selectOne("team.selectNo", teamName);
		return no;
	}
	@Override
	public int insertMem(Map<String, Long> map) {
		int insertedCount = 0;
		insertedCount = sqlSession.insert("team.insertMem", map);
		return insertedCount;
	}
	@Override
	public String getName(Long teamNo) {
		String teamName = sqlSession.selectOne("team.selectTname", teamNo);
		return teamName;
	}
	@Override
	public List<MentorVo> getMentorList() {
		List<MentorVo> mlist = sqlSession.selectList("team.selectConfirmedMentor");
		return mlist;
	}
	@Override
	public int mentorReq(Map<String, Long> map) {
		int insertedCount = 0;
		insertedCount = sqlSession.insert("team.insertMentorReq", map);
		return insertedCount;
	}
	@Override
	public int updateMentor(Map<String, Long> map) {
		int updatedCount = 0;
		updatedCount = sqlSession.update("team.updateMentor", map);
		return updatedCount;
	}
	@Override
	public List<TeamVo> getTeamList(Long userNo) {
		List<TeamVo> tlist = sqlSession.selectList("team.teamList", userNo);
		return tlist;
	}
	@Override
	public List<UserVo> getTeamMemList(Long teamNo) {

		List<UserVo> tmlist = sqlSession.selectList("team.teamMemList",teamNo);
		
		return tmlist;
	}
	@Override
	public int leaveTeam(Map<String, Long> map) {
		int deletedCount = 0;
		deletedCount = sqlSession.delete("team.leaveTeam", map);
		return deletedCount;
	}
	
	
}
