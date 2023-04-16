package com.bit.bimanda.service;

import java.util.List;

import com.bit.bimanda.vo.ProjectVo;

public interface ProjectService {
	public ProjectVo select(Long teamNo);
	public boolean insert(ProjectVo vo);
	public boolean delete(Long projNo);
	public List<ProjectVo> selectList(Long teamNo);
	public ProjectVo getProject(Long projNo);
}
