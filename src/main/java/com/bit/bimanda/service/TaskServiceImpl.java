package com.bit.bimanda.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bit.bimanda.repository.TaskDao;
import com.bit.bimanda.vo.TaskVo;
@Service
public class TaskServiceImpl implements TaskService {
	@Autowired
	TaskDao taskDaoImpl;
	@Override
	public List<TaskVo> getList(Long projNo) {
		return taskDaoImpl.selectAll(projNo);
	}

	@Override
	public boolean insert(TaskVo vo) {
		return 1==taskDaoImpl.insert(vo);
	}

	@Override
	public boolean delete(Long taskNo) {
		return 1==taskDaoImpl.delete(taskNo);
	}

	@Override
	public boolean update(Map<String, Object> map) {
		return 1==taskDaoImpl.update(map);
	}

	@Override
	public boolean move(Map<String, Object> map) {
		return 1==taskDaoImpl.move(map);
	}

	@Override
	public boolean setIndex(Long idx, Long index) {
		return 1==taskDaoImpl.setIndex(idx,index);
	}

	@Override
	public Long checkState(Long taskNo) {
		return taskDaoImpl.checkState(taskNo);
	}
}
