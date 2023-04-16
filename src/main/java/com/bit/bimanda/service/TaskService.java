package com.bit.bimanda.service;

import java.util.List;
import java.util.Map;

import com.bit.bimanda.vo.TaskVo;

public interface TaskService {
	public List<TaskVo> getList(Long projNo);
	public boolean insert(TaskVo vo);
	public boolean delete(Long taskNo);
	public boolean update(Map<String, Object> map);
	public boolean move(Map<String, Object> map);
	public boolean setIndex(Long idx,Long index);
	public Long checkState(Long taskNo);
}
