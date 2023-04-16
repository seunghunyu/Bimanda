package com.bit.bimanda.repository;

import java.util.List;

import com.bit.bimanda.vo.ProjectVo;

public interface ProjectDao {
	public ProjectVo select(Long teamNo);
	public int insert(ProjectVo vo);
	public int delete(Long projNo);
	public List<ProjectVo> selectList(Long teamNo);
	public ProjectVo selectProject(Long projNo);
}
