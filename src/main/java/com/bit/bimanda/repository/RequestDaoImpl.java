package com.bit.bimanda.repository;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.bit.bimanda.vo.RequestVo;

@Repository
public class RequestDaoImpl implements RequestDao {
	@Autowired
	SqlSession sqlSession;
	
	@Override
	public int insert(RequestVo vo) {
		int insertedCount = 0;
		insertedCount = sqlSession.insert("request.insert", vo);
		return insertedCount;
	}
	@Override
	public List<RequestVo> getList(Long no) {
		List<RequestVo> list = sqlSession.selectList("request.selectAll", no);
		return list;
	}
	@Override
	public RequestVo getContent(Long reqNo) {
		RequestVo vo = sqlSession.selectOne("request.selectOne", reqNo);
		return vo;
	}
	@Override
	public RequestVo getRequest(Map<String, Long> map) {
		RequestVo vo = sqlSession.selectOne("request.checkRequest", map);
		return vo;
	}
	@Override
	public int update(RequestVo vo) {
		int updatedCount = 0;
		updatedCount = sqlSession.update("request.update", vo);
		return updatedCount;
	}
	@Override
	public int delete(Long reqNo) {
		int deletedCount = 0;
		deletedCount = sqlSession.delete("request.delete", reqNo);
		return deletedCount;
	}
}
