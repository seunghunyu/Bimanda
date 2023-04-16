package com.bit.bimanda.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bit.bimanda.repository.ProjectDao;
import com.bit.bimanda.vo.ProjectVo;
@Service
public class ProjectServiceImpl implements ProjectService {
	@Autowired
	ProjectDao projectDaoImpl;
	@Override
	public ProjectVo select(Long teamNo) {
		return projectDaoImpl.select(teamNo);
	}

	@Override
	public boolean insert(ProjectVo vo) {
		return 1==projectDaoImpl.insert(vo);
	}

	@Override
	public boolean delete(Long projNo) {
		return 1==projectDaoImpl.delete(projNo);
	}

	@Override
	public List<ProjectVo> selectList(Long teamNo) {
		// TODO Auto-generated method stub
		return projectDaoImpl.selectList(teamNo);
	}

	@Override
	public ProjectVo getProject(Long projNo) {
		// TODO Auto-generated method stub
		return projectDaoImpl.selectProject(projNo);
	}

}
