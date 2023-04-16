package com.bit.bimanda.repository;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.bit.bimanda.vo.CategoryVo;

@Repository
public class CategoryDaoImpl implements CategoryDao{
	@Autowired
	SqlSession sqlSession;

	@Override
	public List<CategoryVo> getList() {
		// TODO Auto-generated method stub
		return sqlSession.selectList("category.getList");
	}
}
