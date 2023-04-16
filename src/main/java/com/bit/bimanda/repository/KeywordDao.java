package com.bit.bimanda.repository;

import java.util.List;

import com.bit.bimanda.vo.KeywordVo;
import com.bit.bimanda.vo.TeamKeywordVo;

public interface KeywordDao {

	int insert(KeywordVo vo);

	List<KeywordVo> getList(long userNo);

	List<KeywordVo> getTeamKeywordList(Long teamNo);

	int insertTeamKeyword(KeywordVo vo);

	int deleteTeamKeyword(KeywordVo vo);

	int deleteKeyword(KeywordVo vo);

}
