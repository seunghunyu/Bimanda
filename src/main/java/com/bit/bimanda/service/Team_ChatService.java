package com.bit.bimanda.service;

import java.util.List;

import com.bit.bimanda.vo.Team_ChatVo;

public interface Team_ChatService {
	boolean insert(long teamNo, Long userNo, String userId, String chatContent);

	List<Team_ChatVo> getList(long teamNo);
}
