package com.bit.bimanda.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bit.bimanda.repository.KeywordDao;
import com.bit.bimanda.vo.KeywordVo;



@Service
public class KeywordServiceImpl implements KeywordService{
	@Autowired
	KeywordDao KeywordDaoImpl;
	
	@Override
	public boolean insert(KeywordVo vo) {
		// TODO Auto-generated method stub
		int insertedCount = KeywordDaoImpl.insert(vo);
		return 1==insertedCount;
	}
	
	@Override
	public List<KeywordVo> getList(long userNo) {
		// TODO Auto-generated method stub
		return KeywordDaoImpl.getList(userNo);
	}

	@Override
	public List<KeywordVo> getTeamKeyList(Long teamNo) {
		// TODO Auto-generated method stub
		return KeywordDaoImpl.getTeamKeywordList(teamNo);
	}

	@Override
	public boolean insertTeamKeyword(KeywordVo vo) {
		// TODO Auto-generated method stub
		int insertedCount = KeywordDaoImpl.insertTeamKeyword(vo);
		return 1==insertedCount;
	}

	@Override
	public boolean deleteTeamKeyword(KeywordVo vo) {
		// TODO Auto-generated method stub
		int deletedCount = KeywordDaoImpl.deleteTeamKeyword(vo);
		return 1==deletedCount;

	}

	@Override
	public boolean delete(KeywordVo vo) {
		// TODO Auto-generated method stub
		int deletedCount = KeywordDaoImpl.deleteKeyword(vo);
		return 1==deletedCount;
	}

}
