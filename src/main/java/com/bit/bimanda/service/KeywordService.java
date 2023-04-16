package com.bit.bimanda.service;

import java.util.List;

import com.bit.bimanda.vo.KeywordVo;

public interface KeywordService {
	public boolean insert(KeywordVo vo);
	public List<KeywordVo> getList(long userNo);
	public List<KeywordVo> getTeamKeyList(Long teamNo);
	public boolean insertTeamKeyword(KeywordVo vo);
	public boolean deleteTeamKeyword(KeywordVo vo);
	public boolean delete(KeywordVo vo);
}
