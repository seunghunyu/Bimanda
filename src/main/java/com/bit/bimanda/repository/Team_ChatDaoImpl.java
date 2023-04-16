package com.bit.bimanda.repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.bit.bimanda.vo.Team_ChatVo;

@Repository
public class Team_ChatDaoImpl implements Team_ChatDao{
	@Autowired
	SqlSession sqlSession;
	@Override
	public int insert(long teamNo, Long userNo, String userId, String chatContent) {
		// TODO Auto-generated method stub
		int insertedCount = 0;
		Map<String, Object> map = new HashMap<>();
		map.put("teamNo", teamNo);
		map.put("userNo", userNo);
		map.put("userId", userId);
		map.put("chatContent",chatContent);
		insertedCount = sqlSession.insert("team_chat.insert", map);
		return insertedCount;
	}

	@Override
	public List<Team_ChatVo> getList(long teamNo) {
		// TODO Auto-generated method stub
		return sqlSession.selectList("team_chat.getList",teamNo);
	}
	
}
