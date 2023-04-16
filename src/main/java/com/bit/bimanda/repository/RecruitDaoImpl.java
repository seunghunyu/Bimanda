package com.bit.bimanda.repository;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.bit.bimanda.vo.RecruitVo;

@Repository
public class RecruitDaoImpl implements RecruitDao {
	@Autowired
	SqlSession sqlSession;
	
	@Override
	public int insert(RecruitVo vo) {
		int insertedCount = 0;
		insertedCount = sqlSession.insert("recruit.insert", vo);
		return insertedCount;
	}
	@Override
	public List<RecruitVo> getList() {
		List<RecruitVo> list = sqlSession.selectList("recruit.selectAll");
		return list;
	}
	@Override
	public RecruitVo detail(Long no) {
		RecruitVo vo = sqlSession.selectOne("recruit.selectOne", no);
		return vo;
	}
	@Override
	public int update(RecruitVo vo) {
		int updatedCount = 0;
		updatedCount = sqlSession.update("recruit.update", vo);
		return updatedCount;
	}
	@Override
	public int delete(Long no) {
		int deletedCount = 0;
		deletedCount = sqlSession.delete("recruit.delete", no);
		return deletedCount;
	}
	@Override
	public int updatehit(Long no) {
		return sqlSession.update("recruit.updatehit", no);
	}
}
