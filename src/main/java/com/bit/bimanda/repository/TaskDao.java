package com.bit.bimanda.repository;

import java.util.List;
import java.util.Map;

import com.bit.bimanda.vo.TaskVo;

public interface TaskDao {
	public List<TaskVo> selectAll(Long projNo);
	public int insert(TaskVo vo);
	public int delete(Long taskNo);
	public int move(Map<String, Object> map);
	public int update(Map<String, Object> map);
	public int setIndex(Long idx, Long index);
	public Long checkState(Long taskNo);
}
