package com.bit.bimanda.repository;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.bit.bimanda.vo.KeywordVo;
import com.bit.bimanda.vo.LocationVo;

@Repository
public class KeywordDaoImpl implements KeywordDao{
	@Autowired
	SqlSession sqlSession;

	@Override
	public int insert(KeywordVo vo) {
		// TODO Auto-generated method stub
		int insertedCount = 0;
		insertedCount = sqlSession.insert("keyword.insert", vo);
		return insertedCount;
	}

	@Override
	public List<KeywordVo> getList(long userNo) {
		// TODO Auto-generated method stub
		List<KeywordVo> list=sqlSession.selectList("keyword.getList", userNo);
		return list;
	}

	@Override
	public List<KeywordVo> getTeamKeywordList(Long teamNo) {
		// TODO Auto-generated method stub
		List<KeywordVo> list=sqlSession.selectList("keyword.getTeamKeywordList", teamNo);
		return list;
	}

	@Override
	public int insertTeamKeyword(KeywordVo vo) {
		// TODO Auto-generated method stub
		int insertedCount = 0;
		insertedCount = sqlSession.insert("keyword.insertTeamKeyword", vo);
		return insertedCount;
	}

	@Override
	public int deleteTeamKeyword(KeywordVo vo) {
		// TODO Auto-generated method stub
		int deletedCount = 0;
		deletedCount = sqlSession.delete("keyword.deleteTeamKeyword", vo);
		return deletedCount;
	}

	@Override
	public int deleteKeyword(KeywordVo vo) {
		// TODO Auto-generated method stub
		int deletedCount = 0;
		deletedCount = sqlSession.delete("keyword.deleteKeyword", vo);
		return deletedCount;
	}
}
