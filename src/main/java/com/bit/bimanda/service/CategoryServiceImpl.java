package com.bit.bimanda.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bit.bimanda.repository.CategoryDao;
import com.bit.bimanda.vo.CategoryVo;

@Service
public class CategoryServiceImpl implements CategoryService{
	@Autowired
	CategoryDao categoryDaoImpl;

	@Override
	public List<CategoryVo> getList() {
		// TODO Auto-generated method stub
		
		return categoryDaoImpl.getList();
	}
	
}
