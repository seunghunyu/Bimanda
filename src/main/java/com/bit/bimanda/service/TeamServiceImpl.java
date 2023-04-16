package com.bit.bimanda.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bit.bimanda.repository.TeamDao;
import com.bit.bimanda.vo.MentorVo;
import com.bit.bimanda.vo.TeamVo;
import com.bit.bimanda.vo.UserVo;

@Service
public class TeamServiceImpl implements TeamService {
	@Autowired
	TeamDao teamDaoImpl;

	@Override
	public boolean createTeam(String teamName) {
		int createCount = teamDaoImpl.createTeam(teamName);
		return 1==createCount;
	}
	@Override
	public Long getNo(String teamName) {
		Long no = teamDaoImpl.getNo(teamName);
		return no;
	}
	@Override
	public boolean insertMem(Map<String, Long> map) {
		int insertCount = teamDaoImpl.insertMem(map);
		return 1==insertCount;
	}
	@Override
	public String getName(Long teamNo) {
		String teamName = teamDaoImpl.getName(teamNo);
		return teamName;
	}
	
	@Override
	public List<MentorVo> getMentorList() {
		List<MentorVo> mlist = teamDaoImpl.getMentorList();
		return mlist;
	}
	@Override
	public boolean mentorReq(Map<String, Long> map) {
		int insertedCount = teamDaoImpl.mentorReq(map);
		return 1==insertedCount;
	}
	@Override
	public boolean updateMentor(Map<String, Long> map) {
		int updatedCount = teamDaoImpl.updateMentor(map);
		return 1==updatedCount;
	}
	@Override
	public List<TeamVo> getTeamList(Long userNo) {
		List<TeamVo> tlist = teamDaoImpl.getTeamList(userNo);
		return tlist;
	}
	@Override
	public List<UserVo> getTeamMemList(Long teamNo) {
		List<UserVo> tmlist = teamDaoImpl.getTeamMemList(teamNo);
		return tmlist;
	}
	@Override
	public boolean leaveTeam(Map<String, Long> map) {
		int deletedCount = teamDaoImpl.leaveTeam(map);
		return 1==deletedCount;
	}
}
