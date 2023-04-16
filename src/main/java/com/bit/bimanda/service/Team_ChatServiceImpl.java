package com.bit.bimanda.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bit.bimanda.repository.Team_ChatDao;
import com.bit.bimanda.vo.Team_ChatVo;


@Service
public class Team_ChatServiceImpl implements Team_ChatService{
	@Autowired
	Team_ChatDao team_ChatDaoImpl;
	@Override
	public boolean insert(long teamNo, Long userNo, String userId, String chatContent) {
		// TODO Auto-generated method stub
		int insertCount = team_ChatDaoImpl.insert(teamNo,userNo, userId,chatContent);
		return 1==insertCount;
	}

	@Override
	public List<Team_ChatVo> getList(long teamNo) {
		// TODO Auto-generated method stub
		return team_ChatDaoImpl.getList(teamNo);
	}

	

}
