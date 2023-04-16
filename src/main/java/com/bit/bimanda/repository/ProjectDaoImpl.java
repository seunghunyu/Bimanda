package com.bit.bimanda.repository;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.bit.bimanda.vo.ProjectVo;
@Repository
public class ProjectDaoImpl implements ProjectDao {
	@Autowired
	SqlSession sqlSession;
	
	@Override
	public ProjectVo select(Long teamNo) {
		return sqlSession.selectOne("project.select",teamNo);
	}

	@Override
	public int insert(ProjectVo vo) {
		return sqlSession.insert("project.insert",vo);
	}

	@Override
	public int delete(Long projNo) {
		return sqlSession.delete("project.delete",projNo);
	}

	@Override
	public List<ProjectVo> selectList(Long teamNo) {
		// TODO Auto-generated method stub
		return sqlSession.selectList("project.selectList",teamNo);
	}

	@Override
	public ProjectVo selectProject(Long projNo) {
		// TODO Auto-generated method stub
		return sqlSession.selectOne("project.selectProject",projNo);
	}

}
