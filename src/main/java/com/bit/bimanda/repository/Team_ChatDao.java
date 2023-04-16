package com.bit.bimanda.repository;

import java.util.List;

import com.bit.bimanda.vo.Team_ChatVo;

public interface Team_ChatDao {

	public int insert(long teamNo, Long userNo, String userId, String chatContent);
	public List<Team_ChatVo> getList(long teamNo);

}
