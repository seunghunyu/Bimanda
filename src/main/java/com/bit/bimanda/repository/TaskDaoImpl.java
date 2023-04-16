package com.bit.bimanda.repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.bit.bimanda.vo.TaskVo;
@Repository
public class TaskDaoImpl implements TaskDao {
	@Autowired
	SqlSession sqlSession;
	
	@Override
	public List<TaskVo> selectAll(Long projNo) {
		return sqlSession.selectList("task.selectAll",projNo);
	}

	@Override
	public int insert(TaskVo vo) {
		return sqlSession.insert("task.insert",vo);
	}

	@Override
	public int delete(Long taskNo) {
		return sqlSession.delete("task.delete",taskNo);
	}

	@Override
	public int update(Map<String, Object> map) {
		return sqlSession.update("task.update",map);
	}

	@Override
	public int move(Map<String, Object> map) {
		return sqlSession.update("task.move",map);
	}


	@Override
	public int setIndex(Long idx, Long index) {
		// TODO Auto-generated method stub
		Map<String, Object> map = new HashMap<>();
		map.put("idx", idx );
		map.put("index", index);
		return sqlSession.update("task.setIndex", map);
	}

	@Override
	public Long checkState(Long taskNo) {
		return sqlSession.selectOne("task.checkState", taskNo);
	}

}
